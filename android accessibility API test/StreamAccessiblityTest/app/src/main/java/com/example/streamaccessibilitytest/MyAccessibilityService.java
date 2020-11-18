package com.example.streamaccessibilitytest;

import android.accessibilityservice.AccessibilityService;
import android.media.AudioManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import static android.media.AudioManager.*;

public class MyAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeInfo interactedNodeInfo =
                accessibilityEvent.getSource();

        if (interactedNodeInfo == null) {
            return;
        }

        Log.d("plusapps", "text: " + interactedNodeInfo.getText());
//        if (interactedNodeInfo.getText() != null && interactedNodeInfo.getText().toString().toLowerCase().equals("increase volume")) {
//            Log.d("plusapps", "Increase volume");
//
//            AudioManager audioManager =
//                    (AudioManager) getSystemService(AUDIO_SERVICE);
//            audioManager.adjustStreamVolume(AudioManager.STREAM_ACCESSIBILITY,
//                    ADJUST_RAISE, 0);
//        }
    }

    @Override
    public void onInterrupt() {

    }
}