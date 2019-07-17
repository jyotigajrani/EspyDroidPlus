/**
 * @testcase_name ComplexReflection
 * 
 * @description Leak through Reflection and Obfuscation
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Late Binding of Reflection Targets, Inter-Component Communication, Conditional Constraint, Complex GUI
 */

package com.example.sixactivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Instructions extends ActionBarActivity {
	String meth = Base64.encodeToString("write_sf".getBytes(), Base64.DEFAULT);
	String eclz = Base64.encodeToString("com.example.sixactivity.Write".getBytes(), Base64.DEFAULT);

	Button homeB,playB,myserviceB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instructions);
		Intent in = this.getIntent();
		String SF = in.getStringExtra("sharedp");
		homeB = (Button) findViewById(R.id.button_ins);
		playB=(Button) findViewById(R.id.button_play);
		myserviceB=(Button) findViewById(R.id.button_myservice);
		
		String clz = new String(Base64.decode(eclz,Base64.DEFAULT));
		String methode = new String(Base64.decode(meth,Base64.DEFAULT));
//		Class.forName(clz).getMethod(methode).invoke();
		Toast.makeText(Instructions.this, "INS", Toast.LENGTH_LONG).show();
		
		try {
			Object[] obj = { this, SF};
			Class<?> c = Class.forName(clz);
			Object o = c.newInstance();
			Class<?> params[] = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] instanceof String) {
					params[i] = String.class;
				} else if (obj[i] instanceof SharedPreferences) {
					params[i] = SharedPreferences.class;
				}
			}
			//Constructor<?> constt = clazz.getConstructor(Context.class);
			Method methodezz = o.getClass().getMethod(methode,params);
			methodezz.invoke(o,obj);
			//startService(new Intent(this, MyServices.class));
			/*
			Class<?> clazz = Class.forName(clz);
			Constructor<?> constt = clazz.getConstructor(Context.class);
			Method methodezz = clazz.getClass().getMethod(meth);
			Object clazzObj = constt.newInstance(Instructions.this);
			methodezz.invoke(clazzObj);*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		homeB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Instructions.this,HomeActivity.class);
				i.putExtra("eclz", "com.example.sixactivity.Read");
				startActivity(i);
			}
		});
		
		playB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Instructions.this,Play.class);
				startActivity(i);
			}
		});
		
		myserviceB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Instructions.this,MyServices.class);
				startService(i);
			}
		});
				
//		Write write = new Write(getApplication());
//		write.write_sf();
		
		
		
		
	}

}
