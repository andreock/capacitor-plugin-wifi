<script>
  import {
    Page,
    Navbar,
    NavLeft,
    NavTitle,
    NavTitleLarge,
    NavRight,
    Link,
    Toolbar,
    Block,
    BlockTitle,
    List,
    ListItem,
    Button,
    f7,
  } from 'framework7-svelte';
  import { CapacitorWifi } from 'capacitor-plugin-wifi';

  async function checkPermissionResult() {
    let result = await CapacitorWifi.checkPermission();
    f7.dialog.alert(result.status)
  }

  async function scanWifiResult() {
    let result = await CapacitorWifi.wifiScan();
    f7.dialog.alert(JSON.stringify(result));
  }

  async function wifiStatusResult() {
    let result = await CapacitorWifi.getWifiStatus();
    f7.dialog.alert(result.status);
  }
</script>

<Page name="home">
  <!-- Top Navbar -->
  <Navbar large sliding={false}>
    <NavLeft>
      <Link iconIos="f7:menu" iconMd="material:menu" panelOpen="left" />
    </NavLeft>
    <NavTitle sliding>Capacitor Wifi Plugin Example</NavTitle>
    <NavRight>
      <Link iconIos="f7:menu" iconMd="material:menu" panelOpen="right" />
    </NavRight>
    <NavTitleLarge>Capacitor Wifi Plugin Example</NavTitleLarge>
  </Navbar>
  <!-- Toolbar -->
  <Toolbar bottom>
    <Link>Left Link</Link>
    <Link>Right Link</Link>
  </Toolbar>
  <!-- Page content -->
  <Block>
    <Button fill on:click={checkPermissionResult}>CheckPermission</Button>
  </Block>
  <Block>
    <Button fill on:click={CapacitorWifi.requestPermission}>RequirePermission</Button>
  </Block>
  <Block>
    <Button fill on:click={scanWifiResult}>Start WiFi scan</Button>
  </Block>
  <Block>
    <Button fill on:click={wifiStatusResult}>Get WiFi status</Button>
  </Block>
</Page>
