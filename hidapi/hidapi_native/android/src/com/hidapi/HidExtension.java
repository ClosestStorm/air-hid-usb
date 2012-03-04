/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package com.hidapi;

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
        // TODO Auto-generated method stub

    }

    public void initialize()
    {
        // TODO Auto-generated method stub

    }

}