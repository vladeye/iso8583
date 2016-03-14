package com.iso8583;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.codec.binary.Base64;

public class main {
   
	public static HashMap<String, String> ISOSCHEMA = new HashMap<String, String>();
	public static HashMap<String, String> ISOMESSAGE = new HashMap<String, String>();
	public static HashMap<String, String> PARSEDISOMESSAGE = new HashMap<String, String>();
	public static HashMap<Integer, ArrayList<Integer>> SUBFIELDSMAPPING = new HashMap<Integer, ArrayList<Integer>>();
	public static boolean ISSUBFIELDPARSING = false;
	public static String SUBFIELDID = "";
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//TODO Auto-generated method stub
		
		//isoSchema.put("<field-id","<field-data-type>-<field-size>-<field-max-length>-<has-subfields_<has-bitmap>>-<Field Name>");
		
		ISOSCHEMA.put("1","BITMAP");
		ISOSCHEMA.put("2","NUM-2-19-0_0");
		ISOSCHEMA.put("3","FCHAR-1-6-0_0");
		ISOSCHEMA.put("4","NUMERIC-2-16-0_0");
		ISOSCHEMA.put("6","NUMERIC-2-16-0_0");
		ISOSCHEMA.put("7","NUMERIC-2-10-0_0");
		ISOSCHEMA.put("8","NUMERIC-2-12-0_0");
		ISOSCHEMA.put("11","NUMERIC-2-12-0_0");
		ISOSCHEMA.put("12","NUMERIC-2-14-0_0");
		ISOSCHEMA.put("13","NUMERIC-1-6-0_0");
		ISOSCHEMA.put("14","NUMERIC-1-4-0_0");
		ISOSCHEMA.put("17","NUMERIC-1-4-0_0");
		ISOSCHEMA.put("18","CHAR-3-140-0_0");
		ISOSCHEMA.put("21","FCHAR-2-22-0_0");
		ISOSCHEMA.put("22","CHAR-2-16-0_0");
		ISOSCHEMA.put("23","NUMERIC-1-3-0_0");
		ISOSCHEMA.put("24","NUMERIC-1-3-0_0");
		ISOSCHEMA.put("25","NUMERIC-1-4-0_0");
		ISOSCHEMA.put("26","NUMERIC-1-4-0_0");
		ISOSCHEMA.put("30","NUMERIC-2-32-0_0");
		ISOSCHEMA.put("32","NUM-2-11-0_0");
		ISOSCHEMA.put("33","NUM-2-11-0_0");
		ISOSCHEMA.put("35","CHAR-2-37-0_0");
		ISOSCHEMA.put("37","FCHAR-2-12-0_0");
		ISOSCHEMA.put("38","FCHAR-1-6-0_0");
		ISOSCHEMA.put("39","FCHAR-1-4-0_0");
		ISOSCHEMA.put("41","CHAR-2-99-0_0");
		ISOSCHEMA.put("42","CHAR-2-99-0_0");
		ISOSCHEMA.put("43","CHAR-4-9999-1_1");
			ISOSCHEMA.put("43.1","BITMAP");
			ISOSCHEMA.put("43.2","CHAR-2-50-0_0");
			ISOSCHEMA.put("43.3","CHAR-2-99-0_0");
			ISOSCHEMA.put("43.4","CHAR-2-50-0_0");
			ISOSCHEMA.put("43.5","FCHAR-1-3-0_0");
			ISOSCHEMA.put("43.6","FCHAR-2-10-0_0");
			ISOSCHEMA.put("43.7","FCHAR-1-3-0_0");
			ISOSCHEMA.put("43.8","FCHAR-2-16-0_0");
			ISOSCHEMA.put("43.9","FCHAR-2-16-0_0");
			ISOSCHEMA.put("43.10","CHAR-2-30-0_0");
			ISOSCHEMA.put("43.11","CHAR-3-255-0_0");
			ISOSCHEMA.put("43.12","CHAR-2-99-0_0");
		ISOSCHEMA.put("44","CHAR-2-25-0_0");
		ISOSCHEMA.put("45","CHAR-2-76-0_0");
		ISOSCHEMA.put("46","CHAR-3-216-1_0");
			ISOSCHEMA.put("46.1","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("46.2","NUMERIC-2-13-0_0");
			ISOSCHEMA.put("46.3","NUMERIC-1-8-0_0");
			ISOSCHEMA.put("46.4","NUMERIC-2-13-0_0");
		ISOSCHEMA.put("47","CHAR-3-999-1_1");
			ISOSCHEMA.put("47.1","BITMAP");
			ISOSCHEMA.put("47.2","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("47.3","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("47.4","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("47.5","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("47.6","CHAR-2-10-0_0");
			ISOSCHEMA.put("47.7","CHAR-2-10-0_0");
			ISOSCHEMA.put("47.8","CHAR-2-10-0_0");
			ISOSCHEMA.put("47.9","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("47.10","CHAR-2-10-0_0");
			ISOSCHEMA.put("47.11","CHAR-2-10-0_0");
			ISOSCHEMA.put("47.12","CHAR-2-99-0_0");
			ISOSCHEMA.put("47.13","CHAR-2-20-0_0");
			ISOSCHEMA.put("47.14","CHAR-2-99-0_0");
			ISOSCHEMA.put("47.15","CHAR-2-15-0_0");
			ISOSCHEMA.put("47.16","CHAR-2-15-0_0");
			ISOSCHEMA.put("47.17","CHAR-2-10-0_0");
			ISOSCHEMA.put("47.18","CHAR-2-30-0_0");
			ISOSCHEMA.put("47.19","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("47.20","CHAR-2-99-0_0");
			ISOSCHEMA.put("47.21","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("47.22","CHAR-2-10-0_0");
			ISOSCHEMA.put("47.23","CHAR-2-10-0_0");
			ISOSCHEMA.put("47.24","CHAR-3-200-0_0");
			ISOSCHEMA.put("47.25","CHAR-3-100-0_0");
			ISOSCHEMA.put("47.26","CHAR-2-30-0_0");
			ISOSCHEMA.put("47.27","CHAR-3-150-0_0");
			ISOSCHEMA.put("47.28","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("47.29","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("47.30","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("47.31","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("47.32","CHAR-3-255-0_0");
			ISOSCHEMA.put("47.33","CHAR-3-255-0_0");
			ISOSCHEMA.put("47.34","CHAR-3-255-0_0");
			ISOSCHEMA.put("47.35","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("47.36","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("47.37","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("47.38","CHAR-3-255-0_0");
			ISOSCHEMA.put("47.39","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("47.40","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("47.41","CHAR-3-100-0_0");
			ISOSCHEMA.put("47.42","CHAR-3-255-0_0");
			ISOSCHEMA.put("47.43","CHAR-1-1-0_0");
			ISOSCHEMA.put("47.44","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("47.45","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("47.46","CHAR-2-99-0_0");
		ISOSCHEMA.put("48","CHAR-4-9999-1_1");
			ISOSCHEMA.put("48.1","BITMAP");
			ISOSCHEMA.put("48.2","CHAR-2-99-0_0");
			ISOSCHEMA.put("48.3","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("48.4","NUMERIC-1-5-0_0");
			ISOSCHEMA.put("48.5","FCHAR-1-1-0_0");
			ISOSCHEMA.put("48.6","CHAR-2-20-0_0");
			ISOSCHEMA.put("48.7","CHAR-2-20-0_0");
			ISOSCHEMA.put("48.8","CHAR-2-99-0_0");
			ISOSCHEMA.put("48.9","CHAR-2-99-0_0");
			ISOSCHEMA.put("48.10","CHAR-2-10-0_0");
			ISOSCHEMA.put("48.11","CHAR-2-20-0_0");
			ISOSCHEMA.put("48.12","CHAR-2-20-0_0");
			ISOSCHEMA.put("48.13","CHAR-2-20-0_0");
			ISOSCHEMA.put("48.14","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("48.15","CHAR-2-40-0_0");
			ISOSCHEMA.put("48.16","CHAR-2-30-0_0");
			ISOSCHEMA.put("48.25","CHAR-3-255-0_0");
			ISOSCHEMA.put("48.26","NUMERIC-2-10-0_0");
		ISOSCHEMA.put("52","PCHAR-2-16-0_0");
		ISOSCHEMA.put("53","NUMERIC-2-18-0_0");
		ISOSCHEMA.put("54","CHAR-3-126-1_0");
			ISOSCHEMA.put("54.1","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("54.2","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("54.3","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("54.4","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("54.5","FCHAR-1-1-0_0");
			ISOSCHEMA.put("54.6","NUMERIC-2-12-0_0");
		ISOSCHEMA.put("55","CHAR-3-999-1_1");
			ISOSCHEMA.put("54.1","BITMAP");
			ISOSCHEMA.put("54.2","CHAR-2-10-0_0");
			ISOSCHEMA.put("54.3","CHAR-2-10-0_0");
			ISOSCHEMA.put("54.4","NUMERIC-1-10-0_0");
			ISOSCHEMA.put("54.5","NUMERIC-1-10-0_0");
		ISOSCHEMA.put("56","CHAR-2-41-0_0");
		ISOSCHEMA.put("57","CHAR-2-50-0_0");
		ISOSCHEMA.put("58","CHAR-2-99-0_0");
		ISOSCHEMA.put("59","CHAR-3-999-0_0");
		ISOSCHEMA.put("62","CHAR-3-999-1_1");
			ISOSCHEMA.put("62.1","BITMAP");
			ISOSCHEMA.put("62.2","CHAR-2-30-0_0");
			ISOSCHEMA.put("62.3","CHAR-2-10-0_0");
			ISOSCHEMA.put("62.4","CHAR-2-10-0_0");
			ISOSCHEMA.put("62.5","NUMERIC-2-10-0_0");
		ISOSCHEMA.put("63","CHAR-3-999-0_0");
		ISOSCHEMA.put("64","BINARY-0-4-0_0");
		ISOSCHEMA.put("68","FCHAR-1-9-0_0");
		ISOSCHEMA.put("72","CHAR-4-9999-0_0");
		ISOSCHEMA.put("74","NUM-2-78-1_0");
			ISOSCHEMA.put("74.1","NUMERIC-2-16-0_0");
			ISOSCHEMA.put("74.2","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("74.3","NUMERIC-2-16-0_0");
			ISOSCHEMA.put("74.4","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("74.5","NUMERIC-2-16-0_0");
			ISOSCHEMA.put("74.6","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("74.7","NUMERIC-2-16-0_0");
			ISOSCHEMA.put("74.8","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("74.9","NUMERIC-2-16-0_0");
			ISOSCHEMA.put("74.10","NUMERIC-2-10-0_0");
		ISOSCHEMA.put("97","NUMERIC-2-21-0_0");
		ISOSCHEMA.put("101","CHAR-2-99-0_0");
		ISOSCHEMA.put("102","CHAR-2-28-0_0");
		ISOSCHEMA.put("103","CHAR-2-28-0_0");
		ISOSCHEMA.put("104","NUMERIC-1-6-0_0");
		ISOSCHEMA.put("105","NUMERIC-1-6-0_0");
		ISOSCHEMA.put("106","CHAR-3-999-1_1");
			ISOSCHEMA.put("106.1","BITMAP");
			ISOSCHEMA.put("106.2","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("106.3","CHAR-2-10-0_0");
			ISOSCHEMA.put("106.4","CHAR-2-30-0_0");
			ISOSCHEMA.put("106.5","CHAR-2-99-0_0");
			ISOSCHEMA.put("106.6","CHAR-2-99-0_0");
			ISOSCHEMA.put("106.7","CHAR-2-99-0_0");
			ISOSCHEMA.put("106.8","CHAR-2-99-0_0");
		ISOSCHEMA.put("107","CHAR-3-999-0_0");
		ISOSCHEMA.put("111","CHAR-4-9999-0_0");
		ISOSCHEMA.put("112","CHAR-4-9999-0_0");
		ISOSCHEMA.put("113","NUMERIC-2-11-0_0");
		ISOSCHEMA.put("114","CHAR-4-9999-1_1");
			ISOSCHEMA.put("114.1","BITMAP");
			ISOSCHEMA.put("114.2","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.3","CHAR-2-40-0_0");
			ISOSCHEMA.put("114.4","CHAR-2-40-0_0");
			ISOSCHEMA.put("114.5","CHAR-2-40-0_0");
			ISOSCHEMA.put("114.6","CHAR-2-40-0_0");
			ISOSCHEMA.put("114.7","CHAR-3-300-0_0");
			ISOSCHEMA.put("114.8","NUMERIC-1-8-0_0");
			ISOSCHEMA.put("114.9","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.10","FCHAR-2-10-0_0");
			ISOSCHEMA.put("114.11","FCHAR-1-1-0_0");
			ISOSCHEMA.put("114.12","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.13","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.14","CHAR-2-40-0_0");
			ISOSCHEMA.put("114.15","FCHAR-1-2-0_0");
			ISOSCHEMA.put("114.16","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.17","FCHAR-1-5-0_0");
			ISOSCHEMA.put("114.18","CHAR-2-15-0_0");
			ISOSCHEMA.put("114.19","CHAR-2-15-0_0");
			ISOSCHEMA.put("114.20","CHAR-2-15-0_0");
			ISOSCHEMA.put("114.21","CHAR-2-15-0_0");
			ISOSCHEMA.put("114.22","CHAR-3-120-0_0");
			ISOSCHEMA.put("114.23","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.24","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.25","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.26","CHAR-2-50-0_0");
			ISOSCHEMA.put("114.27","CHAR-2-50-0_0");
			ISOSCHEMA.put("114.28","FCHAR-2-10-0_0");
			ISOSCHEMA.put("114.29","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.30","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.31","CHAR-2-40-0_0");
			ISOSCHEMA.put("114.32","FCHAR-1-2-0_0");
			ISOSCHEMA.put("114.33","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.34","FCHAR-1-5-0_0");
			ISOSCHEMA.put("114.35","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.36","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.37","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.38","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.39","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.40","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.41","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.42","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.43","CHAR-2-50-0_0");
			ISOSCHEMA.put("114.44","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.45","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.46","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.47","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.48","CHAR-2-30-0_0");
			ISOSCHEMA.put("114.49","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.50","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.51","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.52","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.53","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.54","NUMERIC-1-10-0_0");
			ISOSCHEMA.put("114.55","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.56","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.57","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.58","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.59","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.60","NUMERIC-1-11-0_0");
			ISOSCHEMA.put("114.61","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.62","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.63","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.64","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.65","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.67","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.68","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.69","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.70","CHAR-3-300-0_0");
			ISOSCHEMA.put("114.71","CHAR-2-30-0_0");
			ISOSCHEMA.put("114.72","NUM-2-11-0_0");
			ISOSCHEMA.put("114.73","NUM-2-11-0_0");
			ISOSCHEMA.put("114.74","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.75","CHAR-3-300-0_0");
			ISOSCHEMA.put("114.76","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.77","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("114.78","CHAR-3-200-0_0");
			ISOSCHEMA.put("114.79","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.80","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.81","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.82","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.83","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.84","CHAR-2-40-0_0");
			ISOSCHEMA.put("114.85","FCHAR-1-2-0_0");
			ISOSCHEMA.put("114.86","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.87","FCHAR-1-5-0_0");
			ISOSCHEMA.put("114.88","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.89","NUMERIC-1-4-0_0");
			ISOSCHEMA.put("114.90","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.91","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.92","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.93","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.94","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.95","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.96","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.97","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.98","NUMERIC-3-11-0_0");
			ISOSCHEMA.put("114.99","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.100","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.101","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.102","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.103","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.104","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.105","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.106","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.107","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.108","CHAR-2-40-0_0");
			ISOSCHEMA.put("114.109","FCHAR-1-2-0_0");
			ISOSCHEMA.put("114.110","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.111","FCHAR-1-5-0_0");
			ISOSCHEMA.put("114.112","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.113","CHAR-2-11-0_0");
			ISOSCHEMA.put("114.114","CHAR-2-11-0_0");
			ISOSCHEMA.put("114.115","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.116","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.117","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.118","NUMERIC-2-15-0_0");
			ISOSCHEMA.put("114.119","NUMERIC-1-4-0_0");
			ISOSCHEMA.put("114.120","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.121","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.122","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("114.123","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("114.124","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.125","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.126","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.127","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.128","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.129","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.130","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.131","CHAR-2-11-0_0");
			ISOSCHEMA.put("114.132","CHAR-2-11-0_0");
			ISOSCHEMA.put("114.133","CHAR-2-10-0_0");
			ISOSCHEMA.put("114.134","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.135","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("114.136","CHAR-2-25-0_0");
			ISOSCHEMA.put("114.137","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.138","NUMERIC-1-8-0_0");
			ISOSCHEMA.put("114.139","NUMERIC-1-8-0_0");
			ISOSCHEMA.put("114.140","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.141","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.142","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.143","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.144","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.145","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.146","NUMERIC-2-14-0_0");
			ISOSCHEMA.put("114.147","CHAR-2-10-0_0");
			ISOSCHEMA.put("114.148","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.149","CHAR-2-99-0_0");
			ISOSCHEMA.put("114.150","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.151","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.152","NUMERIC-2-14-0_0");
			ISOSCHEMA.put("114.153","NUMERIC-2-14-0_0");
			ISOSCHEMA.put("114.154","NUMERIC-1-10-0_0");
			ISOSCHEMA.put("114.155","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.156","CHAR-3-255-0_0");
			ISOSCHEMA.put("114.157","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("114.158","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.159","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("114.160","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.161","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("114.162","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("114.163","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("114.164","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("114.165","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.166","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.167","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.168","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.169","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.170","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.171","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.172","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.173","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.174","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.175","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.176","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.177","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.178","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("114.179","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("114.180","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("114.181","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.182","CHAR-3-100-0_0");
			ISOSCHEMA.put("114.183","CHAR-2-40-0_0");
			ISOSCHEMA.put("114.184","FCHAR-1-2-0_0");
			ISOSCHEMA.put("114.185","CHAR-2-20-0_0");
			ISOSCHEMA.put("114.186","FCHAR-1-5-0_0");
			ISOSCHEMA.put("114.189","NUMERIC-1-1-0_0");
		ISOSCHEMA.put("120","CHAR-4-9999-1_1");
			ISOSCHEMA.put("120.1","BITMAP");
			ISOSCHEMA.put("120.2","NUMERIC-1-10-0_0");
			ISOSCHEMA.put("120.3","NUMERIC-1-10-0_0");
			ISOSCHEMA.put("120.4","CHAR-2-32-0_0");
			ISOSCHEMA.put("120.5","CHAR-2-15-0_0");
			ISOSCHEMA.put("120.6","CHAR-2-50-0_0");
			ISOSCHEMA.put("120.7","CHAR-2-15-0_0");
			ISOSCHEMA.put("120.8","CHAR-2-40-0_0");
			ISOSCHEMA.put("120.9","CHAR-2-10-0_0");
			ISOSCHEMA.put("120.10","FCHAR-1-5-0_0");
			ISOSCHEMA.put("120.11","CHAR-3-100-0_0");
			ISOSCHEMA.put("120.12","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.13","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.14","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.15","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.16","NUMERIC-1-4-0_0");
			ISOSCHEMA.put("120.17","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.18","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.19","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.20","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.21","CHAR-2-50-0_0");
			ISOSCHEMA.put("120.22","CHAR-2-10-0_0");
			ISOSCHEMA.put("120.24","CHAR-2-10-0_0");
			ISOSCHEMA.put("120.25","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.26","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.27","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.28","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.29","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.30","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.31","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.32","CHAR-3-150-0_0");
			ISOSCHEMA.put("120.33","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.34","CHAR-2-15-0_0");
			ISOSCHEMA.put("120.35","CHAR-3-100-0_0");
			ISOSCHEMA.put("120.36","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.37","CHAR-2-30-0_0");
			ISOSCHEMA.put("120.38","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.39","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.40","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.41","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("120.42","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.43","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.44","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.45","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.46","NUMERIC-1-8-0_0");
			ISOSCHEMA.put("120.47","NUMERIC-1-8-0_0");
			ISOSCHEMA.put("120.48","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.49","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.50","NUMERIC-2-14-0_0");
			ISOSCHEMA.put("120.51","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.52","CHAR-2-30-0_0");
			ISOSCHEMA.put("120.53","CHAR-3-300-0_0");
			ISOSCHEMA.put("120.54","CHAR-2-10-0_0");
			ISOSCHEMA.put("120.55","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.56","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.57","CHAR-2-99-0_0");
			ISOSCHEMA.put("120.58","CHAR-2-99-0_0");
			ISOSCHEMA.put("120.65","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.66","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.67","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.68","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.69","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.70","CHAR-3-999-0_0");
			ISOSCHEMA.put("120.71","CHAR-3-100-0_0");
			ISOSCHEMA.put("120.72","CHAR-3-100-0_0");
			ISOSCHEMA.put("120.73","CHAR-2-30-0_0");
			ISOSCHEMA.put("120.74","NUMERIC-1-5-0_0");
			ISOSCHEMA.put("120.75","NUMERIC-1-5-0_0");
			ISOSCHEMA.put("120.76","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.77","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.78","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.79","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.80","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.81","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.82","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.83","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.84","NUMERIC-2-1-0_0");
			ISOSCHEMA.put("120.85","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.86","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.87","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.88","CHAR-2-11-0_0");
			ISOSCHEMA.put("120.89","CHAR-2-11-0_0");
			ISOSCHEMA.put("120.90","CHAR-2-50-0_0");
			ISOSCHEMA.put("120.91","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.92","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.93","CHAR-2-99-0_0");
			ISOSCHEMA.put("120.94","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.95","CHAR-2-99-0_0");
			ISOSCHEMA.put("120.96","CHAR-2-10-0_0");
			ISOSCHEMA.put("120.97","CHAR-2-30-0_0");
			ISOSCHEMA.put("120.98","CHAR-2-30-0_0");
			ISOSCHEMA.put("120.99","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.100","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("120.101","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("120.102","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.103","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.104","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.105","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.106","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.107","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.108","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.109","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.110","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.111","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("120.112","NUMERIC-2-14-0_0");
			ISOSCHEMA.put("120.113","NUMERIC-2-14-0_0");
			ISOSCHEMA.put("120.114","FCHAR-2-10-0_0");
			ISOSCHEMA.put("120.115","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("120.116","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("120.117","FCHAR-2-15-0_0");
			ISOSCHEMA.put("120.118","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.119","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.120","FCHAR-2-20-0_0");
			ISOSCHEMA.put("120.121","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("120.122","CHAR-2-40-0_0");
			ISOSCHEMA.put("120.123","CHAR-2-40-0_0");
			ISOSCHEMA.put("120.124","CHAR-2-40-0_0");
			ISOSCHEMA.put("120.125","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.126","CHAR-3-300-0_0");
			ISOSCHEMA.put("120.127","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.128","NUMERIC-1-5-0_0");
			ISOSCHEMA.put("120.129","CHAR-1-5-0_0");
			ISOSCHEMA.put("120.130","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.131","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.132","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("120.133","CHAR-2-64-0_0");
			ISOSCHEMA.put("120.134","CHAR-2-16-0_0");
			ISOSCHEMA.put("120.135","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.136","CHAR-3-120-0_0");
			ISOSCHEMA.put("120.137","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.138","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.139","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.140","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.141","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("120.142","CHAR-3-200-0_0");
			ISOSCHEMA.put("120.143","NUMERIC-2-14-0_0");
			ISOSCHEMA.put("120.144","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.145","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.146","NUMERIC-2-30-0_0");
			ISOSCHEMA.put("120.147","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.148","CHAR-3-300-0_0");
			ISOSCHEMA.put("120.149","CHAR-2-11-0_0");
			ISOSCHEMA.put("120.150","CHAR-2-11-0_0");
			ISOSCHEMA.put("120.151","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.152","NUMERIC-2-11-0_0");
			ISOSCHEMA.put("120.153","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.154","CHAR-2-50-0_0");
			ISOSCHEMA.put("120.155","NUMERIC-2-10-0_0");
			ISOSCHEMA.put("120.156","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("120.157","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.158","CHAR-2-50-0_0");
			ISOSCHEMA.put("120.159","CHAR-3-255-0_0");
			ISOSCHEMA.put("120.160","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("120.161","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.162","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("120.163","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.164","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("120.165","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("120.166","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("120.167","NUMERIC-1-3-0_0");
			ISOSCHEMA.put("120.168","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.169","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.170","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.171","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.172","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.173","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.174","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.175","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.176","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.177","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.178","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.179","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.180","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.181","NUMERIC-1-1-0_0");
			ISOSCHEMA.put("120.182","CHAR-3-100-0_0");
			ISOSCHEMA.put("120.183","CHAR-3-100-0_0");
			ISOSCHEMA.put("120.184","CHAR-2-40-0_0");
			ISOSCHEMA.put("120.185","FCHAR-1-2-0_0");
			ISOSCHEMA.put("120.186","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.186","CHAR-2-20-0_0");
			ISOSCHEMA.put("120.187","FCHAR-1-5-0_0");
		ISOSCHEMA.put("121","CHAR-4-9999-1_1");
			ISOSCHEMA.put("121.1","BITMAP");
			ISOSCHEMA.put("121.2","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("121.3","NUMERIC-1-2-0_0");
			ISOSCHEMA.put("121.4","CHAR-3-100-0_0");
			ISOSCHEMA.put("121.5","CHAR-3-100-0_0");
			ISOSCHEMA.put("121.6","CHAR-2-40-0_0");
			ISOSCHEMA.put("121.7","FCHAR-2-2-0_0");
			ISOSCHEMA.put("121.8","CHAR-2-20-0_0");
			ISOSCHEMA.put("121.9","FCHAR-1-5-0_0");
			ISOSCHEMA.put("121.10","CHAR-3-100-0_0");
			ISOSCHEMA.put("121.11","CHAR-3-100-0_0");
			ISOSCHEMA.put("121.12","CHAR-2-40-0_0");
			ISOSCHEMA.put("121.13","FCHAR-1-2-0_0");
			ISOSCHEMA.put("121.14","CHAR-2-20-0_0");
			ISOSCHEMA.put("121.15","FCHAR-1-5-0_0");
			ISOSCHEMA.put("121.16","CHAR-3-100-0_0");
			ISOSCHEMA.put("121.17","CHAR-3-100-0_0");
			ISOSCHEMA.put("121.18","CHAR-3-40-0_0");
			ISOSCHEMA.put("121.19","FCHAR-1-2-0_0");
			ISOSCHEMA.put("121.20","CHAR-2-20-0_0");
			ISOSCHEMA.put("121.21","FCHAR-1-5-0_0");
			ISOSCHEMA.put("121.22","NUMERIC-1-11-0_0");
			ISOSCHEMA.put("121.23","NUMERIC-1-11-0_0");
			ISOSCHEMA.put("121.24","NUMERIC-1-11-0_0");
			ISOSCHEMA.put("121.25","CHAR-3-255-0_0");
			ISOSCHEMA.put("121.26","CHAR-3-255-0_0");
			ISOSCHEMA.put("121.27","CHAR-3-255-0_0");
			ISOSCHEMA.put("121.28","CHAR-3-255-0_0");
			ISOSCHEMA.put("121.29","CHAR-3-255-0_0");
			ISOSCHEMA.put("121.30","NUMERIC-1-11-0_0");
			ISOSCHEMA.put("121.31","CHAR-3-150-0_0");
			ISOSCHEMA.put("121.32","CHAR-3-255-0_0");

			ISOSCHEMA.put("128","CHAR-2-16-0_0");

		String packUnpack = "pack";
		//String isoInput = args[0];
		
		//Login
		//String isoInput = "eyJNVEkiOiIwMzA1IiwiZGF0YSI6W3siaWQiOiI1OCIsInZhbHVlIjoiV2ViIHJlcXVlc3QifSx7ImlkIjoiNyIsInZhbHVlIjoiMDYwNDExMTQ1MyJ9LHsiaWQiOiI1NyIsInZhbHVlIjoiV2ViIHJlcXVlc3QifSx7ImlkIjoiNTIiLCJ2YWx1ZSI6IjZkNjk2YjY1In0seyJpZCI6IjEwNCIsInZhbHVlIjoiMDAxMDAyIn0seyJpZCI6IjI0IiwidmFsdWUiOiIzMDEifSx7ImlkIjoiMTE0LjciLCJ2YWx1ZSI6Im1pa2VAZ21haWwuY29tIn0seyJpZCI6IjEzIiwidmFsdWUiOiIwMDA2MDQifSx7ImlkIjoiMTAxIiwidmFsdWUiOiJWQUxJREFUSU9OIn0seyJpZCI6IjEyIiwidmFsdWUiOiIwMDAwMDAwMDExMTQ1MyJ9XX0=";
		
		//Add Card
		//String isoInput = "eyJNVEkiOiIwMzA1IiwiZGF0YSI6W3siaWQiOiI1OCIsInZhbHVlIjoiMGQxZmJlNTYtYmQwNy00Mzg1LWFmNzYtYTlkYzYxNDFiY2NjIn0seyJpZCI6IjU3IiwidmFsdWUiOiIzNTc2NTUwNDk4MzI5MjYifSx7ImlkIjoiMTMiLCJ2YWx1ZSI6IjAwMDYwMSJ9LHsiaWQiOiIxNCIsInZhbHVlIjoiMTQwNiJ9LHsiaWQiOiIxMiIsInZhbHVlIjoiMDAwMDAwMDAwMTA4MjkifSx7ImlkIjoiNDguOSIsInZhbHVlIjoibWogcm9hZCJ9LHsiaWQiOiI0OC44IiwidmFsdWUiOiIxMixuZWFyIHN1bnBhcmsifSx7ImlkIjoiNDguNyIsInZhbHVlIjoiIn0seyJpZCI6IjQ4LjYiLCJ2YWx1ZSI6Im15LW1hc3RlciJ9LHsiaWQiOiI0OC40IiwidmFsdWUiOiIwMjU2OCJ9LHsiaWQiOiI0OC4zIiwidmFsdWUiOiIyIn0seyJpZCI6IjQ4LjIiLCJ2YWx1ZSI6IlBldGVyIn0seyJpZCI6IjExNC4yNCIsInZhbHVlIjoiMDAuMDAwMCJ9LHsiaWQiOiIxMDQiLCJ2YWx1ZSI6IjAyMTAwMiJ9LHsiaWQiOiIxMDEiLCJ2YWx1ZSI6IkFERENBUkQifSx7ImlkIjoiMTE0LjIzIiwidmFsdWUiOiIwMC4wMDAwIn0seyJpZCI6IjQ4LjEzIiwidmFsdWUiOiJVUyJ9LHsiaWQiOiI0OC4xMCIsInZhbHVlIjoiNTc4OCJ9LHsiaWQiOiI0OC4xMSIsInZhbHVlIjoiVGV4YXMifSx7ImlkIjoiNDguMTIiLCJ2YWx1ZSI6IkFaIn0seyJpZCI6IjI0IiwidmFsdWUiOiIzMDEifSx7ImlkIjoiMjUiLCJ2YWx1ZSI6IjAwMjgifSx7ImlkIjoiMiIsInZhbHVlIjoiMjU0NzY1MzI2ODU3NTQyIn0seyJpZCI6IjciLCJ2YWx1ZSI6IjA2MDEwMTA4MjkifSx7ImlkIjoiNTMiLCJ2YWx1ZSI6IjAwMDAwMDAwMDAwMDAwMDE0NCJ9XX0=";
		
		//passcode
		//String isoInput = "eyJNVEkiOiIwMzA1IiwiZGF0YSI6W3siaWQiOiI1OCIsInZhbHVlIjoiZDY0NGQ1MjMtMWMzOS00YjQ5LTg5N2MtMDM4NjdmZjU5NzNhIn0seyJpZCI6IjU3IiwidmFsdWUiOiIzNTc2NTUwNDk4MzI5MjYifSx7ImlkIjoiMjQiLCJ2YWx1ZSI6IjMwMSJ9LHsiaWQiOiIxMyIsInZhbHVlIjoiMDAwNjA2In0seyJpZCI6IjEyIiwidmFsdWUiOiIwMDAwMDAwMDAyMDYzNSJ9LHsiaWQiOiI3IiwidmFsdWUiOiIwNjA2MDIwNjM1In0seyJpZCI6IjY0IiwidmFsdWUiOiIxMDAxMTAxMDAxMCJ9LHsiaWQiOiI1MyIsInZhbHVlIjoiMDAwMDAwMDAwMDAwMDAwMDA0In0seyJpZCI6IjEwNCIsInZhbHVlIjoiMDIxMDAyIn0seyJpZCI6IjExNC4yNCIsInZhbHVlIjoiMDAuMDAwMCJ9LHsiaWQiOiIxMTQuMjUiLCJ2YWx1ZSI6IjEifSx7ImlkIjoiMTAxIiwidmFsdWUiOiJQQVNTQ09ERSJ9LHsiaWQiOiIxMTQuMjMiLCJ2YWx1ZSI6IjAwLjAwMDAifV19";
//		
		//String isoInput = "eyJNVEkiOiIwMzA1IiwiZGF0YSI6W3siaWQiOiIxMTQuNyIsInZhbHVlIjoiYWpvQGdtYWlsLmNvbSJ9LHsiaWQiOiI1MiIsInZhbHVlIjoiNjE2YTZmNjE2YTZmIn0seyJpZCI6IjciLCJ2YWx1ZSI6IjA2MDYwNzI5MzQifSx7ImlkIjoiMTIiLCJ2YWx1ZSI6IjA3MjkzNCJ9LHsiaWQiOiIxMyIsInZhbHVlIjoiMjAxMjA2In0seyJpZCI6IjI0IiwidmFsdWUiOjMwMX0seyJpZCI6NTcsInZhbHVlIjoiV2ViIHJlcXVlc3QifSx7ImlkIjo1OCwidmFsdWUiOiJXZWIgcmVxdWVzdCJ9LHsiaWQiOjEwMSwidmFsdWUiOiJWQUxJREFUSU9OIn0seyJpZCI6MTA0LCJ2YWx1ZSI6IjAwMTAwMiJ9XX0=";

		//String isoInput = "MDMwNcKCEAEAAAAAAAAAAAAJAEEAMDcwNjEwNTQzODAwMDAwNzA2MTA1NDM4MzA1MTBDVVBMU1RGSUxUMDIxMDAyMDEzOcKAAAAAAAAAIMKAAAAAAgAAAAAEEAAAAAAAMDExMDUxLDEsMiwzLDQsNSw2LDcsOCw5LDEwLDExLDEyLDEzLDE0LDE1LDE2LDE3LDE4LDE5LDIwMDAxMTA1MSwxLDIsMyw0LDUsNiw3LDgsOSwxMCwxMSwxMiwxMywxNCwxNSwxNiwxNywxOCwxOSwyMDAwMjhgAAAAAAAAADAwMDAwMDAwMDEwMDAwMDAwMDk5";
		Base64 base = new Base64();
		//Login
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"114.7\",\"value\":\"ajo@gmail.com\"},{\"id\":\"13\",\"value\":\"000606\"},{\"id\":\"12\",\"value\":\"00000000020529\"},{\"id\":\"7\",\"value\":\"0606020529\"},{\"id\":\"52\",\"value\":\"706574657231\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"VALIDATION\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"14\",\"value\":\"1601\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"48.9\",\"value\":\"lane 2\"},{\"id\":\"48.8\",\"value\":\"11near sunpark\"},{\"id\":\"48.7\",\"value\":\"\"},{\"id\":\"48.6\",\"value\":\"my amex\"},{\"id\":\"48.4\",\"value\":\"02568\"},{\"id\":\"48.3\",\"value\":\"3\"},{\"id\":\"48.2\",\"value\":\"Shon\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"ADDCARD\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"48.13\",\"value\":\"US\"},{\"id\":\"48.10\",\"value\":\"4667\"},{\"id\":\"48.11\",\"value\":\"Texas\"},{\"id\":\"48.12\",\"value\":\"AK\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"2\",\"value\":\"2225478054365887\"},{\"id\":\"7\",\"value\":\"0606020014\"},{\"id\":\"53\",\"value\":\"000000000000000154\"}]}";
		//Registration
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120618165613\"},{\"id\":\"7\",\"value\":\"0606020635\"},{\"id\":\"64\",\"value\":\"4d2\"},{\"id\":\"52\",\"value\":\"00726F736172696F\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.25\",\"value\":\"1\"},{\"id\":\"101\",\"value\":\"REGISTER\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.22\",\"value\":\"0000000001\"},{\"id\":\"114.20\",\"value\":\"5713231661\"},{\"id\":\"114.19\",\"value\":\"5717202121\"},{\"id\":\"114.18\",\"value\":\"5717282323\"},{\"id\":\"114.17\",\"value\":\"CO\"},{\"id\":\"114.16\",\"value\":\"12345\"},{\"id\":\"114.15\",\"value\":\"DC\"},{\"id\":\"114.14\",\"value\":\"BOG\"},{\"id\":\"114.13\",\"value\":\"High Reservation\"},{\"id\":\"114.12\",\"value\":\"Leonis Stret Concord Av\"},{\"id\":\"114.8\",\"value\":\"19850528\"},{\"id\":\"114.7\",\"value\":\"chamaco1789@gmail.com\"},{\"id\":\"114.5\",\"value\":\"Carson\"},{\"id\":\"114.3\",\"value\":\"Andrew\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"114.13\",\"value\":\"\"},{\"id\":\"114.24\",\"value\":\"-122.406417\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"114.15\",\"value\":\"AL\"},{\"id\":\"64\",\"value\":\"04D2\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.5\",\"value\":\"Tambe\"},{\"id\":\"104\",\"value\":\"022002\"},{\"id\":\"12\",\"value\":\"20120929213946\"},{\"id\":\"114.17\",\"value\":\"US\"},{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"114.12\",\"value\":\"Street1\"},{\"id\":\"13\",\"value\":\"201209\"},{\"id\":\"114.4\",\"value\":\"\"},{\"id\":\"114.23\",\"value\":\"37.785834\"},{\"id\":\"114.19\",\"value\":\"1123456987\"},{\"id\":\"114.14\",\"value\":\"pune\"},{\"id\":\"101\",\"value\":\"REGISTER\"},{\"id\":\"114.3\",\"value\":\"Nikhil\"},{\"id\":\"114.8\",\"value\":\"19920929\"},{\"id\":\"52\",\"value\":\"00006e696b68696c\"},{\"id\":\"114.20\",\"value\":\"\"},{\"id\":\"114.16\",\"value\":\"12345\"},{\"id\":\"7\",\"value\":\"0929160946\"},{\"id\":\"114.22\",\"value\":\"1\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"114.7\",\"value\":\"nikhil@gmail.com\"},{\"id\":\"114.18\",\"value\":\"9874563210\"}]}";

		
		//Passcode
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606020635\"},{\"id\":\"64\",\"value\":\"04d2\"},{\"id\":\"53\",\"value\":\"290\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.25\",\"value\":\"1\"},{\"id\":\"101\",\"value\":\"PASSCODE\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"000606\"},{\"id\":\"12\",\"value\":\"00000000020635\"},{\"id\":\"7\",\"value\":\"0606020635\"},{\"id\":\"64\",\"value\":\"04D2\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.25\",\"value\":\"1\"},{\"id\":\"101\",\"value\":\"PASSCODE\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";

		//Mark Favorite Credit Card
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"48.15\",\"value\":\"989022f86c71bb6606d251a38c06ed14\"},{\"id\":\"58\",\"value\":\" \"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606022051\"},{\"id\":\"53\",\"value\":\"000000000000000291\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"SELMAIN\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";
		                                                                      					
		//UnMark Favorite Credit Card
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"48.15\",\"value\":\"bd7a57d4b7194b14c0cc17e2f4d8c7df\"},{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606022125\"},{\"id\":\"53\",\"value\":\"14\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"UNSELTMAIN\"},{\"id\":\"114.23\",\"value\":\"00.0000\"} ]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"48.15\",\"value\":\"3234915ecbd52726dd2e761b5803d021\"},{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606022321\"},{\"id\":\"53\",\"value\":\"000000000000000315\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"UNSELMAIN\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";
		//AddCard
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"14\",\"value\":\"1601\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"48.9\",\"value\":\"lane 2\"},{\"id\":\"48.8\",\"value\":\"11near sunpark\"},{\"id\":\"48.7\",\"value\":\"\"},{\"id\":\"48.6\",\"value\":\"my amex32\"},{\"id\":\"48.4\",\"value\":\"02568\"},{\"id\":\"48.3\",\"value\":\"3\"},{\"id\":\"48.2\",\"value\":\"Shon\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"ADDCARD\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"48.13\",\"value\":\"US\"},{\"id\":\"48.10\",\"value\":\"4667\"},{\"id\":\"48.11\",\"value\":\"Texas\"},{\"id\":\"48.12\",\"value\":\"AK\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"2\",\"value\":\"3336478054365123\"},{\"id\":\"7\",\"value\":\"0606020014\"},{\"id\":\"53\",\"value\":\"000000000000000154\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"14\",\"value\":\"1601\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"48.9\",\"value\":\"lane 2\"},{\"id\":\"48.8\",\"value\":\"11near sunpark\"},{\"id\":\"48.7\",\"value\":\"\"},{\"id\":\"48.6\",\"value\":\"my visa2\"},{\"id\":\"48.4\",\"value\":\"02568\"},{\"id\":\"48.3\",\"value\":\"3\"},{\"id\":\"48.2\",\"value\":\"Sagar\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"ADDCARD\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"48.13\",\"value\":\"US\"},{\"id\":\"48.10\",\"value\":\"4667\"},{\"id\":\"48.11\",\"value\":\"Texas\"},{\"id\":\"48.12\",\"value\":\"AK\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"2\",\"value\":\"111178054365887\"},{\"id\":\"7\",\"value\":\"0606020014\"},{\"id\":\"53\",\"value\":\"291\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"14\",\"value\":\"1601\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"48.9\",\"value\":\"lane 2\"},{\"id\":\"48.8\",\"value\":\"11near sunpark\"},{\"id\":\"48.7\",\"value\":\"Visa\"},{\"id\":\"48.6\",\"value\":\"my visa239\"},{\"id\":\"48.4\",\"value\":\"02568\"},{\"id\":\"48.3\",\"value\":\"3\"},{\"id\":\"48.2\",\"value\":\"Sagar\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"ADDCARD\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"48.13\",\"value\":\"US\"},{\"id\":\"48.10\",\"value\":\"4667\"},{\"id\":\"48.11\",\"value\":\"Texas\"},{\"id\":\"48.12\",\"value\":\"AK\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"2\",\"value\":\"41115478054365996\"},{\"id\":\"7\",\"value\":\"0606020014\"},{\"id\":\"53\",\"value\":\"14\"}]}";
		//Delete Card
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"48.15\",\"value\":\"29a6aa1dd3259e64798803552fc202ea\"},{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606022321\"},{\"id\":\"53\",\"value\":\"000000000000000291\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"DELCARD\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";
		
		//Edit Card
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"2\",\"value\":\"1115478054365123\"},{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"14\",\"value\":\"1601\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"48.9\",\"value\":\"lane 2\"},{\"id\":\"48.8\",\"value\":\"11near sunpark\"},{\"id\":\"48.7\",\"value\":\"\"},{\"id\":\"48.6\",\"value\":\"my amex\"},{\"id\":\"48.4\",\"value\":\"02547\"},{\"id\":\"48.3\",\"value\":\"3\"},{\"id\":\"48.2\",\"value\":\"ajo\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"EDTCARD\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"48.13\",\"value\":\"US\"},{\"id\":\"48.15\",\"value\":\"577ef1154f3240ad5b9b413aa7346a1e\"},{\"id\":\"48.10\",\"value\":\"4667\"},{\"id\":\"48.11\",\"value\":\"Texas\"},{\"id\":\"48.12\",\"value\":\"AK\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"7\",\"value\":\"0606022907\"},{\"id\":\"53\",\"value\":\"000000000000000004\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"0d1fbe56-bd07-4385-af76-a9dc6141bccc\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"14\",\"value\":\"1406\"},{\"id\":\"12\",\"value\":\"20120619052532\"},{\"id\":\"48.9\",\"value\":\"mj road\"},{\"id\":\"48.8\",\"value\":\"12,near sunpark\"},{\"id\":\"48.7\",\"value\":\"Master Card\"},{\"id\":\"48.6\",\"value\":\"my-master\"},{\"id\":\"48.4\",\"value\":\"02568\"},{\"id\":\"48.3\",\"value\":\"2\"},{\"id\":\"48.2\",\"value\":\"Ronald Spielberg\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"ADDCARD\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"48.13\",\"value\":\"US\"},{\"id\":\"48.10\",\"value\":\"5788\"},{\"id\":\"48.11\",\"value\":\"Texas\"},{\"id\":\"48.12\",\"value\":\"AZ\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"2\",\"value\":\"254765326857542\"},{\"id\":\"7\",\"value\":\"0601010829\"},{\"id\":\"53\",\"value\":\"000000000000000144\"}]}";
		
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"000606\"},{\"id\":\"12\",\"value\":\"00000000020635\"},{\"id\":\"7\",\"value\":\"0606020635\"},{\"id\":\"64\",\"value\":\"04E4\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.25\",\"value\":\"1\"},{\"id\":\"101\",\"value\":\"REGISTER\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"000606\"},{\"id\":\"12\",\"value\":\"00000000020635\"},{\"id\":\"7\",\"value\":\"0606020635\"},{\"id\":\"64\",\"value\":\"04E4\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.25\",\"value\":\"1\"},{\"id\":\"101\",\"value\":\"REGISTER\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";

		
		//String enInput = "{\"MTI\":\"0200\",\"data\":[{\"id\":\"47.5\",\"value\":\"1\"},{\"id\":\"47.6\",\"value\":\"08:00AM\"},{\"id\":\"47.3\",\"value\":\"1\"},{\"id\":\"47.4\",\"value\":\"1\"},{\"id\":\"58\",\"value\":\"\"},{\"id\":\"47.9\",\"value\":\"1\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"47.7\",\"value\":\"05:00PM\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"47.10\",\"value\":\"3 days\"},{\"id\":\"47.11\",\"value\":\"10 minutes\"},{\"id\":\"47.2\",\"value\":\"0\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606123359\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000001\"},{\"id\":\"101\",\"value\":\"COUPSET\"}]}";
		
		//App Settings
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.39\",\"value\":\"2\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"114.152\",\"value\":\"00000000000001\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"114.42\",\"value\":\"0\"},{\"id\":\"7\",\"value\":\"0606023044\"},{\"id\":\"114.65\",\"value\":\"1\"},{\"id\":\"114.35\",\"value\":\"1\"},{\"id\":\"114.36\",\"value\":\"1\"},{\"id\":\"114.37\",\"value\":\"1\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.38\",\"value\":\"0\"},{\"id\":\"114.41\",\"value\":\"0\"},{\"id\":\"114.40\",\"value\":\"0\"},{\"id\":\"101\",\"value\":\"APPSETT\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";
		
		//Get Merchant Details
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023354\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"GETMERDET\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";				
			
		//Purchase
		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"3\",\"value\":\"503161\"},{\"id\":\"48.15\",\"value\":\"a906bdcb1048f7b6765a3d8438bfd6cb\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"1\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"4\",\"value\":\"8402000000006250\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000315\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"101\",\"value\":\"PURCHASE\"}]}";
		//String enInput1 = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"3\",\"value\":\"503162\"},{\"id\":\"48.15\",\"value\":\"c81e728d9d4c2f636f067f89cc14862c\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"0\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"4\",\"value\":\"8402000000007123\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000001\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"101\",\"value\":\"PURCHASE\"},{\"id\":\"114.82\",\"value\":\"Av Street\"},{\"id\":\"114.83\",\"value\":\"Marnon\"},{\"id\":\"114.84\",\"value\":\"Vernon\"},{\"id\":\"114.85\",\"value\":\"CA\"},{\"id\":\"114.86\",\"value\":\"145289\"},{\"id\":\"114.87\",\"value\":\"USA\"},{\"id\":\"114.115\",\"value\":\"130\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"114.144\",\"value\":\"0025103157\"}]}";
		//String enInput2 = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"3\",\"value\":\"503162\"},{\"id\":\"48.15\",\"value\":\"c81e728d9d4c2f636f067f89cc14862c\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"0\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"4\",\"value\":\"8402000000007123\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000001\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"101\",\"value\":\"PURCHASE\"},{\"id\":\"114.82\",\"value\":\"Av Street\"},{\"id\":\"114.83\",\"value\":\"Marnon\"},{\"id\":\"114.84\",\"value\":\"Vernon\"},{\"id\":\"114.85\",\"value\":\"CA\"},{\"id\":\"114.86\",\"value\":\"145289\"},{\"id\":\"114.87\",\"value\":\"USA\"},{\"id\":\"114.115\",\"value\":\"130\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"114.144\",\"value\":\"0025103158\"}]}";
		//String enInput3 = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"3\",\"value\":\"503162\"},{\"id\":\"48.15\",\"value\":\"c81e728d9d4c2f636f067f89cc14862c\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"0\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"4\",\"value\":\"8402000000007123\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000001\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"101\",\"value\":\"PURCHASE\"},{\"id\":\"114.82\",\"value\":\"Av Street\"},{\"id\":\"114.83\",\"value\":\"Marnon\"},{\"id\":\"114.84\",\"value\":\"Vernon\"},{\"id\":\"114.85\",\"value\":\"CA\"},{\"id\":\"114.86\",\"value\":\"145289\"},{\"id\":\"114.87\",\"value\":\"USA\"},{\"id\":\"114.115\",\"value\":\"130\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"114.144\",\"value\":\"0025103159\"}]}";

		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"3\",\"value\":\"503161\"},{\"id\":\"48.15\",\"value\":\"a906bdcb1048f7b6765a3d8438bfd6cb\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"1\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"4\",\"value\":\"8402000000006250\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000315\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"101\",\"value\":\"PURCHASE\"},{\"id\":\"114.82\",\"value\":\"Av Street\"},{\"id\":\"114.83\",\"value\":\"Marnon\"},{\"id\":\"114.84\",\"value\":\"Vernon\"},{\"id\":\"114.85\",\"value\":\"CA\"},{\"id\":\"114.86\",\"value\":\"145289\"},{\"id\":\"114.87\",\"value\":\"USA\"},{\"id\":\"114.115\",\"value\":\"130\"},{\"id\":\"114.112\",\"value\":\"2\"},{\"id\":\"114.144\",\"value\":\"1\"}]}";
		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"3\",\"value\":\"503161\"},{\"id\":\"48.15\",\"value\":\"32f9ac1c5d0bd2bf147f1d9a11410538\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201207\"},{\"id\":\"12\",\"value\":\"20120714040804\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"1\"},{\"id\":\"41\",\"value\":\"10004320507401957\"},{\"id\":\"4\",\"value\":\"8402000000054350\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"022002\"},{\"id\":\"53\",\"value\":\"315\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"101\",\"value\":\"PURCHASE\"},{\"id\":\"26\",\"value\":\"0020\"},{\"id\":\"114.145\",\"value\":\"687318304\"}]}";
		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"3\",\"value\":\"503161\"},{\"id\":\"26\",\"value\":\"0020\"},{\"id\":\"114.145\",\"value\":\"0000423304\"},{\"id\":\"48.15\",\"value\":\"32f9ac1c5d0bd2bf147f1d9a11410538\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"11\"},{\"id\":\"41\",\"value\":\"10004320507401957\"},{\"id\":\"4\",\"value\":\"8402000000006250\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"315\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"21\"},{\"id\":\"101\",\"value\":\"PURCHASE\"}]}";
		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"3\",\"value\":\"503161\"},{\"id\":\"26\",\"value\":\"20\"},{\"id\":\"114.145\",\"value\":\"406466184\"},{\"id\":\"48.15\",\"value\":\"32f9ac1c5d0bd2bf147f1d9a11410538\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"11\"},{\"id\":\"41\",\"value\":\"10004320507401957\"},{\"id\":\"4\",\"value\":\"8402000000006250\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"315\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"21\"},{\"id\":\"101\",\"value\":\"PURCHASE\"}]}";
		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"3\",\"value\":\"503161\"},{\"id\":\"114.115\",\"value\":\"130\"},{\"id\":\"26\",\"value\":\"20\"},{\"id\":\"114.145\",\"value\":\"0000000009\"},{\"id\":\"48.15\",\"value\":\"32f9ac1c5d0bd2bf147f1d9a11410538\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"11\"},{\"id\":\"41\",\"value\":\"10004320507401957\"},{\"id\":\"4\",\"value\":\"8402000000006250\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"315\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"101\",\"value\":\"PURCHASE\"}]}";
		
		//String enInput = "{\"id\":\"3\",\"value\":\"503162\"},{\"id\":\"4\",\"value\":\"8402000000007123\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"26\",\"value\":\"30\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"42\",\"value\":\"0\"},{\"id\":\"48.15\",\"value\":\"c81e728d9d4c2f636f067f89cc14862c\"},{\"id\":\"53\",\"value\":\"000000000000000001\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"101\",\"value\":\"PURCHASE\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.60\",\"value\":\"21\"},{\"id\":\"114.82\",\"value\":\"Av Street\"},{\"id\":\"114.83\",\"value\":\"Marnon\"},{\"id\":\"114.84\",\"value\":\"Vernon\"},{\"id\":\"114.85\",\"value\":\"CA\"},{\"id\":\"114.86\",\"value\":\"145289\"},{\"id\":\"114.87\",\"value\":\"USA\"},{\"id\":\"114.115\",\"value\":\"130\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"114.144\",\"value\":\"0025103130\"}";		
		
		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"48.15\",\"value\":\"d29505c1873b38c04c9af67c809bce53\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620010148\"},{\"id\":\"7\",\"value\":\"0620010148\"},{\"id\":\"42\",\"value\":\"1\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"4\",\"value\":\"8402000000006250\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"291\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"PURCHASE\"}]}";
		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"114.82\",\"value\":\"karve road\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"114.83\",\"value\":\"lane 2\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.84\",\"value\":\"texas\"},{\"id\":\"114.85\",\"value\":\"CM\"},{\"id\":\"114.86\",\"value\":\"457809\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"114.87\",\"value\":\"MX\"},{\"id\":\"13\",\"value\":\"201208\"},{\"id\":\"12\",\"value\":\"20120810021653\"},{\"id\":\"42\",\"value\":\"0\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"PURCHASE\"},{\"id\":\"48.15\",\"value\":\"9679ccf1efaf6d71ea643c059fdfc795\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"26\",\"value\":\"30\"},{\"id\":\"114.144\",\"value\":\"0025103130\"},{\"id\":\"3\",\"value\":\"503162\"},{\"id\":\"7\",\"value\":\"0810021653\"},{\"id\":\"4\",\"value\":\"8402000000001600\"},{\"id\":\"53\",\"value\":\"000000000000000299\"}]}";
		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"114.82\",\"value\":\"karve road\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"114.83\",\"value\":\"lane 2\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.84\",\"value\":\"texas\"},{\"id\":\"114.85\",\"value\":\"CM\"},{\"id\":\"114.86\",\"value\":\"457809\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"114.87\",\"value\":\"MX\"},{\"id\":\"13\",\"value\":\"201208\"},{\"id\":\"12\",\"value\":\"20120810021653\"},{\"id\":\"42\",\"value\":\"0\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"PURCHASE\"},{\"id\":\"48.15\",\"value\":\"d0a2bcac2a58e4718dfbcbef1a10563b\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"26\",\"value\":\"30\"},{\"id\":\"114.144\",\"value\":\"0025103130\"},{\"id\":\"3\",\"value\":\"503162\"},{\"id\":\"7\",\"value\":\"0810021653\"},{\"id\":\"4\",\"value\":\"8402000000012600\"},{\"id\":\"114.115\",\"value\":\"293\"},{\"id\":\"53\",\"value\":\"299\"}]}";
		//String enInput = "{\"MTI\":\"0204\",\"data\":[{\"id\":\"114.82\",\"value\":\"karve road\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"114.83\",\"value\":\"lane 2\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.84\",\"value\":\"texas\"},{\"id\":\"114.85\",\"value\":\"CM\"},{\"id\":\"114.86\",\"value\":\"457809\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"114.87\",\"value\":\"MX\"},{\"id\":\"13\",\"value\":\"201208\"},{\"id\":\"12\",\"value\":\"20120810021653\"},{\"id\":\"42\",\"value\":\"0\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.60\",\"value\":\"6\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"PURCHASE\"},{\"id\":\"48.15\",\"value\":\"8974da9e01b851ba2f1bbd7ee0d776e8\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"26\",\"value\":\"30\"},{\"id\":\"114.144\",\"value\":\"0025103130\"},{\"id\":\"3\",\"value\":\"503162\"},{\"id\":\"7\",\"value\":\"0810021653\"},{\"id\":\"4\",\"value\":\"8402000000050000\"},{\"id\":\"53\",\"value\":\"330\"}]}";
		
		//Coupon Settings
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"47.5\",\"value\":\"1\"},{\"id\":\"47.6\",\"value\":\"2012062210\"},{\"id\":\"47.3\",\"value\":\"1\"},{\"id\":\"47.4\",\"value\":\"1\"},{\"id\":\"58\",\"value\":\"\"},{\"id\":\"47.9\",\"value\":\"1\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"47.7\",\"value\":\"2012062310\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"47.10\",\"value\":\"4320\"},{\"id\":\"47.11\",\"value\":\"10\"},{\"id\":\"47.2\",\"value\":\"0\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606123359\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000001\"},{\"id\":\"101\",\"value\":\"COUPSET\"}]}";
		
		//Edit Notify Travel
		//String enInput = "{\"MTI\":\"0304\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"62.2\",\"value\":\"mumbai\"},{\"id\":\"62.3\",\"value\":\"2012070610\"},{\"id\":\"62.4\",\"value\":\"2012070710\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"62.5\",\"value\":\"0000000030\"},{\"id\":\"7\",\"value\":\"0607104548\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000001\"},{\"id\":\"101\",\"value\":\"SETNOTIFY\"}]}";
		
		//Add Notify Travel
		//String enInput = "{\"MTI\":\"0304\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201207\"},{\"id\":\"62.2\",\"value\":\"dubai\"},{\"id\":\"62.3\",\"value\":\"2012060610\"},{\"id\":\"62.4\",\"value\":\"2012070610\"},{\"id\":\"12\",\"value\":\"00000000085928\"},{\"id\":\"7\",\"value\":\"0607085928\"},{\"id\":\"53\",\"value\":\"4\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"ADDNOTIFYT\"}]}";	

		//Delete Notify Travel
		//String enInput = "{\"MTI\":\"0304\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"7\",\"value\":\"0607104643\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"101\",\"value\":\"DELNOTIFY\"},{\"id\":\"62.5\",\"value\":\"0000000030\"},{\"id\":\"12\",\"value\":\"20120620065109\"}]}";
		

		//Coupon Add Favorite Address
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"47.14\",\"value\":\"new Stanford\"},{\"id\":\"47.15\",\"value\":\"stanford\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"47.13\",\"value\":\"work\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"114.49\",\"value\":\"0\"},{\"id\":\"7\",\"value\":\"0606123922\"},{\"id\":\"114.48\",\"value\":\"\"},{\"id\":\"47.18\",\"value\":\"CO\"},{\"id\":\"47.17\",\"value\":\"1734\"},{\"id\":\"47.16\",\"value\":\"stanford\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"114.54\",\"value\":\"291\"},{\"id\":\"101\",\"value\":\"CPADFVADD\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"47.20\",\"value\":\"19\"},{\"id\":\"58\",\"value\":\"a6b45a65-9a5c-4a2e-b9ba-ad2e57569d11\"},{\"id\":\"7\",\"value\":\"0606124446\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"4\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"101\",\"value\":\"ADDFAVCAT\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"12\",\"value\":\"20120620065109\"}]}";
		
		
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606123922\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"47.14\",\"value\":\"new Stanford\"},{\"id\":\"47.15\",\"value\":\"stanford\"},{\"id\":\"47.13\",\"value\":\"work\"},{\"id\":\"47.17\",\"value\":\"1734\"},{\"id\":\"47.16\",\"value\":\"CA\"},{\"id\":\"47.18\",\"value\":\"US\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"291\"},{\"id\":\"101\",\"value\":\"CPADFVADD\"}]}";	
		//Edit Profile - Fetch Details
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"7\",\"value\":\"0606023918\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"299\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"101\",\"value\":\"REGFETCH\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"12\",\"value\":\"20120620065109\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"132343\"},{\"id\":\"114.19\",\"value\":\"5718289292\"},{\"id\":\"57\",\"value\":\"adfwerwefwe\"},{\"id\":\"114.18\",\"value\":\"5829383838\"},{\"id\":\"114.17\",\"value\":\"CO\"},{\"id\":\"114.8\",\"value\":\"19711015\"},{\"id\":\"114.7\",\"value\":\"ajo@gmail.com\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"114.4\",\"value\":\"F\"},{\"id\":\"114.5\",\"value\":\"Gonzales\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"114.3\",\"value\":\"Reger\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.22\",\"value\":\"312373833\"},{\"id\":\"101\",\"value\":\"REGEDT\"},{\"id\":\"114.20\",\"value\":\"3152345323\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"7\",\"value\":\"0606024112\"},{\"id\":\"53\",\"value\":\"299\"},{\"id\":\"114.12\",\"value\":\"Av Plazas\"},{\"id\":\"114.13\",\"value\":\"Riod\"},{\"id\":\"114.14\",\"value\":\"Bogota\"},{\"id\":\"114.15\",\"value\":\"DC\"},{\"id\":\"114.16\",\"value\":\"12345\"}]}";
		
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"47.14\",\"value\":\"new Stanford\"},{\"id\":\"47.15\",\"value\":\"stanford\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"47.13\",\"value\":\"work\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"114.49\",\"value\":\"0\"},{\"id\":\"7\",\"value\":\"0606123922\"},{\"id\":\"114.48\",\"value\":\"\"},{\"id\":\"47.18\",\"value\":\"\"},{\"id\":\"47.17\",\"value\":\"1734\"},{\"id\":\"47.16\",\"value\":\"stanford\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000291\"},{\"id\":\"114.50\",\"value\":\"\"},{\"id\":\"101\",\"value\":\"CPADFVADD\"}]}";

		//Merchant Detail
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023354\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"101\",\"value\":\"GETMERDET\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";
		
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"7\",\"value\":\"0606023918\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000291\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"64\",\"value\":\"02D4\"},{\"id\":\"52\",\"value\":\"73616368696e\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"101\",\"value\":\"REGFETCH\"},{\"id\":\"12\",\"value\":\"20120620065109\"}]}";
		
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.19\",\"value\":\"12145708754\"},{\"id\":\"114.18\",\"value\":\"11564586235\"},{\"id\":\"114.17\",\"value\":\"US\"},{\"id\":\"114.8\",\"value\":\"1981070610\"},{\"id\":\"114.7\",\"value\":\"ajo@gmail.com\"},{\"id\":\"114.4\",\"value\":\"T\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"114.5\",\"value\":\"ajo\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"114.3\",\"value\":\"ajo\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.22\",\"value\":\"4\"},{\"id\":\"101\",\"value\":\"REGEDT\"},{\"id\":\"114.20\",\"value\":\"12536253258\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"7\",\"value\":\"0606024112\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"114.12\",\"value\":\"Aventura  Florida United States (305) 935-1110\"},{\"id\":\"114.13\",\"value\":\"Aventura  Florida United States (305) 935-1110\"},{\"id\":\"114.14\",\"value\":\"Texas\"},{\"id\":\"114.15\",\"value\":\"AO\"},{\"id\":\"114.16\",\"value\":\"20008\"}]}";

		//Edit Profile
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"52\",\"value\":\"706574657231\"},{\"id\":\"64\",\"value\":\"4D2\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.19\",\"value\":\"12145708754\"},{\"id\":\"114.18\",\"value\":\"11564586235\"},{\"id\":\"114.17\",\"value\":\"US\"},{\"id\":\"114.8\",\"value\":\"1981060610\"},{\"id\":\"114.7\",\"value\":\"ajo@gmail.com\"},{\"id\":\"114.4\",\"value\":\"T\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"114.5\",\"value\":\"ajo\"},{\"id\":\"114.3\",\"value\":\"ajo\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.22\",\"value\":\"4\"},{\"id\":\"101\",\"value\":\"REGEDT\"},{\"id\":\"114.20\",\"value\":\"12536253258\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"7\",\"value\":\"0606024112\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"114.12\",\"value\":\"Aventura  Florida United States (305) 935-1110\"},{\"id\":\"114.13\",\"value\":\"Aventura  Florida United States (305) 935-1110\"},{\"id\":\"114.14\",\"value\":\"Texas\"},{\"id\":\"114.15\",\"value\":\"AO\"},{\"id\":\"114.16\",\"value\":\"20008\"}]}";
		
		//User Availability
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.18\",\"value\":\"2070807047\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"114.7\",\"value\":\"ajo@gmail.com\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606015839\"},{\"id\":\"53\",\"value\":\"000000000000000291\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"USERAVAIL\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.18\",\"value\":\"11564586235\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"114.7\",\"value\":\"ajo@gmail.com\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606015839\"},{\"id\":\"53\",\"value\":\"291\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"USERAVAIL\"}]}";		
		
		//Device tracking after mobile lock
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120621060720\"},{\"id\":\"7\",\"value\":\"0621060720\"},{\"id\":\"114.7\",\"value\":\"ajo@gmail.com\"},{\"id\":\"53\",\"value\":\"000000000000000291\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"DEVTRACK\"},{\"id\":\"114.18\",\"value\":\"11564586235\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120621060720\"},{\"id\":\"7\",\"value\":\"0621060720\"},{\"id\":\"114.7\",\"value\":\"sagartambe@gmail.com\"},{\"id\":\"53\",\"value\":\"291\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"DEVTRACK\"},{\"id\":\"114.57\",\"value\":\"2\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120621060720\"},{\"id\":\"7\",\"value\":\"0621060720\"},{\"id\":\"53\",\"value\":\"291\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"DEVTRACK\"},{\"id\":\"114.57\",\"value\":\"1\"}]}";
		
		//Add Shipping Address
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.87\",\"value\":\"CO\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120702065109\"},{\"id\":\"7\",\"value\":\"1207024819\"},{\"id\":\"53\",\"value\":\"000000000000000116\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.82\",\"value\":\"Rosver Av Sunse St 45\"},{\"id\":\"114.83\",\"value\":\"lane 2\"},{\"id\":\"101\",\"value\":\"ADDSHIPSAD\"},{\"id\":\"114.84\",\"value\":\"New York\"},{\"id\":\"114.85\",\"value\":\"NY\"},{\"id\":\"114.86\",\"value\":\"12345\"},{\"id\":\"114.87\",\"value\":\"CO   \"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.87\",\"value\":\"US\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606024819\"},{\"id\":\"53\",\"value\":\"315\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.82\",\"value\":\"Rosver Av Sunse St 45\"},{\"id\":\"114.83\",\"value\":\"lane 2\"},{\"id\":\"101\",\"value\":\"ADDSHIPSAD\"},{\"id\":\"114.84\",\"value\":\"New York\"},{\"id\":\"114.85\",\"value\":\"NY\"},{\"id\":\"114.86\",\"value\":\"1002\"},{\"id\":\"114.87\",\"value\":\"US\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.87\",\"value\":\"US\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606024819\"},{\"id\":\"53\",\"value\":\"315\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.82\",\"value\":\"Rosver Av Sunse St 45\"},{\"id\":\"114.83\",\"value\":\"lane 2\"},{\"id\":\"101\",\"value\":\"ADDSHIPSAD\"},{\"id\":\"114.84\",\"value\":\"New York\"},{\"id\":\"114.85\",\"value\":\"NY\"},{\"id\":\"114.86\",\"value\":\"1002\"},{\"id\":\"114.87\",\"value\":\"US\"}]}";
		
		//Delete Shipping Address
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"7\",\"value\":\"0607090304\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.115\",\"value\":\"00000000241\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"101\",\"value\":\"DELSHIPSAD\"},{\"id\":\"12\",\"value\":\"20120620065109\"}]}";

		//User Password
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"7\",\"value\":\"0607104927\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"52\",\"value\":\"706574657231\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"101\",\"value\":\"USRPSWD\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"12\",\"value\":\"20120620065109\"}]}";
		
		//Item Info Details
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620080636\"},{\"id\":\"7\",\"value\":\"0620080636\"},{\"id\":\"114.90\",\"value\":\"2\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"53\",\"value\":\"000000000000000118\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"INFITEDET\"}]}";

		//Edit Shipping Addreses
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.115\",\"value\":\"250\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606024819\"},{\"id\":\"53\",\"value\":\"311\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.82\",\"value\":\"Av cucaracho\"},{\"id\":\"114.83\",\"value\":\"carapacho\"},{\"id\":\"101\",\"value\":\"EDCONSHAD\"},{\"id\":\"114.84\",\"value\":\"Vernon\"},{\"id\":\"114.85\",\"value\":\"CA\"},{\"id\":\"114.86\",\"value\":\"90201\"},{\"id\":\"114.87\",\"value\":\"US\"}]}";		

		//Delete shipiong address
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"7\",\"value\":\"0607090304\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.115\",\"value\":\"120\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"291\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"101\",\"value\":\"DELSHIPSAD\"},{\"id\":\"12\",\"value\":\"20120620065109\"}]}";

		//Product Purchase Details
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.47\",\"value\":\"23\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"42\",\"value\":\"2\"},{\"id\":\"12\",\"value\":\"20120620080636\"},{\"id\":\"7\",\"value\":\"0620080636\"},{\"id\":\"53\",\"value\":\"291\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"PROPURDE\"},{\"id\":\"114.112\",\"value\":\"2\"},{\"id\":\"114.144\",\"value\":\"2\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"2d7f7882-4e48-464a-95f3-e966b481013b\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201207\"},{\"id\":\"12\",\"value\":\"20120723120427\"},{\"id\":\"7\",\"value\":\"0723120427\"},{\"id\":\"114.144\",\"value\":\"2\"},{\"id\":\"114.112\",\"value\":\"2\"},{\"id\":\"53\",\"value\":\"000000000000000299\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"42\",\"value\":\"2\"},{\"id\":\"41\",\"value\":\"2\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.47\",\"value\":\"23\"},{\"id\":\"101\",\"value\":\"PROPURDE\"}]}";
		
		//Consumer Report Abuse Status
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023354\"},{\"id\":\"41\",\"value\":\"56\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"COREABST\"},{\"id\":\"114.143\",\"value\":\"1\"},{\"id\":\"114.154\",\"value\":\"10\"}]}";
		
		//Coupon Share
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"47.21\",\"value\":\"1\"},{\"id\":\"47.24\",\"value\":\"Nice promotion\"},{\"id\":\"47.25\",\"value\":\"ajo@gmail.com\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606123359\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"101\",\"value\":\"SHCOUP\"},{\"id\":\"114.23\",\"value\":\"123.12345\"},{\"id\":\"114.24\",\"value\":\"123.12345\"}]}";

		//News
		//Terminal Update Qrcode
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606123359\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000011\"},{\"id\":\"101\",\"value\":\"TERUPQR\"},{\"id\":\"114.104\",\"value\":\"http://www.nomorecards.net/development/merchant/116?amt=500\"}]}";

		//Transaction Count
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606123359\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000011\"},{\"id\":\"101\",\"value\":\"TRANCOUNT\"},{\"id\":\"114.138\",\"value\":\"20120312\"},{\"id\":\"114.139\",\"value\":\"20120312\"}]}";
				
		//Merchant Count
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.14\",\"value\":\"Los Angeles\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"114.15\",\"value\":\"CA\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023044\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"MERCCOUNT\"},{\"id\":\"114.143\",\"value\":\"1\"}]}";

		//Report Abuse Status
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.143\",\"value\":\"1\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"114.154\",\"value\":\"6\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023044\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"COREABST\"}]}";

		//Kill Phone Count
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023044\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"KIPHCOUNT\"}]}";

		//New Installation Count
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023044\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"NEWINCOUNT\"}]}";

		//Consumer Report Abuse Count
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023044\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"COREPCOUNT\"},{\"id\":\"114.153\",\"value\":\"20120630055122\"}]}";

		//Shipping and tax calculation
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023044\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"COREPCOUNT\"},{\"id\":\"114.153\",\"value\":\"20120630055122\"}]}";
		
		//Change Consumer Contact Request Status
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606023044\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"CHCSCORQST\"},{\"id\":\"114.54\",\"value\":\"4\"},{\"id\":\"114.143\",\"value\":\"1\"}]}";
		
		//Consumer Registration
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"114.7\",\"value\":\"davidvilla@gmail.com\"},{\"id\":52,\"value\":\"313233343536\"},{\"id\":64,\"value\":\"02D4\"},{\"id\":\"114.3\",\"value\":\"Test\"},{\"id\":\"114.5\",\"value\":\"Demo\"},{\"id\":\"114.4\",\"value\":\"S\"},{\"id\":\"114.151\",\"value\":\"1\"},{\"id\":\"114.8\",\"value\":\"04051990\"},{\"id\":\"114.18\",\"value\":\"598-1111117894\"},{\"id\":\"114.21\",\"value\":\"44-4577778977\"},{\"id\":\"114.22\",\"value\":\"3\"},{\"id\":\"114.12\",\"value\":\"ST main tower2\"},{\"id\":\"114.13\",\"value\":\"ST main tower1\"},{\"id\":\"114.14\",\"value\":\"newyark\"},{\"id\":\"114.15\",\"value\":\"CA\"},{\"id\":\"114.16\",\"value\":\"123456\"},{\"id\":\"114.17\",\"value\":\"US\"},{\"id\":\"114.181\",\"value\":\"ST main tower2\"},{\"id\":\"114.182\",\"value\":\"ST main tower1\"},{\"id\":\"114.183\",\"value\":\"newyark\"},{\"id\":\"114.184\",\"value\":\"AB\"},{\"id\":\"114.185\",\"value\":\"123456\"},{\"id\":\"114.186\",\"value\":\"CA\"},{\"id\":\"114.63\",\"value\":\"1\"},{\"id\":\"114.44\",\"value\":\"6\"},{\"id\":\"114.67\",\"value\":\"11\"},{\"id\":\"114.64\",\"value\":\"FFFF\"},{\"id\":\"114.45\",\"value\":\"111\"},{\"id\":\"114.68\",\"value\":\"mac\"},{\"id\":101,\"value\":\"REGCONS\"},{\"id\":7,\"value\":\"0606075422\"},{\"id\":12,\"value\":\"075422\"},{\"id\":13,\"value\":\"201206\"},{\"id\":24,\"value\":301},{\"id\":57,\"value\":\"Web request\"},{\"id\":58,\"value\":\"Web request\"},{\"id\":104,\"value\":\"001002\"},{\"id\":11,\"value\":\"090748530581\"}]}";
		
		//Consumer Manager
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"7\",\"value\":\"0607104927\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"52\",\"value\":\"706574657231\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"000000000000000004\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"101\",\"value\":\"CONSMAN\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"114.122\",\"value\":\"3\"}]}";

		
		//Admin Unlink Phone
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":53,\"value\":\"303\"},{\"id\":\"114.7\",\"value\":\"peter666@gmail.com\"},{\"id\":52,\"value\":\"\"},{\"id\":64,\"value\":\"1234\"},{\"id\":\"114.77\",\"value\":\"2\"},{\"id\":\"114.78\",\"value\":\"hhh\"},{\"id\":12,\"value\":\"20120620065109\"},{\"id\":13,\"value\":\"201206\"},{\"id\":24,\"value\":301},{\"id\":57,\"value\":\"Web request\"},{\"id\":58,\"value\":\"Web request\"},{\"id\":101,\"value\":\"ULKPHOADM\"},{\"id\":104,\"value\":\"001003\"},{\"id\":7,\"value\":\"0612011320\"},{\"id\":11,\"value\":\"612518506464\"}]}";
		
		
		//Coupon Brand List
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"101\",\"value\":\"COUBRLST\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"12\",\"value\":\"0713100108\"},{\"id\":\"120.2\",\"value\":\"1\"},{\"id\":\"120.3\",\"value\":\"100\"},{\"id\":\"24\",\"value\":\"305\"},{\"id\":\"53\",\"value\":\"315\"},{\"id\":\"7\",\"value\":\"0713100108\"}]}";
		
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120618165613\"},{\"id\":\"7\",\"value\":\"0606020635\"},{\"id\":\"64\",\"value\":\"FFD2\"},{\"id\":\"52\",\"value\":\"00726F736172696F\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.25\",\"value\":\"1\"},{\"id\":\"101\",\"value\":\"REGISTER\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.22\",\"value\":\"0000000001\"},{\"id\":\"114.20\",\"value\":\"5713231661\"},{\"id\":\"114.19\",\"value\":\"5717202121\"},{\"id\":\"114.18\",\"value\":\"5717282323\"},{\"id\":\"114.17\",\"value\":\"CO\"},{\"id\":\"114.16\",\"value\":\"12345\"},{\"id\":\"114.15\",\"value\":\"DC\"},{\"id\":\"114.14\",\"value\":\"BOG\"},{\"id\":\"114.13\",\"value\":\"High Reservation\"},{\"id\":\"114.12\",\"value\":\"Leonis Stret Concord Av\"},{\"id\":\"114.8\",\"value\":\"19810612\"},{\"id\":\"114.7\",\"value\":\"sagar20@gmail.com\"},{\"id\":\"114.5\",\"value\":\"Carson\"},{\"id\":\"114.3\",\"value\":\"Andrew\"}]}";
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"114.13\",\"value\":\"\"},{\"id\":\"114.24\",\"value\":\"-122.406417\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"114.15\",\"value\":\"AL\"},{\"id\":\"64\",\"value\":\"04D2\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.5\",\"value\":\"Tambe\"},{\"id\":\"104\",\"value\":\"022002\"},{\"id\":\"12\",\"value\":\"20120929213946\"},{\"id\":\"114.17\",\"value\":\"US\"},{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"114.12\",\"value\":\"Street1\"},{\"id\":\"13\",\"value\":\"201209\"},{\"id\":\"114.4\",\"value\":\"\"},{\"id\":\"114.23\",\"value\":\"37.785834\"},{\"id\":\"114.19\",\"value\":\"1123456987\"},{\"id\":\"114.14\",\"value\":\"pune\"},{\"id\":\"101\",\"value\":\"REGISTER\"},{\"id\":\"114.3\",\"value\":\"Nikhil\"},{\"id\":\"114.8\",\"value\":\"19920929\"},{\"id\":\"52\",\"value\":\"00006e696b68696c\"},{\"id\":\"114.20\",\"value\":\"\"},{\"id\":\"114.16\",\"value\":\"12345\"},{\"id\":\"7\",\"value\":\"0929160946\"},{\"id\":\"114.22\",\"value\":\"1\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"114.7\",\"value\":\"nikhil2@gmail.com\"},{\"id\":\"114.18\",\"value\":\"9874563210\"}]}";
		
		
		//Coupon Add Favorite Categories
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"47.20\",\"value\":\"18,15,16,13\"},{\"id\":\"58\",\"value\":\"8d7d8e17-5666-4027-8ed6-ff6d2bf8aa4b\"},{\"id\":\"7\",\"value\":\"0719023900\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"53\",\"value\":\"000000000000000299\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201207\"},{\"id\":\"101\",\"value\":\"ADDFAVCAT\"},{\"id\":\"11\",\"value\":\"000000000123\"},{\"id\":\"12\",\"value\":\"20120719023900\"}]}";

		//Add Coupon Stats
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"47.22\",\"value\":\"20120606\"},{\"id\":\"47.21\",\"value\":\"54\"},{\"id\":\"58\",\"value\":\"a6b45a65-9a5c-4a2e-b9ba-ad2e57569d11\"},{\"id\":\"47.23\",\"value\":\"share\"},{\"id\":\"57\",\"value\":\"355921042675148\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"7\",\"value\":\"0606011415\"},{\"id\":\"53\",\"value\":\"342\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"101\",\"value\":\"ADDCOUPST\"}]}";
		
		//Add New Shopping List
		String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"7\",\"value\":\"0820015854\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"299\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201208\"},{\"id\":\"101\",\"value\":\"ADNWSHOPLST\"},{\"id\":\"121.31\",\"value\":\"BIBI\"}]}";

		//Remove Shopping List
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"7\",\"value\":\"0820015854\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"299\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201208\"},{\"id\":\"101\",\"value\":\"REMSHOPLST\"},{\"id\":\"121.32\",\"value\":\"4,5,14\"}]}";
		
		//Add Shopping List	Item	
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"7\",\"value\":\"0820015854\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"299\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201208\"},{\"id\":\"101\",\"value\":\"ADSHOPLSTIT\"},{\"id\":\"121.30\",\"value\":\"4\"},{\"id\":\"47.21\",\"value\":\"6\"}]}";

		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"7\",\"value\":\"1001141222\"},{\"id\":\"114.144\",\"value\":\"025103132\"},{\"id\":\"120.2\",\"value\":\"1\"},{\"id\":\"24\",\"value\":\"305\"},{\"id\":\"53\",\"value\":\"299\"},{\"id\":\"101\",\"value\":\"ProductPackageCa\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"12\",\"value\":\"20121001194222\"},{\"id\":\"104\",\"value\":\"022002\"},{\"id\":\"120.3\",\"value\":\"8\"}]}";

		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"7\",\"value\":\"0820015854\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"299\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201208\"},{\"id\":\"101\",\"value\":\"VALPSPS\"},{\"id\":\"114.89\",\"value\":\"4\"},{\"id\":\"64\",\"value\":\"04d2\"}]}";

		//Add New My Orders 
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"7\",\"value\":\"0820015854\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"299\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201208\"},{\"id\":\"101\",\"value\":\"ADNWMYORD\"},{\"id\":\"114.112\",\"value\":\"1\"},{\"id\":\"114.144\",\"value\":\"4\"}]}";

		//App Settings
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"114.39\",\"value\":\"2\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"114.152\",\"value\":\"00000000000001\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120620065109\"},{\"id\":\"114.42\",\"value\":\"0\"},{\"id\":\"7\",\"value\":\"0606023044\"},{\"id\":\"114.65\",\"value\":\"1\"},{\"id\":\"114.35\",\"value\":\"1\"},{\"id\":\"114.36\",\"value\":\"1\"},{\"id\":\"114.37\",\"value\":\"1\"},{\"id\":\"53\",\"value\":\"291\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.38\",\"value\":\"0\"},{\"id\":\"114.41\",\"value\":\"0\"},{\"id\":\"114.40\",\"value\":\"0\"},{\"id\":\"101\",\"value\":\"APPSETT\"},{\"id\":\"114.23\",\"value\":\"00.0000\"}]}";
		
		
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"d644d523-1c39-4b49-897c-03867ff5973a\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201206\"},{\"id\":\"12\",\"value\":\"20120618165613\"},{\"id\":\"7\",\"value\":\"0606020635\"},{\"id\":\"64\",\"value\":\"4d2\"},{\"id\":\"52\",\"value\":\"73616368696e\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"114.24\",\"value\":\"00.0000\"},{\"id\":\"114.25\",\"value\":\"1\"},{\"id\":\"101\",\"value\":\"REGISTER\"},{\"id\":\"114.23\",\"value\":\"00.0000\"},{\"id\":\"114.22\",\"value\":\"0000000001\"},{\"id\":\"114.20\",\"value\":\"5713231661\"},{\"id\":\"114.19\",\"value\":\"5717202121\"},{\"id\":\"114.18\",\"value\":\"5717282323\"},{\"id\":\"114.17\",\"value\":\"US\"},{\"id\":\"114.16\",\"value\":\"12345\"},{\"id\":\"114.15\",\"value\":\"NY\"},{\"id\":\"114.14\",\"value\":\"BOG\"},{\"id\":\"114.13\",\"value\":\"High Reservation\"},{\"id\":\"114.12\",\"value\":\"Leonis Stret Concord Av\"},{\"id\":\"114.8\",\"value\":\"19850528\"},{\"id\":\"114.7\",\"value\":\"test3@gmail.com\"},{\"id\":\"114.5\",\"value\":\"Carson\"},{\"id\":\"114.3\",\"value\":\"Andrew\"}]}";
		
		
		//String enInput = "{\"MTI\":\"0305\",\"data\":[{\"id\":\"58\",\"value\":\"9830ad9b-2621-4727-b25d-d1929414e837\"},{\"id\":\"7\",\"value\":\"0820015854\"},{\"id\":\"57\",\"value\":\"357655049832926\"},{\"id\":\"104\",\"value\":\"021002\"},{\"id\":\"53\",\"value\":\"299\"},{\"id\":\"24\",\"value\":\"301\"},{\"id\":\"13\",\"value\":\"201208\"},{\"id\":\"101\",\"value\":\"ADNWSHOPLST\"},{\"id\":\"121.31\",\"value\":\"RYAN2\"}]}";
		
		String isoInput = new String(base.encodeAsString(enInput.getBytes()));
		
//String tst = base.decode(isoInput).toString();
//System.out.println(tst);
		if (packUnpack == "pack"){
			
			HashMap <String, String> isofields = new HashMap<String, String>();
			
			JSONObject json = (JSONObject) JSONSerializer.toJSON(new String(base.decode(isoInput)));
			int size = json.getJSONArray("data").size();
			String MTI = json.getString("MTI");
			
			for (int i = 0; i < size; i++) {
				String field = json.getJSONArray("data").getJSONObject(i).getString("id");
				String value = json.getJSONArray("data").getJSONObject(i).getString("value");
	
				if (!value.equals("null") && !field.equals("null"))
					isofields.put(field, value);
			}
			
			String isoMessage = packIsoMsg(MTI,isofields);
			System.out.println("Encoded Message: "+isoMessage+"\n");
			
			int port = 1020;
			int dataport = -1;
			int rev = 1;

			String clientRequest = "";

			
//			Client myclient = new Client(port, dataport, "100.43.205.74", rev);
			Client myclient = new Client(port, dataport, "192.168.0.7", rev);
//			Client myclient = new Client(port, dataport, "192.168.2.101", rev);
//			Client myclient = new Client(port, dataport, "192.168.0.13", rev);
//			Client myclient = new Client(port, dataport, "192.168.0.14", rev);
//		Client myclient = new Client(port, dataport, "192.168.2.101", rev);
			myclient.SendString(isoMessage);
			clientRequest = myclient.RecvString(4096);
			
			/*=========*/
			
			unpackIsoMsg(clientRequest);
			Set<?> set = PARSEDISOMESSAGE.entrySet();
			Iterator<?> i = set.iterator(); 
			
			JSONObject object = new JSONObject();	
			JSONObject data = new JSONObject();
			
			while(i.hasNext()) { 
				
				@SuppressWarnings("rawtypes")
				Map.Entry me = (Map.Entry)i.next();
		
				String key = me.getKey().toString();
				String value = me.getValue().toString();
   
				data.put("id",key);
				data.put("value",value);
				object.accumulate("data", data);
			}
			System.out.println("Decoded Message: "+object);
		} else {
			unpackIsoMsg(new String(base.decode(isoInput)));
			//System.out.println(PARSEDISOMESSAGE);
			Set<?> set = PARSEDISOMESSAGE.entrySet();
			Iterator<?> i = set.iterator(); 
			
			JSONObject object = new JSONObject();	
			JSONObject data = new JSONObject();
			
			while(i.hasNext()) { 
				
				@SuppressWarnings("rawtypes")
				Map.Entry me = (Map.Entry)i.next();
		
				String key = me.getKey().toString();
				String value = me.getValue().toString();
   
				data.put("id",key);
				data.put("value",value);
				object.accumulate("data", data);
			}
			System.out.println(object);
		}
	}
	
	   public static void SendString(String str) throws IOException
	   {

		/* convert our string into an array of bytes */

		ByteArrayOutputStream bytestream;
		bytestream = new ByteArrayOutputStream(str.length());

		DataOutputStream out;
		out = new DataOutputStream(bytestream);

		for (int i=0; i<str.length(); i++)
			out.write((byte) str.charAt(i));



	   }	
	
	
	public static String packIsoMsg(String MTI, HashMap <String, String> isofields) throws Exception
	{
		Set<?> set = isofields.entrySet();
		Iterator<?> i = set.iterator(); 
		ArrayList<String> keys = new ArrayList<String>();
		HashMap<String, ArrayList<Integer>> subFiledBitMapHolder = new HashMap<String, ArrayList<Integer>>();  
		
		//Lets start building the ISO Message
		ISOMESSAGE.put("MTI", MTI);
		
		while(i.hasNext()) { 
			
		   @SuppressWarnings("rawtypes")
		   Map.Entry me = (Map.Entry)i.next();
				
		   String fields = me.getKey().toString();
		   
		   keys.add(fields);
	       
	       //Get Schema of fields
	       String dataType = null;
		   String fieldLenType = null;
		   String fieldMaxLen = null;
		   String subfieldIndicator = null;
		   String hasSubfield = null;
		   @SuppressWarnings("unused")
		   String hasSubfieldBitmap = null;
	       
		   String breakFieldForSubField[] = null;
	       String baseField = null;  
	       Integer subField = null;
	       
	       
	       try{
		       breakFieldForSubField = fields.split("[.]");
		       baseField = breakFieldForSubField[0].toString();
		       subField = Integer.parseInt(breakFieldForSubField[1]);
		       try{
		    	   subFiledBitMapHolder.get(baseField).add(subField);
		       }
		       catch(Exception e)
		       {
		    	   ArrayList<Integer> arrSubFields = new ArrayList<Integer>();
		    	   arrSubFields.add(subField);
		    	   subFiledBitMapHolder.put(baseField, arrSubFields);
		       }
		       
		   }catch(Exception e)
	       {
			   //Nothing to do here
	       }
	       
	       
	       String schema = ISOSCHEMA.get(fields);
	       //System.out.println(fields + " = " + schema);
	       String arrSchema[] = schema.split("-");
	       dataType = arrSchema[0];
	       fieldLenType = arrSchema[1];
	       fieldMaxLen = arrSchema[2];
	       subfieldIndicator = arrSchema[3];
	       String arrSubField[] = subfieldIndicator.split("_");
	       
	       hasSubfieldBitmap = arrSubField[1];
	       
	       if(dataType.equalsIgnoreCase("NUM") && fieldLenType.equalsIgnoreCase("1"))
	       {
	    	   throw new IOException("Field:" +fields + " has data type NUM is having field-size = 1 in ISOSCHEMA. Try assign NUMERIC data type");
	       }
	       
    	   String fieldVlaue = isofields.get(fields);
    	   Integer fieldLength = fieldVlaue.length();
   	   
    	   String strfieldLength = fieldLength.toString();
    	   
    	   if(dataType.equalsIgnoreCase("NUMERIC"))
    	   {   
    		   if(Integer.parseInt(fieldMaxLen) >= fieldLength) 
    		   {
    			   String newFieldValue = "";
    			   if (fieldVlaue.equals(""))
    			   {
    				   newFieldValue = String.format("%0"+ fieldMaxLen +"d", 0);
    			   }
    			   else if (fieldLength == Integer.parseInt(fieldMaxLen))
    			   {
    				   newFieldValue = fieldVlaue;
    			   }
    			   else
    			   {
    				   newFieldValue = String.format("%0"+ fieldMaxLen +"d", Long.parseLong(fieldVlaue));
    			   }
            	   
    			   ISOMESSAGE.put(fields, newFieldValue);
    		   }
    		   else
    		   {
    			   throw new IOException("Field:"+fields + " Has bigger value. Its set "+fieldMaxLen +" in ISOSCHEMA and you have entered" + fieldLength );
    		   }
    	   }
    	   else if (dataType.equalsIgnoreCase("CHAR") || dataType.equalsIgnoreCase("NUM"))
    	   {
    		   if(Integer.parseInt(fieldMaxLen) >= fieldLength) //?????????? fieldLength OR fieldMaxLen -- Discuss with Sagar
    		   {
    			   String newFieldLen = String.format("%0"+ fieldLenType +"d", Long.parseLong(strfieldLength));
    			   
    			   /////
	    		   ISOMESSAGE.put(fields.toString(), newFieldLen + fieldVlaue);
	    		   ////
    		   }
    		   else
    		   {
    			   throw new IOException("Field:"+fields + " Has bigger value. Its set "+fieldMaxLen +" in ISOSCHEMA and you have entered" + fieldLength );
    		   }
    	   }
    	   else if (dataType.equalsIgnoreCase("FCHAR"))
    	   {
    		   if(Integer.parseInt(fieldMaxLen) >= fieldLength) //?????????? fieldLength OR fieldMaxLen -- Discuss with Sagar
    		   {
    			   
    			   String newFieldValue = String.format("%-"+ fieldMaxLen +"s", fieldVlaue);
    			   
    			   /////
	    		   ISOMESSAGE.put(fields.toString(),  newFieldValue);
	    		   ////
    		   }
    		   else
    		   {
    			   throw new IOException("Field:"+fields + " Has bigger value. Its set "+fieldMaxLen +" in ISOSCHEMA and you have entered" + fieldLength );
    		   }
    	   }
    	   else if (dataType.equalsIgnoreCase("PCHAR"))
    	   {
    		   if(Integer.parseInt(fieldMaxLen) >= fieldLength) //?????????? fieldLength OR fieldMaxLen -- Discuss with Sagar
    		   {
    			   String newFieldValue = String.format("%"+ fieldMaxLen +"s", fieldVlaue);
    			   newFieldValue = newFieldValue.replaceAll(" ", "0");
    			   /////
	    		   ISOMESSAGE.put(fields.toString(),  newFieldValue);
	    		   ////
    		   }
    		   else
    		   {
    			   throw new IOException("Field:"+fields + " Has bigger value. Its set "+fieldMaxLen +" in ISOSCHEMA and you have entered" + fieldLength );
    		   }
    	   }
    	   else if (dataType.equalsIgnoreCase("BINARY"))
    	   {
    		   if(Integer.parseInt(fieldMaxLen) >= fieldLength) //?????????? fieldLength OR fieldMaxLen -- Discuss with Sagar
    		   {
    				int bytes = (fieldLength + 1) / 2; // The +1 is so it rounds up
    				byte b;
    				StringBuffer sb = new StringBuffer();
    				String fPa;
    				if (fieldLength % 2 == 0) // Even number of chars, so there's no padding at the end
    				{
    				  for (int iRe = 0; iRe < fieldLength; iRe += 2)
    				  {    		
    					  fPa = fieldVlaue.substring(iRe, iRe + 2);
    					  sb.append(CtoX(fPa));
    				  }
    				}
    				else // Odd number
    				{
    				  int iRe;

    				  fPa = "";
    				  fPa = fPa + fieldVlaue.charAt(0);

    				  sb.append(CtoX(fPa)); // Get the first char from the second nibble
    				  for (iRe = 1; iRe < fieldLength; iRe += 2)
    				  {
    					  fPa = fieldVlaue.substring(iRe, iRe + 2);
    					  sb.append(CtoX(fPa));
    				  }
    				}
    			   
    			   /////
	    		   ISOMESSAGE.put(fields.toString(),  sb.substring(0, sb.length()));
	    		   ////
    		   }
    		   else
    		   {
    			   throw new IOException("Field:"+fields + " Has bigger value. Its set "+fieldMaxLen +" in ISOSCHEMA and you have entered" + fieldLength );
    		   }
    	   }

    	   
	  }
	    
	  //Process Bitmap - Add 1 and 65 number Data Elements
	  ArrayList<Integer> finalFields = new ArrayList<Integer>();	
	  finalFields = processBitmap(parseFields(keys)); 
	  
	  //Process Subfield bitmap - Add 1 and 65 for each field that has subfields
	   processSubFieldBitmap(subFiledBitMapHolder);
	    
	   return buildISOMessage(finalFields);
	   
	}
	
	public static String buildISOMessage(ArrayList<Integer> finalFields) throws Exception
	{
		
		//System.out.println(finalFields);
		//System.out.println(ISOMESSAGE);
		//System.out.println(SUBFIELDSMAPPING);
		String isoMessage = ISOMESSAGE.get("MTI");
		isoMessage = isoMessage + ISOMESSAGE.get("1");

		
		Iterator<?> j = finalFields.iterator();
		while(j.hasNext())
		{
			   String dataElement = j.next().toString();	
			   String schema = ISOSCHEMA.get(dataElement);

			   try{
				   String arrSchema[] = schema.split("-");
				   
			       String fieldLenType = arrSchema[1];
				   
				   String subfieldIndicator = arrSchema[3];
				   String arrSubField[] = subfieldIndicator.split("_");
			       String hasSubfield = arrSubField[0];
		       		  
			       //System.out.println(dataElement + "-"  + hasSubfield + "-" + hasSubfieldBitmap);
			       
			       if(hasSubfield.equalsIgnoreCase("1"))
			       {
			    	   //Traverse subfields
			    	  ArrayList <Integer> subFields = new ArrayList<Integer>();
			    	  subFields = SUBFIELDSMAPPING.get(Integer.parseInt(dataElement));
			    	  Iterator<?> i = subFields.iterator();
			    	  String isoSubMessage = "";
			    	  if(!subFields.contains(1)){
			    		  isoSubMessage = isoSubMessage + ISOMESSAGE.get(dataElement + ".1");
			    		  
			    	  }
			    	  

			 		  while(i.hasNext())
			 		  {
			 			  String subDataElement = i.next().toString();
			 			  String mainDataElement = dataElement + "." + subDataElement;
			 			  if(!subDataElement.equals("65")){
				 			  
				 			  isoSubMessage = isoSubMessage + ISOMESSAGE.get(mainDataElement);
			 			  }
			 		  }
			 		  
			 		
		    		  String subMessageLen = String.format("%0"+ fieldLenType +"d", isoSubMessage.length());
			 		 
			 		 //System.out.println(subMessageLen);
			 		 //System.out.println(isoSubMessage);
			 		  isoMessage = 	isoMessage + subMessageLen + isoSubMessage; 
			 		  //vikratn is here --> Message length should be in perfect leng as per the schema
			       }
			       else
			       {			    	   
			    	   isoMessage = isoMessage + ISOMESSAGE.get(dataElement);
			       }
			       
			   }catch(Exception e)
			   {
				   //System.out.println(" has problem with schema");
			   }
		}
		
		//System.out.println(isoMessage);
		//System.out.println("isoMsg: " + isoMsg);
		
		return isoMessage;
	}
	
	//Create array list of all mail Data Elements that are available.
	public static ArrayList<Integer> parseFields(ArrayList<String> fields) throws Exception
	{
		
		ArrayList<Integer> newFiledMap = new ArrayList<Integer>();
		Iterator<?> j = fields.iterator();
		  while(j.hasNext())
		  {
			  try{
				  String breakFieldForSubField[] = j.next().toString().split("[.]");
			      Integer baseField = Integer.parseInt(breakFieldForSubField[0].toString());
			      
			      if(!newFiledMap.contains(baseField))
			      {
			    	  newFiledMap.add(baseField);
			      }
			  }catch(Exception e)
			  {
				  Integer keys = Integer.parseInt(j.next().toString());
				  newFiledMap.add(keys);
				  //System.out.println(e + "these are subfields");
			  }
		  }
		
		//Sort fields in assending order
		Collections.sort(newFiledMap);    
		return newFiledMap;
	}
	
	public static ArrayList<Integer> processBitmap(ArrayList<Integer> fields) throws Exception
	{
		String bitmapType = "primary";
		char[] bitMap;
		
		//Retrive heighest Data Element from the list
		Integer DE = fields.get(fields.size() - 1);
		
		//Know the type of bitmap (primary, secondary, tertiary
		if(DE > 65 && DE <= 128)
		{
			fields.add(1); //Add 1st Bit for secondary bitmap
			bitmapType = "secondary";
			bitMap = new char[16];
		}
		else if(DE > 128)
		{
			fields.add(1); //Add 1st Bit for secondary bitmap
			fields.add(65); //Add 65th Bit for tertiary bitmap
			ISOMESSAGE.put("65", "1");
			bitmapType = "tertiary";
			bitMap = new char[24];
			
		}
		else
		{
			//fields.add(1); //Add 1st Bit for secondary bitmap
			bitmapType = "primary";
			bitMap = new char[8];

		}
		
		//Sort ArrayList again 
		Collections.sort(fields);

		
		//Append BITMAP to the message
		CalcBitMap(bitMap, fields);
		String sBitM = CalcBitMap(fields, bitmapType);
		//ISOMESSAGE.put("1", String.valueOf(bitMap, 0, bitMap.length));
		ISOMESSAGE.put("1", sBitM);
	
		return fields;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void processSubFieldBitmap(HashMap<String, ArrayList<Integer>> subFieldMap) throws Exception
	{
		Set<?> set = subFieldMap.entrySet();
		Iterator<?> i = set.iterator(); 
		char[] bitMap;
		while(i.hasNext()) {
			 
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry)i.next();
				
			ArrayList <Integer> fields = new ArrayList<Integer>(); 
			fields = (ArrayList<Integer>) me.getValue();
			String subFieldId = me.getKey().toString();
			//System.out.println(fields);
			
			Collections.sort(fields); //Sort the arrayList
		
			String bitmapType = "primary";
			
			//Retrive heighest Data Element from the list
			Integer DE = fields.get(fields.size() - 1);
			//System.out.println(DE);
			//Know the type of bitmap (primary, secondary, tertiary
			if(DE > 65 && DE <= 128)
			{
				fields.add(1); //Add 1st Bit for secondary bitmap
				bitmapType = "secondary";
				bitMap = new char[16];
			}
			else if(DE > 128)
			{
				fields.add(1); //Add 1st Bit for secondary bitmap
				fields.add(65); //Add 65th Bit for tertiary bitmap
				ISOMESSAGE.put(subFieldId + ".65", "1");
				bitmapType = "tertiary";
				bitMap = new char[24];
			}
			else
			{
				//fields.add(1); //Add 1st Bit for secondary bitmap
				bitmapType = "primary";
				bitMap = new char[8];
			}
			//Sort ArrayList again 
			Collections.sort(fields);
			
			//System.out.println(fields);
			
			//Maintain Subfield Mapping
			SUBFIELDSMAPPING.put(Integer.parseInt(subFieldId), fields);
			
			//System.out.println(fields + " " + bitmapType);
			
			//Append BITMAP to the message
		 	//ISOMESSAGE.put(subFieldId+".1", CalcBitMap(fields, bitmapType));
			CalcBitMap(bitMap, fields);

			//String sBitM = CalcBitMap(fields, bitmapType);
			ISOMESSAGE.put(subFieldId+".1", String.valueOf(bitMap, 0, bitMap.length));		 	
		}
	}
	
	
	
	public static void unpackIsoMsg(String isoMessage) throws Exception
	{
		 //System.out.println(isoMessage);
		 String overallBitmap = null;
		 
		 String messageAfterBitMap = null;
		 
		 if(ISSUBFIELDPARSING == false)
			 PARSEDISOMESSAGE.put("MTI",isoMessage.substring(0, 4));
		 
		 String priMaryHexBitMap = "";
		 if(ISSUBFIELDPARSING == false)
			 priMaryHexBitMap = isoMessage.substring(4, 12);
		 else
			 priMaryHexBitMap = isoMessage.substring(0, 8);
		 
		 //Convert BITMAP to Binary
		 String primaryBitMap = GetBitMap(priMaryHexBitMap ,1);
		 
		 overallBitmap = primaryBitMap;
		 //Check if Secondary bitMap is available or not
		 Integer firstBit = Integer.parseInt(primaryBitMap.substring(0, 1));
		 
		 //if firstBit = 1 it means secondary bitmap is available
		 String secondaryHexBitmap = null;
		 String secondaryBitMap = null;
		 String tertiaryHexBitmap = null;
		 String tertiaryBitMap = null;
		 int bitmaplength = 64; 
		 
		 if(firstBit > 0)
		 {
			bitmaplength = 128;
			if(ISSUBFIELDPARSING == false)
				secondaryHexBitmap = isoMessage.substring(12, 20);
			else
				secondaryHexBitmap = isoMessage.substring(8, 16);
			
			secondaryBitMap = GetBitMap(secondaryHexBitmap, 1);
			overallBitmap = overallBitmap + secondaryBitMap;
			//if 65th field is binary then there is a tertiary bitmap
			Integer firstBitOfSecBitmap = Integer.parseInt(secondaryBitMap.substring(0, 1));
			if(firstBitOfSecBitmap > 0)
			{
				 bitmaplength = 192;
				 if(ISSUBFIELDPARSING == false)
					 tertiaryHexBitmap = isoMessage.substring(20, 28);
				 else
					 tertiaryHexBitmap = isoMessage.substring(16, 24);
				 
				 tertiaryBitMap = GetBitMap(tertiaryHexBitmap, 1);
				 overallBitmap = overallBitmap + tertiaryBitMap;
				 if(ISSUBFIELDPARSING == false)
					 messageAfterBitMap = isoMessage.substring(28); //After MTI and Primary bitmap
				 else
					 messageAfterBitMap = isoMessage.substring(24); //After MTI and Primary bitmap
			}
			else
			{
				if(ISSUBFIELDPARSING == false)
					messageAfterBitMap = isoMessage.substring(20); //After MTI and Primary bitmap
				else
					messageAfterBitMap = isoMessage.substring(16); //After MTI and Primary bitmap
			}
		 }
		 else //Secondary bitmap is not available so remaining message is actual data
		 {
			 if(ISSUBFIELDPARSING == false)
				 messageAfterBitMap = isoMessage.substring(12); //After MTI and Primary bitmap
			 else
				 messageAfterBitMap = isoMessage.substring(8); //After MTI and Primary bitmap
		 }
		 //System.out.println("overallBitmap="+overallBitmap);
		 
		 //Traverse the overall bitmap string
		 ArrayList<Integer> debugList = new ArrayList<Integer>(); //This is just for debugging purpose
		 
		 String remainingMessage = null;
		 for(int i=0;i<bitmaplength;i++)
		 {
			 //now figure out which fields are available
			 char bit =  overallBitmap.charAt(i);
			 if(bit == '1')
			 {
				 debugList.add(i+1); //This is just for debugging purpose.
				 Integer field = i+1;
				 
				 String dataType = null;
				 String fieldLenType = null;
				 String fieldMaxLen = null;
				 String subfieldIndicator = null;
				 String hasSubfield = null;
				 String hasSubfieldBitmap = null;
				 if(field > 1) //Exclude 1st Field which is reserve for bitmap
			     {
					   String schema = null;
					   try{
						   schema = ISOSCHEMA.get(SUBFIELDID+field.toString());
					   }catch(Exception e)
					   {
						   throw new IOException(field + " has problem with schema.");
					   }
					   
					   try{
					       String arrSchema[] = schema.split("-");
					       
					       dataType = arrSchema[0];
					       fieldLenType = arrSchema[1];
					       fieldMaxLen = arrSchema[2];
					       subfieldIndicator = arrSchema[3];
					       String arrSubField[] = subfieldIndicator.split("_");
					       hasSubfield = arrSubField[0];
					       hasSubfieldBitmap = arrSubField[1];
					   }catch(Exception e)
					   {
						   throw new IOException(field + " has problem with schema.");
					   }
				      
				       //chala todat todat javoo
				       if(dataType.equalsIgnoreCase("NUMERIC"))
			    	   {
				    	   if(remainingMessage == null)
				    	   {
				    		   String fieldValue = messageAfterBitMap.substring(0,Integer.parseInt(fieldMaxLen));
				    		   
				    		   if(fieldValue == null)
				    		   {
				    			   throw new IOException(field + " Has null or inappropriate value");
				    		   }
				    		   
				    		   if(hasSubfield.equalsIgnoreCase("1"))
						       {
						    	   ISSUBFIELDPARSING = true;
						    	   SUBFIELDID = field+".";
						    	   unpackIsoMsg(fieldValue);
						       }
						       else
						       {
						    	    PARSEDISOMESSAGE.put(SUBFIELDID+field.toString(), fieldValue); //Lets start pushing parsed fields into Hash Map
						       }
				    		   remainingMessage = messageAfterBitMap.substring(Integer.parseInt(fieldMaxLen));
				    	   }
				    	   else //operation on remainingMessage
				    	   {
				    		   String fieldValue = remainingMessage.substring(0,Integer.parseInt(fieldMaxLen));
				    		   
				    		   if(fieldValue == null)
				    		   {
				    			   throw new IOException(field + " Has null or inappropriate value");
				    		   }
				    		   
				    		   if(hasSubfield.equalsIgnoreCase("1"))
						       {
						    	   ISSUBFIELDPARSING = true;
						    	   SUBFIELDID = field+".";
						    	   unpackIsoMsg(fieldValue);
						       }
						       else
						       {
						    	    PARSEDISOMESSAGE.put(SUBFIELDID+field.toString(), fieldValue); //Lets start pushing parsed fields into Hash Map
						       }
				    		   remainingMessage = remainingMessage.substring(Integer.parseInt(fieldMaxLen));
				    	   }
			    	   }
				       else if(dataType.equalsIgnoreCase("FCHAR"))
			    	   {
				    	   if(remainingMessage == null)
				    	   {
				    		   String fieldValue = messageAfterBitMap.substring(0,Integer.parseInt(fieldMaxLen)).trim();
				    		   
				    		   if(fieldValue == null)
				    		   {
				    			   throw new IOException(field + " Has null or inappropriate value");
				    		   }
				    		   
				    		   if(hasSubfield.equalsIgnoreCase("1"))
						       {
						    	   ISSUBFIELDPARSING = true;
						    	   SUBFIELDID = field+".";
						    	   unpackIsoMsg(fieldValue);
						       }
						       else
						       {
						    	    PARSEDISOMESSAGE.put(SUBFIELDID+field.toString(), fieldValue); //Lets start pushing parsed fields into Hash Map
						       }
				    		   remainingMessage = messageAfterBitMap.substring(Integer.parseInt(fieldMaxLen));
				    	   }
				    	   else //operation on remainingMessage
				    	   {
				    		   String fieldValue = remainingMessage.substring(0,Integer.parseInt(fieldMaxLen)).trim();
				    		   
				    		   if(fieldValue == null)
				    		   {
				    			   throw new IOException(field + " Has null or inappropriate value");
				    		   }
				    		   
				    		   if(hasSubfield.equalsIgnoreCase("1"))
						       {
						    	   ISSUBFIELDPARSING = true;
						    	   SUBFIELDID = field+".";
						    	   unpackIsoMsg(fieldValue);
						       }
						       else
						       {
						    	    PARSEDISOMESSAGE.put(SUBFIELDID+field.toString(), fieldValue); //Lets start pushing parsed fields into Hash Map
						       }
				    		   remainingMessage = remainingMessage.substring(Integer.parseInt(fieldMaxLen));
				    	   }
			    	   }
				       else if(dataType.equalsIgnoreCase("PCHAR"))
			    	   {
				    	   if(remainingMessage == null)
				    	   {
				    		   String fieldValue = messageAfterBitMap.substring(0,Integer.parseInt(fieldMaxLen)).replaceFirst("^0+(?!$)", "");
				    		   
				    		   if(fieldValue == null)
				    		   {
				    			   throw new IOException(field + " Has null or inappropriate value");
				    		   }
				    		   
				    		   if(hasSubfield.equalsIgnoreCase("1"))
						       {
						    	   ISSUBFIELDPARSING = true;
						    	   SUBFIELDID = field+".";
						    	   unpackIsoMsg(fieldValue);
						       }
						       else
						       {
						    	    PARSEDISOMESSAGE.put(SUBFIELDID+field.toString(), fieldValue); //Lets start pushing parsed fields into Hash Map
						       }
				    		   remainingMessage = messageAfterBitMap.substring(Integer.parseInt(fieldMaxLen));
				    	   }
				    	   else //operation on remainingMessage
				    	   {
				    		   String fieldValue = remainingMessage.substring(0,Integer.parseInt(fieldMaxLen)).replaceFirst("^0+(?!$)", "");
				    		   
				    		   if(fieldValue == null)
				    		   {
				    			   throw new IOException(field + " Has null or inappropriate value");
				    		   }
				    		   
				    		   if(hasSubfield.equalsIgnoreCase("1"))
						       {
						    	   ISSUBFIELDPARSING = true;
						    	   SUBFIELDID = field+".";
						    	   unpackIsoMsg(fieldValue);
						       }
						       else
						       {
						    	    PARSEDISOMESSAGE.put(SUBFIELDID+field.toString(), fieldValue); //Lets start pushing parsed fields into Hash Map
						       }
				    		   remainingMessage = remainingMessage.substring(Integer.parseInt(fieldMaxLen));
				    	   }
			    	   }
				       else if(dataType.equalsIgnoreCase("CHAR") || dataType.equalsIgnoreCase("NUM"))
			    	   {
			    		   if(remainingMessage == null)
				    	   {
			    			   String fieldlength = messageAfterBitMap.substring(0,Integer.parseInt(fieldLenType));
			    			   remainingMessage = messageAfterBitMap.substring(Integer.parseInt(fieldLenType));
			    			   
			    			   String fieldValue = remainingMessage.substring(0,Integer.parseInt(fieldlength));
			    			   if(fieldValue == null)
				    		   {
				    			   throw new IOException(field + " Has null or inappropriate value");
				    		   }
			    			   
			    			   if(hasSubfield.equalsIgnoreCase("1"))
						       {
						    	   ISSUBFIELDPARSING = true;
						    	   SUBFIELDID = field+".";
						    	   unpackIsoMsg(fieldValue);
						       }
						       else
						       {
						    	    PARSEDISOMESSAGE.put(SUBFIELDID+field.toString(), fieldValue); //Lets start pushing parsed fields into Hash Map
						       }
						    	
			    			   remainingMessage = remainingMessage.substring(Integer.parseInt(fieldlength));
			    			   
				    	   }
			    		   else //operation on remaining message
			    		   {
			    			   String fieldlength = remainingMessage.substring(0,Integer.parseInt(fieldLenType));
			    		
			    			   remainingMessage = remainingMessage.substring(Integer.parseInt(fieldLenType));
			    			   
			    			   String fieldValue = remainingMessage.substring(0,Integer.parseInt(fieldlength));
			    			   if(fieldValue == null)
				    		   {
				    			   throw new IOException(field + " Has null or inappropriate value");
				    		   }
			    			   
			    			   if(hasSubfield.equalsIgnoreCase("1"))
						       {
						    	   ISSUBFIELDPARSING = true;
						    	   SUBFIELDID = field+".";
						    	   unpackIsoMsg(fieldValue);
						       }
						       else
						       {
						    	    PARSEDISOMESSAGE.put(SUBFIELDID+field.toString(), fieldValue); //Lets start pushing parsed fields into Hash Map
						       }
			    			   
			    			   remainingMessage = remainingMessage.substring(Integer.parseInt(fieldlength));
			    		   }
			    	   }
			       }
			 }
		 }
		 
		 ISSUBFIELDPARSING = false;
	     SUBFIELDID = "";
		 
		 //System.out.println("Parsed Message " + PARSEDISOMESSAGE); 
	}
	
	
	/**
	 * Method For Calculating the bitmap for fields
	 * @input= Arraylist for the subfields present 
	 * @return String containing bitmap msg
	 * @throws Exception 
	 * 
	 */
	public static String CalcBitMap(char[] bitMap, ArrayList<Integer> list) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{		
			
			int iPos = (list.get(i) / 8);
			int iPosIn = list.get(i) - (iPos * 8);
			if(iPosIn == 0){
				iPos--;
				iPosIn = 8;
			}	
			if((65 <= list.get(i))  && (list.get(i) < 128)){
				bitMap[ 0 ] |= 1 << 7;
				bitMap[ iPos ] |= 1 << (8 - iPosIn);
			} else if((128 <= list.get(i))  && (list.get(i) < 192)){
				bitMap[ 8 ] |= 1 << 7;
				bitMap[ iPos  ] |= 1 << (8 - iPosIn);

			} else if((0 <= list.get(i))  && (list.get(i) <= 64)){
				bitMap[ iPos ] |= 1 << (8 - iPosIn);
			}
				
		

		}
		
		return "OK";
	}
	public static String CalcBitMap(ArrayList<Integer> list, String type) throws Exception
	{
		String strBitmap = "0000000000000000000000000000000000000000000000000000000000000000";
		String bitmap = null;
		if(type.equalsIgnoreCase("primary"))
			 bitmap=strBitmap;
		else if (type.equalsIgnoreCase("secondary"))
			 bitmap=strBitmap + strBitmap;
		else if((type.equalsIgnoreCase("tertiary")))
			bitmap=strBitmap + strBitmap + strBitmap;
		else
			bitmap=strBitmap;
		
		for(int i=0;i<list.size();i++)
		{
			int pos=0;
			char a = '1'; 
			pos=list.get(i);
			//System.out.println(pos);
			bitmap=replaceCharAt(bitmap, pos-1, a);
		}
		
		String fullHex="";
		String hexString = "";
		for(int y=0; y<bitmap.length();y=y+4)
		{
			 String toHex = bitmap.substring(y,y+4);
			 int i= Integer.parseInt(toHex,2);
			 hexString = Integer.toHexString(i);
			 fullHex = fullHex+hexString;
		}
		
		//return fullHex;
		
		String fullHexAscii="";
		
		byte[] arrBytes = new byte[(fullHex.length()/2)];
		
	    for (int i = 0, j=0; i < fullHex.length(); i+=2, j++) {
	        String str = fullHex.substring(i, i+2);

	        arrBytes[j] = (byte)Integer.parseInt(str, 16);
	    	
	        fullHexAscii = fullHexAscii + (char)Integer.parseInt(str, 16);
	    }
	    
		return fullHexAscii;
	 }
	
	public static String GetBitMap(String bin, int nouse) throws Exception  
	{  
		int iCo = 0;
		StringBuffer binRes = new StringBuffer(64);
		
		for(iCo = 0; iCo < 8; iCo++){
			if((bin.charAt(iCo) & 128) > 0) binRes.append('1'); else binRes.append('0');
			if((bin.charAt(iCo) & 64) > 0) binRes.append('1'); else binRes.append('0');
			if((bin.charAt(iCo) & 32) > 0) binRes.append('1'); else binRes.append('0');
			if((bin.charAt(iCo) & 16) > 0) binRes.append('1'); else binRes.append('0');
			if((bin.charAt(iCo) & 8) > 0) binRes.append('1'); else binRes.append('0');
			if((bin.charAt(iCo) & 4) > 0) binRes.append('1'); else binRes.append('0');
			if((bin.charAt(iCo) & 2) > 0) binRes.append('1'); else binRes.append('0');
			if((bin.charAt(iCo) & 1) > 0) binRes.append('1'); else binRes.append('0');
		}

		return binRes.toString();
	}
	
	public static char CtoX(String x)
	{
		int r = 0;
		r = Integer.parseInt(x,16);
		System.out.println("Char is:=" + r);
		return (char)r;
	}
public static String GetBitMap(String bin) throws Exception  
{  
	String[]hex={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};  
	String[]binary={"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};  
	  
	String originalMsg="";
    for (int x = 0; x < bin.length(); x++) {
    	int value = (int)bin.charAt(x);
    	String originalValue = Integer.toHexString(value);

    	if (originalValue.length() < 2)
    		originalMsg = originalMsg + "0";
    	
    	if (originalValue.length() > 2)
    		originalValue = originalValue.replace("ff", "");
    	
    	originalMsg = originalMsg + originalValue;
    }
    
    System.out.println("Output Bimap: "+originalMsg);
    
	String userInput= originalMsg;  
	String result="";  
	for(int i=0;i<userInput.length();i++)  
	{  
		char temp=userInput.charAt(i);  
		String temp2=""+temp+"";  
		for(int j=0;j<hex.length;j++)  
		{  
			if(temp2.equalsIgnoreCase(hex[j]))  
			{  
				result=result+binary[j];  
			}  
		}  
	}  
	return result; 
}  

	
	
	  /**
	   * Method for Replacing the char in string at provided location
	   * @input pos-Int position where to replace character
	   *        char-char to be replace at provided pos
	   *        String- s which is to be formated
	   * 
	   * @return Re-formated String       
	   * 
	   * */
	public static String replaceCharAt(String s, int pos, char c) throws Exception
	{
		   return s.substring(0,pos) + c + s.substring(pos+1);
	}
	


	   // recv a short ack from the server so we know they are ready for more
	
}
