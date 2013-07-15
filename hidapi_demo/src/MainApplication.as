/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package
{
    import com.hidapi.HIDFactory;
    import com.hidapi.IHIDManager;

    import flash.events.MouseEvent;

    import hid.logging.TextTarget;

    import mx.collections.ArrayCollection;
    import mx.controls.Spacer;
    import mx.core.FlexGlobals;
    import mx.graphics.SolidColorStroke;

    import renderer.DeviceRenderer;

    import spark.components.BorderContainer;

    import spark.components.Button;

    import spark.components.DataGroup;
    import spark.components.HGroup;
    import spark.components.Label;
    import spark.components.Scroller;

    import spark.components.TextArea;
    import spark.components.VGroup;
    import spark.layouts.VerticalLayout;

    CONFIG::desktop
    {
        import flash.desktop.NativeApplication;
        import flash.display.Screen;
    }
    import flash.events.Event;
    import flash.geom.Rectangle;
    import flash.utils.clearInterval;
    import flash.utils.setInterval;

    import hid.logging.ConsoleTarget;

    import mx.events.FlexEvent;

    import mx.logging.ILogger;
    import mx.logging.Log;

    public class MainApplication extends BaseApplication
    {
        private static const log:ILogger = Log.getLogger("MainApplication");

        private static const SHOW_LOGS:String = "Show Logs";
        private static const SHOW_DEVICES:String = "Show Devices";

        private var content:VGroup;
        private var btnSwitch:Button;
        private var dgrDevices:DataGroup;
        private var txtLogOutput:TextArea;
        private var dgrContainer:BorderContainer;

        private var timer:uint;

        private var manager:IHIDManager;

        public function MainApplication()
        {
            var verticalLayout:VerticalLayout = new VerticalLayout();
            verticalLayout.gap = 8;
            verticalLayout.horizontalAlign = "center";
            layout = verticalLayout;

            txtLogOutput = new TextArea();
            txtLogOutput.percentHeight = 100;
            txtLogOutput.percentWidth = 100;
            txtLogOutput.editable = false;

            Log.addTarget(new ConsoleTarget());
            Log.addTarget(new TextTarget(txtLogOutput));


            addEventListener(FlexEvent.INITIALIZE, initializeApplication, false, 0, true);
            addEventListener(Event.CLOSE, closeApplication, false, 0, true);
        }

        private function initializeApplication(event:FlexEvent):void
        {
            CONFIG::desktop
            {
                this.width = 500;
                this.height = 500;
                var visibleBounds:Rectangle = Screen.mainScreen.visibleBounds;
                this.move(visibleBounds.width / 2 - this.width / 2, visibleBounds.height / 2 - this.height / 2);
                this.title = "DemoHID";
            }
        }

        private function getVersion():String
        {
            var version:String = "mobile";
            CONFIG::desktop
            {
                var descriptor:XML = NativeApplication.nativeApplication.applicationDescriptor;
                var ns:Namespace = descriptor.namespaceDeclarations()[0];
                version = descriptor.ns::versionNumber;
            }
            return version;
        }

        override protected function createChildren():void
        {
            super.createChildren();

            content = new VGroup();
            content.percentHeight = 100;
            content.percentWidth = 100;
            content.paddingBottom = 5;
            content.paddingTop = 5;
            content.paddingLeft = 5;
            content.paddingRight = 5;

            var header:HGroup = new HGroup();
            header.percentWidth = 100;
            header.height = 50;
            header.horizontalAlign = "left";
            header.verticalAlign = "middle";


            btnSwitch = new Button();
            btnSwitch.label = SHOW_LOGS;
            btnSwitch.height = 50;
            btnSwitch.addEventListener(MouseEvent.CLICK, onSwitchClick, false, 0, true);
            header.addElement(btnSwitch);

            var spacer:Spacer = new Spacer();
            spacer.height = 25;
            spacer.percentWidth = 100;
            header.addElement(spacer);


            var version:Label = new Label();
            version.text = "version " + getVersion();
            header.addElement(version);


            dgrContainer = new BorderContainer();
            dgrContainer.percentWidth = 100;
            dgrContainer.percentHeight = 100;
            dgrContainer.borderStroke = new SolidColorStroke(0xCCCCCC, 1);

            var scroller:Scroller = new Scroller();
            scroller.percentWidth = 100;
            scroller.percentHeight = 100;
            dgrContainer.addElement(scroller);

            dgrDevices = new DataGroup();
            var verticalLayout:VerticalLayout = new VerticalLayout();
            verticalLayout.gap = 1;
            dgrDevices.layout = verticalLayout;
            dgrDevices.itemRenderer = new DeviceRenderer();

            scroller.viewport = dgrDevices;

            content.addElement(header);
            content.addElement(dgrContainer);
            this.addElement(content);

            updateStatus();
            startDeviceDetection();
        }

        private function onSwitchClick(event:MouseEvent):void
        {
            if(dgrContainer.parent != null)
            {
                btnSwitch.label = SHOW_DEVICES;
                content.removeElement(dgrContainer);
                content.addElement(txtLogOutput);
            }
            else
            {
                btnSwitch.label = SHOW_LOGS;
                content.removeElement(txtLogOutput);
                content.addElement(dgrContainer);
            }
        }

        private function closeApplication(event:Event):void
        {
            stopDeviceDetection();
        }

        private function stopDeviceDetection():void
        {
            if (timer != 0)
            {
                clearInterval(timer);
                timer = 0;
            }
        }

        private function startDeviceDetection():void
        {
            manager = HIDFactory.getHIDManager();

            updateStatus();

            timer = setInterval(updateStatus, 5000);
        }

        private function updateStatus():void
        {
            try
            {
                var deviceList:Array = manager.getDeviceList();
                dgrDevices.dataProvider = new ArrayCollection(deviceList);
                log.info("devices found: {0}", deviceList.length);
            }
            catch(e:Error)
            {
                log.error(e.message);
            }
        }
    }
}
