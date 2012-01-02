/*
 ~ Copyright: (c) 2011. Turtsevitch Alexander
 ~
 ~ Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */
package com.hidapi
{
    import flash.events.EventDispatcher;
    import mx.logging.ILogger;
    import mx.logging.Log;

    /**
     * @private
     */
    internal class HIDManager extends EventDispatcher implements IHIDManager
    {
        private static const log:ILogger = Log.getLogger("com.hidapi.HIDManager");

        private var hid:Hid;

        public function HIDManager()
        {
            this.hid = new Hid("HIDManager");
        }

        /**
         * @inheritDoc
         * @see com.hidapi.IHIDManager
         */
        public function getDevice(productId:uint, vendorId:uint, serial_number:String = null):IHIDDevice
        {
            var hid:Hid = new Hid();
            return new HIDDevice(hid, null, productId, vendorId, serial_number, this);
        }

        /**
         * @inheritDoc
         */
        public function getDeviceByPath(path:String):IHIDDevice
        {
            var hid:Hid = new Hid();
            return new HIDDevice(hid, path, 0, 0, null, this);
        }

        /**
         * @inheritDoc
         */
        public function getDeviceList(productId:uint = 0x0, vendorId:uint = 0x0):Array
        {
            var result:Array = [];
            var status:Boolean = hid.hid_enumerate(result, productId, vendorId);
            return status ? result : null;
        }

        /**
         * @inheritDoc
         */
        public function getDeviceListError():String
        {
            return hid.hid_error();
        }
    }
}
