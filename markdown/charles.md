# Charles
## How to enable Charles Proxy in debug build
https://www.charlesproxy.com/documentation/using-charles/ssl-certificates/

## How to enable Charles Proxy in release build
1. Connect your Android to Charles. Otherwise, Charles Root Certificate will NOT be automatically downloaded in the next step.
1. Access http://www.charlesproxy.com/getssl/ or http://chls.pro/ssl from Android. Then, Charles Root Certificate should be automatically downloaded.
1. Open Charles and go to the menu bar > `Help` > `SSL Proxying` > `Save Charles Root Certificate`
1. Add the downloaded PEM file as `res/raw/charles_certificate.pem`.
1. Add `ref/xml/network_security_config.xml` as follows.
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config>
        <trust-anchors>
            <certificates src="system" />
            <certificates src="@raw/charles_certificate"/>
            <!-- The following in <base-config> has no effect. -->
            <!-- <certificates src="user" /> -->
        </trust-anchors>
    </base-config>
</network-security-config>
```
1. Reference `ref/xml/network_security_config.xml` in `AndroidManifest.xml` as follows.
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest>
    <application android:networkSecurityConfig="@xml/network_security_config" />
</manifest>
```

### Troubleshooting
If you have installed the Charles Root Certificate on Android and still see the following error, you may be doing the release build. If so, the above steps will solve the error.
```
SSL handshake with client failed: An unknown issue occurred processing the certificate (certificate_unknown)
You may need to configure your browser or application to trust the Charles Root Certificate. See SSL Proxying in the Help menu.
certificate_unknown (46) - An unknown issue occurred processing the certificate.
```
