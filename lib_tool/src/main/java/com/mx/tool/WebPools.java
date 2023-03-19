package com.mx.tool;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import com.mx.mlog.MLog;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Mx
 * @date 2022/12/13
 */
public class WebPools {
    private final Queue<WebView> mWebViews;

    private final static int MAX_POOL_SIZE = 2;

    private static class SingleHelper {
        private static final WebPools ins = new WebPools();
    }

    private WebPools() {
        mWebViews = new LinkedBlockingQueue<>();
    }

    public static WebPools getInstance() {
        return SingleHelper.ins;
    }

    public void preload(Context context) {
        new Handler(Looper.getMainLooper()).post(() -> {
            try {
                Looper.myQueue().addIdleHandler(() -> {
                    if (mWebViews.size() < MAX_POOL_SIZE) {
                        mWebViews.offer(acquireWebView(context.getApplicationContext()));
                    }
                    return false;
                });
            } catch (Throwable var2) {
                MLog.INSTANCE.e("PreloadingWebView", "Oops!" + var2);
            }
        });
    }

    public void recycle(WebView webView) {
        if (webView == null) return;
        recycleInternal(webView);
    }

    public @NonNull WebView acquireWebView(Context context) {
        return acquireWebViewInternal(context);
    }

    private WebView acquireWebViewInternal(Context context) {
        WebView mWebView = mWebViews.poll();
        if (mWebView == null) {
            mWebView = new WebView(new MutableContextWrapper(context));
        } else {
            MutableContextWrapper mMutableContextWrapper = (MutableContextWrapper) mWebView.getContext();
            mMutableContextWrapper.setBaseContext(context);
        }
        return mWebView;
    }

    private void recycleInternal(WebView webView) {
        try {
            if (webView.getContext() instanceof MutableContextWrapper) {
                MutableContextWrapper mContext = (MutableContextWrapper) webView.getContext();
                mContext.setBaseContext(mContext.getApplicationContext());
                webView.stopLoading();
                webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                webView.clearHistory();
                webView.setWebViewClient(null);
                webView.setWebChromeClient(null);
                ViewParent viewParent = webView.getParent();
                if (viewParent != null) {
                    ((ViewGroup) viewParent).removeView(webView);
                }
                preload(mContext.getApplicationContext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
