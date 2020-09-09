package com.example.tiku53_57.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku53_57.R;
import com.example.tiku53_57.bean.Hjjc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        title.setText("主界面");
        change.setImageResource(R.mipmap.change);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Class myClass = null;
                switch (menuItem.getItemId()) {
                    case R.id.rzcx:
                        myClass = Z_RZCXActivity.class;
                        break;
                    case R.id.yhgl:
                        myClass = Z_YHGLActivity.class;
                        break;
                    case R.id.gjcx:
                        myClass = Z_GJCXActivity.class;
                        break;
                    case R.id.ssgj:
                        myClass = Z_SSJTActivity.class;
                        break;
                    case R.id.hjjc:
                        myClass = Z_HJJCActivity.class;
                        break;
                }
                if (myClass != null) {
                    startActivity(new Intent(MainActivity.this, myClass));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        drawerLayout.openDrawer(GravityCompat.START);
    }
}
