/**
 * 
 */

package com.brixip.controller.loader.jobscheduler.servlet;

/**
 * @author dumesh
 */

public interface EnumerationIF
{
    // Condition HashMap Header Enumaration
    final int I_HEADEROBJECT=1;
    final int I_HEADERPARAM=2;
    
    final String STR_HEADER="HEADER";
    final String STR_OBJECT="OBJECT";
    final String STR_CODE="CODE";
    final String STR_LISTITEM="LISTITEM";
    final String STR_ARRAYLIST="ARRAYLIST";
    final String STR_LISTITEMS="LISTITEMS";
    final String STR_STRING="STRING";
    final String STR_PROBLEM="PROBLEM";
    final String STR_MULTIPLEPLANTS = "MULTIPLEPLANTS";   // Added by Niketa Mane .... 30/10/2014
    final String STR_ISACTIVE = "ISACTIVE";      // Added by Niketa Mane .... 30/10/2014
    final String STR_TAAPPROVALNO = "TA_APRROVAL";      // Added by Niketa Mane .... 28/1/2015
    final String STR_EMAILSTATUS="EMAILSTATUS";
    
    final String STR_VIEW="VIEW";    // Added by Niketa Mane .... 28/1/2015
    
    final String STR_COPCONTACT_OBJECT = "COP_CONTACT_OBJECT"; /// Added By niketa mane
    
    final String STR_DATA = "DATA"; /// Added By niketa mane
    
    final String STR_DATA1 = "DATA1"; /// Added By niketa mane
    
    final String STR_COLUMNS = "COLUMNS"; /// Added By niketa mane
    
    //Enumeration for Datagrid column datafield and header // Addded by Niketa Mane
	final String STR_HEADERTEXT ="HEADERTEXT";
	final String STR_DATAFIELD ="DATAFIELD";
	final String STEP_ID="STEP_ID";
    
    //ENUMERATION FOR PAGE VIEW/ACTION
    final String STR_ACTION="ACTION";
    final String STR_RETURNCODE="RETURNCODE";
    final String STR_NEW="NEW";
    final String STR_NEW_REPLICA="NEW_REPLICA";
    final String STR_UPDATE="UPDATE";
    final String STR_DELETE="DELETE";
    final String STR_DISPLAY="DISPLAY";
    final String STR_IMPORT_NEW="NEW_IMPORT";
    final String STR_SAVE="SAVE";
    final String STR_CANCEL="CANCEL";
    final String STR_BACK="BACK";
    final String STR_OK="OK";
    final String STR_SOAKROOMDATA="SOAKROOMDATA";
    
    final String STR_SUBINDEX="SUBINDEX";
    
    // SAVE ITEM TO INDICATE WHICH ITEM TO SAVE
    final String STR_SAVEITEM="SAVEITEM";
    
    final String STR_OBJECTIVES="OBJECTIVES";
    
    final String STR_AVAILABLE_TESTTYPE="TR_TESTTYPE";
    
    // ENUMARATION FOR THE REPORT OUT PUT FILE
    // -------------------------------------------- UMESH, 15-07-2009
    final String STR_REPORTOUTPUT="REPORTOUTPUT";
    
    final String STR_DOWNLOAD_FILEPATH="DownloadFilePath";
    
    // To hold type of report on general analysis report tab...............btushar, 10-06-2013.
    final String STR_REPORT_TYPE = "REPORT_TYPE";
    
    final String STR_PREVCOP_ID="PREV_COP";
    final String STR_EXCOP_ID="EX_COP";
    
    final int I_ORACELDB=1;
    final int I_SQLDB=2;
    
	// Enumerations for Error Code

	final int I_SUCCESS = 1;
	final int I_FAILURE = 2;
	final int I_ERROR = 3;
	final int I_EXCEPTION = 4;
	final int I_DUPLICATE = 6;
	final int I_INUSED = 7;
	final int I_INVALID = 8;
	final int I_INVALID_UNAUTHORIZED_KEY = 9;
	final int I_REQ_UNAUTHORIZED = 401;
	final int I_REQ_SUCCESS = 200;
	final int I_SESSION_KEY_EXPIRE = 9;
	final int I_NOT_FOUND = 10;
	final int I_DEACTIVATED_USER = 11; // Added by Tushar, 12-12-13
	final int I_ITEM_INUSED = 12; // ADDED BY TUSHAR, 02/01/2014
	final int I_EMAILFAILED = 13; // ADDED BY Niketa , 02/01/2014
    
    // Enumeration for Return Map header code
    final int I_RETURNHEADEROBJECT=1;
    final int I_RETURNHEADERPARAM=2;
    final int I_RETURNHEADERCODE=3;
    
    // Enumeration for Soak Room Temperature data  ...Tushar, 09-09-2014
	public final int I_KEEP_OLD	= 0;
	public final int I_REPLACE_DATA	= 1;
	public final int I_DELETE_DATA	= 2;
    
    /** Enumerations for List Items */
    final int I_MANUFACTURER=1;
    final int I_PLANT=2;
    
    final int I_USER=3;
    final int I_ROLE=4;
    final int I_DESIGNATION=5;
    
    final int I_TESTSTANDARD=6;
    final int I_LEGISLATION=7;
    
    final int I_TESTBED=8;
    final int I_REGULATION=9;
    
    final int I_TYPEOFTEST=10;
    
    final int I_UUTTYPE=11;
    final int I_UUTSUBTYPE=12;
    
    final int I_ALL=13;
    final int I_COP=14;
    final int I_EXCOP=15;
    final int I_TA=16;
    final int I_DEV=17;
    final int I_HOMO=18;
    final int I_SHED=19;
    
    final int I_FUEL=20;
    final int I_SUBFUEL=21;
    final int I_FUELPARAMETER=22;
    
    final int I_ENGINEQP=23;
    final int I_VEHICLEQP=24;
    
    final int I_UUT=25; // for getting UUTs
    final int I_FAMILY=26; // for getting Families
    final int I_TAG=27;
    
    final int I_USERLIST=28;
    final int I_TAGDETAIL=99;
    
    final int I_UNITID=30;
    

	//ADDED BY NIKETA MANE FOR THE ENGINE VEHICLE VIEW CONST
    final int I_ENGINEMAUFACTURER    = 159;
    final int I_ENGINEPLANT          = 160;
    final int I_ENGINETYPE           = 161;
    final int I_ENGINEID             = 162;
    final int I_ENGINEVEHICLEFAMILY  = 163;
    
    // TO SAVE UUT SPECIFIED PARAMETERS
    final int I_UUTSPECIFIED=31;
    
    // TO SAVE DECLARED PARAMETERS
    final int I_UUTDECLARED=32;
    
    // TO SAVE FTP TAG INFORMATION
    final int I_SAVE_FTP_PARAM=33;
    
    // TO CREATE MEASUREMENT
    final int I_CRE_MEASUREMENT=34;
    // TO UPDATE MEASUREMENT
    final int I_UPD_MEASUREMENT=35;
    // TO DELETE MEASUREMENT
    final int I_DEL_MEASUREMENT=36;
    
    final int I_TESTREQ_REGULATION=37;
    final int I_TESTREQ_UUT=38;
    
    final int I_UUT_TESTREQ=39;
    final int I_UUT_MANUFATURER=40;
    
    final int I_USER_UUT=41;
    final int I_USER_ROLE=42;
    
    final int I_TS_STEP_INFO=43;
    
    final int I_UPDATESTATUS_TS=44;
    final int I_UPDATESTATUS_TR=45;
    final int I_UPDATESTATUS_TP=46;
    
    // FOR SGHOWING
    final int I_REGULATIONlIMITTYPE=47;
    
    // GET THE LIST ACCESS FIRGTHS
    final int I_ACCESSRIGHTS=48;
    // GET THE LIST OF FUNCTION FOR ACCESS RIGHTS
    final int I_FUNCTIONLIST=49;
    
    // for getting list of regulations
    final int I_ALLREGULATION=50;
    
    // GET COMPONENT LIST
    final int I_COMPONENT_LIST=51;
    
    // GET UUT REPLICA
    final int I_UUT_REPLICA=52;
    
    // GET UUT GROUP
    final int I_UUT_GROUP=53;
    
    final int I_FAMILY_STANDARD = 54;
    final int I_FAMILY_LEGISLATION = 55;
	
	
    // GET TAG FROM TEMPLATE
    final int I_TAG_TEMPLATE=200;
    
    // GET TAG FROM UUT
    final int I_TAG_UUT=55;
    
    final int I_VEHICLE_TEMPLATE=56;
    
    final int I_ENGINE_TEMPLATE=57;
    
    final int I_TEMPLATE=58;
    
    final int I_TAGCATEGORY=59;
    
    // TO SAVE ENGINE SPECIFIED PARAMETERS, MODIFIED ON 10-06-09 BY UMESH
    final int I_UUTENGINESPEC=60;
    
    final int I_VARIANT=61;
    
    // to get the fuel tag list from TemplateManagementBean
    final int I_FUELTAG=62;
    
    // for test plan combination subindex
    final int I_SUBINDEX=63;
    
    final int I_REGULATIONHEADER=64;
    
    final int I_REGULATIONSUBITEM=65;
    
    final int I_COMPULSORYTAG=66;
    
    final int I_VEHICLELIST=67;
    
    final int I_UUT_VEHICLE=68;
    
    final int I_ENGINEFAMILY=69;
    
    final int I_UUT_ENGINEFAMILY=70;
    
    // ------------------------------------------------------- umesh, 25-06-2009
    // QUALITY PLAN APPROVAL LIST ITEM
    final int I_APPROVEQP=71;
    // QUALITY PLAN FINAL APPROVAL LIST ITEM
    final int I_APPROVEFINALQP=72;
    // QUALITY PLAN REJECT LIST ITEM
    final int I_REJECTQP=73;
    // QUALITY PLAN ACTIVATE LIST ITEM
    final int I_ACTIVATEQP=74;
    
    // ------------------------------------------------------- PALLAVI,
    // 25-06-2009
    // LIST OF REF. TO TYPE APPROVAL, TO SHOW IN QP CREATION FOR COP
    final int I_REFERAL_TA=75;
    
    // ------------------------------------------------------- umesh, 25-06-2009
    // GET THE LIST ITEM FOR THE
    final int I_APPROVAL_ACTION=76;
    
    // TO SAVE DECLARED PARAMETERS
    final int I_UUTENGINESPEC_BS=77;
    
