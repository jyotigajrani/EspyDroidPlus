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

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyServices extends Service {
    public MyServices() {
    	
    	try {
        
     	String cName = "android.telephony.TelephonyManager";
         Class c = Class.forName(cName);

         TelephonyManager telephonyManager = (TelephonyManager) this
                 .getSystemService(Context.TELEPHONY_SERVICE);
         Method method = c.getMethod("get" + "Device" + "Id", new Class<?>[0]);
         String did = (String) method.invoke(telephonyManager);
        

     } catch (Exception e) {
         e.printStackTrace();

     }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(getApplicationContext(),"IMEI IN SERVICE",Toast.LENGTH_SHORT).show();
        Log.d("IMEI in Service", "kkkkkkkkkkkkkkkkkkkkk");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
