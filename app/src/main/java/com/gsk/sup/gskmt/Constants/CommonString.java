package com.gsk.sup.gskmt.Constants;

import android.os.Environment;

public class CommonString {


    // preferenec keys
    public static final String DATA_DELETE_ALERT_MESSAGE = "Saved data will be lost - Do you want to continue?";
    public static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/GSK_MT_SUP_IMAGES/";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_REMEMBER = "remember";
    public static final String KEY_RIGHT_NAME = "right_name";
    public static final String KEY_PATH = "path";
    public static final String KEY_VERSION = "APP_VERSION";
    public static final String KEY_GEO_TAG = "GEO_TAG";
    public static final String KEY_GEO_Y = "Y";
    public static final String TABLE_INSERT_SKU_DATA = "SKU_ASSET_DATA";
    public static final String URL_Notice_Board = "http://gskmtm.parinaam.in/notice/notice.html";
    public static final String TABLE_INSERT_SKU_PACKD = "SKU_PACKD_DATA";
    public static final String TABLE_INSERT_SECONDARY_SKU_DATA = "SECONDARY_SKU_DATA";
    // public static final String KEY_APPVERSION = "1.1";

    public static final String KEY_DATE = "date";
    public static final String MID = "MID";
     public static final String KEY_Y = "Y";
    public static final String KEY_P = "P";
    public static final String KEY_D = "D";
    public static final String KEY_U = "U";
    public static final String KEY_C = "C";
    public static final String KEY_L = "Leave";
    public static final String KEY_N = "NOT_VISITED";
    public static final String KEY_INVALID = "INVALID";
    public static final String STORE_STATUS_LEAVE = "L";
    public static final String KEY_VALID = "Valid";
    public static final String KEY_STORE_IN_TIME = "Store_in_time";
    public static final String KEY_STORE_OUT_TIME = "Store_out_time";

    public static final String KEY_IMAGE_PATH1 = "IMAGE_PATH1";
    public static final String KEY_IMAGE_PATH2 = "IMAGE_PATH2";
    public static final String KEY_IMAGE_PATH3 = "IMAGE_PATH3";
    public static final String TABLE_INSERT_GEO_TAG = "INSERT_GEO_TAG_DATA";
    public static final String TABLE_GEO_TAG_MAPPING = "GEOTAG_STORE";
    public static final String TABLE_GEOTAG_CITY = "GEO_TAG_CITY";
    public static final String TABLE_QUESTION_ANSWER = "QUESTION_ANSWER_TABLE";
    public static final String TABLE_QUESTION_ANSWER_STOCKAFTER = "QUESTION_ANSWER_TABLE_STOCKAFTER";
    public static final String TABLE_PROMOTION_DATA = "PROMOTION_DATA";
    public static final String TABLE_TEMP_QUESTION_ANSWER = "TEMP_QUESTION_ANSWER_TABLE";
    public static final String TABLE_TOT_AFTER = "TOT_AFTER";
    public static final String TABLE_TOT_BEFORE = "TOT_BEFORE";

    // webservice constants
    public static final String KEY_SUCCESS = "Success";
    public static final String KEY_FAILURE = "Failure";
    public static final String KEY_FALSE = "False";
    public static final String KEY_CHANGED = "Changed";

    public static final String KEY_NO_DATA = "NODATA";

    public static final String KEY_MID = "MID";
    public static final String KEY_SKU_STOCK = "SKU_STOCK";
    public static final String KEY_SKU_ID = "SKU_ID";
    public static final String KEY_SKUFACEUP = "SKUFACEUP";
    public static final String KEY_SKUNAME = "SKUNAME";

    public static final String KEY_DOM1 = "DOM1";
    public static final String KEY_DOM2 = "DOM2";
    public static final String KEY_DOM3 = "DOM3";
    public static final String KEY_DOM4 = "DOM4";
    public static final String KEY_DOM5 = "DOM5";

    public static final String KEY_ENTRY = "ENTRY";
    public static final String KEY_IMAGE = "IMAGE";


    public static final String KEY_IMAGE1 = "BEFORE_IMAGE1";
    public static final String KEY_IMAGE2 = "BEFORE_IMAGE2";
    public static final String KEY_IMAGE3 = "BEFORE_IMAGE3";
    public static final String KEY_IMAGE4 = "BEFORE_IMAGE4";


    public static final String KEY_IMAGE5 = "AFTER_IMAGE1";
    public static final String KEY_IMAGE6 = "AFTER_IMAGE2";
    public static final String KEY_IMAGE7 = "AFTER_IMAGE3";
    public static final String KEY_IMAGE8 = "AFTER_IMAGE4";

    public static final String KEY_NUMOFWINDOWS = "NO_OF_WINDOWS";
    public static final String KEY_LENGTH = "LENGTH";
    public static final String KEY_HEIGHT = "HEIGHT";

    public static final String KEY_IMAGE_SAVE_STATUS = "Save_Status";
    public static final String KEY_REMARK = "REMARK";

    public static final String KEY_SOD_ID = "SOD_ID";

    public static final String KEY_ASSETS_ID = "ASSETS_ID";
    public static final String KEY_ASSETS = "ASSET";
    public static final String KEY_COMMONID = "COMMONID";
    public static final String KEY_STOCK_REASON_ID = "STOCKREASON_ID";
    public static final String KEY_STOCK_REASON = "STOCK_REASON";

    // location
    public static final String TABLE_LOCATION_STATUS = "LOCATION_STATUS";
    public static final String KEY_NETWORK_STATUS = "NETWORK_STATUS";
    public static final String KEY_CURRENT_TIME = "CURRENT_TIME";

    public static final String NAMESPACE = "http://tempuri.org/";
    public static final String URL = "http://gskmtm.parinaam.in/GskmtService.asmx";

//	public static final String URL = "http://gskmt.parinaam.in/gsk.asmx";
    //public static final String local_URL = "http://10.200.20.133/GSK_GT_SERVICE/GSKWebservice.asmx";

    public static final String METHOD_LOGIN = "UserLoginDetail_SUP";
    public static final String SOAP_ACTION_LOGIN = "http://tempuri.org/"
            + METHOD_LOGIN;

    public static final String METHOD_UPLOAD_STORE_STATUS = "InsertUserCurrentLocation";
    public static final String SOAP_ACTION_UPLOAD_STORE_STATUS = "http://tempuri.org/"
            + METHOD_UPLOAD_STORE_STATUS;

    // public static final String METHOD_NAME_JCP = "DownLoadStoreJcp";
    public static final String METHOD_NAME_JCP = "DownLoadStoreJcp_Special";

    public static final String METHOD_NAME_UNIVERSAL_DOWNLOAD = "Download_Universal";

    public static final String METHOD_NAME_PJP_STORE = "DownloadStoreEmployeeWise";

    public static final String METHOD_Checkout_StatusNew_deviation = "Upload_Store_ChecOut_Status_Deviation";

    public static final String SOAP_ACTION_Checkout_StatusNew_deviation = "http://tempuri.org/"
            + METHOD_Checkout_StatusNew_deviation;


    public static final String SOAP_ACTION_UNIVERSAL = "http://tempuri.org/"
            + METHOD_NAME_UNIVERSAL_DOWNLOAD;

    public static final String SOAP_ACTION_PJP = "http://tempuri.org/"
            + METHOD_NAME_PJP_STORE;

    public static final String METHOD_NAME_STORE_LAYOUT = "DownLoad_Store_Layout";
    public static final String SOAP_ACTION_STORE_LAYOUT = "http://tempuri.org/"
            + METHOD_NAME_STORE_LAYOUT;

    public static final String METHOD_NAME_STORE_SIZE = "DownLoad_Store_Size";
    public static final String SOAP_ACTION_STORE_SIZE = "http://tempuri.org/"
            + METHOD_NAME_STORE_SIZE;

