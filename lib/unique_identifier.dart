import 'dart:async';

import 'package:flutter/services.dart';

class UniqueIdentifier {
  // Define a Method channel with 'unique_identifier' name
  static const MethodChannel _channel = const MethodChannel('unique_identifier');

  // Static function for getting the identifier
  // Returns Future<String?>
  static Future<String?> get serial async {
    final String? identifier = await _channel.invokeMethod('getUniqueIdentifier');
    return identifier;
  }
}
