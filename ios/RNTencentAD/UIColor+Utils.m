//
//  UIColor+Utils.m
//  RNTencentAD
//
//  Created by Dean Ji on 16/8/11.
//  Copyright © 2016年 Dean Ji. All rights reserved.
//

#import "UIColor+Utils.h"

@implementation UIColor(Utils)

static inline NSUInteger hexStrToInt(NSString *str) {
    uint32_t result = 0;
    sscanf([str UTF8String], "%X", &result);
    return result;
}

static BOOL hexStrToRGBA(NSString *str,
                         CGFloat *r, CGFloat *g, CGFloat *b, CGFloat *a) {
    
    NSCharacterSet *set = [NSCharacterSet whitespaceAndNewlineCharacterSet];
    
    str = [[str stringByTrimmingCharactersInSet:set] uppercaseString];
    if ([str hasPrefix:@"#"]) {
        str = [str substringFromIndex:1];
    } else if ([str hasPrefix:@"0X"]) {
        str = [str substringFromIndex:2];
    }
    
    NSUInteger length = [str length];
    //         RGB            ARGB          RRGGBB        AARRGGBB
    if (length != 3 && length != 4 && length != 6 && length != 8) {
        return NO;
    }
    
    //RGB,ARGB,RRGGBB,AARRGGBB
    if (length < 5) {
        
        if (length == 4){
            *a = hexStrToInt([str substringWithRange:NSMakeRange(0, 1)]) / 255.0f;
            str = [str substringFromIndex:1];
        }
        else *a = 1;
        
        *r = hexStrToInt([str substringWithRange:NSMakeRange(0, 1)]) / 255.0f;
        *g = hexStrToInt([str substringWithRange:NSMakeRange(1, 1)]) / 255.0f;
        *b = hexStrToInt([str substringWithRange:NSMakeRange(2, 1)]) / 255.0f;
        
        
    } else {
        
        if (length == 8){
            *a = hexStrToInt([str substringWithRange:NSMakeRange(0, 2)]) / 255.0f;
            str = [str substringFromIndex:2];
        }
        else *a = 1;
        
        *r = hexStrToInt([str substringWithRange:NSMakeRange(0, 2)]) / 255.0f;
        *g = hexStrToInt([str substringWithRange:NSMakeRange(2, 2)]) / 255.0f;
        *b = hexStrToInt([str substringWithRange:NSMakeRange(4, 2)]) / 255.0f;
    }
    return YES;
}

+ (instancetype)colorWithHexString:(NSString *)hexStr {
    CGFloat r, g, b, a;
    if (hexStrToRGBA(hexStr, &r, &g, &b, &a)) {
        return [UIColor colorWithRed:r green:g blue:b alpha:a];
    }
    return nil;
}

@end
