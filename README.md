# Unique Identifier

[![pub package](https://img.shields.io/badge/pub-0.3.0-green.svg)](https://pub.dartlang.org/packages/unique_identifier)

A Flutter plugin to get the ANDROID_ID for android and identifierForVendor for iOS platforms.

## Installing

```yaml
dependencies:
  unique_identifier: ^0.3.0
```

### Import

```dart
import 'package:unique_identifier/unique_identifier.dart';
```

## How To Use

```
 dart String _identifier = 'Unknown';
  
 @override
 void initState() {
   super.initState();
   initUniqueIdentifierState();
 }

 Future<void> initUniqueIdentifierState() async {
   String identifier;
   try {
     identifier = await UniqueIdentifier.serial;
   } on PlatformException {
     identifier = 'Failed to get Unique Identifier';
   }

   if (!mounted) return;

   setState(() {
     _identifier = identifier;
   });
 }

 @override
 Widget build(BuildContext context) {
   return new MaterialApp(
     home: new Scaffold(
       appBar: new AppBar(
         title: const Text('Plugin example app'),
       ),
       body: new Center(
         child: new Text('Running on device with id: $_identifier\n'),
       ),
     ),
   );
 }
```

## Bugs & Requests

If you encounter any bugs feel free to open an issue. Raise a ticket on github for suggestions. Pull request are also welcome.

### Flutter

For help getting started with Flutter, view our online
[documentation](https://flutter.io/).

For help on editing plugin code, view the [documentation](https://flutter.io/platform-plugins/#edit-code).

## License

MIT License


## NOTE
On Android 8.0 (API level 26) and higher versions of the platform, a 64-bit number (expressed as a hexadecimal string), unique to each combination of app-signing key, user, and device. Values of ANDROID_ID are scoped by signing key and user. The value may change if a factory reset is performed on the device or if an APK signing key changes. For more information about how the platform handles ANDROID_ID in Android 8.0 (API level 26) and higher, see Android 8.0 Behavior Changes.
Note: For apps that were installed prior to updating the device to a version of Android 8.0 (API level 26) or higher, the value of ANDROID_ID changes if the app is uninstalled and then reinstalled after the OTA. To preserve values across uninstalls after an OTA to Android 8.0 or higher, developers can use Key/Value Backup.
In versions of the platform lower than Android 8.0 (API level 26), a 64-bit number (expressed as a hexadecimal string) that is randomly generated when the user first sets up the device and should remain constant for the lifetime of the user's device. On devices that have multiple users, each user appears as a completely separate device, so the ANDROID_ID value is unique to each user.
Note: If the caller is an Instant App the ID is scoped to the Instant App, it is generated when the Instant App is first installed and reset if the user clears the Instant App.