package com.idwzx.klinedemo;

public interface IBaseChart {
    boolean isLongPressing();
    void setLongPressing(boolean longPressing);

    void addUnionChart(BaseCombinedChart baseCombinedChart);

    float getTouchX();

    void setTouchX(float touchX);

    float getTouchY();

    void setTouchY(float touchY);

    float transferToUnionTouchY(BaseCombinedChart chart,float srcTouchY);
}
