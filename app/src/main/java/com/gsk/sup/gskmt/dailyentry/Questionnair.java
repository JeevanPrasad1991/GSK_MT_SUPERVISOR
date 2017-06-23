package com.gsk.sup.gskmt.dailyentry;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemSelectedListener;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.delegates.CoverageBean;
import com.gsk.sup.gskmt.delegates.QuestionnairBean;

public class Questionnair extends AppCompatActivity implements OnClickListener {
    ExpandableListAdapter listAdapter;
    ExpandableListView lv;
    Button save_btn;
    List<QuestionnairBean> save_listDataHeader;
    private static ArrayList<QuestionnairBean> compliance_list = new ArrayList<QuestionnairBean>();
    private HashMap<QuestionnairBean, List<QuestionnairBean>> _listDataChild;
    List<QuestionnairBean> listDataHeader;
    HashMap<QuestionnairBean, List<QuestionnairBean>> listDataChild;
    private static ArrayList<QuestionnairBean> sub_compliance_list = new ArrayList<QuestionnairBean>();
    private static ArrayList<QuestionnairBean> answerslist = new ArrayList<QuestionnairBean>();
    GSK_MT_SUPDatabase db;
    SharedPreferences preferences;
    String store_id, process_id, answer, answer_id;
    private ArrayAdapter<CharSequence> reason_adapter;
    Boolean update = false;
    boolean validation_flag = true;
    List<Integer> validate_header = new ArrayList<>();
    ArrayList<CoverageBean> somecoverageData = new ArrayList<>();
    ArrayList<CoverageBean> coverageDataWithProcess = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnair);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CheckList");
        lv = (ExpandableListView) findViewById(R.id.lvExp);
        save_btn = (Button) findViewById(R.id.save_btn);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        preferences = PreferenceManager.getDefaultSharedPreferences(Questionnair.this);
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        db = new GSK_MT_SUPDatabase(Questionnair.this);
        db.open();
        process_id = getIntent().getStringExtra(CommonString.KEY_PROCESS_ID);
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        lv.setAdapter(listAdapter);
        save_btn.setOnClickListener(this);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                lv.clearFocus();
                if (SCROLL_STATE_TOUCH_SCROLL == scrollState) {
                    View currentFocus = getCurrentFocus();
                    if (currentFocus != null) {
                        currentFocus.clearFocus();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }


    private void prepareListData() {
        listDataHeader = new ArrayList<QuestionnairBean>();
        listDataChild = new HashMap<QuestionnairBean, List<QuestionnairBean>>();
        compliance_list = db.getInsertedAssetComplianceData(store_id, process_id);
        if (compliance_list.size() == 0) {
            compliance_list = db.getHeaderQuestionsData(store_id, process_id);

            for (int i2 = 0; i2 < compliance_list.size(); i2++) {
                QuestionnairBean chk = new QuestionnairBean();
                chk.setQSUB_CATEGORY_ID(compliance_list.get(i2).getQSUB_CATEGORY_ID());
                chk.setQSUB_CATEGORY(compliance_list.get(i2).getQSUB_CATEGORY());
                listDataHeader.add(chk);
                sub_compliance_list = db.getQuestionChildListData(compliance_list.get(i2).getQSUB_CATEGORY_ID(), store_id, process_id);
                for (int i = 0; i < sub_compliance_list.size(); i++) {
                    answerslist = db.getAllAnswers(sub_compliance_list.get(i).getQuestion_id(), process_id);
                    sub_compliance_list.get(i).setAnswer_id("");
                    sub_compliance_list.get(i).setAnswer("");
                }
                listDataChild.put(chk, sub_compliance_list);
            }

        } else {
            update = true;
            save_btn.setText("Update");
            for (int i2 = 0; i2 < compliance_list.size(); i2++) {
                QuestionnairBean chk = new QuestionnairBean();
                chk.setQSUB_CATEGORY(compliance_list.get(i2).getQSUB_CATEGORY());
                chk.setQSUB_CATEGORY_ID(compliance_list.get(i2).getQSUB_CATEGORY_ID());
                listDataHeader.add(chk);
                sub_compliance_list = db.getInsertedAssetSubList(compliance_list.get(i2).getCommonid(), store_id, process_id);
                listDataChild.put(chk, sub_compliance_list);
            }
        }
    }

    private class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context _context;

        public ExpandableListAdapter(Context context, List<QuestionnairBean> listDataHeader, HashMap<QuestionnairBean, List<QuestionnairBean>> listChildData) {
            this._context = context;
            save_listDataHeader = listDataHeader;
            _listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return _listDataChild.get(save_listDataHeader.get(groupPosition)).get(childPosition);
        }


        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final QuestionnairBean childText = (QuestionnairBean) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.asset_trackerlistview, null);
            }


