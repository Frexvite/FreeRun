package com.freerun.message.thirdparty.ali;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "freerun.sms.ali")
public class AliProperties {
    private String accessId;
    private String accessSecret;
}
