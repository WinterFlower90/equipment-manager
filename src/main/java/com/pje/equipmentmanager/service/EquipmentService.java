package com.pje.equipmentmanager.service;

import com.pje.equipmentmanager.entity.Equipment;
import com.pje.equipmentmanager.enums.EquipmentType;
import com.pje.equipmentmanager.exception.CMissingDataException;
import com.pje.equipmentmanager.model.ListResult;
import com.pje.equipmentmanager.model.EquipmentDetail;
import com.pje.equipmentmanager.model.EquipmentItem;
import com.pje.equipmentmanager.model.EquipmentPurchaseRequest;
import com.pje.equipmentmanager.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public Equipment getEquipmentData(long id) {
        return equipmentRepository.findById(id).orElseThrow(CMissingDataException::new);
    }

    public void setEquipment(EquipmentPurchaseRequest purchaseRequest) {
        Equipment equipment = new Equipment.EquipmentBuilder(purchaseRequest).build();
        equipmentRepository.save(equipment);
    }

    public EquipmentDetail getEquipment(long id) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(CMissingDataException::new);
        return new EquipmentDetail.EquipmentDetailBuilder(equipment).build();
    }

    public ListResult<EquipmentItem> getEquipments() {
        List<Equipment> equipments = equipmentRepository.findAll();
        List<EquipmentItem> result = new LinkedList<>();

        equipments.forEach(equipment -> {
            EquipmentItem equipmentItem = new EquipmentItem.EquipmentItemBuilder(equipment).build();
            result.add(equipmentItem);
        });

        return ListConvertService.settingResult(result);
    }

    public ListResult<EquipmentItem> getEquipments(EquipmentType equipmentType) {
        List<Equipment> equipments = equipmentRepository.findAllByEquipmentTypeOrderByEquipmentIdDesc(equipmentType);
        List<EquipmentItem> result = new LinkedList<>();

        equipments.forEach(equipment -> {
            EquipmentItem equipmentItem = new EquipmentItem.EquipmentItemBuilder(equipment).build();
            result.add(equipmentItem);
        });

        return ListConvertService.settingResult(result);
    }

    public void putEquipmentBroken(long id) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(CMissingDataException::new);
        equipment.putDateBroken();
        equipmentRepository.save(equipment);
    }
}
