package com.web.novel.point;

import com.web.novel.member.Member.MemberId;
import com.web.novel.point.dto.PointChargeRequestDto;
import com.web.novel.point.port.in.PointChargeUseCase;
import com.web.novel.point.port.in.PointChargeUseCase.Command;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointApiController {

    private final PointChargeUseCase pointChargeUseCase;

    public PointApiController(final PointChargeUseCase pointChargeUseCase) {
        this.pointChargeUseCase = pointChargeUseCase;
    }

    @PostMapping("/api/v1/point")
    public void pointCharge(
        @RequestParam Long memberId,
        @RequestBody @Valid PointChargeRequestDto dto) {

        pointChargeUseCase.command(
            new Command(
                new MemberId(memberId),
                PayMethod.create(dto.getPayMethod()),
                new ChargeAmount(dto.getChargeAmount())));
    }
}
