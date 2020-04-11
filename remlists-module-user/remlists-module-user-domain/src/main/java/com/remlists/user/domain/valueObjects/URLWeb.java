package com.remlists.user.domain.valueObjects;

import org.hibernate.validator.constraints.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public final class URLWeb implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(URLWeb.class);


    @URL
    private String url;

    public URLWeb(@URL String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof URLWeb)) return false;

        URLWeb urlWeb = (URLWeb) o;

        return url != null ? url.equals(urlWeb.url) : urlWeb.url == null;
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "URLWeb{" +
                "url='" + url + '\'' +
                '}';
    }
}
