# Flutter Search Panel

[![pub package](https://img.shields.io/badge/pub-0.0.3-green.svg)](https://pub.dartlang.org/packages/unique_identifier)

A Flutter plugin to get the ANDROID_ID for android and identifierForVendor for iOS platforms.

## Installing

```yaml
dependencies:
  unique_identifier: ^0.0.3
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

