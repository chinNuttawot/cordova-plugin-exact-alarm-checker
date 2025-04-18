package com.example.exactalarm;

import android.app.AlarmManager;
import android.os.Build;
import android.content.Context;

import org.apache.cordova.*;
import org.json.JSONArray;

public class ExactAlarm extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("checkPermission".equals(action)) {
            Context context = this.cordova.getActivity().getApplicationContext();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                boolean allowed = alarmManager.canScheduleExactAlarms();
                callbackContext.success(allowed ? 1 : 0);
            } else {
                callbackContext.success(1); // always true on < Android 12
            }
            return true;
        }
        return false;
    }
}
