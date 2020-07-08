/*******************************************************************************
 * @(#) $Id: EnumWebServiceIF.java    2016   PVMSys
 * ***************************************************************************
 *  COPYRIGHT ©, 2009-2013                                                  
 *  PVMSys Infra Solutions Pvt. Ltd.                                                                                                                      
 * ****************************************************************************
 *  PVMSys Infra Solutions Pvt. Ltd.                                        
 *  1st Floor, Plot No. 7, Silver Oak Park Society, Baner Road, Pune-411045 
 *  Phone: +91-20-27293979, Internet: www.pvmsys.com                                                                                     
 * **************************************************************************                                                                   
 *  All Rights Reserved.                                                    
 * This software is the confidential and proprietary information of the    
 * authors. It may be freely copied and distributed with the following     
 * stipulations:                                                            
 * 	  o No fee except to recover costs of media and delivery may         
 *             be charged for the use or possession of this software.            
 *          o Sources to this utility must be made available in machine-       
 *             readable form along with the executable form.                    
 *          o No portion of this program may be used in any program sold       
 *              for a fee or used for production purposes.                       
 *          o This copyright notice must not be removed.                       
 *                                                                  
 *  All brand names and product names used in this software are trademarks, 
 *  registered trademarks, or trade names of their respective holders.      
 *  The authors of this software are not associated with any product or     
 *  vendor mentioned in the code or documentation.
 ******************************************************************************/
package com.brixip.core.web.util;

public interface EnumWebServiceIF 
{
	
	final int I_SUCCESS = 1;
	final int I_FAILURE = 2;
	final int I_ERROR = 3;
	final int I_EXCEPTION = 4;
	final int I_DUPLICATE = 6;
	final int I_INUSED = 7;
	final int I_INVALID = 8;
	final int I_INVALID_UNAUTHORIZED_KEY= 9;
	final int I_REQ_UNAUTHORIZED= 401;
	final int I_REQ_SUCCESS= 200;
	final int I_SESSION_KEY_EXPIRE= 9;
	final int I_NOT_FOUND = 10;
	final int I_DISABLED = 5;
	final int I_BADREQUEST= 400;
} // public interface EnumWebServiceIF 
