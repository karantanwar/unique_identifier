package altercode.xyz.uniqueidentifier;

import android.content.Context;
import android.provider.Settings;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import android.provider.Settings.Secure;


public class UniqueIdentifierPlugin implements MethodCallHandler {
   static private Context context;
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "unique_identifier");
      context = registrar.context();
    channel.setMethodCallHandler(new UniqueIdentifierPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
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
}
