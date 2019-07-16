//
//  JVerificationPlugin.m
//  HelloWorld
//
//  Created by 韦瑞杨 on 2019/7/15.
//

#import "JVerificationPlugin.h"
#import <Cordova/CDVPlugin.h>
//引入JVERIFICATIONService.h头文件
#import "JVERIFICATIONService.h"
// 如果需要使用 idfa 功能所需要引入的头文件（可选）
#import <AdSupport/AdSupport.h>

#define UIColorFromRGBValue(rgbValue) [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16))/255.0 green:((float)((rgbValue & 0xFF00) >> 8))/255.0 blue:((float)(rgbValue & 0xFF))/255.0 alpha:1.0]


@implementation JVerificationPlugin


- (void)init:(CDVInvokedUrlCommand*)command
{
    NSString *plistPath = [[NSBundle mainBundle]pathForResource:@"JVerificationConfig" ofType:@"plist"];
    NSMutableDictionary *dataDic = [[NSMutableDictionary alloc]initWithContentsOfFile:plistPath];
//    NSLog(@"%@",dataDic);//直接打印数据
    
    // 如需使用 IDFA 功能请添加此代码并在初始化配置类中设置 advertisingId
    NSString *idfaStr = [[[ASIdentifierManager sharedManager] advertisingIdentifier] UUIDString];
    
    JVAuthConfig *config = [[JVAuthConfig alloc] init];
    config.appKey = dataDic[@"Appkey"];
    config.channel = dataDic[@"Channel"];
    config.advertisingId = idfaStr;
    [JVERIFICATIONService setupWithConfig:config];
    
}


- (void)isInitSuccess:(CDVInvokedUrlCommand*)command
{
    
    BOOL isSetupClient = [JVERIFICATIONService isSetupClient];
    CDVPluginResult*  pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:isSetupClient];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}


- (void)setDebugMode:(CDVInvokedUrlCommand*)command
{
    BOOL debug = [command.arguments  objectAtIndex:0];
    [JVERIFICATIONService setDebug:debug];
}

- (void)checkVerifyEnable:(CDVInvokedUrlCommand*)command
{
    BOOL b = [JVERIFICATIONService checkVerifyEnable];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:b];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}



