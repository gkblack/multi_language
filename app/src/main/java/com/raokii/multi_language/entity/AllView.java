package com.raokii.multi_language.entity;

import android.view.View;

import java.io.Serializable;

/**
 * @author Rao
 * @date 2019/4/2
 */
public class AllView implements Serializable {

    public View view;

    public String id;

    public String type;

    public String defaultText; // 默认的text名字

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public AllView(View view, String id, String type, String defaultText) {
        this.view = view;
        this.id = id;
        this.type = type;
        this.defaultText = defaultText;
    }
}
