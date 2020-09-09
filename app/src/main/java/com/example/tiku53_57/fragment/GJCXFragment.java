package com.example.tiku53_57.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku53_57.R;
import com.example.tiku53_57.bean.Gjjl;
import com.example.tiku53_57.net.VolleyLo;
import com.example.tiku53_57.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-28 at 21:32
 */
@SuppressLint("ValidFragment")
public class GJCXFragment extends Fragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_bus_1)
    TextView tvBus1;
    @BindView(R.id.tv_bus_2)
    TextView tvBus2;
    @BindView(R.id.tv_road_1)
    TextView tvRoad1;
    @BindView(R.id.tv_road_2)
    TextView tvRoad2;
    Unbinder unbinder;
    private int busId;
    private VolleyTo volleyTo;
    private List<Gjjl> gjjls;

    public GJCXFragment(int busId) {
        this.busId = busId;
    }


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            tvTitle.setText("距" + busId + "号站台的距离：");
            if (volleyTo!=null){
                volleyTo.setLoop(false);
                volleyTo = null;
            }
            volleyTo = new VolleyTo();
            volleyTo.setUrl("get_bus_stop_distance")
                    .setJsonObject("UserName", "user1")
                    .setLoop(true)
                    .setTime(3000)
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            gjjls = new Gson().fromJson(jsonObject.optJSONArray(busId==1?"中医院站":"联想大厦站").toString()
                                    , new TypeToken<List<Gjjl>>() {
                                    }.getType());
                            if (gjjls.size() != 0) {
                                tvBus1.setText("1号公交车：" + gjjls.get(0).getDistance() + "m");
                                tvBus2.setText("2号公交车：" + gjjls.get(1).getDistance() + "m");
                            }
                            setVolleySense();
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gjcx_fragment, container,false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void sendMessage(){
        handler.sendEmptyMessage(0);
    }

    private void setVolleySense() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tvRoad1.setText("PM2.5："+jsonObject.optString("pm25")+"µg/m3，温度："+jsonObject.optString("temperature")+"˚C");
                        tvRoad2.setText("湿度："+jsonObject.optString("humidity")+"%，CO2："+jsonObject.optString("co2")+"PPM");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (volleyTo != null) {
            volleyTo.setLoop(false);
            volleyTo = null;
        }
        unbinder.unbind();
    }
}
