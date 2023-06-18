export interface CapacitorWifiPlugin {
  checkPermission(): Promise<{status: string}>;
  requestPermission(): void;
  wifiScan(): Promise< {networks: unknown[], error: string | undefined}>;
  getWifiStatus(): Promise<{status: boolean }>;
}
