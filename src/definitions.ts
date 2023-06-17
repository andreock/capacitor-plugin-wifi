export interface CapacitorWifiPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
