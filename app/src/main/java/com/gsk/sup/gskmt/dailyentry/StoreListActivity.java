package com.gsk.sup.gskmt.dailyentry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.CompleteDownload.CompleteDownloadActivity;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.MainMenuActivity;
import com.gsk.sup.gskmt.Upload.PreviousUploadDataActivity;
import com.gsk.sup.gskmt.delegates.CoverageBean;
import com.gsk.sup.gskmt.delegates.StoreBean;

public class StoreListActivity extends AppCompatActivity {
    ArrayList<StoreBean> jcplist = new ArrayList<StoreBean>();
    FloatingActionButton fab;
    LinearLayout nodata_linear;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;
    ArrayList<CoverageBean> coverageBeenListSomeData = new ArrayList<CoverageBean>();
    ArrayList<CoverageBean> coverageBeenList = new ArrayList<CoverageBean>();
    ArrayList<StoreBean> process_list;
    GSK_MT_SUPDatabase database;
    String store_intime = "", date, store_cd;
    private Dialog dialog;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storename);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Store List");
        lv = (ListView) findViewById(R.id.list);
        nodata_linear = (LinearLayout) findViewById(R.id.no_data_lay);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        date = preferences.getString(CommonString.KEY_DATE, null);
        database = new GSK_MT_SUPDatabase(StoreListActivity.this);
        database.open();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Download data
                if (checkNetIsAvailable()) {
                    if (database.isCoverageDataFilled(date)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(StoreListActivity.this);
                        builder.setTitle("Parinaam");
                        builder.setMessage("Please Upload Previous Data First")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent startUpload = new Intent(StoreListActivity.this, PreviousUploadDataActivity.class);
                                        startActivity(startUpload);
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        Intent startDownload = new Intent(StoreListActivity.this, CompleteDownloadActivity.class);
                        startActivity(startDownload);
                        finish();
                    }
                } else {
                    Snackbar.make(lv, "No Network Available", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
            }

        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(this, MainMenuActivity.class));
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        String checkouS = "";
        String uploadS = "";
        boolean deleteflag = false;
        boolean deletecheckoutflag = false;
        ArrayList<String> removeStoreList = new ArrayList<>();
        database.open();
        jcplist = database.getStoreAllData(date);
        if (jcplist.size() > 0) {
            for (int i = 0; i < jcplist.size(); i++) {
                process_list = database.getProcess(date, jcplist.get(i).getSTORE_ID());
                checkouS = "";
                uploadS = "";
                deleteflag = false;
                if (process_list.size() > 1) {
                    boolean flag_check_needed = false;
                    if (removeStoreList.size() > 0) {
                        if (!removeStoreList.contains(jcplist.get(i).getSTORE_ID())) {
                            flag_check_needed = true;
                        }
                    } else {
                        flag_check_needed = true;
                    }

                    if (flag_check_needed) {
                        for (int i1 = 0; i1 < process_list.size(); i1++) {
                            if (checkouS.equals("")) {
                                checkouS = process_list.get(i1).getCHECKOUT_STATUS();
                            }
                            if (i1 != 0 && !checkouS.equalsIgnoreCase(process_list.get(i1).getCHECKOUT_STATUS())) {
                                deleteflag = true;
                                deletecheckoutflag = true;
                                break;
                            }


                            if (uploadS.equalsIgnoreCase("")) {
                                uploadS = process_list.get(i1).getUPLOAD_STATUS();
                            }
                            if (i1 != 0 && !uploadS.equalsIgnoreCase(process_list.get(i1).getUPLOAD_STATUS())) {
                                deleteflag = true;
                                break;
                            }

                        }
                    }
                    //adding store id from removing duplicaseeeee
                    if (deleteflag) {
                        String store_id = jcplist.get(i).getSTORE_ID();
                        if (!removeStoreList.contains(store_id)) {
                            removeStoreList.add(store_id);
                        }

                    }

                }

            }
            //change status.....
            if (removeStoreList.size() > 0) {
                for (int k = 0; k < removeStoreList.size(); k++) {
                    boolean flag = true;
                    for (int l = 0; l < jcplist.size(); l++) {
                        if (jcplist.get(l).getSTORE_ID().equals(removeStoreList.get(k))) {
                            if (flag) {
                                flag = false;
                                if (deletecheckoutflag) {
                                    jcplist.get(l).setCHECKOUT_STATUS("N");
                                } else {
                                    jcplist.get(l).setUPLOAD_STATUS("N");
                                }
                            } else {
                                jcplist.remove(l);
                                --l;
                            }
                        }
                    }

                }

            }

        }
        coverageBeenListSomeData = database.getCoverageSomeData(date);
        coverageBeenList = database.getCoverageData_Updated(date);
        if (jcplist.size() > 0) {
            lv.setAdapter(new MyAdaptor());
            lv.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    store_cd = jcplist.get(position).getSTORE_ID();
                    final String upload_status = jcplist.get(position).getUPLOAD_STATUS();
                    final String checkoutstatus = jcplist.get(position).getCHECKOUT_STATUS();
                    editor = preferences.edit();
                    editor.commit();
                    database.open();
                    ArrayList<CoverageBean> coverage_Some_data = database.getCoverageSomeData(date);
                    ArrayList<CoverageBean> coverageData = database.getCoverageData_Updated(date);
                    if (upload_status.equals(CommonString.KEY_U)) {
                        Snackbar.make(lv, "All Data Uploaded", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    } else if (upload_status.equals(CommonString.KEY_D)) {
                        Snackbar.make(lv, "Data Uploaded", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    } else if (upload_status.equals(CommonString.KEY_P)) {
                        Snackbar.make(lv, "Persing error please try again", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    } else if (((checkoutstatus.equals(CommonString.KEY_Y)))) {
                        Snackbar.make(lv, "Store already checked out", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    } else {
                        boolean enteryflag = true;
                        if (coverageData.size() > 0) {
                            for (int i2 = 0; i2 < coverageData.size(); i2++) {
                                if (coverageData.get(i2).getStoreId().equalsIgnoreCase(store_cd)) {
                                    if (coverageData.get(i2).getStatus().equals(CommonString.STORE_STATUS_LEAVE)) {
                                        Snackbar.make(lv, "Store Already Closed", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                        enteryflag = false;
                                        break;
                                    }
                                }
                            }
                            if (enteryflag) {
                                for (int i = 0; i < coverageData.size(); i++) {
                                    if (coverageData.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_VALID) ||
                                            coverageData.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_INVALID)) {
                                        if (!coverageData.get(i).getStoreId().equalsIgnoreCase(store_cd)) {
                                            Snackbar.make(lv, "Please checkout from current store", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                            enteryflag = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (coverage_Some_data.size() > 0) {
                                for (int i2 = 0; i2 < coverage_Some_data.size(); i2++) {
                                    if (coverage_Some_data.get(i2).getStoreId().equalsIgnoreCase(store_cd)) {
                                        if (coverage_Some_data.get(i2).getStatus().equals(CommonString.STORE_STATUS_LEAVE)) {
                                            Snackbar.make(lv, "Store Already Closed", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                            enteryflag = false;
                                            break;
                                        }
                                    }

                                }

                                if (enteryflag) {
                                    for (int i = 0; i < coverage_Some_data.size(); i++) {
                                        if (coverage_Some_data.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_VALID) ||
                                                coverage_Some_data.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_INVALID)) {
                                            if (!coverage_Some_data.get(i).getStoreId().equalsIgnoreCase(store_cd)) {
                                                Snackbar.make(lv, "Please checkout from current store", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                                enteryflag = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (enteryflag) {
                            showMyDialog(store_cd, "Yes",
                                    jcplist.get(position).getVISIT_DATE(), jcplist.get(position).getCHECKOUT_STATUS(),
                                    "", jcplist.get(position).getSTORE()
                                    , jcplist.get(position).geotag_status, jcplist.get(position).getKey_id(),
                                    jcplist.get(position).getREGION_ID(), jcplist.get(position).getStoreType_id());
                        }
                    }
                }

            });
            lv.setVisibility(View.VISIBLE);
            nodata_linear.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);

        }
    }


    public boolean checkNetIsAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(StoreListActivity.this, MainMenuActivity.class);
        startActivity(in);
        finish();
    }

    private class ViewHolder {
        TextView storename, city, keyaccount;
        ImageView img, imgtag, checkinclose;
        Button checkout;

    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":" + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);
        return intime;
    }

    public boolean CheckNetAvailability() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            connected = true;
        }
        return connected;
    }

    private class MyAdaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return jcplist.size();
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
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.storeviewlist, null);
                holder.storename = (TextView) convertView.findViewById(R.id.tvstorename);
                holder.city = (TextView) convertView.findViewById(R.id.tvcity);
                holder.keyaccount = (TextView) convertView.findViewById(R.id.tvkeyaccount);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.checkout = (Button) convertView.findViewById(R.id.chkout);
                holder.checkinclose = (ImageView) convertView.findViewById(R.id.closechkin);
                holder.imgtag = (ImageView) convertView.findViewById(R.id.imgtag);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.checkout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (CheckNetAvailability()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                StoreListActivity.this);
                        builder.setMessage("Are you sure you want to checkout")
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) {
                                                Intent i = new Intent(StoreListActivity.this, CheckOutStoreActivity.class);
                                                i.putExtra(CommonString.KEY_STORE_ID, jcplist.get(position).getSTORE_ID());
                                                startActivity(i);
                                            }
                                        })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        Snackbar.make(lv, "Check internet connection", Snackbar.LENGTH_LONG).show();
                    }
                }
            });
            String storecd = jcplist.get(position).getSTORE_ID();
            database.open();
            ArrayList<CoverageBean> coverage_Some_data = database.getCoverageSomeData_(storecd,date);
            ArrayList<CoverageBean> coveragebeenData = database.getCoverageSpecificData(storecd);
            if (jcplist.get(position).getUPLOAD_STATUS().equals(CommonString.KEY_U)) {
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setBackgroundResource(R.drawable.tick_u);
                holder.checkout.setVisibility(View.INVISIBLE);
                holder.checkinclose.setVisibility(View.INVISIBLE);
            } else if ((jcplist.get(position).getUPLOAD_STATUS().equals(CommonString.KEY_P))) {
                holder.img.setVisibility(View.INVISIBLE);
                holder.checkinclose.setBackgroundResource(R.drawable.tick_p);
                holder.checkinclose.setVisibility(View.VISIBLE);
                holder.checkout.setVisibility(View.INVISIBLE);
            } else if ((jcplist.get(position).getUPLOAD_STATUS().equals(CommonString.KEY_D))) {
                holder.img.setVisibility(View.INVISIBLE);
                holder.checkinclose.setBackgroundResource(R.drawable.tick_d);
                holder.checkinclose.setVisibility(View.VISIBLE);
                holder.checkout.setVisibility(View.INVISIBLE);
            } else if ((jcplist.get(position).getCHECKOUT_STATUS().equals(CommonString.KEY_Y))) {
                holder.img.setVisibility(View.INVISIBLE);
                holder.checkinclose.setBackgroundResource(R.drawable.tick_c);
                holder.checkinclose.setVisibility(View.VISIBLE);
                holder.checkout.setVisibility(View.INVISIBLE);
            } else if (coveragebeenData.size() > 0) {
                for (int i = 0; i < coveragebeenData.size(); i++) {
                    if (coveragebeenData.get(i).getStatus().equals(CommonString.STORE_STATUS_LEAVE)) {
                        holder.img.setBackgroundResource(R.drawable.leave_tick);
                        holder.img.setVisibility(View.VISIBLE);
                        holder.checkout.setVisibility(View.INVISIBLE);
                    } else if (coveragebeenData.get(i).getStatus().equals(CommonString.KEY_VALID)) {
                        holder.checkout.setBackgroundResource(R.drawable.checkout);
                        holder.checkout.setVisibility(View.VISIBLE);
                        holder.checkout.setEnabled(true);
                        holder.checkinclose.setVisibility(View.INVISIBLE);
                        holder.img.setVisibility(View.INVISIBLE);
                    } else if (coveragebeenData.get(i).getStatus().equals(CommonString.KEY_INVALID)) {
                        holder.img.setVisibility(View.VISIBLE);
                        holder.img.setBackgroundResource(R.drawable.store);
                        holder.checkout.setEnabled(false);
                        holder.checkout.setVisibility(View.INVISIBLE);
                        holder.checkinclose.setBackgroundResource(R.drawable.checkin_ico);
                        holder.checkinclose.setVisibility(View.VISIBLE);
                    }

                }
            } else {
                holder.checkout.setEnabled(false);
                holder.checkout.setVisibility(View.INVISIBLE);
                holder.img.setVisibility(View.VISIBLE);
                holder.img.setBackgroundResource(R.drawable.store);
                holder.checkinclose.setEnabled(false);
                holder.checkinclose.setVisibility(View.INVISIBLE);
            }
            if (coverage_Some_data.size() > 0) {
                if (coverage_Some_data.get(0).getStatus().equals(CommonString.KEY_INVALID)) {
                    holder.img.setVisibility(View.VISIBLE);
                    holder.img.setBackgroundResource(R.drawable.store);
                    holder.checkout.setEnabled(false);
                    holder.checkout.setVisibility(View.INVISIBLE);
                    holder.checkinclose.setBackgroundResource(R.drawable.checkin_ico);
                    holder.checkinclose.setVisibility(View.VISIBLE);
                }
            }

            holder.storename.setText(jcplist.get(position).getSTORE());
            holder.city.setText(jcplist.get(position).getCITY());
            holder.keyaccount.setText(jcplist.get(position).getKey_account());
            return convertView;
        }
    }

    void showMyDialog(final String storeCd, final String status,
                      final String visitDate, final String checkout_status,
                      final String process_id, final String store_name, final String geoTagStatus,
                      final String key_id, final String region_id, final String store_type_cd) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogbox);
        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radiogrpvisit);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.yes) {
                    editor = preferences.edit();
                    editor.putString(CommonString.KEY_STOREVISITED_STATUS, "Yes");
                    editor.putString(CommonString.KEY_LATITUDE, "");
                    editor.putString(CommonString.KEY_LONGITUDE, "");
                    editor.putString(CommonString.KEY_STORE_CD, storeCd);
                    editor.putString(CommonString.KEY_STORE_NAME, store_name);
                    editor.putString(CommonString.KEY_ID, key_id);
                    editor.putString(CommonString.KEY_REASON_ID, region_id);
                    editor.putString(CommonString.KEY_STORE_TYPEID, store_type_cd);
                    editor.putString(CommonString.KEY_PROCESS_ID, process_id);
                    if (!visitDate.equals("")) {
                        editor.putString(CommonString.KEY_VISIT_DATE, visitDate);
                    }
                    if (status.equals("Yes")) {
                        editor.putString(CommonString.KEY_STOREVISITED_STATUS, "Yes");
                    }
                    database.updateStoreStatusOnCheckout_some(storeCd, date, CommonString.KEY_INVALID);
                    editor.commit();
                    if (store_intime.equalsIgnoreCase("")) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(CommonString.KEY_STORE_IN_TIME, getCurrentTime());
                        editor.putString(CommonString.KEY_STOREVISITED_STATUS, "Yes");
                        editor.commit();
                    }
                    dialog.cancel();
                    boolean flag = true;
                    if (coverageBeenList.size() > 0) {
                        for (int i = 0; i < coverageBeenList.size(); i++) {
                            if (store_cd.equals(coverageBeenList.get(i).getStoreId())) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (coverageBeenListSomeData.size() > 0) {
                        for (int i = 0; i < coverageBeenListSomeData.size(); i++) {
                            if (store_cd.equals(coverageBeenListSomeData.get(i).getStoreId())) {
                                flag = false;
                                break;
                            }
                        }
                    }


                    if (flag == true) {
                        Intent in = new Intent(StoreListActivity.this, StoreInformationActivity.class);
                        startActivity(in);
                        finish();
                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    } else {
                        Intent in = new Intent(StoreListActivity.this, StoreWisePerformanceActivity.class);
                        // Intent in = new Intent(StoreListActivity.this, ProcessEntry.class);
                        startActivity(in);
                        finish();

                    }

                } else if (checkedId == R.id.no) {
                    dialog.cancel();
                    if (checkout_status.equals(CommonString.KEY_INVALID) || checkout_status.equals(CommonString.KEY_VALID)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(StoreListActivity.this);
                        builder.setMessage(CommonString.DATA_DELETE_ALERT_MESSAGE).setCancelable(false).setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        UpdateData(storeCd, process_id);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString(CommonString.KEY_STORE_CD, storeCd);
                                        editor.putString(CommonString.KEY_STORE_IN_TIME, getCurrentTime());
                                        editor.putString(CommonString.KEY_PROCESS_ID, process_id);
                                        editor.commit();
                                        Intent in = new Intent(StoreListActivity.this, NonWorkingActivity.class);
                                        startActivity(in);
                                        finish();

                                    }
                                })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        UpdateData(storeCd, process_id);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(CommonString.KEY_STORE_CD, storeCd);
                        editor.putString(CommonString.KEY_STORE_IN_TIME, getCurrentTime());
                        editor.putString(CommonString.KEY_PROCESS_ID, process_id);
                        editor.commit();
                        Intent in = new Intent(StoreListActivity.this, NonWorkingActivity.class);
                        startActivity(in);
                        finish();
                    }

                }
            }

        });


        dialog.show();
    }

    public void UpdateData(String storeCd, String process_id) {
        database.open();
        database.deleteAllTables(storeCd, process_id);
        database.updateStoreStatusOnCheckout(storeCd, jcplist.get(0).getVISIT_DATE(), "N", process_id);
    }
}
