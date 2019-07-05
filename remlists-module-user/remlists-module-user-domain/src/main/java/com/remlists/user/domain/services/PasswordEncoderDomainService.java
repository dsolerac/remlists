package com.remlists.user.domain.services;

public interface PasswordEncoderDomainService {

    String  encode(String passwordToEncode);
    boolean matches(String passwordToEncode, String passwordEncodedStored);
    boolean upgradeEncoding(String encodedPassword);

}
