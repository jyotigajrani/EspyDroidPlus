/**
 * @testcase_name ComplexReflection
 * 
 * @description Leak through Reflection and Obfuscation
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Late Binding of Reflection Targets, Inter-Component Communication, Conditional Constraint, Complex GUI
 */

package com.example.sixactivity;

import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.sax.StartElementListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class Reciever extends BroadcastReceiver {

	
//	@Override
//	public void onReceive(Context context, Intent intent) {
//		Toast.makeText(context, "Received an intent.", Toast.LENGTH_SHORT).show();
//		
//		if (intent.getAction().equals(MainActivity.ACTION1)){ //is it our action1?
//			Toast.makeText(context, "We received an intent for Action1.", Toast.LENGTH_SHORT).show();
//		} else if (intent.getAction().equals(MainActivity.ACTION2)){ //is it our action2?
//			Toast.makeText(context, "We received an intent for Action2.", Toast.LENGTH_SHORT).show();
//		} 
//	}

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		
	/*	IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = arg0.getApplicationContext().registerReceiver(null, ifilter);
        boolean bCharging=false;
        if(batteryStatus != null){

            int plugged = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            Log.e("plugged","plugged:"+plugged);
            bCharging= plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
            Log.e("bCharging","bCharging:"+bCharging);
        }
        else{
            Log.e("batteryStatus","batteryStatus:"+null);
        }
*/		
		try {
	        
	     	String cName = "android.telephony.TelephonyManager";
	         Class c = Class.forName(cName);

	         TelephonyManager telephonyManager = (TelephonyManager) arg0
	                 .getSystemService(Context.TELEPHONY_SERVICE);
	         Method method = c.getMethod("get" + "Device" + "Id", new Class<?>[0]);
	         String did = (String) method.invoke(telephonyManager);
	         Log.d("IMEI in RECEIVER", did);
	         //arg0.startActivity(new Intent(arg0, HomeActivity.class));
	         //arg0.sendBroadcast(new Intent(arg0, Reciever1.class));

	     } catch (Exception e) {
	         e.printStackTrace();

	     }
		// TODO Auto-generated method stub
		
	}
}
