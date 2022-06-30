package com.example.parenteye;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static HashMap<String,String> blockedList;
    private EditText edtPkgName;
    private String appName;
    private Button btnBlockApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtPkgName = findViewById(R.id.mainPkgName);
        appName = edtPkgName.getText().toString();
        blockedList = new HashMap<>();
        btnBlockApp = findViewById(R.id.btnBlockApp);

        boolean enabled = isAccessibilityServiceEnabled(this, MyAccessibilityService.class);
        if (!enabled){
            Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Service Enabled", Toast.LENGTH_SHORT).show();
        }




    }

    public void addApp(View view){
        if (edtPkgName.getText().toString().equals(""))
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        else {
            blockedList.put(edtPkgName.getText().toString(), edtPkgName.getText().toString());
            Toast.makeText(this, edtPkgName.getText().toString() + "added to blocked list", Toast.LENGTH_SHORT).show();
            edtPkgName.setText("");
        }

    }
    public void removeApp(View view){
        if (edtPkgName.getText().toString().equals(""))
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        else {
            blockedList.remove(edtPkgName.getText().toString());
            Toast.makeText(this, edtPkgName.getText().toString() + "removed from blocked list", Toast.LENGTH_SHORT).show();
            edtPkgName.setText("");
        }

    }

    public static boolean isAccessibilityServiceEnabled(Context context, Class<? extends AccessibilityService> service) {
        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        assert am != null;
        List<AccessibilityServiceInfo> enabledServices = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);

        for (AccessibilityServiceInfo enabledService : enabledServices) {
            ServiceInfo enabledServiceInfo = enabledService.getResolveInfo().serviceInfo;
            if (enabledServiceInfo.packageName.equals(context.getPackageName()) && enabledServiceInfo.name.equals(service.getName())){
                Toast.makeText(context, "True", Toast.LENGTH_SHORT).show();
                return true;
            }

        }

        Toast.makeText(context, "False", Toast.LENGTH_SHORT).show();
        return false;
    }

}


