package com.remlists.user.domain.services;

import com.remlists.user.domain.valueObjects.Password;

public interface PasswordEncoderDomainService {

    String  encode(Password passwordToEncode);
    boolean matches(Password passwordToEncode, Password passwordEncodedStored);
    boolean upgradeEncoding(String encodedPassword);

}
