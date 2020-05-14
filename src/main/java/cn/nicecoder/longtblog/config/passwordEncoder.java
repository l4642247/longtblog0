package cn.nicecoder.longtblog.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: longt
 * @Date: 2019/3/29 10:23
 * @Description:
 */
public class passwordEncoder  implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
