package com.gsk.sup.gskmt.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gsk.sup.gskmt.Constants.CommonString;
import com.gsk.sup.gskmt.delegates.CoverageBean;
import com.gsk.sup.gskmt.delegates.QuestionnairBean;
import com.gsk.sup.gskmt.delegates.ReasonModel;
import com.gsk.sup.gskmt.delegates.SkuBean;
import com.gsk.sup.gskmt.delegates.StoreBean;
import com.gsk.sup.gskmt.delegates.TOTBean;
import com.gsk.sup.gskmt.delegates.TableBean;
import com.gsk.sup.gskmt.xmlGetterSetter.AdditionalGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.DisplayGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.FocusSaleStoreWiseGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.JCPGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.NonWorkingGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.PerformanceNonAchivementGetterSetter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GSK_MT_SUPDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "GTMT_SUP_DATABASE";
    public static final int DATABASE_VERSION = 13;
    private SQLiteDatabase db;

    public GSK_MT_SUPDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    public void open() {
        try {

            db = this.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableBean.getJcp_table());
        db.execSQL(TableBean.getQuestionnair_table());
        db.execSQL(TableBean.getNon_working_table());
        db.execSQL(TableBean.getCategory_master_table());
        db.execSQL(TableBean.getTot_mapping_table());
        db.execSQL(TableBean.getBrand_master_table());
        db.execSQL(TableBean.getDisplay_table());
        db.execSQL(TableBean.getAdditional_mapping_table());
        db.execSQL(TableBean.getQuestion_mapping_table());
        db.execSQL(TableBean.getQuestion_Table());

        db.execSQL(TableBean.getFocus_sale_mapping_table());
        db.execSQL(TableBean.getSupIncentive_mapping_table());
        db.execSQL(TableBean.getTotal_sale_mapping_table());
        db.execSQL(TableBean.getTeam_pass_sale_mapping_table());
        db.execSQL(TableBean.getFocus_sale_storewise_sale_mapping_table());


        db.execSQL(TableBean.getTotal_sale_storewise_sale_mapping_table());
        db.execSQL(TableBean.getPss_sale_storewise_sale_mapping_table());
        db.execSQL(TableBean.getPss_srorewise_details_mapping_table());
        db.execSQL(TableBean.getRemark_table());

        db.execSQL(CommonString.CREATE_TABLE_insertHEADER_ASSET_DATA);
        db.execSQL(CommonString.CREATE_TABLE_insertCHILD_ASSET_DATA);
        db.execSQL(CommonString.CREATE_TABLE_ADDITIONAL_DETAILS);
        db.execSQL(CommonString.CREATE_TABLE_AFTER_ADDITIONAL_DETAILS);
        db.execSQL(CommonString.CREATE_TABLE_TOT_AFTER);
        db.execSQL(CommonString.CREATE_TABLE_QUESTION_ANSWER);
        db.execSQL(CommonString.CREATE_TABLE_COVERAGE_DATA);
        db.execSQL(CommonString.CREATE_TABLE_NON_ACHIVEMENT_DETAILS);
        db.execSQL(CommonString.CREATE_TABLE_SOME_COVERAGE_DATA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getJcp_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getNon_working_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getQuestionnair_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getCategory_master_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getTot_mapping_table());

        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getBrand_master_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getDisplay_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getAdditional_mapping_table());

        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getQuestion_mapping_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getQuestion_Table());

        db.execSQL("DROP TABLE IF EXISTS " + CommonString.CREATE_TABLE_insertHEADER_ASSET_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + CommonString.CREATE_TABLE_insertCHILD_ASSET_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + CommonString.CREATE_TABLE_ADDITIONAL_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + CommonString.CREATE_TABLE_AFTER_ADDITIONAL_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + CommonString.CREATE_TABLE_TOT_AFTER);

        db.execSQL("DROP TABLE IF EXISTS " + CommonString.CREATE_TABLE_QUESTION_ANSWER);
        db.execSQL("DROP TABLE IF EXISTS " + CommonString.CREATE_TABLE_COVERAGE_DATA);

    }


    public void deleteAllTables(String storeid, String process_id) {
        db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_STORE_ID + " = '" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_QUESTION_ANSWER, CommonString.KEY_STORE_ID + " = '" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_TOT_AFTER, CommonString.KEY_STORE_ID + " = '" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_INSERT_ADDTIONAL_DETAILS, CommonString.KEY_STORE_ID + " = '" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_INSERT_AFTER_ADDTIONAL_DETAILS, CommonString.KEY_STORE_ID + " = '" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_INSERT_PERFORMANCE_DETAILS, CommonString.KEY_STORE_ID + " = '" + storeid + "'", null);
        db.delete(CommonString.TABLE_COVERAGES_SOME_DATA, CommonString.KEY_STORE_ID + " = '" + storeid + "'", null);
        db.delete(CommonString.TABLE_INSERT_HEADER_QUESTIONNAIR_DATA, CommonString.KEY_STORE_ID + " = '" + storeid + "'", null);
        db.delete(CommonString.TABLE_INSERT_CHILD_QUESTIONNAIR_DATA, CommonString.KEY_STORE_ID + " = '" + storeid + "'", null);
    }

    public void deleteAllTables() {
        db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
        db.delete(CommonString.TABLE_INSERT_ADDTIONAL_DETAILS, null, null);
        db.delete(CommonString.TABLE_INSERT_AFTER_ADDTIONAL_DETAILS, null, null);
        db.delete(CommonString.TABLE_QUESTION_ANSWER, null, null);
        db.delete(CommonString.TABLE_INSERT_HEADER_QUESTIONNAIR_DATA, null, null);
        db.delete(CommonString.TABLE_TOT_AFTER, null, null);
        db.delete(CommonString.TABLE_INSERT_PERFORMANCE_DETAILS, null, null);
        db.delete(CommonString.TABLE_COVERAGES_SOME_DATA, null, null);
        db.delete(CommonString.TABLE_INSERT_CHILD_QUESTIONNAIR_DATA, null, null);



    }



    public void InsertJCP(JCPGetterSetter data) {
        db.delete("JOURNEY_PLAN_SUP", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getSTORE_ID().size(); i++) {
                values.put("STORE_ID", data.getSTORE_ID().get(i));
                values.put("EMP_ID", data.getEMP_ID().get(i));
                values.put("STORE", data.getSTORE().get(i));
                values.put("CITY", data.getCITY().get(i));
                values.put("VISIT_DATE", data.getVISIT_DATE().get(i));
                values.put("PROCESS_ID", data.getPROCESS_ID().get(i));
                values.put("PROCESS", data.getPROCESS().get(i));
                values.put("REGION_ID", data.getREGION_ID().get(i));
                values.put("KEY_ID", data.getKEY_ID().get(i));
                values.put("STORETYPE_ID", data.getSTORETYPE_ID().get(i));
                values.put("UPLOAD_STATUS", data.getUPLOAD_STATUS().get(i));
                values.put("CHECKOUT_STATUS", data.getCHECKOUT_STATUS().get(i));
                db.insert("JOURNEY_PLAN_SUP", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Journey plan ",
                    ex.toString());
        }
    }

    //get JCP Data

    public ArrayList<StoreBean> getAllJCPData() {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StoreBean> list = new ArrayList<StoreBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from JOURNEY_PLAN_SUP ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreBean sb = new StoreBean();
                    sb.setSTORE_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));
                    sb.setSTORE((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE))));
                    sb.setCITY(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ADDRES)));
                    sb.setEMP_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMP_ID")));
                    sb.setVISIT_DATE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("VISIT_DATE")));
                    sb.setREGION_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REGION_ID")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    sb.setStoreType_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORETYPE_ID")));
                    sb.setUPLOAD_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("UPLOAD_STATUS")));
                    sb.setCHECKOUT_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKOUT_STATUS")));
                    sb.setPROCESS_ID((dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROCESS_ID"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertQuestionnairData(QuestionnairGetterSetter data) {

        db.delete("QUESTIONNAIRE_SUP", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getPROCESS_ID().size(); i++) {
                values.put("PROCESS_ID", data.getPROCESS_ID().get(i));
                values.put("QSUB_CATEGORY_ID", data.getQSUB_CATEGORY_ID().get(i));
                values.put("QUESTION_SUB_CATEGORY", data.getQUESTION_SUB_CATEGORY().get(i));
                values.put("QUESTION_ID", data.getQUESTION_ID().get(i));
                values.put("QUESTION", data.getQUESTION().get(i));
                values.put("ANSWER_ID", data.getANSWER_ID().get(i));
                values.put("ANSWER", data.getANSWER().get(i));
                db.insert("QUESTIONNAIRE_SUP", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Journey plan ", ex.toString());
        }
    }


    public void InsertNonWorking(NonWorkingGetterSetter data) {

        db.delete("NON_WORKING_REASON_SUP", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getREASON_ID().size(); i++) {

                values.put("REASON_ID", data.getREASON_ID().get(i));
                values.put("REASON", data.getREASON().get(i));

                values.put("IMAGE_ALLOW", data.getIMAGE_ALLOW()
                        .get(i));

                values.put("ENTRY_ALLOW", data.getENTRY_ALLOW().get(i));


                db.insert("NON_WORKING_REASON_SUP", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Journey plan ",
                    ex.toString());
        }
    }

    public void InsertCategoryMaster(StockMappingGetterSetter data) {

        db.delete("CATEGORY_MASTER", null, null);

        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCategory_id().size(); i++) {

                values.put("CATEGORY_ID", data.getCategory_id().get(i));
                values.put("CATEGORY", data.getCategory().get(i));

                db.insert("CATEGORY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Category Master ",
                    ex.toString());
        }

    }


    public void InsertTDS(TDSGetterSetter tDsData) {

        db.delete("TOT_MAPPING_SUP", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < tDsData.getDISPLAY_ID().size(); i++) {

                values.put("STORE_ID", tDsData.getSTORE_ID().get(i));
                values.put("CATEGORY_ID", tDsData.getCATEGORY_ID().get(i));
                values.put("DISPLAY_ID", tDsData.getDISPLAY_ID().get(i));
                values.put("TARGET_QTY", tDsData.getTARGET_QTY().get(i));
                values.put("PROCESS_ID", tDsData.getPROCESS_ID().get(i));
                values.put("BRAND_ID", tDsData.getBRAND_ID().get(i));
                values.put("TYPE", tDsData.getTYPE().get(i));
                values.put("UID", tDsData.getUID().get(i));


                db.insert("TOT_MAPPING_SUP", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }
    }


    public void InsertBrandMasterData(SKUGetterSetter data
    ) {

        db.delete("BRAND_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getBrand_id().size(); i++) {

                values.put("BRAND_ID", data.getBrand_id().get(i));
                values.put("BRAND", data.getBrand().get(i));

                values.put("CATEGORY_ID", data.getCategory_id()
                        .get(i));

                values.put("CATEGORY", data.getCategory_name().get(i));

                values.put("COMPANY_ID", data.getCompany_id().get(i)
                );

                db.insert("BRAND_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }
    }


    public void InsertDisplay(DisplayGetterSetter displayData) {

        db.delete("DISPLAY_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < displayData.getDisplay_id().size(); i++) {

                values.put("DISPLAY_ID", displayData.getDisplay_id().get(i));
                values.put("DISPLAY", displayData.getDisplay().get(i));
                values.put("IMAGE_URL", displayData.getImage_url().get(i));


                db.insert("DISPLAY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }

    }


    public void InsertAdditionalMappingData(AdditionalGetterSetter data) {
        db.delete("MAPPING_ADDITIONAL_VISIBILITY_SUP", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getDisplay_id().size(); i++) {
                values.put("STORETYPE_ID", data.getStore_type_id().get(i));
                values.put("CATEGORY_ID", data.getCategory_id().get(i));
                values.put("DISPLAY_ID", data.getDisplay_id().get(i));
                values.put("PROCESS_ID", data.getProcess_id().get(i));

                db.insert("MAPPING_ADDITIONAL_VISIBILITY_SUP", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }


    public ArrayList<StoreBean> getJCP(String date) {
        Cursor cursordata = null;
        ArrayList<StoreBean> storedata = new ArrayList<StoreBean>();

        try {
            cursordata = db.rawQuery("SELECT  * from JOURNEY_PLAN_SUP WHERE VISIT_DATE = '" + date + "'", null);
            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    StoreBean sb = new StoreBean();
                    sb.setKey_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    sb.setSTORE_ID(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setEMP_ID(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_EMP_CD)));
                    sb.setSTORE(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_STORE)));

                    sb.setREGION_ID(cursordata.getString(cursordata.getColumnIndexOrThrow("REGION_ID")));

                    sb.setStoreType_id(cursordata.getString(cursordata.getColumnIndexOrThrow("STORETYPE_ID")));

                    sb.setUPLOAD_STATUS(cursordata.getString(cursordata.getColumnIndexOrThrow("UPLOAD_STATUS")));


                    sb.setPROCESS_ID(cursordata.getString(cursordata.getColumnIndexOrThrow("PROCESS_ID")));

                    sb.setProcess(cursordata.getString(cursordata.getColumnIndexOrThrow("PROCESS")));


                    sb.setVISIT_DATE(cursordata.getString(cursordata.getColumnIndexOrThrow("VISIT_DATE")));

                    sb.setCHECKOUT_STATUS(cursordata.getString(cursordata.getColumnIndexOrThrow("CHECKOUT_STATUS")));

                    sb.setCITY(cursordata.getString(cursordata.getColumnIndexOrThrow("CITY")));


                    storedata.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return storedata;

    }





    public ArrayList<StoreBean> getProcess(String date, String store_id) {
        Cursor cursordata = null;
        ArrayList<StoreBean> storedata = new ArrayList<StoreBean>();
        try {
            cursordata = db.rawQuery("SELECT  * from JOURNEY_PLAN_SUP WHERE VISIT_DATE = '" + date + "' AND STORE_ID ='" + store_id + "'", null);
            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    StoreBean sb = new StoreBean();
                    sb.setKey_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    sb.setSTORE_ID(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setEMP_ID(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_EMP_CD)));
                    sb.setSTORE(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_STORE)));
                    sb.setREGION_ID(cursordata.getString(cursordata.getColumnIndexOrThrow("REGION_ID")));
                    sb.setStoreType_id(cursordata.getString(cursordata.getColumnIndexOrThrow("STORETYPE_ID")));
                    sb.setUPLOAD_STATUS(cursordata.getString(cursordata.getColumnIndexOrThrow("UPLOAD_STATUS")));
                    sb.setPROCESS_ID(cursordata.getString(cursordata.getColumnIndexOrThrow("PROCESS_ID")));
                    sb.setProcess(cursordata.getString(cursordata.getColumnIndexOrThrow("PROCESS")));
                    sb.setVISIT_DATE(cursordata.getString(cursordata.getColumnIndexOrThrow("VISIT_DATE")));
                    sb.setCHECKOUT_STATUS(cursordata.getString(cursordata.getColumnIndexOrThrow("CHECKOUT_STATUS")));
                    sb.setCITY(cursordata.getString(cursordata.getColumnIndexOrThrow("CITY")));
                    storedata.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ", ex.toString());
        }
        return storedata;
    }

    public ArrayList<StoreBean> getStoreData(String date) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StoreBean> list = new ArrayList<StoreBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  DISTINCT STORE_ID ,STORE,CITY,EMP_ID,VISIT_DATE,REGION_ID,KEY_ID" +
                            ",STORETYPE_ID,UPLOAD_STATUS, CHECKOUT_STATUS, PROCESS_ID from JOURNEY_PLAN_SUP"
                            + " WHERE " + CommonString.KEY_CURRENT_DATETIME + "='" + date + "'",
                    null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreBean sb = new StoreBean();
                    sb.setSTORE_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));
                    sb.setSTORE((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE))));
                    sb.setCITY(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ADDRES)));
                    sb.setEMP_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMP_ID")));
                    sb.setVISIT_DATE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("VISIT_DATE")));
                    sb.setREGION_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REGION_ID")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    sb.setStoreType_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORETYPE_ID")));
                    sb.setUPLOAD_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("UPLOAD_STATUS")));
                    sb.setCHECKOUT_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKOUT_STATUS")));
                    sb.setPROCESS_ID((dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROCESS_ID"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }

    public ArrayList<StoreBean> getStoreAllData(String date) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StoreBean> list = new ArrayList<StoreBean>();
        Cursor dbcursor = null;
        try {
            dbcursor =
                    db.rawQuery("SELECT distinct STORE_ID,EMP_ID,STORE,CITY,VISIT_DATE,REGION_ID,KEY_ID,STORETYPE_ID,UPLOAD_STATUS,CHECKOUT_STATUS from " +
                            "JOURNEY_PLAN_SUP" + " WHERE " + CommonString.KEY_CURRENT_DATETIME + "='" + date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreBean sb = new StoreBean();
                    sb.setSTORE_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));
                    sb.setSTORE((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE))));
                    sb.setCITY(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ADDRES)));
                    sb.setEMP_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMP_ID")));
                    sb.setVISIT_DATE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("VISIT_DATE")));
                    sb.setREGION_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REGION_ID")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    sb.setStoreType_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORETYPE_ID")));
                    sb.setUPLOAD_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("UPLOAD_STATUS")));
                    sb.setCHECKOUT_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKOUT_STATUS")));
                    //sb.setPROCESS_ID((dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROCESS_ID"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<StoreBean> getStoreAllDataWithOutDate() {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StoreBean> list = new ArrayList<StoreBean>();
        Cursor dbcursor = null;
        try {
            dbcursor =
                    db.rawQuery("SELECT distinct STORE_ID,EMP_ID,STORE,CITY,VISIT_DATE,REGION_ID,KEY_ID,STORETYPE_ID,UPLOAD_STATUS,CHECKOUT_STATUS from JOURNEY_PLAN_SUP", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreBean sb = new StoreBean();
                    sb.setSTORE_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));
                    sb.setSTORE((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE))));
                    sb.setCITY(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ADDRES)));
                    sb.setEMP_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMP_ID")));
                    sb.setVISIT_DATE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("VISIT_DATE")));
                    sb.setREGION_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REGION_ID")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    sb.setStoreType_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORETYPE_ID")));
                    sb.setUPLOAD_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("UPLOAD_STATUS")));
                    sb.setCHECKOUT_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKOUT_STATUS")));
                    //sb.setPROCESS_ID((dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROCESS_ID"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<QuestionnairBean> getHeaderQuestionsData(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<QuestionnairBean> list = new ArrayList<QuestionnairBean>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT DISTINCT  QSUB_CATEGORY_ID, QUESTION_SUB_CATEGORY FROM QUESTIONNAIRE_SUP"
                            + " WHERE " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'",
                    null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    QuestionnairBean sb = new QuestionnairBean();

                    sb.setQSUB_CATEGORY_ID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QSUB_CATEGORY_ID")));

                    sb.setQSUB_CATEGORY(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUESTION_SUB_CATEGORY")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<QuestionnairBean> getQuestionChildListData(String q_header_id, String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<QuestionnairBean> list = new ArrayList<QuestionnairBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT DISTINCT QUESTION_ID, QUESTION FROM QUESTIONNAIRE_SUP" + " WHERE " + CommonString.KEY_PROCESS_ID
                            + "='" + process_id + "' AND QSUB_CATEGORY_ID ='" + q_header_id + "'",
                    null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    QuestionnairBean sb = new QuestionnairBean();
                    sb.setQuestion_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("QUESTION_ID")));
                    sb.setQuestion(dbcursor.getString(dbcursor.getColumnIndexOrThrow("QUESTION")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }

    public void updateCoverageStoreOutTime(String StoreId, String VisitDate, String outtime, String status, String process_id) {
        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_OUT_TIME, outtime);
            values.put(CommonString.KEY_STATUS, status);
            db.update(CommonString.TABLE_COVERAGE_DATA, values, CommonString.KEY_STORE_ID + "='" + StoreId + "' AND " + CommonString.KEY_VISIT_DATE + "='" + VisitDate + "' AND PROCESS_ID ='" + process_id + "'", null);
        } catch (Exception e) {

        }
    }

    public void updateCoverageStoreSomeOutTime(String StoreId, String VisitDate, String outtime, String status) {
        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_OUT_TIME, outtime);
            values.put(CommonString.KEY_STATUS, status);
            db.update(CommonString.TABLE_COVERAGES_SOME_DATA, values, CommonString.KEY_STORE_ID + "='" + StoreId + "' AND " + CommonString.KEY_VISIT_DATE + "='" + VisitDate + "'", null);
        } catch (Exception e) {

        }
    }

    public void updateCoverageStoreStatus(String StoreId, String VisitDate, String status) {
        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_STATUS, status);
            db.update(CommonString.TABLE_COVERAGE_DATA, values, CommonString.KEY_STORE_ID + "='" + StoreId + "' AND " + CommonString.KEY_VISIT_DATE + "='" + VisitDate + "'", null);
        } catch (Exception e) {

        }
    }


    public void updateStoreStatusOnCheckout(String storeid, String visitdate, String status, String process_id) {
        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_CHECKOUT_STATUS, status);
            db.update("JOURNEY_PLAN_SUP", values, CommonString.KEY_STORE_CD + "='" + storeid + "' AND " +
                    CommonString.KEY_CURRENT_DATETIME + "='" + visitdate + "' AND PROCESS_ID ='" + process_id + "'", null);
        } catch (Exception e) {
        }
    }

    public void updateStoreUploadStatus(String storeid, String visitdate, String status) {
        try {
            ContentValues values = new ContentValues();
            values.put("UPLOAD_STATUS", status);
            db.update("JOURNEY_PLAN_SUP", values, CommonString.KEY_STORE_CD + "='" + storeid + "' AND " + CommonString.KEY_CURRENT_DATETIME + "='" + visitdate + "'", null);
        } catch (Exception e) {
        }
    }

    public void updateStoreStatusOnCheckout_some(String storeid, String visitdate, String status) {
        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_CHECKOUT_STATUS, status);
            db.update("JOURNEY_PLAN_SUP", values, CommonString.KEY_STORE_CD + "='" + storeid + "' AND " + CommonString.KEY_CURRENT_DATETIME + "='" + visitdate + "'", null);
        } catch (Exception e) {

        }
    }



    public ArrayList<QuestionnairBean> getAllAnswers(String question_id,
                                                     String process_id) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<QuestionnairBean> list = new ArrayList<QuestionnairBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT DISTINCT ANSWER_ID, ANSWER FROM QUESTIONNAIRE_SUP" + " WHERE " + CommonString.KEY_PROCESS_ID
                            + "='" + process_id + "' AND QUESTION_ID ='" + question_id + "'",
                    null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    QuestionnairBean sb = new QuestionnairBean();

                    sb.setAnswer_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER_ID")));

                    sb.setAnswer(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public void InsertQuestionnairAnswerData(String process_id, String storeid, HashMap<QuestionnairBean, List<QuestionnairBean>> data,
                                             List<QuestionnairBean> save_listDataHeader) {
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {
            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, storeid);
                values.put(CommonString.KEY_PROCESS_ID, process_id);
                values.put(CommonString.KEY_QSUB_CATEGORY_ID, save_listDataHeader.get(i).getQSUB_CATEGORY_ID());
                values.put(CommonString.KEY_QSUB_CATEGORY, save_listDataHeader.get(i).getQSUB_CATEGORY());
                long l = db.insert(CommonString.TABLE_INSERT_HEADER_QUESTIONNAIR_DATA, null, values);
                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put(CommonString.KEY_COMMONID, l);
                    values1.put(CommonString.KEY_STORE_ID, storeid);
                    values1.put(CommonString.KEY_PROCESS_ID, process_id);
                    values1.put(CommonString.KEY_QUESTION_ID, data.get(save_listDataHeader.get(i)).get(j).getQuestion_id());
                    values1.put(CommonString.KEY_QUESTION, data.get(save_listDataHeader.get(i)).get(j).getQuestion());
                    values1.put(CommonString.KEY_ANSWER_ID, data.get(save_listDataHeader.get(i)).get(j).getAnswer_id());
                    values1.put(CommonString.KEY_ANSWER, data.get(save_listDataHeader.get(i)).get(j).getAnswer());
                    db.insert(CommonString.TABLE_INSERT_CHILD_QUESTIONNAIR_DATA, null, values1);
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Inserting Asset Data ", ex.toString());
        }
    }


    public ArrayList<QuestionnairBean> getInsertedAssetComplianceData(String storeid, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<QuestionnairBean> list = new ArrayList<QuestionnairBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_INSERT_HEADER_QUESTIONNAIR_DATA + " where " +
                    CommonString.KEY_STORE_ID + " = '" + storeid + "'AND PROCESS_ID ='" + process_id + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    QuestionnairBean sb = new QuestionnairBean();

                    sb.setCommonid(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    sb.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setProcess_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));
                    sb.setQSUB_CATEGORY_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_QSUB_CATEGORY_ID)));
                    sb.setQSUB_CATEGORY(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_QSUB_CATEGORY)));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<QuestionnairBean> getInsertedAssetSubList(int commomid, String store_cd, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<QuestionnairBean> list = new ArrayList<QuestionnairBean>();
        Cursor dbcursor = null;

        try {
/*

            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_INSERT_CHILD_QUESTIONNAIR_DATA + " where " + CommonString.KEY_PROCESS_ID + " = '" + process_id  + " AND STORE_ID ='" + store_cd + "'", null);
*/

            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_INSERT_CHILD_QUESTIONNAIR_DATA + " where " + CommonString.KEY_COMMONID + " ='" + commomid + "' AND PROCESS_ID ='" + process_id + "' AND STORE_ID ='" + store_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    QuestionnairBean sb = new QuestionnairBean();

                    sb.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));

                    sb.setProcess_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));


                    sb.setQuestion_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_QUESTION_ID)));


                    sb.setQuestion(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_QUESTION)));

                    sb.setAnswer_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_ANSWER_ID)));


                    sb.setAnswer(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_ANSWER)));


                    sb.setCommonid(dbcursor.getInt(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_COMMONID)));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void deleteQuestionnairData(String storeid, String process_id) {

        try {

            db.delete(CommonString.TABLE_INSERT_HEADER_QUESTIONNAIR_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "' AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'", null);
            db.delete(CommonString.TABLE_INSERT_CHILD_QUESTIONNAIR_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "' AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'", null);

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }



    public void InsertNonAchivementPerformance(String store_id, String reason_id, String reason,String visit_date) {
        db.delete(CommonString.TABLE_INSERT_PERFORMANCE_DETAILS, CommonString.KEY_STORE_ID + "='" + store_id + "' AND VISIT_DATE = '" + visit_date + "'", null);
        ContentValues values = new ContentValues();
        try {
            values.put(CommonString.KEY_STORE_ID, store_id);
            values.put(CommonString.KEY_NON_ACHIVEMENT_REASON_ID, reason_id);
            values.put(CommonString.KEY_NON_ACHIVEMENT_REASON, reason);
            values.put(CommonString.KEY_VISIT_DATE, visit_date);
            db.insert(CommonString.TABLE_INSERT_PERFORMANCE_DETAILS, null, values);
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Additional data ", ex.getMessage());
        }

    }


    /// getnonAchievementData
    public PerformanceNonAchivementGetterSetter getnonAchievementData(String id) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");

        PerformanceNonAchivementGetterSetter sb = new PerformanceNonAchivementGetterSetter();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from  NON_ACHIVEMENT_PERFORMANCE_DETAILS" + "  WHERE STORE_ID = '" + id + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                sb.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                sb.setReason_id((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_NON_ACHIVEMENT_REASON_ID))));
                sb.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_NON_ACHIVEMENT_REASON)));
                dbcursor.moveToNext();
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return sb;

    }












    public void InsertQuestionData(QuestionGetterSetter data) {
        db.delete("QUESTION_MASTER", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getQuestion_id().size(); i++) {
                values.put("QUESTION_ID", data.getQuestion_id().get(i));
                values.put("QUESTION", data.getQuestion().get(i));
                db.insert("QUESTION_MASTER", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }


    public void InsertQuestionMappingData(QuestionGetterSetter data) {
        db.delete("QUESTION_MAPPING", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getQuestion_id().size(); i++) {

                values.put("QUESTION_ID", data.getQuestion_id().get(i));
                values.put("DISPLAY_ID", data.getDisplay_id().get(i));

                db.insert("QUESTION_MAPPING", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }

    /// get store Status
    public StoreBean getStoreStatus_new(String id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");

        StoreBean sb = new StoreBean();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from  JOURNEY_PLAN_SUP" + "  WHERE STORE_ID = '" + id + "' AND PROCESS_ID ='" + process_id + " '", null);
            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; i++) {
                    sb.setSTORE_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setCHECKOUT_STATUS((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKOUT_STATUS"))));
                    sb.setUPLOAD_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("UPLOAD_STATUS")));
                    sb.setPROCESS_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROCESS_ID")));
                    dbcursor.moveToNext();

                }

                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return sb;

    }



    public void InsertCoverage(CoverageBean data, String process_id, String app_version, String remark, String status) {
        db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_STORE_ID + "='" + data.getStoreId() + "' and " + CommonString.KEY_VISIT_DATE + "='" + data.getVisitDate() + "' AND PROCESS_ID ='" + process_id + "'", null);
        ContentValues values = new ContentValues();
        try {
          values.put(CommonString.KEY_STORE_ID, data.getStoreId());
            values.put(CommonString.KEY_USER_ID, data.getUserId());
            values.put(CommonString.KEY_IN_TIME, data.getInTime());
            values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
            values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
            values.put(CommonString.KEY_LATITUDE, data.getLatitude());
            values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
            values.put(CommonString.KEY_REASON_ID, data.getReasonid());
            values.put(CommonString.KEY_REMARK, remark);
            values.put(CommonString.KEY_VERSION, app_version);
            values.put(CommonString.KEY_PROCESS_ID, process_id);
            values.put(CommonString.KEY_STORE_IMAGE, data.getImage());
            values.put(CommonString.KEY_STORE_IMAGE_ALLOW, data.getImage_allow());
            values.put(CommonString.KEY_STATUS, status);
            values.put("SUB_REASON_ID", data.getSub_reasonId());
            db.insert(CommonString.TABLE_COVERAGE_DATA, null, values);
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ", ex.toString());
        }

    }

    public void InsertCoveragesSomeData(CoverageBean data) {
        db.delete(CommonString.TABLE_COVERAGES_SOME_DATA, CommonString.KEY_STORE_ID + "='" + data.getStoreId() + "' and "
                + CommonString.KEY_VISIT_DATE + "='" + data.getVisitDate() +  "'", null);
        ContentValues values = new ContentValues();
        try {
            values.put(CommonString.KEY_STORE_ID, data.getStoreId());
            values.put(CommonString.KEY_USER_ID, data.getUserId());
            values.put(CommonString.KEY_IN_TIME, data.getInTime());
            values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
            values.put(CommonString.KEY_LATITUDE, data.getLatitude());
            values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
            values.put(CommonString.KEY_REASON_ID, data.getReasonid());
            values.put(CommonString.KEY_REMARK, data.getRemark());
            values.put(CommonString.KEY_STORE_IMAGE, data.getImage());
            values.put(CommonString.KEY_STORE_IMAGE_ALLOW, data.getImage_allow());
            values.put(CommonString.KEY_STATUS, data.getStatus());
            values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
            values.put("SUB_REASON_ID", data.getSub_reasonId());
            db.insert(CommonString.TABLE_COVERAGES_SOME_DATA, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ", ex.toString());
        }

    }


    public ArrayList<CoverageBean> getCoverageSomeData(String visitdate) {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGES_SOME_DATA + " where " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "'", null);

            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
                    sb.setInTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
                    sb.setReasonid(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)))));
                    sb.setReason(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REMARK)))));
                    sb.setStatus((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STATUS))))));
                    sb.setImage_allow(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE_ALLOW)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE)));
                    sb.setSub_reasonId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_REASON_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        } finally {
            if (dbcursor != null) {
                dbcursor.close();
            }
        }
        return list;
    }

    public void deleteSomeCoverageData() {
        db.delete(CommonString.TABLE_COVERAGES_SOME_DATA, null, null);
    }

    public ArrayList<CoverageBean> getCoverageSomeData_(String store_id,String visit_date) {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGES_SOME_DATA + " where " + CommonString.KEY_STORE_ID + "='" + store_id + "' AND VISIT_DATE = '"+visit_date+"'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
                    sb.setInTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
                    sb.setReasonid(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)))));
                    sb.setReason(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REMARK)))));
                    sb.setStatus((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STATUS))))));
                    sb.setImage_allow(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE_ALLOW)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE)));
                    sb.setSub_reasonId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_REASON_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        } finally {
            if (dbcursor != null) {
                dbcursor.close();
            }
        }
        return list;
    }


    //check if table is empty
    public boolean isOpeningDataFilledInCoverageStatus(String store_cd, String visit_date) {
        boolean filled = false;
        Cursor dbcursor = null;
        // values1.put(CommonString.KEY_ANSWER_ID, data.get(save_listDataHeader.get(i)).get(j).getAnswer_id());
        try {
            dbcursor = db.rawQuery("SELECT  * DR_STORE_COVERAGE WHERE STORE_ID = '" + store_cd + "' AND VISIT_DATE = '" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (!dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STATUS)).equalsIgnoreCase(CommonString.KEY_U)) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    public void updateStoreStatusOnLeave(String storeId, String visit_date, String status, String process_id) {
        try {


            ContentValues values = new ContentValues();
            values.put("UPLOAD_STATUS", status);
            db.update("JOURNEY_PLAN_SUP", values, CommonString.KEY_STORE_CD + "='" + storeId + "' AND " + CommonString.KEY_CURRENT_DATETIME + "='" + visit_date + "' AND PROCESS_ID ='" + process_id + "'", null);


        } catch (Exception e) {

        }
    }

    public void updateStoreStatusOnLeaveOrHoliday(ArrayList<StoreBean> list, String visit_date, String status) {
        try {
            ContentValues values = new ContentValues();
            for (int i = 0; i < list.size(); i++) {
                values.put("UPLOAD_STATUS", status);
                values.put("CHECKOUT_STATUS", CommonString.STORE_STATUS_LEAVE);
                db.update("JOURNEY_PLAN_SUP", values, CommonString.KEY_CURRENT_DATETIME + "='" + visit_date + "'", null);
            }
        } catch (Exception e) {

        }
    }


    public void InsertCoverageInLeaveCase(CoverageBean data, String user_id, ArrayList<StoreBean> storelist) {
        ContentValues values = new ContentValues();
        try {
            db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_VISIT_DATE + "='" + data.getVisitDate() + "'", null);
            for (int i = 0; i < storelist.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, storelist.get(i).getSTORE_ID());
                values.put(CommonString.KEY_USER_ID, data.getUserId());
                values.put(CommonString.KEY_IN_TIME, data.getInTime());
                values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
                values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
                values.put(CommonString.KEY_LATITUDE, data.getLatitude());
                values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
                values.put(CommonString.KEY_REASON_ID, data.getReasonid());
                values.put(CommonString.KEY_REMARK, data.getRemark());
                values.put(CommonString.KEY_VERSION, data.getApp_version());
                values.put(CommonString.KEY_PROCESS_ID, data.getProcess_id());
                values.put(CommonString.KEY_STORE_IMAGE, data.getImage());
                values.put(CommonString.KEY_STORE_IMAGE_ALLOW, data.getImage_allow());
                values.put(CommonString.KEY_STATUS, data.getStatus());
                values.put("SUB_REASON_ID", data.getSub_reasonId());
                db.insert(CommonString.TABLE_COVERAGE_DATA, null, values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Images ",
                    ex.toString());
        }
    }

    public void updateCoverageStatus(String store_id, String status, String process_id) {
        try {

            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_STATUS, status);
            db.update(CommonString.TABLE_COVERAGE_DATA, values, CommonString.KEY_STORE_ID + "='" + store_id + "'  AND PROCESS_ID ='" + process_id + "'", null);


        } catch (Exception e) {

        }
    }

    // getCoverageData
    public ArrayList<CoverageBean> getCoverageSpecificData(String store_id) {

        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_STORE_ID + "='" + store_id + "'", null);


            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
                    sb.setInTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
                    sb.setReasonid(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)))));
                    sb.setReason(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REMARK)))));
                    sb.setStatus((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STATUS))))));
                    sb.setProcess_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));
                    sb.setImage_allow(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE_ALLOW)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE)));
                    sb.setSub_reasonId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_REASON_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }

        return list;

    }



    public ArrayList<CoverageBean> getCoverageData(String visitdate, String store_id, String process_id) {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "' ", null);
            // dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "' AND STORE_ID ='" + store_id +  "' AND PROCESS_ID ='"+ process_id + "' ", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
                    sb.setInTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
                    sb.setReasonid(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)))));
                    sb.setReason(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REMARK)))));
                    sb.setStatus((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STATUS))))));
                    sb.setProcess_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));
                    sb.setImage_allow(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE_ALLOW)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE)));
                    sb.setSub_reasonId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_REASON_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        } finally {
            if (dbcursor != null) {
                dbcursor.close();
            }
        }
        return list;
    }


    public ArrayList<CoverageBean> getCoverageDataWithProcessid(String store_id, String process_id) {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_STORE_ID + "='" + store_id + "' AND PROCESS_ID ='" + process_id + "' ", null);
            // dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "' AND STORE_ID ='" + store_id +  "' AND PROCESS_ID ='"+ process_id + "' ", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
                    sb.setInTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
                    sb.setReasonid(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)))));
                    sb.setReason(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REMARK)))));
                    sb.setStatus((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STATUS))))));
                    sb.setProcess_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));
                    sb.setImage_allow(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE_ALLOW)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE)));
                    sb.setSub_reasonId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_REASON_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        } finally {
            if (dbcursor != null) {
                dbcursor.close();
            }
        }
        return list;
    }


    public ArrayList<CoverageBean> getCoverageData_Updated(String visitdate) {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "' ", null);
            // dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "' AND STORE_ID ='" + store_id +  "' AND PROCESS_ID ='"+ process_id + "' ", null);
            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
                    sb.setInTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
                    sb.setReasonid(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)))));
                    sb.setReason(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REMARK)))));
                    sb.setStatus((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STATUS))))));
                    sb.setProcess_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));
                    sb.setImage_allow(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE_ALLOW)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE)));
                    sb.setSub_reasonId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_REASON_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        } finally {
            if (dbcursor != null) {
                dbcursor.close();
            }
        }
        return list;
    }


    public void deleteCoverageFOR() {
        try {
            db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }


    public ArrayList<ReasonModel> getNonWorkingReason() {
        Cursor cursordata = null;
        ArrayList<ReasonModel> storedata = new ArrayList<ReasonModel>();

        try {

            cursordata = db.rawQuery("SELECT  * from NON_WORKING_REASON_SUP", null);
            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    ReasonModel sb = new ReasonModel();
                    sb.setReasonid(cursordata.getString(cursordata.getColumnIndexOrThrow("REASON_ID")));
                    sb.setReason(cursordata.getString(cursordata.getColumnIndexOrThrow("REASON")));
                    sb.setIMAGE_ALLOW(cursordata.getString(cursordata.getColumnIndexOrThrow("IMAGE_ALLOW")));
                    sb.setENTRY_ALLOW(cursordata.getString(cursordata.getColumnIndexOrThrow("ENTRY_ALLOW")));
                    storedata.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return storedata;

    }


    public void updateOutTime(String storeid, String outtime) {
        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_OUT_TIME, outtime);
            db.update(CommonString.TABLE_COVERAGE_DATA, values, CommonString.KEY_STORE_ID + "=" + storeid, null);
        } catch (Exception e) {

        }
    }







    public ArrayList<QuestionnairBean> getQuestionnairDataUpload(String storeId, String process_id) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<QuestionnairBean> list = new ArrayList<QuestionnairBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.PROCESS_ID, SD.STORE_ID, SD.QUESTION_ID, SD.QUESTION, SD.ANSWER_ID, SD.ANSWER, CD.QSUB_CATEGORY_ID " +
                                    "FROM INSERT_QUESTIONNAIR_HEADER_DATA CD INNER JOIN INSERT_QUESTIONNAIR_CHILD_DATA SD ON CD.KEY_ID=SD.COMMONID WHERE CD.STORE_ID= '"
                                    + storeId + "' AND CD.PROCESS_ID ='" + process_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    QuestionnairBean sb = new QuestionnairBean();


                    sb.setProcess_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));

                    sb.setStore_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));

                    sb.setQuestion_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_QUESTION_ID)));


                    sb.setQuestion(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_QUESTION)));

                    sb.setAnswer_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_ANSWER_ID)));


                    sb.setAnswer(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_ANSWER)));


                    sb.setQSUB_CATEGORY_ID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_QSUB_CATEGORY_ID)));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records !!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }
        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //check if table is empty
    public boolean isCoverageDataFilled(String visit_date) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM DR_STORE_COVERAGE " + "where " + CommonString.KEY_VISIT_DATE + "<>'" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }

    public void InsertSupIncentiveData(SupincentiveGetterSetter data) {
        db.delete("SUP_INCENTIVE_LTM", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getCurMonth().size(); i++) {
                values.put("CURRENTMONTH", data.getCurMonth().get(i));
                values.put("PM1", data.getPM1().get(i));
                values.put("PM2", data.getPM2().get(i));
                values.put("PM3", data.getPM3().get(i));
                values.put("CURPER", data.getCurrMonthper().get(i));
                values.put("PM2PER", data.getPm2per().get(i));
                values.put("PM1PER", data.getPm1per().get(i));
                values.put("PM3PER", data.getPm3per().get(i));
                db.insert("SUP_INCENTIVE_LTM", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }

    public ArrayList<SupincentiveGetterSetter> getSupIncentiveData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<SupincentiveGetterSetter> list = new ArrayList<SupincentiveGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from SUP_INCENTIVE_LTM ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SupincentiveGetterSetter sb = new SupincentiveGetterSetter();
                    sb.setCurMonth(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTMONTH")));
                    sb.setPM1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1")));
                    sb.setPM2(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2")));
                    sb.setPM3(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3")));

                    sb.setCurrMonthper(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURPER")));
                    sb.setPm1per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1PER")));
                    sb.setPm2per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2PER")));
                    sb.setPm3per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3PER")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public void InsertFocusSaleData(SupFocusSaleGetterSetter data) {
        db.delete("SUP_FOCUS_SALES", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getEmployee().size(); i++) {
                values.put("CURRENTMONTH", data.getCurrMonth().get(i));
                values.put("PM2", data.getPm2().get(i));
                values.put("PM1", data.getPm1().get(i));
                values.put("PM3", data.getPm3().get(i));
                values.put("CURRENTPER", data.getCurrMonthper().get(i));
                values.put("PM2PER", data.getPm2per().get(i));
                values.put("PM1PER", data.getPm1per().get(i));
                values.put("PM3PER", data.getPm3per().get(i));
                values.put("EMPLOYEE", data.getEmployee().get(i));
                db.insert("SUP_FOCUS_SALES", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }

    public ArrayList<SupFocusSaleGetterSetter> getSupFocusSaleData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<SupFocusSaleGetterSetter> list = new ArrayList<SupFocusSaleGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from SUP_FOCUS_SALES ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SupFocusSaleGetterSetter sb = new SupFocusSaleGetterSetter();
                    sb.setEmployee(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMPLOYEE")));
                    sb.setCurrMonth(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTMONTH")));
                    sb.setPm1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1")));
                    sb.setPm2(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2")));
                    sb.setPm3(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3")));
                    sb.setCurrMonthper(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTPER")));
                    sb.setPm1per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1PER")));
                    sb.setPm2per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2PER")));
                    sb.setPm3per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3PER")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertTotalSaleData(TotalSaleGetterSetter data) {
        db.delete("SUP_TOTAL_SALES", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getEmployee().size(); i++) {
                values.put("CURRENTMONTH", data.getCurrMonth().get(i));
                values.put("PM2", data.getPm2().get(i));
                values.put("PM1", data.getPm1().get(i));
                values.put("PM3", data.getPm3().get(i));
                values.put("CURRENTPER", data.getCurrMonthper().get(i));
                values.put("PM2PER", data.getPm2per().get(i));
                values.put("PM1PER", data.getPm1per().get(i));
                values.put("PM3PER", data.getPm3per().get(i));
                values.put("EMPLOYEE", data.getEmployee().get(i));
                db.insert("SUP_TOTAL_SALES", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }


    public ArrayList<TotalSaleGetterSetter> getSupTotalSalesData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<TotalSaleGetterSetter> list = new ArrayList<TotalSaleGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from SUP_TOTAL_SALES ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TotalSaleGetterSetter sb = new TotalSaleGetterSetter();
                    sb.setEmployee(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMPLOYEE")));
                    sb.setCurrMonth(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTMONTH")));
                    sb.setPm1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1")));
                    sb.setPm2(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2")));
                    sb.setPm3(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3")));
                    sb.setCurrMonthper(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTPER")));
                    sb.setPm1per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1PER")));
                    sb.setPm2per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2PER")));
                    sb.setPm3per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3PER")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertTeamPssData(TeamPassGetterSetter data) {
        db.delete("SUP_TEAM_PSS", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getMerchanD().size(); i++) {
                values.put("EMPLOYEE", data.getMerchanD().get(i));
                values.put("PERFECT", data.getPerfect().get(i));
                values.put("NEAR_PERFECT", data.getNear20perF().get(i));
                values.put("NOT_PERFECT", data.getNot20perF().get(i));
                values.put("TOTAL_STORES", data.getTotal20store().get(i));
                db.insert("SUP_TEAM_PSS", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }


    public ArrayList<TeamPassGetterSetter> getTeamPssSalesData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<TeamPassGetterSetter> list = new ArrayList<TeamPassGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from SUP_TEAM_PSS ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TeamPassGetterSetter sb = new TeamPassGetterSetter();
                    sb.setMerchanD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMPLOYEE")));
                    sb.setPerfect(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PERFECT")));
                    sb.setNear20perF(dbcursor.getString(dbcursor.getColumnIndexOrThrow("NEAR_PERFECT")));
                    sb.setNot20perF(dbcursor.getString(dbcursor.getColumnIndexOrThrow("NOT_PERFECT")));
                    sb.setTotal20store(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TOTAL_STORES")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public void InsertFocusSaleStoreWiseData(FocusSaleStoreWiseGetterSetter data) {
        db.delete("SUP_FOCUS_SALES_STOREWISE", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getEmployee().size(); i++) {
                values.put("STORE_ID", data.getStorenm().get(i));
                values.put("PM2", data.getPm2().get(i));
                values.put("PM1", data.getPm1().get(i));
                values.put("PM3", data.getPm3().get(i));
                values.put("CURRENTPER", data.getCurrMonthper().get(i));
                values.put("PM2PER", data.getPm2per().get(i));
                values.put("PM1PER", data.getPm1per().get(i));
                values.put("PM3PER", data.getPm3per().get(i));
                values.put("CURRENTMONTH", data.getCuurrentM().get(i));
                values.put("EMPLOYEE", data.getEmployee().get(i));
                values.put("TARGET", data.getTarget().get(i));
                db.insert("SUP_FOCUS_SALES_STOREWISE", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }

    public ArrayList<FocusSaleStoreWiseGetterSetter> getStoreWiseFocusSaleData(String store_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<FocusSaleStoreWiseGetterSetter> list = new ArrayList<FocusSaleStoreWiseGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from SUP_FOCUS_SALES_STOREWISE where " + CommonString.KEY_STORE_ID + " = '" + store_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FocusSaleStoreWiseGetterSetter sb = new FocusSaleStoreWiseGetterSetter();
                    sb.setEmployee(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMPLOYEE")));
                    sb.setStorenm(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")));
                    sb.setCuurrentM(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTMONTH")));
                    sb.setPm1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1")));
                    sb.setPm2(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2")));
                    sb.setPm3(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3")));
                    sb.setTarget(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TARGET")));
                    sb.setCurrMonthper(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTPER")));
                    sb.setPm1per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1PER")));
                    sb.setPm2per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2PER")));
                    sb.setPm3per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3PER")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertTotalSaleStoreWiseData(TotalSaleStorewiseGetterSetter data) {
        db.delete("SUP_TOTAL_SALES_STOREWISE", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getEmployee().size(); i++) {
                values.put("STORE_ID", data.getStoreN().get(i));
                values.put("PM2", data.getPm2().get(i));
                values.put("PM1", data.getPm1().get(i));
                values.put("PM3", data.getPm3().get(i));
                values.put("TARGET", data.getTarget().get(i));
                values.put("CURRENTPER", data.getCurrMonthper().get(i));
                values.put("PM2PER", data.getPm2per().get(i));
                values.put("PM1PER", data.getPm1per().get(i));
                values.put("PM3PER", data.getPm3per().get(i));
                values.put("CURRENTMONTH", data.getCurrentM().get(i));
                values.put("EMPLOYEE", data.getEmployee().get(i));
                db.insert("SUP_TOTAL_SALES_STOREWISE", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }

    public ArrayList<TotalSaleStorewiseGetterSetter> getStoreWiseTotalSaleData(String store_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<TotalSaleStorewiseGetterSetter> list = new ArrayList<TotalSaleStorewiseGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from SUP_TOTAL_SALES_STOREWISE where " + CommonString.KEY_STORE_ID + " = '" + store_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TotalSaleStorewiseGetterSetter sb = new TotalSaleStorewiseGetterSetter();
                    sb.setEmployee(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMPLOYEE")));
                    sb.setStoreN(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")));
                    sb.setCurrentM(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTMONTH")));
                    sb.setPm1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1")));
                    sb.setPm2(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2")));
                    sb.setPm3(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3")));
                    sb.setTarget(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TARGET")));
                    sb.setCurrMonthper(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTPER")));
                    sb.setPm1per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1PER")));
                    sb.setPm2per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2PER")));
                    sb.setPm3per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3PER")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertPssSaleStoreWiseData(PssStorewiseGetterSetter data) {
        db.delete("SUP_PSS_STOREWISE", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getStoreN().size(); i++) {
                values.put("STORE_ID", data.getStoreN().get(i));
                values.put("PM2", data.getPm2().get(i));
                values.put("PM1", data.getPm1().get(i));
                values.put("PM3", data.getPm3().get(i));
                values.put("CURRENTMONTH", data.getCurrentM().get(i));
                values.put("EMPLOYEE", data.getMerchanD().get(i));
                values.put("CURRPER", data.getCurrMonthper().get(i));
                values.put("PM2PER", data.getPm2per().get(i));
                values.put("PM1PER", data.getPm1per().get(i));
                values.put("PM3PER", data.getPm3per().get(i));
                db.insert("SUP_PSS_STOREWISE", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }

    }

    public ArrayList<PssStorewiseGetterSetter> getStoreWisePSSData(String store_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<PssStorewiseGetterSetter> list = new ArrayList<PssStorewiseGetterSetter>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * from SUP_PSS_STOREWISE where " + CommonString.KEY_STORE_ID + " = '" + store_id + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    PssStorewiseGetterSetter sb = new PssStorewiseGetterSetter();
                    sb.setMerchanD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMPLOYEE")));
                    sb.setCurrentM(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRENTMONTH")));
                    sb.setPm1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1")));
                    sb.setPm2(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2")));
                    sb.setPm3(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3")));
                    sb.setCurrMonthper(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CURRPER")));
                    sb.setPm1per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM1PER")));
                    sb.setPm2per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM2PER")));
                    sb.setPm3per(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PM3PER")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertPssStoreWiseDetailsData(PssStorewiseDetailGetterSetter data) {
        db.delete("SUP_PSS_STOREWISE_DETAIL", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getStore_id().size(); i++) {
                values.put("STORE_ID", data.getStore_id().get(i));
                values.put("CATEGORY_ID", data.getCategory_id().get(i));
                values.put("CATEGORY", data.getCategory().get(i));
                values.put("SOS", data.getSos().get(i));
                values.put("TOT", data.getTot().get(i));
                values.put("PAID", data.getPaid().get(i));
                values.put("ADDITIONAL", data.getAddition().get(i));
                values.put("PSS_SCORE", data.getPss_store().get(i));
                db.insert("SUP_PSS_STOREWISE_DETAIL", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }
    }

    public ArrayList<PssStorewiseDetailGetterSetter> getStoreWisePSSDetailsData(String store_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<PssStorewiseDetailGetterSetter> list = new ArrayList<PssStorewiseDetailGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from SUP_PSS_STOREWISE_DETAIL where " + CommonString.KEY_STORE_ID + " = '" + store_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    PssStorewiseDetailGetterSetter sb = new PssStorewiseDetailGetterSetter();
                    sb.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")));
                    sb.setCategory_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_ID")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY")));
                    sb.setSos(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SOS")));
                    sb.setTot(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TOT")));
                    sb.setPaid(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PAID")));
                    sb.setAddition(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ADDITIONAL")));
                    sb.setPss_store(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PSS_SCORE")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public void InsertRemarkData(RemarkGetterSetter data) {
        db.delete("SUP_PSS_REMARKS", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getRemark_cd().size(); i++) {
                values.put("ID", data.getRemark_cd().get(i));
                values.put("REMARKS", data.getRemark().get(i));

                db.insert("SUP_PSS_REMARKS", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.toString());
        }
    }

    public ArrayList<RemarkGetterSetter> getRemarksData() {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<RemarkGetterSetter> list = new ArrayList<RemarkGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from SUP_PSS_REMARKS ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    RemarkGetterSetter sb = new RemarkGetterSetter();
                    sb.setRemark_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ID")));
                    sb.setRemark(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REMARKS")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public void updateCoverageCheckoutStatus(String storeid, String visitdate, String status) {
        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_STATUS, status);
            db.update(CommonString.TABLE_COVERAGE_DATA, values, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        } catch (Exception e) {

        }
    }


    //check if table is empty
    public boolean isOpeningDataFilledChecklist(String store_cd, String process_id) {
        boolean filled = false;
        Cursor dbcursor = null;
        // values1.put(CommonString.KEY_ANSWER_ID, data.get(save_listDataHeader.get(i)).get(j).getAnswer_id());
        try {
            dbcursor = db.rawQuery("SELECT  DISTINCT ANSWER_ID FROM INSERT_QUESTIONNAIR_CHILD_DATA WHERE STORE_ID = '" + store_cd + "' AND PROCESS_ID = '" + process_id + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("ANSWER_ID")).equalsIgnoreCase("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }

}
