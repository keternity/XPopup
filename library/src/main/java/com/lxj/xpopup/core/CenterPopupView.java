package com.lxj.xpopup.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.eternity.android.annotation.extra.core.svc.control.ControlTower;
import com.eternity.android.annotation.extra.core.svc.screen.Screen;
import com.eternity.android.annotation.extra.core.svc.views.Views;
import com.lxj.xpopup.R;
import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.animator.ScaleAlphaAnimator;
import com.lxj.xpopup.util.XPopupUtils;

import static com.lxj.xpopup.enums.PopupAnimation.ScaleAlphaFromCenter;

/**
 * Description: 在中间显示的Popup
 * Create by dance, at 2018/12/8
 */
public class CenterPopupView extends BasePopupView {
    protected FrameLayout centerPopupContainer;
    protected int bindLayoutId;
    protected int bindItemLayoutId;

    public CenterPopupView(@NonNull Context context) {
        super(context);
        initInject();
    }

    public CenterPopupView(@NonNull Screen screen) {
        super(screen);
        initInject();
    }

    public CenterPopupView(@NonNull Fragment fragment) {
        super(fragment);
        initInject();
    }

    public CenterPopupView(@NonNull Views viewAction) {
        super(viewAction);
        initInject();
    }

    public CenterPopupView(@NonNull ControlTower controlAction) {
        super(controlAction);
        initInject();
    }

    private void initInject(){
        centerPopupContainer = findViewById(R.id.centerPopupContainer);
        initImplContentView();
    }

    @Override
    protected void initImplContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(getImplLayoutId(), centerPopupContainer, false);
        LayoutParams params = (LayoutParams) contentView.getLayoutParams();
        params.gravity = Gravity.CENTER;
        centerPopupContainer.addView(contentView, params);
    }

    @Override
    protected int getPopupLayoutId() {
        return R.layout._xpopup_center_popup_view;
    }

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        getPopupContentView().setTranslationX(popupInfo.offsetX);
        getPopupContentView().setTranslationY(popupInfo.offsetY);
        XPopupUtils.applyPopupSize((ViewGroup) getPopupContentView(), getMaxWidth(), getMaxHeight());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setTranslationY(0);
    }

    /**
     * 具体实现的类的布局
     *
     * @return
     */
    protected int getImplLayoutId() {
        return 0;
    }

    protected int getMaxWidth() {
        return popupInfo.maxWidth == 0 ? (int) (XPopupUtils.getWindowWidth(getContext()) * 0.86f)
                : popupInfo.maxWidth;
    }

    @Override
    protected PopupAnimator getPopupAnimator() {
        return new ScaleAlphaAnimator(getPopupContentView(), ScaleAlphaFromCenter);
    }
}
