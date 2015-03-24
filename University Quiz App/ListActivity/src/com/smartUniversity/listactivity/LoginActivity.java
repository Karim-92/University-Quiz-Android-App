package com.smartUniversity.listactivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartUniversity.sessionManagment.AlertDialogManager;
import com.smartUniversity.sessionManagment.SessionManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	// Creating HTTP Post

	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
	// Email, password edittext
	EditText txtUsername, txtPassword;
	String user;
	private static String KEY_SUCCESS = "success";
	private static String KEY_COOKIE="session";
	

	// login button
	Button btnLogin;
	
	//register button
	Button btnReg;
	
	// Alert Dialog Manager
	AlertDialogManager alert = new AlertDialogManager();
	String url="http://service.byethost11.com/service/login";

	// Session Manager Class
	SessionManager session;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		txtUsername = (EditText) findViewById(R.id.textUsername);
		txtPassword = (EditText) findViewById(R.id.textPassword);

		// Login button
		btnLogin = (Button) findViewById(R.id.loginButton);
		
		//Registeration Button
		btnReg=(Button) findViewById(R.id.registerButton);


		// Login button click event
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Get username, password from EditText
				String username = txtUsername.getText().toString();
				String password = txtPassword.getText().toString();

				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.loginUser(username, password);
				 try {
					if (json.getString(KEY_SUCCESS) != null) {
					     String res = json.getString(KEY_SUCCESS);
					     if(Integer.parseInt(res) != 0){
					    	 session.createLoginSession(json.getString(KEY_COOKIE), username);
					    	 Intent courseList=new Intent(getApplicationContext(), ListViewActivity.class);
					    	 startActivity(courseList);
					     }
					     if(Integer.parseInt(res)==0){
					    	 Toast.makeText(getApplicationContext(), "Wrong Login Credentials", Toast.LENGTH_LONG).show();
					     }
					     

					     }
					else
						Toast.makeText(getApplicationContext(), "Unable to contact the server, please try again later.", Toast.LENGTH_LONG).show();
				} catch (NumberFormatException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnReg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent reg= new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(reg);
			}
		});


	}
	

}