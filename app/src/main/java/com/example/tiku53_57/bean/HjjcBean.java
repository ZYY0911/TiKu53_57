package com.example.tiku53_57.bean;

import java.util.stream.Stream;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-30 at 10:19
 */
public class HjjcBean {
    private String mag;
    private int max,min,average;

    public HjjcBean(String mag, int max, int min, int average) {
        this.mag = mag;
        this.max = max;
        this.min = min;
        this.average = average;
    }

    public <T extends Object & Comparable<? super T>> HjjcBean(String mag, T max, T min, Stream<Integer> stream) {
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
}
