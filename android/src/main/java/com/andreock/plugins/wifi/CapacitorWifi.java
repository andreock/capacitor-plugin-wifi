package com.andreock.plugins.wifi;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.andreock.plugins.wifi.models.SupportedBand;
import com.getcapacitor.BridgeActivity;

import java.util.List;

public class CapacitorWifi {
    public boolean checkPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean getWifiStatus(WifiManager manager) {
        return manager.isWifiEnabled();
    }

    public boolean disconnect(WifiManager manager) {
        return manager.disconnect();
    }

    public SupportedBand bandSupported(WifiManager manager) {
        SupportedBand bands = new SupportedBand();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            bands.WiFi24 = manager.is24GHzBandSupported();
            bands.WiFi60 = manager.is60GHzBandSupported();
        }
        bands.WiFi5 = manager.is5GHzBandSupported();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            bands.WiFi6 = manager.is6GHzBandSupported();
        }
        return bands;
    }

    public DhcpInfo getDHCPInfo(WifiManager manager) {
        return manager.getDhcpInfo();
    }

    public WifiInfo getCurrentNetworkConfiguration(WifiManager manager) {
        return manager.getConnectionInfo();
    }

    @SuppressLint("MissingPermission")  // Check did in CapacitorWifi
    public List<WifiConfiguration> getConfiguratedNeworks(WifiManager manager) {
        return manager.getConfiguredNetworks();
    }

    public boolean changeWifiStatus(WifiManager manager, boolean status) {
        return manager.setWifiEnabled(status);
    }

    public boolean isP2PSupported(WifiManager manager) {
        return manager.isP2pSupported();
    }
}