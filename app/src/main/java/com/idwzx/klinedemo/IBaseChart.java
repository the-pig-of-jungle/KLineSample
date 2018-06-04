package com.idwzx.klinedemo;

import java.util.List;

public interface IBaseChart {
    
    float DEFAULT_CHART_BORDER_WIDTH = 0.3f;
    float DEFAULT_MAX_SCALE_X = 9f;
    float DEFAULT_INIT_SCALE_X = 4f;


    boolean isLongPressing();

    void setLongPressing(boolean longPressing);

    float getTouchX();

    void setTouchX(float touchX);

    float getTouchY();

    void setTouchY(float touchY);

    float transferToUnionTouchY(BaseCombinedChart chart, float srcTouchY);
}
