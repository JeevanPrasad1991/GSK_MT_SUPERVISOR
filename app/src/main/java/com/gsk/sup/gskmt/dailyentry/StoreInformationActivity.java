package com.gsk.sup.gskmt.dailyentry;

import java.io.File;

import java.util.Calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.delegates.CoverageBean;

public class StoreInformationActivity extends AppCompatActivity implements OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    ImageView img_cam, img_clicked;
    private static String str;
    protected String _pathforcheck = "";
    public String image1 = "";
    Button btn_save;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;
    String visit_date, imgDate, store_id, _path, img1 = "", picStatus = "", process_id, reason_id = "0", remark = "", storename, intime, username, app_version, lat = "0.0", longi = "0.0", store_type_cd, key_id;
    GSK_MT_SUPDatabase db;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Store Image");
        img_cam = (ImageView) findViewById(R.id.img_selfie);
        img_clicked = (ImageView) findViewById(R.id.img_cam_selfie);
        btn_save = (Button) findViewById(R.id.btn_save_selfie);
        str = CommonString.FILE_PATH;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        process_id = preferences.getString(CommonString.KEY_PROCESS_ID, null);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        app_version = preferences.getString(CommonString.KEY_VERSION, null);
        storename = preferences.getString(CommonString.KEY_STORE_NAME, null);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        store_type_cd = preferences.getString(CommonString.KEY_STORE_TYPEID, null);
        key_id = preferences.getString(CommonString.KEY_ID, null);
        imgDate = visit_date.replace("/", "-");
        img_cam.setOnClickListener(this);
        img_clicked.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        db = new GSK_MT_SUPDatabase(StoreInformationActivity.this);
        db.open();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_cam_selfie:
                _pathforcheck = store_id + "_storeImage_" + visit_date.replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
                _path = str + _pathforcheck;
                startCameraActivity();
                break;
            case R.id.btn_save_selfie:
                if (!img1.equalsIgnoreCase("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(StoreInformationActivity.this);
                    builder.setMessage("Are you sure you want to save").setCancelable(false).setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    CoverageBean data = new CoverageBean();
                                    data.setImage(img1);
                                    data.setStoreId(store_id);
                                    data.setLatitude(lat);
                                    data.setLongitude(longi);
                                    data.setVisitDate(visit_date);
                                    data.setInTime(getCurrentTime());
                                    data.setOutTime("");
                                    data.setReasonid(reason_id);
                                    data.setRemark(remark);
                                    data.setUserId(username);
                                   /* data.setApp_version(app_version);
                                    data.setProcess_id(process_id);*/
                                    data.setImage_allow(picStatus);
                                    data.setStatus(CommonString.KEY_INVALID);
                                    // db.InsertCoverage(data);
                                    db.InsertCoveragesSomeData(data);
                                    Intent in = new Intent(StoreInformationActivity.this, StoreWisePerformanceActivity.class);
                                    //  Intent in = new Intent(StoreInformationActivity.this, ProcessEntry.class);
                                    startActivity(in);
                                    finish();
                                    editor = preferences.edit();
                                    editor.putString(CommonString.KEY_IN_TIME, intime);
                                    editor.commit();
                                }
                            }).setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    Snackbar.make(btn_save, "Please click image", Snackbar.LENGTH_LONG).show();
                }
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), StoreListActivity.class));
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":" + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);
        return intime;
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
                    if (new File((str + _pathforcheck).trim()).exists()) {
                        Bitmap bmp = BitmapFactory.decodeFile(str + _pathforcheck);
                        // Set the decoded bitmap into ImageView
                        img_cam.setImageBitmap(bmp);
                        img_clicked.setVisibility(View.GONE);
                        img_cam.setVisibility(View.VISIBLE);
                        img1 = _pathforcheck;
                        _pathforcheck = "";
                        break;
                    }
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(StoreInformationActivity.this, StoreListActivity.class);
        startActivity(in);
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
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
