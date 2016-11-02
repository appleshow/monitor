package com.aps.monitor.comm;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class TT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(System.currentTimeMillis());
		System.out.println(Integer.MAX_VALUE);
		System.out.println(MD5Util.getMD5String(MD5Util.getMD5String("admin") + "2196"));
		String s = "{\"parcnt\":2,\"inpar\":{\"codeType\":\"C\",\"codeName\":\"1\"}}";
		
		System.out.println(s);
		JsonFactory jsonFactory = new JsonFactory();
		try {
			JsonParser jsonParse = jsonFactory.createParser(s);

			System.out.println(jsonParse.toString());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
