/*
package com.gsk.sup.gskmt.Upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.cpm.Constants.CommonString;
import com.cpm.Database.GSK_MT_SUPDatabase;


import com.cpm.delegates.CoverageBean;


import com.cpm.delegates.SkuBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.TOTBean;
import com.cpm.message.AlertMessage;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlHandler.FailureXMLHandler;
import com.example.gsk_mt_sup.R;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;

import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UploadImageActivity extends Activity {
	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	private String visit_date;
	private SharedPreferences preferences;
	private GSK_MT_SUPDatabase database;
	private int factor, k;
	private FailureGetterSetter failureGetterSetter = null;

	String result, username, cate_id;
	String datacheck = "";
	String[] words;
	String validity, storename="", devationStoresName;
	String mid = "";
	String errormsg = "";
	static int counter = 1;
	
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	
	ArrayList<SkuBean> stockimages = new ArrayList<SkuBean>();
	
	ArrayList<TOTBean> beforetotData = new ArrayList<TOTBean>();
	ArrayList<TOTBean> afterTOTData = new ArrayList<TOTBean>();
	ArrayList<SkuBean> beforeaddtionalData = new ArrayList<SkuBean>();
	ArrayList<SkuBean> afterAddaitionalData = new ArrayList<SkuBean>();
	ArrayList<SkuBean> compData = new ArrayList<SkuBean>();
	
	StoreBean storestatus = new StoreBean();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_option);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		visit_date = preferences.getString(CommonString.KEY_DATE, null);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		username = preferences.getString(CommonString.KEY_USERNAME, null);
		cate_id = preferences.getString(CommonString.KEY_CATEGORY_ID, null);
		database = new GSK_MT_SUPDatabase(this);
		database.open();
		
		new UploadTask(this).execute();
		
	}
	
	private class UploadTask extends AsyncTask<Void, Void, String>{
		
		private Context context;

		UploadTask(Context context) {
			this.context = context;
		}
		
		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();

			dialog = new Dialog(context);
			dialog.setContentView(R.layout.custom);
			dialog.setTitle("Uploading Image");
			dialog.setCancelable(false);
			dialog.show();
			pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
			percentage = (TextView) dialog.findViewById(R.id.percentage);
			message = (TextView) dialog.findViewById(R.id.message);
		}

		@Override
		protected String doInBackground(Void... params) {
			

			try {
				coverageBeanlist = database.getCoverageData(visit_date, null, null);


				if (coverageBeanlist.size() > 0){

					if (coverageBeanlist.size() == 1) {
						factor = 50;
					} else {

						factor = 100 / (coverageBeanlist.size());
					}
				}
				
				for (int i = 0; i < coverageBeanlist.size(); i++) {

					if (coverageBeanlist.get(i).getStatus()
							.equalsIgnoreCase("D")) {

						storestatus = database.getStoreStatus(coverageBeanlist
								.get(i).getStoreId(), coverageBeanlist.get(i).getProcess_id());

						if ((storestatus.getCHECKOUT_STATUS().equalsIgnoreCase(
								CommonString.KEY_L) || storestatus
								.getCHECKOUT_STATUS().equalsIgnoreCase(
										CommonString.KEY_C))) {

							database.open();
//							storename = database.getStoreName(coverageBeanlist
//									.get(i).getStoreId());

							runOnUiThread(new Runnable() {

								public void run() {
									
									k = k + factor;
									pb.setProgress(k);
									percentage.setText(k + "%");
									message.setText(storename + " Images");
								}
							});

							
								
								if (coverageBeanlist.get(i).getImage() != null && !coverageBeanlist.get(i).getImage()
												.equals("")) {
								
								if (new File(Environment.getExternalStorageDirectory()+"/SUP_MT_GSK_Images/"
										+ coverageBeanlist.get(i).getImage())
										.exists()) {

									result = UploadPOSMImage(

											coverageBeanlist.get(i).getImage(), "SupDealerBoardImages");

								  if (result
											.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

										return CommonString.METHOD_Get_DR_POSM_IMAGES
												+ "," + errormsg;
									}

									runOnUiThread(new Runnable() {

										public void run() {
											message.setText("Coverage Image Uploaded");
										}
									});
								}
								}
								

							 

								database.open();

						
						//TOT Images
						
						
						afterTOTData = database.getAfterTOTDataForUpload(coverageBeanlist.get(i).getStoreId(),
								coverageBeanlist.get(i).getProcess_id());
						
						for(int j=0;j<afterTOTData.size();j++){


							if (afterTOTData.get(j).getImage1() != null
									&& !afterTOTData.get(j).getImage1()
											.equals("")) {

								if (new File(
										Environment.getExternalStorageDirectory()+"/SUP_MT_GSK_Images/"

										+ afterTOTData.get(j).getImage1())
										.exists()) {

									result = UploadPOSMImage(afterTOTData.get(j).getImage1(), "SupTOTImages");
									if (result
											.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

										return CommonString.METHOD_Upload_StoreDeviationImage
												+ "," + errormsg;
									}

									runOnUiThread(new Runnable() {

										public void run() {


											message.setText("After TOT image1 Uploaded");
										}
									});
								}
							}
							
							
							if (afterTOTData.get(j).getImage2() != null
									&& !afterTOTData.get(j).getImage2()
											.equals("")) {

								if (new File(
										Environment.getExternalStorageDirectory()+"/SUP_MT_GSK_Images/"

										+ afterTOTData.get(j).getImage2())
										.exists()) {

									result = UploadPOSMImage(afterTOTData.get(j).getImage2(), "SupTOTImages");
									if (result
											.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

										return CommonString.METHOD_Upload_StoreDeviationImage
												+ "," + errormsg;
									}

									runOnUiThread(new Runnable() {

										public void run() {


											message.setText("After TOT image 2 Uploaded");
										}
									});
								}
							}
							
							
							
							if (afterTOTData.get(j).getImage3() != null
									&& !afterTOTData.get(j).getImage3()
											.equals("")) {

								if (new File(
										Environment.getExternalStorageDirectory()+"/SUP_MT_GSK_Images/"

										+ afterTOTData.get(j).getImage3())
										.exists()) {

									result = UploadPOSMImage(afterTOTData.get(j).getImage3(), "SupTOTImages");
									if (result
											.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

										return CommonString.METHOD_Upload_StoreDeviationImage
												+ "," + errormsg;
									}

									runOnUiThread(new Runnable() {

										public void run() {


											message.setText("After TOT image 3 Uploaded");
										}
									});
								}
							}
							
						}
							
							beforeaddtionalData = database.getProductEntryDetailForUpload
									(coverageBeanlist.get(i).getStoreId(),coverageBeanlist.get(i).getProcess_id());
							
							for(int j=0;j<beforeaddtionalData.size();j++){


								if (beforeaddtionalData.get(j).getAdditional_image() != null
										&& !beforeaddtionalData.get(j).getAdditional_image()
												.equals("")) {

									if (new File(
											Environment.getExternalStorageDirectory()+"/SUP_MT_GSK_Images/"

											+ beforeaddtionalData.get(j).getAdditional_image())
											.exists()) {

										result = UploadPOSMImage(beforeaddtionalData.get(j).getAdditional_image(), 
												"SupAdditionalImages");
										if (result
												.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

											return CommonString.METHOD_Upload_StoreDeviationImage
													+ "," + errormsg;
										}

										runOnUiThread(new Runnable() {

											public void run() {


												message.setText("Additional Display Images Uploaded");
											}
										});
									}
								}
								
								
								
							}
	
							afterAddaitionalData = database.getAfterProductEntryDetailForUpload(coverageBeanlist.get(i)
									.getStoreId(),coverageBeanlist.get(i)
									.getProcess_id());
							
							for(int j=0;j<afterAddaitionalData.size();j++){
								



								if (afterAddaitionalData.get(j).getAdditional_image() != null
										&& !afterAddaitionalData.get(j).getAdditional_image()
												.equals("")) {

									if (new File(
											Environment.getExternalStorageDirectory()+"/SUP_MT_GSK_Images/"

											+ afterAddaitionalData.get(j).getAdditional_image())
											.exists()) {

										result = UploadPOSMImage(afterAddaitionalData.get(j).getAdditional_image(),
												"SupAdditionalImages");
										if (result
												.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

											return CommonString.METHOD_Upload_StoreDeviationImage
													+ "," + errormsg;
										}

										runOnUiThread(new Runnable() {

											public void run() {


												message.setText("Additional Display Images Uploaded");
											}
										});
									}
								}

								
							}
	

							}

							database.open();
							database.deleteAllTables(coverageBeanlist.get(i)
									.getStoreId(),coverageBeanlist.get(i).getProcess_id());
							database.updateStoreStatusAfterImageUpload(coverageBeanlist.get(i)
									.getStoreId(), coverageBeanlist.get(i)
									.getVisitDate(), "U", coverageBeanlist.get(i).getProcess_id());
							
//							database.updatePJPStoreStatusOnLeave(coveragePJPlist.get(i)
//									.getStoreId(), coveragePJPlist.get(i).getVisitDate(), "U");
							
//							database.deletepjpdata(coveragePJPlist.get(i).getStoreId(),
//									coveragePJPlist.get(i).getVisitDate());

							// SET COVERAGE STATUS
							String statusxml = "[DATA][USER_DATA][STORE_ID]"
									+ coverageBeanlist.get(i).getStoreId()
									+ "[/STORE_ID][CREATED_BY]" + username
									+ "[/CREATED_BY][VISIT_DATE]"
									+ coverageBeanlist.get(i).getVisitDate()
									+ "[/VISIT_DATE][STATUS]"
									+ CommonString.KEY_U
									+ "[/STATUS][/USER_DATA][/DATA]";

							SoapObject request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_SET_COVERAGE_STATUS);
							request.addProperty("onXML", statusxml);

							SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							HttpTransportSE androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport
									.call(CommonString.SOAP_ACTION_SET_COVERAGE_STATUS,
											envelope);
							Object result = (Object) envelope.getResponse();

							if (!result.toString().equalsIgnoreCase(
									CommonString.KEY_SUCCESS)) {

								if (result.toString().equalsIgnoreCase(
										CommonString.KEY_FALSE)) {

								}

							
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_SUCCESS)) {

							}

						}
					}

				return CommonString.KEY_SUCCESS;
			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						
						message.showMessage();
					}
				});

			} catch (IOException e) {
				final AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION,
						"socket_uploadimage", e);
				counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						
						message.showMessage();
						*/