    // TO SAVE DECLARED PARAMETERS
    final int I_UUTENGINESPEC_SHEAREOBJ=78;
    
    // TO SAVE SPECIFIED PARAMETER VALUES
    final int I_UUTSPECIFIED_SHEAREOBJ=79;
    
    // TO SAVE DECLARED PARAMETERS
    final int I_UUTDECLARED_SHEAREOBJ=80;
    
    // ------------------------------------------------------- PALLAVI,
    // 28-06-2009
    // LIST OF REF. TO PREV COP, TO SHOW IN QP CREATION FOR COP
    final int I_PREV_COP=81;
    
    // GET LIST OF LESIGLATIONS ------------------------------ PALLAVI, 29/06/09
    final int I_LEGISLATION_LIST=82;
    
    // ------------------------------------------------------ UMESH, 29-06-2009
    // required by rajesh
    final int I_MANUALDATA=83;
    
    // --------------- TO SAVE LIMIT VALUE -------------- Pallavi,06.07.09
    final int I_LIMIT=84;
    final String STR_LIMIT="LIMIT";
    
    // ------------------------------------------------------ UMESH, 29-06-2009
    // required by rajesh
    final int I_OBJECTIVE=85;
    
    // ------------------------------------------------------ UMESH, 29-06-2009
    final int I_REDECLAREDPOWER=86;
    
    // ------------------------------------------------------ UMESH, 29-06-2009
    final int I_DISPLAY_REPORT=7777;
    
    // ------------------------------------------------------- RAJESH,
    // 25-07-2009
    final int I_TREELIST=5555;
    
    final int I_REPORT_INFO=7778;
    
    // TO SAVE TEST SPECIFIED PARAMETER VALUES BEFORE SAVE ------- UMESH,
    // 16-07-2009
    // TO SAVE TEST SPECIFIED PARAMETERS IN TEH SUMMARYDETAILS_TBL
    final int I_UUTSPECIFIED_BS=87;
    
    // ------------------------------------------------------ RAJESH, 07-08-2009
    final int I_TEST_BED=88;
    
    final int I_BATCHNUMBER=89;
    
    final int I_TESTTYPE=90;
    
    // TO GENERATE NEXT STEP FOR DEVELOPMENT
    final int I_NEXTSTEP=91;
    
    // ENUMERATIONS FOR RECALCULATE TEST PARAMETERS ------- PALLAVI,24/08/09
    final int I_RECALCULATE_RESULT=92;
    
    // ENUMERATIONS FOR TEST REQUEST STATUS ------- PALLAVI,28/08/09
    final int I_TR_STATUS=93;
    
    // ENUMERATIONS FOR UUT SUBTYPE LIST ------- PALLAVI,29/08/09
    final int I_UUTSUBTYPE_LIST=94;
    
    // ENUMERATIONS FOR SEARCHFILTER ------- PALLAVI,29/08/09
    final int I_SEARCHFILTER_LIST=95;
    
    // ENUMERATIONS FOR SAVE COMPONENTS ------- PALLAVI,11/09/09
    final int I_COMPONENT_CONFIGURATION=96;
    
    // ENUMERATIONS FOR SAVE COMPONENTS ------- PALLAVI,11/09/09
    final int I_COMPONENTS=97;
    
    // ENUMERATION FOR SAVE ENGINE RULES ------- PALLAVI,13/09/09
    final int I_SAVE_ENGINE_RULES=113;
    
    // ENUMERATION FOR GET ENGINE RULES ------- PALLAVI,13/09/09
    final int I_ENGINE_RULES=114;
    
    // ENUMERATION FOR GET ENGINE RULE INFO ------- PALLAVI,14/09/09
    final int I_GET_ENGINE_RULE=115;
    
    // ENUMERATION FOR SAVE ENGINE RULES ------- PALLAVI,14/09/09
    final int I_UPDATE_ENGINE_RULES=116;
    
    // ENUMERATION FOR UPDATE ENGINE RULES ------- PALLAVI,14/09/09
    final int I_DELETE_ENGINE_RULES=117;
    
    // ENUMERATION FOR SAVE ENGINE RULES ------- PALLAVI,15/09/09
    final int I_SAVE_PROCEDURE=118;
    
    // ENUMERATION FOR GET PROCEDURE LIST ------- PALLAVI,15/09/09
    final int I_PROCEDURE_LIST=119;
    
    // ENUMERATION FOR GET ENGINE RULE INFO ------- PALLAVI,15/09/09
    final int I_GET_PROCEDURE=120;
    
    // ENUMERATION FOR UPDATE ENGINE RULES ------- PALLAVI,15/09/09
    final int I_UPDATE_PROCEDURE=121;
    
    // ENUMERATION FOR UPDATE ENGINE RULES ------- PALLAVI,15/09/09
    final int I_DELETE_PROCEDURE=122;
    
    // QUALITY PLAN CLOSE LIST ITEM ------- PALLAVI,16/09/09
    final int I_DONEQP=123;
    
    final int I_FUELTEMPLATE=124;
    
    // GET LIST OF ALL LESIGLATIONS ------------------------------ PALLAVI,
    // 23/10/09
    final int I_LEGISLATIONS_LIST=125;
    
    //ENUEMRATION FOR BATCH NUMBER ----------- PALLAVI,30/10/09
    final int I_FUEL_BATCHNUMBER=126;
    
    //ENUEMRATION FOR BATCH NUMBER ----------- PALLAVI,30/10/09
    final int I_FUEL_SPECIFICGRAVITY=127;
    
    //ENUEMRATION FOR BATCH NUMBER ----------- PALLAVI,30/10/09
    final int I_FUEL_CARBONPERCENT=128;
    
    //ENUEMRATION FOR BATCH NUMBER ----------- PALLAVI,30/10/09
    final int I_FUEL_HCRATIO=129;
    
    //ENUMERATION TO SET TREE NODE ID'com.brixip.controller.loader.usermanagement.servlet ------- PALLAVI,02/11/09
    final int I_GEAR_SHIFT_PATTERN=130;
    
    //ENUMERATION TO SET TREE NODE ID'com.brixip.controller.loader.usermanagement.servlet ------- Asameer,11/02/10 
    final int I_COASTDOWN_REPORT_NO=142;
    
    final int I_MANUFACTURER_DETAILS=131;// ------ PALLAVI,03/11/09 
    
    // GET LIST OF ALL REGULATIONS ------------------------------ PALLAVI,
    // 05/11/09
    final int I_REGULATIONS_LIST=132;
    
    //DELETE SHED TEST ----------- PALLAVI,17/11/09
    final int I_DELETE_SHEDTEST=133;
    
    //DELETE SHED TEST ----------- PALLAVI,17/11/09
    final int I_FILE_PATH=134;
    
    //DELETE SHED TEST ----------- PALLAVI,17/11/09
    final int I_GET_FILE_PATH=135;
    
    //GET FILE INFO ----------- RAJESH,04/02/2010
    final int I_GET_FILE_INFO=55555;
    
    //ENUMERATION FOR GET PROCEDURE NAME ------- PALLAVI,07/12/09
    final int I_PROCEDURE_NAME=136;
    
    // QUALITY PLAN CANCEL LIST ITEM
    final int I_CANCELQP=137;
    
    // GET THE LIST ITEM FOR THE QP CANCEL ACTION
    final int I_CANCEL_ACTION=138;
    
    // QUALITY PLAN DELETE LIST ITEM
    final int I_DELETEQP=139;
    
    //USER DESIGNATION LISTITEM ---------- PALLAVI,22/12/09
    final int I_USER_DESIGNATION=140;
    
    // QUALITY PLAN CANCEL LIST ITEM
    final int I_REVERTQP=141;
    
    final int I_SAVE_SPANVALUES = 143; //added by bdinesh
    final int I_SPANVALUES_LIST = 144;  
    final int I_HOD_CONFIGURATION = 145; //added by bdinesh..for HOD Name 19/03/2011   
    final int I_UPDATE_HOD_DATA = 146; //added by bdinesh.19/03/2011

    //to get list of user having role driver----------------- btushar, 5 June 2013.
	final int I_DRIVER_USER	= 149;
	
	//To get lis of all engineers --------------------- Tushar, 12th Oct, 2013 for engineer wise report
	final int I_ENGINEER_USER	= 154;
	
	//TO Get the COP contact person detail ------------------ Niketa Mane , 11th Oct
	final int I_COPCONTACT	= 155;        
	final int I_CONTACT       = 157;                        // ----------Niketa Mane to load on the data on register view
	final int I_TAAPPROVAL       = 158;                        // ----------Niketa Mane to get the ta aprroval
	
	//.......To get list of all Test Cycles ...tushar, 16/9/2013
	final int I_TESTCYCLE_LIST	  = 150;
	final int I_SAVE_TESTCYCLE	  = 151;
	final int I_DELETE_TESTCYCLE  = 152;
	final int I_TESTCYCLE		  = 153;
	
	final int I_TESTBED_LIST		= 156;
	final int I_SAVE_TESTBED 		= 157;
	final int I_DELETE_TESTBED  	= 158;
    
    //ENUEMRATION FOR TAG ----------- PALLAVI,30/10/09
    final int I_CARBON_PERCENT=5067;
    
    //ENUEMRATION FOR TAG----------- PALLAVI,30/10/09
    final int I_HC_RATIO=5184;
    
    // Enumeration for UUTSUBTYPES FOR ENGINE
    final int I_TRACTOR=5;
    final int I_CEV=6;
    final int I_AUTOMATIVE=7;
    final int I_GENSET=8;
    final int I_POWERTEST=9;
    
    // Enumeration for UUTSUBTYPES FOR VEHICLE
    final int I_4WHEELER=4;
    final int I_3WHEELER=3;
    final int I_2WHEELER=2;
    
    // Enumeration for buttons
    final int I_NEWBTN=1;
    final int I_UDATEBTN=2;
    final int I_DELETEBTN=3;
    final int I_SAVETN=4;
    final int I_CANCELBTN=5;
    final int I_PRINTBTN=6;
    final int I_BACKBTN=7;
    
    final int I_PRINTAXBTN=8;
    final int I_PRINTQPBTN=9;
    final int I_NEWVARIANTBTN=10;
    final int I_APPROVALBTN=11;
    final int I_FINALAPPROVALBTN=12;
    
