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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ScoreBoard extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_board);
	}

}
