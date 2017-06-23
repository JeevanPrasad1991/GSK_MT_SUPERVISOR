package com.gsk.sup.gskmt.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.dailyentry.ProcessEntry;
import com.gsk.sup.gskmt.dailyentry.StoreWisePerformanceActivity;
import com.gsk.sup.gskmt.dailyentry.StorewisePssPerformanceActivity;
import com.gsk.sup.gskmt.xmlGetterSetter.PssStorewiseDetailGetterSetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 29-05-2017.
 */

public class CustomdialogClass extends Dialog implements View.OnClickListener {
    ListView pss_list;
    Button btnok,no;
    SharedPreferences preferences;
    GSK_MT_SUPDatabase db;
    String  store_id;
    Context context;
    ArrayList<PssStorewiseDetailGetterSetter>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        pss_list= (ListView) findViewById(R.id.pss_list);
        btnok= (Button) findViewById(R.id.btnok);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        db = new GSK_MT_SUPDatabase(getOwnerActivity());
        db.open();
        list=db.getStoreWisePSSDetailsData(store_id);
        if (list.size()>0){
            pss_list.setAdapter(new pssDetailsAdapter());
        }
    }

  /*  ll_parent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Dialog dialog = new Dialog(StorewisePssPerformanceActivity.this);
            //setting custom layout to dialog
            dialog.setContentView(R.layout.custom_dialog);
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            pss_list = (ListView) dialog.findViewById(R.id.pss_list);
            btnok = (Button) dialog.findViewById(R.id.btnok);
            db = new GSK_MT_SUPDatabase(StorewisePssPerformanceActivity.this);
            db.open();
            listPssDetails = db.getStoreWisePSSDetailsData(store_id);
            if (listPssDetails.size() > 0) {
                pss_list.setAdapter(new StorewisePssPerformanceActivity.pssDetailsAdapter());
            }
            btnok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    });*/

    public CustomdialogClass(Context context) {
        super(context);

    }

    @Override
    public void onClick(View v) {
        int  id=v.getId();
        switch (id){
            case R.id.btnok:
                Intent intent=new Intent(getContext(), ProcessEntry.class);
               getContext().startActivity(intent);
                break;
        }

    }

    private class pssDetailsAdapter extends BaseAdapter {
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
                LayoutInflater inflater = (LayoutInflater) getOwnerActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_pss_details_storewise, null);
                holder.emp = (TextView) convertView.findViewById(R.id.emp_txt3);
                holder.current = (TextView) convertView.findViewById(R.id.current_txt3);
                holder.fpm1 = (TextView) convertView.findViewById(R.id.Fpm1_txt3);
                holder.fpm2 = (TextView) convertView.findViewById(R.id.Fpm2_txt3);
                holder.fpm3 = (TextView) convertView.findViewById(R.id.Fpm3_txt3);
                holder.total_store_txt= (TextView) convertView.findViewById(R.id.total_store_txt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.emp.setText(list.get(position).getCategory().get(0));
            holder.current.setText(list.get(position).getSos().get(0));
            holder.fpm1.setText(list.get(position).getTot().get(0));
            holder.fpm2.setText(list.get(position).getPaid().get(0));
            holder.fpm3.setText(list.get(position).getAddition().get(0));
            holder.total_store_txt.setText(list.get(position).getPss_store().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView emp, current, fpm1, fpm2, fpm3,total_store_txt;

        }
    }
}
