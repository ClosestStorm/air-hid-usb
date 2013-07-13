/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package com.hidapi;

import android.content.Context;
import android.hardware.usb.*;
import android.util.Log;
import com.adobe.fre.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HidEnumerateFunction implements FREFunction
{
    public static final String FUNCTION_NAME = "hid_enumerateA";

    private static final String COM_HIDAPI_HID_DEVICE_INFO = "com.hidapi.HidDeviceInfo";

    private static final String PROPERTY_MANUFACTURER_STRING = "manufacturer_string";  //string
    private static final String PROPERTY_PRODUCT_STRING = "product_string";            //string
    private static final String PROPERTY_PATH = "path";                                //uint
    private static final String PROPERTY_PRODUCTID = "productId";                      //uint
    private static final String PROPERTY_VENDORID = "vendorId";                        //uint
    private static final String PROPERTY_SERIAL_NUMBER = "serial_number";              //string
    private static final String PROPERTY_RELEASE_NUMBER = "release_number";            //uint
    private static final String PROPERTY_USAGE_PAGE = "usage_page";                    //uint
    private static final String PROPERTY_USAGE = "usage";                              //uint
    private static final String PROPERTY_INTERFACE_NUMBER = "interface_number";        //int

    public FREObject call(FREContext context, FREObject[] passedArgs)
    {
        HidContext hidContext = (HidContext) context;

        Log.i("HidEnumerateFunction", "api level: " + android.os.Build.VERSION.SDK_INT);

        UsbManager usbService = (UsbManager) hidContext.getActivity().getSystemService(Context.USB_SERVICE);

        FREArray freArray = (FREArray)passedArgs[0];
        try
        {
            if (usbService != null)
            {
                Log.i("HidEnumerateFunction", "got UsbManager");

                HashMap<String, UsbDevice> deviceList = usbService.getDeviceList();

                List<FREObject> freObjects = new LinkedList<FREObject>();

                for (Map.Entry<String, UsbDevice> stringUsbDeviceEntry : deviceList.entrySet())
                {
                    Log.i("HidEnumerateFunction", "device: " + stringUsbDeviceEntry.getKey() + " - " + stringUsbDeviceEntry.getValue().getVendorId());
                    FREObject hidDeviceInfo = createInfo(stringUsbDeviceEntry.getValue());
                    if(hidDeviceInfo != null)
                        freObjects.add(hidDeviceInfo);
                }

                freArray.setLength(freObjects.size());
                int i = 0;
                for (FREObject freObject : freObjects)
                {
                    freArray.setObjectAt(i, freObject);
                    i++;
                }
            }
            else
            {
                Log.w("HidEnumerateFunction", "Failed to get UsbManager system service");
            }
        }
        catch (Exception e)
        {
            Log.e("HidEnumerateFunction", "error", e);
        }

        return freArray;
    }

    private FREObject createInfo(UsbDevice usbDevice) throws FREWrongThreadException, FRENoSuchNameException, FRETypeMismatchException, FREASErrorException, FREInvalidObjectException, FREReadOnlyException
    {
        if (UsbConstants.USB_CLASS_HID == usbDevice.getDeviceClass())
        {
            FREObject hidDeviceInfo = FREObject.newObject(COM_HIDAPI_HID_DEVICE_INFO, new FREObject[]{});
            hidDeviceInfo.setProperty(PROPERTY_PRODUCTID, FREObject.newObject(usbDevice.getProductId()));
            hidDeviceInfo.setProperty(PROPERTY_VENDORID, FREObject.newObject(usbDevice.getVendorId()));

            int interfaceCount = usbDevice.getInterfaceCount();

            for (int i = 0; i < interfaceCount; i++)
            {
                UsbInterface anInterface = usbDevice.getInterface(i);
            }

            return hidDeviceInfo;
        }
        return null;
    }
}
