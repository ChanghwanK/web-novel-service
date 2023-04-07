package com.web.novel.novel.chapter;

public class Chapter {
    private final String title;
    private final Content content;
    private final String authorComment; // 작가의 말
    private final String reservationDate; // 예약일

    public Chapter(
            final String title,
            final Content content,
            final String authorComment,
            final String reservationDate) {
        this.title = title;
        this.content = content;
        this.authorComment = authorComment;
        this.reservationDate = reservationDate;
    }

    public String getTitle() { return title; }

    public Content getContent() { return content; }

    public String getAuthorComment() { return authorComment; }

    public String getReservationDate() { return reservationDate; }
}