    public static final String METHOD_NAME_UPLOAD_GEOTAG_IMAGE = "Upload_StoreGeoTag_IMAGES";
    public static final String SOAP_ACTION_UPLOAD_GEOTAG_IMAGE = "http://tempuri.org/"
            + METHOD_NAME_UPLOAD_GEOTAG_IMAGE;

    public static final String METHOD_NAME_PLANOGRAM_IMAGES = "DownLoad_PlanoGramMapping";
    public static final String SOAP_ACTION_PLANOGRAM_IMAGES = "http://tempuri.org/"
            + METHOD_NAME_PLANOGRAM_IMAGES;

    public static final String METHOD_NAME_delete_coverage = "DeleteChekoutAndCoverage";
    public static final String SOAP_ACTION_delete_coverage = "http://tempuri.org/"
            + METHOD_NAME_delete_coverage;

    public static final String METHOD_Checkout_StatusNew = "Upload_Store_ChecOut_Status";
    public static final String METHOD_STORE_VISIT = "STORE_VISITNEW";
    public static final String SOAP_ACTION_Checkout_StatusNew = "http://tempuri.org/"
            + METHOD_Checkout_StatusNew;

    public static final String SOAP_ACTION_STORE_VISIT = "http://tempuri.org/"
            + METHOD_STORE_VISIT;

    // String value for promotional master

    public static final String METHOD_NAME_DownLoad_Promotional_Master = "DownLoad_Promotional_Master";
    public static final String SOAP_ACTION_Promotional_Master = "http://tempuri.org/"
            + METHOD_NAME_DownLoad_Promotional_Master;


    // String value for Marchendiser code and name
    public static final String METHOD_NAME_ALL_EMP = "GetEmployee";
    public static final String SOAP_ACTION_ALL_EMP = "http://tempuri.org/"
            + METHOD_NAME_ALL_EMP;

    public static final String METHOD_NAME_WINDOW_SIZE_STATUS = "Download_WindowSize_Status";
    public static final String SOAP_ACTION_WINDOW_SIZE = "http://tempuri.org/"
            + METHOD_NAME_WINDOW_SIZE_STATUS;

    // String value for SKU master

    public static final String METHOD_NAME_DOWNLOAD_SKU_MASTER = "DownLoad_SKU_Master";
    public static final String SOAP_ACTION_DOWNLAOD_SKU_MASTER = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_SKU_MASTER;

    // string value for Master

    public static final String METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER = "DownLoad_NonWorkingReason_Master";

    public static final String METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER_subReason = "DownLoad_NonWorkingSubReason_ByReason";

    public static final String SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER;

    public static final String SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER_SUBREASON = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER_subReason;

    public static final String METHOD_NAME_DOWNLOAD_sku_mapping = "DownLoad_SKU_By_Mapping";
    public static final String SOAP_ACTION_DOWNLAOD_sku_mapping = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_sku_mapping;

    // string value for DowloadComplaince

    public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE = "DowloadComplaince";
    public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_COMPLIANCE;

    // STRING VALUE FOR DowloadPromotionWithComplainceByMapping

    public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING = "DowloadPromotionWithComplainceByMapping";
    public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING;

    public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING_SPECIAL = "DownLoad_PROMOTION_COMPLIANCE_MAPPING_SPECIAL";
    public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING_SPECIAL = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING_SPECIAL;

    public static final String METHOD_VERTICAL_MASTER = "DOWLOAD_VERTICALMASTER";
    public static final String SOAP_ACTION_VERTICAL_MASTER = "http://tempuri.org/"
            + METHOD_VERTICAL_MASTER;

    public static final String METHOD_BRAND_MASTER = "DOWLOAD_BRANDMASTER";
    public static final String SOAP_ACTION_BRAND_Master = "http://tempuri.org/"
            + METHOD_BRAND_MASTER;

    public static final String METHOD_VERTICAL_BRAND_MAPPING = "DOWLOAD_VERTICALBRANDMAPPING";
    public static final String SOAP_ACTION_VERTICAL_BRAND_Mapping = "http://tempuri.org/"
            + METHOD_VERTICAL_BRAND_MAPPING;

    public static final String METHOD_VERTICAL_SKU_MAPPING = "SKUBRANDMAPPINGDownload";
    public static final String SOAP_ACTION_VERTICAL_SKU_Mapping = "http://tempuri.org/"
            + METHOD_VERTICAL_SKU_MAPPING;

    public static final String METHOD_CATEGORY_MASTER = "DOWLOAD_CATEGORYMASTER";
    public static final String SOAP_ACTION_CATEGORY_MASTER = "http://tempuri.org/"
            + METHOD_CATEGORY_MASTER;

    public static final String METHOD_CATEGORY_SKU_MAPPING = "CATEGORYSKUMAPPINGDownload";
    public static final String SOAP_ACTION_CATEGORY_SKU_MAPPING = "http://tempuri.org/"
            + METHOD_CATEGORY_SKU_MAPPING;

    public static final String METHOD_CATEGORY_VERTICAL_MAPPING = "CATEGORYVERTICALMAPPINGDownload";
    public static final String SOAP_ACTION_CATEGORY_VERTICAL_MAPPING = "http://tempuri.org/"
            + METHOD_CATEGORY_VERTICAL_MAPPING;

    public static final String METHOD_CATEGORY_POSM_MAPPING = "POSMBRANDMAPPINGDownload";
    public static final String SOAP_ACTION_POSM_MAPPING = "http://tempuri.org/"
            + METHOD_CATEGORY_POSM_MAPPING;

    public static final String METHOD_SKU_MASTER_DOWNLOAD = "SKU_MASTERDownload";
    public static final String SOAP_ACTION_SKU_MASTER = "http://tempuri.org/"
            + METHOD_SKU_MASTER_DOWNLOAD;

    public static final String METHOD_COMPANY_MASTER_DOWNLOAD = "COMPANY_MASTERDownload";
    public static final String SOAP_ACTION_COMPANY_MASTER = "http://tempuri.org/"
            + METHOD_COMPANY_MASTER_DOWNLOAD;

    // Shahab
    public static final String METHOD_NONSKU_REASON = "DOWLOAD_NON_STOCK_REASON_MASTER";
    public static final String SOAP_ACTION_NONSKU_REASON = "http://tempuri.org/"
            + METHOD_NONSKU_REASON;

    public static final String METHOD_SKU_FOCUS_DOWNLOAD = "SKUAVALIBILITY_FOCUS";
    public static final String SOAP_ACTION_SKU_FOCUS = "http://tempuri.org/"
            + METHOD_SKU_FOCUS_DOWNLOAD;

    public static final String METHOD_MAPPING_COMPETITOR = "DOWLOAD_MAPPINGCOMPEPITORBRAND";
    public static final String SOAP_ACTION_MAPPING_COMPETITOR = "http://tempuri.org/"
            + METHOD_MAPPING_COMPETITOR;

    public static final String METHOD_POSM_MASTER_DOWNLOAD = "DOWLOAD_POSMMASTER";
    public static final String SOAP_ACTION_POSM_MASTER_DOWNLOAD = "http://tempuri.org/"
            + METHOD_POSM_MASTER_DOWNLOAD;

    // Upload Coverage
    public static final String METHOD_UPLOAD_DR_STORE_COVERAGE = "UPLOAD_COVERAGE";
    public static final String METHOD_UPLOAD_DR_STORE_COVERAGE_LOC = "UPLOAD_STORE_COVERAGE_WSC_SUP";

    public static final String METHOD_UPLOAD_COVERAGE_DEVIATION = "UPLOAD_COVERAGE_DEVIATION";

    public static final String SOAP_ACTION_UPLOAD_COVERAGE_DEVIATION = "http://tempuri.org/" +
            "UPLOAD_COVERAGE_DEVIATION";

