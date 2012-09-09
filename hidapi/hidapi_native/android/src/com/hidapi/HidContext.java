/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package com.hidapi;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class HidContext extends FREContext
{
    public void dispose()
    {
        Log.i("HidContext", "dispose");
    }

    public Map<String, FREFunction> getFunctions()
    {
        Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();

        functionMap.put(HidEnumerateFunction.FUNCTION_NAME, new HidEnumerateFunction());
        functionMap.put(HidCloseFunction.FUNCTION_NAME, new HidCloseFunction());
        functionMap.put(HidGetFeatureReportFunction.FUNCTION_NAME, new HidGetFeatureReportFunction());
        functionMap.put(HidSendFeatureReportFunction.FUNCTION_NAME, new HidSendFeatureReportFunction());
        functionMap.put(HidOpenPathFunction.FUNCTION_NAME, new HidOpenPathFunction());
        functionMap.put(HidErrorFunction.FUNCTION_NAME, new HidErrorFunction());
        functionMap.put(HidGetIndexedStringFunction.FUNCTION_NAME, new HidGetIndexedStringFunction());
        functionMap.put(HidGetManufacturerStringFunction.FUNCTION_NAME, new HidGetManufacturerStringFunction());
        functionMap.put(HidGetProductStringFunction.FUNCTION_NAME, new HidGetProductStringFunction());
        functionMap.put(HidGetSerialNumberStringFunction.FUNCTION_NAME, new HidGetSerialNumberStringFunction());
        functionMap.put(HidOpenFunction.FUNCTION_NAME, new HidOpenFunction());
        functionMap.put(HidReadFunction.FUNCTION_NAME, new HidReadFunction());
        functionMap.put(HidReadTimeoutFunction.FUNCTION_NAME, new HidReadTimeoutFunction());
        functionMap.put(HidSetNonBlockingFunction.FUNCTION_NAME, new HidSetNonBlockingFunction());
        functionMap.put(HidWriteFunction.FUNCTION_NAME, new HidWriteFunction());

        return functionMap;
    }
}
