package nice.cashe.domain;

import java.util.concurrent.ConcurrentHashMap;
import nice.cashe.domain.cashe_component.UntilTime;
import nice.cashe.domain.cashe_component.repository_component.Key;
import nice.cashe.domain.cashe_component.repository_component.Targets;

public class Cashe {

    private static final ConcurrentHashMap<Key, Targets> repository = new ConcurrentHashMap<>();
    private final UntilTime untilTime;

    private Cashe(String key, int count, String userTime) {
        createRepository(key, count);
        this.untilTime = createTime(userTime);
    }

    public static Cashe of(String key, int count, String localDateTime) {
        return new Cashe(key, count, localDateTime);
    }

    private void createRepository(String inputKey, int count) {
        Key key = initKey(inputKey);
        Targets target = initTarget(count);
        repository.put(key, target);
    }

    private Key initKey(String inputKey) {
        return new Key(inputKey);
    }

    private Targets initTarget(int count) {
        return new Targets(count);
    }

    private UntilTime createTime(String userTime) {
        return new UntilTime(userTime);
    }

}
