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
import com.example.tiku53_57.bean.Rzcx;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-29 at 11:47
 */
public class RZCXAdapter extends ArrayAdapter<Rzcx> {
    public RZCXAdapter(@NonNull Context context, @NonNull List<Rzcx> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rzcx_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Rzcx rzcx = getItem(position);
        holder.tvCo.setText(rzcx.getCo2() + "PPM");
        holder.tvGz.setText(rzcx.getIllumination() + "SI");
        holder.tvPm.setText(rzcx.getPm25() + "μg/m3");
        holder.tvWd.setText(rzcx.getTemperature() + "℃");
        holder.tvSd.setText(rzcx.getHumidity() + "RH");
        holder.tvTime.setText(rzcx.getRESULT());
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_co)
        TextView tvCo;
        @BindView(R.id.tv_sd)
        TextView tvSd;
        @BindView(R.id.tv_pm)
        TextView tvPm;
        @BindView(R.id.tv_gz)
        TextView tvGz;
        @BindView(R.id.tv_wd)
        TextView tvWd;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
