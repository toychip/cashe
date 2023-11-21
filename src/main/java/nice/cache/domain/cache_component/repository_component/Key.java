package nice.cache.domain.cache_component.repository_component;

import java.util.Objects;

public class Key {
    private String value;

    public Key(String value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Key key = (Key) o;
        return Objects.equals(value, key.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
