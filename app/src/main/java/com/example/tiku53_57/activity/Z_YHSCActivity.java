package com.example.tiku53_57.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tiku53_57.AppClient;
import com.example.tiku53_57.R;
import com.example.tiku53_57.adapter.YHSCAdapter;
import com.example.tiku53_57.bean.YHGL;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-30 at 22:24
 */
public class Z_YHSCActivity extends BaseActivity {
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.layout_null)
    RelativeLayout layoutNull;
    private AppClient appClient;
    private List<YHGL> yhgls;
    private YHSCAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.yhsc_layout;
    }

    @Override
    public String setTitle() {
        return "用户收藏";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        appClient = (AppClient) getApplication();
        yhgls = appClient.getYhgls();
        adapter = new YHSCAdapter(this,yhgls);
        listView.setAdapter(adapter);
        listView.setEmptyView(layoutNull);
    }
}
