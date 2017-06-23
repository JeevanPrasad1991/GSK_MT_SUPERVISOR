package com.gsk.sup.gskmt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.CompleteDownload.CompleteDownloadActivity;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.Upload.PreviousUploadDataActivity;
import com.gsk.sup.gskmt.Upload.UploadDataActivity;
import com.gsk.sup.gskmt.dailyentry.StoreListActivity;
import com.gsk.sup.gskmt.delegates.CoverageBean;
import com.gsk.sup.gskmt.delegates.StoreBean;
import com.gsk.sup.gskmt.fragment.MainFragment;
import com.gsk.sup.gskmt.message.AlertMessage;
import com.gsk.sup.gskmt.xmlGetterSetter.JCPGetterSetter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String date, user_name, right_name;
    SharedPreferences preferences;
    private int versionCode;
    AlertDialog alert;
    GSK_MT_SUPDatabase db;
    ArrayList<StoreBean> storelist = new ArrayList<StoreBean>();
    Toolbar toolbar;
    ArrayList<StoreBean> jcplist = new ArrayList<StoreBean>();
    ArrayList<CoverageBean> coverageBeenListSomeData = new ArrayList<CoverageBean>();
    TextView username_txt, user_type;
    NavigationView navigationView;
    ArrayList<CoverageBean> cdata = new ArrayList<>();
    ArrayList<StoreBean> process_list;
    StoreBean storestatus = new StoreBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        user_name = preferences.getString(CommonString.KEY_USERNAME, null);
        right_name = preferences.getString(CommonString.KEY_RIGHT_NAME, null);
        date = preferences.getString(CommonString.KEY_DATE, null);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header_main2, navigationView, false);
        navigationView.addHeaderView(headerView);
        username_txt = (TextView) headerView.findViewById(R.id.username_txt);
        user_type = (TextView) headerView.findViewById(R.id.user_type);
        username_txt.setText(user_name);
        user_type.setText(right_name);
        navigationView.setNavigationItemSelectedListener(this);
        db = new GSK_MT_SUPDatabase(MainMenuActivity.this);
        db.open();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_daily_entry) {
            Intent in = new Intent(MainMenuActivity.this, StoreListActivity.class);
            startActivity(in);
            // Handle the camera action
        } else if (id == R.id.nav_downld) {
            if (CheckNetAvailability()) {
                if (db.isCoverageDataFilled(date)) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                    builder.setTitle("Parinaam");
                    builder.setMessage("Please Upload Previous Data First")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent startUpload = new Intent(MainMenuActivity.this, PreviousUploadDataActivity.class);
                                    startActivity(startUpload);
                                    finish();
                                }
                            });
                    android.app.AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    Intent startDownload = new Intent(getApplicationContext(), CompleteDownloadActivity.class);
                    startActivity(startDownload);
                    finish();
                }
            } else {
                Snackbar.make(toolbar, "No Network Available", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        } else if (id == R.id.navupload) {
            if (CheckNetAvailability()) {
                db.open();
                boolean flag = true;
                jcplist = db.getStoreAllData(date);
                coverageBeenListSomeData = db.getCoverageSomeData(date);
                if (jcplist.size() == 0) {
                    Snackbar.make(toolbar, "Please Download Data First", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                if (coverageBeenListSomeData.size() > 0) {
                    Snackbar.make(toolbar, "First checkout of store", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                for (int i1 = 0; i1 < jcplist.size(); i1++) {
                    process_list = db.getProcess(date, jcplist.get(i1).getSTORE_ID());
                    if (process_list.size() > 0) {
                        for (int i2 = 0; i2 < process_list.size(); i2++) {
                            cdata = db.getCoverageData(process_list.get(i2).getVISIT_DATE(), process_list.get(i2).getSTORE_ID(), process_list.get(i2).getPROCESS_ID());
                            if (cdata.size() > 0) {
                                for (int i = 0; i < cdata.size(); i++) {
                                    if (cdata.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_VALID) || cdata.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_INVALID)) {
                                        Snackbar.make(toolbar, "First checkout of store", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag) {
                                    if ((validate_data())) {
                                        Intent i = new Intent(getBaseContext(), UploadDataActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }

                            } else {
                                Snackbar.make(toolbar, "No data found!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            }
                        }
                    }
                }
            } else {
                Snackbar.make(toolbar, "No Network Available", Snackbar.LENGTH_LONG).show();
            }

        } else if (id == R.id.nav_export) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(
                    MainMenuActivity.this);
            builder1.setMessage(
                    "Are you sure you want to take the backup of your data")
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {

                                    try {
                                        File file = new File(Environment.getExternalStorageDirectory(), "GskMTSup_backup");
                                        if (!file.isDirectory()) {
                                            file.mkdir();
                                        }
                                        File sd = Environment.getExternalStorageDirectory();
                                        File data = Environment.getDataDirectory();
                                        if (sd.canWrite()) {
                                            String currentDBPath = "//data//com.example.gsk_mt_sup//databases//" + GSK_MT_SUPDatabase.DATABASE_NAME;
                                            String backupDBPath = "GskGTSup_backup" + date.replace('/', '-');
                                            File currentDB = new File(data, currentDBPath);
                                            File backupDB = new File("/mnt/sdcard/GskMTSUP_Backup/", backupDBPath);
                                            Snackbar.make(toolbar, "Database Exported Successfully", Snackbar.LENGTH_SHORT).show();
                                            if (currentDB.exists()) {
                                                FileChannel src = new FileInputStream(currentDB).getChannel();
                                                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                                                dst.transferFrom(src, 0, src.size());
                                                src.close();
                                                dst.close();
                                            }
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.toString());
                                    }

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert1 = builder1.create();
            alert1.show();

        } else if (id == R.id.nav_performance) {
            jcplist = db.getStoreAllData(date);
            if (jcplist.size() > 0) {
                startActivity(new Intent(getApplicationContext(), PerformanceTabActivity.class));
                finish();
            } else {
                Snackbar.make(toolbar, "Please download data first", Snackbar.LENGTH_LONG).show();
            }


        } else if (id == R.id.nav_exit) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean validate_data() {
        boolean result = false;
        db.open();
        ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();
        StoreBean storestatus = new StoreBean();
        jcplist = db.getStoreAllData(date);
        for (int i1 = 0; i1 < jcplist.size(); i1++) {
            process_list = db.getProcess(jcplist.get(i1).getVISIT_DATE(), jcplist.get(i1).getSTORE_ID());
            if (process_list.size() > 0) {
                for (int i2 = 0; i2 < process_list.size(); i2++) {
                    cdata = db.getCoverageData(process_list.get(i2).getVISIT_DATE(), process_list.get(i2).getSTORE_ID(), process_list.get(i2).getPROCESS_ID());
                    if (cdata.size() > 0) {
                        for (int i = 0; i < cdata.size(); i++) {
                            storestatus = db.getStoreStatus_new(cdata.get(i).getStoreId(), cdata.get(i).getProcess_id());
                            if (storestatus.getUPLOAD_STATUS() != null) {
                                if (!storestatus.getUPLOAD_STATUS().equalsIgnoreCase(CommonString.KEY_U)) {
                                    if ((storestatus.getCHECKOUT_STATUS().equalsIgnoreCase(CommonString.KEY_C) || storestatus.getUPLOAD_STATUS().equalsIgnoreCase(CommonString.KEY_P) ||
                                            cdata.get(i).getStatus().equalsIgnoreCase(CommonString.STORE_STATUS_LEAVE)) ||
                                            storestatus.getUPLOAD_STATUS().equalsIgnoreCase(CommonString.KEY_D) || storestatus.getCHECKOUT_STATUS().equalsIgnoreCase(CommonString.KEY_Y)) {
                                        result = true;
                                        break;

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        return result;
    }

    public boolean CheckNetAvailability() {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            // we are connected to a network
            connected = true;
        }
        return connected;
    }


    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment cartfrag = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, cartfrag).commit();
    }
}