    // Enumaration for Test Type
    final int I_TTCOP=1;
    final int I_TTEXCOP=2;
    final int I_TTTA=3;
    final int I_TTDEV=4;
    final int I_TTHOMO=5;
    final int I_TTSHED=6;
    
    // Enumaration for Test Bed
    final int I_VTC01=1;
    final int I_VTC02=2;
    final int I_VTC03=3;
    
    final int I_TESTCELL1=4;
    final int I_TESTCELL2=5;
    
    // Enumeration for UUT Type
    final int I_ENGINE=1;
    final int I_VEHICLE=2;
    final int I_EGR=3;
    final int I_ECU=4;
    final int I_SPARKPLUG=5;
    final int I_CANISTER=6;
    final int I_CARBURATOR=7;
    final int I_FIPUMP=8;
    final int I_CYLINDER=9;
    final int I_PRESSUREREGULATOR=10;
    final int I_GASINJECTOR=11;
    final int I_INTERFACTINGUNIT=12;
    final int I_OXYGENSENSOR=13;
    final int I_MULTIFUNCTIONVALVE=14;
    final int I_REFILLINGVALVE=15;
    final int I_FIPPUMPOBSERVED=16;
    final int I_TURBOCHARGED=17;
    final int I_COMMONRAILPUMP=18;
    final int I_COMMONRAILPUMPOBSERVED=19;
    final int I_EGRCONTROLLER=20;
    final int I_GASAIRMIXTURE=21;
    final int I_EGROBSERVED=22;
    final int I_FUELINJECTOR=23;
    final int I_HOTTUBE=24;
    final int I_CATCON=25;
    final int I_INJECTOR=26;
    final int I_GOVERNOR=27;
    final int I_DPF=35;
    
  //Added by niketa mane for engine-vehicle uut type
    final int I_ENGINEVEHICLE= 35;
    
    final int I_ENGINEVEHICLE_TYPE= 10;
    final int I_COPYENGINE=1221;
    
    final String STR_COMPONENTID="COMPONENT_ID";
    
    // Enumarations for the TAG CATEGORIES
    final String STR_TAGCATAGORY="TAGCATAGORY";
    final int I_TC_TEST=1;
    final int I_TC_ENGINE=2;
    final int I_TC_SUMMARY=3;
    final int I_TC_STDCALCULATIONS=4;
    final int I_TC_MODEL=5;
    final int I_TC_VEHICLE=6;
    final int I_TC_COMPONENT=7;
    final int I_TC_FUEL=8;
    
    final String STR_CSPACC="Accepted";
    final String STR_CSPREJ="Rejected";
    
    final String STR_QMAVI="Available";
    final String STR_QMNOTAVI="Not Available";
    
    // Enumeration for get_Items ----- 16/05/09
    final int I_TESTREQUEST=1;
    final String STR_TESTREQUESTID="TESTREQUEST_ID";
    final String STR_TESTREQUESTNUMBER="TESTREQUEST_NUMBER";
    
    // Pallavi, to get list of multipple test requests ----- 15/07/09
    final String STR_TESTREQUESTIDLIST="TESTREQUEST_ID_LIST";
    
    final int I_TESTPLAN=2;
    final String STR_TESTPLANID="TESTPLAN_ID";
    
    final int I_TESTSPECIFICATION=3;
    final String STR_TESTSPECID="TESTSPEC_ID";
    
    // MODIFIED BY UMESH, TO GET THE TEST SPECIFICATION RESULT
    // ------------------------- UMESH, 12-07-2009
    final String STR_TESTSPEC_RESULT="TESTSPEC_RESULT";
    
    final int I_TESTPLANCOMBINATION=4;
    final String STR_TESTPLANCOMID="TESTPLANCOM_ID";
    
    final String STR_ASV_TPCINDEXID="TPCINDEX_ID"; // !< TEST PLAN COMBINATION
    // INDEX ID
    
    final String STR_TESTSTDID="TESTSTD_ID";
    
    final String STR_UUTID="UUT_ID";
    
    final String STR_UUTTYPEID="UUTTYPE_ID";
    
    final String STR_UUTTYPENAME="UUTTYPE_NAME";
    
    final String STR_LEGISLATIONID="LEGISLATION_ID";
    
    final int I_UUTGROUP=5;
    
    final int I_CREATE_TESTPLANCOMBINATION=6;
    
    final int I_WITHDRAWN_TESTPLANCOMBINATION=7;
    
    final int I_UNDO_TESTPLANCOMBINATION=8;
    
    final int I_END_QP=9;
    
    final int I_SEND_TO_APPROVAL=10;
    
    final int I_TYPEOFTESTID=11;
    
    final int I_CREATE_TESTPLAN=12;
    final int I_DELETE_TESTPLAN=13;
    final String STR_TYPEOFTESTID="TYPEOFTEST_ID";
    
    // Enumaration for Tag Type informations
    final String STR_SUMMARY="SUMMARY"; // SUMMARY for getting information if
    
    //added by bdinesh .......16 march 2011
    final String STR_HIGHIDLE = "HIGHIDLE" ; 
    // summary tag
    final String STR_INSTANT="INSTANT"; // INSTANT for getting information of
    // instant tag
    final String STR_RANDOMNOX="RANDOMNOX"; // INSTANT for getting information
    // of RANDOMNOX tag
    
    final String STR_ELRSMOKE="ELRSMOKE"; // ELRSMOKE for getting information
    // of ELRSMOKE tag Values
    
    // ENGINE SPECIFICATION for getting information of engine specification tag
    final String STR_ENGINESPEC="ENGINESPEC";
    
    final String STR_CALIBRATION="CALIBRATION"; // INSTANT for getting
    // information of instant
    // tag
    
    final String STR_TESTTYPE="TESTTYPE";
    final String STR_TESTNUMBER="TESTNUMBER";
    final String STR_TESTTYPEID="TESTTYPE_ID";
    
    final String STR_BEFORE_CALIBRATION="BEFORECALIBRATION";
    final String STR_AFTER_CALIBRATION="AFTERCALIBRATION";
    final String STR_COLUMNARRAY="COLUMNARRAY";
    
    final String STR_SUMMARYRESULT="SUMMARYRESULT"; // SUMMARY for getting
    // information if
    // summary tag
    final String STR_INSTANTRESULT="INSTANTRESULT"; // INSTANT for getting
    // information of
    // instant tag
    
    //added by bdinesh.......21 May 2011
    final String STR_NRTCDATA ="NRTCDATA";
    
    // FOR SHOWING FTP SPECIFIED RESULT AND FTP DECLARED POWER RESULT
    final String STR_FTPSPECRESULT="FTPSPECIFICATIONRESULT"; // FTP
    // SPECIFICATION
    // for getting
    // information
    // if summary
    // tag
    final String STR_FTPDECPOWERRESULT="FTPDECLAREDPOWERRESULT"; // FTP
    // DECLARED
    // POWER for
    // getting
    // information
    // if
    // summary
    // tag
    
    final String STR_FTP80SMOKERESULT="FTP80SMOKERESULT"; // FTP DECLARED
    // POWER for getting
    // information if
    // summary tag
    
    // enumaration for therandom nox result
    // -------------------------------------------- UMESH, 17-07-09
    final String STR_RANDOMNOXRESULT="RANDOMNOXRESULT"; // FTP DECLARED POWER
    // for getting
    // information if
    // summary tag
    
    final String STR_LIMITRESULT="LIMITRESULT"; // LIMITRESULT for getting
    // information of limits tag
    
    // ENUMARATIONS FOR SAVING VARIOUS
    final int I_PREENGINECHECK=1;
    final int I_PREVEHPRE=2;
    final int I_PREEMISSIONTEST=3;
    final int I_FTP=4;
    final int I_FTP80=5;
    final int I_FLS=6;
    final int I_EMISSION=7;
    final int I_PARTICULATE=8;
    final int I_RENDOMNOX=9;
    final int I_POWERMAP=10;
    final int I_ELRSMOKE=11;
    final int I_FAS=12;
    final int I_TYPEII_III_DIESEL=13;
    final int I_TYPEII_III_GASOLINE=14;
    final int I_SHEDTT=15;
    final int I_ETC=16;
    final int I_OBD=17;
    final int I_TYPEII_III_EURO=18;
    final int I_TYPEII_III_CNG=19;
    
    final int I_POSTTEST=20;
    
    // ENUMARATIONS FOR SAVING VARIOUS TEST SPECIFICATION
    final int I_SAVE_PREENGINECHECK=1;
    final int I_SAVE_PREVEHPRE=2;
    final int I_SAVE_PREEMISSIONTEST=3;
    final int I_SAVE_FTP=4;
    final int I_SAVE_FTP80=5;
    final int I_SAVE_FLS=6;
    final int I_SAVE_EMISSION=7;
    final int I_SAVE_PARTICULATE=8;
    final int I_SAVE_RENDOMNOX=9;
    final int I_SAVE_POWERMAP=10;
    final int I_SAVE_ELRSMOKE=11;
    final int I_SAVE_FAS=12;
    final int I_SAVE_TYPEII_III_DIESEL=13;
    final int I_SAVE_TYPEII_III_GASOLINE=14;
    final int I_SAVE_SHEDTT=15;
    final int I_SAVE_ETC=16;
    final int I_SAVE_OBD=17;
    final int I_SAVE_TYPEII_III_EURO=18;
    final int I_SAVE_TYPEII_III_CNG=19;
    final int I_SAVE_POSTTEST=20;
    // UMESH TO SAVE THE INTERPOLATED VALUES OF THE RANDOM NOX VALUES
    // ------------------- UEMSH. 25-07-2009
    final int I_SAVE_INTERPOLATEDNOX=888;
    
    final int I_DISPLAY_PREENGINECHECK=21;
    final int I_DISPLAY_PREVEHPRE=22;
    final int I_DISPLAY_PREEMISSIONTEST=23;
    final int I_DISPLAY_FTP=24;
    final int I_DISPLAY_FTP80=25;
    final int I_DISPLAY_FLS=26;
    final int I_DISPLAY_EMISSION=27;
    final int I_DISPLAY_PARTICULATE=28;
    final int I_DISPLAY_RENDOMNOX=29;
    final int I_DISPLAY_POWERMAP=30;
    final int I_DISPLAY_ELRSMOKE=31;
    final int I_DISPLAY_FAS=32;
    final int I_DISPLAY_TYPEII_III_DIESEL=33;
    final int I_DISPLAY_TYPEII_III_GASOLINE=34;
    final int I_DISPLAY_SHEDTT=35;
    final int I_DISPLAY_ETC=36;
    final int I_DISPLAY_OBD=37;
    final int I_DISPLAY_TYPEII_III_CNG=39;
    final int I_DISPLAY_TYPEII_III_EURO=38;
    final int I_DISPLAY_POSTTEST=40;
    
