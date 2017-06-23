package com.gsk.sup.gskmt.dailyentry;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.delegates.CoverageBean;
import com.gsk.sup.gskmt.delegates.ReasonModel;
import com.gsk.sup.gskmt.delegates.StoreBean;

public class NonWorkingActivity extends AppCompatActivity
        implements OnItemSelectedListener, OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    Spinner reason, sub_reason;
    String reasonname, reasonid, image, entry, intime, reasonval, sub_reason_id = "", entry_allow = "", image_allow = "";
    ArrayList<ReasonModel> reasonData = new ArrayList<ReasonModel>();
    ArrayList<ReasonModel> subreasonData = new ArrayList<ReasonModel>();
    ArrayList<StoreBean> storelist = new ArrayList<StoreBean>();
    private ArrayAdapter<CharSequence> reason_adapter;
    private ArrayAdapter<CharSequence> sub_reason_adapter;
    Button save;
    EditText remarks;
    protected String _pathforcheck = "";
    protected String _path;
    GSK_MT_SUPDatabase db;
    AlertDialog alert;
    ImageView img;
    SharedPreferences preferences;
    String process_id, username, app_version, date, store_id, lat = "0.0", longi = "0.0", meetingstatus;
    private String image1 = "";
    String str;
    ArrayList<StoreBean> jcplist = new ArrayList<StoreBean>();
    ArrayList<StoreBean> process_list;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_working);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Nonworking");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        reason = (Spinner) findViewById(R.id.reason_spinner);
        sub_reason = (Spinner) findViewById(R.id.sub_reason);
        save = (Button) findViewById(R.id.save);
        remarks = (EditText) findViewById(R.id.remark);
        img = (ImageView) findViewById(R.id.image);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        intime = getCurrentTime();
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        app_version = preferences.getString(CommonString.KEY_VERSION, null);
        meetingstatus = preferences.getString("EmpMeetingStatus", null);
        date = preferences.getString(CommonString.KEY_DATE, null);
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        str = CommonString.FILE_PATH;
        db = new GSK_MT_SUPDatabase(NonWorkingActivity.this);
        db.open();
        jcplist = db.getStoreAllData(date);
        reasonData = db.getNonWorkingReason();
        storelist = db.getStoreData(date);
        for (int i = 0; i < reasonData.size(); i++) {
            if (reasonData.get(i).getReason().equalsIgnoreCase("pjp deviation")) {
                reasonData.remove(i);
            }
        }
        if (jcplist.size() > 0) {
            try {
                for (int i = 0; i < jcplist.size(); i++) {
                    if (jcplist.get(i).getUPLOAD_STATUS().equalsIgnoreCase(CommonString.KEY_U)) {
                        for (int j = 0; j < reasonData.size(); j++) {
                            if (reasonData.get(j).getReason().equalsIgnoreCase("Leave")) {
                                reasonData.remove(j);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        reason_adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        sub_reason_adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        reason_adapter.add("-Select Reason-");
        sub_reason_adapter.add("Select Sub-Reason");

        for (int i = 0; i < reasonData.size(); i++) {
            reason_adapter.add(reasonData.get(i).getReason());

        }
        reason.setAdapter(reason_adapter);
        reason_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sub_reason_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reason.setOnItemSelectedListener(this);
        sub_reason.setOnItemSelectedListener(this);
        img.setOnClickListener(this);
        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (check_condition()) {
                    if (check_condition1()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(NonWorkingActivity.this);
                        builder.setMessage("Are you sure you want to save").setCancelable(false).setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if ((entry_allow.equals("0"))) {
                                            db.open();
                                            process_list = db.getProcess(date, store_id);
                                            db.deleteAllTables();
                                            if (process_list.size() > 0) {
                                                for (int i = 0; i < process_list.size(); i++) {
                                                    CoverageBean data = new CoverageBean();
                                                    data.setImage(image1);
                                                    data.setLatitude(lat);
                                                    data.setLongitude(longi);
                                                    data.setVisitDate(date);
                                                    data.setInTime(intime);
                                                    data.setOutTime(getCurrentTime());
                                                    data.setReasonid(reasonid);
                                                    data.setRemark(remarks.getText().toString().replaceAll("[&^<>{}'$]", " "));
                                                    data.setUserId(username);
                                                    data.setApp_version(app_version);
                                                    data.setProcess_id(process_list.get(i).getPROCESS_ID());
                                                    data.setImage_allow("");
                                                    data.setSub_reasonId(sub_reason_id);
                                                    data.setStatus(CommonString.STORE_STATUS_LEAVE);
                                                    db.InsertCoverageInLeaveCase(data, date, storelist);
                                                    db.updateStoreStatusOnLeave(store_id, date, CommonString.STORE_STATUS_LEAVE, process_list.get(i).getPROCESS_ID());
                                                    db.updateStoreStatusOnCheckout(store_id, date, CommonString.STORE_STATUS_LEAVE, process_list.get(i).getPROCESS_ID());
                                                    /*db.updateCoverageStoreStatus(store_id, date, CommonString.STORE_STATUS_LEAVE);
                                                    db.updateStoreStatusOnLeaveOrHoliday(storelist, date, CommonString.STORE_STATUS_LEAVE);*/
                                                    Intent in = new Intent(NonWorkingActivity.this, StoreListActivity.class);
                                                    startActivity(in);
                                                    NonWorkingActivity.this.finish();
                                                }
                                            }

                                        } else {
                                            db.open();
                                            process_list = db.getProcess(date, store_id);
                                            if (process_list.size() > 0) {
                                                for (int i = 0; i < process_list.size(); i++) {
                                                    CoverageBean data = new CoverageBean();
                                                    data.setImage(image1);
                                                    data.setStoreId(store_id);
                                                    data.setLatitude(lat);
                                                    data.setLongitude(longi);
                                                    data.setVisitDate(date);
                                                    data.setInTime(intime);
                                                    data.setOutTime(getCurrentTime());
                                                    data.setReason(reasonname);
                                                    data.setReasonid(reasonid);
                                                    data.setUserId(username);
                                                    data.setProcess_id(process_list.get(i).getPROCESS_ID());
                                                    data.setImage_allow("");
                                                    data.setSub_reasonId(sub_reason_id);
                                                    data.setStatus(CommonString.STORE_STATUS_LEAVE);
                                                    db.InsertCoverage(data,process_list.get(i).getPROCESS_ID() , app_version, remarks.getText().toString().replaceAll("[&^<>{}'$]", " "),
                                                            CommonString.STORE_STATUS_LEAVE);
                                                    db.updateStoreStatusOnLeave(store_id, date, CommonString.STORE_STATUS_LEAVE, process_list.get(i).getPROCESS_ID());
                                                    db.updateStoreStatusOnCheckout(store_id, date, CommonString.STORE_STATUS_LEAVE, process_list.get(i).getPROCESS_ID());
                                                    SharedPreferences.Editor editor = preferences.edit();
                                                    editor.putString(CommonString.KEY_STOREVISITED, "none");
                                                    editor.putString(CommonString.KEY_STOREVISITED_STATUS, "");
                                                    editor.putString(CommonString.KEY_STORE_IN_TIME, "");
                                                    editor.commit();
                                                    Intent intent = new Intent(getApplicationContext(), StoreListActivity.class);
                                                    startActivity(intent);
                                                    NonWorkingActivity.this.finish();

                                                }
                                            }


                                        }
                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();

                        alert.show();
                    } else {
                        Snackbar.make(save, "Please select a reason ", Snackbar.LENGTH_LONG).show();
                    }
                } else {

                    Snackbar.make(save, "Please take a photo", Snackbar.LENGTH_LONG).show();

                }

            }
        });

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }


    public boolean check_condition() {
        boolean result = true;
        if (reasonid.equalsIgnoreCase("2") || reasonid.equalsIgnoreCase("3") || reasonid.equalsIgnoreCase("4")) {
            if (image1.equalsIgnoreCase("")) {
                result = false;
            }
        }
        return result;

    }


    public boolean check_condition1() {
        boolean result = true;
        if (remarks.getText().toString().equalsIgnoreCase("")) {
            result = false;
        }
        return result;

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
        Intent in = new Intent(NonWorkingActivity.this, StoreListActivity.class);
        startActivity(in);
        NonWorkingActivity.this.finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                               long arg3) {
        switch (arg0.getId()) {

            case R.id.reason_spinner:

                if (position != 0 && reasonData.size() > 0) {
                    reasonname = reasonData.get(position - 1).getReason();
                    reasonid = reasonData.get(position - 1).getReasonid();
                    entry_allow = reasonData.get(position - 1).getENTRY_ALLOW();
                    image_allow = reasonData.get(position - 1).getIMAGE_ALLOW();
                    if (subreasonData.size() > 0) {
                        sub_reason_adapter.clear();
                        for (int i = 0; i < subreasonData.size(); i++) {
                            sub_reason_adapter.add(subreasonData.get(i).getSub_reason());

                        }
                        sub_reason_adapter.notifyDataSetChanged();
                        sub_reason.setAdapter(sub_reason_adapter);
                        sub_reason.setOnItemSelectedListener(new OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                sub_reason_id = subreasonData.get(position).getSub_reasonId();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {


                            }
                        });

                    } else {
                        sub_reason_adapter.clear();
                        sub_reason_id = "";
                    }
                } else {
                    reasonname = "";
                    reasonid = "";
                    reasonval = "";
                }
                if (image_allow.equals("1")) {
                    img.setVisibility(View.VISIBLE);
                    img.setEnabled(true);
                    image1 = "";
                } else {
                    img.setVisibility(View.INVISIBLE);
                    img.setEnabled(false);
                    image1 = "";
                }
                break;
        }
    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":" + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);
        return intime;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image) {
            _pathforcheck = store_id + "_NonWorking_" + date.replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
            _path = str + _pathforcheck;
            Log.i("MakeMachine", "ButtonClickHandler.onClick()");
            startCameraActivity();
        }
    }

    protected void startCameraActivity() {
        try {
            Log.i("MakeMachine", "startCameraActivity()");
            File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(intent, 0);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;

            case -1:
                if (_pathforcheck != null && !_pathforcheck.equals("")) {
                    if (new File(str + _pathforcheck).exists()) {
                        img.setBackgroundResource(R.drawable.camera_tick_ico);
                        image1 = _pathforcheck;
                    }
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            lat = String.valueOf(mLastLocation.getLatitude());
            longi = String.valueOf(mLastLocation.getLongitude());
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


}
