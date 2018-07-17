package com.zjwz.cmm.mydemo.scan;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.zjwz.cmm.mydemo.BaseActivity;
import com.zjwz.cmm.mydemo.MainActivity;
import com.zjwz.cmm.mydemo.R;
import com.zjwz.cmm.mydemo.utils.CommonUtil;
import com.zjwz.cmm.mydemo.view.BaseTitlebar;
import com.zjwz.cmm.mydemo.zxing.activity.CaptureActivity;
import com.zjwz.cmm.mydemo.zxing.encoding.EncodingHandler;

/**
 * @author hc101
 */
public class ScanActivity extends BaseActivity  {

    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;
    private int CAMERA_CODE = 0X103;

    private TextView qrCodeText;
    private TextView text;
    private ImageView qrCode;

    @Override
    public void setContentView() {
        super.setContentView();
        setContentView(R.layout.activity_scan);
    }

    @Override
    public void initView() {
        super.initView();

        BaseTitlebar titlebar = (BaseTitlebar) findViewById(R.id.title_bar);
        titlebar.setTitle("扫描");
        titlebar.setLeftTextButton("返回", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btScan = (Button) findViewById(R.id.bt_scan);
        qrCodeText = (TextView) findViewById(R.id.qr_code_text);
        text = (TextView) findViewById(R.id.text);
        Button createOrCode = (Button) findViewById(R.id.create_or_code);

        qrCode = (ImageView) findViewById(R.id.qr_code);

        btScan.setOnClickListener(this);
        createOrCode.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_scan:
                //打开二维码扫描界面
                if(CommonUtil.isCameraCanUse()){
                    Intent intent = new Intent(this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }else{
                    Toast.makeText(this,"请打开此应用的摄像头权限！",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.create_or_code:
                try {
                    //获取输入的文本信息
                    String str = text.getText().toString().trim();
                    if(str != null && !"".equals(str.trim())){
                        //根据输入的文本生成对应的二维码并且显示出来
                        Bitmap mBitmap = EncodingHandler.createQRCode(text.getText().toString(), 500);
                        if(mBitmap != null){
                            Toast.makeText(this,"二维码生成成功！",Toast.LENGTH_SHORT).show();
                            qrCode.setImageBitmap(mBitmap);
                        }
                    }else{
                        Toast.makeText(this,"文本信息不能为空！",Toast.LENGTH_SHORT).show();
                    }
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调   //RESULT_OK = -1
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            //将扫描出的信息显示出来
            qrCodeText.setText(scanResult);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initView();
                Intent intent = this.getIntent();
                int reqCode = intent.getIntExtra("int", 0);
            } else {
                Toast.makeText(ScanActivity.this,"请开启相机权限",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
