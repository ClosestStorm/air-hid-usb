Building Windows extension with with MinGW

1. Setup MinGW: http://www.mingw.org/wiki/InstallationHOWTOforMinGW
2. Download MinGW Utils: http://sourceforge.net/projects/mingw/files/MinGW/Extension/mingw-utils/
3. Create def-file using reimp from utils: reimp -d FlashRuntimeExtensions.lib
4. Rename generated def file("Adobe Air" or similar) to FlashRuntimeExtensions.def
5. Create .a library from FlashRuntimeExtensions.lib(see Air SDK lib folder): dlltool -d FlashRuntimeExtensions.def -l FlashRuntimeExtensions.dll.a
6. Fix FlashRuntimeExtensions.h
#ifdef __MINGW32__
    #include <stdint.h>
#else
    #ifdef WIN32
          typedef unsigned __int32	uint32_t;
          typedef unsigned __int8	uint8_t;
          typedef          __int32	int32_t;
    #else
          #include <stdint.h>
    #endif
#endif
7. Now you can compile dll:
    g++ -Wall -shared Extension.cpp -o Extension.dll -L. -lFlashRuntimeExtensions -static-libgcc -static-libstdc++
8. Download http://sourceforge.net/projects/unxutils extract and add to PATH
9. Now you can use: mingw32-make clean && mingw32-make all
10. Read DevelopingActionScriptExtensionsForAdobeAIR.pdf

Example:

-------------------------------------
Extension.h:
-------------------------------------

#include "FlashRuntimeExtensions.h"

#ifdef __cplusplus
extern "C" {
#endif

__declspec(dllexport) void initializer(void** extData, FREContextInitializer* ctxInitializer, FREContextFinalizer* ctxFinalizer);
__declspec(dllexport) void finalizer(void* extData);

#ifdef __cplusplus
}
#endif

-------------------------------------
Extension.cpp:
-------------------------------------
#include <malloc.h>
#include "Extension.h"

#ifdef __cplusplus
extern "C" {
#endif
    //will return Boolean true to AS3 side
    FREObject test(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[])
    {
        bool value = true;
        FREObject resultObject;
        FRENewObjectFromBool(value, &resultObject);
        return resultObject;
    }

    /*
    * usage:
    * var context:ExtensionContext = ExtensionContext.createExtensionContext("AnyContext", null);
    * var result:Boolean = context.call('test') as Boolean;
    */
    void contextInitializer(void* extData, const uint8_t* ctxType, FREContext ctx, uint32_t* numFunctions, const FRENamedFunction** functions)
    {
        *numFunctions = 1;

        FRENamedFunction* func = (FRENamedFunction*) malloc(sizeof(FRENamedFunction) * (*numFunctions));

        func[0].name = (const uint8_t*) "test";
        func[0].functionData = NULL;
        func[0].function = &test;

        *functions = func;
    }

    void contextFinalizer(FREContext ctx)
    {
        return;
    }

    void initializer(void** extData, FREContextInitializer* ctxInitializer, FREContextFinalizer* ctxFinalizer)
    {
        *ctxInitializer = &contextInitializer;
        *ctxFinalizer = &contextFinalizer;
    }

    //The runtime calls this function when it unloads an extension. However, the runtime does not guarantee that it will
    //unload the extension or call FREFinalizer().
    //it seems to be never called atleast on windows
    void finalizer(void* extData)
    {
        return;
    }
#ifdef __cplusplus
}
#endif
