package com.nvisions.solutionsforaccessibility.WebView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.nvisions.solutionsforaccessibility.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
//혹시 힌트를 얻을까해서 Accessibility Service를 보았으나 소용없음
//        if(!isAccessServiceEnabled()) {
//            setAccessibilityPermissions();
//        }
    }

    public boolean isAccessServiceEnabled()
    {
        String prefString = Settings.Secure.getString(getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);//
        return prefString!= null && prefString.contains(getPackageName() + "/" + MyAccessibilityService.class.getName());
    }

// 접근성 설정화면으로 넘겨주는 부분
    public void setAccessibilityPermissions() {
        AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
        gsDialog.setTitle("접근성 서비스");
        gsDialog.setMessage("데모를 보려면 접근성 서비스를 활성화해야 합니다.");
        gsDialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // 설정화면으로 보내는 부분
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                return;
            }
        }).create().show();
    }

    public void launchWebViewWithoutAccessibilityActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), WebViewWithoutAccessibilityActivity.class);
        startActivity(intent);
    }

    public void launchWebViewWithAccessibilityActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), WebViewWithAccessibilityActivity.class);
        startActivity(intent);
    }
}
