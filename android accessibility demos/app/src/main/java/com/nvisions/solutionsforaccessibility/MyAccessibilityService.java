package com.nvisions.solutionsforaccessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.nvisions.solutionsforaccessibility.korailTalk.KorailTalkWithAccessibilityActivity;

public class MyAccessibilityService extends AccessibilityService {


    @Override
    public boolean onKeyEvent(KeyEvent event) {
        Log.d("plusapps", "onKeyEvent");
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        if (action == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                KorailTalkWithAccessibilityActivity.handleVolumeUp();
                Log.d("plusapps", "KeyUp");
            } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                KorailTalkWithAccessibilityActivity.handleVolumeDown();
                Log.d("plusapps", "KeyDown");
            }
            return true;
        } else {
            return super.onKeyEvent(event);
        }
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.d("plusapps", "onAccessibilityEvent");
        AccessibilityNodeInfo interactedNodeInfo =
                accessibilityEvent.getSource();


        if (interactedNodeInfo == null) {
            return;
        }

        Log.d("plusapps", "text: " + interactedNodeInfo.getText());

    }

    @Override
    public void onInterrupt() {

    }

    /**
     * Passes information to AccessibilityServiceInfo.
     */
    @Override
    public void onServiceConnected() {
        Log.v("apple", "on Service Connected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.packageNames = new String[] { "com.nvisions.solutionsforaccessibility" };
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.notificationTimeout = 100;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        setServiceInfo(info);

    }// end onServiceConnected
}