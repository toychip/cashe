package nice.cashe.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import nice.cashe.domain.cashe_component.Targets;
import nice.cashe.domain.cashe_component.Value;
import nice.cashe.domain.cashe_component.repository_component.Key;
import nice.cashe.domain.cashe_component.repository_component.Target;
import nice.cashe.domain.cashe_component.repository_component.UntilTime;
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
        repository.entrySet().removeIf(entry -> entry.getValue().getUntilValue().isBefore(now));
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

    public void put(String inputKey, Object... userTargets) {
        saveData(inputKey, userTargets);
    }

    private void saveData(String inputKey, Object... userTargets) {
        // 키 생성
        Key key = initKey(inputKey);
        // 초기화 되어있는 시간 추출
        UntilTime untilTime = repository.get(key).getUntilTime();
        // 유저가 입력한 객체를 컬렉션으로 변환
        List<Target> targets = getTargets(userTargets);
        // List<객체>와 유효 시간을 Targets으로 생성
        Targets savedTarget = Targets.saveOf(targets, untilTime);
        repository.put(key, savedTarget);
    }

    private List<Target> getTargets(Object[] userTargets) {
        return Arrays.stream(userTargets)
                .map(Target::new)
                .collect(Collectors.toList());
    }

    // 초기화용 메서드
    public void put(String inputKey, String inputValue, String userInputTime) {
        initData(inputKey, inputValue, userInputTime);
    }

    private void initData(String inputKey, String inputValue, String userInputTime) {
        Key key = initKey(inputKey);
        Value value = initValue(inputValue);
        Targets target = initTargets(value, userInputTime);
        repository.put(key, target);
    }

    private Key initKey(String inputKey) {
        return new Key(inputKey);
    }

    private Value initValue(String inputValue) {
        return new Value(inputValue);
    }

    private Targets initTargets(Value value, String userInputTime) {
        return Targets.initOf(value, userInputTime);
    }

    public void remove(String userInputKey) {
        Key key = initKey(userInputKey);
        repository.remove(key);
    }
}
