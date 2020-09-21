
import 'dart:async';

import 'package:flutter/services.dart';

class BootstrapNotifier {
  static const MethodChannel _channel =
      const MethodChannel('bootstrap_notifier');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
