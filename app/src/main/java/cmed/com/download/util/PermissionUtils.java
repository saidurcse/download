package cmed.com.download.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {

    String[] permission_list = new String[]{"WRITE_EXTERNAL_STORAGE", "INTERNET"};

    public static boolean CheckPermission(Context context, String item) {
        return (ContextCompat.checkSelfPermission(context, "android.permission." + item) == PackageManager.PERMISSION_GRANTED);
    }

    public void SetPermission(Activity activity, String [] permissions) {
        List<String> AskPermission = new ArrayList<String>();

        for(int i = 0; i < permissions.length; i++) {
            if(CheckPermission(activity.getApplicationContext(), permissions[i]) == false) {
                AskPermission.add("android.permission." + permissions[i]);
            }
        }

        if(AskPermission.size() > 0) {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(activity, AskPermission.toArray(new String[AskPermission.size()]), REQUEST_CODE_ASK_PERMISSIONS);
        }
    }

    public static void SetAllPermission(Activity activity) {
        PermissionUtils permissionManager = new PermissionUtils();
        permissionManager.SetPermission(activity, permissionManager.permission_list);
    }

    public static boolean HasStoragePermission(Context context){
        return  CheckPermission(context,"WRITE_EXTERNAL_STORAGE") && CheckPermission(context,"READ_EXTERNAL_STORAGE");
    }

    public static boolean HasInternetPermission(Context context){
        return  CheckPermission(context,"INTERNET");
    }
}