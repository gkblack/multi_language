package com.raokii.multi_language.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.raokii.multi_language.entity.Lang;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rao
 * @date 2019/4/1
 */
public class XliffUtil {

    public static List<Lang> readXliff(Context context) {
        List<Lang> list = new ArrayList<>();
        SAXReader reader = new SAXReader();
        try {
            // 读取外部文件
            Document document = reader.read(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "120.xliff"));
            list = getList(document);
        } catch (DocumentException e) {
            // 当获取的list为空时从assets中读取数据
            // 读取assets文件
            try {
                Document doc = reader.read(context.getAssets().open("120.xliff"));
                list = getList(doc);
            } catch (DocumentException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
        return list;
    }

    private static List<Lang> getList(Document document){
        List<Lang> list = new ArrayList<>();
        Element root = document.getRootElement();
        Iterator iters = root.element("file").element("body").elementIterator("trans-unit");
        while (iters.hasNext()) {
            Log.e("ttt", "遍历");
            Element recordEle = (Element) iters.next();
            String source = recordEle.elementTextTrim("source");
            String target = recordEle.elementTextTrim("target");
            Log.e("ttt", source + "\n" + target);
            Lang lang = new Lang();
            lang.setSource(source);
            lang.setTarget(target);
            list.add(lang);
        }
        return list;
    }
}
