package com.raokii.multi_language.entity;

import android.util.Log;

/**
 * @author Rao
 * @date 2019/4/2
 */
public class Lang {

    public String source;

    public String target;

    public String cn = "测试";

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCN(){
        Log.e("test","test");
        return cn;
    }

    public void setCN(String cn){
        this.cn = cn;
    }
}
