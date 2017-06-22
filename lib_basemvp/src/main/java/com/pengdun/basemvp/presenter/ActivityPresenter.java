package com.pengdun.basemvp.presenter;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.pengdun.basemvp.view.IDelegate;


/**
 * <b>Project:</b> YuantaiApplication<br>
 * <b>Create Date:</b> 2016/9/22<br>
 * <b>Author:</b> pengdun<br>
 * <b>Description:</b> <br>
 * <p>
 * Presenter base class for Activity
 * Presenter层的实现基类
 */
public abstract class ActivityPresenter<T extends IDelegate> extends AppCompatActivity {

    protected T viewDelegate;

    public ActivityPresenter() {
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("create IDelegate error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IDelegate error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(viewDelegate.getRootView());
        initToolbar();
        viewDelegate.initWidget();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                viewDelegate.getRootView().setFitsSystemWindows(true);
                if (viewDelegate.getRootView() instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) viewDelegate.getRootView();
                    viewGroup.setClipToPadding(false);
                }
            }
        }
        if (viewDelegate.getToolbar() != null) {
            setSupportActionBar(viewDelegate.getToolbar());
            viewDelegate.getToolbar().setTitle("");
        }
        bindEvenListener();
    }

    protected void bindEvenListener() {
    }

    protected void initToolbar() {
        Toolbar toolbar = viewDelegate.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (viewDelegate == null) {
            try {
                viewDelegate = getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("create IDelegate error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create IDelegate error");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (viewDelegate != null && viewDelegate.getOptionsMenuId() != 0) {
            getMenuInflater().inflate(viewDelegate.getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseData();
        viewDelegate = null;
    }

    /**
     * 释放viewDelegate 中的数据
     */
    protected void releaseData() {
    }

    protected abstract Class<T> getDelegateClass();


}
