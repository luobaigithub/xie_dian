package com.example.xie_dian;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterMain;

public class MainActivity extends FlutterActivity {
    private static final String BLUETOOTH_CHANNEL="samples.flutter.io/bluetooth";

    private BluetoothManager bluetoothManager = null;
    private BluetoothAdapter bluetoothAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FlutterMain.startInitialization(this);
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(new FlutterEngine(this));
        
        new MethodChannel(getFlutterEngine().getDartExecutor().getBinaryMessenger(),BLUETOOTH_CHANNEL).setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                        if(methodCall.method.equals("openBuleTooth")){
                            if(supportBuleTooth()){
                                openBuleTooth();
                                result.success("已同步");
                            }else{
                                result.error("设备不支持蓝牙",null,null);
                            }
                        }
                    }
                }
        );
    }

    //是否支持蓝牙

    private boolean supportBuleTooth(){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bluetoothManager=(BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bluetoothAdapter=bluetoothManager.getAdapter();
        }
        if (bluetoothAdapter==null){    //不支持蓝牙
            return false;
        }
        return true;
    }

    //打开蓝牙
    private void openBuleTooth(){
        //判断蓝牙是否开启
        Intent enabler=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enabler,1);
    }

    //判断蓝牙是否已经开启
    private boolean disabled(){
        if(bluetoothAdapter.isEnabled()){
            return true;
        }
        return false;
    }

}
