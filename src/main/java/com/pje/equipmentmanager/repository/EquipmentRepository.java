package com.pje.equipmentmanager.repository;

import com.pje.equipmentmanager.entity.Equipment;
import com.pje.equipmentmanager.enums.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByEquipmentTypeOrderByEquipmentIdDesc(EquipmentType equipmentType);
}
