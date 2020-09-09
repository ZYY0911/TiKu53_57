package com.example.tiku53_57.bean;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-30 at 09:37
 */
public class Hjjc {

    /**
     * temperature : 19
     * humidity : 19
     * illumination : 1405
     * co2 : 5153
     * pm25 : 157
     * RESULT : S
     */

    private int temperature;
    private int humidity;
    private int illumination;
    private int co2;
    private int pm25;
    private String RESULT;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }
}
