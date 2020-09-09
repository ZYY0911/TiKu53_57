package com.example.tiku53_57.bean;

import org.litepal.crud.LitePalSupport;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-29 at 20:58
 */
public class BusHistory extends LitePalSupport {
    private int id;
    private String msg;
    public BusHistory(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
