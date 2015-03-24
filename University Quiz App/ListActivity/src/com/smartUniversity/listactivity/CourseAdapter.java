package com.smartUniversity.listactivity;

import java.util.List;

import com.smartUniversity.listactivity.model.Courses;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CourseAdapter extends ArrayAdapter<Courses>{
	
	private Context context;
	private List<Courses> courseList;

	public CourseAdapter(Context context, int resource, List<Courses> objects) {
		super(context, resource, objects);
		this.context = context;
		this.courseList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item_course, parent, false);
		Courses course =courseList.get(position);
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText(course.getName());
		return view;
	}

}

	


