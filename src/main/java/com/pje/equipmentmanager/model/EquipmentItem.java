package com.pje.equipmentmanager.model;

import com.pje.equipmentmanager.entity.Equipment;
import com.pje.equipmentmanager.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EquipmentItem {
    @ApiModelProperty(notes = "기자재 시퀀스", required = true)
    private Long equipmentId;

    @ApiModelProperty(notes = "기자재 전체 이름", required = true)
    private String equipmentFullName;

    @ApiModelProperty(notes = "기자재 구매 날짜", required = true)
    private LocalDate datePurchase;

    private EquipmentItem(EquipmentItemBuilder builder) {
        this.equipmentId = builder.equipmentId;
        this.equipmentFullName = builder.equipmentFullName;
        this.datePurchase = builder.datePurchase;
    }

    public static class EquipmentItemBuilder implements CommonModelBuilder<EquipmentItem> {
        private final Long equipmentId;
        private final String equipmentFullName;
        private final LocalDate datePurchase;

        public EquipmentItemBuilder(Equipment equipment) {
            this.equipmentId = equipment.getEquipmentId();
            this.equipmentFullName = "[" + equipment.getEquipmentType().getName() + "] " + equipment.getEquipmentName() + " 번 기자재";
            this.datePurchase = equipment.getDatePurchase();
        }

        @Override
        public EquipmentItem build() {
            return new EquipmentItem(this);
        }
    }
}
