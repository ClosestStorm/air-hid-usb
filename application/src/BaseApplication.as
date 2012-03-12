/*
 * Copyright: (c) 2012. Turtsevich Alexander
 *
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.html
 */

package
{
    CONFIG::desktop
    {
        import spark.components.WindowedApplication;
    }

    CONFIG::mobile
    {
        import spark.components.Application;
    }

    CONFIG::desktop
    public class BaseApplication extends WindowedApplication
    {
        public function BaseApplication()
        {
            super();
            showStatusBar = false;
        }
    }

    CONFIG::mobile
    public class BaseApplication extends Application
    {
        public function BaseApplication()
        {
            super();
        }
    }
}
