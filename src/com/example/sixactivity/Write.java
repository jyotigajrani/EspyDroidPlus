/**
 * @testcase_name ComplexReflection
 * 
 * @description Leak through Reflection and Obfuscation
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Late Binding of Reflection Targets, Inter-Component Communication, Conditional Constraint, Complex GUI
 */

package com.example.sixactivity;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class Write {
	/*Context context;
	public static String SF = "MyPrefsFile";*/
	//public SharedPreferences sf;

/*	public Write(Context context) {
		this.context = context.getApplicationContext();
		sf = context.getApplicationContext().getSharedPreferences("test", 0);

	}
*/
	public void write_sf(Context context,SharedPreferences at) {
		//sf = context.getApplicationContext().getSharedPreferences(context.SF, 0);
		SharedPreferences.Editor ed = at.edit();
		TelephonyManager tm = (TelephonyManager) context
				.getApplicationContext().getSystemService(
						Context.TELEPHONY_SERVICE);
		ed.putString("number", tm.getDeviceId().toString());
		ed.commit();
		Log.i("JYOTIIIIIIIIIIIIIIIIIIIIIIIIIII", tm.getDeviceId().toString()); //sink, leak
		Toast.makeText(context, "WRITE: " + tm.getDeviceId().toString(),
				Toast.LENGTH_LONG).show();
	}
}