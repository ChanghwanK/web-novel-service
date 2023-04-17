package com.web.novel.point;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Value;


@Value
public class ChargedAt {
    private static final DateTimeFormatter DATE_FORMAT
        = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    String time;

    public static ChargedAt of(LocalDateTime time) {
        return new ChargedAt(time.format(DATE_FORMAT));
    }
}
