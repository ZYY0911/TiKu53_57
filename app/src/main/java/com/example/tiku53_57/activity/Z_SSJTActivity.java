package com.example.tiku53_57.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku53_57.R;
import com.example.tiku53_57.adapter.BusAdapter;
import com.example.tiku53_57.bean.BusHistory;
import com.example.tiku53_57.bean.Ssjt;
import com.example.tiku53_57.net.VolleyLo;
import com.example.tiku53_57.net.VolleyTo;
import com.example.tiku53_57.util.MyUtils;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-29 at 20:15
 */
public class Z_SSJTActivity extends BaseActivity {
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.et_bus)
    EditText etBus;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.history_list)
    ListView historyList;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    private Ssjt ssjt;
    private List<BusHistory> busHistories;
    private BaseAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.ssjt_layout;
    }

    @Override
    public String setTitle() {
        return "实时交通";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        title1.setText("地图");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter == null) {
            busHistories = LitePal.findAll(BusHistory.class);
            Collections.reverse(busHistories);
            adapter = new BusAdapter(this, busHistories);
            historyList.setAdapter(adapter);
        } else {
            busHistories.clear();
            busHistories.addAll(LitePal.findAll(BusHistory.class));
            Collections.reverse(busHistories);
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.title1, R.id.tv_search, R.id.tv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title1:
                startActivity(new Intent(this, Z_LXDTActivity.class));
                break;
            case R.id.tv_search:
                int busId = Integer.parseInt(etBus.getText().toString());
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("get_bus_data")
                        .setJsonObject("UserName", "user1")
                        .setJsonObject("Busid", busId)
                        .setDialog(this)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                ssjt = new Gson().fromJson(jsonObject.toString(), Ssjt.class);
                                if (ssjt.getROWS_DETAIL().size() == 0) {
                                    MyUtils.showDialog(Z_SSJTActivity.this, "没有查询到此线路");
                                } else {
                                    Intent intent = new Intent(Z_SSJTActivity.this, Z_SSJTJGActivity.class);
                                    intent.putExtra("info", ssjt);
                                    startActivity(intent);
                                    etBus.setText("");
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
                break;
            case R.id.tv_clear:
                LitePal.deleteAll(BusHistory.class);
                busHistories.clear();
                busHistories = LitePal.findAll(BusHistory.class);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
