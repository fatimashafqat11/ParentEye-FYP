package com.example.parenteye;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import static com.example.screentime.MainActivity.blockedList;

public class MyAccessibilityService extends AccessibilityService {
    private Context context;
    @RequiresApi (api = Build.VERSION_CODES.Q)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
//        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED){
//            if (accessibilityEvent.getPackageName().toString().equals(blockedList.get(accessibilityEvent.getPackageName().toString()))) {
//                Intent serviceIntent = new Intent(MyAccessibilityService.this,AfterEvent.class);
//                System.out.println("OK if condition + " + blockedList.get(accessibilityEvent.getPackageName().toString()));
//                serviceIntent.putExtra("package_name", accessibilityEvent.getPackageName().toString());
//                serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(serviceIntent);
//            }
//        }

        final int eventType = accessibilityEvent.getEventType();
        blockedList.get(accessibilityEvent.getPackageName().toString());
        if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (accessibilityEvent.getPackageName().equals("com.google.android.youtube")) {
                System.out.println("Youtube");

                Toast toast = Toast.makeText(getApplicationContext(), "Youtube", Toast.LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(MyAccessibilityService.this,AfterEvent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }


    }
    @Override
    public void onInterrupt() {

    }
}
