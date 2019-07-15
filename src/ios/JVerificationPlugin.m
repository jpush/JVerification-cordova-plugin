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

@implementation JVerificationPlugin


- (void)init:(CDVInvokedUrlCommand*)command
{
    NSString *plistPath = [[NSBundle mainBundle]pathForResource:@"JVerificationConfig" ofType:@"plist"];
    NSMutableDictionary *dataDic = [[NSMutableDictionary alloc]initWithContentsOfFile:plistPath];
    NSLog(@"%@",dataDic);//直接打印数据
    
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
        NSLog(@"getToken result:%@", result);
        
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
        NSLog(@"verifyNumber result:%@", result);
        
        
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
        NSLog(@"preLogin result:%@", result);
        
        
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
        NSLog(@"getAuthorizationWithController result:%@", result);
        
        
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
    NSLog(@"setCustomUIWithConfig:%@", json);
    
    NSData *data = [json dataUsingEncoding:NSUTF8StringEncoding];
    NSDictionary *dict = APNativeJSONObject(data);
    
    NSLog(@"setCustomUIWithConfig dict:%@", dict);
    
    
    /*移动*/
    JVMobileUIConfig *mobileUIConfig = [[JVMobileUIConfig alloc] init];
    /*联通*/
    JVUnicomUIConfig *unicomUIConfig = [[JVUnicomUIConfig alloc] init];
    /*电信*/
    JVTelecomUIConfig *telecomUIConfig = [[JVTelecomUIConfig alloc] init];
    
    NSArray* arrayKeys = [dict allKeys];
    for(NSString * key in arrayKeys){
        
    }
//    CDVPluginResult* pluginResult = nil;
//    if (myarg != nil) {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
//    } else {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Arg was null"];
//    }
//    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

void setJVUIConfig(NSString* key ,NSDictionary *dict,
                   JVMobileUIConfig *mobileUIConfig ,JVUnicomUIConfig *unicomUIConfig,JVTelecomUIConfig *telecomUIConfig  ){
    
    
    if ([key containsString:@""]) {
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
    }else if([key containsString:@""]){
        
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
