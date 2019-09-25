package com.ys.gtapi;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.ys.gtapi.Utils.StorageUtils;
import com.ys.gtapi.Utils.Utils;
import com.ys.gtapi.Utils.VersionUtils;
import com.ys.myapi.IgetMessage;

import java.io.File;
import java.io.IOException;


public class MyManager {
    private static final String TAG = "MyManager";
    private static MyManager myManager;

    private Context mContext;

    private DisplayManager mDisplayManager;

    private MyManager(Context context) {
        mContext = context;
    }

    public static synchronized MyManager getInstance(Context context) {
        if (myManager == null) {
            myManager = new MyManager(context);
        }
        return myManager;
    }

    public void bindAIDLService(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.AIDLService");
        intent.setComponent(new ComponentName("com.ys.ys_receiver", "com.ys.ys_receiver.AIDLService"));
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unBindAIDLService(Context context) {
        context.unbindService(serviceConnection);
    }

    private IgetMessage igetMessage;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            igetMessage = IgetMessage.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            igetMessage = null;
        }
    };

    //获取目前API平台-版本-日期信息，如果API发生修改就需要修改此处
    public String getApiVersion() {///ok
        return "V1.0_20190924";
    }

    //获取目前设备的型号
    public String getAndroidModle() {//ok
        return VersionUtils.getAndroidModle();
    }

    //获取目前设备的android系统的版本
    public String getAndroidVersion() {  //ok
        return Build.VERSION.SDK;
    }

    //获取设备的硬件内存大小容量。
    public String getRunningMemory() {//ok
        return StorageUtils.getRealMeoSize();
    }

    //获取设备的硬件内部存储大小容量
    public String getInternalStorageMemory() {//ok
        return StorageUtils.getRealSizeOfNand();
    }

    //获取设备的固件SDK版本。
    public String getFirmwareVersion() { // ok
        return "1.0";
    }

    //获取设备的固件内核版本。
    public String getKernelVersion() { // ok
        return VersionUtils.getKernelVersion();
    }

    //获取设备的固件系统版本和编译日期。
    public String getAndroidDisplay() {  //ok
        return VersionUtils.getSystemVersionInfo();
    }

    //获取设备CPU型号
    public String getCPUType() {
        return VersionUtils.getCpuInfo()[0];
    }

    //获取固件编译的时间
    public String getFirmwareDate() {  // ok
        return VersionUtils.getFirmwareDate();
    }

    //关机
    public void shutdown() {
        Intent intent = new Intent(Constant.SHUTDOWN_ACTION);
        intent.setPackage("com.ys.ys_receiver");
        mContext.sendBroadcast(intent);
    }

    //重启
    public void reboot() {
        Intent intent = new Intent(Constant.REBOOT_ACTION);
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        mContext.sendBroadcast(intent);
    }

    public boolean getNavBarHideState() {
        return Utils.getValueFromProp(Constant.PROP_STATUSBAR_STATE_LU).equals("0");
    }

    //设置显示或隐藏导航
    public void hideNavBar(boolean hide) {  // ok
        Intent intent = new Intent();
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        if (!hide) {
            intent.setAction("android.action.adtv.showNavigationBar");
            mContext.sendBroadcast(intent);
        } else {
            intent.setAction("android.action.adtv.hideNavigationBar");
            mContext.sendBroadcast(intent);
        }
    }

    public boolean isSlideShowNavBarOpen() {
        return Utils.getValueFromProp(Constant.PROP_SWIPE_STATUSBAR_LU).equals("1");
    }

    public void setSlideShowNavBar(Context context, boolean flag) {
        if (flag)
            Utils.setValueToProp(Constant.PROP_SWIPE_STATUSBAR_LU, "1");
        else
            Utils.setValueToProp(Constant.PROP_SWIPE_STATUSBAR_LU, "0");
    }

    public boolean isSlideShowNotificationBarOpen() {
        return Utils.getValueFromProp(Constant.PROP_SWIPE_NOTIFIBAR_LU).equals("0");
    }

    public void setSlideShowNotificationBar(Context context, boolean flag) {
        if (flag)
            Utils.setValueToProp(Constant.PROP_SWIPE_NOTIFIBAR_LU, "0");
        else
            Utils.setValueToProp(Constant.PROP_SWIPE_NOTIFIBAR_LU, "1");
    }

    public boolean takeScreenshot(String path) { // ok
        boolean flag = false;
        if (igetMessage != null) {
            try {
                flag = igetMessage.isSuccessScreenshot(path);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public int getDisplayWidth(Context context) {
        WindowManager manager = ((Activity) context).getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public int getDisplayHeight(Context context) {
        WindowManager manager = ((Activity) context).getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public void changeScreenLight(Context context, int value) {
        Intent intent = new Intent("com.ys.set_screen_bright");
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        intent.putExtra("brightValue", value);
        context.sendBroadcast(intent);
    }

    public void turnOnBackLight() {
        try {
            Utils.writeIntFile("1", "/sys/class/backlight/backlight/bl_power");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnOffBackLight() {
        try {
            Utils.writeIntFile("0", "/sys/class/backlight/backlight/bl_power");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isBackLightOn() {
        return "1".equals(Utils.readGpioPG("/sys/class/backlight/backlight/bl_power"));
    }

    public int getSystemBrightness() {
        int systemBrightness;
        int value = 0;
        try {
            systemBrightness = Settings.System.getInt(mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            value = systemBrightness * 100 / 255;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void turnOnHDMI() {
        try {
            Utils.writeIntFile("1", "/sys/class/hdmi/hdmi/status");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnOffHDMI() {
        try {
            Utils.writeIntFile("0", "/sys/class/hdmi/hdmi/status");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isHDMIConnect() {
        return "1".equals(Utils.readGpioPG("/sys/class/hdmi/hdmi/status"));
    }

    public void rotateScreen(String degree) {
        Utils.setValueToProp("persist.sys.displayrot", degree);
        reboot();
    }

    public void rebootRecovery() {
        Intent intent = new Intent("com.ys.recovery_system");
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        mContext.sendBroadcast(intent);
    }

    public boolean silentInstallApk(String apkPath) {
        return Utils.execRoot("pm install -r " + apkPath);
    }

    public boolean getEthStatus() {
        boolean flag = false;
        if (igetMessage != null) {
            try {
                flag = igetMessage.getEthStatus();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public String getEthMode() {
        String ethMode = "";
        if (igetMessage != null) {
            try {
                ethMode = igetMessage.getEthMode();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return ethMode;
    }

    public String getEthMacAddress() {
        return Utils.getEthMAC();
    }

    public String getStaticEthIPAddress() {
        String staticEthIP = "";
        if (igetMessage != null) {
            try {
                staticEthIP = igetMessage.getStaticIP();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return staticEthIP;
    }

    public String getDhcpIpAddress() {//ok
        String dhcpEthIP = "";
        if (igetMessage != null) {
            try {
                dhcpEthIP = igetMessage.getDhcpIpAddress();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return dhcpEthIP;
    }

    public void setStaticEthIPAddress(String ip, String gateWay, String mask, String dns1, String dns2) {//ok
        Intent intent = new Intent(Constant.ETH_STATIC_IP_ACTION);
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        intent.putExtra("useStaticIP",1);
        intent.putExtra(Constant.ETH_SET_IP, ip);//IP地址
        intent.putExtra(Constant.ETH_SET_GATEWAY, gateWay);//网关
        intent.putExtra(Constant.ETH_SET_MASK, mask);//子网掩码
        intent.putExtra(Constant.ETH_DNS1, dns1);//dns1
        intent.putExtra(Constant.ETH_DNS2, dns2);//dns2
        mContext.sendBroadcast(intent);
    }

    public void ethEnabled(boolean enable) {
        Intent intent = new Intent(Constant.SET_ETH_ENABLE_ACTION);
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        intent.putExtra(Constant.ETH_MODE, enable);
        mContext.sendBroadcast(intent);
    }

    public void setDhcpIpAddress() {
        Intent intent = new Intent("com.ys.set_dhcp");
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        intent.putExtra("useStaticIP",0);
        mContext.sendBroadcast(intent);
    }

    public String getGateway() {
        String gateway = "";
        if (igetMessage != null) {
            try {
                gateway = igetMessage.getGateway();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return gateway;
    }

    public String getNetMask() {
        String mask = "";
        if (igetMessage != null) {
            try {
                mask = igetMessage.getNetMask();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return mask;
    }

    public String getEthDns1() {
        String dns1 = "";
        if (igetMessage != null) {
            try {
                dns1 = igetMessage.getEthDns1();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return dns1;
    }

    public String getEthDns2() {
        String dns2 = "";
        if (igetMessage != null) {
            try {
                dns2 = igetMessage.getEthDns2();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return dns2;
    }

    public void setTime(int year, int month, int day, int hour, int minute,int sec) {
        Intent intent = new Intent(Constant.UPDATE_TIME_ACTION);
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        intent.putExtra(Constant.UPDATE_TIME_KEY,Utils.getTimeMills(year, month, day, hour, minute,sec));
        mContext.sendBroadcast(intent);
    }

    public int getCurrentNetType() {
        int realNetType = Utils.getNetWorkType(mContext);
        if (realNetType == 9)
            return Constant.NET_TYPE_ETH;
        if (realNetType == 1)
            return Constant.NET_TYPE_WIFI;
        if (realNetType == 0)
            return Constant.NET_TYPE_MOBILE;
        return Constant.NETWORK_UNKNOW;
    }

    public int getScreenNumber() {
        mDisplayManager = (DisplayManager) mContext.getSystemService(
                Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays();
        return displays.length;
    }

    public void switchAutoTime(boolean open) {
        Intent intent = new Intent("com.ys.switch_auto_set_time");
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        intent.putExtra("switch_auto_time",open);
        mContext.sendBroadcast(intent);
    }

    public boolean isAutoSyncTime() {
        boolean flag = false;
        if (igetMessage != null) {
            try {
                flag = igetMessage.isAutoSyncTime();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public void setDormantInterval(Context context,long time) {
        Intent intent = new Intent(Constant.DORMANT_INTERVAL);
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        intent.putExtra("time_interval",time);
        context.sendBroadcast(intent);
    }

    public boolean isSetDefaultInputMethodSuccess(String defaultInputMethod) {//ok
        boolean isSuccess = false;
        if (igetMessage != null) {
            try {
                isSuccess = igetMessage.isSetDefaultInputMethodSuccess(defaultInputMethod);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public String getDefaultInputMethod(){
        String defaultInputMethod = "";
        if (igetMessage != null) {
            try {
                defaultInputMethod = igetMessage.getDefaultInputMethod();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return defaultInputMethod;
    }

    public void setLanguage(String language,String country) {
        Intent intent = new Intent("com.ys.set_language");
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        intent.putExtra("language",language);
        intent.putExtra("country",country);
        mContext.sendBroadcast(intent);
    }

    public int getCPUTemperature() {
        ///sys/class/thermal/thermal_zone0/temp
        String s = Utils.readGpioPG("/sys/class/thermal/thermal_zone0/temp");
        int temp = Integer.parseInt(s.substring(0,5));
        return (int) (temp/1000);
    }

    public void setADBOpen(boolean open) {
        if (open) {
            Utils.setValueToProp("persist.sys.usb.otg.mode","1");
        } else {
            Utils.setValueToProp("persist.sys.usb.otg.mode","0");
        }
    }

    public void replaceBootanimation(String path) {
        String[] commands = new String[6];
        commands[0] = "mount -o rw,remount -t ext4 /system";
        commands[1] = "rm -rf system/media/bootanimation.zip";
        commands[2] = "cp  "+ path + " system/media/bootanimation.zip";
        commands[3] = "chmod 644 system/media/bootanimation.zip";
        commands[4] = "sync";
        commands[5] = "mount -o ro,remount -t ext4 /system";
        for (int i = 0;i < commands.length;i ++)
            Utils.execRoot(commands[i]);
        reboot();
    }

    public void setDefaultLauncher(String packageAndClassName) {
        Intent intent = new Intent("com.ys.setDefaultLauncher");
        intent.setPackage(Constant.YSRECEIVER_PACKAGE_NAME);
        intent.putExtra("defaultLauncher",packageAndClassName);
        mContext.sendBroadcast(intent);
    }

}
