package com.web.novel.novel.port.out;

import com.web.novel.novel.Genre;

public interface GenreLoadPort {
    Genre getByName(String name);
    Genre getById(Long genreId);
}
