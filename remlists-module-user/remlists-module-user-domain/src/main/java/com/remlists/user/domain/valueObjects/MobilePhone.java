package com.remlists.user.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public final class MobilePhone implements Serializable  {

    private static final Logger LOG = LoggerFactory.getLogger(MobilePhone.class);

    @NotEmpty
    private String phone;

    public MobilePhone(@NotEmpty String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MobilePhone)) return false;

        MobilePhone that = (MobilePhone) o;

        return getPhone() != null ? getPhone().equals(that.getPhone()) : that.getPhone() == null;
    }

    @Override
    public int hashCode() {
        return getPhone() != null ? getPhone().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "phone='" + phone + '\'' +
                '}';
    }
}
