package com.pje.equipmentmanager.service;

import com.pje.equipmentmanager.entity.Staff;
import com.pje.equipmentmanager.exception.CMissingDataException;
import com.pje.equipmentmanager.exception.CNoMemberDataException;
import com.pje.equipmentmanager.model.ListResult;
import com.pje.equipmentmanager.model.StaffItem;
import com.pje.equipmentmanager.model.StaffJoinRequest;
import com.pje.equipmentmanager.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

    public Staff getStaffData(long id) {
        return staffRepository.findById(id).orElseThrow(CMissingDataException::new);
    }

    public void setStaff(StaffJoinRequest joinRequest) {
        Staff staff = new Staff.StaffBuilder(joinRequest).build();
        staffRepository.save(staff);
    }

    public ListResult<StaffItem> getStaffs() {
        List<Staff> staffs = staffRepository.findAll();
        List<StaffItem> result = new LinkedList<>();

        staffs.forEach(staff -> {
            StaffItem addItem = new StaffItem.StaffItemBuilder(staff).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result);
    }

    public ListResult<StaffItem> getStaffs(Boolean isInOffice) {
        List<Staff> staffs = staffRepository.findAllByIsInOfficeOrderByStaffIdDesc(isInOffice);
        List<StaffItem> result = new LinkedList<>();

        staffs.forEach(staff -> {
            StaffItem addItem = new StaffItem.StaffItemBuilder(staff).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result);
    }

    public void putStaffDateRetire(long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(CMissingDataException::new);

        if (!staff.getIsInOffice()) throw new CNoMemberDataException();

        staff.putDateRetire();
        staffRepository.save(staff);
    }
}