    public static final String SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE = "http://tempuri.org/"
            + METHOD_UPLOAD_DR_STORE_COVERAGE_LOC;

    // public static final String METHOD_UPLOAD_DR_STORE_DATA =
    // "Upload_Store_Layout";
    public static final String METHOD_UPLOAD_DR_STORE_DATA = "Upload_Store_Layout_V1";
    public static final String SOAP_ACTION_UPLOAD_DR_STORE_DATA = "http://tempuri.org/"
            + METHOD_UPLOAD_DR_STORE_DATA;

    public static final String METHOD_UPLOAD_DR_RETAILER_INFO = "Upload_DR_STORE_PAYMENT";
    public static final String SOAP_ACTION_UPLOAD_DR_RETAILER_INFO = "http://tempuri.org/"
            + METHOD_UPLOAD_DR_RETAILER_INFO;

    public static final String METHOD_UPLOAD_QUESTION = "QuestionNair Data";
    public static final String METHOD_UPLOAD_ASSET = " Data";

    public static final String METHOD_PRIMARY_WINDOW_IMAGES = "UPLOAD_Store_Image";

    public static final String METHOD_UPLOAD_PRIMARY_WINDOW_IMAGES = "GetImageNew";

    public static final String SOAP_ACTION_UPLOAD_PRIMARY_WINDOW_IMAGES = "http://tempuri.org/"
            + METHOD_UPLOAD_PRIMARY_WINDOW_IMAGES;

    public static final String SOAP_ACTION_PRIMARY_WINDOW_IMAGES = "http://tempuri.org/"
            + METHOD_PRIMARY_WINDOW_IMAGES;

    public static final String METHOD_UPLOAD_STOCK_XML_DATA = "DrUploadXml";
    public static final String SOAP_ACTION_UPLOAD_ASSET = "http://tempuri.org/"
            + METHOD_UPLOAD_ASSET;

    public static final String SOAP_ACTION_UPLOAD_ASSET_XMLDATA = "http://tempuri.org/"
            + METHOD_UPLOAD_STOCK_XML_DATA;

    public static final String METHOD_UPLOAD_SEC_SKU = "Upload_Stock_Availiablity_SECONDARY";
    public static final String SOAP_ACTION_UPLOAD_SEC_SKU = "http://tempuri.org/"
            + METHOD_UPLOAD_SEC_SKU;

    public static final String METHOD_UPLOAD_PCKGE_SKU = "Upload_DR_CORE_SKU_PACKAGING";
    public static final String SOAP_ACTION_UPLOAD_PCKGE_SKU = "http://tempuri.org/"
            + METHOD_UPLOAD_PCKGE_SKU;

    public static final String METHOD_UPLOAD_POSM = "Upload_Posm_Deployed";
    public static final String SOAP_ACTION_UPLOAD_POSM = "http://tempuri.org/"
            + METHOD_UPLOAD_POSM;

    public static final String METHOD_Upload_Posm_Deployed_Deviation = "Upload_Posm_Deployed_Deviation";
    public static final String SOAP_ACTION_Upload_Posm_Deployed_Deviation = "http://tempuri.org/"
            + METHOD_Upload_Posm_Deployed_Deviation;

    public static final String METHOD_UPLOAD_COMPLIANCE = "Upload_Promotion_WindowExists";
    public static final String SOAP_ACTION_COMPLIANCE = "http://tempuri.org/"
            + METHOD_UPLOAD_COMPLIANCE;

    public static final String METHOD_UPLOAD_COMPLIANCE_SPECIAL = "Upload_Promotion_Special";
    public static final String SOAP_ACTION_COMPLIANCE_SPECIAL = "http://tempuri.org/"
            + METHOD_UPLOAD_COMPLIANCE_SPECIAL;

    public static final String METHOD_NON_WORKING_MASTER = "DOWLOAD_NONWORKINGREGIONMASTER";
    public static final String SOAP_ACTION_NONWORKING = "http://tempuri.org/"
            + METHOD_NON_WORKING_MASTER;

    public static final String METHOD_SET_COVERAGE_STATUS = "Upload_Status_SUP";
    public static final String SOAP_ACTION_SET_COVERAGE_STATUS = "http://tempuri.org/"
            + METHOD_SET_COVERAGE_STATUS;

    public static final String METHOD_SET_UPLOAD_GEODATA = "Upload_Store_Geo_Tag";
    public static final String SOAP_ACTION_UPLOAD_GEODATA = "http://tempuri.org/"
            + METHOD_SET_UPLOAD_GEODATA;

    // database

    public static final String TABLE_COVERAGE_DATA = "DR_STORE_COVERAGE";
    public static final String TABLE_COVERAGES_SOME_DATA = "SOME_STORE_COVERAGE_DATA";
    public static final String TABLE_TEMP_FLAG = "TEMP_FLAG";

    public static final String TABLE_PRIMNARY_WINDOW_IMG = "PRIMNARY_WINDOW_IMG";
    public static final String TABLE_PRIMNARY_WINDOW_SIZE = "PRIMNARY_WINDOW_SIZE";

    public static final String TABLE_PJP_COVERAGE_DATA = "PJP_COVERAGE_DATA";

    public static final String TABLE_PJP_DEVIATION = "PJP_DEVIATION";

    public static final String TABLE_PJP_DEVIATION_STORE = "PJP_DEVIATION_STORE";

    public static final String TABLE_NON_WORKING_REASON = "NON_WORKING_DATA";

    public static final String TABLE_NON_SKU_REASON = "NON_SKU_REASON";

    public static final String CREATE_TABLE_NON_SKU_REASON = "CREATE TABLE NON_SKU_REASON(STOCK_REASON VARCHAR, STOCK_REASON_ID VARCHAR)";
    // public static final String CREATE_TABLE_KEY_MODEL_DATA =
    // "CREATE TABLE KEY_MODEL_DATA (MID INTEGER, KEY_MODEL_NAME VARCHAR,KEY_MODEL_ID INTEGER,KEY_MODEL_QTNY INTEGER)";

    // CONSTANT FIELD NAMES FOR GTGSK


    public static final String TABLE_INSERT_HEADER_QUESTIONNAIR_DATA = "INSERT_QUESTIONNAIR_HEADER_DATA";
    public static final String TABLE_INSERT_CHILD_QUESTIONNAIR_DATA = "INSERT_QUESTIONNAIR_CHILD_DATA";

    // FOR JCP DOWNLOAD
    public static final String KEY_ID = "KEY_ID";

    public static final String KEY_PACKED_KEY = "PACKED_KEY";

    public static final String UNIQUE_KEY_ID = "UNIQUE_KEY_ID";
    public static final String KEY_STORE_ID = "STORE_ID";
    public static final String KEY_STORE_NAME = "STORE_NAME";
    public static final String KEY_STORE_IMAGE = "STORE_IMAGE";
    public static final String KEY_STORE_IMAGE_ALLOW = "IMAGE_ALLOW";
    public static final String KEY_STORE_LAYOUT = "LAYOUT";
    public static final String KEY_STORE_SIZE = "SIZE";
    public static final String KEY_ADDRESS = "ADDRESS";
    public static final String KEY_USER_ID = "USER_ID";
    //	public static final String KEY_CATEGORY_ID = "CATEGORY_ID";
    public static final String KEY_IN_TIME = "IN_TIME";
    public static final String KEY_OUT_TIME = "OUT_TIME";
    public static final String KEY_VISIT_DATE = "VISIT_DATE";
    public static final String KEY_PROCESS_ID = "PROCESS_ID";
    public static final String KEY_PROCESS_NAME = "PROCESS_NAME";
    public static final String KEY_STOCK = "STOCK";
    public static final String KEY_LATITUDE = "LATITUDE";
    public static final String KEY_LONGITUDE = "LONGITUDE";
    public static final String KEY_REASON_ID = "REASON_ID";
    public static final String KEY_SUB_REASON_ID = "SUB_REASON_ID";
    public static final String KEY_REASON = "REASON";
    public static final String KEY_STATUS = "STATUS";
    public static final String KEY_CHECKOUT_STATUS = "CHECKOUT_STATUS";

