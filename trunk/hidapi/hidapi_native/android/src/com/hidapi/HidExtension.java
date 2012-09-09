/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package com.hidapi;

import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class HidExtension implements FREExtension
{
    public FREContext createContext(String contextType)
    {
        return new HidContext();
    }

    public void dispose()
    {
        Log.i("HidContext", "dispose");

    }

    public void initialize()
    {
        Log.i("HidContext", "initialize");
    }

}