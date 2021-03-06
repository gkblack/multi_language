package com.raokii.multi_language;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raokii.multi_language.base.BaseActivity;
import com.raokii.multi_language.entity.AllView;
import com.raokii.multi_language.entity.Lang;
import com.raokii.multi_language.util.ChangeLangEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一次进入时遍历获取对应的标签值，
 */
public class MainActivity extends BaseActivity {

    Button btnEN;
    Button btnCN;
    Button btnDefault;
    TextView text;
//    List<Lang> langs = new ArrayList<>();
//    List<View> viewList = new ArrayList<>();
//    List<AllView> allChild = new ArrayList<>();
    List<AllView> defaultLangs = new ArrayList<>();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        binding.getRoot().findViewById(R.id.change_cn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","click");
                EventBus.getDefault().post(new ChangeLangEvent());
            }
        });
        binding.setVariable(BR.lang, new Lang());
//        btnCN = findViewById(R.id.change_cn);
        btnEN = findViewById(R.id.change_en);
        text = findViewById(R.id.lang_tv_text);
        btnDefault = findViewById(R.id.change_default);
    }

    @Override
    protected void initData() {
        super.initData();
//        Log.e("viewType","simple"+btnCN.getClass().getSimpleName());
//        Log.e("viewType","id"+btnCN.getId() + "");

//        btnCN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView tv = (TextView) allChild.get(0).getView();
//                Log.e("langs",langs.get(0).getSource());
//                tv.setText(langs.get(0).getSource());
//            }
//        });
//        btnEN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView tv = (TextView) allChild.get(0).getView();
//                tv.setText(langs.get(0).getTarget());
//            }
//        });
//        btnDefault.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView tv = (TextView) allChild.get(0).getView();
//                tv.setText(allChild.get(0).getDefaultText());
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, SecondActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    //    @Override
//    public void getAllViews(View view) {
//        viewList = getAllChildViews(view);
//        for(View v : viewList){
//            String strId = v.toString();
//            // 判断是否是需要替换的语言
//            if (strId.contains("app") && strId.contains("lang")) {
//                String id = strId.substring(strId.indexOf("app:id")).replace("}", "").
//                        replace("/","").replace("app:id","");
//                TextView tv = (TextView) v;
//                if(strId.contains("TextView")){
//                    allChild.add(new AllView(v,id, "TextView", tv.getText().toString()));
//                }
//            }
//        }
//        Log.e("size",viewList.size() +"");
//    }
//
//    /**
//     * 遍历获取布局
//     * @param view
//     * @return
//     */
//    private List<View> getAllChildViews(View view) {
//        List<View> allChildren = new ArrayList<>();
//        if (view instanceof ViewGroup) {
//            ViewGroup vp = (ViewGroup) view;
//            for (int i = 0; i < vp.getChildCount(); i++) {
//                View viewChild = vp.getChildAt(i);
//                Log.e("childId", vp.getChildCount() +"");
//                String strId = viewChild.toString();
//                // 判断该view是否需要替换语言
//                if (strId.contains("app") && strId.contains("lang")) {
//                    String id = strId.substring(strId.indexOf("app:id")).replace("}", "").
//                            replace("/","").replace("app:id","");
//                    TextView tv = (TextView) viewChild;
//                    Log.e("id","id:" + "R.id." + id +"\n textview" +  tv.getText());
//                    if(strId.contains("TextView")){
//                        allChild.add(new AllView(viewChild,id, "TextView", tv.getText().toString()));
//                    }
//                }
//                // 再次调用本身
//                allChildren.addAll(getAllChildViews(viewChild));
//            }
//        }
//        return allChildren;
//    }

    public void setTxt(View view,String type){

    }
}
