package com.remlists.user.domain.entities;

import com.remlists.user.domain.valueObjects.City;
import com.remlists.user.domain.valueObjects.Country;
import com.remlists.user.domain.valueObjects.PostalCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.io.Serializable;

public final class UserAddress implements Serializable {

    private Logger LOG = LoggerFactory.getLogger(UserAddress.class);

    @Valid
    private User user;
    @Valid
    private Country country;
    @Valid
    private City city;
    @Valid
    private PostalCode postalCode;


    public UserAddress( @Valid User user,
                        @Valid Country country,
                        @Valid City city,
                        @Valid PostalCode postalCode) {

        this.user = user;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;

    }




}
