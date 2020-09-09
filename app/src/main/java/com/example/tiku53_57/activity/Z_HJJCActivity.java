package com.example.tiku53_57.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.tiku53_57.R;
import com.example.tiku53_57.adapter.HJJCAdapter;
import com.example.tiku53_57.bean.Hjjc;
import com.example.tiku53_57.bean.HjjcBean;
import com.example.tiku53_57.net.VolleyLo;
import com.example.tiku53_57.net.VolleyTo;
import com.example.tiku53_57.util.MyUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-30 at 08:34
 */
public class Z_HJJCActivity extends BaseActivity {
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_up_msg)
    TextView tvUpMsg;
    @BindView(R.id.pic_chart)
    PieChart picChart;
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.list_view)
    ListView listView;
    private Map<String, List<Hjjc>> mapAll;
    private Map<String, Hjjc> map;
    private String[] arr = {"雄安", "北京", "上海", "深圳", "重庆"};
    private boolean isLoop = true;
    private List<PieEntry> pieEntries;
    private int[] colors = {Color.parseColor("#B22125"), Color.parseColor("#233543")
            , Color.parseColor("#509098"), Color.parseColor("#C86D53"), Color.parseColor("#80BD9F")};
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            setDate();
            return false;
        }
    });
    private int index = 0, minute = 0, select = 0;
    private List<HjjcBean> hjjcBeans;
    private HJJCAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.hjjc_layout;
    }

    @Override
    public String setTitle() {
        return "环境检测";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mapAll = new HashMap<>();
        picChart.getDescription().setEnabled(false);
        Legend legend = picChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setFormSize(25);
        legend.setTextSize(25);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoop) {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        setClick();
    }

    private void setClick() {
        picChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                select = (int) h.getX();
                setSelect();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void setDate() {
        if (map == null) map = new HashMap<>();
        else map.clear();
        for (int i = 0; i < 5; i++) {
            setVolley(i);
        }
    }

    private void setVolley(final int i) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        List<Hjjc> hjjcs = mapAll.get(arr[i]);
                        if (hjjcs == null) {
                            hjjcs = new ArrayList<>();
                        }
                        hjjcs.add(0, new Gson().fromJson(jsonObject.toString(), Hjjc.class));
                        mapAll.put(arr[i], hjjcs);
                        map.put(arr[i], hjjcs.get(0));
                        if (map.size() == 5) {
                            tvDate.setText(MyUtils.SimpDate("yyyy年M月d日 H:mm", new Date()));
                            setSelect();
                            setPieChart();
                            index++;
                            if (index == 12 || index == 1) {
                                minute++;
                                index = 1;
                                tvUpMsg.setText("最近更新:" + (minute == 1 ? "最新数据" : minute + "分钟之前"));
                            }
                        }

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setPieChart() {
        if (pieEntries == null) pieEntries = new ArrayList<>();
        else pieEntries.clear();
        for (int i = 0; i < map.size(); i++) {
            Hjjc hjjcs = map.get(arr[i]);
            pieEntries.add(new PieEntry(hjjcs.getPm25(), arr[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(colors);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.5f);
        dataSet.setValueLinePart1OffsetPercentage(70);
        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setValueTextSize(10);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if (value <= 35) {
                    return "优:" + value;
                }else if (value>35&&value<=75){
                    return "良:"+value;
                }else if (value>75&&value<=115){
                    return "轻度污染:"+value;
                }else if (value>115&&value<=150){
                    return "中度污染:"+value;
                }else {
                    return "重度污染:"+value;
                }
            }
        });
        PieData data = new PieData(dataSet);
        picChart.setData(data);
        picChart.setDrawHoleEnabled(false);
        picChart.setEntryLabelColor(Color.BLACK);
        picChart.invalidate();
    }

    private void setSelect() {
        tvCityName.setText(arr[select]);
        if (mapAll.size() != 5) {
            return;
        }
        List<Hjjc> hjjcs = mapAll.get(arr[select]);
        List<Integer> wds = new ArrayList<>();
        List<Integer> cos = new ArrayList<>();
        List<Integer> pms = new ArrayList<>();
        List<Integer> gzs = new ArrayList<>();
        List<Integer> sds = new ArrayList<>();
        int wd = 0, sd = 0, gz = 0, pm = 0, co = 0;
        for (int i = 0; i < hjjcs.size(); i++) {
            Hjjc hjjc = hjjcs.get(i);
            wds.add(hjjc.getTemperature());
            wd += hjjc.getTemperature();
            cos.add(hjjc.getCo2());
            co += hjjc.getCo2();
            pms.add(hjjc.getPm25());
            pm += hjjc.getPm25();
            gzs.add(hjjc.getIllumination());
            gz += hjjc.getIllumination();
            sds.add(hjjc.getHumidity());
            sd += hjjc.getHumidity();
        }
        if (hjjcBeans == null) hjjcBeans = new ArrayList<>();
        else hjjcBeans.clear();
        hjjcBeans.add(new HjjcBean("pm2.5(µg/m3)", Collections.max(pms), Collections.min(pms), pm / pms.size()));
        hjjcBeans.add(new HjjcBean("二氧化碳(ppm)", Collections.max(cos), Collections.min(cos), co / cos.size()));
        hjjcBeans.add(new HjjcBean("光照强度(SI)", Collections.max(gzs), Collections.min(gzs), gz / gzs.size()));
        hjjcBeans.add(new HjjcBean("湿度(RH)", Collections.max(sds), Collections.min(sds), sd / sds.size()));
        hjjcBeans.add(new HjjcBean("温度(C)", Collections.max(wds), Collections.min(wds), wd / wds.size()));
        if (adapter == null) {
            adapter = new HJJCAdapter(this, hjjcBeans);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isLoop = false;
    }
}
