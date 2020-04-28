package com.zza.jpaa.services.impl;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.config.UserInfoConfig;
import com.zza.jpaa.constant.enums.OperaTypeEnum;
import com.zza.jpaa.entity.InviteCode;
import com.zza.jpaa.entity.User;
import com.zza.jpaa.entity.dto.InvitedCodeDto;
import com.zza.jpaa.enums.RoleEnum;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.InvitedCodeRepository;
import com.zza.jpaa.respository.UserRepository;
import com.zza.jpaa.services.InvitedCodeService;
import com.zza.jpaa.services.LogService;
import com.zza.jpaa.util.PersonlStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InvitedCodeServiceImpl implements InvitedCodeService {

    @Resource
    InvitedCodeRepository invitedCodeRepository;

    @Resource
    UserRepository userRepository;

    @Resource
    LogService logService;

    private static int CODE_LENGTH = 1 << 3;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public InvitedCodeDto createCode(String account) {
        Optional<User> optionalUser = userRepository.findByNameEqualsAndRoleEquals(account, RoleEnum.ADMIN.getCode());

        // 判断用户是否过期
        if (optionalUser.isPresent()) {
            throw new BizException("用户已存在，请勿重复生成");
        }

        // 判断是否已有激活码
        Optional<InviteCode> optCode = invitedCodeRepository.findByUserNameAndStatus(account, 0);
        if (optCode.isPresent()) {
            throw new BizException("用户已激活或激活码已存在，请勿重复生成");
        }
        // 生成随机邀请码
        String code = PersonlStringUtil.makeRandomString(CODE_LENGTH);
        Date now = new Date();
        InviteCode inviteCode = InviteCode.builder()
                .code(code)
                .createTime(now)
                .status(0)
                .userName(account)
                .build();

        // 存储邀请码
        invitedCodeRepository.save(inviteCode);
        logService.doLog(OperaTypeEnum.CREATE_CODE, UserInfoConfig.currUserInfos.get().getUserId());
        return InvitedCodeDto.builder()
                .account(account)
                .code(code)
                .createTime(sdf.format(now))
                .build();
    }

    @Override
    public ResultData deleteCode(String id) {
        Optional<InviteCode> optById = invitedCodeRepository.findById(id);
        if (optById.isPresent()) {
            if (optById.get().getStatus() == 1) {
                throw new BizException("账户已使用，无法删除");
            }
            log.info("删除激活码 {}", optById.get().toString());
            invitedCodeRepository.deleteById(id);
        } else {
            throw new BizException("邀请码不存在");
        }
        logService.doLog(OperaTypeEnum.DEL_CODE);
        return ResultData.success("删除成功");
    }

    @Override
    public List<InvitedCodeDto> codeList() {

        List<InviteCode> all = invitedCodeRepository.findAll();
        return all.stream()
                .map(this::mapTo)
                .collect(Collectors.toList());
    }

    private InvitedCodeDto mapTo(InviteCode item) {
        return InvitedCodeDto.builder()
                .createTime(sdf.format(item.getCreateTime()))
                .code(item.getCode())
                .account(item.getUserName())
                .status(item.getStatus())
                .id(item.getId())
                .build();
    }
}
