package com.remlists.user.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public final class PostalCode  implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(PostalCode.class);


    @NotEmpty
    private String code;

    public PostalCode(@NotEmpty String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostalCode)) return false;

        PostalCode that = (PostalCode) o;

        return getCode() != null ? getCode().equals(that.getCode()) : that.getCode() == null;
    }

    @Override
    public int hashCode() {
        return getCode() != null ? getCode().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PostalCode{" +
                "code='" + code + '\'' +
                '}';
    }
}
