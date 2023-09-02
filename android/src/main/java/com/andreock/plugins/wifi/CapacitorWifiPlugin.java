package com.andreock.plugins.wifi;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.DhcpInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import org.json.JSONException;

import java.util.stream.Collectors;

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
                        ret.put("networks", new JSArray(wifiManager.getScanResults().stream().map(result -> implementation.scanResultToJS(result)).collect(Collectors.toList())));
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

    @PluginMethod
    public void getSupportedBands(PluginCall call) {
        Context context = getContext();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        JSObject ret = new JSObject();
        ret.put("result", implementation.bandSupported(wifiManager));
        call.resolve(ret);
    }

    @PluginMethod
    public void changeWifiStatus(PluginCall call) {
        Context context = getContext();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        JSObject ret = new JSObject();
        boolean status = implementation.changeWifiStatus(wifiManager, Boolean.TRUE.equals(call.getBoolean("status")));
        ret.put("status", status);
        call.resolve(ret);
    }

    @PluginMethod
    public void disconnect(PluginCall call) {
        Context context = getContext();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        JSObject ret = new JSObject();
        boolean status = implementation.disconnect(wifiManager);
        ret.put("status", status);
        call.resolve(ret);
    }

    @PluginMethod
    public void getDHCPInfo(PluginCall call) {
        Context context = getContext();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        JSObject ret = new JSObject();
        DhcpInfo dhcpInfo = implementation.getDHCPInfo(wifiManager);
        ret.put("dhcp", dhcpInfo);
        call.resolve(ret);
    }

    @PluginMethod
    public void getCurrentNetworkConfiguration(PluginCall call) {
        Context context = getContext();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        JSObject ret = new JSObject();
        WifiInfo currentWifi = implementation.getCurrentNetworkConfiguration(wifiManager);
        ret.put("ssid", currentWifi.getSSID());
        ret.put("bssid", currentWifi.getBSSID());
        ret.put("frequency", currentWifi.getFrequency());
        ret.put("hidden", currentWifi.getHiddenSSID());
        ret.put("ip_address", currentWifi.getIpAddress());
        ret.put("link_speed", currentWifi.getLinkSpeed());
        ret.put("mac_address", currentWifi.getMacAddress());    // TODO: Change it
        ret.put("network_id", currentWifi.getNetworkId());
        ret.put("rssi", currentWifi.getRssi());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {   // A10
            ret.put("current_rx_speed", currentWifi.getRxLinkSpeedMbps());
            ret.put("current_tx_speed", currentWifi.getTxLinkSpeedMbps());
            ret.put("fqdn", currentWifi.getPasspointFqdn());
            ret.put("passpoint_provider_friendly_name", currentWifi.getPasspointProviderFriendlyName());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {   // A11
            ret.put("max_rx_speed", currentWifi.getMaxSupportedRxLinkSpeedMbps());
            ret.put("max_tx_speed", currentWifi.getMaxSupportedTxLinkSpeedMbps());
            ret.put("wifi_standard", currentWifi.getWifiStandard());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {   // A12
            ret.put("security", currentWifi.getCurrentSecurityType());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {    // A13
            ret.put("mlo_lSinks", currentWifi.getAffiliatedMloLinks());
            ret.put("mld", currentWifi.getApMldMacAddress());
            ret.put("mlo_id", currentWifi.getApMloLinkId());
            ret.put("associated_mlo_links", currentWifi.getAffiliatedMloLinks());
        }
        call.resolve(ret);
    }

    @PluginMethod
    public void isP2PSupported(PluginCall call) {
        Context context = getContext();
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        JSObject ret = new JSObject();
        boolean p2PSupported = implementation.isP2PSupported(wifiManager);
        ret.put("p2p_supported", p2PSupported);
        call.resolve(ret);
    }
}
