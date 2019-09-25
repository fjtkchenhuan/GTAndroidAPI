package com.ys.gtapi.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.ys.gtapi.Constant;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Calendar;

public class Utils {
    public static void setValueToProp(String key, String val) {
        Class<?> classType;
        try {
            classType = Class.forName("android.os.SystemProperties");
            Method method = classType.getDeclaredMethod("set", new Class[]{String.class, String.class});
            method.invoke(classType, new Object[]{key, val});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValueFromProp(String key) {
        String value = "";
        try {
            Class<?> classType = Class.forName("android.os.SystemProperties");
            Method getMethod = classType.getDeclaredMethod("get", new Class<?>[]{String.class});
            value = (String) getMethod.invoke(classType, new Object[]{key});
        } catch (Exception e) {
        }
        return value;
    }

    public static boolean execRoot(String command) {
        Log.d("execFor7", "command = " + command);
        boolean result = false;
        DataOutputStream dataOutputStream = null;
        BufferedReader errorStream = null;
        try {
            // 申请su权限
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            // 执行pm install命令
            String s = command + "\n";
            dataOutputStream.write(s.getBytes(Charset.forName("utf-8")));
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String msg = "";
            String line;
            // 读取命令的执行结果
            while ((line = errorStream.readLine()) != null) {
                msg += line;
            }
            Log.d("execFor7", "execFor7 msg is " + msg);
            // 如果执行结果中包含Failure字样就认为是安装失败，否则就认为安装成功
            if (!msg.contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            Log.e("execFor7", e.getMessage(), e);
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (errorStream != null) {
                    errorStream.close();
                }
            } catch (IOException e) {
                Log.e("TAG", e.getMessage(), e);
            }
        }
        return result;
    }

    public static void writeIntFile(String str, String path) throws IOException, InterruptedException {
        File file = new File(path);
        file.setExecutable(true);
        file.setReadable(true);//设置可读权限
        file.setWritable(true);//设置可写权限
        if (str.equals("0")) {
            execRoot("busybox echo 0 > " + path);
        } else {
            execRoot("busybox echo 1 > " + path);
        }
    }

    public static String readGpioPG(String path) {
        String str = "";
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            fileInputStream.read(buffer);
            fileInputStream.close();
            str = new String(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getEthMAC() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/eth0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (macSerial != null && !macSerial.equals("") && macSerial.length() == 17) {
            return macSerial.toUpperCase();
        }
        return "";
    }

    public static String getEthernet(Context context) {
        String value = "";
        try {
            Class<?> classType = Class.forName("android.net.EthernetManager");
            Method getMethod = classType.getDeclaredMethod("getIpAddress");
            @SuppressLint("WrongConstant") Object obj = (context.getSystemService("ethernet"));
            value = (String) getMethod.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static long getTimeMills(int year, int month, int day, int hour, int minute, int sec) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, sec);
        return calendar.getTimeInMillis();
    }

    public static int getNetWorkType(Context context) {
        int netWorkType = -100;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            netWorkType = networkInfo.getType();
        }
        return netWorkType;
    }

}
