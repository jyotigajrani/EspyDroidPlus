/**
 * @testcase_name ComplexReflection
 * 
 * @description Leak through Reflection and Obfuscation
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Late Binding of Reflection Targets, Inter-Component Communication, Conditional Constraint, Complex GUI
 */

package com.example.sixactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class Read {

	//Context context;
	//public static String SF = "MyPrefsFile";
	//public SharedPreferences sf;
/*
	public Read(Context context) {
		this.context = context.getApplicationContext();
		sf = context.getApplicationContext().getSharedPreferences("test", 0);

	}
*/
	public void read_sf(Context context,SharedPreferences at) {
		//sf = at.getString(arg0, arg1)(context., 0);		
		String imei = at.getString("number", "");
		 Log.i("JYOTIIIIIIIIIIIIIIIIIIIIIIIIIII", imei); //sink, leak
		Toast.makeText(context, "READ"+imei, Toast.LENGTH_LONG).show();
	}
}
