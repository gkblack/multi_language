package com.raokii.multi_language;

import com.raokii.multi_language.base.BaseActivity;
import com.raokii.multi_language.util.LogUtil;

public class SecondActivity extends BaseActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        LogUtil.e("Second::"+allChild.size());
    }
}
