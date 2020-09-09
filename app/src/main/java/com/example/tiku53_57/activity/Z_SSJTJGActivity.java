package com.example.tiku53_57.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku53_57.R;
import com.example.tiku53_57.bean.BusHistory;
import com.example.tiku53_57.bean.Ssjt;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-29 at 20:44
 */
public class Z_SSJTJGActivity extends BaseActivity {
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.tv_bus_line)
    TextView tvBusLine;
    @BindView(R.id.tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.tv_time_end)
    TextView tvTimeEnd;
    @BindView(R.id.all_station)
    TextView allStation;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.layout)
    LinearLayout layout;
    private Ssjt.ROWSDETAILBean bean;
    private List<String> busLine;

    @Override
    public int getLayout() {
        return R.layout.ssjtjg_layout;
    }

    @Override
    public String setTitle() {
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
        layout.removeAllViews();
        for (int i = 0; i < busLine.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.ssjt_item, null);
            TextView title_num = view.findViewById(R.id.title_num);
            TextView title_name = view.findViewById(R.id.title_name);
            if (i == 0) {
                title_num.setBackgroundResource(R.drawable.shap_yuan_rad);
                title_name.setTextColor(Color.RED);
            } else {
                title_num.setBackgroundResource(R.drawable.k_shap_yuan);
                title_name.setTextColor(Color.BLACK);
            }
            title_num.setText(i + 1 + "");
            title_name.setText(busLine.get(i));
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < layout.getChildCount(); j++) {
                        View view1 = layout.getChildAt(j);
                        TextView title_num = view1.findViewById(R.id.title_num);
                        TextView title_name = view1.findViewById(R.id.title_name);
                        if (finalI == j) {
                            title_name.setTextColor(Color.RED);
                            title_num.setBackgroundResource(R.drawable.shap_yuan_rad);
                        } else {
                            title_num.setBackgroundResource(R.drawable.k_shap_yuan);
                            title_name.setTextColor(Color.BLACK);
                        }

                    }
                }
            });
            view.setPadding(0, 10, 0, 10);
            layout.addView(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }
    }

    private void initView() {
        Ssjt ssjt = (Ssjt) getIntent().getSerializableExtra("info");
        bean = ssjt.getROWS_DETAIL().get(0);
        change.setImageResource(R.mipmap.back);
        title.setText(bean.getId() + "路");
        busLine = bean.getSite();
        tvBusLine.setText(busLine.get(0) + "-" + busLine.get(busLine.size() - 1));
        tvTimeStart.setText(bean.getStart());
        tvTimeEnd.setText(bean.getEnd());
        allStation.setText(busLine.size() + "站/" + bean.getMileage() + "公里");
        price.setText("最高票价" + bean.getPrice() + "元");
        BusHistory busHistory = new BusHistory(title.getText() + "(" + tvBusLine.getText() + ")");
        busHistory.save();
        List<BusHistory> busHistories = LitePal.findAll(BusHistory.class);
        for (int i = 0; i < busHistories.size() - 1; i++) {
            BusHistory busHistory1 = busHistories.get(i);
            for (int j = busHistories.size() - 1; j > i; j--) {
                BusHistory busHistory2 = busHistories.get(j);
                if (busHistory1.getMsg().equals(busHistory2.getMsg())) {
                    LitePal.delete(BusHistory.class, busHistory1.getId());
                }
            }
        }
    }
}
