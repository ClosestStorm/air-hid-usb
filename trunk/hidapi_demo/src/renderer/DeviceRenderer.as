/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package renderer
{
    import mx.core.IFactory;

    import spark.components.Label;
    import spark.components.supportClasses.ItemRenderer;

    public class DeviceRenderer extends ItemRenderer implements IFactory
    {
        private var lblText:Label;
        
        private var dataChanged:Boolean;
        
        public function DeviceRenderer()
        {
            height = 20;
        }

        override protected function createChildren():void
        {
            super.createChildren();
            lblText = new Label();
            lblText.percentWidth = 100;
            addElement(lblText);
        }


        override protected function commitProperties():void
        {
            super.commitProperties();
            if(dataChanged)
            {
                lblText.text = labelFunction(data, "productId");
                dataChanged = false;
            }
        }

        public function labelFunction(item:Object, property:String):String
        {
            var result:String = "";
            if(item && item.hasOwnProperty(property))
            {
                result = "0x" + uint(item[property]).toString(16).toUpperCase();
            }
            else
            {
                result = "0x0";
            }
            return result;
        }

        override public function set data(value:Object):void
        {
            super.data = value;
            dataChanged = true;
            invalidateProperties();
        }

        public function newInstance():*
        {
            return new DeviceRenderer();
        }
    }
}
