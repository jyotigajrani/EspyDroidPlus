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
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity {
	public static final String SF = "MyPrefsFile";
	
	String em = Base64.encodeToString("read_sf".getBytes(), Base64.DEFAULT);
	Button insB, insplay, insscoreboard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		insB = (Button) findViewById(R.id.button_home);
		insplay = (Button) findViewById(R.id.button_play);
		insscoreboard = (Button) findViewById(R.id.button_scoreboard);
	
		insB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in=new Intent(HomeActivity.this, Instructions.class);
				in.putExtra("sharedp", SF);
				startActivity(in);
				
			}
		});
		insplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(HomeActivity.this, Play.class));
				
			}
		});

	insscoreboard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(HomeActivity.this, ScoreBoard.class));
				
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent in = this.getIntent();
		String clzS = in.getStringExtra("clz");


//		String clz = new String(Base64.decode(eclz,Base64.DEFAULT));
		String methode = new String(Base64.decode(em,Base64.DEFAULT));
//		Class.forName(clz).getMethod(methode).invoke();
		Toast.makeText(HomeActivity.this, "INS", Toast.LENGTH_LONG).show();
		
		try {
			Object[] obj = { this};
			Class<?> c = Class.forName(clzS);
			Object o = c.newInstance();
			Class<?> params[] = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] instanceof Context) {
					params[i] = Context.class;
				} else if (obj[i] instanceof SharedPreferences) {
					params[i] = SharedPreferences.class;
				}
			}
			//Constructor<?> constt = clazz.getConstructor(Context.class);
			Method methodezz = o.getClass().getMethod(methode,params);
			
			
			methodezz.invoke(o,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	};

}