/*
						 * if (counter < 3) { new
						 * UploadTask(UploadImageActivity.this).execute(); }
						 * else { message.showMessage(); counter = 1; }
						 *//*

					}
				});
			}

			catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);
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
		protected void onPostExecute(String result) {
			
			super.onPostExecute(result);

			dialog.dismiss();

			if (result.equals(CommonString.KEY_SUCCESS)) {
				database.deleteAllTables();
				database.close();
				AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_UPLOAD_IMAGE, "success", null);
				message.showMessage();
			} else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						UploadImageActivity.this, result, "success", null);
				message.showMessage();
			}
		}
		
	}
	
	
	public String UploadPOSMImage(String path, String folder) throws Exception {

		errormsg = "";
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		
		
		BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/SUP_MT_GSK_Images/" + path, o);

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
		Bitmap bitmap = BitmapFactory.decodeFile(
				Environment.getExternalStorageDirectory()+"/SUP_MT_GSK_Images/" + path, o2);

		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
		byte[] ba = bao.toByteArray();
		String ba1 = Base64.encodeBytes(ba);

		SoapObject request = new SoapObject(CommonString.NAMESPACE,
				CommonString.METHOD_Get_DR_POSM_IMAGES);

		request.addProperty("img", ba1);
		request.addProperty("name", path);
		request.addProperty("FolderName", folder);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				CommonString.URL);

		androidHttpTransport.call(
				CommonString.SOAP_ACTION_Get_DR_POSM_IMAGES, envelope);
		Object result = (Object) envelope.getResponse();

		if (result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
			
			new File(Environment.getExternalStorageDirectory()+"/SUP_MT_GSK_Images/" + path).delete();

			*/
