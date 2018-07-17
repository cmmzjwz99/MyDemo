package com.zjwz.cmm.mydemo.utils;

import android.hardware.Camera;

/**
 * @className: CommonUtil
 * @classDescription: 通用工具类
 * @createTime: 2017年2月10日
 */
public class CommonUtil {

    public static boolean isCameraCanUse() {
            boolean canUse = true;
            Camera mCamera = null;
            try {
                mCamera = Camera.open();
            } catch (Exception e) {
                canUse = false;
            }
            if (canUse) {
                if (mCamera != null)
                    mCamera.release();
                mCamera = null;
            }
            return canUse;
    }
}