    public static final String KEY_CHANNEL = "channel";
    public static final String KEY_CLASSIFICATION = "classification";
    public static final String KEY_NO_OF_SHELFS = "shelfs";

    public static final String KEY_STORE_CD = "STORE_ID";
    public static final String KEY_STORE = "STORE";
    public static final String KEY_ADDRES = "CITY";
    public static final String KEY_USER_CITY = "CITY";
    public static final String KEY_EMP_CD = "EMP_ID";
    public static final String KEY_CURRENT_DATETIME = "VISIT_DATE";
    public static final String KEY_STAT = "STATUS";

    public static final String KEY_STOREVISITED = "STORE_VISITED";
    public static final String KEY_STOREVISITED_STATUS = "STORE_VISITED_STATUS";


    // FOR TABLE DOWNOAD_PROMOTION
    public static final String PROMOTION_CD = "PROMOTION_CD";
    public static final String PROMOTION_WINDOW = "PROMOTION_NAME";


    //FOR TABLE EMPLOYEE DETAILS
//	public static final String KEY_EMP_CD = "PROMOTION_CD";
    public static final String KEY_EMP_NAME = "EMP_NAME";


    // FOR TABLE SKU_MASTER
    public static final String SKU_CD = "SKU_CD";
    public static final String SKU_seq = "SKU_seq";
    public static final String KEY_CORE_SKU = "Core_Sku";
    public static final String is_competitor = "IS_COMPETITOR";
    public static final String region_id = "REGION_ID";
    public static final String storetype_id = "STORETYPE_ID";
    public static final String KEY_CATEGORY_ID = "CATEGORY_ID";
    public static final String KEY_CATEGORY_NAME = "CATEGORY_NAME";

    public static final String KEY_BRAND_CATEGORY_ID = "BRAND_CATEGORY_ID";
    public static final String KEY_BRAND_CATEGORY = "BRAND_CATEGORY";
    public static final String KEY_MONTH_HERO = "MONTH_HERO";

    public static final String BRAND_SEQ = "BRAND_SEQ";
    public static final String SKU = "SKU";
    // FOR TBALE NON_WORKING_REASON
    public static final String REASON_ID = "REASON_ID";
    public static final String REASON_VALID = "REASON_VALID";
    public static final String REASON = "REASON";

    public static final String SUB_REASON_ID = "SUB_REASON_ID";
    public static final String SUB_REASON = "SUB_REASON";


    // FOR TABLE COMPLIANCE_MASTER
    public static final String COMPLIANCE_ID = "COMPLIANCE_CD";
    public static final String KEY_WINDOW_STATUS = "WINDOW_STATUS";
    public static final String COMPLIANCE = "COMPLIANCE";
    // FOR TABLE COMPLIANCE_MAPPING_MASTER
    public static final String COMPLIANCE_CD = "COMPLIANCE_CD";
    public static final String PROMOTIONCD = "PROMOTION_CD";
    // Posm
    public static final String KEY_POSM_CD = "POSM_CD";
    public static final String KEY_POSM = "POSM";
    // POSM Master
    public static final String METHOD_NAME_DOWNLOAD_POSM_MASTER = "POSM";
    public static final String SOAP_ACTION_DOWNLAOD_POSM_MASTER = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_POSM_MASTER;
    // geo tag
    public static final String METHOD_NAME_DOWNLOAD_GEO = "DownLoadStoreByUser";
    public static final String SOAP_ACTION_DOWNLAOD_GEO = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_GEO;

    // CREATING TABLE NAMES GTGSK

    public static final String KEY_NORMAL = "Normal";
    public static final String KEY_SPECIAL = "Special";
    public static final String KEY_PERSON_NAME = "Name";
    public static final String KEY_CONTACT_NUMBER = "Number";

    public static final String TABLE_STORE_DETAIL = "STORE_MASTER";
    public static final String TABLE_STORE_DATA = "STORE_DATA";
    public static final String TABLE_STORE_LAYOUT = "LAYOUT_MASTER";
    public static final String TABLE_STORE_SIZE = "SIZE_MASTER";
    public static final String TABLE_PROMOTION_NAME_GTGSK = "DOWNLOAD_PROMOTION";


    public static final String TABLE_EMPLOYEE_DATA = "EMPLOYEE_DATA";

    public static final String TABLE_SKU_MASTER_GTGSK = "SKUMASTER";
    public static final String TABLE_NON_WORKING_MASTER_GTGSK = "NON_WORKING_REASONMASTER";
    public static final String TABLE_NON_WORKING_MASTER_GTGSK_SUBREASON = "NON_WORKING_REASONMASTER_SUBREASON";

    public static final String TABLE_COMPLIANCE_MASTER_GTGSK = "COMPLIANCE_MASTER";
    public static final String TABLE_WINDOW_STATUS = "WINDOW_STATUS";
    public static final String TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK = "COMPLIANCE_MAPPING_MASTER";
    public static final String TABLE_COMPLIANCE_MAPPING_SPECIAL = "COMPLIANCE_MAPPING_SPECIAL";
    public static final String TABLE_POSM_MASTER_GTGSK = "POSMMASTER";
    public static final String TABLE_SKU_MAPPING = "SKU_MAPPING";
    public static final String TABLE_PLANOGRAM_MAPPING_GTGSK = "PLANOGRAM_MAPPING";
    // CREATING TABLE FOR ABOVE TABLE NAMES
    // Tables

    public static final String CREATE_TABLE_STORE = "CREATE TABLE "
            + TABLE_STORE_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_MID + " VARCHAR,"
            + KEY_STORE_ID + " VARCHAR," + KEY_IMAGE + " VARCHAR,"
            + KEY_PERSON_NAME + " VARCHAR," + KEY_CONTACT_NUMBER + " VARCHAR,"
            + KEY_STORE_SIZE + " VARCHAR," + KEY_STORE_LAYOUT + " VARCHAR)";

    public static final String KEY_AVAILABILITY = "QUANTITY";
    public static final String TABLE_INSERT_COMPLIANCE_DATA = "COMPLIANCE_DATA_INSERTED";
    public static final String TABLE_INSERT_SUBCOMPLIANCE_DATA = "SUBCOMPLIANCE_DATA_INSERTED";
    public static final String COMMONID = "COMMONID";
    public static final String CATEGORY_BRANDID = "CAT_BRANDID";
    public static final String CREATE_TABLE_COMPLIANCE_DATA = "CREATE TABLE "
            + TABLE_INSERT_COMPLIANCE_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
            + KEY_STORE_ID + " VARCHAR," + PROMOTION_CD + " VARCHAR,"
            + PROMOTION_WINDOW + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR,"
            + KEY_BRAND_CATEGORY + " VARCHAR," + CATEGORY_BRANDID + " VARCHAR,"
            + KEY_MONTH_HERO + " VARCHAR," + KEY_IMAGE + " VARCHAR)";

