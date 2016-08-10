//
//  SplashBottomView.m
//  example
//
//  Created by Dean Ji on 8/9/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import "SplashBottomView.h"

@implementation SplashBottomView

- (instancetype)initWithFrame:(CGRect)frame{
  
    frame = CGRectMake(0, 0, [[UIScreen mainScreen] bounds].size.width, 120);
    if (self = [super initWithFrame:frame]) {
      self.backgroundColor = [UIColor whiteColor];
      self.contentMode = UIViewContentModeCenter;
      self.image = [UIImage imageNamed:@"SplashLogo"];
    }
    return self;
}
@end
