package com.web.novel.novel;

import lombok.Value;

public class Genre {
    private GenreId genreId;
    private String name;

    private Genre(final GenreId genreId) {
        this.genreId = genreId;
    }

    public Genre(GenreId genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public static Genre newInstance(final GenreId genreId) {
        return new Genre(genreId);
    }

    public GenreId getGenreId() {
        return genreId;
    }

    public String getName() {
        return name;
    }

    @Value
    public static class GenreId {
        Long value;
    }
}