    public static final String CREATE_TABLE_SUBCOMPLIANCE_DATA = "CREATE TABLE "
            + TABLE_INSERT_SUBCOMPLIANCE_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COMMONID
            + " INTEGER,"
            + COMPLIANCE_ID
            + " VARCHAR,"
            + KEY_STORE_ID
            + " VARCHAR,"
            + COMPLIANCE + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR)";

    public static final String TABLE_INSERT_COMPLIANCE_DATA_SPECIAL = "COMPLIANCE_DATA_INSERTED_SPECIAL";
    public static final String TABLE_INSERT_SUBCOMPLIANCE_DATA_SPECIAL = "SUBCOMPLIANCE_DATA_INSERTED_SPECIAL";

    public static final String CREATE_TABLE_COMPLIANCE_DATA_SPECIAL = "CREATE TABLE "
            + TABLE_INSERT_COMPLIANCE_DATA_SPECIAL
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + MID
            + " INTEGER,"
            + KEY_STORE_ID
            + " VARCHAR,"
            + PROMOTION_CD
            + " VARCHAR,"
            + PROMOTION_WINDOW
            + " VARCHAR,"
            + KEY_AVAILABILITY
            + " VARCHAR,"
            + KEY_IMAGE + " VARCHAR)";

    public static final String CREATE_TABLE_SUBCOMPLIANCE_DATA_SPECIAL = "CREATE TABLE "
            + TABLE_INSERT_SUBCOMPLIANCE_DATA_SPECIAL
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COMMONID
            + " INTEGER,"
            + COMPLIANCE_ID
            + " VARCHAR,"
            + KEY_STORE_ID
            + " VARCHAR,"
            + COMPLIANCE + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR)";

    public static final String CREATE_TABLE_SKU_DATA = "CREATE TABLE "
            + TABLE_INSERT_SKU_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
            + KEY_SKU_STOCK + " VARCHAR," + KEY_SKU_ID + " VARCHAR,"
            + KEY_SKUNAME + " VARCHAR," + KEY_CORE_SKU + " VARCHAR," + KEY_DOM1
            + " VARCHAR," + KEY_DOM2 + " VARCHAR," + KEY_DOM3 + " VARCHAR,"
            + is_competitor + " VARCHAR," + KEY_SKUFACEUP + " VARCHAR)";

    public static final String CREATE_TABLE_SKU_PACKD = "CREATE TABLE "
            + TABLE_INSERT_SKU_PACKD + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
            + KEY_SKU_ID + " VARCHAR," + KEY_SKUNAME + " VARCHAR," + KEY_DOM1
            + " VARCHAR," + KEY_DOM2 + " VARCHAR," + KEY_DOM3 + " VARCHAR,"
            + KEY_DOM4 + " VARCHAR," + KEY_DOM5 + " VARCHAR)";

    public static final String CREATE_TABLE_SECONDARY_SKU_DATA = "CREATE TABLE "
            + TABLE_INSERT_SECONDARY_SKU_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + MID
            + " INTEGER,"
            + KEY_SKU_STOCK
            + " VARCHAR,"
            + KEY_SKU_ID
            + " VARCHAR,"
            + KEY_SKUNAME + " VARCHAR," + KEY_SKUFACEUP + " VARCHAR)";


    public static final String CREATE_TABLE_TEMP = "CREATE TABLE "
            + TABLE_TEMP_FLAG
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_ID
            + " VARCHAR,"
            + KEY_REASON_ID
            + " VARCHAR,"
            + KEY_REASON
            + " VARCHAR,"
            + KEY_VISIT_DATE
            + " VARCHAR)";


    public static final String CREATE_TABLE_PRIMARY_WINDOW_IMAGES = "CREATE TABLE "
            + TABLE_PRIMNARY_WINDOW_IMG
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_IMAGE1
            + " VARCHAR,"
            + KEY_IMAGE2
            + " VARCHAR,"
            + KEY_IMAGE3
            + " VARCHAR,"
            + KEY_STORE_ID
            + " VARCHAR)";


    public static final String CREATE_TABLE_PRIMARY_WINDOW_SIZE = "CREATE TABLE "
            + TABLE_PRIMNARY_WINDOW_SIZE
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_NUMOFWINDOWS
            + " VARCHAR,"
            + KEY_LENGTH
            + " VARCHAR,"
            + KEY_HEIGHT
            + " VARCHAR,"
            + KEY_STORE_ID
            + " VARCHAR,"
            + KEY_VISIT_DATE
            + " VARCHAR)";


    public static final String CREATE_TABLE_SKU_MAPPING = "CREATE TABLE "
            + TABLE_SKU_MAPPING + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + SKU_CD + " VARCHAR,"
            + storetype_id + " VARCHAR," + SKU_seq + " VARCHAR," + KEY_CORE_SKU
            + " VARCHAR," + region_id + " VARCHAR)";

    // FOR JCP GT GSK

    public static final String CREATE_TABLE_STORE_MASTER = "CREATE TABLE "
            + TABLE_STORE_DETAIL + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_CD
            + " VARCHAR," + KEY_STORE + " VARCHAR," + KEY_ADDRES + " VARCHAR,"
            + KEY_CHANNEL + " VARCHAR," + KEY_CLASSIFICATION + " VARCHAR,"
            + KEY_NO_OF_SHELFS + " VARCHAR," + KEY_USER_CITY + " VARCHAR,"
            + KEY_EMP_CD + " VARCHAR," + KEY_CURRENT_DATETIME + " VARCHAR,"
            + region_id + " VARCHAR," + KEY_IMAGE + " VARCHAR,"
            + KEY_STORE_SIZE + " VARCHAR," + KEY_STORE_LAYOUT + " VARCHAR,"
            + KEY_IMAGE_SAVE_STATUS + " VARCHAR," + storetype_id + " VARCHAR,"
            + KEY_CHECKOUT_STATUS + " VARCHAR," + KEY_NORMAL + " VARCHAR,"
            + KEY_PERSON_NAME + " VARCHAR," + KEY_CONTACT_NUMBER + " VARCHAR,"
            + KEY_SPECIAL + " VARCHAR," + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_STAT + " VARCHAR)";
    public static final String TABLE_INSERT_ADDTIONAL_DETAILS = "ADDTIONAL_INFO";
    public static final String TABLE_INSERT_PERFORMANCE_DETAILS = "NON_ACHIVEMENT_PERFORMANCE_DETAILS";


    public static final String TABLE_INSERT_STOCK_TOT = "STOCK_TOT";
    public static final String TABLE_INSERT_SALES_STOCK = "SALES_STOCK";

    public static final String TABLE_INSERT_COMPETITION_INFO = "COMPETITION_INFO";

    public static final String TABLE_INSERT_AFTER_ADDTIONAL_DETAILS = "AFTER_ADDTIONAL_INFO";
    public static final String KEY_BRAND_ID = "BRAND_ID";
    public static final String KEY_TYPE = "TYPE";
    public static final String KEY_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_BRAND = "BRAND";
    public static final String KEY_DISPLAY_ID = "DISPLAY_ID";
    public static final String KEY_DISPLAY = "DISPLAY";
    public static final String KEY_QUANTITY = "QUANTITY";
    public static final String KEY_NON_ACHIVEMENT_REASON_ID = "REASON_ID";
    public static final String KEY_NON_ACHIVEMENT_REASON = "REASON";
    public static final String KEY_ADDITIONAL_YESYorNO = "ADDITIONALYESORNO";
    public static final String KEY_QSUB_CATEGORY_ID = "QSUB_CATEGORY_ID";
    public static final String KEY_QSUB_CATEGORY = "QSUB_CATEGORY";

    public static final String KEY_QUESTION_ID = "QUESTION_ID";
    public static final String KEY_QUESTION = "QUESTION";

    public static final String KEY_ANSWER_ID = "ANSWER_ID";
    public static final String KEY_ANSWER = "ANSWER";


    public static final String KEY_AFTER_QUANTITY = "AFTER_QUANTITY";
    public static final String KEY_AFTER_STOCK_COUNT = "AFTER_STOCK_COUNT";

    public static final String KEY_BEFORE_QUANTITY = "BEFORE_QUANTITY";
    public static final String KEY_BEFORE_STOCK_COUNT = "BEFORE_STOCK_COUNT";
    public static final String KEY_TARGER_QUANTITY = "TARGER_QUANTITY";


    public static final String CREATE_TABLE_ADDITIONAL_DETAILS = "CREATE TABLE "
            + TABLE_INSERT_ADDTIONAL_DETAILS + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STORE_ID + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR," + KEY_BRAND + " VARCHAR,"
            + KEY_DISPLAY_ID + " VARCHAR,"
            + KEY_DISPLAY + " VARCHAR," + KEY_QUANTITY + " VARCHAR," + KEY_IMAGE + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_ADDITIONAL_YESYorNO + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR)";


    public static final String CREATE_TABLE_NON_ACHIVEMENT_DETAILS = "CREATE TABLE "
            + TABLE_INSERT_PERFORMANCE_DETAILS + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STORE_ID + " INTEGER,"
            + KEY_NON_ACHIVEMENT_REASON_ID + " INTEGER,"
            + KEY_VISIT_DATE + " VARCHAR,"
            + KEY_NON_ACHIVEMENT_REASON + " VARCHAR)";


    public static final String CREATE_TABLE_STOCK_TOT = "CREATE TABLE "
            + TABLE_INSERT_STOCK_TOT + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STORE_ID + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR," + KEY_BRAND + " VARCHAR,"
            + KEY_DISPLAY_ID + " VARCHAR,"
            + KEY_SKU_ID + " VARCHAR," + KEY_QUANTITY + " VARCHAR," + UNIQUE_KEY_ID + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_SKUNAME + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR)";


    public static final String CREATE_TABLE_SALES_STOCK = "CREATE TABLE "
            + TABLE_INSERT_SALES_STOCK + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STORE_ID + " VARCHAR,"

            + KEY_BRAND_ID + " VARCHAR," + KEY_BRAND + " VARCHAR,"
            + KEY_SKU_ID + " VARCHAR," + KEY_QUANTITY + " INTEGER,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_SKUNAME + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR,"
            + KEY_USER_ID + " VARCHAR)";


    public static final String CREATE_TABLE_COMPETITION_INFO = "CREATE TABLE "
            + TABLE_INSERT_COMPETITION_INFO + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STORE_ID + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR," + KEY_BRAND + " VARCHAR,"
            + KEY_DISPLAY_ID + " VARCHAR,"
            + KEY_DISPLAY + " VARCHAR," + KEY_QUANTITY + " VARCHAR," + KEY_IMAGE + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR)";

	
	/*public static final String CREATE_TABLE_AFTER_ADDITIONAL_DETAILS = "CREATE TABLE "
			+ TABLE_INSERT_AFTER_ADDTIONAL_DETAILS + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STORE_ID + " VARCHAR,"
			+ KEY_BRAND_ID + " VARCHAR," + KEY_BRAND + " VARCHAR,"
			+ KEY_DISPLAY_ID +" VARCHAR,"
			+ KEY_DISPLAY + " VARCHAR," + KEY_QUANTITY + " VARCHAR," + KEY_IMAGE + " VARCHAR,"
			+ KEY_CATEGORY_ID + " VARCHAR)";*/


    public static final String CREATE_TABLE_AFTER_ADDITIONAL_DETAILS = "CREATE TABLE "
            + TABLE_INSERT_AFTER_ADDTIONAL_DETAILS + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STORE_ID + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR," + KEY_BRAND + " VARCHAR,"
            + KEY_DISPLAY_ID + " VARCHAR,"
            + KEY_DISPLAY + " VARCHAR," + KEY_QUANTITY + " VARCHAR," + KEY_IMAGE + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_ADDITIONAL_YESYorNO + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR)";


    public static final String CREATE_TABLE_TOT_AFTER = "CREATE TABLE "
            + TABLE_TOT_AFTER + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_AFTER_QUANTITY + " VARCHAR,"
            + KEY_DISPLAY_ID + " VARCHAR,"
            + KEY_AFTER_STOCK_COUNT + " VARCHAR,"
            + KEY_STORE_ID + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_IMAGE1 + " VARCHAR,"
            + KEY_IMAGE2 + " VARCHAR,"
            + KEY_IMAGE3 + " VARCHAR,"
            + KEY_DISPLAY + " VARCHAR,"
            + KEY_TARGER_QUANTITY + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR,"
            + KEY_BRAND + " VARCHAR,"
            + UNIQUE_KEY_ID + " VARCHAR,"
            + KEY_TYPE + " VARCHAR,"
            + KEY_IMAGE_URL + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR)";


    public static final String CREATE_TABLE_TOT_BEFORE = "CREATE TABLE "
            + TABLE_TOT_BEFORE + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_BEFORE_QUANTITY + " VARCHAR,"
            + KEY_DISPLAY_ID + " VARCHAR,"
            + KEY_BEFORE_STOCK_COUNT + " VARCHAR,"
            + KEY_STORE_ID + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_IMAGE1 + " VARCHAR,"
            + KEY_IMAGE2 + " VARCHAR,"
            + KEY_IMAGE3 + " VARCHAR,"
            + KEY_DISPLAY + " VARCHAR,"
            + KEY_TARGER_QUANTITY + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR,"
            + KEY_TYPE + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR)";


    // layout Master

    public static final String CREATE_TABLE_LAYOUT_MASTER = "CREATE TABLE "
            + TABLE_STORE_LAYOUT + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_LAYOUT
            + " VARCHAR)";

    // Size Master

    public static final String CREATE_TABLE_SIZE_MASTER = "CREATE TABLE "
            + TABLE_STORE_SIZE + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_SIZE
            + " VARCHAR)";

    // FOR PROMOTION GT GSK
    public static final String CREATE_TABLE_PROMOTIONAL_MASTER_GTGSK = "CREATE TABLE "
            + TABLE_PROMOTION_NAME_GTGSK
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + PROMOTION_CD
            + " VARCHAR," + PROMOTION_WINDOW + " VARCHAR)";


    // FOR EMPLOYEE GT GSK
    public static final String CREATE_TABLE_EMPLOYEE_DATA = "CREATE TABLE "
            + TABLE_EMPLOYEE_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_EMP_CD
            + " VARCHAR," + KEY_EMP_NAME + " VARCHAR)";


    // FOR SKU GTGSK
    public static final String CREATE_TABLE_SKU_MASTER_GTGSK = "CREATE TABLE "
            + TABLE_SKU_MASTER_GTGSK + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + SKU_CD + " VARCHAR,"
            + is_competitor + " VARCHAR," + SKU + " VARCHAR)";
    // FOR NOT WORKING REASON
    public static final String CREATE_TABLE_NONWORKINGREASON_GTGSK = "CREATE TABLE "
            + TABLE_NON_WORKING_MASTER_GTGSK
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + REASON_ID
            + " VARCHAR,"
            + REASON_VALID
            + " VARCHAR,"
            + REASON + " VARCHAR)";


    public static final String CREATE_TABLE_NONWORKINGREASON_GTGSK_SUBREASON = "CREATE TABLE "
            + TABLE_NON_WORKING_MASTER_GTGSK_SUBREASON
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + REASON_ID
            + " VARCHAR,"
            + KEY_SUB_REASON_ID
            + " VARCHAR,"
            + SUB_REASON + " VARCHAR)";


    // FOR COMPLIANCE MASTER

    public static final String CREATE_TABLE_COMPLIACEMASTER_GTGSK = "CREATE TABLE "
            + TABLE_COMPLIANCE_MASTER_GTGSK
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COMPLIANCE_ID
            + " VARCHAR," + COMPLIANCE + " VARCHAR)";


    public static final String CREATE_TABLE_WINDOW_STATUS = "CREATE TABLE "
            + TABLE_WINDOW_STATUS
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_WINDOW_STATUS
            + " VARCHAR)";


    // FOR COMPLIANCE MAPPING

    public static final String CREATE_TABLE_PLANOGRAMMAPPING_GTGSK = "CREATE TABLE "
            + TABLE_PLANOGRAM_MAPPING_GTGSK
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + PROMOTIONCD
            + " VARCHAR,"
            + region_id
            + " VARCHAR,"
            + storetype_id
            + " VARCHAR,"
            + KEY_BRAND_CATEGORY_ID
            + " VARCHAR,"
            + KEY_IMAGE + " VARCHAR," + KEY_CATEGORY_ID + " VARCHAR)";

    // planogram

    public static final String CREATE_TABLE_COMPLIACEMASTERMAPPING_GTGSK = "CREATE TABLE "
            + TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COMPLIANCE_CD
            + " VARCHAR,"
            + PROMOTIONCD
            + " VARCHAR,"
            + region_id
            + " VARCHAR,"
            + storetype_id
            + " VARCHAR,"
            + KEY_BRAND_CATEGORY_ID
            + " VARCHAR,"
            + KEY_MONTH_HERO
            + " VARCHAR,"
            + KEY_BRAND_CATEGORY
            + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR)";


    public static final String CREATE_TABLE_insertHEADER_ASSET_DATA = "CREATE TABLE "
            + TABLE_INSERT_HEADER_QUESTIONNAIR_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_PROCESS_ID
            + " VARCHAR,"
            + KEY_STORE_ID
            + " VARCHAR,"
            + KEY_QSUB_CATEGORY_ID
            + " VARCHAR,"
            + KEY_QSUB_CATEGORY + " VARCHAR)";


    public static final String CREATE_TABLE_insertCHILD_ASSET_DATA = "CREATE TABLE "
            + TABLE_INSERT_CHILD_QUESTIONNAIR_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_COMMONID
            + " INTEGER,"
            + KEY_PROCESS_ID
            + " VARCHAR,"
            + KEY_STORE_ID
            + " VARCHAR,"
            + KEY_QUESTION_ID
            + " VARCHAR,"
            + KEY_QUESTION + " VARCHAR,"
            + KEY_ANSWER_ID + " VARCHAR,"
            + KEY_ANSWER + " VARCHAR)";


    public static final String CREATE_TABLE_COMPLIACE_MAPPING_SPECIAL = "CREATE TABLE "
            + TABLE_COMPLIANCE_MAPPING_SPECIAL
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COMPLIANCE_CD
            + " VARCHAR," + PROMOTIONCD + " VARCHAR)";

    public static final String CREATE_TABLE_POSM_MASTER_GTGSK = "CREATE TABLE "
            + TABLE_POSM_MASTER_GTGSK + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_POSM_CD
            + " VARCHAR," + KEY_POSM + " VARCHAR)";

    public static final String TABLE_INSERT_POSM_DATA = "POSM_DATA_INSERTED";

    public static final String TABLE_INSERT_POSM_DEVIATION_DATA = "POSM_DEVIATION_DATA_INSERTED";

    public static final String CREATE_TABLE_POSM_DATA = "CREATE TABLE "
            + TABLE_INSERT_POSM_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
            + KEY_STORE_ID + " VARCHAR," + KEY_POSM_CD + " VARCHAR," + KEY_POSM
            + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR," + KEY_IMAGE
            + " VARCHAR)";


    public static final String CREATE_TABLE_POSM_DEVIATION_DATA = "CREATE TABLE "
            + TABLE_INSERT_POSM_DEVIATION_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_ID + " VARCHAR," + KEY_POSM_CD + " VARCHAR," + KEY_POSM
            + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR," + KEY_IMAGE
            + " VARCHAR)";

    public static final String INVOICE_NUMBER = "INVOICE_NUMBER";
    public static final String CHEQUE_NUMBER = "CHEQUE_NUMBER";

    public static final String TABLE_INSERT_RETAILER_INFO = "RETAILER_INFO";
    public static final String CREATE_TABLE_RETAILER_INFO = "CREATE TABLE "
            + TABLE_INSERT_RETAILER_INFO + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
            + KEY_STORE_ID + " VARCHAR," + INVOICE_NUMBER + " VARCHAR,"
            + KEY_IMAGE + " VARCHAR," + CHEQUE_NUMBER + " VARCHAR)";

    // *************** ENDS

