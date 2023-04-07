package com.web.novel.novel.entity;

import com.web.novel.exception.InvalidParamException;
import com.web.novel.novel.SerialInfo;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

@Embeddable
public class SerialInfoJpaEntity {

    @Column(name = "info", nullable = false)
    private String info;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @RequiredArgsConstructor
    @Getter
    public enum Type {
        WEEKLY("WEEKLY"), MONTHLY("MONTHLY");
        private final String description;
    }

    protected SerialInfoJpaEntity() {}

    public SerialInfoJpaEntity(
            final String info,
            final Type type) {

        if(ObjectUtils.isEmpty(info))
            throw new InvalidParamException();

        if(ObjectUtils.isEmpty(type))
            throw new InvalidParamException();

        this.info = info;
        this.type = type;
    }

    public static SerialInfoJpaEntity init(SerialInfo serialInfo) {
        if(serialInfo.getType().name().equals(Type.MONTHLY.description)) {
            return initMonthlyType(serialInfo);
        }
        return initWeeklyType(serialInfo);
    }

    private static SerialInfoJpaEntity initWeeklyType(SerialInfo serialInfo) {
        return new SerialInfoJpaEntity(serialInfo.getDay(), Type.WEEKLY);
    }

    private static SerialInfoJpaEntity initMonthlyType(SerialInfo serialInfo) {
        return new SerialInfoJpaEntity(serialInfo.getDate(), Type.MONTHLY);
    }
}
