package com.raokii.multi_language.util;


import android.util.Log;

/**
 * @author Rao
 */

public class LogUtil {

    public static final String USER_NAME = "rao---";
    public static final boolean LOG_OPEN = true;
    private static final boolean LOG_FILE_OPEN = false;
    private static final boolean LOG_TOAST = false;

    public static void v(String msg) {
        if (LOG_OPEN) {
            Log.v(USER_NAME + getClassName(), msg);
        }
    }

    public static void d(String msg) {
        d("", msg);
    }

    public static void d(String method, Object msg) {
        if (LOG_OPEN) {
            Log.d(USER_NAME + getClassName(), method + " " + (msg == null ? "null" : msg.toString()));
        }
    }

    public static void i(Object msg) {
        i("", msg);
    }

    public static void i(String method, Object msg) {
        if (LOG_OPEN) {
            String log = method + "" + (msg == null ? "null" : msg.toString());
            /*if (log.length()>2000){
                Log.i(USER_NAME + getClassName(),"超长开始"+log.length());
                for(int i=0;i<log.length();i+=2000){
                    if(i+2000<log.length())
                        Log.i(USER_NAME + getClassName(),log.substring(i, i+2000));
                    else
                        Log.i(USER_NAME + getClassName(),log.substring(i, log.length()));
                }
                Log.i(USER_NAME + getClassName(),"超长结束");
            }else {
                Log.i(USER_NAME + getClassName(), log);
            }*/

            Log.i(USER_NAME + getClassName(), log);
        }
    }

    public static void w(String msg) {
        w("", msg);
    }

    public static void w(String method, Object msg) {
        if (LOG_OPEN) {
            Log.w(USER_NAME + getClassName(), method + " " + (msg == null ? "" : msg.toString()));
        }
    }

    public static void e(String msg) {
        if (LOG_OPEN) {
            Log.e(USER_NAME + getClassName(), msg);
        }
    }

    public static void e(String msg, Throwable throwable) {
        if (LOG_OPEN) {
            String log = "";
            if (throwable == null) {
                log = "null";
            } else {
                log = throwable.getMessage();
            }
            Log.e(USER_NAME + getClassName(), msg + log);
        }
    }

    /**
     * @return 当前的类名(simpleName)
     */
    private static String getClassName() {

        String result;
        StackTraceElement thisMethodStack = Thread.currentThread().getStackTrace()[4];
        result = thisMethodStack.getClassName();
        if (result.contains("LogUtil")) {
            thisMethodStack = Thread.currentThread().getStackTrace()[5];
            result = thisMethodStack.getClassName();
        }

        //获取类名
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1);
        int i = result.indexOf("$");// 剔除匿名内部类名
        result = (i == -1 ? result : result.substring(0, i));

        //获取行号
        String lineNumber = String.valueOf(thisMethodStack.getLineNumber());


        result = "(" + result + ".java:" + lineNumber + ")";
        while (result.length() < 40) {
            result = "-" + result;
        }
        return result;

    }

    /**
     * 打印 Log 行数位置
     */
    private static String log(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[5];
        String className = targetElement.getClassName();
        className = className.substring(className.lastIndexOf('.') + 1) + ".java";
        int lineNumber = targetElement.getLineNumber();
        if (lineNumber < 0) lineNumber = 0;
        return "(" + className + ":" + lineNumber + ") " + message;
    }

    public static void showLogCat(String tag, String msg) {

        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        int currentIndex = -1;
        for (int i = 0; i < stackTraceElement.length; i++) {
            if (stackTraceElement[i].getMethodName().compareTo("showLogCat") == 0) {
                currentIndex = i + 1;
                break;
            }
        }

        String fullClassName = stackTraceElement[currentIndex].getClassName();
        String className = fullClassName.substring(fullClassName
                .lastIndexOf(".") + 1);
        String methodName = stackTraceElement[currentIndex].getMethodName();
        String lineNumber = String
                .valueOf(stackTraceElement[currentIndex].getLineNumber());

        Log.i(tag, msg);
        Log.i(tag + " position", "at " + fullClassName + "." + methodName + "("
                + className + ".java:" + lineNumber + ")");

    }

    public static void printStack() {
        StackTraceElement[] thisMethodStack = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : thisMethodStack) {
            if (element != null) {
                String className = element.getClassName();
                String methodName = element.getMethodName();
                String lineNumber = String.valueOf(element.getLineNumber());
                lineNumber = "(" + className + ".java:" + lineNumber + ")";
                lineNumber = ">" + lineNumber;
                while (lineNumber.length() < 80) {
                    lineNumber = "-" + lineNumber;
                }
                lineNumber = lineNumber + methodName;
                Log.e(USER_NAME, lineNumber);
            }
        }
    }

    public static void saveAllLogToFile() {
//        saveAllLogTOFile(1);
//        saveAllLogTOFile(3);
    }


}