//			TextView sku_name = (TextView)convertView.findViewById(R.id.sku_name);
            TextView asset_name = (TextView) convertView.findViewById(R.id.q_name);

            Spinner reasonSpinner = (Spinner) convertView.findViewById(R.id.answers);
            reason_adapter = new ArrayAdapter<CharSequence>(Questionnair.this, android.R.layout.simple_spinner_item);
            reason_adapter.add("-Select Ans-");
            final ArrayList<QuestionnairBean> answerslistNEW = db.getAllAnswers(childText.getQuestion_id(), process_id);
            for (int i3 = 0; i3 < answerslistNEW.size(); i3++) {
                reason_adapter.add(answerslistNEW.get(i3).getAnswer());
            }
            reasonSpinner.setAdapter(reason_adapter);
            reason_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            reasonSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    if (position != 0) {

                        answer = parent.getItemAtPosition(position).toString();

                        for (int i = 0; i < answerslistNEW.size(); i++) {
                            if (answer.equalsIgnoreCase(answerslistNEW.get(i).getAnswer())) {
                                answer_id = answerslistNEW.get(i).getAnswer_id();
                            }
                        }
                        _listDataChild.get(save_listDataHeader.get(groupPosition)).get(childPosition).setAnswer(answer);
                        _listDataChild.get(save_listDataHeader.get(groupPosition)).get(childPosition).setAnswer_id(answer_id);
//				    		 lv.invalidateViews();
                    } else {
                        _listDataChild.get(save_listDataHeader.get(groupPosition)).get(childPosition).setAnswer_id("");
                        _listDataChild.get(save_listDataHeader.get(groupPosition)).get(childPosition).setAnswer("");
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });

            String selectedAns = _listDataChild.get(save_listDataHeader.get(groupPosition))
                    .get(childPosition).getAnswer();

            if (selectedAns.equalsIgnoreCase("")) {
                reasonSpinner.setSelection(0);

            } else {
                int position = 0;
                for (int i = 0; i < answerslistNEW.size(); i++) {

                    String ans = answerslistNEW.get(i).getAnswer();

                    if (ans.equalsIgnoreCase(selectedAns)) {

                        position = i;
                        break;

                    }


                }

                reasonSpinner.setSelection(position + 1);

            }

            asset_name.setText(childText.getQuestion());


            return convertView;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public int getGroupCount() {
            return save_listDataHeader.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return _listDataChild.get(save_listDataHeader.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return save_listDataHeader.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            final QuestionnairBean headerTitle = (QuestionnairBean) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.brand, null);
            }
            TextView brand_name = (TextView) convertView.findViewById(R.id.brand_name);
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.first);
            if (!validation_flag) {
                if (validate_header.contains(groupPosition)) {
                    layout.setBackgroundColor(Color.RED);
                } else {
                    layout.setBackgroundColor(Color.parseColor("#CCCCCC"));
                }
            }
            brand_name.setText(headerTitle.getQSUB_CATEGORY());
            return convertView;

        }
    }


    boolean validateData(
            HashMap<QuestionnairBean, List<QuestionnairBean>> listDataChild2,
            List<QuestionnairBean> listDataHeader2) {
        boolean flag = true;
        validate_header.clear();
        for (int i = 0; i < listDataHeader2.size(); i++) {
            for (int j = 0; j < listDataChild2.get(listDataHeader.get(i)).size(); j++) {
                String answer = listDataChild.get(listDataHeader.get(i)).get(j).getAnswer();
                if (answer.equalsIgnoreCase("")) {
                    flag = false;
                    validation_flag = false;
                    validate_header.add(i);
                    break;
                } else {
                    flag = true;
                }
            }


            if (flag == false) {
                break;
            }


        }


        return flag;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_btn) {
            lv.clearFocus();
            if (validateData(listDataChild, listDataHeader)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to save")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        db.open();
                                        coverageDataWithProcess= db.getCoverageDataWithProcessid(store_id,process_id);
                                        somecoverageData = db.getCoverageSomeData_(store_id);
                                        if (coverageDataWithProcess.size()>0){
                                            db.deleteQuestionnairData(store_id, process_id);
                                            db.InsertQuestionnairAnswerData(process_id, store_id, listDataChild, listDataHeader);
                                        }else {
                                            db.open();
                                            String remark = "";
                                            db.InsertCoverage(somecoverageData.get(0), process_id, "1", remark, CommonString.KEY_INVALID);
                                            db.deleteQuestionnairData(store_id, process_id);
                                            db.InsertQuestionnairAnswerData(process_id, store_id, listDataChild, listDataHeader);
                                        }
                                        Snackbar.make(lv, "Data has been saved", Snackbar.LENGTH_LONG).show();
                                        Intent DailyEntryMenu = new Intent(Questionnair.this, ProcessEntry.class);
                                        startActivity(DailyEntryMenu);
                                        finish();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();

                alert.show();
            } else {
                Snackbar.make(lv, "Please fill all Questions ", Snackbar.LENGTH_LONG).show();
                lv.invalidateViews();
            }


        }

    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(this, ProcessEntry.class);
        startActivity(in);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent in = new Intent(this, ProcessEntry.class);
            startActivity(in);
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

}
