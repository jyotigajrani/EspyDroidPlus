/**
 * @testcase_name ComplexReflection
 * 
 * @description Leak through Reflection and Obfuscation
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Late Binding of Reflection Targets, Inter-Component Communication, Conditional Constraint, Complex GUI
 */

package com.example.sixactivity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Rating extends ActionBarActivity {
	Button playB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		playB=(Button)findViewById(R.id.button_play);
		
		playB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Rating.this, Play.class));
				
			}
		});
		
	}//end of onCreate()...
}//end of class Rating...