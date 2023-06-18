import { WebPlugin } from '@capacitor/core';

import type { CapacitorWifiPlugin } from './definitions';

export class CapacitorWifiWeb extends WebPlugin implements CapacitorWifiPlugin {
  async checkPermission(): Promise<{status: string}> {
    return Promise.resolve({ status: "Not implemented in web" });
  };
  requestPermission(): void {
    console.error("Not implemented in web");
  };
  wifiScan(): Promise< {networks: unknown[], error: string | undefined}> {
    return Promise.resolve({ networks: [], error: "Not implemented in web" });
  };
  getWifiStatus(): Promise<{ status: boolean; }> {
      console.error("Not implemented in web");
      return Promise.resolve({ status: false });
  }
}
