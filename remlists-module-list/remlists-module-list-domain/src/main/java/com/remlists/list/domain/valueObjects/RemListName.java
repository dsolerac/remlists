package com.remlists.list.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import javax.validation.constraints.NotEmpty;
        import java.io.Serializable;

public class RemListName implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(RemListName.class);

    @NotEmpty
    private String name;


    public RemListName() {
    }

    public RemListName(@NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemListName)) return false;

        RemListName that = (RemListName) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }


    @Override
    public String toString() {
        return "RemListName{" +
                "name='" + name + '\'' +
                '}';
    }

}