package com.wanlichangmeng.tonglurendesign.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wanlichangmeng.tonglurendesign.adapter.UpdatingAdapter;
import com.wanlichangmeng.tonglurendesign.utils.chat.EmotionUtils;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UIUtils {
    /**
     * 上下文
     *
     * @return
     */
    public static int with;

    public static Context getContext() {
        return BaseApplication.getContext();
    }
    public static Resources getResources() {
        return getContext().getResources();
    }
    public static String getString(int resId) {
        return getResources().getString(resId);
    }
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }
    public static String getPackageName() {
        return getContext().getPackageName();
    }
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }
    public static Handler getMainHandler() {
        return BaseApplication.getMainHandler();
    }
    public static long getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }
    /**
     * 让task在主线程中执行
     */
    public static void post(Runnable task) {
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            // 在主线程中执行的
            task.run();
        } else {
            // 在子线程中执行的
            getMainHandler().post(task);
        }
    }
    public static void setWindowWidth(View view){
        ViewTreeObserver vto2 = view.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Log.e("wokao","\n\n"+view.getHeight()+","+view.getWidth());
                with = view.getWidth();
                return ;

            }
        });
        return ;
    }
    /**
     * dip 转 px
     *
     * @param dip
     * @return
     */
    public static int dip2px(int dip) {
        //
        // 公式： dp = px / (dpi / 160) px = dp * (dpi / 160)
        // dp = px / denisity
        // px = dp * denisity;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        return (int) (dip * density + 0.5f);
    }
    public static int px2dip(int px) {
        // dp = px / denisity
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        return (int) (px / density + 0.5f);
    }
    /**
     * 执行延时任务
     *
     */
    public static void postDelayed(Runnable task, int delayed) {
        getMainHandler().postDelayed(task, delayed);
    }
    /**
     * 移除任务
     *
     * @param task
     */
    public static void removeCallbacks(Runnable task) {
        getMainHandler().removeCallbacks(task);
    }
    public static String getString(int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }
    //属性动画的开关
    public static void doAnimationToggle(ViewGroup container, boolean animated,
                                         boolean defailedToggleStatus) {
        // 测量以下,然后得到高度
        container.measure(0, 0);
        int height = container.getMeasuredHeight();
        if (defailedToggleStatus) {
            if (animated) {
                // 动画
                // 如果是打开的，那么就关闭
                // height ---> 0
                int start = height;
                int end = 0;
                doAnimation(container, start, end);
            } else {
                // 这个
                ViewGroup.LayoutParams params = container.getLayoutParams();
                params.height = 0;
                container.setLayoutParams(params);
            }
        } else {
            if (animated) {
                // 如果是关闭的，那么就打开
                int start = 0;
                int end = height;
                doAnimation(container, start, end);
            } else {
                ViewGroup.LayoutParams params = container.getLayoutParams();
                params.height = height;
                container.setLayoutParams(params);
            }
        }
        // // 给 箭头设置动画
        // if (defailedToggleStatus) {
        // // 如果是打开的，需要关闭，箭头由 上 到下
        // ObjectAnimator.ofFloat(mIvArrow, "rotation", -180, 0).start();
        // } else {
        // // 箭头由 下到上
        // ObjectAnimator.ofFloat(mIvArrow, "rotation", 0, 180).start();
        // }
        // 状态改变
        defailedToggleStatus = !defailedToggleStatus;
    }
    private static void doAnimation(final ViewGroup container, int start,
                                    int end) {
        // 属性动画,ValueAnimator
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int value = (Integer) animator.getAnimatedValue();
                ViewGroup.LayoutParams params = container.getLayoutParams();
                params.height = value;
                container.setLayoutParams(params);
            }
        });
        animator.start();
    }
    //做动画的监听,使得父容器中存在ScollerView的时候能滚动到下面
    public static void let_ParentsScollerViewFucosDowmWithAnimationListener(
            Animator animator, final View rootView) {
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator arg0) {
                //View rootView = getRootView();
                ScrollView scrollView = null;
                ViewParent parent = rootView.getParent();
                if (parent != null && parent instanceof ViewGroup) {
                    while (true) {
                        parent = parent.getParent();
                        if (parent != null && parent instanceof ScrollView) {
                            scrollView = (ScrollView) parent;
                            break;
                        }
                        if (parent == null) {
                            break;
                        }
                    }
                    // 让ScrollView滚动到底部
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }
            }
            @Override
            public void onAnimationStart(Animator arg0) {
            }
            @Override
            public void onAnimationRepeat(Animator arg0) {
            }
            @Override
            public void onAnimationCancel(Animator arg0) {
            }
        });
    }
    /**
     * 下面都是chatUi的函数下面都是chatUi的函数下面都是chatUi的函数下面都是chatUi的函数下面都是chatUi的函数
     * dp转dip
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5F);
    }
    /**
     * 文本中的emojb字符处理为表情图片
     *
     * @param context
     * @param tv
     * @param source
     * @return
     */
    public static SpannableString getEmotionContent(final Context context, final TextView tv, String source) {
        SpannableString spannableString = new SpannableString(source);
        Resources res = context.getResources();

        String regexEmotion = "\\[([\u4e00-\u9fa5\\w])+\\]";
        Pattern patternEmotion = Pattern.compile(regexEmotion);
        Matcher matcherEmotion = patternEmotion.matcher(spannableString);

        while (matcherEmotion.find()) {
            // 获取匹配到的具体字符
            String key = matcherEmotion.group();
            // 匹配字符串的开始位置
            int start = matcherEmotion.start();
            // 利用表情名字获取到对应的图片
            Integer imgRes = EmotionUtils.EMOTION_STATIC_MAP.get(key);
            if (imgRes != null) {
                // 压缩表情图片
                int size = (int) tv.getTextSize() * 13 / 8;
                Bitmap bitmap = BitmapFactory.decodeResource(res, imgRes);
                Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);

                ImageSpan span = new ImageSpan(context, scaleBitmap);
                spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }

    /**
     * 返回当前时间的格式为 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(System.currentTimeMillis());
    }

    //毫秒转秒
    public static String long2String(long time) {
        //毫秒转秒
        int sec = (int) time / 1000;
        int min = sec / 60;    //分钟
        sec = sec % 60;        //秒
        if (min < 10) {    //分钟补0
            if (sec < 10) {    //秒补0
                return "0" + min + ":0" + sec;
            } else {
                return "0" + min + ":" + sec;
            }
        } else {
            if (sec < 10) {    //秒补0
                return min + ":0" + sec;
            } else {
                return min + ":" + sec;
            }
        }
    }

    /**
     * 毫秒转化时分秒毫秒
     *
     * @param ms
     * @return
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "d");
        }
        if (hour > 0) {
            sb.append(hour + "h");
        }
        if (minute > 0) {
            sb.append(minute + "′");
        }
        if (second > 0) {
            sb.append(second + "″");
        }
        return sb.toString();
    }
}
