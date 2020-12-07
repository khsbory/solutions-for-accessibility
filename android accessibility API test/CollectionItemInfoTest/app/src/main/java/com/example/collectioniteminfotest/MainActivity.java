package com.example.collectioniteminfotest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<String> numberName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isAccessibilityServiceEnabled()) {
            setAccessibilityServicePermissions();
        }
        initList();
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


    private void initList() {
        numberName = new ArrayList<>(Arrays.asList("Four...", "Nine... ", "Seven...", "Six...", "Ten...", "Three...", "Two..."));

        // Calling the RecyclerView
        recyclerView = findViewById(R.id.horizontalList);
        recyclerView.setHasFixedSize(true);
        // The number of Columns
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyListDecoration());
        adapter = new MyAdapter(MainActivity.this, numberName, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
        recyclerView.setAdapter(adapter);


        ViewCompat.setAccessibilityDelegate(recyclerView, new AccessibilityDelegateCompat() {
            @Override
            public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onPopulateAccessibilityEvent(host, event);
                // We call the super implementation to populate its text for the
                // event. Then we add our text not present in a super class.
                // Very often you only need to add the text for the custom view.
//                CharSequence text = getText();
//                if (!TextUtils.isEmpty(text)) {
//                    event.getText().add(text);
//                }
            }
            @Override
            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onInitializeAccessibilityEvent(host, event);
                // We call the super implementation to let super classes
                // set appropriate event properties. Then we add the new property
                // (checked) which is not supported by a super class.
                //event.setChecked(isChecked());
            }
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onInitializeAccessibilityNodeInfo(View host,
                                                          AccessibilityNodeInfoCompat info) {

                //AccessibilityService를 구현하고 토크백을 켜야 이 메소드가 호출됨
                super.onInitializeAccessibilityNodeInfo(host, info);

                Log.d("plusapps", "info: " + info.toString());
                final int rowsCount = 3;
                final int selectionMode = AccessibilityNodeInfo.CollectionInfo.SELECTION_MODE_SINGLE;
                final AccessibilityNodeInfoCompat.CollectionInfoCompat collectionInfo = AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(rowsCount, 1, false, selectionMode);
                Log.d("plusapps", "collectionInfo: " + collectionInfo.getColumnCount() + ", " + collectionInfo.getRowCount() + ", " + collectionInfo.getSelectionMode());
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo = AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(rowsCount,1, 1, 1, false);
                Log.d("plusapps", "collectionItemInfo: " + collectionItemInfo.getColumnIndex() + ", " + collectionItemInfo.getColumnSpan() +  ", " + collectionItemInfo.getRowIndex());

                info.setCollectionInfo(collectionInfo);
                if (rowsCount > 0) {
                    Log.d("plusapps",  "addAction");
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_TO_POSITION);
                }


            }
        });

    }
}