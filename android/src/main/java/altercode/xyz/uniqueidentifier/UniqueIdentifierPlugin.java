package altercode.xyz.uniqueidentifier;

import android.content.Context;

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
      String android_id = null;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
        android_id = Secure.getString(context.getContentResolver(),
                Secure.ANDROID_ID);
      }

      result.success(android_id);
    } else {
      result.notImplemented();
    }


  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }



}
