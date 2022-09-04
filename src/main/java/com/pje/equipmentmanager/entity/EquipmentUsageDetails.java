package com.pje.equipmentmanager.entity;

import com.pje.equipmentmanager.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EquipmentUsageDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipmentId", nullable = false)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffId", nullable = false)
    private Staff staff;

    @Column(nullable = false)
    private LocalDateTime dateUsage;

    private EquipmentUsageDetails(EquipmentUsageDetailsBuilder builder) {
        this.equipment = builder.equipment;
        this.staff = builder.staff;
        this.dateUsage = builder.dateUsage;
    }

    public static class EquipmentUsageDetailsBuilder implements CommonModelBuilder<EquipmentUsageDetails> {
        private final Equipment equipment;
        private final Staff staff;
        private final LocalDateTime dateUsage;

        public EquipmentUsageDetailsBuilder(Equipment equipment, Staff staff, LocalDateTime dateUsage) {
            this.equipment = equipment;
            this.staff = staff;
            this.dateUsage = dateUsage;
        }

        @Override
        public EquipmentUsageDetails build() {
            return new EquipmentUsageDetails(this);
        }
    }
}