    final int I_UPDATE_PREENGINECHECK=41;
    final int I_UPDATE_PREVEHPRE=42;
    final int I_UPDATE_PREEMISSIONTEST=43;
    final int I_UPDATE_FTP=44;
    final int I_UPDATE_FTP80=45;
    final int I_UPDATE_FLS=46;
    final int I_UPDATE_EMISSION=47;
    final int I_UPDATE_PARTICULATE=48;
    final int I_UPDATE_RENDOMNOX=49;
    final int I_UPDATE_POWERMAP=50;
    final int I_UPDATE_ELRSMOKE=51;
    final int I_UPDATE_FAS=52;
    final int I_UPDATE_TYPEII_III_DIESEL=53;
    final int I_UPDATE_TYPEII_III_GASOLINE=54;
    final int I_UPDATE_SHEDTT=55;
    final int I_UPDATE_ETC=56;
    final int I_UPDATE_OBD=57;
    final int I_UPDATE_TYPEII_III_CNG=59;
    final int I_UPDATE_TYPEII_III_EURO=58;
    final int I_UPDATE_POSTTEST=60;
    
    // ENUMARATIONS FOR FULE TYPE
    final int I_PETROL=1;
    final int I_DIESEL=2;
    final int I_LPG=3;
    final int I_CNG=4;
    
    final String STR_FUELID="FUELID";
    
    final String STR_FUELTEMPLATEID="FUELTEMPLATE_ID";
    
    // ENUMARATIONS FOR FULE SUB TYPE
    final int I_REFERENCE=1;
    final int I_UNLEADED=2;
    final int I_COMMERCIAL=3;
    final int I_YR2000=4;
    final int I_EUROII=5;
    final int I_EUROIII=6;
    final int I_EUROIV=7;
    final int I_EUROV=8;
    final int I_EUROVI=9;
    
    // ENUMARATIONS FOR TEST SPECICFICATION STATUS
    final int I_OPEN=1;
    final int I_CLOSE=2;
    final int I_WAIT=3;
    
    // ENUMARATIONS FOR TAGTYPE, AND UNIT LIST ITEM
    final String STR_UNIT="UNIT";
    final String STR_TAGTYPE="TAGTYPE";
    final String STR_TAGID="TAGID";
    final String STR_COMPULSORYTAG="COMPULSORY_TAG";
    
    final int TAG_ANALOG_IN=0;
    final int TAG_ANALOG_OUT=1;
    final int TAG_DISCRETE_IN=2;
    final int TAG_DISCRETE_OUT=3;
    final int TAG_MEMORY_INT=4;
    final int TAG_MEMORY_REAL=5;
    final int TAG_MEMORY_DISCRETE=6;
    final int TAG_MEMORY_STR=7;
    
    // ENUMERATONS FOR CONDITIONS
    final int I_EQUAL=1;
    final int I_LESS=2;
    final int I_LESSEQUAL=3;
    final int I_GREATER=4;
    final int I_GREATEREQUAL=5;
    final int I_NOTEQUAL=6;
    final int I_RANGE=7;
    final int I_TOLERANCE=8;
    final int I_MINUS=9;
    final int I_PLUS=10;
    final int I_PLUSMINUS=11;
    
    // ENUMERATONS FOR ITEM PROPERTY
    final String STR_FROM="FROM_ID";
    final String STR_GET="GET_ID";
    
    // ENUMARATIONS FOR BY_DATASOURCE
    final String STR_DATASOURCE="DATASOURCE";
    final int BY_DATABASE=1;
    final int BY_FILE=0;
    final String STR_DATASOURCENAME="DATASOURCENAME";
    
    // ENUMARATIONS FOR MIMETYPE
    final String STR_MIMITYPE="MIMITYPE";
    final int I_MIME_SUMMARY=1;
    final int I_MIME_TEST=2;
    final int I_MIME_CALIBRATION=3;
    
    final int I_MIME_VEHICLEPARA=4;
    final int I_MIME_ELR=5;
    
    // MODIFIED BY UMESH TO SAVE ENGINEPARA TO THE SUMMARYDETAILS_TBL.
    // ---------------------- 25-06-2009
    final int I_MIME_DECLARED=6;
    final int I_MIME_ENGINEPARA=7;
    
    // MODIFIED BY UMESH TO SAVE ENGINE test PARA TO THE SUMMARYDETAILS_TBL.
    // ---------------- 16-07-2009
    final int I_MIME_ENGINETESTPARA=8;
    
    //Added by bdinesh, 18 May 2011 .....for NRTC Test .......18 May 2011
    final int I_MIME_NRTCPARA=9;
    
    final String STR_SPEED = "Speed";
    final String STR_POWER = "Power";
    final String STR_TORQUE = "Torque";
    
    //ID'com.brixip.controller.loader.usermanagement.servlet used in case of NRTC Parameter View
    final int I_SPEED = 1;
    final int I_POWER = 2;
    final int I_TORQUE = 3;
    
    
    
    final String STR_MESAUREMENT="MESAUREMENT";
    
    // ENUMERATION FOR ROLE
    final String STR_ROLE="ROLE_NAME";
    
    // ITEM IS IN USED   
    final int I_PROBLEM=-1;
    
    // ENUMARATIONS FOR DATABASE OPERATIONS
    final int I_SAVE=1;
    final int I_UPDATE=2;
    final int I_DELETE=3;
    
    // ENUMERATION FOR TESTSPECIFICATION PAGE VIEW/ACTION
    final String STR_TESTSPECIFICATION_ACTION="TSCALL";
    
    // ENUMERATION FOR TESTSPECIFICATION DATA SAVE ACTION
    final String STR_TESTSPECIFICATION_SAVE_ACTION="TSSAVE";
    
    final String OBJ_PREENGINECHECK="PREENGINECHECKINFO";
    
    // ENUMARATIONS FOR THE SAVING DATA WHICH REQUIRED TO STORE DATA
    // FOR WHOLE APPLICATIONS
    final String STR_ASV_TRUUTTYPE="TRUUT_TYPE"; // !< TEST REQUEST UUT TYPE
    final String STR_ASV_TRUUTSUBTYPE="TRUUTSUB_ID"; // !< TEST REQUEST UUT
    // SUB TYPE
    
    final String STR_ASV_TRUUTID="TRUUT_ID"; // !< TEST REQUEST UUT ID
    final String STR_ASV_TRLEGISLATIONID="TRLEGISLATION_ID"; // !< TEST
    // REQUEST
    // LEIGISLATION
    final String STR_ASV_TRTESTSTD="TRTESTSTD_ID"; // !< TEST REQUEST TEST
    // STANDARD
    final String STR_ASV_TRTYPEOFTEST="TYPEOFTEST"; // !< TEST REQUEST TYPE OF
    // TEST
    final String STR_ASV_TPSUBINDEXID="TESTPLANSUBINDEX_ID"; // !< TEST PLAN
    // SUBINDEX ID
    
    final String STR_ASV_TESTREQ="TESTREQ_ID"; // !< TEST REQUEST ID
    final String STR_ASV_TESTPLAN="TESTPLAN_ID"; // !< TEST PLAN ID
    final String STR_ASV_TESTSPCE="TESTSPCE_ID"; // !< TEST SPECIFICATION ID
    
    final String STR_ASV_FUELID="TESTFUEL_ID"; // !< TEST REQUEST FUEL ID
    
    final String STR_CURRENT_STEP="CURRENTSTEP";
    final String STR_NEXT_STEP="NEXTSTEP";
    
    
    // ENUMARATION FOR TEMPLATE ID
    final String STR_TEMPLATEID="TEMPLATE_ID";
    
    final String STR_PLANTID		= "PLANTID";
    final String STR_ASV_TESTBED		= "TESTBED_ID";
    
    
    // uut name
    final String STR_UUTNAME="UUTNAME";
    // variant id
    final String STR_VARIANTID="VARIANTID";
    
    final int I_IDLE_RPM=6002;
    final int I_RPM_NO_LOAD=6003;
    final int I_RPM_WITH_LOAD=6004;
    
    // ENUMERATIONS FOR IDLE TEST PARAMETERS ------- PALLAVI,21/08/09
    final int I_VIDLE_CO=5018;
    final int I_VIDLE_HC=5019;
    final int I_VIDLE_RPM=5017;
    final int I_VIdle_O2=5206; // ENUMERATIONS FOR IDLE TEST PARAMETERS ------- RAJESH,20/01/10
    final int I_VIdle_Lambda=5207;
    
    // ENUMERATIONS FOR HIGH IDLE TEST PARAMETERS ------- PALLAVI,21/08/09
    final int I_VHIGH_CO=5018;
    final int I_VHIGH_HC=5019;
    final int I_VHIGH_RPM=5017;
    final int I_VHIGH_O2=5208; // ENUMERATIONS FOR HIGH IDLE TEST PARAMETERS ------- RAJESH,20/01/10
    final int I_VHIGH_Lambda=5209;
    final int EngineOil_Temp=5210;
    
    // ENUMERATIONS FOR PHASE COUNT
    final String STR_PHASE_COUNT="PHASE_COUNT";
    
    final int I_PHASE_COUNT=4001;
    
    // FIXED TAGS FOR THE EMISSION RESULT SHOWING
    final int L_VFINAL_CO=5026;
    final int L_VFINAL_HC=5027;
    final int L_VFINAL_NOx=5028;
    final int L_VFINAL_HC_NOx=5029;
    final int L_VFINAL_PM=5030;
    final int L_VFINAL_RHC=5032;
    final int L_VFINAL_NMHC=5031;
    final int L_VFINAL_FC=5143;
    final int L_VFINAL_CO2=5144;
    
    final int L_EFINAL_CO=5131;
    final int L_EFINAL_HC=5132;
    final int L_EFINAL_NOx=5133;
    final int L_EFINAL_HC_NOx=5140;
    final int L_EFINAL_PM=5139;
    final int L_EFINAL_RHC=5141;
    final int L_EFINAL_NMHC=5142;
    final int L_EFINAL_FC=5143;
    final int L_IDLE_CO=5177;
    final int L_IDLE_HC=5178;
    
