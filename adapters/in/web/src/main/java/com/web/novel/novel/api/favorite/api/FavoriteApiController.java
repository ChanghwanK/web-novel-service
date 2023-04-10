package com.web.novel.novel.api.favorite.api;

import com.web.novel.config.response.CommonResponse;
import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.api.favorite.api.mapper.FavoriteApiMapper;
import com.web.novel.novel.favorite.port.FavoriteUpsertUseCase;
import com.web.novel.novel.favorite.port.FavoriteUpsertUseCase.Command;
import com.web.novel.novel.favorite.port.in.FavoriteNovelsQueryUseCase;
import com.web.novel.novel.favorite.port.in.FavoriteNovelsQueryUseCase.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteApiController {

    private final FavoriteApiMapper favoriteApiMapper;
    private final FavoriteUpsertUseCase favoriteRegisterUseCase;
    private final FavoriteNovelsQueryUseCase favoriteNovelsQueryUseCase;

    public FavoriteApiController(
            final FavoriteApiMapper favoriteApiMapper,
            final FavoriteUpsertUseCase favoriteRegisterUseCase,
            final FavoriteNovelsQueryUseCase favoriteNovelsQueryUseCase) {
        this.favoriteApiMapper = favoriteApiMapper;
        this.favoriteRegisterUseCase = favoriteRegisterUseCase;
        this.favoriteNovelsQueryUseCase = favoriteNovelsQueryUseCase;
    }

    /**
     * 회원 가입 기능은 중점이 아니기 때문에 제공하지 않아 memberId를 직접받는 것으로 진행
     */
    @PutMapping("/api/v1/novel/{novelId}/favorite")
    public CommonResponse<Void> registerFavoriteNovel(
            @PathVariable Long novelId, @RequestParam Long memberId) {

        favoriteRegisterUseCase.command(new Command(new NovelId(novelId), new MemberId(memberId)));
        return CommonResponse.SUCCESS_DEFAULT;
    }

    @GetMapping("/api/v1/novel/favorite")
    public CommonResponse<?> getFavoriteNovels(@RequestParam Long memberId) {
        var results = favoriteNovelsQueryUseCase.query(new Query(new MemberId(memberId)));
        var data = favoriteApiMapper.mapToFavoriteListResponse(results);
        return CommonResponse.success(data);
    }
}
