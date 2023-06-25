import { registerPlugin } from '@capacitor/core';

import type { CapacitorWifiPlugin } from './definitions';

const CapacitorWifi = registerPlugin<CapacitorWifiPlugin>('CapacitorWifi', {
  web: () => import('./web').then(m => new m.CapacitorWifiWeb()),
});

export * from './constants';
export * from './definitions';
export { CapacitorWifi };
