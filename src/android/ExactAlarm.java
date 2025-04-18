package com.example.exactalarm;

import android.app.AlarmManager;
import android.os.Build;
import android.content.Context;
import android.util.Log; 
import org.apache.cordova.*;
import org.json.JSONArray;

public class ExactAlarm extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        Log.d("ExactAlarm", "Action: " + action);
        if ("checkPermission".equals(action)) {
            try {
                Context context = this.cordova.getActivity().getApplicationContext();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    boolean allowed = alarmManager.canScheduleExactAlarms();
                    Log.d("ExactAlarm", "Allowed: " + allowed);
                    callbackContext.success(allowed ? 1 : 0);
                } else {
                    callbackContext.success(1); // always true < Android 12
                }
            } catch (Exception e) {
                Log.e("ExactAlarm", "Error checking permission", e);
                callbackContext.error("Java error: " + e.getMessage());
            }
            return true;
        }
        return false;
    }
    
}
