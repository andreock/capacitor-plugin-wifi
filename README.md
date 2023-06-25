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
* [`getSupportedBands()`](#getsupportedbands)
* [`changeWifiStatus()`](#changewifistatus)
* [`disconnect()`](#disconnect)
* [`getCurrentNetworkConfiguration()`](#getcurrentnetworkconfiguration)
* [`getDHCPInfo()`](#getdhcpinfo)
* [`isP2PSupported()`](#isp2psupported)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

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


### getSupportedBands()

```typescript
getSupportedBands() => Promise<SupportedBands>
```

**Returns:** <code>Promise&lt;<a href="#supportedbands">SupportedBands</a>&gt;</code>

--------------------


### changeWifiStatus()

```typescript
changeWifiStatus() => Promise<{ status: boolean; }>
```

**Returns:** <code>Promise&lt;{ status: boolean; }&gt;</code>

--------------------


### disconnect()

```typescript
disconnect() => Promise<{ status: boolean; }>
```

**Returns:** <code>Promise&lt;{ status: boolean; }&gt;</code>

--------------------


### getCurrentNetworkConfiguration()

```typescript
getCurrentNetworkConfiguration() => Promise<Wifi>
```

**Returns:** <code>Promise&lt;<a href="#wifi">Wifi</a>&gt;</code>

--------------------


### getDHCPInfo()

```typescript
getDHCPInfo() => Promise<DhcpInfo>
```

**Returns:** <code>Promise&lt;<a href="#dhcpinfo">DhcpInfo</a>&gt;</code>

--------------------


### isP2PSupported()

```typescript
isP2PSupported() => Promise<{ p2p_supported: boolean; }>
```

**Returns:** <code>Promise&lt;{ p2p_supported: boolean; }&gt;</code>

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


#### SupportedBands

| Prop         | Type                 |
| ------------ | -------------------- |
| **`WiFi24`** | <code>boolean</code> |
| **`WiFi5`**  | <code>boolean</code> |
| **`WiFi6`**  | <code>boolean</code> |
| **`WiFi60`** | <code>boolean</code> |


#### Wifi

| Prop                                   | Type                                                                       |
| -------------------------------------- | -------------------------------------------------------------------------- |
| **`ssid`**                             | <code>string</code>                                                        |
| **`bssid`**                            | <code>string</code>                                                        |
| **`frequency`**                        | <code>number</code>                                                        |
| **`hidden`**                           | <code>boolean</code>                                                       |
| **`ip_address`**                       | <code>number</code>                                                        |
| **`link_speed`**                       | <code>number</code>                                                        |
| **`mac_address`**                      | <code>string</code>                                                        |
| **`network_id`**                       | <code>number</code>                                                        |
| **`rssi`**                             | <code>number</code>                                                        |
| **`current_rx_speed`**                 | <code>number \| null</code>                                                |
| **`current_tx_speed`**                 | <code>number \| null</code>                                                |
| **`fqdn`**                             | <code>string \| null</code>                                                |
| **`passpoint_provider_friendly_name`** | <code>string \| null</code>                                                |
| **`max_rx_speed`**                     | <code>string \| null</code>                                                |
| **`max_tx_speed`**                     | <code>string \| null</code>                                                |
| **`wifi_standard`**                    | <code>number \| null</code>                                                |
| **`security`**                         | <code>number \| null</code>                                                |
| **`mlo_links`**                        | <code><a href="#record">Record</a>&lt;string, unknown&gt;[] \| null</code> |
| **`mld`**                              | <code>string \| null</code>                                                |
| **`mlo_id`**                           | <code>number \| null</code>                                                |
| **`associated_mlo_links`**             | <code><a href="#record">Record</a>&lt;string, unknown&gt;[] \| null</code> |


#### DhcpInfo

| Prop                | Type                |
| ------------------- | ------------------- |
| **`dns1`**          | <code>number</code> |
| **`dns2`**          | <code>number</code> |
| **`gateway`**       | <code>number</code> |
| **`ipAddress`**     | <code>number</code> |
| **`leaseDuration`** | <code>number</code> |
| **`netmask`**       | <code>number</code> |
| **`serverAddress`** | <code>number</code> |


### Type Aliases


#### Record

Construct a type with a set of properties K of type T

<code>{ [P in K]: T; }</code>

</docgen-api>

Check example for more information.