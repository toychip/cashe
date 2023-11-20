package nice.cashe.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import nice.cashe.domain.cashe_component.Targets;
import nice.cashe.domain.cashe_component.Value;
import nice.cashe.domain.cashe_component.repository_component.Key;
import nice.cashe.domain.exception.InputNotExistsKeyException;

public class Cashe {

    private static final ConcurrentHashMap<Key, Targets> repository = new ConcurrentHashMap<>();
    private static final Timer timer = new Timer();

    static {
        // 10초마다 실행되는 타이머 작업 스케줄링
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                removeExpiredEntries();
            }
        }, 0, 10000); // 0초 지연, 10초 간격
    }

    public Cashe() {
    }

    private static void removeExpiredEntries() {
        LocalDateTime now = LocalDateTime.now();
        repository.entrySet().removeIf(entry -> entry.getValue().getUntilTime().isBefore(now));
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

    public void put(String inputKey, Value value, String userInputTime) {
        saveData(inputKey, value, userInputTime);
    }

    private void saveData(String inputKey, Value value, String userInputTime) {
        Key key = initKey(inputKey);
        Targets target = initTarget(value, userInputTime);
        repository.put(key, target);
    }

    private Key initKey(String inputKey) {
        return new Key(inputKey);
    }

    private Targets initTarget(Value value, String userInputTime) {
        return new Targets(value, userInputTime);
    }

    public void remove(String userInputKey) {
        Key key = initKey(userInputKey);
        repository.remove(key);
    }
}
