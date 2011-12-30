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
   Compatible library must be generated:  hidapi\hidapi_native\windows\readme.txt
6. See application\extensions\unpacked\readme.txt for debug info.
7. HIDAPI info http://www.signal11.us/oss/hidapi/

