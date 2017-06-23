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
import com.gsk.sup.gskmt.xmlGetterSetter.SupFocusSaleGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.TeamPassGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.TotalSaleGetterSetter;

import java.util.ArrayList;

public class TeamPerformanceFragment extends Fragment {
    // TODO: Rename and change types of parameters
    private static final String ARG_SECTION_NUMBER = "section_number";
    ListView focusS_list, totalsale_list, teampss_list;
    GSK_MT_SUPDatabase database;
    ArrayList<SupFocusSaleGetterSetter> focusSaleList = new ArrayList<>();
    ArrayList<TotalSaleGetterSetter> totalSaleList = new ArrayList<>();
    ArrayList<TeamPassGetterSetter> teamPssSaleList = new ArrayList<>();
    int focus_currentTotal = 0, focus_pm1 = 0, focus_pm2 = 0, focus_pm3 = 0;
    TextView total_txt, current_total, pm1_tot, pm2_total, pm3_total_txt;
    TextView current_total1, pm1_tot1, pm2_total1, pm3_total_txt1;
    TextView current_total2, pm1_tot2, pm2_total2, pm3_total_txt2;
    int focus_currentTotal1 = 0, focus_pm1_1 = 0, focus_pm2_1 = 0, focus_pm3_1 = 0;
    int focus_currentTotal2 = 0, focus_pm1_2 = 0, focus_pm2_2 = 0, focus_pm3_2 = 0;
    public TeamPerformanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_performance, container, false);
        focusS_list = (ListView) view.findViewById(R.id.focus_list);
        totalsale_list = (ListView) view.findViewById(R.id.totalsale_list);
        teampss_list = (ListView) view.findViewById(R.id.teampss_list);
     //   total_txt = (TextView) view.findViewById(R.id.total_txt);
        current_total = (TextView) view.findViewById(R.id.current_total);
        pm1_tot = (TextView) view.findViewById(R.id.pm1_tot);
        pm2_total = (TextView) view.findViewById(R.id.pm2_total);
        pm3_total_txt = (TextView) view.findViewById(R.id.pm3_total_txt);

        current_total1 = (TextView) view.findViewById(R.id.current_total1);
        pm1_tot1 = (TextView) view.findViewById(R.id.pm1_tot1);
        pm2_total1 = (TextView) view.findViewById(R.id.pm2_total1);
        pm3_total_txt1 = (TextView) view.findViewById(R.id.pm3_total_txt1);

        current_total2 = (TextView) view.findViewById(R.id.current_total2);
        pm1_tot2 = (TextView) view.findViewById(R.id.pm1_tot2);
        pm2_total2 = (TextView) view.findViewById(R.id.pm2_total2);
        pm3_total_txt2 = (TextView) view.findViewById(R.id.pm3_total_txt2);
        database = new GSK_MT_SUPDatabase(getActivity());
        database.open();
        focusSaleList = database.getSupFocusSaleData();
        if (focusSaleList.size() > 0) {
            for (int i = 0; i < focusSaleList.size(); i++) {
                int a = Integer.parseInt(focusSaleList.get(i).getCurrMonth().get(0));
                focus_currentTotal = focus_currentTotal + a;
                int b = Integer.parseInt(focusSaleList.get(i).getPm1().get(0));
                focus_pm1 = focus_pm1 + b;
                int c = Integer.parseInt(focusSaleList.get(i).getPm2().get(0));
                focus_pm2 = focus_pm2 + c;
                int d = Integer.parseInt(focusSaleList.get(i).getPm3().get(0));
                focus_pm3 = focus_pm3 + d;
            }
            pm1_tot.setBackgroundColor(Color.GREEN);
            pm2_total.setBackgroundColor(Color.GREEN);
            pm3_total_txt.setBackgroundColor(Color.GREEN);

            current_total.setText(String.valueOf(focus_currentTotal));
            pm1_tot.setText(String.valueOf(focus_pm1));
            pm2_total.setText(String.valueOf(focus_pm2));
            pm3_total_txt.setText(String.valueOf(focus_pm3));
            focusS_list.setAdapter(new FocusBrandAdapter());
        }
        totalSaleList = database.getSupTotalSalesData();
        if (totalSaleList.size() > 0) {
            for (int i = 0; i < totalSaleList.size(); i++) {
                int a = Integer.parseInt(totalSaleList.get(i).getCurrMonth().get(0));
                focus_currentTotal1 = focus_currentTotal1 + a;
                int b = Integer.parseInt(totalSaleList.get(i).getPm1().get(0));
                focus_pm1_1 = focus_pm1_1 + b;
                int c = Integer.parseInt(totalSaleList.get(i).getPm2().get(0));
                focus_pm2_1 = focus_pm2_1 + c;
                int d = Integer.parseInt(totalSaleList.get(i).getPm3().get(0));
                focus_pm3_1 = focus_pm3_1 + d;
            }

            pm1_tot1.setBackgroundColor(Color.GREEN);
            pm2_total1.setBackgroundColor(Color.GREEN);
            pm3_total_txt1.setBackgroundColor(Color.GREEN);

            current_total1.setText(String.valueOf(focus_currentTotal1));
            pm1_tot1.setText(String.valueOf(focus_pm1_1));
            pm2_total1.setText(String.valueOf(focus_pm2_1));
            pm3_total_txt1.setText(String.valueOf(focus_pm3_1));

            totalsale_list.setAdapter(new SalesTotalAdapter());
        }
        teamPssSaleList = database.getTeamPssSalesData();
        if (teamPssSaleList.size() > 0) {
            for (int i = 0; i < teamPssSaleList.size(); i++) {
                int a = Integer.parseInt(teamPssSaleList.get(i).getPerfect().get(0));
                focus_currentTotal2 = focus_currentTotal2 + a;
                int b = Integer.parseInt(teamPssSaleList.get(i).getNear20perF().get(0));
                focus_pm1_2 = focus_pm1_2 + b;
                int c = Integer.parseInt(teamPssSaleList.get(i).getNot20perF().get(0));
                focus_pm2_2 = focus_pm2_2 + c;
                int d = Integer.parseInt(teamPssSaleList.get(i).getTotal20store().get(0));
                focus_pm3_2 = focus_pm3_2 + d;
            }

            current_total2.setText(String.valueOf(focus_currentTotal2));
            pm1_tot2.setText(String.valueOf(focus_pm1_2));
            pm2_total2.setText(String.valueOf(focus_pm2_2));
            pm3_total_txt2.setText(String.valueOf(focus_pm3_2));
            teampss_list.setAdapter(new TeamPssAdapter());
        }

        return view;
    }

    private class FocusBrandAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return focusSaleList.size();
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
                convertView = inflater.inflate(R.layout.row_focus_sales, null);
                holder.emp = (TextView) convertView.findViewById(R.id.emp_txt);
                holder.current = (TextView) convertView.findViewById(R.id.current_txt);
                holder.fpm1 = (TextView) convertView.findViewById(R.id.Fpm1_txt);
                holder.fpm2 = (TextView) convertView.findViewById(R.id.Fpm2_txt);
                holder.fpm3 = (TextView) convertView.findViewById(R.id.Fpm3_txt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.fpm1.setBackgroundColor(Color.GREEN);
            holder.fpm2.setBackgroundColor(Color.GREEN);
            holder.fpm3.setBackgroundColor(Color.GREEN);
            holder.emp.setText(focusSaleList.get(position).getEmployee().get(0));
            holder.current.setText(focusSaleList.get(position).getCurrMonth().get(0));
            holder.fpm1.setText(focusSaleList.get(position).getPm1().get(0));
            holder.fpm2.setText(focusSaleList.get(position).getPm2().get(0));
            holder.fpm3.setText(focusSaleList.get(position).getPm3().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView emp, current, fpm1, fpm2, fpm3;

        }
    }


    private class SalesTotalAdapter extends BaseAdapter {
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
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_focus_sales, null);
                holder.emp = (TextView) convertView.findViewById(R.id.emp_txt);
                holder.current = (TextView) convertView.findViewById(R.id.current_txt);
                holder.fpm1 = (TextView) convertView.findViewById(R.id.Fpm1_txt);
                holder.fpm2 = (TextView) convertView.findViewById(R.id.Fpm2_txt);
                holder.fpm3 = (TextView) convertView.findViewById(R.id.Fpm3_txt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.fpm1.setBackgroundColor(Color.GREEN);
            holder.fpm2.setBackgroundColor(Color.GREEN);
            holder.fpm3.setBackgroundColor(Color.GREEN);
            holder.emp.setText(totalSaleList.get(position).getEmployee().get(0));
            holder.current.setText(totalSaleList.get(position).getCurrMonth().get(0));
            holder.fpm1.setText(totalSaleList.get(position).getPm1().get(0));
            holder.fpm2.setText(totalSaleList.get(position).getPm2().get(0));
            holder.fpm3.setText(totalSaleList.get(position).getPm3().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView emp, current, fpm1, fpm2, fpm3;

        }
    }

    private class TeamPssAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return teamPssSaleList.size();
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
                convertView = inflater.inflate(R.layout.row_focus_sales, null);
                holder.emp = (TextView) convertView.findViewById(R.id.emp_txt);
                holder.current = (TextView) convertView.findViewById(R.id.current_txt);
                holder.fpm1 = (TextView) convertView.findViewById(R.id.Fpm1_txt);
                holder.fpm2 = (TextView) convertView.findViewById(R.id.Fpm2_txt);
                holder.fpm3 = (TextView) convertView.findViewById(R.id.Fpm3_txt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.emp.setText(teamPssSaleList.get(position).getMerchanD().get(0));
            holder.current.setText(teamPssSaleList.get(position).getPerfect().get(0));
            holder.fpm1.setText(teamPssSaleList.get(position).getNear20perF().get(0));
            holder.fpm2.setText(teamPssSaleList.get(position).getNot20perF().get(0));
            holder.fpm3.setText(teamPssSaleList.get(position).getTotal20store().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView emp, current, fpm1, fpm2, fpm3;

        }
    }


}
