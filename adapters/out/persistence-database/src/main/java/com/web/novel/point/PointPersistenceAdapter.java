package com.web.novel.point;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.point.mapper.PointMapper;
import com.web.novel.point.port.out.PointSavePort;
import com.web.novel.point.repository.PointRepository;

@PersistenceAdapter
public class PointPersistenceAdapter implements PointSavePort {

    private final PointMapper pointMapper;
    private final PointRepository pointRepository;

    public PointPersistenceAdapter(final PointMapper pointMapper, final PointRepository pointRepository) {
        this.pointMapper = pointMapper;
        this.pointRepository = pointRepository;
    }

    @Override
    public void save(final Point point) {
        var initEntity = pointMapper.mapToJpaEntityForSave(point);
        pointRepository.save(initEntity);
    }
}