- (void)getToken:(CDVInvokedUrlCommand*)command
{
    NSNumber* timeout = [command.arguments objectAtIndex:0];
    [JVERIFICATIONService getToken:[timeout intValue] completion:^(NSDictionary *result) {
        //        {"code":2000,"content":"ok","operator":"CM"}
//        result 字典 获取到token时key有operator、code、token字段，获取不到token时key为code和content字段
//        NSLog(@"getToken result:%d", [timeout intValue]);
//        NSLog(@"getToken result:%@", result);
        
        NSNumber* code = result[@"code"];
        NSString* token = result[@"token"];
        NSString* operator = result[@"operator"];
        NSString* content = result[@"content"];
        
        
        NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
        [dic setObject:[NSNumber numberWithInteger:[code intValue]] forKey:@"code"];
        
        if (nil != token) {
            [dic setValue:token forKey:@"content"];
            [dic setValue:operator forKey:@"operator"];
        }else{
            [dic setValue:content forKey:@"content"];
        }
        
        NSString* json =  messageAsDictionary(dic);
        CDVPluginResult* pluginResult = pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:json];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)verifyNumber:(CDVInvokedUrlCommand*)command
{
    
    NSString* token = [command.arguments objectAtIndex:0];
    NSString* phone = [command.arguments objectAtIndex:1];
    
    
    JVAuthEntity *entity = [[JVAuthEntity alloc] init];
    entity.number = phone;
    entity.token = token;
    [JVERIFICATIONService verifyNumber:entity result:^(NSDictionary *result) {
//        {"code":1000,"content":"ok","operator":"CM"}
//        result 字典 key为code和content两个字段
//        NSLog(@"verifyNumber result:%@", result);
        
        
        NSNumber* code = result[@"code"];
        NSString* operator = result[@"operator"];
        NSString* content = result[@"content"];
        
        
        NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
        [dic setObject:[NSNumber numberWithInteger:[code intValue]] forKey:@"code"];
        
        [dic setValue:content forKey:@"content"];
        [dic setValue:operator forKey:@"operator"];
        
        
        NSString* json =  messageAsDictionary(dic);
        CDVPluginResult* pluginResult = pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:json];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        
        
    }];
    
    
//    CDVPluginResult* pluginResult = nil;
//    if (myarg != nil) {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
//    } else {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Arg was null"];
//    }
//    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}


- (void)preLogin:(CDVInvokedUrlCommand*)command
{
    
    NSNumber* timeout = [command.arguments objectAtIndex:0];
    [JVERIFICATIONService preLogin:[timeout intValue] completion:^(NSDictionary *result) {
   
        //        {"code":7000,"content":"ok"}
        //       result 字典 key为code和message两个字段
//        NSLog(@"preLogin result:%d", [timeout intValue]);
//        NSLog(@"preLogin result:%@", result);
        
        
        NSNumber* code = result[@"code"];
        NSString* content = result[@"content"];
        NSString* message = result[@"message"];
        
        
        NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
        [dic setObject:[NSNumber numberWithInteger:[code intValue]] forKey:@"code"];
        
        if (nil != content) {
            [dic setValue:content forKey:@"content"];
        }else{
            [dic setValue:message forKey:@"content"];
        }
        
        
        NSString* json =  messageAsDictionary(dic);
        CDVPluginResult* pluginResult = pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:json];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        
        
        
    }];
//    CDVPluginResult* pluginResult = nil;
//    NSString* myarg = [command.arguments objectAtIndex:0];
//
//    if (myarg != nil) {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
//    } else {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Arg was null"];
//    }
//    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}



- (void)loginAuth:(CDVInvokedUrlCommand*)command
{
    
    BOOL b = [command.arguments  objectAtIndex:0];
    
    [JVERIFICATIONService getAuthorizationWithController:self.viewController hide:b completion:^(NSDictionary *result) {
//        {"code":6000,"content":"ok","operator":"CM"}
//        result 字典 获取到token时key有operator、code、loginToken字段，获取不到token是key为code和content字段
//        NSLog(@"getAuthorizationWithController result:%d", b);
//        NSLog(@"getAuthorizationWithController result:%@", result);
        
        
        NSNumber* code = result[@"code"];
        NSString* operator = result[@"operator"];
        NSString* content = result[@"content"];
        NSString* loginToken = result[@"loginToken"];
        
        
        NSMutableDictionary *dic = [[NSMutableDictionary alloc] init];
        [dic setObject:[NSNumber numberWithInteger:[code intValue]] forKey:@"code"];
        
        if (nil != loginToken) {
            [dic setValue:loginToken forKey:@"content"];
            [dic setValue:operator forKey:@"operator"];
        }else{
            [dic setValue:content forKey:@"content"];
        }
        
        
        NSString* json =  messageAsDictionary(dic);
        CDVPluginResult* pluginResult = pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:json];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        
        
    }];
    
//    CDVPluginResult* pluginResult = nil;
//    NSString* myarg = [command.arguments objectAtIndex:0];
//
//    if (myarg != nil) {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
//    } else {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Arg was null"];
//    }
//    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}



- (void)dismissLoginAuth:(CDVInvokedUrlCommand*)command
{
    [JVERIFICATIONService dismissLoginController];
}



- (void)setCustomUIWithConfig:(CDVInvokedUrlCommand*)command
{
    
    NSString* json = [command.arguments objectAtIndex:0];
//    NSLog(@"setCustomUIWithConfig:%@", json);
    
    NSData *data = [json dataUsingEncoding:NSUTF8StringEncoding];
    NSDictionary *dict = APNativeJSONObject(data);
    
    
    
    
    /*移动*/
    JVMobileUIConfig *mobileUIConfig = [[JVMobileUIConfig alloc] init];
    /*联通*/
    JVUnicomUIConfig *unicomUIConfig = [[JVUnicomUIConfig alloc] init];
    /*电信*/
    JVTelecomUIConfig *telecomUIConfig = [[JVTelecomUIConfig alloc] init];
    
    NSArray* arrayKeys = [dict allKeys];
    for(NSString * key in arrayKeys){
        setJVUIConfig(key,dict,mobileUIConfig);
        setJVUIConfig(key,dict,unicomUIConfig);
        setJVUIConfig(key,dict,telecomUIConfig);
    }
    [JVERIFICATIONService customUIWithConfig:mobileUIConfig];
    [JVERIFICATIONService customUIWithConfig:unicomUIConfig];
    [JVERIFICATIONService customUIWithConfig:telecomUIConfig];
}

void setJVUIConfig(NSString* key ,NSDictionary *dict,
                   JVUIConfig *jvUIConfig){
    
    if ([key containsString:@"authPageBackgroundImage"]) {
        jvUIConfig.authPageBackgroundImage = [UIImage imageNamed:dict[key]];
    }else if([key containsString:@"navColor"]){
        jvUIConfig.navColor = UIColorFromRGBValue([dict[key] intValue]);
//        NSLog(@"jvUIConfig.navColor :%@", jvUIConfig.navColor );
    }else if([key containsString:@"barStyle"]){
        jvUIConfig.barStyle = [dict[key] intValue];//UIBarStyle;
        //NSLog(@"jvUIConfig.barStyle :%ld", jvUIConfig.barStyle);
    }else if([key containsString:@"navText"]){
//        jvUIConfig.navText= [[NSAttributedString alloc] initWithString:dict[key]];//NSAttributedString ;
        
        NSArray* textArry = dict[key];
        NSMutableAttributedString *navText = [[NSMutableAttributedString alloc] initWithString:textArry[0]];
//        [navText addAttribute:NSFontAttributeName
//                         value:[UIFont boldSystemFontOfSize:[textArry[1] floatValue]]
//                         range:[textArry[0] rangeOfString:textArry[0]]];
        [navText addAttribute:NSForegroundColorAttributeName
                          value:UIColorFromRGBValue([textArry[1] intValue])
                          range:[textArry[0] rangeOfString:textArry[0]]];
        
        jvUIConfig.navText = navText;
        
//        NSLog(@"jvUIConfig.navText :%@", jvUIConfig.navText);
    }else if([key containsString:@"navReturnImg"]){
        jvUIConfig.navReturnImg = [UIImage imageNamed:dict[key]];
    }else if([key containsString:@"navControl"]){
//        jvUIConfig.navControl = UIBarButtonItem;
    }else if([key containsString:@"navCustom"]){
        jvUIConfig.navCustom = [dict[key] boolValue];//BOOL
    }else if([key containsString:@"logoImg"]){
        jvUIConfig.logoImg = [UIImage imageNamed:dict[key]];
    }else if([key containsString:@"logoWidth"]){
        jvUIConfig.logoWidth =  [dict[key] floatValue];//CGFloat ;
    }else if([key containsString:@"logoHeight"]){
        jvUIConfig.logoHeight =[dict[key] floatValue];//CGFloat;
    }else if([key containsString:@"logoOffsetY"]){
        jvUIConfig.logoOffsetY =[dict[key] floatValue];//CGFloat;
    }else if([key containsString:@"logoHidden"]){
        jvUIConfig.logoHidden =[dict[key] boolValue];//BOOL
    }else if([key containsString:@"logBtnText"]){
        jvUIConfig.logBtnText = dict[key];
    }else if([key containsString:@"logBtnOffsetY"]){
        jvUIConfig.logBtnOffsetY =[dict[key] floatValue];//CGFloat;
    }else if([key containsString:@"logBtnTextColor"]){
        jvUIConfig.logBtnTextColor = UIColorFromRGBValue([dict[key] intValue]);//UIColor;
    }else if([key containsString:@"logBtnImgs"]){
        NSArray* imgPaths = dict[key];
        NSArray* logBtnImgs = [[NSArray alloc] initWithObjects:
                               [UIImage imageNamed:imgPaths[0]],
                               [UIImage imageNamed:imgPaths[1]],
                               [UIImage imageNamed:imgPaths[2]],
                               nil];
        jvUIConfig.logBtnImgs = logBtnImgs;
    }else if([key containsString:@"numberColor"]){
        jvUIConfig.numberColor = UIColorFromRGBValue([dict[key] intValue]);
    }else if([key containsString:@"numberSize"]){
        jvUIConfig.numberSize = [dict[key] floatValue];//CGFloat;
    }else if([key containsString:@"numFieldOffsetY"]){
        jvUIConfig.numFieldOffsetY =  [dict[key] floatValue];//CGFloat;
    }else if([key containsString:@"uncheckedImg"]){
        jvUIConfig.uncheckedImg = [UIImage imageNamed:dict[key]];
    }else if([key containsString:@"checkedImg"]){
        jvUIConfig.checkedImg = [UIImage imageNamed:dict[key]];
    }else if([key containsString:@"appPrivacyOne"]){
        jvUIConfig.appPrivacyOne = dict[key];
    }else if([key containsString:@"appPrivacyTwo"]){
        jvUIConfig.appPrivacyTwo = dict[key];
    }else if([key containsString:@"appPrivacyColor"]){
        NSArray* colors = dict[key];
        NSArray* appPrivacyColor = [[NSArray alloc] initWithObjects:
                                    UIColorFromRGBValue([colors[0] intValue]),
                                    UIColorFromRGBValue([colors[1] intValue]),
                                    nil];
        jvUIConfig.appPrivacyColor = appPrivacyColor;
    }else if([key containsString:@"privacyState"]){
        jvUIConfig.privacyState = [dict[key] boolValue];
    }else if([key containsString:@"privacyOffsetY"]){
        jvUIConfig.privacyOffsetY = [dict[key] floatValue];
    }else if([key containsString:@"sloganOffsetY"]){
        jvUIConfig.sloganOffsetY = [dict[key] floatValue];
    }else if([key containsString:@"sloganTextColor"]){
        jvUIConfig.sloganTextColor = UIColorFromRGBValue([dict[key] intValue]);
    }
    
}


NSData *APNativeJSONData(id obj) {
    NSError *error = nil;
    NSData *data = [NSJSONSerialization dataWithJSONObject:obj options:0 error:&error];
    if (error) {
        NSLog(@"%s trans obj to data with error: %@", __func__, error);
        return nil;
    }
    return data;
}

NSString *messageAsDictionary(NSDictionary * dic) {
    NSData *data = APNativeJSONData(dic);
    return [[NSString alloc]initWithData:data encoding:NSUTF8StringEncoding];
}

id APNativeJSONObject(NSData *data) {
    if (!data) {
        return nil;
    }
    
    NSError *error = nil;
    id retId = [NSJSONSerialization JSONObjectWithData:data options:0 error:&error];
    
    if (error) {
        NSLog(@"%s trans data to obj with error: %@", __func__, error);
        return nil;
    }
    
    return retId;
}

@end
