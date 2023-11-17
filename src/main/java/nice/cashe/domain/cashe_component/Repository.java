package nice.cashe.domain.cashe_component;

import java.util.concurrent.ConcurrentHashMap;
import nice.cashe.domain.cashe_component.repository_component.Key;
import nice.cashe.domain.cashe_component.repository_component.Target;

public class Repository {
    private final ConcurrentHashMap<Key, Target> dataMap = new ConcurrentHashMap<>();

    private Repository() {
    }
}
