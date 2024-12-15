import { WebPlugin } from '@capacitor/core';

import type { CapacitorWifiPlugin, ScanResult, SupportedBands , Wifi, DhcpInfo } from './definitions';

export class CapacitorWifiWeb extends WebPlugin implements CapacitorWifiPlugin {
  async checkPermission(): Promise<{status: boolean}> {
    return Promise.resolve({ status: false });
  };
  requestPermission(): void {
    console.error("Not implemented in web");
  };
  wifiScan(): Promise< {networks: ScanResult[], error: string | undefined}> {
    console.warn("Not implemented in web, returning a mock");
    return Promise.resolve({ networks: [{
      BSSID: "aa:bb:cc:dd:ee:ff",
      SSID: "Test SSID",
      capabilities: "example",
      centerFreq0: 80,
      centerFreq1: 80,
      frequency: 0,
      level: 20,
      timestamp: 20
    },
    {
      BSSID: "aa:bb:cc:dd:ee:fx",
      SSID: "Test 2 SSID",
      capabilities: "example",
      centerFreq0: 80,
      centerFreq1: 80,
      frequency: 0,
      level: 20,
      timestamp: 20
    }], error: "Not implemented in web" });
  };
  getWifiStatus(): Promise<{ status: boolean; }> {
      console.error("Not implemented in web");
      return Promise.resolve({ status: false });
  }
  getSupportedBands() : Promise<SupportedBands> {
    throw new Error("Not implemented in web");
  };
  changeWifiStatus(): Promise<{status: boolean}> {
    console.error("Not implemented in web");
    return Promise.resolve({ status: true });
  };
  disconnect(): Promise<{status: boolean}> {
    console.error("Not implemented in web");
    return Promise.resolve({ status: true });
  };
  getCurrentNetworkConfiguration(): Promise<Wifi> {
    throw new Error("Not implemented in web");
  };
  getDHCPInfo(): Promise<DhcpInfo> {
    throw new Error("Not implemented in web");
  };
  isP2PSupported(): Promise<{p2p_supported: boolean}> {
    return Promise.resolve({ p2p_supported: true });
  };
}
