package com.gsk.sup.gskmt.dailyentry;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.delegates.CoverageBean;
import com.gsk.sup.gskmt.message.AlertMessage;
import com.gsk.sup.gskmt.xmlGetterSetter.FailureGetterSetter;
import com.gsk.sup.gskmt.xmlHandler.FailureXMLHandler;


public class CheckOutStoreActivity extends Activity {
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private String username, visit_date, store_id, store_intime, store_outtime, outTime, process_id;
    private Data data;
    private GSK_MT_SUPDatabase db;
    private SharedPreferences preferences = null;
    private FailureGetterSetter failureGetterSetter = null;
    static int counter = 1;
    public String currLatitude = "0.0";
    public String currLongitude = "0.0";
    ArrayList<CoverageBean> list_coverage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString(CommonString.KEY_USERNAME, "");
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        process_id = preferences.getString(CommonString.KEY_PROCESS_ID, "");
        db = new GSK_MT_SUPDatabase(this);
        db.open();
        store_id = getIntent().getStringExtra(CommonString.KEY_STORE_ID);
        list_coverage = db.getCoverageSpecificData(store_id);
        new BackgroundTask(this).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        db.open();

    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    private class BackgroundTask extends AsyncTask<Void, Data, String> {
        private Context context;

        BackgroundTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Uploading Checkout Data");
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
                data.name = "Checkout Data Uploading";
                publishProgress(data);
                Object result = "";
                if (list_coverage.size() > 0) {
                    for (int i = 0; i < list_coverage.size(); i++) {
                        String onXML = "[DATA][USER_DATA][USER_ID]" + username
                                + "[/USER_ID]" + "[LATITUDE]" + list_coverage.get(i).getLatitude()
                                + "[/LATITUDE][LONGITUDE]" + list_coverage.get(i).getLongitude()
                                + "[/LONGITUDE] [CHECKOUT_DATE]" + visit_date
                                + "[/CHECKOUT_DATE][CHECK_OUTTIME]" + getCurrentTime()
                                + "[/CHECK_OUTTIME][CHECK_INTIME]" + list_coverage.get(i).getInTime()
                                + "[/CHECK_INTIME][CREATED_BY]" + username
                                + "[/CREATED_BY][STORE_ID]" + store_id
                                + "[/STORE_ID]"
                                + "[PROCESS_ID]"
                                + list_coverage.get(i).getProcess_id()
                                + "[/PROCESS_ID]"
                                + "[/USER_DATA][/DATA]";


                        SoapObject request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_Checkout_StatusNew);
                        request.addProperty("onXML", onXML);
                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope.dotNet = true;
                        envelope.setOutputSoapObject(request);
                        data.value = 60;
                        data.name = "Checkout from store";
                        publishProgress(data);
                        HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString.URL);
                        androidHttpTransport.call(CommonString.SOAP_ACTION_Checkout_StatusNew, envelope);
                        result = (Object) envelope.getResponse();
                        if (result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                            db.updateCoverageStoreOutTime(store_id, visit_date, getCurrentTime(), CommonString.KEY_Y, list_coverage.get(i).getProcess_id());
                            db.updateStoreStatusOnCheckout(store_id, visit_date, CommonString.KEY_Y, list_coverage.get(i).getProcess_id());
                            // db.updateCoverageStoreSomeOutTime(store_id, visit_date, getCurrentTime(), CommonString.KEY_C);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString(CommonString.KEY_STOREVISITED, "");
                            editor.putString(CommonString.KEY_STOREVISITED_STATUS, "");
                            editor.commit();
                            data.value = 100;
                            data.name = "Checkout Done";
                            publishProgress(data);
                        } else {
                            if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                                return CommonString.METHOD_Checkout_StatusNew;
                            }
                        }
                    }
                }
                return result.toString();
            } catch (MalformedURLException e) {
                final AlertMessage message = new AlertMessage(CheckOutStoreActivity.this, AlertMessage.MESSAGE_EXCEPTION, "checkout", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();
                    }
                });
            } catch (IOException e) {
                final AlertMessage message = new AlertMessage(CheckOutStoreActivity.this, AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket_checkout_store", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();
                    }
                });
            } catch (Exception e) {
                final AlertMessage message = new AlertMessage(CheckOutStoreActivity.this, AlertMessage.MESSAGE_EXCEPTION, "acra_checkout", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();
                    }
                });
            }

            return "";
        }

        @Override
        protected void onProgressUpdate(Data... values) {
            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (result.equals(CommonString.KEY_SUCCESS)) {
                AlertMessage message = new AlertMessage(CheckOutStoreActivity.this, AlertMessage.MESSAGE_CHECKOUT, "checkout", null);
                message.showMessage();
            } else if (!result.equals("")) {
                AlertMessage message = new AlertMessage(CheckOutStoreActivity.this, AlertMessage.MESSAGE_ERROR + result, "checkoutfail", null);
                message.showMessage();

            }
        }
    }
    class Data {
        int value;
        String name;
    }
    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());
        return cdate;

    }


}
