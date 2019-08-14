package com.lxj.xpopup.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternity.android.annotation.extra.core.svc.control.ControlTower;
import com.eternity.android.annotation.extra.core.svc.screen.Screen;
import com.eternity.android.annotation.extra.core.svc.views.Views;
import com.lxj.xpopup.R;
import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.animator.ScrollScaleAnimator;
import com.lxj.xpopup.util.XPopupUtils;
import com.lxj.xpopup.widget.PartShadowContainer;

import static com.lxj.xpopup.enums.PopupAnimation.ScaleAlphaFromCenter;

/**
 * Description: 用于自由定位的弹窗
 * Create by dance, at 2019/6/14
 */
public class PositionPopupView extends BasePopupView {
    protected PartShadowContainer attachPopupContainer;

    public PositionPopupView(@NonNull Context context) {
        super(context);
        initInject();
    }

    public PositionPopupView(@NonNull Fragment fragment) {
        super(fragment);
        initInject();
    }

    public PositionPopupView(@NonNull Screen screen) {
        super(screen);
        initInject();
    }

    public PositionPopupView(@NonNull Views viewAction) {
        super(viewAction);
    }

    public PositionPopupView(@NonNull ControlTower controlAction) {
        super(controlAction);
    }

    private void initInject() {
        attachPopupContainer = findViewById(R.id.attachPopupContainer);
        initImplContentView();
    }

    @Override
    protected void initImplContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(getImplLayoutId(), attachPopupContainer, false);
        attachPopupContainer.addView(contentView);
    }

    @Override
    protected int getPopupLayoutId() {
        return R.layout._xpopup_attach_popup_view;
    }

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        XPopupUtils.applyPopupSize((ViewGroup) getPopupContentView(), getMaxWidth(), getMaxHeight(), new Runnable() {
            @Override
            public void run() {
                if (popupInfo.isCenterHorizontal) {
                    float left = (XPopupUtils.getWindowWidth(getContext()) - attachPopupContainer.getMeasuredWidth()) / 2f;
                    attachPopupContainer.setTranslationX(left);
                } else {
                    attachPopupContainer.setTranslationX(popupInfo.offsetX);
                }
                attachPopupContainer.setTranslationY(popupInfo.offsetY);
            }
        });
    }

    @Override
    protected PopupAnimator getPopupAnimator() {
        return new ScrollScaleAnimator(getPopupContentView(), ScaleAlphaFromCenter);
    }
}
