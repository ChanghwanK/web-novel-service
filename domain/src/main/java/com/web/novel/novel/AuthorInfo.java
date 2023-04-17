package com.web.novel.novel;

import com.web.novel.member.Member;
import lombok.Value;

public class AuthorInfo {

    private AuthorId authorId;
    private String authorNickName;
    private String email;

    public String getAuthorNickName() { return authorNickName; }

    public String getEmail() { return email; }

    public static AuthorInfo init(AuthorId authorId) {
        return new AuthorInfo(authorId);
    }

    public static AuthorInfo init(final Member member) {
        return new AuthorInfo(member.getNickName().getValue(), member.getEmail().getValue());
    }

    public AuthorInfo(final String authorNickName) {
        this.authorNickName = authorNickName;
    }

    private AuthorInfo(final AuthorId authorId) {
        this.authorId = authorId;
    }

    private AuthorInfo(final String authorNickName, final String email) {
        this.authorNickName = authorNickName;
        this.email = email;
    }

    public AuthorInfo(AuthorId authorId, String authorNickName, String email) {
        this.authorId = authorId;
        this.authorNickName = authorNickName;
        this.email = email;
    }

    public AuthorId getAuthorId() {
        return authorId;
    }

    @Value
    public static class AuthorId {
        Long value;
    }
}
