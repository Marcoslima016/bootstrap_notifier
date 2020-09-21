import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:bootstrap_notifier/bootstrap_notifier.dart';

void main() {
  const MethodChannel channel = MethodChannel('bootstrap_notifier');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await BootstrapNotifier.platformVersion, '42');
  });
}
