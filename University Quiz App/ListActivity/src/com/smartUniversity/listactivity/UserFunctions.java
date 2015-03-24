package com.smartUniversity.listactivity;

import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.smartUniversity.listactivity.parsers.JSONParser;
 
public class UserFunctions {
     
    private JSONParser jsonParser;
     

    private static String loginURL = "http://service.byethost11.com/service/login";
    private static String registerURL="http://service.byethehost11.com/service/register";
    

    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
   
    public JSONObject loginUser(String email, String password){
        /*// Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));*/
    	
        JSONObject json = jsonParser.getJSONFromUrl(loginURL+"?"+"email="+email+"&"+"password="+password);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }
    
    public  JSONObject registerUser(String name, String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
         
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL+"?"+"username="+name+"&"+"email="+email+"&"+"password="+password);
        // return json
        return json;
    }
    
    
}
     