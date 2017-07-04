package com.gsk.sup.gskmt.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.PerformanceTabActivity;
import com.gsk.sup.gskmt.xmlGetterSetter.SupincentiveGetterSetter;

import java.util.ArrayList;

public class MyperformanceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ListView myPerList;
    GSK_MT_SUPDatabase db;
    ArrayList<SupincentiveGetterSetter> list = new ArrayList<>();
    // TODO: Rename and change types of parameters
    public MyperformanceFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myperformance, container, false);
        myPerList = (ListView) view.findViewById(R.id.myperform_list);
        db = new GSK_MT_SUPDatabase(getActivity());
        db.open();
        list = db.getSupIncentiveData();
        if (list.size()>0){
            myPerList.setAdapter(new MyAdapterSales());
        }
        return view;
    }


    private class MyAdapterSales extends BaseAdapter {
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
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_sales, null);
                holder.currentM = (TextView) convertView.findViewById(R.id.cumonth_txt);
                holder.Pm1 = (TextView) convertView.findViewById(R.id.pm1_txt);
                holder.pm2 = (TextView) convertView.findViewById(R.id.pm2_txt);
                holder.pm3 = (TextView) convertView.findViewById(R.id.pm3_txt);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.currentM.setText(list.get(position).getCurMonth().get(0));
            holder.pm3.setBackgroundColor(getPerColorwithDivided(list.get(position).getPm3per().get(0)));
            holder.Pm1.setBackgroundColor(getPerColorwithDivided(list.get(position).getPm1per().get(0)));
            holder.pm2.setBackgroundColor(getPerColorwithDivided(list.get(position).getPm2per().get(0)));


            holder.Pm1.setText(list.get(position).getPM1().get(0));
            holder.pm2.setText(list.get(position).getPM2().get(0));
            holder.pm3.setText(list.get(position).getPM3().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView currentM, Pm1, pm2, pm3;

        }

    }

    public int getPerColorwithDivided(String  val) {
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
