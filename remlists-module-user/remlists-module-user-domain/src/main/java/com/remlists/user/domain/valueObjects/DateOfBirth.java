package com.remlists.user.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

public final class DateOfBirth implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(DateOfBirth.class);

    @Past(message = "user.dateOfBirth.past" )
    private LocalDate date;

    public DateOfBirth(LocalDate date) {
        this.date = date;
    }

    public DateOfBirth(int year, int month, int dayOfMonth) {
        this (LocalDate.of(year, month, dayOfMonth));
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateOfBirth)) return false;

        DateOfBirth that = (DateOfBirth) o;

        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }

    @Override
    public int hashCode() {
        return getDate() != null ? getDate().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DateOfBirth{" +
                "date=" + date +
                '}';
    }
}