/*if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
				return CommonString.KEY_FALSE;
			}

			SAXParserFactory saxPF = SAXParserFactory.newInstance(); 
			SAXParser saxP = saxPF.newSAXParser();
			XMLReader xmlR = saxP.getXMLReader();

			// for failure
			FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
			xmlR.setContentHandler(failureXMLHandler);

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(result.toString()));
			xmlR.parse(is);

			failureGetterSetter = failureXMLHandler
					.getFailureGetterSetter();

			if (failureGetterSetter.getStatus().equalsIgnoreCase(
					CommonString.KEY_FAILURE)) {
				errormsg = failureGetterSetter.getErrorMsg();
				return CommonString.KEY_FAILURE;
			}*//*

		} else 
		{
			
			return CommonString.KEY_FAILURE;
			
//			new File(Environment.getExternalStorageDirectory()+"/MT_GSK_Images/" + path).delete();
		}

		return "";
	}
	
	public String UploadGeoImage(String path, String folder) throws Exception {

		errormsg = "";
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/MT_GSK_Images/" + path, o);

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
		Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/MT_GSK_Images/" + path, o2);

		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
		byte[] ba = bao.toByteArray();
		String ba1 = Base64.encodeBytes(ba);

		SoapObject request = new SoapObject(CommonString.NAMESPACE,
				CommonString.METHOD_Get_DR_POSM_IMAGES);

		request.addProperty("img", ba1);
		request.addProperty("name", path);
		request.addProperty("FolderName", folder);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				CommonString.URL);

		androidHttpTransport.call(
				CommonString.SOAP_ACTION_Get_DR_POSM_IMAGES, envelope);
		Object result = (Object) envelope.getResponse();

		if (result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
			new File(Environment.getExternalStorageDirectory()+"/MT_GSK_Images/" + path).delete();
			*/
/*if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
				return CommonString.KEY_FALSE;
			}

			SAXParserFactory saxPF = SAXParserFactory.newInstance();
			SAXParser saxP = saxPF.newSAXParser();
			XMLReader xmlR = saxP.getXMLReader();

			// for failure
			FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
			xmlR.setContentHandler(failureXMLHandler);

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(result.toString()));
			xmlR.parse(is);

			failureGetterSetter = failureXMLHandler
					.getFailureGetterSetter();

			if (failureGetterSetter.getStatus().equalsIgnoreCase(
					CommonString.KEY_FAILURE)) {
				errormsg = failureGetterSetter.getErrorMsg();
				return CommonString.KEY_FAILURE;
			}
*//*
		} else {
			return CommonString.KEY_FAILURE;
		}

		return "";
	}

}
*/
