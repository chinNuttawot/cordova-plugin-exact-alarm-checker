package org.apache.cordova.exactalarm;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Build;
import android.util.Log; // อย่าลืม import นี้ถ้าใช้ Log

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;

public class ExactAlarm extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("checkPermission".equals(action)) {
            try {
                Context context = this.cordova.getActivity().getApplicationContext();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    boolean allowed = alarmManager.canScheduleExactAlarms();
                    Log.d("ExactAlarm", "Allowed: " + allowed);
                    callbackContext.success(allowed ? 1 : 0);
                } else {
                    callbackContext.success(1); // ต่ำกว่า Android 12 ถือว่า "อนุญาต" โดยไม่ต้องขอ
                }
            } catch (Exception e) {
                Log.e("ExactAlarm", "Error checking permission", e);
                callbackContext.error("Exception: " + e.getMessage());
            }
            return true;
        }
        return false;
    }
}

