import { WebPlugin } from '@capacitor/core';

import type { CapacitorWifiPlugin } from './definitions';

export class CapacitorWifiWeb extends WebPlugin implements CapacitorWifiPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