    final int L_EFINAL_CO_ORG=5195;
    final int L_EFINAL_HC_ORG=5196;
    final int L_EFINAL_NOx_ORG=5197;
    final int L_EFINAL_HC_NOx_ORG=5199;
    final int L_EFINAL_PM_ORG=5198;
    final int L_EFINAL_RHC_ORG=5141;
    final int L_EFINAL_NMHC_ORG=5142;
    final int L_EFINAL_FC_ORG=5143;
    final int L_IDLE_CO_ORG=5177;
    final int L_IDLE_HC_ORG=5178;
    
    // ADDED BY UMESH TO SAVE THE SPE. NOX. INTERPOLATED VALUE --------------
    // UMESH, 01-08-2009
    final int L_NOX_INTERPOLATED=5015;
    
    // ENUMARATIONS FOR THE RESULT VALUES
    final int enParameterNoResult=-1;
    final int enParameterFail=0;
    final int enParameterPass=1;
    final int enParameterGo_V2_Test=2;
    final int enParameterGo_V3_Test=3;
    final int enParameterMore_Sample=4;
    
    // for getting family
    final String STR_FAMILY="FAMILY";
    
    // for getting financial year                                added by niketa mane
    final String STR_FINANCIAL_YEAR="FINANCIALYEAR";
    
    // ENUMARATIONS FOR THE FUNCTION NAMES
    final int I_FUN_USER_CONFIG=1; // !< User Configuration
    final int I_FUN_OENT_TAG_CONFIG=2; // !< OENT Tag Configuration
    final int I_FUN_TR_CONFIG=3; // !< Create Test Request (Quality Plan)
    final int I_FUN_TR_APPROVE=4; // !< Approve Test Request (Quality Plan)
    final int I_FUN_TP_EXECUTE=5; // !< Execute Test Plans (Tests)
    final int I_FUN_GENERATE_REPORT=6; // !< Generate Reports
    final int I_FUN_VEHICLE_CONFIG=7; // !< Vehicle configuration
    final int I_FUN_ENGINE_CONFIG=8; // !< Engine configuration
    final int I_FUN_TEST_CONFIG=9; // !< Test Configuration
    final int I_FUN_COMPONENT_CONFIG=10; // !< Component Configuration
    final int I_FUN_FUEL_CONFIG=11; // !< Fuel Configuration
    final int I_FUN_REGULATION_CONFIG=12; // !< Regulation Configuration
    final int I_FUN_CUSTOMER_CONFIG=13; // !< Manufacturer configuration
    final int I_FUN_MANUAL_TEMPLATE_CONFIG=14; // !< Manual template
    // configuration
    final int I_FUN_OBJECTIVE_CONFIG=15; // !< Objectives Configuration
    final int I_FUN_REPORT_DATA_CONFIG=16; // !< Report Data Configuration
    final int I_FUN_TEMPLATE_CONFIG=17; // !< Template Configuration
    final int I_FUN_LIST_PENDING_FOR_APPROVAL=18; // !< Pending For Approval
    // Test Requests
    final int I_FUN_LIST_PENDING_FOR_TEST=19; // !< Pending For Test Test
    // Requests
    final int I_FUN_LIST_PENDING_FOR_FINAL_APPROVAL=20; // !< Pending For
    // Final Approval
    // Test Requests
    final int I_FUN_LIST_PENDING_FOR_REPORT=21; // !< Pending For Report Test
    // Requests
    final int I_FUN_LIST_ALL_TEST_REQUESTS=22; // !< Show All Test Requests
    final int I_FUN_MANUFACTURER_PLANT_CONFIG=23; // !< Manufacturer Plant
    // Configuration
    
    final String STR_MANUFACTURERID="MANUFACTURER_ID";
    
    // MODIFIED BY UMESH, FOR QP APPRIVAL ACTION
    // ---------------------------------------------- UMESH, 25-06-2009
    final String STR_APPROVEACTION="APPROVE_ACTION";
    
    // MODIFIED BY UMESH, FOR THE TEST REQUEST STATUS
    // ----------------------------------------- UMESH, 25-06-2009
    // ENUMARATIONS FOR THE FUNCTION NAMES
    final int I_TR_PENDING_APPROVE=1; // !< TEST REQUEST PENDING FOR APPRAVAL
    final int I_TR_PENDING_TEST=2; // !< TEST REQUEST PENDING FOR TEST
    final int I_TR_PENDING_FINAL_APPROVE=3; // !< TEST REQUEST PENDING FOR
    // FINAL APPROVAL
    final int I_TR_PENDING_REPORT=4; // !< TEST REQUEST PENDING FOR REPORT
    final int I_TR_PENDING_ALL_TEST_COMPLETE=5; // !< TEST REQUEST ALL TEST
    // ARE COMPLETED
    final int I_TR_CLOSED=6; // !< TEST REQUEST CLOSE
    final int I_TR_CANCELED=7; // !< TEST REQUEST CANCELED
    
    // enumarations for the CACTYPE tag values
  /*  final int I_NATURALLY_ASPIRED=1;
    final int I_MECH_SUPERCHARAGED=2;
    final int I_WITHOUT_CAC_TURBOCHARGED=3;
    final int I_AIR_TO_AIR_TURBOCHARGED=4;
    final int I_JACKET_WATER_TURBOCHARGED=5;
    final int I_SPARK_IGNITION=6;*/
    
    
    final int I_NATURALLY_ASPIRED          = 0;
    final int I_AIR_TO_AIR_TURBOCHARGED    = 3;//prev 1 //or 4
    final int I_WITHOUT_CAC_TURBOCHARGED   = 2; // or 3 
	
    
    final String STR_NATURALLY_ASPIRED="Naturally Aapired";
 //   final String STR_MECH_SUPERCHARAGED="Mech. Supercharaged";
    final String STR_WITHOUT_CAC_TURBOCHARGED="Turbocharged"; //prev:Without CAC Turbocharged
    final String STR_AIR_TO_AIR_TURBOCHARGED="Turbocharged with IC"; //prev:Air To Air_Turbocharged
  //  final String STR_JACKET_WATER_TURBOCHARGED="Jacket Water Turbocharged";
   // final String STR_SPARK_IGNITION="Spark Ignition";
    
    // ENUMERATIONS FOR TAGS OF PARTICULATE TEST -------------- Pallavi,05/07/09
    final int I_PARTICULATE_MASS=5173;
    final int I_GEDF_WEIGHTED=5158;
    final int I_MSAM_TOTAL=5159;
    final int I_POWER_WEIGHTED=5160;
    final int I_WF_EFFECTIVENESS=5161;
    final int I_GEDF_DEVIATION=5162;
    final int I_MAX_TENP=5163;
    
    final int I_V_PARTICULATE_NO = 7046;  //ADDED BY TUSHAR...21-6-2013   TO STORE PARTICULATE NUMBER IN SUMMARYDETAILS_TBL
    
    //Added by bdinesh -----------------------------------------08/12/2010
    final int I_E_LEAK_CHECK_SYSTEM = 5211; //Prev 5212
    
    final int I_E_FILTER_INLET_PRESSURE = 5212; //Prev 5213 
    
    // ENUMERATIONS FOR TAGS OF FAS TEST -------------- Pallavi,11/07/09
    final int I_SMOKE_FAS=5153;
    
    // ENUMERATIONS FOR TAGS OF SMOKE -------------- UMESH,21/10/09
    final int I_SMOKE=19;
    
    // ENUMERATIONS FOR TAGS OF RATED SPEED -------------- UMESH,21/10/09
    final int I_RATEDSPEED=5138;
    
    // ENUMERATIONS FOR TAGS OF FAS TEST -------------- Pallavi,12/10/09
    // final int I_TOIL_FAS=5183; // old tag for ARAI_SITE Data base 
    final int I_TOIL_FAS=5194;
    
    // ENUMERATIONS FOR TAGS OF FAS TEST -------------- Pallavi,12/10/09
    // final int I_TWATEROUT_FAS=5182; // old tag for ARAI_SITE Data base
    final int I_TWATEROUT_FAS=5193;
    
    // ENUMERATIONS FOR TAGS OF POSTTEST TEST -------------- Pallavi,13/07/09
    final int I_INJECTION_TIMING=5149;
    final int I_FLYUP_SPEED=4011;
    final int I_IDLE_SPEED=4012;
    
    // ENUMERATIONS FOR TAGS OF VEHICLE PARTICULATE TEST --------------
    // Pallavi,16/07/09
    final int I_PRI_BEFORE=5035;
    final int I_PRI_AFTER=5036;
    final int I_SEC_BEFORE=5037;
    final int I_SEC_AFTER=5038;
    
    // ENUMERATIONS FOR TAGS OF VEHICLE PARTICULATE TEST PHASE COUNT
    // -------------- Pallavi,16/07/09
    final int I_PHASE_I=1;
    final int I_PHASE_II=2;
    final int I_PHASE_III=3;
    
    // ENUMERATIONS FOR TAGS OF VEHICLE EMISSION SUMMARY TEST --------------
    // Pallavi,17/07/09
    final int I_REL_HUM=7;
    final int I_ABS_HUM=5016;
    final int I_BARO_PRE=8;
    final int I_TEST_DATE=7033;
    final int I_AMB_TEMP=6;
    final int I_LUBE_OIL_TEMP=5034;
    final int I_SOAK_ROOM_TEMP=5033;
    
    // ENUMERATIONS FOR TAGS OF VEHICLE EMISSION INSTANT TEST --------------
    // Pallavi,17/07/09
    final int I_VMIX=5008;
    final int I_DISTANCE=5009;
    final int I_TUNNEL_VOLUME=5013;
    
    // ENUMERATIONS FOR TAGS OF VEHICLE EMISSION INSTANT TEST FOR SAMPLE
    // PHASE-------------- Pallavi,17/07/09
    final int I_SAMPLE_CO=2;
    final int I_SAMPLE_HC=4;
    final int I_SAMPLE_NOX=5;
    final int I_SAMPLE_CO2=3;
    
    // ENUMERATIONS FOR TAGS OF VEHICLE EMISSION INSTANT TEST FOR AMBIENT
    // PHASE-------------- Pallavi,17/07/09
    final int I_AMBIENT_CO=5001;
    final int I_AMBIENT_HC=5003;
    final int I_AMBIENT_NOX=5004;
    final int I_AMBIENT_CO2=5002;
    
