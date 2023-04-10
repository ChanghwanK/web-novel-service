package com.web.novel.novel;

public class SerialInfo {
    private String day;
    private String date;
    private Type type;

    public enum Type {
        WEEKLY, MONTHLY;
    }

    public SerialInfo(String day, String date, Type type) {
        this.day = day;
        this.date = date;
        this.type = type;
    }

    public static SerialInfo init(String type, String value) {
        if(type.equals(Type.WEEKLY.name()))
            return initWeeklyType(value);

        return initMonthlyType(value);
    }

    private static SerialInfo initWeeklyType(final String day) {
        return new SerialInfo(day, null, Type.WEEKLY);
    }

    private static SerialInfo initMonthlyType(final String date) {
        return new SerialInfo(null, date + "일", Type.MONTHLY);
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }
}
