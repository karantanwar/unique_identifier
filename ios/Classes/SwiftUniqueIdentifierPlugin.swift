import Flutter
import UIKit
    
public class SwiftUniqueIdentifierPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "unique_identifier", binaryMessenger: registrar.messenger())
    let instance = SwiftUniqueIdentifierPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch call.method {
    case "getUniqueIdentifier":
      let ios_id = getUniqueDeviceIdentifierAsString()
      result(ios_id)
    default:
      result(FlutterMethodNotImplemented)
    }
  }

  private func getUniqueDeviceIdentifierAsString() -> String {
    let appName = Bundle.main.infoDictionary?[kCFBundleNameKey as String] ?? ""
    let keychain = KeychainSwift()
    let service = "\(appName).incoding" // Combine app name and service

    let storedUUID = keychain.get(forKey: service)

    if storedUUID == nil {
      let deviceUUID = UIDevice.current.identifierForVendor?.uuidString ?? ""
      keychain.set(deviceUUID, forKey: service)
      return deviceUUID
    } else {
      return storedUUID!
    }
  }
}
