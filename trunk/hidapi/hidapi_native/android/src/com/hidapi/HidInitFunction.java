/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package com.hidapi;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class HidInitFunction implements FREFunction
{
    public FREObject call(FREContext context, FREObject[] passedArgs)
    {
        HidContext hidContext = (HidContext) context;

        return null;
    }
}
