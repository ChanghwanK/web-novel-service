package com.web.novel.novel.api.favorite.api.mapper;

import com.web.novel.novel.api.favorite.api.dto.FavoriteListResponse;
import com.web.novel.novel.favorite.port.in.FavoriteNovelsQueryUseCase.Result;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FavoriteApiMapper {

    public List<FavoriteListResponse> mapToFavoriteListResponse(List<Result> results) {
        return results.stream()
            .map(result -> new FavoriteListResponse(
                result.getNovelTitle(),
                result.getLastUploadedChapterTile(),
                result.getLastUploadedAt()))
            .collect(Collectors.toList());
    }

}
