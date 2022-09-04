package com.pje.equipmentmanager.model;

import com.pje.equipmentmanager.enums.OfficePosition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class StaffJoinRequest {
    @ApiModelProperty(notes = "직급", required = true)
    @Enumerated(value = EnumType.STRING)
    private OfficePosition officePosition;

    @ApiModelProperty(notes = "사원 이름(~20글자)", required = true)
    @NotNull
    @Length(max = 20)
    private String staffName;

    @ApiModelProperty(notes = "사원 연락처(~15글자)", required = true)
    @NotNull
    @Length(max = 15)
    private String staffPhone;

    @ApiModelProperty(notes = "사원 생년월일", required = true)
    @NotNull
    private LocalDate staffBirthday;
}
