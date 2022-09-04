package com.pje.equipmentmanager.controller;

import com.pje.equipmentmanager.enums.EquipmentType;
import com.pje.equipmentmanager.model.CommonResult;
import com.pje.equipmentmanager.model.ListResult;
import com.pje.equipmentmanager.model.SingleResult;
import com.pje.equipmentmanager.model.EquipmentDetail;
import com.pje.equipmentmanager.model.EquipmentItem;
import com.pje.equipmentmanager.model.EquipmentPurchaseRequest;
import com.pje.equipmentmanager.service.EquipmentService;
import com.pje.equipmentmanager.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "기자재 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/V1/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @ApiOperation(value = "기자재 정보 등록하기")
    @PostMapping("/new")
    public CommonResult setEquipment(@RequestBody @Valid EquipmentPurchaseRequest purchaseRequest) {
        equipmentService.setEquipment(purchaseRequest);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "기자재 리스트 가져오기")
    @GetMapping("/search")
    public ListResult<EquipmentItem> getEquipments(@RequestParam(value = "equipmentType", required = false) EquipmentType equipmentType) {
        if (equipmentType == null) {
            return ResponseService.getListResult(equipmentService.getEquipments(), true);
        } else  {
            return ResponseService.getListResult(equipmentService.getEquipments(equipmentType), true);
        }
    }

    @ApiOperation(value = "기자재 정보 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "기자재 시퀀스", required = true)
    })
    @GetMapping("/{id}")
    public SingleResult<EquipmentDetail> getEquipment(@PathVariable long id) {
        return ResponseService.getSingleResult(equipmentService.getEquipment(id));
    }

    @ApiOperation(value = "기자재 고장 체크하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "기자재 시퀀스", required = true)
    })
    @PutMapping("/{id}")
    public CommonResult putDateBroken(@PathVariable long id) {
        equipmentService.putEquipmentBroken(id);
        return ResponseService.getSuccessResult();
    }

    //기자재 고장상태를 체크하는 컨트롤러라서 deleteMapping 쓰지 않고 putMapping 사용.
    //추후 수리해서 재사용 하는 경우도 있으므로... 완전한 기자재의 삭제로 보기 어려워서 이렇게 판단하였습니다.
}
