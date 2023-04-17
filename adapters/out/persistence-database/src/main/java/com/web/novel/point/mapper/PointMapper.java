package com.web.novel.point.mapper;

import com.web.novel.point.Point;
import com.web.novel.point.entity.PointJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class PointMapper {

    public PointJpaEntity mapToJpaEntityForSave(final Point point) {
        return PointJpaEntity.initForSave(
            point.getMemberId().getValue(),
            point.getChargeAmount().getValue(),
            point.getPayMethod().name(),
            point.getPointNumber().getValue()
        );
    }

}
