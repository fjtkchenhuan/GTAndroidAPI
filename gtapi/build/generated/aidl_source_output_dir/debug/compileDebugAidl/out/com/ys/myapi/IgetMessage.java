/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.ys.myapi;
// Declare any non-default types here with import statements

public interface IgetMessage extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.ys.myapi.IgetMessage
{
private static final java.lang.String DESCRIPTOR = "com.ys.myapi.IgetMessage";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ys.myapi.IgetMessage interface,
 * generating a proxy if needed.
 */
public static com.ys.myapi.IgetMessage asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.ys.myapi.IgetMessage))) {
return ((com.ys.myapi.IgetMessage)iin);
}
return new com.ys.myapi.IgetMessage.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
java.lang.String descriptor = DESCRIPTOR;
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(descriptor);
return true;
}
case TRANSACTION_getStaticIP:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getStaticIP();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getEthMode:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getEthMode();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getEthStatus:
{
data.enforceInterface(descriptor);
boolean _result = this.getEthStatus();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isAutoSyncTime:
{
data.enforceInterface(descriptor);
boolean _result = this.isAutoSyncTime();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getEthDns1:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getEthDns1();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getEthDns2:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getEthDns2();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getGateway:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getGateway();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getNetMask:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getNetMask();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getDhcpIpAddress:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getDhcpIpAddress();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_isSuccessScreenshot:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.isSuccessScreenshot(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isSetDefaultInputMethodSuccess:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.isSetDefaultInputMethodSuccess(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getDefaultInputMethod:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getDefaultInputMethod();
reply.writeNoException();
reply.writeString(_result);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.ys.myapi.IgetMessage
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String getStaticIP() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getStaticIP, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getEthMode() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getEthMode, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean getEthStatus() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getEthStatus, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean isAutoSyncTime() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isAutoSyncTime, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getEthDns1() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getEthDns1, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getEthDns2() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getEthDns2, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getGateway() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getGateway, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getNetMask() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getNetMask, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getDhcpIpAddress() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getDhcpIpAddress, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean isSuccessScreenshot(java.lang.String path) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(path);
mRemote.transact(Stub.TRANSACTION_isSuccessScreenshot, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean isSetDefaultInputMethodSuccess(java.lang.String defaultInputMethod) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(defaultInputMethod);
mRemote.transact(Stub.TRANSACTION_isSetDefaultInputMethodSuccess, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getDefaultInputMethod() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getDefaultInputMethod, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getStaticIP = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getEthMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getEthStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_isAutoSyncTime = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getEthDns1 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getEthDns2 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getGateway = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getNetMask = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getDhcpIpAddress = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_isSuccessScreenshot = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_isSetDefaultInputMethodSuccess = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_getDefaultInputMethod = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
}
public java.lang.String getStaticIP() throws android.os.RemoteException;
public java.lang.String getEthMode() throws android.os.RemoteException;
public boolean getEthStatus() throws android.os.RemoteException;
public boolean isAutoSyncTime() throws android.os.RemoteException;
public java.lang.String getEthDns1() throws android.os.RemoteException;
public java.lang.String getEthDns2() throws android.os.RemoteException;
public java.lang.String getGateway() throws android.os.RemoteException;
public java.lang.String getNetMask() throws android.os.RemoteException;
public java.lang.String getDhcpIpAddress() throws android.os.RemoteException;
public boolean isSuccessScreenshot(java.lang.String path) throws android.os.RemoteException;
public boolean isSetDefaultInputMethodSuccess(java.lang.String defaultInputMethod) throws android.os.RemoteException;
public java.lang.String getDefaultInputMethod() throws android.os.RemoteException;
}
