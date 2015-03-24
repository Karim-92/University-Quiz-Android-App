package com.smartUniversity.listactivity.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartUniversity.listactivity.model.Courses;

public class CourseJSONParser {
	
	

	public static List<Courses> parseFeed(String content) {
	
		try {
			JSONArray ar = new JSONArray(content);
			List<Courses> courseList = new ArrayList<>();
			
			for (int i = 0; i < ar.length(); i++) {
				
				JSONObject obj = ar.getJSONObject(i);
				Courses course = new Courses();

				course.setName(obj.getString("name"));
				course.setSubscribeState(obj.getBoolean("Subscribed"));
				course.setId(obj.getString("id"));
				

				courseList.add(course);
			}
			
			return courseList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}