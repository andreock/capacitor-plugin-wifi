import { WebPlugin } from '@capacitor/core';

import type { CapacitorWifiPlugin, ScanResult, SupportedBands , Wifi, DhcpInfo } from './definitions';

export class CapacitorWifiWeb extends WebPlugin implements CapacitorWifiPlugin {
  async checkPermission(): Promise<{status: string}> {
    return Promise.resolve({ status: "Not implemented in web" });
  };
  requestPermission(): void {
    console.error("Not implemented in web");
  };
  wifiScan(): Promise< {networks: ScanResult[], error: string | undefined}> {
    return Promise.resolve({ networks: [], error: "Not implemented in web" });
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