    // REPORT ENUMARATIONS FOR REPORT GENERATIONS
    // ----------------------------------------- UMESH, 01/08/2009
    // ////////////////////////////////////////////////////////////////////////////////////
    // UMESH, 01/08/2009\
    final String REPORT_TRID="REPORT_TRID"; // !< TEST REQUEST ID

    // added by Rajesh for generating report of ecop having linking with ecop & cop
    // 08/04/2010
    
    final String REPORT_ECOPTRID1="REPORT_ECOPTRID1"; // !< TEST REQUEST ID
    final String REPORT_ECOPTRID2="REPORT_ECOPTRID2"; // !< TEST REQUEST ID
    final String REPORT_ECOPTRID3="REPORT_ECOPTRID3"; // !< TEST REQUEST ID
    final String REPORT_ECOPTRID4="REPORT_ECOPTRID4"; // !< TEST REQUEST ID
    final String REPORT_ECOPTRID5="REPORT_ECOPTRID5"; // !< TEST REQUEST ID
    final String REPORT_COPTRID1="REPORT_COPTRID1"; // !< TEST REQUEST ID
    
    final String REPORT_TPID="REPORT_TPID"; // !< TEST PLAN ID
    
    final String REPORT_TR_TESTBED="REPORT_TR_TESTBED"; // !< TEST BED ID
    final String REPORT_TR_TESTLEGISLATION="REPORT_TR_TESTLEGISLATION"; // !<
    // TEST
    // LEGISLATION
    // ID
    final String REPORT_TR_UUTSUBTYPE="REPORT_TR_UUTSUBTYPE"; // !< TEST UUT
    // SUB TYPE ID
    final String REPORT_TR_UUTTYPE="REPORT_TR_UUTTYPE"; // !< TEST UUT TYPE ID
    final String REPORT_TR_UUTID="REPORT_TR_UUTID"; // !< TEST UUT TYPE ID
    final String REPORT_TR_FUEL="REPORT_TR_FUEL"; // !< TEST FUEL ID
    final String REPORT_TR_SUBFUEL="REPORT_TR_SUBFUEL"; // !< TEST SUB FUEL ID
    final String REPORT_TR_TYPEOFTEST="REPORT_TR_TYPEOFTEST"; // !< TYPE OF
    final String REPORT_TR_TESTBEDID="REPORT_TR_TESTBEDID";
    // TEST ID
    final String REPORT_TR_TESTSTD="REPORT_TR_TESTSTD"; // !< TEST STANDARD
    
    final String REPORT_TSID_FTP="REPORT_TSID_FTP"; // !< TEST SPECIFICATION
    
    final String REPORT_TYPE="REPORT_TYPE"; // !< TEST SPECIFICATION
    // ID FOR FTP
    final String REPORT_TSID_FTP80="REPORT_TSID_FTP80"; // !< TEST
    // SPECIFICATION ID
    // FOR FTP80
    final String REPORT_TSID_EMISSION="REPORT_TSID_EMISSION"; // !< TEST
    
    // ADDED BY RAJESH ON 25/11/2009
    final String REPORT_START_DATE="REPORT_START_DATE"; // !< TEST
    final String REPORT_END_DATE="REPORT_END_DATE"; // !< TEST
    
    //Added by bdinesh......for Stroke Type Analysis 21 March 2011
    
    final String REPORT_STROKE_TYPE = "REPORT_STROKE_TYPE";
    
    final String REPORT_TESTBED = "REPORT_TESTBED";
    // SPECIFICATION
    // ID FOR
    // EMISSION
    final String REPORT_TSID_CURRENT="REPORT_TSID_CURRENT"; // !< TEST
    
    final String REPORT_TSID_FAS="REPORT_TSID_FAS"; // !< TEST SPECIFICATION
    // ID FOR FAS
    final String REPORT_TSID_FLS="REPORT_TSID_FLS"; // !< TEST SPECIFICATION
    // ID FOR FLS
    final String REPORT_TSID_ELRSMOKE="REPORT_TSID_ELRSMOKE"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // ELRSMOKE
    final String REPORT_TSID_PARTICULATE="REPORT_TSID_PARTICULATE"; // !< TEST
    // SPECIFICATION
    // ID
    // FOR
    // PARTICULATE
    final String REPORT_TSID_RANDOMNOX="REPORT_TSID_RANDOMNOX"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // RANDOMNOX
    final String REPORT_TSID_TYPEII_DIESEL="REPORT_TSID_TYPEII_DIESEL"; // !<
    // TEST
    // SPECIFICATION
    // ID
    // FOR
    // TYPEII_DIESEL
    final String REPORT_TSID_TYPEII_GASOLINE="REPORT_TSID_TYPEII_GASOLINE"; // !<
    // TEST
    // SPECIFICATION
    // ID
    // FOR
    // TYPEII_GASOLINE
    final String REPORT_TSID_PREENGINECHECK="REPORT_TSID_PREENGINECHECK"; // !<
    // TEST
    // SPECIFICATION
    // ID
    // FOR
    // PREENGINECHECK
    final String REPORT_TSID_PREVEHICLE="REPORT_TSID_PREVEHICLE"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // PREVEHICLE
    final String REPORT_TSID_PREEMISSION="REPORT_TSID_PREEMISSION"; // !< TEST
    // SPECIFICATION
    // ID
    // FOR
    // PREEMISSON
    final String REPORT_TSID_POSTCHECK="REPORT_TSID_POSTCHECK"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // POSTCHECK
    
    final String REPORT_TSID_SHED="REPORT_TSID_SHED"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // SHED
    final String REPORT_TSID_ETC="REPORT_TSID_ETC"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // ETC
    final String REPORT_TSID_OBD="REPORT_TSID_OBD"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // OBD
    final String REPORT_TSID_TYPEII_III_EURO="REPORT_TSID_TYPEII_III_EURO"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // HIGH_GASOLINE
    
    final String REPORT_TSID_IDLEING_CNG="REPORT_TSID_IDLEING_CNG"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // IDLEING_CNG
    
    final String REPORT_TT_FTP="REPORT_TT_FTP"; // !< TEST SPECIFICATION ID
    // FOR FTP
    final String REPORT_TT_FTP80="REPORT_TT_FTP80"; // !< TEST SPECIFICATION
    // ID FOR FTP80
    final String REPORT_TT_EMISSION="REPORT_TT_EMISSION"; // !< TEST
    // SPECIFICATION ID
    // FOR EMISSION
    final String REPORT_TT_FAS="REPORT_TT_FAS"; // !< TEST SPECIFICATION ID
    // FOR FAS
    final String REPORT_TT_FLS="REPORT_TT_FLS"; // !< TEST SPECIFICATION ID
    // FOR FLS
    final String REPORT_TT_ELRSMOKE="REPORT_TT_ELRSMOKE"; // !< TEST
    // SPECIFICATION ID
    // FOR ELRSMOKE
    final String REPORT_TT_PARTICULATE="REPORT_TT_PARTICULATE"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // PARTICULATE
    final String REPORT_TT_RANDOMNOX="REPORT_TT_RANDOMNOX"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // RANDOMNOX
    final String REPORT_TT_TYPEII_DIESEL="REPORT_TT_TYPEII_DIESEL"; // !< TEST
    // SPECIFICATION
    // ID
    // FOR
    // TYPEII_DIESEL
    final String REPORT_TT_TYPEII_GASOLINE="REPORT_TT_TYPEII_GASOLINE"; // !<
    // TEST
    // SPECIFICATION
    // ID
    // FOR
    // TYPEII_GASOLINE
    final String REPORT_TT_PREENGINECHECK="REPORT_TT_PREENGINECHECK"; // !<
    // TEST
    // SPECIFICATION
    // ID
    // FOR
    // PREENGINECHECK
    final String REPORT_TT_PREVEHICLE="REPORT_TT_PREVEHICLE"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // PREVEHICLE
    final String REPORT_TT_PREEMISSION="REPORT_TT_PREEMISSION"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // PREEMISSON
    final String REPORT_TT_POSTCHECK="REPORT_TT_POSTCHECK"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // POSTCHECK
    final String REPORT_TT_SHED="REPORT_TT_SHED"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // SHED
    final String REPORT_TT_ETC="REPORT_TT_ETC"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // ETC
    final String REPORT_TT_OBD="REPORT_TT_OBD"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // OBD
    final String REPORT_TT_TYPEII_HIGH_GASOLINE="REPORT_TT_TYPEII_HIGH_GASOLINE"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // HIGH_GASOLINE
    
    final String REPORT_TT_IDLEING_CNG="REPORT_TT_IDLEING_CNG"; // !< TEST
    // SPECIFICATION
    // ID FOR
    // IDLEING_CNG
    
    //This is for storing the testplan indiexes for the ftp test
    final String REPORT_FTP_TESTPLAN_INDEX1="REPORT_FTP_TESTPLAN_INDEX1";
    
    //This is for storing the testplan indiexes for the ftp test
    final String REPORT_FTP_TESTPLAN_INDEX2="REPORT_FTP_TESTPLAN_INDEX2";
    
    //This is for storing the testplan indiexes for the ftp test
    final String REPORT_FTP_TESTPLAN_INDEX3="REPORT_FTP_TESTPLAN_INDEX3";
    
    //This is for storing the testplan indiexes for the ftp test
    final String REPORT_FTP_TESTPLAN_INDEX4="REPORT_FTP_TESTPLAN_INDEX4";
    
    //This is for storing the testplan indiexes for the ftp test
    final String REPORT_FTP_TESTPLAN_INDEX5="REPORT_FTP_TESTPLAN_INDEX5";
    
    //This is for storing the testplan indiexes for the ftp test
    final String REPORT_FTP_TESTPLAN_INDEX6="REPORT_FTP_TESTPLAN_INDEX6";
    
