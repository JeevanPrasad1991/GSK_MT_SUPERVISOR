package com.gsk.sup.gskmt.dailyentry;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.delegates.StoreBean;

public class ProcessEntry extends AppCompatActivity {
    GridView gv;
    SharedPreferences preferences;
    GSK_MT_SUPDatabase db;
    String date, process_id, store_id, category_list;
    ArrayList<StoreBean> process_list;
    private SharedPreferences.Editor editor = null;
    boolean checkListflag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Process Entry");
        gv = (GridView) findViewById(R.id.gridView1);
        process_list = new ArrayList<StoreBean>();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        db = new GSK_MT_SUPDatabase(this);
        db.open();
        date = preferences.getString(CommonString.KEY_DATE, null);
        process_id = preferences.getString(CommonString.KEY_PROCESS_ID, null);
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        process_list = db.getProcess(date, store_id);
        if (process_list.size() > 0) {
            gv.setAdapter(new MyAdaptor());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = new GSK_MT_SUPDatabase(this);
        db.open();
        if (process_list.size() > 0) {
            for (int i = 0; i < process_list.size(); i++) {
                checkListflag = db.isOpeningDataFilledChecklist(store_id, process_list.get(i).getPROCESS_ID());
            }
            if (checkListflag) {
                db.updateCoverageCheckoutStatus(store_id, date, CommonString.KEY_VALID);
                db.deleteSomeCoverageData();
            }
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(ProcessEntry.this, StoreListActivity.class);
        startActivity(in);
        ProcessEntry.this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent in = new Intent(ProcessEntry.this, StoreListActivity.class);
            startActivity(in);
            ProcessEntry.this.finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

    private class ViewHolder {
        ImageView img;
    }

    private class MyAdaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return process_list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (holder == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.category_grid_view, null);
                // holder.ProcessName = (TextView) convertView.findViewById(R.id.name);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            db.open();
            checkListflag = db.isOpeningDataFilledChecklist(store_id, process_list.get(position).getPROCESS_ID());
            // holder.ProcessName.setText(process_list.get(position).getProcess());
            if (process_list.get(position).getProcess().equalsIgnoreCase("OHC")) {
                if (checkListflag) {
                    holder.img.setImageResource(R.drawable.ohc_tick);
                } else {
                    holder.img.setImageResource(R.drawable.ohc_ico);
                }
            } else if (process_list.get(position).getProcess().equalsIgnoreCase("HFD")) {
                if (checkListflag) {
                    holder.img.setImageResource(R.drawable.hfd_tick);
                } else {
                    holder.img.setImageResource(R.drawable.hfd_ico);
                }

            } else if (process_list.get(position).getProcess().equalsIgnoreCase("Merchandising")) {
                if (checkListflag) {
                    holder.img.setImageResource(R.drawable.merchandising_done);
                } else {
                    holder.img.setImageResource(R.drawable.merchandising);
                }
            }
            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getBaseContext(), Questionnair.class);
                    in.putExtra(CommonString.KEY_PROCESS_ID, process_list.get(position).getPROCESS_ID());
                    // in.putExtra(CommonString.KEY_PROCESS_NAME, process_list.get(position).getProcess());
                    startActivity(in);
                    ProcessEntry.this.finish();
                }
            });

            return convertView;
        }

    }

}
