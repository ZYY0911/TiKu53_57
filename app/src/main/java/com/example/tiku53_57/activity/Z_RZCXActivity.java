package com.example.tiku53_57.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tiku53_57.R;
import com.example.tiku53_57.adapter.RZCXAdapter;
import com.example.tiku53_57.bean.Rzcx;
import com.example.tiku53_57.net.VolleyLo;
import com.example.tiku53_57.net.VolleyTo;
import com.example.tiku53_57.util.MyListView;
import com.example.tiku53_57.util.MyUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-28 at 15:34
 */
public class Z_RZCXActivity extends BaseActivity {
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.list_view)
    MyListView listView;
    private List<Rzcx> rzcxes;
    private RZCXAdapter adapter;
    private int size = 4;

    @Override
    public int getLayout() {
        return R.layout.rzcx_layout;
    }

    @Override
    public String setTitle() {
        return "日志查询";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        rzcxes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            setVolley(1);
        }
    }

    private void setVolley(final int lx) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Rzcx rzcx = new Gson().fromJson(jsonObject.toString(),Rzcx.class);
                        rzcx.setRESULT(MyUtils.SimpDate("yyyy.MM.dd HH:mm:ss",new Date()));
                        rzcxes.add(0,rzcx);
                        if (lx==1){
                            if (rzcxes.size()==4){
                                adapter = new RZCXAdapter(Z_RZCXActivity.this,rzcxes);
                                listView.setAdapter(adapter);
                                listView.setOnRefreshListener(new MyListView.OnRefreshListener() {
                                    @Override
                                    public void refresh() {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                for (int i = 0; i < 2; i++) {
                                                    setVolley(2);
                                                }
                                            }
                                        },2000);
                                    }
                                });
                            }
                        }else {
                            if (rzcxes.size() == size+2){
                                listView.finishRefresh();
                                adapter.notifyDataSetChanged();
                                size += 2;
                                Toast.makeText(Z_RZCXActivity.this, "更新两条数据", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
}
