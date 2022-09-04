package com.pje.equipmentmanager.entity;

import com.pje.equipmentmanager.enums.OfficePosition;
import com.pje.equipmentmanager.interfaces.CommonModelBuilder;
import com.pje.equipmentmanager.model.StaffJoinRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OfficePosition officePosition;

    @Column(nullable = false, length = 20)
    private String staffName;

    @Column(nullable = false, length = 15)
    private String staffPhone;

    @Column(nullable = false)
    private LocalDate staffBirthday;

    @Column(nullable = false)
    private Boolean isInOffice;

    @Column(nullable = false)
    private LocalDate dateJoin;

    private LocalDate dateRetire;

    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    public void putDateRetire() {
        this.isInOffice = false;
        this.dateRetire = LocalDate.now();
        this.dateUpdate = LocalDateTime.now();
    }

    private Staff(StaffBuilder builder) {
        this.officePosition = builder.officePosition;
        this.staffName = builder.staffName;
        this.staffPhone = builder.staffPhone;
        this.staffBirthday = builder.staffBirthday;
        this.isInOffice = builder.isInOffice;
        this.dateJoin = builder.dateJoin;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;
    }

    public static class StaffBuilder implements CommonModelBuilder<Staff> {
        private final OfficePosition officePosition;
        private final String staffName;
        private final String staffPhone;
        private final LocalDate staffBirthday;
        private final Boolean isInOffice;
        private final LocalDate dateJoin;
        private final LocalDateTime dateCreate;
        private final LocalDateTime dateUpdate;

        public StaffBuilder(StaffJoinRequest joinRequest) {
            this.officePosition = joinRequest.getOfficePosition();
            this.staffName = joinRequest.getStaffName();
            this.staffPhone = joinRequest.getStaffPhone();
            this.staffBirthday = joinRequest.getStaffBirthday();
            this.isInOffice = true;
            this.dateJoin = LocalDate.now();
            this.dateCreate = LocalDateTime.now();
            this.dateUpdate = LocalDateTime.now();
        }

        @Override
        public Staff build() {
            return new Staff(this);
        }
    }
}
