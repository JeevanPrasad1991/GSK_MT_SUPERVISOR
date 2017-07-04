package com.gsk.sup.gskmt.dailyentry;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.customdialog.CustomdialogClass;
import com.gsk.sup.gskmt.xmlGetterSetter.PssStorewiseDetailGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.PssStorewiseGetterSetter;

import java.util.ArrayList;


public class StorewisePssPerformanceActivity extends AppCompatActivity {
    TextView store_name_txt;
    Button btnok, no;
    ListView storewisesalePList, pss_list;
    Toolbar toolbar;
    SharedPreferences preferences;
    GSK_MT_SUPDatabase db;
    String date, process_id, store_id, STORE_NAME;
    LinearLayout ll_parent, rl_some_name, rl_store, no_data_lay;
    ArrayList<PssStorewiseGetterSetter> list = new ArrayList<>();
    ArrayList<PssStorewiseDetailGetterSetter> listPssDetails = new ArrayList<>();
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storewise_pss_performance);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        store_name_txt = (TextView) findViewById(R.id.store_name_txt1);
        storewisesalePList = (ListView) findViewById(R.id.storewisesalePList);

        ll_parent = (LinearLayout) findViewById(R.id.ll_parent);
        rl_some_name = (LinearLayout) findViewById(R.id.rl_some_name);
        rl_store = (LinearLayout) findViewById(R.id.rl_store);
        no_data_lay = (LinearLayout) findViewById(R.id.no_data_lay);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        date = preferences.getString(CommonString.KEY_DATE, null);
        process_id = preferences.getString(CommonString.KEY_PROCESS_ID, null);
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        STORE_NAME = preferences.getString(CommonString.KEY_STORE_NAME, null);
        store_name_txt.setText(STORE_NAME);
        db = new GSK_MT_SUPDatabase(this);
        db.open();
        list = db.getStoreWisePSSData(store_id);
        if (list.size() > 0) {
            ll_parent.setVisibility(View.VISIBLE);
            rl_some_name.setVisibility(View.VISIBLE);
            rl_store.setVisibility(View.VISIBLE);
            no_data_lay.setVisibility(View.GONE);
            storewisesalePList.setAdapter(new FocusBrandAdapter());
        } else {
            ll_parent.setVisibility(View.GONE);
            rl_some_name.setVisibility(View.GONE);
            rl_store.setVisibility(View.GONE);
            no_data_lay.setVisibility(View.VISIBLE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProcessEntry.class));
                finish();
            }
        });
    }

    private class pssDetailsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listPssDetails.size();
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
                convertView = inflater.inflate(R.layout.row_pss_details_storewise, null);
                holder.emp = (TextView) convertView.findViewById(R.id.emp_txt3);
                holder.current = (TextView) convertView.findViewById(R.id.current_txt3);
                holder.fpm1 = (TextView) convertView.findViewById(R.id.Fpm1_txt3);
                holder.fpm2 = (TextView) convertView.findViewById(R.id.Fpm2_txt3);
                holder.fpm3 = (TextView) convertView.findViewById(R.id.Fpm3_txt3);
                holder.total_store_txt = (TextView) convertView.findViewById(R.id.total_store_txt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.emp.setText(listPssDetails.get(position).getCategory().get(0));
            holder.current.setText(listPssDetails.get(position).getSos().get(0));
            holder.fpm1.setText(listPssDetails.get(position).getTot().get(0));
            holder.fpm2.setText(listPssDetails.get(position).getPaid().get(0));
            holder.fpm3.setText(listPssDetails.get(position).getAddition().get(0));
            holder.total_store_txt.setText(listPssDetails.get(position).getPss_store().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView emp, current, fpm1, fpm2, fpm3, total_store_txt;
        }
    }

    private class FocusBrandAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
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
                convertView = inflater.inflate(R.layout.row_pss_sales_storewise, null);
                holder.emp = (TextView) convertView.findViewById(R.id.emp_txt2);
                holder.current = (TextView) convertView.findViewById(R.id.current_txt2);
                holder.fpm1 = (TextView) convertView.findViewById(R.id.Fpm1_txt2);
                holder.fpm2 = (TextView) convertView.findViewById(R.id.Fpm2_txt2);
                holder.fpm3 = (TextView) convertView.findViewById(R.id.Fpm3_txt2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.current.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db = new GSK_MT_SUPDatabase(StorewisePssPerformanceActivity.this);
                    db.open();
                    listPssDetails = db.getStoreWisePSSDetailsData(store_id);
                    if (listPssDetails.size() == 0) {
                        Snackbar.make(storewisesalePList, "No data availeble in Pss Details", Snackbar.LENGTH_LONG).show();
                    } else {
                        final Dialog dialog = new Dialog(StorewisePssPerformanceActivity.this);
                        //setting custom layout to dialog
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_dialog);
                        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                        //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        pss_list = (ListView) dialog.findViewById(R.id.pss_list);
                        btnok = (Button) dialog.findViewById(R.id.btnok);
                        db = new GSK_MT_SUPDatabase(StorewisePssPerformanceActivity.this);
                        db.open();
                        listPssDetails = db.getStoreWisePSSDetailsData(store_id);
                        if (listPssDetails.size() > 0) {
                            pss_list.setAdapter(new pssDetailsAdapter());
                        }
                        btnok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                }
            });

           /* holder.fpm1.setBackgroundColor(getPerColor(focussalesList.get(position).getPm1per().get(0)));
            holder.fpm2.setBackgroundColor(getPerColor(focussalesList.get(position).getPm2per().get(0)));
            holder.fpm3.setBackgroundColor(getPerColor(focussalesList.get(position).getPm3per().get(0)));*/
            holder.current.setBackgroundColor(getPerColor(list.get(position).getCurrMonthper().get(0)));
            holder.emp.setText(list.get(position).getMerchanD().get(0));
            holder.current.setText(list.get(position).getCurrentM().get(0));
            holder.fpm1.setText(list.get(position).getPm1().get(0));
            holder.fpm2.setText(list.get(position).getPm2().get(0));
            holder.fpm3.setText(list.get(position).getPm3().get(0));
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
            startActivity(new Intent(this, StoreWisePerformanceActivity.class));
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(this, StoreWisePerformanceActivity.class);
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


}
