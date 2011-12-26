1. License information: LICENSE-MIT.txt
2. Requirements: Java runtime, Apache Ant, MinGW (Windows),
   MinGW-Utils (Windows), XCode (Mac OS X)
3. Building extension for both Windows and Mac OS does not make sense,
   because native installer must be used anyway. That is why extension is
   build separately for each platform.
4. Output:
            extension: application\extensions\hidapi.ane
            library:   application\lib\hidapi.swc
            installer: DemoHIDInstaller.exe
5. Air SDK Header hidapi\hidapi_native\windows\FlashRuntimeExtensions.h
   must be updated manually to be used with MinGW, see:
    #ifdef __MINGW32__
        #include <stdint.h>
    #else
        #ifdef WIN32
              typedef unsigned __int32	uint32_t;
              typedef unsigned __int8		uint8_t;
              typedef          __int32	int32_t;
        #else
              #include <stdint.h>
        #endif
    #endif

   Compatible library must be generated:  hidapi\hidapi_native\windows\readme.txt
6. See application\extensions\unpacked\readme.txt for debug info.
7. HIDAPI info http://www.signal11.us/oss/hidapi/
