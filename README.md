# capacitor-plugin-wifi

Capacitor JS plugin to scan and connect to wifi

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
wifiScan() => Promise<{ networks: unknown[]; error: string | undefined; }>
```

**Returns:** <code>Promise&lt;{ networks: unknown[]; error: string; }&gt;</code>

--------------------


### getWifiStatus()

```typescript
getWifiStatus() => Promise<{ status: boolean; }>
```

**Returns:** <code>Promise&lt;{ status: boolean; }&gt;</code>

--------------------

</docgen-api>

Check example for more information.