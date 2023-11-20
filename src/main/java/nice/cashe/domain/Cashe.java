package nice.cashe.domain;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import nice.cashe.domain.cashe_component.Targets;
import nice.cashe.domain.cashe_component.repository_component.Key;
import nice.cashe.domain.exception.InputNotExistsKeyException;

public class Cashe {

    private static final ConcurrentHashMap<Key, Targets> repository = new ConcurrentHashMap<>();

    public Cashe() {
    }

    public Targets get(String inputKey) {
        Key key = new Key(inputKey);
        return getTargets(key);
    }

    private Targets getTargets(Key key) {
        return Optional.ofNullable(repository.get(key))
                .orElseThrow(InputNotExistsKeyException::new);
    }

    public List<Targets> getAll() {
        return repository.values().stream()
                .collect(Collectors.toList());
    }

    public void put(String inputKey, int count, String userInputTime) {
        saveData(inputKey, count, userInputTime);
    }

    private void saveData(String inputKey, int count, String userInputTime) {
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
}
