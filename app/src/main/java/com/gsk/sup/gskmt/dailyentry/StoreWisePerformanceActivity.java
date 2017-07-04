package com.gsk.sup.gskmt.dailyentry;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.xmlGetterSetter.FocusSaleStoreWiseGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.PerformanceNonAchivementGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.RemarkGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.TotalSaleStorewiseGetterSetter;

import java.util.ArrayList;

public class StoreWisePerformanceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView storewisePList, total_list;
    Toolbar toolbar;
    SharedPreferences preferences;
    GSK_MT_SUPDatabase db;
    String date, process_id, store_id, STORE_NAME;
    TextView store_name_txt;
    LinearLayout rl_Remark, rl_store_nam, no_data_lay, rl_focus, rl_total;
    FloatingActionButton save_btn;
    Spinner remark_spinner;
    ArrayList<FocusSaleStoreWiseGetterSetter> focussalesList = new ArrayList<>();
    ArrayList<TotalSaleStorewiseGetterSetter> totalSaleList = new ArrayList<>();
    ArrayList<RemarkGetterSetter> remarkList = new ArrayList<>();
    String reason_name = "", reason_id = "";
    TextView emp, current, fpm1, fpm2, fpm3, emp1, current1, fpm1_1, fpm2_1, fpm3_1;
    private ArrayAdapter<CharSequence> reason_adapter;
    PerformanceNonAchivementGetterSetter performanceNonAchivementGetterSetter;
    int a, b, c;
    int color, color1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_wise_performance);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        storewisePList = (ListView) findViewById(R.id.storewisesalePList);
        store_name_txt = (TextView) findViewById(R.id.store_name_txt);
        total_list = (ListView) findViewById(R.id.storewiseTotalSaleList);
        rl_Remark = (LinearLayout) findViewById(R.id.rl_Remark);
        save_btn = (FloatingActionButton) findViewById(R.id.save_btn1);
        remark_spinner = (Spinner) findViewById(R.id.remark_spinner);

        rl_store_nam = (LinearLayout) findViewById(R.id.rl_store_nam);
        no_data_lay = (LinearLayout) findViewById(R.id.no_data_lay);
        rl_focus = (LinearLayout) findViewById(R.id.rl_focus);
        rl_total = (LinearLayout) findViewById(R.id.rl_total);

        emp = (TextView) findViewById(R.id.emp_txt1);
        current = (TextView) findViewById(R.id.current_txt1);
        fpm1 = (TextView) findViewById(R.id.Fpm1_txt1);
        fpm2 = (TextView) findViewById(R.id.Fpm2_txt1);
        fpm3 = (TextView) findViewById(R.id.Fpm3_txt1);
        emp1 = (TextView) findViewById(R.id.emp_txt2);
        current1 = (TextView) findViewById(R.id.current_txt2);
        fpm1_1 = (TextView) findViewById(R.id.Fpm1_txt2);
        fpm2_1 = (TextView) findViewById(R.id.Fpm2_txt2);
        fpm3_1 = (TextView) findViewById(R.id.Fpm3_txt2);
        db = new GSK_MT_SUPDatabase(this);
        db.open();
        date = preferences.getString(CommonString.KEY_DATE, null);
        process_id = preferences.getString(CommonString.KEY_PROCESS_ID, null);
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        STORE_NAME = preferences.getString(CommonString.KEY_STORE_NAME, null);

        remarkList = db.getRemarksData();
        reason_adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_custom_item);
        reason_adapter.add("-Select Reason-");
        for (int i = 0; i < remarkList.size(); i++) {
            reason_adapter.add(remarkList.get(i).getRemark().get(0));
        }
        remark_spinner.setAdapter(reason_adapter);
        performanceNonAchivementGetterSetter = db.getnonAchievementData(store_id);

        for (int i = 0; i < remarkList.size(); i++) {
            if (performanceNonAchivementGetterSetter.getReason_id().equals(remarkList.get(i).getRemark_cd().get(0))) {
                remark_spinner.setSelection(i + 1);
            }
        }
        remark_spinner.setOnItemSelectedListener(this);
        store_name_txt.setText(STORE_NAME);
        focussalesList = db.getStoreWiseFocusSaleData(store_id);
        totalSaleList = db.getStoreWiseTotalSaleData(store_id);

        if (focussalesList.size() == 0 && totalSaleList.size() == 0) {
            rl_Remark.setVisibility(View.GONE);
            rl_total.setVisibility(View.GONE);
            rl_focus.setVisibility(View.GONE);
            rl_store_nam.setVisibility(View.GONE);
            no_data_lay.setVisibility(View.VISIBLE);
            save_btn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.right_arrow));
        }
        if (focussalesList.size() > 0) {
            rl_focus.setVisibility(View.VISIBLE);
            rl_store_nam.setVisibility(View.VISIBLE);
            no_data_lay.setVisibility(View.GONE);
            emp1.setText(focussalesList.get(0).getEmployee().get(0));
            current1.setBackgroundColor(getPerColor(focussalesList.get(0).getCurrMonthper().get(0)));
            current1.setText(focussalesList.get(0).getCuurrentM().get(0));
            fpm1_1.setText(focussalesList.get(0).getPm1().get(0));
            fpm2_1.setText(focussalesList.get(0).getPm2().get(0));
            fpm3_1.setText(focussalesList.get(0).getPm3().get(0));
            // storewisePList.setAdapter(new FocusBrandAdapter());

            color1 = getPerColor(focussalesList.get(0).getCurrMonthper().get(0));
            if (color == Color.RED) {
                rl_Remark.setVisibility(View.VISIBLE);
                save_btn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.save_icon));
            } else {
                save_btn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.right_arrow));
            }

            current1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(StoreWisePerformanceActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_dialog_focus);
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    final TextView focus_target = (TextView) dialog.findViewById(R.id.focus_target);
                    final TextView focus_achievment = (TextView) dialog.findViewById(R.id.focus_achievment);
                    final TextView focus_perc = (TextView) dialog.findViewById(R.id.focus_perc);
                    focus_target.setText(focussalesList.get(0).getTarget().get(0));
                    focus_achievment.setText(focussalesList.get(0).getCuurrentM().get(0));
                    focus_perc.setText(focussalesList.get(0).getCurrMonthper().get(0));
                    final Button btn_ok = (Button) dialog.findViewById(R.id.btn_ok);
                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        } else {
            rl_focus.setVisibility(View.GONE);
        }


        if (totalSaleList.size() > 0) {
            rl_total.setVisibility(View.VISIBLE);
            rl_store_nam.setVisibility(View.VISIBLE);
            no_data_lay.setVisibility(View.GONE);
            emp.setText(totalSaleList.get(0).getEmployee().get(0));
            current.setBackgroundColor(getPerColor(totalSaleList.get(0).getCurrMonthper().get(0)));
            current.setText(totalSaleList.get(0).getCurrentM().get(0));
            fpm1.setText(totalSaleList.get(0).getPm1().get(0));
            fpm2.setText(totalSaleList.get(0).getPm2().get(0));
            fpm3.setText(totalSaleList.get(0).getPm3().get(0));

            color = getPerColor(totalSaleList.get(0).getCurrMonthper().get(0));
            if (color == Color.RED) {
                rl_Remark.setVisibility(View.VISIBLE);
                save_btn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.save_icon));
            } else {
                save_btn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.right_arrow));
            }
            current.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(StoreWisePerformanceActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_dialog_total);
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    final TextView total_target = (TextView) dialog.findViewById(R.id.total_target);
                    final TextView total_achievment = (TextView) dialog.findViewById(R.id.total_achievment);
                    final TextView total_perc = (TextView) dialog.findViewById(R.id.total_perc);
                    total_target.setText(totalSaleList.get(0).getTarget().get(0));
                    total_achievment.setText(totalSaleList.get(0).getCurrentM().get(0));
                    total_perc.setText(totalSaleList.get(0).getCurrMonthper().get(0));
                    final Button btn_ok = (Button) dialog.findViewById(R.id.btn_ok);
                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }
            });
        } else {
            rl_total.setVisibility(View.GONE);
        }


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new GSK_MT_SUPDatabase(StoreWisePerformanceActivity.this);
                if (color == Color.RED || color1 == Color.RED) {
                    if (validatedata()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(StoreWisePerformanceActivity.this);
                        builder.setTitle("Parinaam").setMessage("Save Non achievement data");
                        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.open();
                                // db.getnonAchievementData()
                                db.InsertNonAchivementPerformance(store_id, reason_id, reason_name,date);
                                db.close();
                                startActivity(new Intent(getApplicationContext(), StorewisePssPerformanceActivity.class));
                                finish();
                            }
                        });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();


                    } else {
                        Snackbar.make(save_btn, "Please select reason", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), StorewisePssPerformanceActivity.class));
                    finish();
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.remark_spinner:
                if (position != 0) {
                    reason_name = remarkList.get(position - 1).getRemark().get(0);
                    reason_id = remarkList.get(position - 1).getRemark_cd().get(0);
                }
                break;
        }


    }


    public boolean validatedata() {
        boolean result = false;
        if (!reason_id.equals("")) {
            result = true;
        }
        return result;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class FocusBrandAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return focussalesList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_focus_sales_storewise, null);
                holder.emp = (TextView) convertView.findViewById(R.id.emp_txt1);
                holder.current = (TextView) convertView.findViewById(R.id.current_txt1);
                holder.fpm1 = (TextView) convertView.findViewById(R.id.Fpm1_txt1);
                holder.fpm2 = (TextView) convertView.findViewById(R.id.Fpm2_txt1);
                holder.fpm3 = (TextView) convertView.findViewById(R.id.Fpm3_txt1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.emp.setText(focussalesList.get(position).getEmployee().get(0));
            holder.current.setText(focussalesList.get(position).getCuurrentM().get(0));
            holder.fpm1.setText(focussalesList.get(position).getPm1().get(0));
            holder.fpm2.setText(focussalesList.get(position).getPm2().get(0));
            holder.fpm3.setText(focussalesList.get(position).getPm3().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView emp, current, fpm1, fpm2, fpm3;

        }
    }


    private class totalSaleStorewiseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return totalSaleList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_total_sales_storewise, null);
                holder.emp = (TextView) convertView.findViewById(R.id.emp_txt2);
                holder.current = (TextView) convertView.findViewById(R.id.current_txt2);
                holder.fpm1 = (TextView) convertView.findViewById(R.id.Fpm1_txt2);
                holder.fpm2 = (TextView) convertView.findViewById(R.id.Fpm2_txt2);
                holder.fpm3 = (TextView) convertView.findViewById(R.id.Fpm3_txt2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

          /*  color1 = checkachievement(totalSaleList.get(position).getCurrentM().get(0), totalSaleList.get(position).getTarget().get(0));
            if (color1.equalsIgnoreCase("red")) {
                holder.current.setBackgroundColor(Color.RED);
            } else if (color1.equalsIgnoreCase("amber")) {
                holder.current.setBackgroundColor(Color.YELLOW);
            } else {
                if (color1.equalsIgnoreCase("green")) {
                    holder.current.setBackgroundColor(Color.GREEN);
                }
            }*/

            holder.emp.setText(totalSaleList.get(position).getEmployee().get(0));
            holder.current.setText(totalSaleList.get(position).getCurrentM().get(0));
            holder.fpm1.setText(totalSaleList.get(position).getPm1().get(0));
            holder.fpm2.setText(totalSaleList.get(position).getPm2().get(0));
            holder.fpm3.setText(totalSaleList.get(position).getPm3().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView emp, current, fpm1, fpm2, fpm3;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(this, StoreListActivity.class));
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(this, StoreListActivity.class);
        startActivity(in);
        finish();
    }

    public int getPerColor(String val) {
        int per = 0;
        try {
            per = Integer.parseInt(val);
        } catch (Exception e) {

        }

        if (per > 70) {
            return Color.GREEN;
        } else {
            return Color.RED;
        }
    }


    public String checkachievement(String curent, String target) {
        String color_name = "";
        try {
            int gsk_curent = Integer.parseInt(curent), target_num = Integer.parseInt(target);
            Double calculate_value = Double.valueOf((gsk_curent / target_num) * 100);
            if (calculate_value > 60 || calculate_value == 60) {
                color_name = "green";
            } else if ((calculate_value < 60) && (calculate_value > 40) || calculate_value == 40) {
                color_name = "amber";
            } else {
                color_name = "red";

            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        return color_name;
    }

}
