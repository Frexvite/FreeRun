package com.freerun.user.service.impl;

import com.freerun.common.exceptions.BadRequestException;
import com.freerun.common.utils.CollUtils;
import com.freerun.common.utils.RandomUtils;
import com.freerun.common.utils.StringUtils;
//import com.freerun.message.api.client.AsyncSmsClient;
//import com.freerun.message.domain.dto.SmsInfoDTO;
//import com.freerun.message.domain.enums.SmsTemplate;
import com.freerun.user.service.ICodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.freerun.api.constants.SmsConstants.VERIFY_CODE_PARAM_NAME;
import static com.freerun.common.constants.ErrorInfo.Msg.INVALID_VERIFY_CODE;
import static com.freerun.user.constants.UserConstants.USER_VERIFY_CODE_KEY;
import static com.freerun.user.constants.UserConstants.USER_VERIFY_CODE_TTL;

@Slf4j
@Service
public class CodeServiceImpl implements ICodeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    private AsyncSmsClient asyncSmsClient;

    @Override
    public void sendVerifyCode(String phone) {
        String key = USER_VERIFY_CODE_KEY + phone;
        // 1.查看code是否存在
        String code = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isBlank(code)){
            // 2.生成随机验证码
            code = RandomUtils.randomNumbers(4);
            // 3.保存到redis
            stringRedisTemplate.opsForValue()
                    .set(USER_VERIFY_CODE_KEY + phone, code, USER_VERIFY_CODE_TTL);

        }
        // 4.发送短信
//        log.debug("发送短信验证码：{}", code);
//        SmsInfoDTO info = new SmsInfoDTO();
//        info.setPhones(CollUtils.singletonList(phone));
//        info.setTemplateCode(SmsTemplate.VERIFY_CODE.toString());
//        Map<String, String> params = new HashMap<>(1);
//        params.put(VERIFY_CODE_PARAM_NAME, code);
//        info.setTemplateParams(params);
//        asyncSmsClient.sendMessage(info);
    }

    @Override
    public void verifyCode(String phone, String code) {
        String cacheCode = stringRedisTemplate.opsForValue().get(USER_VERIFY_CODE_KEY + phone);
        if (!StringUtils.equals(cacheCode, code)) {
            // 验证码错误
            throw new BadRequestException(INVALID_VERIFY_CODE);
        }
    }
}
