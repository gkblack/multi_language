package com.raokii.multi_language.base;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raokii.multi_language.entity.AllView;
import com.raokii.multi_language.entity.Lang;
import com.raokii.multi_language.util.XliffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 基类activity
 * 思路-> 登陆时从服务器拉取语言包，languageId对应某个language，根据id获取读取的xliff文件，默认读取设置的default.xliff文件
 * 如果更改了语言，则通过SharedPreferences保存语言id,下次登陆时读取对应文件
 * 遍历每个activity或fragment的view，拿到需要变更语言的view和原本的text文本存入list中
 * 从xliff拿到的source和target，用source和view的id对应，用target替换原本的text文本
 * @author Rao
 * @date 2019/4/2
 */
public abstract class BaseActivity extends AppCompatActivity {

    public List<View> viewList = new ArrayList<>();
    public List<AllView> allChild = new ArrayList<>();
    String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
    public List<Lang> langs = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                !ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, 8811);
            return;
        }
        langs = XliffUtil.readXliff(this);
        // 当参数为空时finish,否则创建
        if(initArgs(getIntent().getExtras())){
            int layoutId =  getContentLayoutId();
            setContentView(layoutId);
            // 拿到当前view做遍历
            getAllViews(((ViewGroup)findViewById(android.R.id.content)).getChildAt(0));
            initWidget();
            initData();
        }else {
            finish();
        }

    }

    /**
     * 在界面未初始化之前调用的初始化窗口
     */
    protected void initWindows(){
    }

    /**
     * 初始化相关参数
     * @param bundle
     * @return 如果参数正确返回true
     */
    protected boolean initArgs(Bundle bundle){
        return true;
    }
    /**
     *  子类复写
     * 得到当前界面的资源文件ID
     * @return
     */
    protected abstract int getContentLayoutId();

//    public void getView(View view){
//        getAllViews(view);
//    };

    /**
     * 初始化控件
     */
    protected void initWidget(){
    }

    /**
     * 初始化数据
     */
    protected void initData(){

    }

    @Override
    public boolean onSupportNavigateUp() {
        // 当点击界面导航返回时，finish当前界面
        return super.onSupportNavigateUp();
    }

    public void getAllViews(View view) {
        viewList = getAllChildViews(view);
        for(View v : viewList){
            String strId = v.toString();
            // 判断是否是需要替换的语言
            if (strId.contains("app") && strId.contains("lang")) {
                String id = strId.substring(strId.indexOf("app:id")).replace("}", "").
                        replace("/","").replace("app:id","");
                TextView tv = (TextView) v;
                if(strId.contains("TextView")){
                    allChild.add(new AllView(v,id, "TextView", tv.getText().toString()));
                }
            }
        }
        Log.e("size",viewList.size() +"");
    }

    /**
     * 遍历获取布局
     * @param view
     * @return
     */
    private List<View> getAllChildViews(View view) {
        List<View> allChildren = new ArrayList<>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewChild = vp.getChildAt(i);
                Log.e("childId", vp.getChildCount() +"");
                String strId = viewChild.toString();
                // 判断该view是否需要替换语言
                if (strId.contains("app") && strId.contains("lang")) {
                    String id = strId.substring(strId.indexOf("app:id")).replace("}", "").
                            replace("/","").replace("app:id","");
                    TextView tv = (TextView) viewChild;
                    Log.e("id","id:" + "R.id." + id +"\n textview" +  tv.getText());
                    if(strId.contains("TextView")){
                        allChild.add(new AllView(viewChild,id, "TextView", tv.getText().toString()));
                    }
                }
                // 再次调用本身
                allChildren.addAll(getAllChildViews(viewChild));
            }
        }
        return allChildren;
    }






}
