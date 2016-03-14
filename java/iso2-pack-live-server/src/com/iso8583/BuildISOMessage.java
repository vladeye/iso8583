/*
 code for iSO8583 messaging 

 @author
 bhushan shirsath
 Cuelogic Technologies pune India


 This class is for packing and unpacking of the ISO8583 messages


-Method used 

 createIsoMsg(String MTI,Hashmap message) -- for packing the iso
 parseISOMsg(String message)  -- For unpacking the ISO format 

*/
package com.iso8583;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;
import javax.management.ObjectInstance;



public class BuildISOMessage{

	//method for packing the iso message
	static String createIsoMsg(String MTI,HashMap message)
	{
		try
		{
			//ISOPackager packager = new ISO93Packeger();
			
			InputStream is = BuildISOMessage.class.getResourceAsStream("basic.xml");
			GenericPackager packager = new GenericPackager(is);
		//	GenericPackager packager = new GenericPackager("basic.xml");
			// Create ISO Message
			ISOMsg isoMsg = new ISOMsg();
			isoMsg.setPackager(packager);
			isoMsg.setMTI(MTI);
	
			String errorMessage = "";
			boolean isError = false; 
			Set entries = message.entrySet();
		    
			for(Iterator it = entries.iterator(); it.hasNext();)
			{
			    Map.Entry entry = (Entry) it.next();
			    String dataElement =(String) entry.getKey();
			    String value =(String) entry.getValue();
			  //  System.out.println("RESULT : "+ "field:"+dataElement +"value :"+value);
			    if(Integer.valueOf(dataElement)!=0 && !(Integer.valueOf(dataElement)>128))
			    {
			    	
			    	isoMsg.set(dataElement, value);
			    	
			    }
			    else
			    {
			    	isError = true;
					errorMessage = "Data Element should not be Zero or less than 128";
			    }
			}
				
			if(isError==false)
			{
		    	isoMsg.pack();// print the DE list 
			    System.out.println("RESULT : " + new String(isoMsg.pack()));	
				
				return new String(isoMsg.pack());
			}
  	}
		catch(ISOException e)
		{
			System.out.println("in catch block"+e.toString());	
			return null;
		}
			return null;
	}
	
	//method for parsing the iso msg
	public static HashMap parseISOMsg(String message)
	{
		try
		{
			String errormsg;
			boolean errorValue=false;
			HashMap msgMap=new HashMap<String,String>();
			if(message!=null)
			{		
				ISOPackager packager = new ISO93Packeger();
				ISOMsg isoMsg = new ISOMsg();
				isoMsg.setPackager(packager);
				isoMsg.unpack(message.getBytes());
				System.out.println("  MTI : " + isoMsg.getMTI());
	 
			  for (int i=1;i<=isoMsg.getMaxField();i++)
			  {
			    if (isoMsg.hasField(i))
				{
					if(i>128)
					{
						errorValue=true;
					}
			    	else
					{
				    	System.out.println("    Field-"+i+" : "+isoMsg.getString(i));
						msgMap.put(i,isoMsg.getString(i));
					}
				}
			  }
			}else
			{    errorValue=true;
				 errormsg="the message String can not be Empty";
			}
			
			if(errorValue==false)
			{
				return msgMap;
			}
			
			}catch(ISOException e)
			{ 
			//	Log.d("iso", "exception "+e.toString());
				return null;
			}
			
		return null;
		
	}
}
