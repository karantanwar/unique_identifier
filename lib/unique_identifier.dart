import 'dart:async';
import 'package:flutter/services.dart';

class UniqueIdentifier {
  static const MethodChannel _channel =
      const MethodChannel('unique_identifier');

  static Future<String> get serial async {
    final String identifier =
        await _channel.invokeMethod('getUniqueIdentifier');
    return identifier;
  }
}
