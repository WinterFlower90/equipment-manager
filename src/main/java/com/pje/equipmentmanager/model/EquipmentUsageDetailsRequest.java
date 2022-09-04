package com.pje.equipmentmanager.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class EquipmentUsageDetailsRequest {
    @ApiModelProperty(notes = "기자재 시퀀스", required = true)
    @NotNull
    private Long equipmentId;

    @ApiModelProperty(notes = "사원 시퀀스", required = true)
    @NotNull
    private Long staffId;

    @ApiModelProperty(notes = "기자재 이용 날짜", required = true)
    @NotNull
    private LocalDateTime dateUsage;
}
