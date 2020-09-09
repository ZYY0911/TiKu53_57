package com.example.tiku53_57;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tiku53_57.bean.YHGL;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-07-15 at 10:19 ：）
 */
public class AppClient extends Application {
    public static SharedPreferences preferences;
    public static RequestQueue requestQueue;
    public static final String IP = "ip";
    public static final String PORT = "port";
    private static String userType = "用户";
    private List<YHGL> yhgls = new ArrayList<>();

    public List<YHGL> getYhgls() {
        return yhgls;
    }

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        AppClient.userType = userType;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        preferences = getSharedPreferences("P", 0);
        requestQueue = Volley.newRequestQueue(this);
    }


    public static void add(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }

    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void finaAll() {
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
