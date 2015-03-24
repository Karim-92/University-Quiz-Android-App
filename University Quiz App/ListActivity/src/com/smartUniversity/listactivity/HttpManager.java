package com.smartUniversity.listactivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import android.net.http.AndroidHttpClient;

public class HttpManager {

	public static String getData(String uri){

		AndroidHttpClient client=  AndroidHttpClient.newInstance("AndroidAgent");
		HttpPost request=new HttpPost(uri);
		
		HttpResponse response;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			return rd.readLine();

		} catch (Exception e) {
			e.printStackTrace();
			return null;	
		} finally {
			client.close();
		}
	}

}
