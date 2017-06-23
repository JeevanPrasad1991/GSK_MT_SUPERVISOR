package com.gsk.sup.gskmt.Upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.MainMenuActivity;
import com.gsk.sup.gskmt.delegates.CoverageBean;
import com.gsk.sup.gskmt.delegates.QuestionnairBean;
import com.gsk.sup.gskmt.delegates.SkuBean;
import com.gsk.sup.gskmt.delegates.StoreBean;
import com.gsk.sup.gskmt.delegates.TOTBean;
import com.gsk.sup.gskmt.fragment.MainFragment;
import com.gsk.sup.gskmt.message.AlertMessage;
import com.gsk.sup.gskmt.xmlGetterSetter.FailureGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.PerformanceNonAchivementGetterSetter;

public class UploadDataActivity extends AppCompatActivity {
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    String app_ver, cate_id;
    private String visit_date, username;
    private SharedPreferences preferences;
    private GSK_MT_SUPDatabase database;
    private int factor, k;
    String datacheck = "";
    String[] words;
    String validity;
    int mid;
    Data data;
    ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
    ArrayList<QuestionnairBean> questionnairData = new ArrayList<QuestionnairBean>();
    PerformanceNonAchivementGetterSetter performanceNonAchivementGetterSetter;
    StoreBean storestatus = new StoreBean();
    String sub_reason = "0", path_, errormsg = "";
    ArrayList<StoreBean> jcplist;
    ArrayList<StoreBean> process_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        cate_id = preferences.getString(CommonString.KEY_CATEGORY_ID, null);
        app_ver = preferences.getString(CommonString.KEY_VERSION, "");
        database = new GSK_MT_SUPDatabase(this);
        database.open();
        new UploadTask(this).execute();
        path_ = CommonString.FILE_PATH;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
        UploadDataActivity.this.finish();
    }

    private class UploadTask extends AsyncTask<Void, Data, String> {
        private Context context;

        UploadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom_upl);
            dialog.setTitle("Uploading Data");
            dialog.setCancelable(false);
            dialog.show();
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                data = new Data();
                data.value = 20;
                data.name = "Uploading";
                publishProgress(data);
                coverageBeanlist = database.getCoverageData_Updated(visit_date);
                if (coverageBeanlist.size() > 0) {
                    for (int i = 0; i < coverageBeanlist.size(); i++) {
                        //storestatus = database.getStoreStatus_new(coverageBeanlist.get(i).getStoreId(), coverageBeanlist.get(i).getProcess_id());
                        if (!coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_U)) {
                            String onXML = "[DATA][USER_DATA][STORE_ID]"
                                    + coverageBeanlist.get(i).getStoreId()
                                    + "[/STORE_ID]"
                                    + "[VISIT_DATE]"
                                    + coverageBeanlist.get(i).getVisitDate()
                                    + "[/VISIT_DATE]"
                                    + "[LATITUDE]"
                                    + coverageBeanlist.get(i).getLatitude()
                                    + "[/LATITUDE]"
                                    + "[STORE_IMAGE]"
                                    + coverageBeanlist.get(i).getImage()
                                    + "[/STORE_IMAGE]"
                                    + "[LONGITUDE]"
                                    + coverageBeanlist.get(i).getLongitude()
                                    + "[/LONGITUDE]"
                                    + "[IN_TIME]"
                                    + coverageBeanlist.get(i).getInTime()
                                    + "[/IN_TIME][OUT_TIME]"
                                    + coverageBeanlist.get(i).getOutTime()
                                    + "[/OUT_TIME][UPLOAD_STATUS]"
                                    + "N"
                                    + "[/UPLOAD_STATUS][CREATED_BY]"
                                    + username
                                    + "[/CREATED_BY][REASON_REMARK]"
                                    + coverageBeanlist.get(i).getReason()
                                    + "[/REASON_REMARK][REASON_ID]"
                                    + coverageBeanlist.get(i).getReasonid()
                                    + "[/REASON_ID][SUB_REASON_ID]"
                                    + sub_reason
                                    + "[/SUB_REASON_ID][APP_VERSION]"
                                    + app_ver
                                    + "[/APP_VERSION] [IMAGE_ALLOW]"
                                    + "0"
                                    + "[/IMAGE_ALLOW]" + "[USER_ID]"
                                    + username
                                    + "[/USER_ID]" + "[PROCESS_ID]"
                                    + coverageBeanlist.get(i).getProcess_id()
                                    + "[/PROCESS_ID]"
                                    + "[/USER_DATA][/DATA]";

                            SoapObject request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE_LOC);
                            request.addProperty("onXML", onXML);
                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);
                            HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString.URL);
                            androidHttpTransport.call(CommonString.SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE, envelope);
                            Object result = (Object) envelope.getResponse();
                            datacheck = result.toString();
                            words = datacheck.split("\\;");
                            validity = (words[0]);
                            if (validity.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                database.updateCoverageStatus(coverageBeanlist.get(i).getStoreId(),
                                        CommonString.KEY_P, coverageBeanlist.get(i).getProcess_id());
                                database.updateStoreStatusOnLeave(coverageBeanlist.get(i).getStoreId(),
                                        coverageBeanlist.get(i).getVisitDate(), CommonString.KEY_P, coverageBeanlist.get(i).getProcess_id());
                            } else {
                                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                                    return CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE;
                                }
                            }
                            mid = Integer.parseInt((words[1]));
                            data = new Data();
                            data.value = 40;
                            data.name = "Coverage data Uploaded";
                            publishProgress(data);

                            // Upload Questionnair Data

                            questionnairData = database.getQuestionnairDataUpload(coverageBeanlist.get(i).getStoreId(), coverageBeanlist.get(i).getProcess_id());
                            String que_xml = "";
                            if (questionnairData.size() > 0) {
                                for (int j = 0; j < questionnairData.size(); j++) {
                                    onXML = "[USER_DATA][MID]"
                                            + mid
                                            + "[/MID][CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY][PROCESS_ID]"
                                            + questionnairData.get(j).getProcess_id()
                                            + "[/PROCESS_ID]"
                                            + "[QUESTION_ID]"
                                            + questionnairData.get(j).getQuestion_id()
                                            + "[/QUESTION_ID][ANSWER_ID]"
                                            + questionnairData.get(j).getAnswer_id()
                                            + "[/ANSWER_ID][QSUB_CATEGORY_ID]"
                                            + questionnairData.get(j).getQSUB_CATEGORY_ID()
                                            + "[/QSUB_CATEGORY_ID]"
                                            + "[/USER_DATA]";

                                    que_xml = que_xml + onXML;
                                }

                                que_xml = "[DATA]" + que_xml + "[/DATA]";
                                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_UPLOAD_STOCK_XML_DATA);
                                request.addProperty("MID", mid);
                                request.addProperty("KEYS", "QUESTIONNAIR_DATA_SUP");
                                request.addProperty("USERNAME", username);
                                request.addProperty("XMLDATA", que_xml);
                                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);
                                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                                androidHttpTransport.call(CommonString.SOAP_ACTION_UPLOAD_ASSET_XMLDATA, envelope);
                                result = (Object) envelope.getResponse();
                                if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                    return CommonString.METHOD_UPLOAD_QUESTION;
                                }
                                data = new Data();
                                data.value = 60;
                                data.name = "QUESTIONNAIR_DATA_SUP data Uploaded";
                                publishProgress(data);
                            }

                            performanceNonAchivementGetterSetter = database.getnonAchievementData(coverageBeanlist.get(i).getStoreId());
                            if (!performanceNonAchivementGetterSetter.getReason_id().equalsIgnoreCase("")) {
                                que_xml = "";
                                onXML = "[USER_DATA][MID]"
                                        + mid
                                        + "[/MID][CREATED_BY]"
                                        + username
                                        + "[/CREATED_BY][STORE_ID]"
                                        + performanceNonAchivementGetterSetter.getStore_id()
                                        + "[/STORE_ID]"
                                        + "[NON_ACHIEVMENT_REASON_ID]"
                                        + performanceNonAchivementGetterSetter.getReason_id()
                                        + "[/NON_ACHIEVMENT_REASON_ID]"
                                        + "[NON_ACHIEVMENT_REASON]"
                                        + performanceNonAchivementGetterSetter.getReason()
                                        + "[/NON_ACHIEVMENT_REASON]"
                                        + "[/USER_DATA]";

                                que_xml = que_xml + onXML;

                                que_xml = "[DATA]" + que_xml + "[/DATA]";
                                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_UPLOAD_STOCK_XML_DATA);
                                request.addProperty("MID", mid);
                                request.addProperty("KEYS", "SUP_NON_ACHIEVMENT");
                                request.addProperty("USERNAME", username);
                                request.addProperty("XMLDATA", que_xml);
                                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);
                                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                                androidHttpTransport.call(CommonString.SOAP_ACTION_UPLOAD_ASSET_XMLDATA, envelope);
                                result = (Object) envelope.getResponse();
                                if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                    return CommonString.METHOD_UPLOAD_QUESTION;
                                }
                                data = new Data();
                                data.value = 70;
                                data.name = "SUP_NON_ACHIEVMENT data Uploaded";
                                publishProgress(data);
                            }

                            if (coverageBeanlist.get(i).getImage() != null && !coverageBeanlist.get(i).getImage().equals("")) {
                                if (new File(CommonString.FILE_PATH + coverageBeanlist.get(i).getImage()).exists()) {
                                    result = UploadPOSMImage(coverageBeanlist.get(i).getImage(), "SupDealerBoardImages");
                                    if (result.toString().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                                        return CommonString.METHOD_Get_DR_POSM_IMAGES + "," + errormsg;
                                    }
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            message.setText("Coverage Image Uploaded");
                                        }
                                    });
                                }
                                data = new Data();
                                data.value = 80;
                                data.name = "Coverage Image Uploaded";
                                publishProgress(data);
                            }

                            database.open();
                            database.updateCoverageStatus(coverageBeanlist.get(i).getStoreId(), CommonString.KEY_D, coverageBeanlist.get(i).getProcess_id());
                            database.updateStoreStatusOnLeave(coverageBeanlist.get(i).getStoreId(),
                                    coverageBeanlist.get(i).getVisitDate(), CommonString.KEY_D, coverageBeanlist.get(i).getProcess_id());
                            data = new Data();
                            data.value = 90;
                            data.name = "Coverage status Uploading";
                            publishProgress(data);
                            String statusxml = "";
                            statusxml = "[DATA][USER_DATA][STORE_ID]"
                                    + coverageBeanlist.get(i).getStoreId()
                                    + "[/STORE_ID][CREATED_BY]" + username
                                    + "[/CREATED_BY]"
                                    + "[PROCESS_ID]"
                                    + coverageBeanlist.get(i).getProcess_id()
                                    + "[/PROCESS_ID]"
                                    + "[VISIT_DATE]"
                                    + coverageBeanlist.get(i).getVisitDate()
                                    + "[/VISIT_DATE]"
                                    + "[STATUS]"
                                    + CommonString.KEY_U
                                    + "[/STATUS][/USER_DATA][/DATA]";

                            request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_SET_COVERAGE_STATUS);
                            request.addProperty("onXML", statusxml);
                            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);
                            androidHttpTransport = new HttpTransportSE(CommonString.URL);
                            androidHttpTransport.call(CommonString.SOAP_ACTION_SET_COVERAGE_STATUS, envelope);
                            result = (Object) envelope.getResponse();
                            if (result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                database.updateStoreStatusOnLeave(coverageBeanlist.get(i).getStoreId(), coverageBeanlist.get(i).getVisitDate(), CommonString.KEY_U, coverageBeanlist.get(i).getProcess_id());
                                database.updateCoverageStatus(coverageBeanlist.get(i).getStoreId(), CommonString.KEY_U, coverageBeanlist.get(i).getProcess_id());
                                data = new Data();
                                data.value = 100;
                                data.name = "Coverage status Uploaded";
                                publishProgress(data);
                            } else {
                                if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                    return CommonString.SOAP_ACTION_SET_COVERAGE_STATUS;
                                }

                            }

                        }

                    }
                }
                return CommonString.KEY_SUCCESS;
            } catch (IOException e) {
                final AlertMessage message = new AlertMessage(
                        UploadDataActivity.this,
                        AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket_upload", e);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();

                    }
                });
            } catch (Exception e) {
                final AlertMessage message = new AlertMessage(
                        UploadDataActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "uploaded", e);

                e.getMessage();
                e.printStackTrace();
                e.getCause();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (result.equals(CommonString.KEY_SUCCESS)) {
                database.deleteAllTables();
                AlertMessage message = new AlertMessage(UploadDataActivity.this, AlertMessage.MESSAGE_UPLOAD_DATA, "success", null);
                message.showMessage();
            } else if (!result.equals("")) {
                AlertMessage message = new AlertMessage(UploadDataActivity.this, AlertMessage.MESSAGE_ERROR + result, "success", null);
                message.showMessage();
            }

        }

        @Override
        protected void onProgressUpdate(Data... values) {
            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);
        }


    }

    public class Data {
        int value;
        String name;
    }

    public String UploadPOSMImage(String path, String folder) throws Exception {
        errormsg = "";
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(CommonString.FILE_PATH + path, o);
        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;
        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeFile(CommonString.FILE_PATH + path, o2);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] ba = bao.toByteArray();
        String ba1 = Base64.encodeBytes(ba);
        SoapObject request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_Get_DR_POSM_IMAGES);
        request.addProperty("img", ba1);
        request.addProperty("name", path);
        request.addProperty("FolderName", folder);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString.URL);
        androidHttpTransport.call(CommonString.SOAP_ACTION_Get_DR_POSM_IMAGES, envelope);
        Object result = (Object) envelope.getResponse();
        if (result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
            new File(CommonString.FILE_PATH + path).delete();
        } else {
            return CommonString.KEY_FAILURE;
        }
        return result.toString();
    }

}
