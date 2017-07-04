package com.gsk.sup.gskmt.CompleteDownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gsk.sup.R;
import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.Database.GSK_MT_SUPDatabase;
import com.gsk.sup.gskmt.delegates.TableBean;
import com.gsk.sup.gskmt.fragment.MainFragment;
import com.gsk.sup.gskmt.message.AlertMessage;
import com.gsk.sup.gskmt.xmlGetterSetter.AdditionalGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.DisplayGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.FailureGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.FocusSaleStoreWiseGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.JCPGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.NonWorkingGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.PssStorewiseDetailGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.PssStorewiseGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.QuestionGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.QuestionnairGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.RemarkGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.SKUGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.StockMappingGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.SupFocusSaleGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.SupincentiveGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.TDSGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.TeamPassGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.TotalSaleGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.TotalSaleStorewiseGetterSetter;
import com.gsk.sup.gskmt.xmlHandler.XMLHandlers;

public class CompleteDownloadActivity extends AppCompatActivity {
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private Data data;
    int eventType;
    String user_name;
    SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;
    JCPGetterSetter jcpData;
    QuestionnairGetterSetter questionnairData;
    NonWorkingGetterSetter nonworkingData;
    GSK_MT_SUPDatabase db;
    StockMappingGetterSetter stockData;
    TDSGetterSetter totData;
    DisplayGetterSetter displayData;
    SKUGetterSetter brandData;
    AdditionalGetterSetter additionalData;
    QuestionGetterSetter questionData, questionMappingData;
    SupincentiveGetterSetter supincentiveGetterSetter;
    SupFocusSaleGetterSetter supFocusSaleGetterSetter;
    TotalSaleGetterSetter totalSaleGetterSetter;
    TeamPassGetterSetter teamPassGetterSetter;
    FocusSaleStoreWiseGetterSetter focusSaleStoreWiseGetterSetter;
    TotalSaleStorewiseGetterSetter totalSaleStorewiseGetterSetter;
    PssStorewiseGetterSetter pssStorewiseGetterSetter;
    PssStorewiseDetailGetterSetter pssStorewiseDetailGetterSetter;
    RemarkGetterSetter remarkGetterSetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new GSK_MT_SUPDatabase(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = PreferenceManager.getDefaultSharedPreferences(CompleteDownloadActivity.this);
        user_name = preferences.getString(CommonString.KEY_USERNAME, "");

        new BackgroundTask(this).execute();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment cartfrag = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, cartfrag).commit();
    }

    @Override
    protected void onStop() {

        super.onStop();
        //db.close();
    }

    class Data {
        int value;
        String name;
    }

    private class BackgroundTask extends AsyncTask<Void, Data, String> {
        private Context context;

        BackgroundTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Download PJP...");
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
                // JCP
                XmlPullParserFactory factory = XmlPullParserFactory
                        .newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                data.value = 10;
                data.name = "JCP Data Downloading";
                publishProgress(data);

                // JCP Master
                SoapObject request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", user_name);
                request.addProperty("Type", "JOURNEYPLAN_SUP");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                Object result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_NAME_STORE_LAYOUT;
                }
                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                FailureGetterSetter failureGetterSetter = XMLHandlers.failureXMLHandler(xpp, eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD + "," + failureGetterSetter.getErrorMsg();
                }
                if (!result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_NAME_JCP;
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    jcpData = XMLHandlers.JCPXMLHandler(xpp, eventType);
                    String jcp_table = jcpData.getMeta_data();
                    TableBean.setJcp_table(jcp_table);
                }
                if (jcpData.getSTORE_ID().size() == 0) {
                    return "NO JCP for Today";
                }

                data.value = 15;
                data.name = "Questionnnair Data Downloading";
                publishProgress(data);
                // Questionnaire - master list
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "QUESTIONNAIRE_SUP");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                }
                if (result.toString().equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    //	return CommonString.METHOD_NAME_STORE_SIZE;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp, eventType);
                if (failureGetterSetter.getStatus().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD + "," + failureGetterSetter.getErrorMsg();
                }

                if (!result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    questionnairData = XMLHandlers.QuestionXMLHandler(xpp, eventType);
                    String question_table = questionnairData.getMeta_data();
                    TableBean.setQuestionnair_table(question_table);
                }

                data.value = 20;
                data.name = "Non Working Data Downloading";

                publishProgress(data);

                // Download Non Working reason sup

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", user_name);
                request.addProperty("Type", "NON_WORKING_REASON_SUP");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //	return CommonString.METHOD_NAME_STORE_SIZE;
                }
                if (!result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_NAME_JCP;
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    nonworkingData = XMLHandlers.NonWorkingXMLHandler(xpp, eventType);
                    String nonWorkingtable = nonworkingData.getMeta_data();
                    TableBean.setNon_working_table(nonWorkingtable);
                }

                data.value = 25;
                data.name = "TOT Mapping Downloading";
                publishProgress(data);
                // TOT Mapping downloading
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "TOT_MAPPING_SUP");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //	return CommonString.METHOD_NAME_STORE_SIZE;
                }
                if (!result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_NAME_JCP;
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    totData = XMLHandlers.TDSXMLHandler(xpp, eventType);
                    String table = totData.getMeta_data();
                    TableBean.setTot_mapping_table(table);


                }
                data.value = 30;
                data.name = "Primary Window Display Downloading";
                publishProgress(data);

                // Primary Window Display downloading
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", user_name);
                request.addProperty("Type", "MAPPING_PRIMARY_WINDOW_DISPLAY_SUP");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //	return CommonString.METHOD_NAME_STORE_SIZE;
                }

                if (!result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                }

                // download Category Master
                data.value = 35;
                data.name = "Category Master Downloading";
                publishProgress(data);
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "CATEGORY_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                }
                if (result.toString().equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                }
                if (result.toString().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    failureGetterSetter = XMLHandlers.failureXMLHandler(xpp, eventType);
                    if (failureGetterSetter.getStatus().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                        return CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD + "," + failureGetterSetter.getErrorMsg();
                    }
                }

                if (!result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    stockData = XMLHandlers.StockMappingXMLHandler(xpp, eventType);
                    String stock_table = stockData.getMeta_data();
                    TableBean.setCategory_master_table(stock_table);
                }


                // Brand - master list
                data.value = 40;
                data.name = "Brand - master list Downloading";
                publishProgress(data);
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "BRANDMASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //	return CommonString.METHOD_NAME_STORE_SIZE;
                }

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    //	return CommonString.METHOD_NAME_STORE_SIZE;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD + ","
                            + failureGetterSetter.getErrorMsg();
                }


                if (!result.toString()
                        .equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_NAME_JCP;

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    brandData = XMLHandlers.BrandXMLHandler(xpp, eventType);
                    String brand_table = brandData.getMeta_data();
                    TableBean.setBrand_master_table(brand_table);

//					size = XMLHandlers.SizemasterHandler(xpp, eventType);
//					db.InsertSizeMaster(size);

                }
                data.value = 45;
                data.name = "Display - master list Downloading";
                publishProgress(data);
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "DISPLAYMASTER_NEW");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //	return CommonString.METHOD_NAME_DownLoad_Promotional_Master;
                }

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    //return CommonString.METHOD_NAME_DownLoad_Promotional_Master;
                }

                // for failure


                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_FAILURE)) {

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                            eventType);

                    if (failureGetterSetter.getStatus().equalsIgnoreCase(
                            CommonString.KEY_FAILURE)) {
                        return CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD
                                + "," + failureGetterSetter.getErrorMsg();
                    }

                }

                if (!result.toString()
                        .equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_NAME_JCP;

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    displayData = XMLHandlers.DisplayXMLHandler(xpp, eventType);
                    String display_table = displayData.getMeta_data();
                    TableBean.setDisplay_table(display_table);

//					promotionMaster = XMLHandlers.PromotionalmasterHandler(xpp,
//							eventType);
//					db.InsertPromotion_data(promotionMaster);


                }

                // Downloading the Additional Display Mapping
                data.value = 50;
                data.name = "Additional Display Mapping Downloading";
                publishProgress(data);
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "MAPPING_ADDITIONAL_VISIBILITY_SUP");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //	return CommonString.METHOD_NAME_DownLoad_Promotional_Master;
                }

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    //return CommonString.METHOD_NAME_DownLoad_Promotional_Master;
                }

                // for failure


                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_FAILURE)) {

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                            eventType);

                    if (failureGetterSetter.getStatus().equalsIgnoreCase(
                            CommonString.KEY_FAILURE)) {
                        return CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD
                                + "," + failureGetterSetter.getErrorMsg();
                    }

                }

                if (!result.toString()
                        .equalsIgnoreCase(CommonString.KEY_FALSE)) {


                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();

                    additionalData = XMLHandlers.AdditionalMappingXMLHandler(xpp, eventType);

                    String additionalMapping = additionalData.getMeta_data();


                    TableBean.setAdditional_mapping_table(additionalMapping);


                }

                // Downloading the question Master
                data.value = 55;
                data.name = "question Master Mapping Downloading";
                publishProgress(data);

                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "QUESTION_MASTER");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //	return CommonString.METHOD_NAME_DownLoad_Promotional_Master;
                }

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    //return CommonString.METHOD_NAME_DownLoad_Promotional_Master;
                }

                // for failure


                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_FAILURE)) {

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                            eventType);

                    if (failureGetterSetter.getStatus().equalsIgnoreCase(
                            CommonString.KEY_FAILURE)) {
                        return CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD
                                + "," + failureGetterSetter.getErrorMsg();
                    }

                }

                if (!result.toString()
                        .equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_NAME_JCP;

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    questionData = XMLHandlers.QuestionXMLHandlerr(xpp, eventType);
                    String question_meta_data = questionData.getMeta_data();

                    TableBean.setQuestion_Table(question_meta_data);


                }


                // Downloading the question Mapping
                data.value = 60;
                data.name = "question Mapping Downloading";
                publishProgress(data);
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "QUESTION_MAPPING");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                // for failure
                if (result.toString().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    failureGetterSetter = XMLHandlers.failureXMLHandler(xpp, eventType);
                    if (failureGetterSetter.getStatus().equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                        return CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD + "," + failureGetterSetter.getErrorMsg();
                    }
                }
                if (!result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    questionMappingData = XMLHandlers.QuestionMappingXMLHandler(xpp, eventType);
                    String questionMapping = questionMappingData.getMeta_data();
                    TableBean.setQuestion_mapping_table(questionMapping);
                }

                // SUP_INCENTIVE_LTM data
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "SUP_INCENTIVE_LTM");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    supincentiveGetterSetter = XMLHandlers.SupIncentiveMappingXMLHandler(xpp, eventType);
                    if (supincentiveGetterSetter.getCurMonth().size() > 0) {
                        //resultHttp = CommonString.KEY_SUCCESS;
                        String table = supincentiveGetterSetter.getSupincentiveTable();
                        TableBean.setSupIncentive_mapping_table(table);
                    } else {
                        // return "COMPANY_MASTER";
                    }
                    data.value = 60;
                    data.name = "SUP_INCENTIVE_LTM Data Downloading";
                    publishProgress(data);


                }
                // SUP_FOCUS_SALES data

                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "SUP_FOCUS_SALES");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    supFocusSaleGetterSetter = XMLHandlers.SupFocusSaleMappingXMLHandler(xpp, eventType);
                    if (supFocusSaleGetterSetter.getEmployee().size() > 0) {
                        //resultHttp = CommonString.KEY_SUCCESS;
                        String table = supFocusSaleGetterSetter.getSupfocussaleTable();
                        TableBean.setFocus_sale_mapping_table(table);
                    } else {
                        // return "COMPANY_MASTER";
                    }
                    data.value = 65;
                    data.name = "SUP_FOCUS_SALES Data Downloading";
                    publishProgress(data);
                }

                // SUP_TOTAL_SALES data

                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "SUP_TOTAL_SALES");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    totalSaleGetterSetter = XMLHandlers.TotalSaleMappingXMLHandler(xpp, eventType);
                    if (totalSaleGetterSetter.getEmployee().size() > 0) {
                        //resultHttp = CommonString.KEY_SUCCESS;
                        String table = totalSaleGetterSetter.getTotalsaleTable();
                        TableBean.setTotal_sale_mapping_table(table);
                    } else {
                        // return "COMPANY_MASTER";
                    }
                    data.value = 70;
                    data.name = "SUP_TOTAL_SALES Data Downloading";
                    publishProgress(data);
                }

                // SUP_TEAM_PSS data

                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "SUP_TEAM_PSS");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    teamPassGetterSetter = XMLHandlers.TeamPassMappingXMLHandler(xpp, eventType);
                    if (teamPassGetterSetter.getMerchanD().size() > 0) {
                        //resultHttp = CommonString.KEY_SUCCESS;
                        String table = teamPassGetterSetter.getTeampassTable();
                        TableBean.setTeam_pass_sale_mapping_table(table);
                    } else {
                        // return "COMPANY_MASTER";
                    }
                    data.value = 75;
                    data.name = "SUP_TEAM_PSS Data Downloading";
                    publishProgress(data);
                }


                // SUP_FOCUS_SALES_STOREWISE data
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "SUP_FOCUS_SALES_STOREWISE");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();

                    focusSaleStoreWiseGetterSetter = XMLHandlers.FocusSaleStoreWiseMappingXMLHandler(xpp, eventType);
                    if (focusSaleStoreWiseGetterSetter.getStorenm().size() > 0) {
                        //resultHttp = CommonString.KEY_SUCCESS;
                        String table = focusSaleStoreWiseGetterSetter.getFocussalestorewiseTable();
                        TableBean.setFocus_sale_storewise_sale_mapping_table(table);
                    } else {
                        // return "COMPANY_MASTER";
                    }
                    data.value = 80;
                    data.name = "SUP_FOCUS_SALES_STOREWISE Data Downloading";
                    publishProgress(data);
                }

                // SUP_TOTAL_SALES_STOREWISE data
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "SUP_TOTAL_SALES_STOREWISE");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();



                    totalSaleStorewiseGetterSetter = XMLHandlers.TotalSaleStoreWiseMappingXMLHandler(xpp, eventType);
                    if (totalSaleStorewiseGetterSetter.getStoreN().size() > 0) {
                        //resultHttp = CommonString.KEY_SUCCESS;
                        String table = totalSaleStorewiseGetterSetter.getTotalsalestorewiseTable();
                        TableBean.setTotal_sale_storewise_sale_mapping_table(table);
                    } else {
                        // return "COMPANY_MASTER";
                    }
                    data.value = 85;
                    data.name = "SUP_TOTAL_SALES_STOREWISE Data Downloading";
                    publishProgress(data);
                }

                // SUP_TOTAL_SALES_STOREWISE data
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "SUP_PSS_STOREWISE");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    pssStorewiseGetterSetter = XMLHandlers.PssStorewiseSaleStoreWiseMappingXMLHandler(xpp, eventType);
                    if (pssStorewiseGetterSetter.getStoreN().size() > 0) {
                        //resultHttp = CommonString.KEY_SUCCESS;
                        String table = pssStorewiseGetterSetter.getPssstorewiseTable();
                        TableBean.setPss_sale_storewise_sale_mapping_table(table);
                    } else {
                        // return "COMPANY_MASTER";
                    }
                    data.value = 90;
                    data.name = "SUP_PSS_STOREWISE Data Downloading";
                    publishProgress(data);
                }

                // SUP_PSS_STOREWISE_DETAIL data
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "SUP_PSS_STOREWISE_DETAIL");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    pssStorewiseDetailGetterSetter = XMLHandlers.PssStorewiseDetailsMappingXMLHandler(xpp, eventType);
                    if (pssStorewiseDetailGetterSetter.getStore_id().size() > 0) {
                        //resultHttp = CommonString.KEY_SUCCESS;
                        String table = pssStorewiseDetailGetterSetter.getPssstorewisedetailTable();
                        TableBean.setPss_srorewise_details_mapping_table(table);
                    } else {
                        // return "COMPANY_MASTER";
                    }
                    data.value = 95;
                    data.name = "SUP_PSS_STOREWISE_DETAIL Data Downloading";
                    publishProgress(data);
                }

                // SUP_PSS_REMARKS data
                request = new SoapObject(CommonString.NAMESPACE, CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", user_name);
                request.addProperty("Type", "SUP_PSS_REMARKS");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString.URL);
                androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    remarkGetterSetter = XMLHandlers.RemarkMappingXMLHandler(xpp, eventType);
                    if (remarkGetterSetter.getRemark_cd().size() > 0) {
                        //resultHttp = CommonString.KEY_SUCCESS;
                        String table = remarkGetterSetter.getRemark_table();
                        TableBean.setRemark_table(table);
                    } else {
                        // return "COMPANY_MASTER";
                    }
                    data.value = 97;
                    data.name = "SUP_PSS_REMARKS Data Downloading";
                    publishProgress(data);
                }


                publishProgress(data);
                db.open();
                db.InsertJCP(jcpData);
                db.InsertQuestionnairData(questionnairData);
                db.InsertNonWorking(nonworkingData);
                db.InsertCategoryMaster(stockData);
                db.InsertTDS(totData);
                db.InsertBrandMasterData(brandData);
                db.InsertDisplay(displayData);
                db.InsertAdditionalMappingData(additionalData);
                db.InsertQuestionData(questionData);
                db.InsertQuestionMappingData(questionMappingData);

                db.InsertSupIncentiveData(supincentiveGetterSetter);
                db.InsertFocusSaleData(supFocusSaleGetterSetter);
                db.InsertTotalSaleData(totalSaleGetterSetter);
                db.InsertTeamPssData(teamPassGetterSetter);
                db.InsertFocusSaleStoreWiseData(focusSaleStoreWiseGetterSetter);
                db.InsertTotalSaleStoreWiseData(totalSaleStorewiseGetterSetter);
                db.InsertPssSaleStoreWiseData(pssStorewiseGetterSetter);
                db.InsertPssStoreWiseDetailsData(pssStorewiseDetailGetterSetter);
                db.InsertRemarkData(remarkGetterSetter);
                data.value = 100;
                data.name = "Finishing";
                publishProgress(data);
                return CommonString.KEY_SUCCESS;

            } catch (MalformedURLException e) {
                final AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                final AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket", e);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();

                    }
                });
            } catch (Exception e) {
                final AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);

                e.getMessage();
                e.printStackTrace();
                e.getCause();
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
            // TODO Auto-generated method stub

            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);

        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            dialog.dismiss();
            if (result.equals(CommonString.KEY_SUCCESS)) {
                AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_DOWNLOAD, "success", null);
                message.showMessage();
            } else if (result.equals(CommonString.METHOD_NAME_JCP)) {
                AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this, AlertMessage.MESSAGE_JCP_FALSE, "success", null);
                message.showMessage();
            } else if (!result.equals("")) {
                AlertMessage message = new AlertMessage(CompleteDownloadActivity.this, result, "success", null);
                message.showMessage();
            }

        }

    }

}
