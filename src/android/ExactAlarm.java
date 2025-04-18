package com.example.exactalarm;

import android.app.AlarmManager;
import android.os.Build;
import android.content.Context;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

public class ExactAlarm extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("checkPermission".equals(action)) {
            this.checkPermission(callbackContext);
            return true;
        }
        return false;
    }

    private void checkPermission(CallbackContext callbackContext) {
        Context context = cordova.getActivity().getApplicationContext();
        boolean allowed = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            allowed = alarmManager.canScheduleExactAlarms();
        }

        callbackContext.success(allowed ? 1 : 0);
    }
}
