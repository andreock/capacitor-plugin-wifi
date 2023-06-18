package com.andreock.plugins.wifi;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(
        name = "CapacitorWifi",
        permissions = {
        @Permission(
                strings = { Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
                alias = "wifiPermission"
        )
}
)
public class CapacitorWifiPlugin extends Plugin {

    private CapacitorWifi implementation = new CapacitorWifi();

    @PluginMethod
    public void checkPermission(PluginCall call) {
        Log.e("CapacitorWifiPlugin", "Checking permission");
        JSObject ret = new JSObject();
        ret.put("status", implementation.checkPermission(getContext()));
        call.resolve(ret);
    }

    @PermissionCallback
    private void permissionCallback(PluginCall call) {
        if (getPermissionState("wifiPermission") == PermissionState.DENIED) {
            call.reject("Permission denied");
        } else {
            JSObject ret = new JSObject();
            ret.put("status", "true");
            call.resolve(ret);
        }
    }

    @PluginMethod
    public void requestPermission(PluginCall call) {
        requestAllPermissions(call, "permissionCallback");
    }

    @PluginMethod
    public void wifiScan(PluginCall call) {
        Context context = getContext();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                boolean success = intent.getBooleanExtra(
                        WifiManager.EXTRA_RESULTS_UPDATED, false);
                Log.e("CapacitorWifiPlugin", String.valueOf(success));
                if (success) {
                    if(implementation.checkPermission(context)){
                        JSObject ret = new JSObject();
                        ret.put("networks", wifiManager.getScanResults());
                        ret.put("error", null);
                        call.resolve(ret);
                    }else {
                        JSObject ret = new JSObject();
                        ret.put("networks", null);
                        ret.put("error", "Permission denied");
                        call.resolve(ret);
                    }
                } else {
                    JSObject ret = new JSObject();
                    ret.put("networks", null);
                    ret.put("error", "true");
                    call.resolve(ret);
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(wifiScanReceiver, intentFilter);
        wifiManager.startScan();
    }

    @PluginMethod
    public void getWifiStatus(PluginCall call) {
        Context context = getContext();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        JSObject ret = new JSObject();
        ret.put("status", implementation.getWifiStatus(wifiManager));
        call.resolve(ret);
    }
}
