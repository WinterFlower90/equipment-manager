package com.pje.equipmentmanager.repository;

import com.pje.equipmentmanager.entity.EquipmentUsageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EquipmentUsageDetailsRepository extends JpaRepository<EquipmentUsageDetails, Long> {
    List<EquipmentUsageDetails> findAllByDateUsageGreaterThanEqualAndDateUsageLessThanEqualOrderByIdDesc(LocalDateTime dateStart, LocalDateTime dateEnd);
}
