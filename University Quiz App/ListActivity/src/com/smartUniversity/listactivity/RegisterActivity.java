package com.smartUniversity.listactivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends ActionBarActivity {
	
	Button submitButton;
	EditText edUser, edPass, edEmail;
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    TextView registerErrorMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		submitButton=(Button) findViewById(R.id.submitButton1);
		edUser=(EditText) findViewById(R.id.editText1);
		edPass=(EditText) findViewById(R.id.editText3);
		edEmail=(EditText) findViewById(R.id.editText2);
		registerErrorMsg=(TextView) findViewById(R.id.textView4);
        registerErrorMsg.setText("");

		
	       submitButton.setOnClickListener(new View.OnClickListener() {        
	            public void onClick(View view) {
	                String name = edUser.getText().toString();
	                String email = edEmail.getText().toString();
	                String password = edPass.getText().toString();
	                UserFunctions userFunction = new UserFunctions();
	                JSONObject json = userFunction.registerUser(name, email, password);
	                 
	                // check for login response
	                try {
	                    if (json.getString(KEY_SUCCESS) != null) {
	                        registerErrorMsg.setText("");
	                        String res = json.getString(KEY_SUCCESS);
	                        if(Integer.parseInt(res) == 1){
	                            // user successfully registred
	                        	registerErrorMsg.setText("User Successfully Registered.");
	                            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
	                            startActivity(login);
	                            finish();
	                        }else{
	                            // Error in registration
	                            registerErrorMsg.setText("Error occured in registration");
	                        }
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	            }
	        });
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
