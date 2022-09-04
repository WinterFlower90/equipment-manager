package com.pje.equipmentmanager.model;

import com.pje.equipmentmanager.entity.Equipment;
import com.pje.equipmentmanager.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EquipmentDetail {
    @ApiModelProperty(notes = "기자재 시퀀스", required = true)
    private Long equipmentId;

    @ApiModelProperty(notes = "기자재 타입명", required = true)
    private String equipmentTypeName;

    @ApiModelProperty(notes = "기자재명", required = true)
    private String equipmentName;

    @ApiModelProperty(notes = "기자재 구매 날짜", required = true)
    private LocalDate datePurchase;

    @ApiModelProperty(notes = "기자재 구매 가격", required = true)
    private Double equipmentPrice;

    @ApiModelProperty(notes = "기자재 가동 여부", required = true)
    private String isAvailable;

    @ApiModelProperty(notes = "기자재 가동 시작 날짜", required = true)
    private LocalDateTime dateUseStart;

    @ApiModelProperty(notes = "기자재 고장 날짜")
    private LocalDateTime dateBroken;

    private EquipmentDetail(EquipmentDetailBuilder builder) {
        this.equipmentId = builder.equipmentId;
        this.equipmentTypeName = builder.equipmentTypeName;
        this.equipmentName = builder.equipmentName;
        this.datePurchase = builder.datePurchase;
        this.equipmentPrice = builder.equipmentPrice;
        this.isAvailable = builder.isAvailable;
        this.dateUseStart = builder.dateUseStart;
        this.dateBroken = builder.dateBroken;
    }

    public static class EquipmentDetailBuilder implements CommonModelBuilder<EquipmentDetail> {
        private final Long equipmentId;
        private final String equipmentTypeName;
        private final String equipmentName;
        private final LocalDate datePurchase;
        private final Double equipmentPrice;
        private final String isAvailable;
        private final LocalDateTime dateUseStart;
        private final LocalDateTime dateBroken;

        public EquipmentDetailBuilder(Equipment equipment) {
            this.equipmentId = equipment.getEquipmentId();
            this.equipmentTypeName = equipment.getEquipmentType().getName();
            this.equipmentName = equipment.getEquipmentName();
            this.datePurchase = equipment.getDatePurchase();
            this.equipmentPrice = equipment.getEquipmentPrice();
            this.isAvailable = equipment.getIsAvailable() ? "예" : "아니오" ;
            this.dateUseStart = equipment.getDateUseStart();
            this.dateBroken = equipment.getDateBroken();
        }


        @Override
        public EquipmentDetail build() {
            return new EquipmentDetail(this);
        }
    }
}
