issue 3 Android and iOS support: http://code.google.com/p/air-hid-usb/issues/detail?id=3

Actually using libusb is not good idea, because it requires rooted android device,
recompiled libusb and many more. android has native usb support since API 12 (Android 3.1.x).

"Android 3.1 adds broad platform support for a variety of USB-connected peripherals and
accessories. Users can attach many types of input devices (keyboards, mice, game controllers)
and digital cameras. Applications can build on the platform’s USB support to extend
connectivity to almost any type of USB device."

The right way to go is to mimic hidapi with some restrictions and create pure java extension.