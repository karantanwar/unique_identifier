package altercode.xyz.uniqueidentifier;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.text.TextUtils;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import android.provider.Settings.Secure;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;


public class UniqueIdentifierPlugin implements FlutterPlugin, MethodCallHandler {
   static private Context context;
  private MethodChannel channel;


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "unique_identifier");
    context = flutterPluginBinding.getApplicationContext();
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getUniqueIdentifier")) {
      final String android_id = getUniqueDeviceIdentifierAsString();
      result.success(android_id);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  private String getUniqueDeviceIdentifierAsString() {
    // Prioritize privacy-friendly identifiers
    String androidId = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
    String hashedAndroidId = (androidId != null) ? hashIdentifier(androidId) : "";

    if (!TextUtils.isEmpty(hashedAndroidId)) {
        return hashedAndroidId;
    }

    // Fall back to device-specific identifiers with caution
    TelephonyManager telephonyManager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
    String deviceId = telephonyManager != null ? telephonyManager.getDeviceId() : null;
    String serialNumber = telephonyManager != null ? telephonyManager.getSimSerialNumber() : null;

    if (!TextUtils.isEmpty(deviceId) && !TextUtils.isEmpty(serialNumber)) {
      UUID deviceUuid = new UUID(deviceId.hashCode(), serialNumber.hashCode());
      return deviceUuid.toString();
    }

    return ""; // Return empty string if no suitable identifier is available
  }


  private String hashIdentifier(String identifier) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(identifier.getBytes(StandardCharsets.UTF_8));
      return Base64.encodeToString(hashBytes, Base64.DEFAULT);
    } catch (NoSuchAlgorithmException e) {
      Log.e("UniqueIdentifierPlugin", "SHA-256 algorithm not found", e);
      return "";
    }
  }
}
