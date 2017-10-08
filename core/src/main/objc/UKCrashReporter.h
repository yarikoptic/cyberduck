//
//  UKCrashReporter.h
//  NiftyFeatures
//
//  Created by Uli Kusterer on Sat Feb 04 2006.
//  Copyright (c) 2006 M. Uli Kusterer. All rights reserved.
//

// -----------------------------------------------------------------------------
//	Headers:
// -----------------------------------------------------------------------------

#import <Foundation/Foundation.h>

// -----------------------------------------------------------------------------
//	Classes:
// -----------------------------------------------------------------------------

@interface UKCrashReporter : NSObject
{
	NSURLConnection*				connection;
}

-(id)		init;

// -----------------------------------------------------------------------------
//	Prototypes:
// -----------------------------------------------------------------------------

/* Call this sometime during startup (e.g. in applicationDidLaunch) and it'll
	check for a new crash log and offer to the user to send it.

	The crash log is sent to a CGI script whose URL you specify in the
	UKUpdateChecker.strings file. If you want, you can even have different
	URLs for different locales that way, in case a crash is caused by an error
	in a localized file.
*/
- (void) checkForCrash:(NSString *)url;

@end