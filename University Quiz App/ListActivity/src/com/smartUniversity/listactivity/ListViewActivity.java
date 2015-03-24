package com.smartUniversity.listactivity;

import java.util.ArrayList;
import java.util.List;

import com.smartUniversity.listactivity.model.Courses;
import com.smartUniversity.listactivity.parsers.CourseJSONParser;
import com.smartUniversity.sessionManagment.*;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


@SuppressWarnings("unused")
public class ListViewActivity extends ListActivity {
	List <Courses> courseList;
	List <MyTask> tasks;
	SessionManager session;
	private String getAllCourses = "http://service.byethost11.com/service/courses";
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		tasks = new ArrayList<>();
		session = new SessionManager(getApplicationContext());

	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_get_data) {
			if (isOnline()) {
				
				session.checkLogin();
				
				requestData(getAllCourses);
			} else {
				Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
			}
		}
		return false;
	}
	
	public void requestData(String uri){
		MyTask task= new MyTask();
		task.execute(uri);
	}
	
	protected boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
	}


	protected void updateDisplay(){
		CourseAdapter adapter = new CourseAdapter(this, R.layout.item_course, courseList);
		setListAdapter(adapter);
	}
	
	
	private class MyTask extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute(){
			
			
		}
		
		@Override
		protected String doInBackground(String... params) {
			String content = HttpManager.getData(params[0]);
			return content;
			
		}
		
		@Override
		protected void onPostExecute(String result){
			
			if (result==null){
				Toast.makeText(ListViewActivity.this, "Can't connect to the web service", Toast.LENGTH_LONG).show();
				return;
			}
			courseList = CourseJSONParser.parseFeed(result);
			updateDisplay();
			
		}
	}
}
