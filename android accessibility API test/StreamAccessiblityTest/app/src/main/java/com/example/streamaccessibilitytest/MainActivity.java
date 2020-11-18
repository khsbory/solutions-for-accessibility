package com.example.streamaccessibilitytest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.accessibility.AccessibilityManager;

import java.util.List;

import static android.media.AudioManager.ADJUST_RAISE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isAccessibilityServiceEnabled()) {
            setAccessibilityServicePermissions();
        }
    }

    // 접근성 권한이 있는지 없는지 확인하는 부분
    // 있으면 true, 없으면 false
    public boolean isAccessibilityServiceEnabled() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);

        // getEnabledAccessibilityServiceList는 현재 접근성 권한을 가진 리스트를 가져오게 된다
        List<AccessibilityServiceInfo> list = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.DEFAULT);

        for (int i = 0; i < list.size(); i++) {
            AccessibilityServiceInfo info = list.get(i);

            // 접근성 권한을 가진 앱의 패키지 네임과 패키지 네임이 같으면 현재앱이 접근성 권한을 가지고 있다고 판단함
            if (info.getResolveInfo().serviceInfo.packageName.equals(getApplication().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    // 접근성 설정화면으로 넘겨주는 부분
    public void setAccessibilityServicePermissions() {
        AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
        gsDialog.setTitle("일림");
        gsDialog.setMessage("데모를 보려면 접근성 서비스를 활성화해야 합니다.");
        gsDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // 설정화면으로 보내는 부분
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                return;
            }
        }).create().show();
    }

    public void onButtonClicked(View view) {
        AudioManager audioManager =
                (AudioManager) getSystemService(AUDIO_SERVICE);
        audioManager.adjustStreamVolume(AudioManager.STREAM_ACCESSIBILITY,
                ADJUST_RAISE, 0);
    }
}