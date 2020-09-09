package com.example.tiku53_57.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiku53_57.R;
import com.example.tiku53_57.bean.HjjcBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-30 at 10:41
 */
public class HJJCAdapter extends ArrayAdapter<HjjcBean> {
    public HJJCAdapter(@NonNull Context context, @NonNull List<HjjcBean> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hjjc_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HjjcBean hjjcBean = getItem(position);
        holder.itemShzs.setText(hjjcBean.getMag());
        holder.itemMax.setText(hjjcBean.getMax() + "");
        holder.itemMin.setText(hjjcBean.getMin() + "");
        holder.itemAverage.setText(hjjcBean.getAverage() + "");
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_shzs)
        TextView itemShzs;
        @BindView(R.id.item_max)
        TextView itemMax;
        @BindView(R.id.item_min)
        TextView itemMin;
        @BindView(R.id.item_average)
        TextView itemAverage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
