package com.pje.equipmentmanager.entity;

import com.pje.equipmentmanager.enums.EquipmentType;
import com.pje.equipmentmanager.interfaces.CommonModelBuilder;
import com.pje.equipmentmanager.model.EquipmentPurchaseRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EquipmentType equipmentType;

    @Column(nullable = false, length = 20)
    private String equipmentName;

    @Column(nullable = false)
    private LocalDate datePurchase;

    @Column(nullable = false)
    private Double equipmentPrice;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private LocalDateTime dateUseStart;

    private LocalDateTime dateBroken;

    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    /*
    기자재 시퀀스. 기자재 타입. 기자재명. 기자재 구입일. 기자재 구입 가격. 데이터 생성날짜. 데이터 수정날짜. 기자재 사용여부. 기자재 사용시작날짜?. 기자재 고장날짜?
     */

    public void putDateBroken() {
        this.isAvailable = false;
        this.dateBroken = LocalDateTime.now();
    }

    private Equipment(EquipmentBuilder builder) {
        this.equipmentType = builder.equipmentType;
        this.equipmentName = builder.equipmentName;
        this.datePurchase = builder.datePurchase;
        this.equipmentPrice = builder.equipmentPrice;
        this.isAvailable = builder.isAvailable;
        this.dateUseStart = builder.dateUseStart;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;

    }

    public static class EquipmentBuilder implements CommonModelBuilder<Equipment> {
        private final EquipmentType equipmentType;
        private final String equipmentName;
        private final LocalDate datePurchase;
        private final Double equipmentPrice;
        private final Boolean isAvailable;
        private final LocalDateTime dateUseStart;
        private final LocalDateTime dateCreate;
        private final LocalDateTime dateUpdate;

        public EquipmentBuilder(EquipmentPurchaseRequest purchaseRequest) {
            this.equipmentType = purchaseRequest.getEquipmentType();
            this.equipmentName = purchaseRequest.getEquipmentName();
            this.datePurchase = purchaseRequest.getDatePurchase();
            this.equipmentPrice = purchaseRequest.getEquipmentPrice();
            this.isAvailable = true;
            this.dateUseStart = LocalDateTime.now();
            this.dateCreate = LocalDateTime.now();
            this.dateUpdate = LocalDateTime.now();
        }


        @Override
        public Equipment build() {
            return new Equipment(this);
        }
    }
}
