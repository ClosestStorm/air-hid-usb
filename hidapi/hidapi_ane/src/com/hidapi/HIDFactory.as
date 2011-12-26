/*
 ~ Copyright: (c) 2011. Turtsevitch Alexander
 ~
 ~ Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */
package com.hidapi
{
/**
     * HID extension entry point
     * @see com.hidapi.IHIDManager
     */
    public class HIDFactory
    {
        private static var _instance:HIDManager;

        /**
         * Creates IHIDManager instance used to operate with HID devices.
         * @return instance of IHIDManager
         */
        public static function getHIDManager():IHIDManager
        {
            return instance;
        }


        private static function get instance():HIDManager
        {
            if(_instance == null)
            {
                _instance = new HIDManager();
            }
            return _instance;
        }
    }
}
