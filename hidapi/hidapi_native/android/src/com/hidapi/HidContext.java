/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package com.hidapi;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class HidContext extends FREContext
{
    public void dispose()
    {
        //todo
    }

    public Map<String, FREFunction> getFunctions()
    {
        Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();

        functionMap.put("init", new HidInitFunction());

        return functionMap;
    }
}
