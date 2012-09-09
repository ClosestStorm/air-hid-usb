/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package com.hidapi;

import android.hardware.usb.*;
import com.adobe.fre.*;

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

//        UsbDevice usbDevice;
//        UsbDeviceConnection usbDeviceConnection = new UsbManager().openDevice(usbDevice);
//        usbDeviceConnection.

        return null;
    }

    private FREObject createInfo(UsbDevice usbDevice) throws FREWrongThreadException, FRENoSuchNameException, FRETypeMismatchException, FREASErrorException, FREInvalidObjectException, FREReadOnlyException
    {
        if (UsbConstants.USB_CLASS_HID == usbDevice.getDeviceClass())
        {
            FREObject hidDeviceInfo = FREObject.newObject(COM_HIDAPI_HID_DEVICE_INFO, new FREObject[]{});
            hidDeviceInfo.setProperty(PROPERTY_PRODUCTID, FREObject.newObject(usbDevice.getProductId()));
            hidDeviceInfo.setProperty(PROPERTY_VENDORID, FREObject.newObject(usbDevice.getVendorId()));

//            usbDevice.

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