//	CREATE TABLE IF NOT EXISTS DR_STORE_COVERAGE(STORE_ID INT, VISIT_DATE TEXT, IN_TIME TEXT, OUT_TIME TEXT, LATITUDE TEXT, LONGITUDE TEXT,  REASON_ID INT, SUB_REASON_ID INT, REMARK  TEXT, IMAGE_ALLOW INT, STORE_IMAGE TEXT,  USER_ID TEXT, APP_VERSION TEXT, PROCESS_ID INT);


    public static final String CREATE_TABLE_COVERAGE_DATA = "CREATE TABLE DR_STORE_COVERAGE " +
            "(KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT ,STORE_ID INTEGER, VISIT_DATE VARCHAR, IN_TIME VARCHAR, OUT_TIME VARCHAR, LATITUDE VARCHAR, " +
            "LONGITUDE VARCHAR,  REASON_ID INTEGER, SUB_REASON_ID INTEGER, REMARK  VARCHAR, IMAGE_ALLOW INTEGER, " +
            "STORE_IMAGE VARCHAR,  USER_ID VARCHAR, APP_VERSION INTEGER, PROCESS_ID INTEGER, STATUS VARCHAR)";


    public static final String CREATE_TABLE_SOME_COVERAGE_DATA = "CREATE TABLE SOME_STORE_COVERAGE_DATA " +
            "(KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT ,STORE_ID INTEGER, VISIT_DATE VARCHAR, IN_TIME VARCHAR, OUT_TIME VARCHAR, LATITUDE VARCHAR, " +
            "LONGITUDE VARCHAR,  REASON_ID INTEGER, SUB_REASON_ID INTEGER, REMARK  VARCHAR, IMAGE_ALLOW INTEGER, " +
            "STORE_IMAGE VARCHAR,  USER_ID VARCHAR, STATUS VARCHAR)";


    public static final String CREATE_TABLE_PJPCOVERAGE_DATA = "CREATE TABLE "
            + TABLE_PJP_COVERAGE_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR," +
            "USER_ID VARCHAR,"
            + KEY_IN_TIME + " VARCHAR,"
            + KEY_OUT_TIME + " VARCHAR," + KEY_VISIT_DATE + " VARCHAR,"
            + KEY_STATUS + " VARCHAR," + KEY_STORE_NAME + " VARCHAR)";


    public static final String CREATE_TABLE_PJP_DEVIATION = "CREATE TABLE "
            + TABLE_PJP_DEVIATION + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR,"
            + KEY_USER_ID + " VARCHAR,"
            + KEY_IMAGE1 + " VARCHAR,"
            + KEY_IMAGE2 + " VARCHAR,"
            + KEY_IMAGE3 + " VARCHAR,"
            + KEY_REMARK + " VARCHAR,"
            + KEY_STORE_NAME + " VARCHAR)";


    public static final String CREATE_TABLE_PJP_DEVIATION_STORE = "CREATE TABLE "
            + TABLE_PJP_DEVIATION_STORE + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_CD
            + " VARCHAR,"
            + KEY_EMP_CD + " VARCHAR,"
            + KEY_STORE_NAME + " VARCHAR,"
            + KEY_ADDRESS + " VARCHAR,"
            + KEY_STATUS + " VARCHAR,"
            + KEY_CURRENT_DATETIME + " VARCHAR)";

    public static final String CREATE_TABLE_NON_WORKING = "CREATE TABLE "
            + TABLE_NON_WORKING_REASON + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_REASON_ID
            + " VARCHAR," + KEY_REASON + " VARCHAR," + KEY_ENTRY + " VARCHAR,"
            + KEY_IMAGE + " VARCHAR)";

    // Upload Image

    public static final String METHOD_Get_DR_STORE_IMAGES = "GET_StoreLayout_IMAGES";
    public static final String SOAP_ACTION_Get_DR_STORE_IMAGES = "http://tempuri.org/"
            + METHOD_Get_DR_STORE_IMAGES;

    public static final String METHOD_Get_DR_CHEQUE_IMAGES = "Upload_StoreCheque_IMAGES";
    public static final String SOAP_ACTION_Get_DR_CHEQUE_IMAGES = "http://tempuri.org/"
            + METHOD_Get_DR_CHEQUE_IMAGES;

    public static final String METHOD_Get_DR_POSM_IMAGES = "GetImageNew";
    public static final String SOAP_ACTION_Get_DR_POSM_IMAGES = "http://tempuri.org/"
            + METHOD_Get_DR_POSM_IMAGES;


    public static final String METHOD_Upload_StoreDeviationImage = "GetImageNew";
    public static final String SOAP_ACTION_Upload_StoreDeviationImage = "http://tempuri.org/"
            + METHOD_Upload_StoreDeviationImage;

    public static final String METHOD_Get_DR_COMPLIANCE_IMAGES = "GET_Store_SecondaryWindowImage";
    public static final String SOAP_ACTION_Get_DR_COMPLIANCE_IMAGES = "http://tempuri.org/"
            + METHOD_Get_DR_COMPLIANCE_IMAGES;

    public static final String METHOD_Get_DR_STORE_IMAGES_GEO = "Upload_StoreGeoTag_IMAGES";
    public static final String SOAP_ACTION_DR_STORE_IMAGES_GEO = "http://tempuri.org/"
            + METHOD_Get_DR_STORE_IMAGES_GEO;

    public static final String CREATE_TABLE_INSERT_GEOTAG = "CREATE TABLE "
            + TABLE_INSERT_GEO_TAG + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR," + KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE
            + " VARCHAR," + KEY_STATUS + " VARCHAR," + KEY_IMAGE_PATH1
            + " VARCHAR," + KEY_IMAGE_PATH2 + " VARCHAR," + KEY_IMAGE_PATH3
            + " VARCHAR)";

    public static final String CREATE_TABLE_GEO_TAG_CITY = "CREATE TABLE "
            + TABLE_GEOTAG_CITY + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + "CITY_ID" + " VARCHAR,"
            + "CITY" + " VARCHAR)";


    public static final String CREATE_TABLE_QUESTION_ANSWER = "CREATE TABLE "
            + TABLE_QUESTION_ANSWER + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + "QUESTION_ID" + " VARCHAR,"
            + "DISPLAY_ID" + " VARCHAR,"
            + "ANSWER" + " VARCHAR,"
            + KEY_STORE_ID + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + UNIQUE_KEY_ID + " VARCHAR,"
            + "QUESTION" + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR)";


    public static final String CREATE_TABLE_QUESTION_ANSWER_STOCKAFTER = "CREATE TABLE "
            + TABLE_QUESTION_ANSWER_STOCKAFTER + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + "QUESTION_ID" + " VARCHAR,"
            + "DISPLAY_ID" + " VARCHAR,"
            + "ANSWER" + " VARCHAR,"
            + KEY_STORE_ID + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + UNIQUE_KEY_ID + " VARCHAR,"
            + "QUESTION" + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR)";
	
	
	/*public static final String CREATE_TABLE_TEMP_QUESTION_ANSWER = "CREATE TABLE "
			+ TABLE_QUESTION_ANSWER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + "QUESTION_ID" + " VARCHAR,"
			+ "DISPLAY_ID" + " VARCHAR,"
			+ "ANSWER" + " VARCHAR,"
			+ KEY_STORE_ID + " VARCHAR"
			+ KEY_CATEGORY_ID + " VARCHAR"
			+ UNIQUE_KEY_ID + " VARCHAR,"
			+ "QUESTION" + " VARCHAR)";*/
	
	/*
	public static final String CREATE_TABLE_QUESTION_ANSWER = "CREATE TABLE "
			+ TABLE_QUESTION_ANSWER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + "QUESTION_ID" + " VARCHAR,"
			+ "DISPLAY_ID" + " VARCHAR,"
			+ "ANSWER" + " VARCHAR,"
			+ KEY_STORE_ID + " VARCHAR,"
			+ KEY_CATEGORY_ID + " VARCHAR,"
			+ UNIQUE_KEY_ID + " VARCHAR,"
			+ "QUESTION" + " VARCHAR)";*/

    public static final String CREATE_TABLE_PROMOTION_DATA = "CREATE TABLE "
            + TABLE_PROMOTION_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + "PROMOTION" + " VARCHAR,"
            + "SKU_ID" + " VARCHAR,"
            + "STOCK" + " VARCHAR,"
            + "POP" + " VARCHAR,"
            + "RUNNING" + " VARCHAR,"
            + KEY_STORE_ID + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR,"
            + "SPECIAL_ID" + " VARCHAR,"
            + "KEY_SKUNAME" + " VARCHAR)";


    public static final String TABLE_AFTERSTOCK_OTHER = "AFTERSTOCK_OTHER";
    public static final String TABLE_STOCKWAREHOUSE = "STOCKWAREHOUSE_DATA";
    public static final String TABLE_SHELF_VISIBILITY = "SHELF_VISIBILITY";

    public static final String UID = "UID";
    public static final String CAT_ID = "CAT_ID";
    public static final String YESNO = "YESNO";
    public static final String DISPLAY_ID = "DISPLAY_ID";
    public static final String SHELF_ID = "SHELF_ID";
    public static final String SHELF_NAME = "SHELF_NAME";

    public static final String DISPLAY = "DISPLAY";
    public static final String KEY_STORE_TYPEID = "STORE_TYPEID";

    public static final String CREATE_TABLE_INSERT_AFTERSOCK_OTHER = "CREATE TABLE IF NOT EXISTS "
            + TABLE_AFTERSTOCK_OTHER + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR," + YESNO + " VARCHAR," + DISPLAY_ID + " VARCHAR," + CAT_ID
            + " VARCHAR," + DISPLAY
            + " VARCHAR," + UID + " VARCHAR," + " VARCHAR,"
            + " IMAGE_URL" + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR,"
            + KEY_BRAND + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR)";


    public static final String CREATE_TABLE_INSERT_STOCKWAREHOUSE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_STOCKWAREHOUSE + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR," + KEY_CATEGORY_ID + " VARCHAR," + KEY_STOCK + " INTEGER,"
            + KEY_PROCESS_ID + " VARCHAR,"
            + KEY_BRAND + " VARCHAR,"
            + KEY_SKU_ID + " VARCHAR,"
            + KEY_SKUNAME + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR,"
            + KEY_USER_ID + " VARCHAR)";


    public static final String CREATE_TABLE_INSERT_SHELF_VISIBILITY = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SHELF_VISIBILITY + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR," + YESNO + " VARCHAR," + KEY_BRAND_ID + " VARCHAR," + CAT_ID
            + " VARCHAR," + SHELF_ID
            + " VARCHAR,"
            + " IMAGE_URL" + " VARCHAR,"
            + KEY_PROCESS_ID + " VARCHAR,"
            + KEY_STORE_TYPEID + " VARCHAR,"
            + KEY_BRAND + " VARCHAR,"
            + SHELF_NAME + " VARCHAR)";


    public static final String CREATE_TABLE_GEO_TAG_MAPPING = "CREATE TABLE IF NOT EXISTS "
            + TABLE_GEO_TAG_MAPPING + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR," + "STORETYPE" + " VARCHAR," + "STORENAME" + " VARCHAR," + KEY_LATITUDE
            + " VARCHAR," + KEY_LONGITUDE + " VARCHAR," + "GEO_TAG_STATUS"
            + " VARCHAR," + "CITY" + " VARCHAR," + "KEYACCOUNT" + " VARCHAR)";


}
