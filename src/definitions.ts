export interface CapacitorWifiPlugin {
  checkPermission(): Promise<{status: string}>;
  requestPermission(): void;
  wifiScan(): Promise< {networks: ScanResult[], error: string | undefined}>;
  getWifiStatus(): Promise<{status: boolean }>;
  getSupportedBands() : Promise<SupportedBands>;
  changeWifiStatus(): Promise<{status: boolean}>;
  disconnect(): Promise<{status: boolean}>;
  getCurrentNetworkConfiguration(): Promise<Wifi>;
  getDHCPInfo(): Promise<DhcpInfo>;
  isP2PSupported(): Promise<{p2p_supported: boolean}>;
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

export interface SupportedBands {
    WiFi24: boolean;
    WiFi5: boolean;
    WiFi6: boolean;
    WiFi60: boolean;
}

export interface DhcpInfo { // Wrapper around DhcpInfo of Android SDK
  dns1: number;
  dns2: number;
  gateway: number;
  ipAddress: number;
  leaseDuration: number;
  netmask: number;
  serverAddress: number;
}

export interface Wifi {
  ssid: string;
  bssid: string;
  frequency: number;
  hidden: boolean;
  ip_address: number;
  link_speed: number;
  mac_address: string;
  network_id: number;
  rssi: number;
  current_rx_speed: number | null;
  current_tx_speed: number | null;
  fqdn: string | null;
  passpoint_provider_friendly_name: string | null;
  max_rx_speed: string | null; 
  max_tx_speed: string | null;
  wifi_standard: number | null;
  security: number | null;
  mlo_links: Record<string, unknown>[] | null;
  mld: string | null;
  mlo_id: number | null;
  associated_mlo_links: Record<string, unknown>[] | null;
}