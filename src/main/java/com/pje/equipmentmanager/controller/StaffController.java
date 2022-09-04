package com.pje.equipmentmanager.controller;

import com.pje.equipmentmanager.model.CommonResult;
import com.pje.equipmentmanager.model.ListResult;
import com.pje.equipmentmanager.model.StaffItem;
import com.pje.equipmentmanager.model.StaffJoinRequest;
import com.pje.equipmentmanager.service.ResponseService;
import com.pje.equipmentmanager.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "사원 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/V1/staff")
public class StaffController {
    private final StaffService staffService;

    @ApiOperation(value = "사원 정보 등록하기")
    @PostMapping("/new")
    public CommonResult setStaff(@RequestBody @Valid StaffJoinRequest joinRequest) {
        staffService.setStaff(joinRequest);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "사원 리스트 가져오기")
    @GetMapping("/search")
    public ListResult<StaffItem> getStaffs(@RequestParam(value = "isInOffice", required = false) Boolean isInOffice) {
        if (isInOffice == null) {
            return ResponseService.getListResult(staffService.getStaffs(), true);
        } else {
            return ResponseService.getListResult(staffService.getStaffs(isInOffice), true);
        }
    }

    @ApiOperation(value = "사원 퇴사 처리하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "사원 시퀀스", required = true)
    })
    @DeleteMapping("/{id}")
    public CommonResult delStaff(@PathVariable long id) {
        staffService.putStaffDateRetire(id);
        return ResponseService.getSuccessResult();
    }
}
