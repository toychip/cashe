package nice.cashe.domain.cashe_component.repository_component;

import java.util.Objects;

public class Target {
    private final Object data;

    public Target(Object object) {
        this.data = object;
    }

    private Object createObject() {
        return new Object();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Target target = (Target) o;
        return Objects.equals(data, target.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
