package com.pje.equipmentmanager.model;

import com.pje.equipmentmanager.entity.Staff;
import com.pje.equipmentmanager.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StaffItem {
    @ApiModelProperty(notes = "사원 시퀀스", required = true)
    private Long staffId;

    @ApiModelProperty(notes = "사원 [직급]이름", required = true)
    private String staffFullInfo;

    @ApiModelProperty(notes = "사원 연락처", required = true)
    private String staffPhone;

    @ApiModelProperty(notes = "사원 생년월일", required = true)
    private LocalDate staffBirthday;

    @ApiModelProperty(notes = "사원 재직 여부", required = true)
    private String isInOffice;

    @ApiModelProperty(notes = "사원 입사일", required = true)
    private LocalDate dateJoin;

    @ApiModelProperty(notes = "사원 퇴사일")
    private LocalDate dateRetire;

    private StaffItem(StaffItemBuilder builder) {
        this.staffId = builder.staffId;
        this.staffFullInfo = builder.staffFullInfo;
        this.staffPhone = builder.staffPhone;
        this.staffBirthday = builder.staffBirthday;
        this.isInOffice = builder.isInOffice;
        this.dateJoin = builder.dateJoin;
        this.dateRetire = builder.dateRetire;

    }

    public static class StaffItemBuilder implements CommonModelBuilder<StaffItem> {
        private final Long staffId;
        private final String staffFullInfo;
        private final String staffPhone;
        private final LocalDate staffBirthday;
        private final String isInOffice;
        private final LocalDate dateJoin;
        private final LocalDate dateRetire;

        public StaffItemBuilder(Staff staff) {
            this.staffId = staff.getStaffId();
            this.staffFullInfo = "[" + staff.getOfficePosition().getName() + "] " + staff.getStaffName();
            this.staffPhone = staff.getStaffPhone();
            this.staffBirthday = staff.getStaffBirthday();
            this.isInOffice = staff.getIsInOffice() ? "예" : "아니오" ;
            this.dateJoin = staff.getDateJoin();
            this.dateRetire = staff.getDateRetire();
        }

        @Override
        public StaffItem build() {
            return new StaffItem(this);
        }
    }
}
