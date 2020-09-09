package com.example.tiku53_57.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-29 at 20:40
 */
public class Ssjt implements Serializable {


    /**
     * ROWS_DETAIL : [{"id":1,"start":"06:23:00","end":"21:19:00","mileage":"20","price":"8","site":["四惠枢纽站","老山公交场站","双庙","宽街路口南","东直门枢纽站","金家村桥东","菜户营桥","北土城公交场站","龙潭公园","六里桥东","五间楼","北京华侨城","金台路","航天桥东","南菜园","孛罗营","大北窑东","五间楼","小营公交场站","角门南站","二里庄"]}]
     * RESULT : S
     */

    private String RESULT;
    private List<ROWSDETAILBean> ROWS_DETAIL;

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean implements Serializable {
        /**
         * id : 1
         * start : 06:23:00
         * end : 21:19:00
         * mileage : 20
         * price : 8
         * site : ["四惠枢纽站","老山公交场站","双庙","宽街路口南","东直门枢纽站","金家村桥东","菜户营桥","北土城公交场站","龙潭公园","六里桥东","五间楼","北京华侨城","金台路","航天桥东","南菜园","孛罗营","大北窑东","五间楼","小营公交场站","角门南站","二里庄"]
         */

        private int id;
        private String start;
        private String end;
        private String mileage;
        private String price;
        private List<String> site;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<String> getSite() {
            return site;
        }

        public void setSite(List<String> site) {
            this.site = site;
        }
    }
}
