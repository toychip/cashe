package nice.cashe.domain;

import java.util.concurrent.ConcurrentHashMap;
import nice.cashe.domain.cashe_component.UntilTime;
import nice.cashe.domain.cashe_component.repository_component.Key;
import nice.cashe.domain.cashe_component.repository_component.Target;

public class Cashe {

    private static final ConcurrentHashMap<Key, Target> repository = new ConcurrentHashMap<>();
    private final UntilTime untilTime;

    private Cashe(String key, Object target, String userTime) {
        createRepository(key, target);
        this.untilTime = createTime(userTime);
    }

    private Cashe(String key, Object target) {
        createRepository(key, target);
        this.untilTime = createTime();
    }

    public static Cashe of(String key, Object target) {
        return new Cashe(key, target);
    }

    public static Cashe of(String key, Object target, String localDateTime) {
        return new Cashe(key, target, localDateTime);
    }

    private void createRepository(String inputKey, Object inputTarget) {
        Key key = getKey(inputKey);
        Target value = getTarget(inputTarget);
        repository.put(key, value);
    }

    private Key getKey(String inputKey) {
        return new Key(inputKey);
    }

    private Target getTarget(Object inputTarget) {
        return new Target(inputTarget);
    }

    private UntilTime createTime(String userTime) {
        return null;
    }

    // default
    private UntilTime createTime() {
        return null;
    }


}
