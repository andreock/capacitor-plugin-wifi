# capacitor-plugin-wifi

Capacitor JS plugin that work as wrapper of WifiManager of Android

## Install

```bash
npm install capacitor-plugin-wifi
npx cap sync
```

## API

<docgen-index>

* [`checkPermission()`](#checkpermission)
* [`requestPermission()`](#requestpermission)
* [`wifiScan()`](#wifiscan)
* [`getWifiStatus()`](#getwifistatus)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### checkPermission()

```typescript
checkPermission() => Promise<{ status: string; }>
```

**Returns:** <code>Promise&lt;{ status: string; }&gt;</code>

--------------------


### requestPermission()

```typescript
requestPermission() => void
```

--------------------


### wifiScan()

```typescript
wifiScan() => Promise<{ networks: ScanResult[]; error: string | undefined; }>
```

**Returns:** <code>Promise&lt;{ networks: ScanResult[]; error: string; }&gt;</code>

--------------------


### getWifiStatus()

```typescript
getWifiStatus() => Promise<{ status: boolean; }>
```

**Returns:** <code>Promise&lt;{ status: boolean; }&gt;</code>

--------------------


### Interfaces


#### ScanResult

| Prop               | Type                        |
| ------------------ | --------------------------- |
| **`BSSID`**        | <code>string</code>         |
| **`SSID`**         | <code>string</code>         |
| **`capabilities`** | <code>string</code>         |
| **`centerFreq0`**  | <code>number \| null</code> |
| **`centerFreq1`**  | <code>number \| null</code> |
| **`frequency`**    | <code>number</code>         |
| **`level`**        | <code>number</code>         |
| **`timestamp`**    | <code>number</code>         |

</docgen-api>

Check example for more information.