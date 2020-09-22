package com.example.bootstrap_notifier;


import android.app.Application;

import androidx.annotation.NonNull;



import android.os.Build;
import android.util.Log;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.RegionBootstrap;
import org.altbeacon.beacon.startup.BootstrapNotifier;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;




/** BootstrapNotifierPlugin */
public class BootstrapNotifierPlugin extends Application implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "bootstrap_notifier");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  private static final String TAG = ".MyApplicationName";
  private RegionBootstrap regionBootstrap;


  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "App started up");
    BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);
    // To detect proprietary beacons, you must add a line like below corresponding to your beacon
    // type.  Do a web search for "setBeaconLayout" to get the proper expression.
    // beaconManager.getBeaconParsers().add(new BeaconParser().
    //        setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

    // wake up the app when any beacon is seen (you can specify specific id filers in the parameters below)
    Region region = new Region("com.example.bootstrap_notifier", null, null, null);
    regionBootstrap = new RegionBootstrap((BootstrapNotifier) this, region);
  }
}
