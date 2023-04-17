package com.web.novel.point.port.out;

import com.web.novel.point.PointNumber;

public interface PointCacheCheckPort {
    void checkDuplication(PointNumber pointNumber);
    void setPointNumber(PointNumber pointNumber);
}
