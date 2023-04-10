package com.web.novel.novel.favorite.port.out;

import com.web.novel.novel.favorite.Favorite.FavoriteId;

public interface FavoriteDeletePort {
    void deleteById(FavoriteId favoriteId);
}
