package com.pje.equipmentmanager.model;

import com.pje.equipmentmanager.entity.EquipmentUsageDetails;
import com.pje.equipmentmanager.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EquipmentUsageDetailsItem {
    @ApiModelProperty(notes = "기자재 사용 내역 시퀀스", required = true)
    private Long equipmentUsageDetailsId;

    @ApiModelProperty(notes = "기자재 사용 시간", required = true)
    private LocalDateTime equipmentDateUsage;

    @ApiModelProperty(notes = "기자재 시퀀스", required = true)
    private Long equipmentId;

    @ApiModelProperty(notes = "기자재 전체 이름", required = true)
    private String equipmentFullName;

    @ApiModelProperty(notes = "기자재 사용 여부", required = true)
    private Boolean equipmentIsAvailable;

    @ApiModelProperty(notes = "기자재 사용 시작 날짜", required = true)
    private LocalDateTime equipmentDateUseStart;

    @ApiModelProperty(notes = "기자재 고장 날짜")
    private LocalDateTime equipmentDateBroken;

    @ApiModelProperty(notes = "사원 시퀀스", required = true)
    private Long staffId;

    @ApiModelProperty(notes = "사원 [직급] + 이름", required = true)
    private String staffFullInfo;

    @ApiModelProperty(notes = "사원 연락처", required = true)
    private String staffPhone;

    @ApiModelProperty(notes = "사원 생년월일", required = true)
    private LocalDate staffBirthday;

    @ApiModelProperty(notes = "사원 재직 여부", required = true)
    private Boolean staffIsInOffice;

    @ApiModelProperty(notes = "사원 입사 날짜", required = true)
    private LocalDate staffDateJoin;

    @ApiModelProperty(notes = "사원 퇴사 날짜")
    private LocalDate staffDateRetire;

    private EquipmentUsageDetailsItem(EquipmentUsageDetailsItemBuilder builder) {
        this.equipmentUsageDetailsId = builder.equipmentUsageDetailsId;
        this.equipmentDateUsage = builder.equipmentDateUsage;
        this.equipmentId = builder.equipmentId;
        this.equipmentFullName = builder.equipmentFullName;
        this.equipmentIsAvailable = builder.equipmentIsAvailable;
        this.equipmentDateUseStart = builder.equipmentDateUseStart;
        this.equipmentDateBroken = builder.equipmentDateBroken;
        this.staffId = builder.staffId;
        this.staffFullInfo = builder.staffFullInfo;
        this.staffPhone = builder.staffPhone;
        this.staffBirthday = builder.staffBirthday;
        this.staffIsInOffice = builder.staffIsInOffice;
        this.staffDateJoin = builder.staffDateJoin;
        this.staffDateRetire = builder.staffDateRetire;
    }

    public static class EquipmentUsageDetailsItemBuilder implements CommonModelBuilder<EquipmentUsageDetailsItem> {
        private final Long equipmentUsageDetailsId;
        private final LocalDateTime equipmentDateUsage;
        private final Long equipmentId;
        private final String equipmentFullName;
        private final Boolean equipmentIsAvailable;
        private final LocalDateTime equipmentDateUseStart;
        private final LocalDateTime equipmentDateBroken;
        private final Long staffId;
        private final String staffFullInfo;
        private final String staffPhone;
        private final LocalDate staffBirthday;
        private final Boolean staffIsInOffice;
        private final LocalDate staffDateJoin;
        private final LocalDate staffDateRetire;

        public EquipmentUsageDetailsItemBuilder(EquipmentUsageDetails equipmentUsageDetails) {
            this.equipmentUsageDetailsId = equipmentUsageDetails.getId();
            this.equipmentDateUsage = equipmentUsageDetails.getDateUsage();
            this.equipmentId = equipmentUsageDetails.getEquipment().getEquipmentId();
            this.equipmentFullName = equipmentUsageDetails.getEquipment().getEquipmentType().getName() + " - " +equipmentUsageDetails.getEquipment().getEquipmentName();
            this.equipmentIsAvailable = equipmentUsageDetails.getEquipment().getIsAvailable();
            this.equipmentDateUseStart = equipmentUsageDetails.getEquipment().getDateUseStart();
            this.equipmentDateBroken = equipmentUsageDetails.getEquipment().getDateBroken();
            this.staffId = equipmentUsageDetails.getStaff().getStaffId();
            this.staffFullInfo = "[" + equipmentUsageDetails.getStaff().getOfficePosition().getName() + "] " + equipmentUsageDetails.getStaff().getStaffName();
            this.staffPhone = equipmentUsageDetails.getStaff().getStaffPhone();
            this.staffBirthday = equipmentUsageDetails.getStaff().getStaffBirthday();
            this.staffIsInOffice = equipmentUsageDetails.getStaff().getIsInOffice();
            this.staffDateJoin = equipmentUsageDetails.getStaff().getDateJoin();
            this.staffDateRetire = equipmentUsageDetails.getStaff().getDateRetire();
        }

        @Override
        public EquipmentUsageDetailsItem build() {
            return new EquipmentUsageDetailsItem(this);
        }
    }

}
