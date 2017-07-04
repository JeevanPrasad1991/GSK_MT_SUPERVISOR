package com.gsk.sup.gskmt.xmlHandler;
import com.gsk.sup.gskmt.xmlGetterSetter.AdditionalGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.DisplayGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.FailureGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.FocusSaleStoreWiseGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.JCPGetterSetter;
import com.gsk.sup.gskmt.xmlGetterSetter.LoginGetterSetter;
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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class XMLHandlers {
	
	
	// LOGIN XML HANDLER
		public static NonWorkingGetterSetter NonWorkingXMLHandler(XmlPullParser xpp,
																  int eventType) {
			NonWorkingGetterSetter lgs = new NonWorkingGetterSetter();

			try {
				while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
					if (xpp.getEventType() == XmlPullParser.START_TAG) {
						
						if (xpp.getName().equals("META_DATA")) {
							lgs.setMeta_data(xpp.nextText());
						}
						
						if (xpp.getName().equals("REASON_ID")) {
							lgs.setREASON_ID(xpp.nextText());
						}
						if (xpp.getName().equals("REASON")) {
							lgs.setREASON(xpp.nextText());
						}
						if (xpp.getName().equals("IMAGE_ALLOW")) {
							lgs.setIMAGE_ALLOW(xpp.nextText());
						}
						if (xpp.getName().equals("ENTRY_ALLOW")) {
							lgs.setENTRY_ALLOW(xpp.nextText());
						}

					}
					xpp.next();
				}
			} catch (XmlPullParserException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return lgs;
		}
		
		
		public static QuestionGetterSetter QuestionMappingXMLHandler(XmlPullParser xpp,
																	 int eventType) {
			QuestionGetterSetter failureGetterSetter = new QuestionGetterSetter();

			try {
				while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
					if (xpp.getEventType() == XmlPullParser.START_TAG) {
						
						if (xpp.getName().equals("META_DATA")) {
							failureGetterSetter.setMeta_data(xpp.nextText());
						}
						
						if (xpp.getName().equals("QUESTION_ID")) {
							failureGetterSetter.setQuestion_id(xpp.nextText());
						}
						
						if (xpp.getName().equals("DISPLAY_ID")) {
							failureGetterSetter.setDisplay_id(xpp.nextText());
						}
						
					}
					xpp.next();
				}
			} catch (XmlPullParserException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return failureGetterSetter;
		}

	// LOGIN XML HANDLER
	public static LoginGetterSetter loginXMLHandler(XmlPullParser xpp,
													int eventType) {
		LoginGetterSetter lgs = new LoginGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("RIGHT_NAME")) {
						lgs.setRIGHT_NAME(xpp.nextText());
					}
					if (xpp.getName().equals("APP_VERSION")) {
						lgs.setAPP_VERSION(xpp.nextText());
					}
					if (xpp.getName().equals("APP_PATH")) {
						lgs.setAPP_PATH(xpp.nextText());
					}
					if (xpp.getName().equals("CURRENTDATE")) {
						lgs.setCURRENTDATE(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return lgs;
	}

	// FAILURE XML HANDLER
	public static FailureGetterSetter failureXMLHandler(XmlPullParser xpp,
														int eventType) {
		FailureGetterSetter failureGetterSetter = new FailureGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STATUS")) {
						failureGetterSetter.setStatus(xpp.nextText());
					}
					if (xpp.getName().equals("ERRORMSG")) {
						failureGetterSetter.setErrorMsg(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return failureGetterSetter;
	}
	
	
	// JCP XML HANDLER
	public static QuestionnairGetterSetter QuestionXMLHandler(XmlPullParser xpp, int eventType) {
		QuestionnairGetterSetter jcpGetterSetter = new QuestionnairGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setMeta_data(xpp.nextText());
					}
					
					if (xpp.getName().equals("PROCESS_ID")) {
						jcpGetterSetter.setPROCESS_ID(xpp.nextText());
					}
					if (xpp.getName().equals("QSUB_CATEGORY_ID")) {
						jcpGetterSetter.setQSUB_CATEGORY_ID(xpp.nextText());
					}

					if (xpp.getName().equals("QUESTION_SUB_CATEGORY")) {
						jcpGetterSetter.setQUESTION_SUB_CATEGORY(xpp.nextText());
					}
					if (xpp.getName().equals("QUESTION_ID")) {
						jcpGetterSetter.setQUESTION_ID(xpp.nextText());
					}
					if (xpp.getName().equals("QUESTION")) {
						jcpGetterSetter.setQUESTION(xpp.nextText());
					}
					if (xpp.getName().equals("ANSWER_ID")) {
						jcpGetterSetter.setANSWER_ID(xpp.nextText());
					}
					if (xpp.getName().equals("ANSWER")) {
						jcpGetterSetter.setANSWER(xpp.nextText());
//						jcpGetterSetter.setUPLOAD_STATUS("N");
					}
					
					
					
					
					
			}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	// JCP XML HANDLER
			public static JCPGetterSetter JCPXMLHandler(XmlPullParser xpp, int eventType) {
				JCPGetterSetter jcpGetterSetter = new JCPGetterSetter();

				try {
					while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
						if (xpp.getEventType() == XmlPullParser.START_TAG) {
							
							if (xpp.getName().equals("META_DATA")) {
								jcpGetterSetter.setMeta_data(xpp.nextText());
							}
							
							if (xpp.getName().equals("STORE_ID")) {
								jcpGetterSetter.setSTORE_ID(xpp.nextText());
							}
							if (xpp.getName().equals("EMP_ID")) {
								jcpGetterSetter.setEMP_ID(xpp.nextText());
							}

							if (xpp.getName().equals("STORE")) {
								jcpGetterSetter.setSTORE(xpp.nextText());
							}
							if (xpp.getName().equals("CITY")) {
								jcpGetterSetter.setCITY(xpp.nextText());
							}
							if (xpp.getName().equals("VISIT_DATE")) {
								jcpGetterSetter.setVISIT_DATE(xpp.nextText());
							}
							
							if (xpp.getName().equals("KEY_ID")) {
								jcpGetterSetter.setKEY_ID(xpp.nextText());
							}
							if (xpp.getName().equals("PROCESS_ID")) {
								jcpGetterSetter.setPROCESS_ID(xpp.nextText());
							}
							
							if (xpp.getName().equals("PROCESS")) {
								jcpGetterSetter.setPROCESS(xpp.nextText());
							}
							
							if (xpp.getName().equals("UPLOAD_STATUS")) {
								jcpGetterSetter.setUPLOAD_STATUS(xpp.nextText());
//								jcpGetterSetter.setUPLOAD_STATUS("N");
							}
							
							if (xpp.getName().equals("REGION_ID")) {
								jcpGetterSetter.setREGION_ID(xpp.nextText());
							}
							
							
							
							if (xpp.getName().equals("STORETYPE_ID")) {
								jcpGetterSetter.setSTORETYPE_ID(xpp.nextText());
							}
							
							if (xpp.getName().equals("CHECKOUT_STATUS")) {
								jcpGetterSetter.setCHECKOUT_STATUS(xpp.nextText());
//								jcpGetterSetter.setCHECKOUT_STATUS("N");
							}
							
							
							
					}
						xpp.next();
					}
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return jcpGetterSetter;
			}

			
			//Stock - Mapping
			public static StockMappingGetterSetter StockMappingXMLHandler(XmlPullParser xpp, int eventType) {
				StockMappingGetterSetter jcpGetterSetter = new StockMappingGetterSetter();

				try {
					while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
						if (xpp.getEventType() == XmlPullParser.START_TAG) {
							
							if (xpp.getName().equals("META_DATA")) {
								jcpGetterSetter.setMeta_data(xpp.nextText());
							}
							
							if (xpp.getName().equals("CATEGORY_ID")) {
								jcpGetterSetter.setCategory_id(xpp.nextText());
							}
							if (xpp.getName().equals("CATEGORY")) {
								jcpGetterSetter.setCategory(xpp.nextText());
							}

							
							
					}
						xpp.next();
					}
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return jcpGetterSetter;
			}
			
			
			public static TDSGetterSetter TDSXMLHandler(XmlPullParser xpp,
														int eventType) {
				TDSGetterSetter failureGetterSetter = new TDSGetterSetter();

				try {
					while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
						if (xpp.getEventType() == XmlPullParser.START_TAG) {
							
							if (xpp.getName().equals("META_DATA")) {
								failureGetterSetter.setMeta_data(xpp.nextText());
							}
							
							if (xpp.getName().equals("STORE_ID")) {
								failureGetterSetter.setSTORE_ID(xpp.nextText());
							}
							if (xpp.getName().equals("CATEGORY_ID")) {
								failureGetterSetter.setCATEGORY_ID(xpp.nextText());
							}
							
							if (xpp.getName().equals("DISPLAY_ID")) {
								failureGetterSetter.setDISPLAY_ID(xpp.nextText());
							}
							
							if (xpp.getName().equals("TARGET_QTY")) {
								failureGetterSetter.setTARGET_QTY(xpp.nextText());
							}
							
							if (xpp.getName().equals("PROCESS_ID")) {
								failureGetterSetter.setPROCESS_ID(xpp.nextText());
							}
							
							if (xpp.getName().equals("BRAND_ID")) {
								failureGetterSetter.setBRAND_ID(xpp.nextText());
							}
							
							if (xpp.getName().equals("DISPLAY_TYPE")) {
								failureGetterSetter.setTYPE(xpp.nextText());
							}
							
							if (xpp.getName().equals("ID")) {
								failureGetterSetter.setUID(xpp.nextText());
							}

						}
						xpp.next();
					}
				} catch (XmlPullParserException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				return failureGetterSetter;
			}
			
			//Brand - Master
			public static SKUGetterSetter BrandXMLHandler(XmlPullParser xpp, int eventType) {
				SKUGetterSetter jcpGetterSetter = new SKUGetterSetter();

				try {
					while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
						if (xpp.getEventType() == XmlPullParser.START_TAG) {
							
							if (xpp.getName().equals("META_DATA")) {
								jcpGetterSetter.setMeta_data(xpp.nextText());
							}
							
							if (xpp.getName().equals("BRAND_ID")) {
								jcpGetterSetter.setBrand_id(xpp.nextText());
							}
							if (xpp.getName().equals("BRAND")) {
								jcpGetterSetter.setBrand(xpp.nextText());
							}

							if (xpp.getName().equals("CATEGORY")) {
								jcpGetterSetter.setCategory_name(xpp.nextText());
							}
							if (xpp.getName().equals("CATEGORY_ID")) {
								jcpGetterSetter.setCategory_id(xpp.nextText());
							}
							if (xpp.getName().equals("COMPANY_ID")) {
								jcpGetterSetter.setCompany_id(xpp.nextText());
							}
							
					}
						xpp.next();
					}
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return jcpGetterSetter;
			}
			
			
			public static DisplayGetterSetter DisplayXMLHandler(XmlPullParser xpp,
																int eventType) {
				DisplayGetterSetter failureGetterSetter = new DisplayGetterSetter();

				try {
					while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
						if (xpp.getEventType() == XmlPullParser.START_TAG) {
							
							if (xpp.getName().equals("META_DATA")) {
								failureGetterSetter.setMeta_data(xpp.nextText());
							}
							
							if (xpp.getName().equals("DISPLAY_ID")) {
								failureGetterSetter.setDisplay_id(xpp.nextText());
							}
							if (xpp.getName().equals("DISPLAY")) {
								failureGetterSetter.setDisplay(xpp.nextText());
							}
							
							if (xpp.getName().equals("IMAGE_URL")) {
								failureGetterSetter.setImage_url(xpp.nextText());
								
							}

						}
						xpp.next();
					}
				} catch (XmlPullParserException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				return failureGetterSetter;
			}
			
			public static AdditionalGetterSetter AdditionalMappingXMLHandler(XmlPullParser xpp,
																			 int eventType) {
				AdditionalGetterSetter failureGetterSetter = new AdditionalGetterSetter();

				try {
					while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
						if (xpp.getEventType() == XmlPullParser.START_TAG) {
							
							if (xpp.getName().equals("META_DATA")) {
								failureGetterSetter.setMeta_data(xpp.nextText());
							}
							
							if (xpp.getName().equals("STORETYPE_ID")) {
								failureGetterSetter.setStore_type_id(xpp.nextText());
							}
							
							if (xpp.getName().equals("CATEGORY_ID")) {
								failureGetterSetter.setCategory_id(xpp.nextText());
							}
							
							if (xpp.getName().equals("DISPLAY_ID")) {
								failureGetterSetter.setDisplay_id(xpp.nextText());
							}
							
							if (xpp.getName().equals("PROCESS_ID")) {
								failureGetterSetter.setProcess_id(xpp.nextText());
							}

							
						}
						xpp.next();
					}
				} catch (XmlPullParserException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				return failureGetterSetter;
			}
			
			
			public static QuestionGetterSetter QuestionXMLHandlerr(XmlPullParser xpp,
					int eventType) {
				QuestionGetterSetter failureGetterSetter = new QuestionGetterSetter();

				try {
					while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
						if (xpp.getEventType() == XmlPullParser.START_TAG) {
							
							if (xpp.getName().equals("META_DATA")) {
								failureGetterSetter.setMeta_data(xpp.nextText());
							}
							
							if (xpp.getName().equals("QUESTION_ID")) {
								failureGetterSetter.setQuestion_id(xpp.nextText());
							}
							
							if (xpp.getName().equals("QUESTION")) {
								failureGetterSetter.setQuestion(xpp.nextText());
							}
							
						}
						xpp.next();
					}
				} catch (XmlPullParserException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				return failureGetterSetter;
			}

	public static SupincentiveGetterSetter SupIncentiveMappingXMLHandler(XmlPullParser xpp, int eventType) {
		SupincentiveGetterSetter failureGetterSetter = new SupincentiveGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						failureGetterSetter.setSupincentiveTable(xpp.nextText());
					}

					if (xpp.getName().equals("CURRENTMONTH")) {
						failureGetterSetter.setCurMonth(xpp.nextText());
					}

					if (xpp.getName().equals("PM1")) {
						failureGetterSetter.setPM1(xpp.nextText());
					}

					if (xpp.getName().equals("PM2")) {
						failureGetterSetter.setPM2(xpp.nextText());
					}

					if (xpp.getName().equals("PM3")) {
						failureGetterSetter.setPM3(xpp.nextText());
					}

					if (xpp.getName().equals("CURPER")) {
						failureGetterSetter.setCurrMonthper(xpp.nextText());
					}

					if (xpp.getName().equals("PM1PER")) {
						failureGetterSetter.setPm1per(xpp.nextText());
					}
					if (xpp.getName().equals("PM2PER")) {
						failureGetterSetter.setPm2per(xpp.nextText());
					}
					if (xpp.getName().equals("PM3PER")) {
						failureGetterSetter.setPm3per(xpp.nextText());
					}


				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return failureGetterSetter;
	}

    public static SupFocusSaleGetterSetter SupFocusSaleMappingXMLHandler(XmlPullParser xpp, int eventType) {
        SupFocusSaleGetterSetter failureGetterSetter = new SupFocusSaleGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        failureGetterSetter.setSupfocussaleTable(xpp.nextText());
                    }

                    if (xpp.getName().equals("CURRENTMONTH")) {
                        failureGetterSetter.setCurrMonth(xpp.nextText());
                    }

                    if (xpp.getName().equals("EMPLOYEE")) {
                        failureGetterSetter.setEmployee(xpp.nextText());
                    }

                    if (xpp.getName().equals("PM2")) {
                        failureGetterSetter.setPm2(xpp.nextText());
                    }
					if (xpp.getName().equals("PM1")) {
						failureGetterSetter.setPm1(xpp.nextText());
					}
					if (xpp.getName().equals("PM3")) {
						failureGetterSetter.setPm3(xpp.nextText());
					}

					if (xpp.getName().equals("CURRENTPER")) {
                        failureGetterSetter.setCurrMonthper(xpp.nextText());
                    }

                    if (xpp.getName().equals("PM1PER")) {
                        failureGetterSetter.setPm1per(xpp.nextText());
                    }
					if (xpp.getName().equals("PM2PER")) {
						failureGetterSetter.setPm2per(xpp.nextText());
					}
					if (xpp.getName().equals("PM3PER")) {
						failureGetterSetter.setPm3per(xpp.nextText());
					}


                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return failureGetterSetter;
    }

	public static TotalSaleGetterSetter TotalSaleMappingXMLHandler(XmlPullParser xpp, int eventType) {
		TotalSaleGetterSetter failureGetterSetter = new TotalSaleGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						failureGetterSetter.setTotalsaleTable(xpp.nextText());
					}

					if (xpp.getName().equals("CURRENTMONTH")) {
						failureGetterSetter.setCurrMonth(xpp.nextText());
					}

					if (xpp.getName().equals("EMPLOYEE")) {
						failureGetterSetter.setEmployee(xpp.nextText());
					}

					if (xpp.getName().equals("PM2")) {
						failureGetterSetter.setPm2(xpp.nextText());
					}
                    if (xpp.getName().equals("PM1")) {
                        failureGetterSetter.setPm1(xpp.nextText());
                    }
                    if (xpp.getName().equals("PM3")) {
                        failureGetterSetter.setPm3(xpp.nextText());
                    }

					if (xpp.getName().equals("CURRENTPER")) {
						failureGetterSetter.setCurrMonthper(xpp.nextText());
					}

					if (xpp.getName().equals("PM1PER")) {
						failureGetterSetter.setPm1per(xpp.nextText());
					}
					if (xpp.getName().equals("PM2PER")) {
						failureGetterSetter.setPm2per(xpp.nextText());
					}
					if (xpp.getName().equals("PM3PER")) {
						failureGetterSetter.setPm3per(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return failureGetterSetter;
	}

    public static TeamPassGetterSetter TeamPassMappingXMLHandler(XmlPullParser xpp, int eventType) {
        TeamPassGetterSetter failureGetterSetter = new TeamPassGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        failureGetterSetter.setTeampassTable(xpp.nextText());
                    }

                    if (xpp.getName().equals("EMPLOYEE")) {
                        failureGetterSetter.setMerchanD(xpp.nextText());
                    }

                    if (xpp.getName().equals("PERFECT")) {
                        failureGetterSetter.setPerfect(xpp.nextText());
                    }

                    if (xpp.getName().equals("NEAR_PERFECT")) {
                        failureGetterSetter.setNear20perF(xpp.nextText());
                    }
                    if (xpp.getName().equals("NOT_PERFECT")) {
                        failureGetterSetter.setNot20perF(xpp.nextText());
                    }
                    if (xpp.getName().equals("TOTAL_STORES")) {
                        failureGetterSetter.setTotal20store(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return failureGetterSetter;
    }
	public static FocusSaleStoreWiseGetterSetter FocusSaleStoreWiseMappingXMLHandler(XmlPullParser xpp, int eventType) {
		FocusSaleStoreWiseGetterSetter failureGetterSetter = new FocusSaleStoreWiseGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						failureGetterSetter.setFocussalestorewiseTable(xpp.nextText());
					}

					if (xpp.getName().equals("STORE_ID")) {
						failureGetterSetter.setStorenm(xpp.nextText());
					}

					if (xpp.getName().equals("EMPLOYEE")) {
						failureGetterSetter.setEmployee(xpp.nextText());
					}

					if (xpp.getName().equals("PM2")) {
						failureGetterSetter.setPm2(xpp.nextText());
					}
					if (xpp.getName().equals("CURRENTMONTH")) {
						failureGetterSetter.setCuurrentM(xpp.nextText());
					}
					if (xpp.getName().equals("PM1")) {
						failureGetterSetter.setPm1(xpp.nextText());
					}
					if (xpp.getName().equals("PM3")) {
						failureGetterSetter.setPm3(xpp.nextText());
					}
					if (xpp.getName().equals("TARGET")) {
						failureGetterSetter.setTarget(xpp.nextText());
					}
					if (xpp.getName().equals("CURRENTPER")) {
						failureGetterSetter.setCurrMonthper(xpp.nextText());
					}

					if (xpp.getName().equals("PM1PER")) {
						failureGetterSetter.setPm1per(xpp.nextText());
					}
					if (xpp.getName().equals("PM2PER")) {
						failureGetterSetter.setPm2per(xpp.nextText());
					}
					if (xpp.getName().equals("PM3PER")) {
						failureGetterSetter.setPm3per(xpp.nextText());
					}


				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return failureGetterSetter;
	}

	public static TotalSaleStorewiseGetterSetter TotalSaleStoreWiseMappingXMLHandler(XmlPullParser xpp, int eventType) {
		TotalSaleStorewiseGetterSetter failureGetterSetter = new TotalSaleStorewiseGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						failureGetterSetter.setTotalsalestorewiseTable(xpp.nextText());
					}

					if (xpp.getName().equals("STORE_ID")) {
						failureGetterSetter.setStoreN(xpp.nextText());
					}

					if (xpp.getName().equals("EMPLOYEE")) {
						failureGetterSetter.setEmployee(xpp.nextText());
					}

					if (xpp.getName().equals("PM2")) {
						failureGetterSetter.setPm2(xpp.nextText());
					}
					if (xpp.getName().equals("CURRENTMONTH")) {
						failureGetterSetter.setCurrentM(xpp.nextText());
					}
					if (xpp.getName().equals("PM1")) {
						failureGetterSetter.setPm1(xpp.nextText());
					}
					if (xpp.getName().equals("PM3")) {
						failureGetterSetter.setPm3(xpp.nextText());
					}

					if (xpp.getName().equals("TARGET")) {
						failureGetterSetter.setTarget(xpp.nextText());
					}

					if (xpp.getName().equals("CURRENTPER")) {
						failureGetterSetter.setCurrMonthper(xpp.nextText());
					}

					if (xpp.getName().equals("PM1PER")) {
						failureGetterSetter.setPm1per(xpp.nextText());
					}
					if (xpp.getName().equals("PM2PER")) {
						failureGetterSetter.setPm2per(xpp.nextText());
					}
					if (xpp.getName().equals("PM3PER")) {
						failureGetterSetter.setPm3per(xpp.nextText());
					}


				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return failureGetterSetter;
	}

	public static PssStorewiseGetterSetter PssStorewiseSaleStoreWiseMappingXMLHandler(XmlPullParser xpp, int eventType) {
		PssStorewiseGetterSetter failureGetterSetter = new PssStorewiseGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						failureGetterSetter.setPssstorewiseTable(xpp.nextText());
					}

					if (xpp.getName().equals("STORE_ID")) {
						failureGetterSetter.setStoreN(xpp.nextText());
					}

					if (xpp.getName().equals("EMPLOYEE")) {
						failureGetterSetter.setMerchanD(xpp.nextText());
					}

					if (xpp.getName().equals("PM2")) {
						failureGetterSetter.setPm2(xpp.nextText());
					}
					if (xpp.getName().equals("CURRENTMONTH")) {
						failureGetterSetter.setCurrentM(xpp.nextText());
					}
					if (xpp.getName().equals("PM1")) {
						failureGetterSetter.setPm1(xpp.nextText());
					}
					if (xpp.getName().equals("PM3")) {
						failureGetterSetter.setPm3(xpp.nextText());
					}
					if (xpp.getName().equals("CURRPER")) {
						failureGetterSetter.setCurrMonthper(xpp.nextText());
					}

					if (xpp.getName().equals("PM1PER")) {
						failureGetterSetter.setPm1per(xpp.nextText());
					}
					if (xpp.getName().equals("PM2PER")) {
						failureGetterSetter.setPm2per(xpp.nextText());
					}
					if (xpp.getName().equals("PM3PER")) {
						failureGetterSetter.setPm3per(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return failureGetterSetter;
	}


	public static RemarkGetterSetter RemarkMappingXMLHandler(XmlPullParser xpp, int eventType) {
		RemarkGetterSetter failureGetterSetter = new RemarkGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						failureGetterSetter.setRemark_table(xpp.nextText());
					}

					if (xpp.getName().equals("ID")) {
						failureGetterSetter.setRemark_cd(xpp.nextText());
					}
					if (xpp.getName().equals("REMARKS")) {
						failureGetterSetter.setRemark(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return failureGetterSetter;
	}

	public static PssStorewiseDetailGetterSetter PssStorewiseDetailsMappingXMLHandler(XmlPullParser xpp, int eventType) {
		PssStorewiseDetailGetterSetter failureGetterSetter = new PssStorewiseDetailGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						failureGetterSetter.setPssstorewisedetailTable(xpp.nextText());
					}

					if (xpp.getName().equals("STORE_ID")) {
						failureGetterSetter.setStore_id(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY_ID")) {
						failureGetterSetter.setCategory_id(xpp.nextText());
					}

					if (xpp.getName().equals("CATEGORY")) {
						failureGetterSetter.setCategory(xpp.nextText());
					}
					if (xpp.getName().equals("SOS")) {
						failureGetterSetter.setSos(xpp.nextText());
					}
					if (xpp.getName().equals("TOT")) {
						failureGetterSetter.setTot(xpp.nextText());
					}
					if (xpp.getName().equals("PAID")) {
						failureGetterSetter.setPaid(xpp.nextText());
					}
					if (xpp.getName().equals("ADDITIONAL")) {
						failureGetterSetter.setAddition(xpp.nextText());
					}
					if (xpp.getName().equals("PSS_SCORE")) {
						failureGetterSetter.setPss_store(xpp.nextText());
					}
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return failureGetterSetter;
	}


}