    final String FTP_CONDUCTED="REPORT_TT_FTP_CONDUCTED"; // !< FTP TEST
    // CONDUCTED OR NOT
    final String FTP80_CONDUCTED="FTP80_CONDUCTED"; // !< FTP80 TEST CONDUCTED
    // OR NOT
    final String EMISSION_CONDUCTED="EMISSION_CONDUCTED"; // !< EMISSION TEST
    // CONDUCTED OR NOT
    final String FAS_CONDUCTED="FAS_CONDUCTED"; // !< FAS TEST CONDUCTED OR
    // NOT
    final String FLS_CONDUCTED="FLS_CONDUCTED"; // !< FLS TEST CONDUCTED OR
    // NOT
    final String ELRSMOKE_CONDUCTED="ELRSMOKE_CONDUCTED"; // !< ELRSMOKE TEST
    // CONDUCTED OR NOT
    final String PARTICULATE_CONDUCTED="PARTICULATE_CONDUCTED"; // !<
    // PARTICULATE
    // TEST
    // CONDUCTED
    // OR NOT
    final String RANDOMNOX_CONDUCTED="RANDOMNOX_CONDUCTED"; // !< RANDOMNOX
    // TEST
    // CONDUCTED OR
    // NOT
    final String TYPEII_DIESEL_CONDUCTED="TYPEII_DIESEL_CONDUCTED"; // !<
    // TYPEII_DIESEL
    // TEST
    // CONDUCTED
    // OR
    // NOT
    final String TYPEII_GASOLINE_CONDUCTED="TYPEII_GASOLINE_CONDUCTED"; // !<
    // TYPEII_GASOLINE
    // TEST
    // CONDUCTED
    // OR
    // NOT
    final String PREENGINECHECK_CONDUCTED="PREENGINECHECK_CONDUCTED"; // !<
    // PREENGINECHECK
    // TEST
    // CONDUCTED
    // OR
    // NOT
    final String PREVEHICLE_CONDUCTED="PREVEHICLE_CONDUCTED"; // !< PREVEHICLE
    // TEST
    // CONDUCTED OR
    // NOT
    final String PREEMISSION_CONDUCTED="PREEMISSION_CONDUCTED"; // !<
    // PREEMISSON
    // TEST
    // CONDUCTED
    // OR NOT
    final String POSTCHECK_CONDUCTED="POSTCHECK_CONDUCTED"; // !< POSTCHECK
    // TEST
    // CONDUCTED OR
    // NOT
    // template fixed values enumaration
    final String STR_="POSTCHECK_CONDUCTED"; // !< POSTCHECK TEST CONDUCTED OR
    // NOT
    
    final String MIMETYPE_SUMMARY="MIMETYPE_SUMMARY"; // !< MIME TYPE FOR
    // SUMMARY
    final String MIMETYPE_TEST="MIMETYPE_TEST"; // !< MIME TYPE FOR TEST
    final String MIMETYPE_CALIBRATION="MIMETYPE_CALIBRATION"; // !< MIME TYPE
    // FOR
    // CALIBRATION
    final String MIMETYPE_VEHICLEPARA="MIMETYPE_VEHICLEPARA"; // !< MIME TYPE
    // FOR VEHICLE
    // PARA
    final String MIMETYPE_ELR="MIMETYPE_ELR"; // !< MIME TYPE FOR ELR
    final String MIMETYPE_DECLARED="MIMETYPE_DECLARED"; // !< MIME TYPE FOR
    // DECLARED
    final String MIMETYPE_ENGINEPARA="MIMETYPE_ENGINEPARA"; // !< MIME TYPE
    // FOR ENGINE
    // PARA
    
    // ENUMERATIONS FOR REPORT GENERATION PROCESS -------------- UMESH, 12/07/09
    final int I_RF_FINALREPORT=9001;
    final int I_RF_ANNEXURE=9002;
    final int I_RF_QPPRINT=9003;
    final int I_RF_ANALYSIS=9004;
    final int I_RF_ANNEXURE_CUST=9005;
    final int I_RF_ANNEXURE_TEST=9006;
    final int I_RF_DRIVER_ANALYSIS=9007; 
    final int I_RF_DELAY_ANLAYSIS = 9008;   // ADDED BY TUSHAR FOR DELAY ANALYSIS REPORT GENERATION ON 19/07/2013
    
    final String STR_ANNEXURE_TYPE="ANNEXURE_TYPE";
    final int I_FTP_ANNEXURE=8001;
    final int I_FTP80_ANNEXURE=8002;
    final int I_EMISSION_ANNEXURE=8003;
    final int I_RANDOMNOX_ANNEXURE=8004;
    final int I_ELR_ANNEXURE=8005;
    final int I_ETC_ANNEXURE=8006;
    final int I_PARTICULATE_ANNEXURE=8007;
    final int I_FAS_ANNEXURE = 8008; // Added by bdinesh,18 Feb 2012.
    
    final String STR_FTP = "FTP";
    final String STR_FTP80 = "FTP80";
    final String STR_EMISSION = "EMISSION";
    final String STR_ELR = "ELR";
    final String STR_ETC = "ETC";
    final String STR_PARTICULATE = "PARTICULATE";
    final String STR_FAS = "FAS";
    
    
    
    
    // ENUMERATIONS FOR TEST BED EQUIPMENTS -------------- Rajesh,07/08/09
    final int I_CHASSISDYNO_MAKE=1;
    final int I_CHASSISDYNO_TYPE=2;
    final int I_COOLINGFAN_MAKE=3;
    final int I_COOLINGFAN_TYPE=4;
    
    final int I_DRIVERAID_MAKE=5;
    final int I_DRIVERAID_TYPE=6;
    final int I_CVS_MAKE=7;
    final int I_CVS_TYPE=8;
    
    final int I_ANALYSISSYSTEM_MAKE=9;
    final int I_ANALYSISSYSTEM_TYPE=10;
    
    // ENUMERATIONS FOR TEST OBJECTIVES -------------- Rajesh,25/08/09
    final int I_UP_6_PASSENGER=0;
    final int I_MORETHAN_6_PASSENGER=1;
    final int I_OTHERTHANPASSENGER_VEHICLE=2;
    
    // ENUMERATIONS FOR SEARCH FILTER COMPONETS ---------- PALLAVI,27/08/09
    final int I_VBOX=1;
    final int I_HBOX=2;
    final int I_LABEL=3;
    final int I_TEXTINPUT=4;
    final int I_COMBOBOX=5;
    final int I_DATEFIELD=6;
    final int I_BUTTON=7;
    final int I_RADIOBTN=8;
    
    // ENUMERATIONS FOR CONFIGUARTION MODULE SUBTYPES ------- PALLAVI,31/08/09
//    final int I_USER_TAB=9001;
//    final int I_ROLE_TAB=9002;
//    final int I_ACCESS_RIGHTS_TAB=9003;
//    final int I_MANUFACTURER_TAB=9004;
//    final int I_PLANT_TAB=9005;
//    final int I_VEHICLE_TAB=9006;
//    final int I_VARIANT_TAB=9007;
//    final int I_ENGINE_TAB=9008;
//    final int I_ENGINE_FAMILY_TAB=9009;
//    final int I_COMPONENT_TAB=9010;
//    final int I_COMPONENT_CONFIGURATION_TAB=9011;
//    final int I_MANUAL_DATA_TAB=9012;
//    final int I_REGULATION_TAB=9013;
//    final int I_FUEL_TAB=9014;
//    final int I_OBJECTIVE_TAB=9015;
//    final int I_TEST_EQUIPMENT_TAB=9016;
//    final int I_REPORT_CONFIGURATION_TAB=9017;
//    final int I_ENGINE_CMVR_TAB=9018;
//    final int I_VEHICLE_TEMPLATE_TAB=9019;
//    final int I_ENGINE_TEMPLATE_TAB=9020;
//    final int I_FUEL_TEMPLATE_TAB=9021;
    
    
    final int I_USER_TAB=9001;
    final int I_ROLE_TAB=9002;
    final int I_ACCESS_RIGHTS_TAB=9003;
    final int I_MANUFACTURER_TAB=9004;
    final int I_PLANT_TAB=9005;
    final int I_VEHICLE_TAB=9006;
    final int I_VARIANT_TAB=9007;
    final int I_ENGINE_TAB=9008;
    final int I_ENGINE_FAMILY_TAB=9009;
    final int I_ENGINEVEHICLE_TAB=9010;
    final int I_COMPONENT_TAB=9011;
    final int I_COMPONENT_CONFIGURATION_TAB=9012;
    final int I_MANUAL_DATA_TAB=9013;
    final int I_REGULATION_TAB=9014;
    final int I_FUEL_TAB=9015;
    final int I_OBJECTIVE_TAB=9016;
    final int I_TEST_EQUIPMENT_TAB=9017;
    final int I_REPORT_CONFIGURATION_TAB=9018;
    final int I_ENGINE_CMVR_TAB=9019;
    final int I_VEHICLE_TEMPLATE_TAB=9020;
    final int I_ENGINE_TEMPLATE_TAB=9021;
    final int I_FUEL_TEMPLATE_TAB=9022;
    
    
    //final int I_ENGINEVEHICLE_TAB=9024;
	
    final String STR_CONFIGURATION_TAB="CONFIGUATION_TAB";
    
    final String STR_SEARCHWORD="SEARCHWORD";
    
    final int I_TA_Fail_Report=0;
    final int I_TA_Pass_Report=1;
    final int I_TA_Withdrawal_Report=2;
    
    // ENUMERATIONS FOR PROCEDURE TYPES -------------- Pallavi,23/10/09
    final String STR_PROCEDURE_TYPE="PROCEDURE_TYPE";
    
    final int I_TEST_PROCEDURE=1;
    final int I_WARMUP_PROCEDURE=2;
    final int I_TEST_FUEL_PROCEDURE=3;
    
    // ENUMERATIONS FOR COP REPORT GENERATION ------------ PALLAVI,28/10/09
    final int I_SAVE_CURRENT_COP=1;
    final int I_SAVE_PREV_COP=2;
    final int I_SAVE_COP_VALUES=3;
    final int I_SAVE_TA_VALUES=6;
    
    //ENUMERATIONS FOR COP REPORT GENERATION ------------ PALLAVI,29/10/09
    final int I_GET_CURRENT_COP=4;
    final int I_GET_PREV_COP=5;
    final int I_GET_TA=7;
    
    //ENUMERATIONS FOR LEGISLATIONS ------------ PALLAVI,31/10/09
    final int I_BSI=1;
    final int I_BSII=2;
    final int I_BSIII=3;
    final int I_BSIV=4;
    final int I_BSV=5;
    
    //ENUMERATIONS FOR TEST STANDARDS ------------ PALLAVI,07/11/09
    final int I_CMVR=1;
    final int I_EPA=2;
    final int I_EEC=3;
    final int I_ECE=4;
    final int I_WMTC=5;
    
    //ENUMERATIONS FOR CYCLE TYPE TAG ------------ PALLAVI,30/10/09
    final int I_CYCLE_TYPE=5203;
    
