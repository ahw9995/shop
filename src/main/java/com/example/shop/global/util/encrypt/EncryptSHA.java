package com.example.shop.global.util.encrypt;

import io.micrometer.common.util.StringUtils;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EncryptSHA {


    public static String encryptSha512(String value) {

        if (StringUtils.isEmpty(value)) {
            throw new EncryptSHAException("암호화할 데이터가 존재하지 않습니다.");
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(value.getBytes(StandardCharsets.UTF_8));
            return String.format("%128x", new BigInteger(1, digest.digest()));

        } catch (Exception e) {
            log.error(""
                + "Encrypt SHA-512 Error. => ", e);

            throw new EncryptSHAException("SHA-512 암호화에 실패하였습니다.");
        }
    }

}
