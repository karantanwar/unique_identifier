#import "UniqueIdentifierPlugin.h"
#import <unique_identifier/unique_identifier-Swift.h>

@implementation UniqueIdentifierPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftUniqueIdentifierPlugin registerWithRegistrar:registrar];
}
@end
