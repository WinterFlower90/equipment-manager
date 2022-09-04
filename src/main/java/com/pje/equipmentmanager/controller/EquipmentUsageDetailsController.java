package com.pje.equipmentmanager.controller;

import com.pje.equipmentmanager.entity.Equipment;
import com.pje.equipmentmanager.entity.Staff;
import com.pje.equipmentmanager.model.CommonResult;
import com.pje.equipmentmanager.model.EquipmentUsageDetailsItem;
import com.pje.equipmentmanager.model.EquipmentUsageDetailsRequest;
import com.pje.equipmentmanager.model.ListResult;
import com.pje.equipmentmanager.service.EquipmentService;
import com.pje.equipmentmanager.service.EquipmentUsageDetailsService;
import com.pje.equipmentmanager.service.ResponseService;
import com.pje.equipmentmanager.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Api(tags = "기자재 이용 내역 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/V1/usage-details")
public class EquipmentUsageDetailsController {
    private final StaffService staffService;
    private final EquipmentService equipmentService;
    private final EquipmentUsageDetailsService usageDetailsService;

    @ApiOperation(value = "이용 내역 등록하기")
    @PostMapping("/new")
    public CommonResult setUsageDetails(@RequestBody @Valid EquipmentUsageDetailsRequest usageDetailsRequest) {
        Equipment equipment = equipmentService.getEquipmentData(usageDetailsRequest.getEquipmentId());
        Staff staff = staffService.getStaffData(usageDetailsRequest.getStaffId());

        usageDetailsService.setEquipmentUsageDetails(equipment, staff, usageDetailsRequest.getDateUsage());

        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "기간별 이용 내역 리스트 가져오기")
    @GetMapping("/search")
    public ListResult<EquipmentUsageDetailsItem> getUsageDetails(
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "dateStart") LocalDate dateStart,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "dateEnd") LocalDate dateEnd
    ) {
        return ResponseService.getListResult(usageDetailsService.getUsageDetails(dateStart, dateEnd), true);
    }
}
