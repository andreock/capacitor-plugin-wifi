export interface CapacitorWifiPlugin {
  checkPermission(): Promise<{status: string}>;
  requestPermission(): void;
  wifiScan(): Promise< {networks: ScanResult[], error: string | undefined}>;
  getWifiStatus(): Promise<{status: boolean }>;
}

export interface ScanResult { // Wrapper around ScanResult of Android SDK
  BSSID: string;
  SSID: string;
  capabilities: string;
  centerFreq0: number | null;
  centerFreq1: number | null;
  frequency: number;
  level: number;
  timestamp: number;
}