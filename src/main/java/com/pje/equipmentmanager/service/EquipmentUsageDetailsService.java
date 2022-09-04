package com.pje.equipmentmanager.service;

import com.pje.equipmentmanager.entity.Equipment;
import com.pje.equipmentmanager.entity.EquipmentUsageDetails;
import com.pje.equipmentmanager.entity.Staff;
import com.pje.equipmentmanager.model.EquipmentUsageDetailsItem;
import com.pje.equipmentmanager.model.ListResult;
import com.pje.equipmentmanager.repository.EquipmentUsageDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentUsageDetailsService {
    private final EquipmentUsageDetailsRepository usageDetailsRepository;

    public void setEquipmentUsageDetails(Equipment equipment, Staff staff, LocalDateTime dateUsage) {
        EquipmentUsageDetails usageDetails = new EquipmentUsageDetails.EquipmentUsageDetailsBuilder(equipment, staff, dateUsage).build();
        usageDetailsRepository.save(usageDetails);
    }

    //

    public ListResult<EquipmentUsageDetailsItem> getUsageDetails(LocalDate dateStart, LocalDate dateEnd) {
        LocalDateTime dateStartTime = LocalDateTime.of(
                dateStart.getYear(),
                dateStart.getMonthValue(),
                dateStart.getDayOfMonth(),
                0,0,0
        );
        LocalDateTime dateEndTime = LocalDateTime.of(
                dateEnd.getYear(),
                dateEnd.getMonthValue(),
                dateEnd.getDayOfMonth(),
                23,59,59
        );

        List<EquipmentUsageDetails> usageDetails = usageDetailsRepository.findAllByDateUsageGreaterThanEqualAndDateUsageLessThanEqualOrderByIdDesc(dateStartTime, dateEndTime);

        List<EquipmentUsageDetailsItem> result = new LinkedList<>();
        usageDetails.forEach(usageDetail -> {
            EquipmentUsageDetailsItem addItem = new EquipmentUsageDetailsItem.EquipmentUsageDetailsItemBuilder(usageDetail).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result);

    }
}
