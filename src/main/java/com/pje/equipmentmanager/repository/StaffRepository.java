package com.pje.equipmentmanager.repository;

import com.pje.equipmentmanager.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findAllByIsInOfficeOrderByStaffIdDesc(Boolean isInOffice);
}
