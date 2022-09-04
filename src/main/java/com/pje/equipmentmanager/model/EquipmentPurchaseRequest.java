package com.pje.equipmentmanager.model;

import com.pje.equipmentmanager.enums.EquipmentType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class EquipmentPurchaseRequest {
    @ApiModelProperty(notes = "기자재 타입(~20글자)", required = true)
    @Enumerated(value = EnumType.STRING)
    private EquipmentType equipmentType;

    @ApiModelProperty(notes = "기자재명(~20글자)", required = true)
    @NotNull
    @Length(max = 20)
    private String equipmentName;

    @ApiModelProperty(notes = "기자재 구입날짜", required = true)
    @NotNull
    private LocalDate datePurchase;

    @ApiModelProperty(notes = "기자재 구매가격", required = true)
    @NotNull
    private Double equipmentPrice;
}
