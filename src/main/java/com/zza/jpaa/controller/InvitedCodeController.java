package com.zza.jpaa.controller;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.dto.InvitedCodeDto;
import com.zza.jpaa.entity.vo.CreateInvitedCodeVo;
import com.zza.jpaa.services.InvitedCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api(tags = {"邀请码获取模块"})
@RestController
@RequestMapping("/inviteCode")
public class InvitedCodeController {

    @Resource
    InvitedCodeService invitedCodeService;

    @ApiOperation(value = "生成邀请码", notes = "createInvitedCode")
    @PostMapping("/create")
    public ResultData createCode(@RequestBody CreateInvitedCodeVo codeVo) {
        InvitedCodeDto code = invitedCodeService.createCode(codeVo.getAccount());
        return ResultData.success("创建成功", code);
    }

    @ApiOperation(value = "删除邀请码", notes = "delInvitedCode")
    @PostMapping("/del/{id}")
    public ResultData delCode(@PathVariable("id") String id) {
        return invitedCodeService.deleteCode(id);
    }


    @ApiOperation(value = "获取列表", notes = "getCodeList")
    @GetMapping("/list")
    public ResultData codeList() {
        List<InvitedCodeDto> invitedCodeDtos = invitedCodeService.codeList();
        return ResultData.success("列表获取成功", invitedCodeDtos);
    }
}
