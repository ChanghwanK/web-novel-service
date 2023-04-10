package com.web.novel.novel.port.in;

import com.web.novel.novel.Novel.NovelId;
import lombok.Value;

public interface NovelDeleteUseCase {
    void command(Command command);

    @Value
    class Command {
        NovelId novelId;
    }
}
