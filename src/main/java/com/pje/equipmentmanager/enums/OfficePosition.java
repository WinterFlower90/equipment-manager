package com.pje.equipmentmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OfficePosition {
    WORKER("사원"),
    DEPUTY("대리"),
    TEAM_HEAD("팀장"),
    SECTION_HEAD("과장"),
    DEPARTMENT_HEAD("부장")
    ;

    private final String name;
}
