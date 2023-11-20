package nice.cashe.domain;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import nice.cashe.domain.cashe_component.Targets;
import nice.cashe.domain.cashe_component.repository_component.Key;
import nice.cashe.domain.exception.InputNotExistsKeyException;

public class Cashe {

    private static final ConcurrentHashMap<Key, Targets> repository = new ConcurrentHashMap<>();

    private Cashe(String key, int count, String userInputTime) {
        createRepository(key, count, userInputTime);
    }

    public static Cashe of(String key, int count, String userInputTime) {
        return new Cashe(key, count, userInputTime);
    }

    private void createRepository(String inputKey, int count, String userInputTime) {
        Key key = initKey(inputKey);
        Targets target = initTarget(count, userInputTime);
        repository.put(key, target);
    }

    private Key initKey(String inputKey) {
        return new Key(inputKey);
    }

    private Targets initTarget(int count, String userInputTime) {
        return new Targets(count, userInputTime);
    }

    public Targets get(String inputKey) {
        Key key = new Key(inputKey);
        return getTargets(key);
    }

    private Targets getTargets(Key key) {
        return Optional.ofNullable(repository.get(key))
                .orElseThrow(InputNotExistsKeyException::new);
    }
}
