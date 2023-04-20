package com.web.novel.novel;

import lombok.Value;

public class AuthorInfo {

    private AuthorId authorId;
    private String authorNickName;
    private String email;

    public String getAuthorNickName() { return authorNickName; }

    public String getEmail() { return email; }

    public static AuthorInfo newInstance(AuthorId authorId) {
        return new AuthorInfo(authorId);
    }

    public AuthorInfo(final String authorNickName) {
        this.authorNickName = authorNickName;
    }

    private AuthorInfo(final AuthorId authorId) {
        this.authorId = authorId;
    }

    public AuthorInfo(
            final AuthorId authorId,
            final String authorNickName,
            final String email) {
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
