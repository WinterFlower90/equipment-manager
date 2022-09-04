package com.pje.equipmentmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EquipmentType {
    DIGITAL_PERM("디지털 파마용"),
    SETTING_PERM("셋팅 파마용"),
    HEAT_PERM("열 파마용"),
    MIST("클리닉용 미스트기"),
    HAIR_DRYER("모발 건조기")
    ;


    private final String name;
}
