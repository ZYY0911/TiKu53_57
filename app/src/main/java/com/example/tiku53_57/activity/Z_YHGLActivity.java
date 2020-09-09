package com.example.tiku53_57.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku53_57.AppClient;
import com.example.tiku53_57.R;
import com.example.tiku53_57.adapter.YHGLAdapter;
import com.example.tiku53_57.bean.YHGL;
import com.example.tiku53_57.net.VolleyLo;
import com.example.tiku53_57.net.VolleyTo;
import com.example.tiku53_57.util.MyUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-28 at 15:35
 */
public class Z_YHGLActivity extends BaseActivity {
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.list_view)
    ListView listView;
    private List<YHGL> yhgls;
    private YHGLAdapter adapter;
    private AppClient appClient;


    @Override
    public int getLayout() {
        return R.layout.yhgl_layout;
    }

    @Override
    public String setTitle() {
        return "用户管理";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        appClient = (AppClient) getApplication();
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_login")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        setVolley_Root((List<YHGL>) new Gson().fromJson(jsonObject.optJSONArray(MyUtils.ROWS).toString()
                                , new TypeToken<List<YHGL>>() {
                                }.getType()));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setVolley_Root(final List<YHGL> yhglList) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_roots")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        yhgls = new Gson().fromJson(jsonObject.optJSONArray(MyUtils.ROWS).toString()
                                , new TypeToken<List<YHGL>>() {
                                }.getType());
                        for (int i = 0; i < yhgls.size(); i++) {
                            YHGL yhgl = yhgls.get(i);
                            for (int j = 0; j < yhglList.size(); j++) {
                                YHGL yhgl1 = yhglList.get(j);
                                if (yhgl.getId() == yhgl1.getId()) {
                                    yhgl.setUsername(yhgl1.getUsername());
                                    yhgl.setRoot(yhgl1.getRoot());
                                    yhgl.setPassword(yhgl1.getPassword());
                                }
                            }
                            yhgls.set(i, yhgl);
                        }
                        setListView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setListView() {
        if (adapter == null) {
            adapter = new YHGLAdapter(this, yhgls, this.getWindowManager().getDefaultDisplay().getWidth());
            listView.setAdapter(adapter);
            adapter.setClickItem(new YHGLAdapter.OnClickItem() {
                @Override
                public void click(int position, int lx) {
                    Log.i("aaa", "click: " + yhgls.get(position).isIs() + "---" + position);
                    if (lx == 1) {
                        YHGL yhgl = yhgls.get(position);
                        if (yhgl.isSc()) {
                            List<YHGL> sc = appClient.getYhgls();
                            for (int i = 0; i < sc.size(); i++) {
                                YHGL yhgl1 = sc.get(i);
                                if (yhgl1.getUsername().equals(yhgl.getUsername())) {
                                    sc.remove(i);
                                }
                            }
                        } else {
                            appClient.getYhgls().add(yhgl);
                        }
                        yhgl.setIs(false);
                        yhgl.setSc(!yhgl.isSc());
                        yhgls.set(position, yhgl);
                    } else if (lx == 2) {
                        yhgls.remove(position);
                    } else if (lx == 3) {
                            for (int i = 0; i < yhgls.size(); i++) {
                                YHGL yhgl = yhgls.get(i);
                                if (i == position) {
                                    yhgl.setIs(true);
                                } else {
                                    yhgl.setIs(false);
                                }
                                yhgls.set(i, yhgl);
                            }
                    } else {
                        startActivity(new Intent(Z_YHGLActivity.this, Z_YHSCActivity.class));
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
