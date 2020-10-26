package com.nvisions.solutionsforaccessibility.WebView;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

import java.util.List;

public class MyAccessibilityService extends android.accessibilityservice.AccessibilityService {
    private static final String TAG = "plusapps";

    // 이벤트가 발생할때마다 실행되는 부분
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
// Log.e(TAG, "Catch Event : " + event.toString());
        Log.e(TAG, "Catch Event Package Name : " + event.getPackageName());
        Log.e(TAG, "Catch Event TEXT : " + event.getText());
        Log.e(TAG, "Catch Event ContentDescription : " + event.getContentDescription());
        Log.e(TAG, "Catch Event getSource : " + event.getSource());

    }

    // 접근성 권한을 가지고, 연결이 되면 호출되는 함수
    public void onServiceConnected() {
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();

        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK; // 전체 이벤트 가져오기
        info.feedbackType = AccessibilityServiceInfo.DEFAULT | AccessibilityServiceInfo.FEEDBACK_HAPTIC;
        info.notificationTimeout = 100; // millisecond

        setServiceInfo(info);
    }

    @Override
    public void onInterrupt() {
        // TODO Auto-generated method stub
        Log.e("TEST", "OnInterrupt");
    }
}