    // ENUMERATIONS FOR UUT SUB TYPE FILTER ------------ UMESH, 02/01/10
    final int I_UUTFILTER=9029;
    
    // ENUMERATIONS FOR UUT SUB TYPE FILTER ------------ UMESH, 02/01/10
    final int I_UUTFILTERID=9030;
    
    // ENUMERATIONS FOR Convert Qp------------ Rajesh, 13/02/10
    final int I_CONVERTQP=9999;
    
    // ENUMERATIONS FOR OC No ------------ Sameer, 23/02/10
    final int I_OCNo=9998;
    
    // ENUMERATIONS FOR KI factors ------------ Sameer, 03/03/10
    final int I_KIFactor=9997;
    
    // ENUMERATIONS FOR DASHBOARDBY KShrikesh on 25 th April 2012
    final int I_GET_TESTBED_NAMES = 3;
    final int I_FILTER_BY_DAYS = 4;
    final int I_FILTER_BY_DATE = 5;
    
    final int I_SOAKROOMDATA = 159;
    final int I_SOAKROOMDATASERVICE = 160;
    
    
  //ENUMERATION FOR COP WORKFLOW OPERATIONS  ------------ NIKETA MANE , 3/12/2014
    final int I_REMINDERMAILDASHBOARD		         =1;
	final int I_PROPPLANDASHBOARD		             =2;
	final int I_ASSIGNDASHBOARD		                 =3;
	final int I_OVERALLDASHBOARD		             =4;
	final int I_COPREMINDERMAIL                      =5;
	final int I_UPDATE_COPREMINDERMAIL               =6;
	final int I_COPEMAILTEMPLATE                     =7;
	final int I_COP_MANUALREMINDER                   =8;
	final int I_COP_SEND_EMAIL                       =9;
	final int I_COP_DETAIL                           =10;
	final int I_COP_DASHBOARD_DETAIL                 =11;
	final int I_COP_PRODUCTIONPLAN_DETAIL            =12;
	final int I_ASSIGN_ENGINEER_LIST                 =13;
	final int I_COP_SELECTIONSTATUS_LIST             =14;
	final int I_SAVE_COP_DETAIL                      =15;
	final int I_FINANCIAL_LIST                       =16;
	final int I_DOWNLOAD_SELECTIONSHEETS             =17;
	final int I_EXPORT_PRODUCTIONPLAN                =18;
	final int I_IMPORT_FILE                          =19;
	final int I_SMTP_SETTINGS                        =20;
	final int I_EMAIL_EVENTS_LIST                    =21;
	final int I_EMAIL_TEMPLATE                       =22;
	final int I_EMAIL_TEMPLATE_DETAILS               =23;
	final int I_EMAIL_EVENTS_DETAILS                 =24;
	
	final int I_AllDASHBOARD						 =26;
	
	//ENUMERATION FOR THE EXCEL SHEETS FIXED VALUES             --------NIKETA MANE , 22/1/2015
	final String TXT_MANUFACTURER="TXT_MANUFACTURER";
	final String TXT_FINANCIALYEAR="TXT_FINANCIALYEAR";
	final String TXT_INPRODUCTION="TXT_INPRODUCTION";
	final String TXT_PLANT="TXT_PLANT";
	final String TXT_FROMDATE="TXT_FROMDATE";
	final String TXT_TODATE="TXT_TODATE";
	final String TXT_MODEL="TXT_MODEL";
	final String TXT_ENGINEfAMILY="TXT_ENGINEfAMILY";
	final String TXT_ENGINEPARENT="TXT_ENGINEPARENT";
	final String TXT_EXHAUSTBACKPRESSURE="TXT_EXHAUSTBACKPRESSURE";
	final String TXT_LEGISLATION="TXT_LEGISLATION";
	final String TXT_AIRiNTAKE="TXT_AIRiNTAKE";
	final String TXT_RUNNINGIN="TXT_RUNNINGIN";
	final String TXT_MAXTORQUE="TXT_MAXTORQUE";
	final String TXT_EXHAUSTVOLUME="TXT_EXHAUSTVOLUME";
	final String TXT_PLANTADDRESS="TXT_PLANTADDRESS";
	final String TXT_VEHICLEMODELVARIANT="TXT_VEHICLEMODELVARIANT";
	final String TXT_CMVRCERTNO="TXT_CMVRCERTNO";
	final String TXT_ENGINEMODEL="TXT_ENGINEMODEL";
	final String TXT_VEHICLEMODEL="TXT_VEHICLEMODEL";
	final String TXT_RATEDPOWER="TXT_RATEDPOWER";
	final String TXT_SOP="TXT_SOP";
	final String TXT_PARAMETERMODEL="TXT_PARAMETERMODEL";
	final String TXT_ENGINEMANUFACTURER="TXT_ENGINEMANUFACTURER";
	final String TXT_ENGINEMANUFACTURER_PLANT="TXT_ENGINEMANUFACTURER_PLANT";
	final String TXT_ENGINEPLANT="TXT_ENGINEPLANT";
	final String TXT_SINCEWHEN="TXT_SINCEWHEN";
	final String TXT_EMISSIONTAREPORTNO="TXT_EMISSIONTAREPORTNO";
	
	final String TXT_ENGINECC="TXT_ENGINECC";
	final String COP_FIRST_HALF="COP_FIRST_HALF";
	final String COP_SECOND_HALF="COP_SECOND_HALF";
	final String COP_PREV_FIRST_HALF="COP_PREV_FIRST_HALF";
	final String COP_PREV_SECOND_HALF="COP_PREV_SECOND_HALF";
	final String COP_ACTUALPRODPREV="COP_ACTUALPRODPREV";
	
	final String TXT_TOTALFIRST="TXT_TOTALFIRST";
	final String TXT_TOTALSECOND="TXT_TOTALSECOND";
	final String TXT_EXPORT="TXT_EXPORT";
	
	
	
	
	
	//ENUMERATION FOR THE EXCEL SHEETS ALIAS NAMES FOR THE IMPORT             --------NIKETA MANE , 22/1/2015
	final String _TXT_MANUFACTURER="_TXT_MANUFACTURER";
	final String _TXT_FINANCIALYEAR="_TXT_FINANCIALYEAR";
	final String _TXT_INPRODUCTION="_TXT_INPRODUCTION";
	final String _TXT_PLANT="_TXT_PLANT";
	final String _TXT_FROMDATE="_TXT_FROMDATE";
	final String _TXT_TODATE="_TXT_TODATE";
	final String _TXT_MODEL="_TXT_MODEL";
	final String _TXT_ENGINEfAMILY="_TXT_ENGINEfAMILY";
	final String _TXT_ENGINEPARENT="_TXT_ENGINEPARENT";
	final String _TXT_EXHAUSTBACKPRESSURE="_TXT_EXHAUSTBACKPRESSURE";
	final String _TXT_LEGISLATION="_TXT_LEGISLATION";
	final String _TXT_AIRiNTAKE="_TXT_AIRiNTAKE";
	final String _TXT_RUNNINGIN="_TXT_RUNNINGIN";
	final String _TXT_MAXTORQUE="_TXT_MAXTORQUE";
	final String _TXT_EXHAUSTVOLUME="_TXT_EXHAUSTVOLUME";
	final String _TXT_PLANTADDRESS="_TXT_PLANTADDRESS";
	final String _TXT_VEHICLEMODELVARIANT="_TXT_VEHICLEMODELVARIANT";
	final String _TXT_CMVRCERTNO="_TXT_CMVRCERTNO";
	final String _TXT_ENGINEMODEL="_TXT_ENGINEMODEL";
	final String _TXT_VEHICLEMODEL="_TXT_VEHICLEMODEL";
	final String _TXT_RATEDPOWER="_TXT_RATEDPOWER";
	final String _TXT_SOP="_TXT_SOP";
	final String _TXT_PARAMETERMODEL="_TXT_PARAMETERMODEL";
	final String _TXT_ENGINEMANUFACTURER="_TXT_ENGINEMANUFACTURER";
	final String _TXT_ENGINEPLANT="_TXT_ENGINEPLANT";
	final String _TXT_SINCEWHEN="_TXT_SINCEWHEN";
	final String _TXT_EMISSIONTAREPORTNO="_TXT_EMISSIONTAREPORTNO";
	final String _TXT_ENGINEMANUFACTURER_PLANT="_TXT_ENGINEMANUFACTURER_PLANT";
	
	final String _COP_FIRST_HALF="_COP_FIRST_HALF";
	final String _COP_SECOND_HALF="_COP_SECOND_HALF";
	final String _COP_PREV_FIRST_HALF="_COP_PREV_FIRST_HALF";
	final String _COP_PREV_SECOND_HALF="COP_PREV_SECOND_HALF";
	
	final String _TXT_TOTALFIRST="_TXT_TOTALFIRST";
	final String _TXT_TOTALSECOND="_TXT_TOTALSECOND";
	
	
	//Constants for email Type other than the reminder
	final int I_EMAILREGISTERED                 = 1;
	final int I_EMAILSUBMITTED                  = 2;
	final int I_EMAILASSIGNEDENG                = 3;
		
	 /**
	  * Enumerations for Email Settings data
	  */
	 final String STR_HOST = "host";
	 final String STR_PORT = "port";
	 final String STR_URL = "url";
	 final String STR_SENDEREMAIL_ID = "fromAddress";
	 
	 
	 /* The following Enumerations are for email sending status.
		 * Added by - mniketa 
		 * Date  - 17/2/2015
		 */
	final int I_UNSENDMAIL 		                = 0;
	final int I_SUCCESSMAIL 	                = 1;
	final int I_FAILUREMAIL		                = 2;
	final int I_DISCARDMAIL 					= 3;
	
	
	//Enumerations for events according to the email types
	final int I_REMINDERENGINE_EVENT 		                =1;
	final int I_REMINDERVEHICLE_EVENT 	                    =2;
	final int I_PRODUCTIONPLAN_SUBMITTED_EVENT		        =3;
	final int I_COPUSER_REGISTRATION_EVENT 					=4;
	final int I_COPSELECTION_ASSIGNENGINNER_EVENT 		    =5;
	
	
	
	//Enumerations for the QP importer and exporter
	final int I_QP_IMPORTER 		                =111;
	final int I_QP_EXPORTER 	                    =222;
	
}// final interface EnumerationIF
