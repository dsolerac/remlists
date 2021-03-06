package com.remlists.user.write.infrastructure.spring.security;

import com.remlists.user.domain.services.PasswordEncoderDomainService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RemlistUserPasswordEncoder implements PasswordEncoderDomainService {

    private PasswordEncoder encoder;

    public RemlistUserPasswordEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(String passwordToEncode) {
        return encoder.encode(passwordToEncode);
    }

    @Override
    public boolean matches(String passwordToEncode, String passwordEncodedStored) {
        return encoder.matches(passwordToEncode, passwordEncodedStored);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return encoder.upgradeEncoding(encodedPassword);
    }


}
