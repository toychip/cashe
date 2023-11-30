package nice.cache.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import nice.cache.domain.cache_component.Targets;
import nice.cache.domain.cache_component.Value;
import nice.cache.domain.cache_component.repository_component.Key;
import nice.cache.domain.cache_component.repository_component.Target;
import nice.cache.domain.cache_component.repository_component.UntilTime;
import nice.cache.domain.exception.InputNotExistsKeyException;
import nice.cache.domain.remove.Remove;

public class Cache {

    private static final ConcurrentHashMap<Key, Targets> repository = new ConcurrentHashMap<>();
    private static final Logger logger = Logger.getLogger(Cache.class.getName());
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

    private static void removeExpiredEntries() {
        LocalDateTime now = LocalDateTime.now();
        repository.entrySet().removeIf(entry -> entry.getValue().getUntilValue().isBefore(now));
    }

    private final Value maxValue;
    private final Remove remove;

    private Cache(int size) {
        maxValue = initValue(size);
        remove = initRemove();
        logger.info("올바른 결과를 보려면 귀하의 객체에 @toString이 알맞게 재정의되어있어야합니다.");
    }

    public static Cache from(int value) {
        return new Cache(value);
    }

    private Remove initRemove() {
        return new Remove(repository);
    }

    private Value initValue(int size) {
        return new Value(size);
    }

    public Targets get(String inputKey) {
        Key key = new Key(inputKey);
        return getTargets(key);
    }

    private Targets getTargets(Key key) {
        Targets targets = getTargetsByKey(key);
        UntilTime untilTime = getUntilTime(targets);
        printLog(targets, untilTime);
        return targets;
    }

    private Targets getTargetsByKey(Key key) {
        return Optional.ofNullable(repository.get(key))
                .orElseThrow(InputNotExistsKeyException::new);
    }

    private UntilTime getUntilTime(Targets targets) {
        return targets.getUntilTime();
    }

    public List<Targets> getAll() {
        List<Targets> allTargets = repository.values().stream().collect(Collectors.toList());
        allTargets.forEach(targets -> {
            UntilTime untilTime = targets.getUntilTime();
            logger.info("----- 전체 조회중...---");
            printLog(targets, untilTime);
            logger.info("----- 전체 조회끝 -----");
        });

        return allTargets;
    }

    public Targets put(String inputKey, Object... userTargets) {
        LocalDateTime userTime = LocalDateTime.MAX;
        return initData(inputKey, userTime, userTargets);
    }

    public Targets putWithTime(String inputKey, long inputUserTime, Object... userTargets) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime userTime = now.plusSeconds(inputUserTime);
        return initData(inputKey, userTime, userTargets);
    }

    private Targets initData(String inputKey, LocalDateTime userTime, Object... userTargets) {
        validOverFlow();
        Key key = initKey(inputKey);
        return judgePut(key, userTime, userTargets);
    }

    private Key initKey(String inputKey) {
        return new Key(inputKey);
    }

    private void validOverFlow() {
        while (repository.size() > maxValue.getSize()) {
            remove.removeStrategy();
        }
    }

    private Targets judgePut(Key key, LocalDateTime userTime, Object... userTargets) {
        Targets findTargets = repository.get(key);
        Targets savedTargets = saveData(key, userTime, userTargets);
        if (findTargets == null) {
            return savedTargets;
        }
        return findTargets;
    }

    private Targets saveData(Key key, LocalDateTime userTime, Object... userTargets) {
        UntilTime untilTime = createUntilTime(userTime);
        List<Target> targets = createTargets(userTargets);
        Targets savedTarget = Targets.of(targets, untilTime);
        return saveLog(key, untilTime, savedTarget);
    }

    private UntilTime createUntilTime(LocalDateTime userTime) {
        return new UntilTime(userTime);
    }

    private List<Target> createTargets(Object... userTargets) {
        return Arrays.stream(userTargets)
                .map(Target::new)
                .collect(Collectors.toList());
    }

    public void remove(String userInputKey) {
        Key key = initKey(userInputKey);
        Targets targets = getTargetsByKey(key);
        UntilTime untilTime = getUntilTime(targets);
        removeLog(key, targets, untilTime);
    }

    private Targets saveLog(Key key, UntilTime untilTime, Targets savedTarget) {
        logger.info("----- 값 삽입 중... ---");
        repository.put(key, savedTarget);
        logger.info("----- 값 삽입 끝. -----");
        printLog(savedTarget, untilTime);
        return savedTarget;
    }

    private void printLog(Targets targets, UntilTime untilTime) {
        logger.info(targets.toString() + " 객체의 유효기간은 " + untilTime + "까지입니다.");
    }

    private void removeLog(Key key, Targets targets, UntilTime untilTime) {
        printLog(targets, untilTime);
        logger.info("----- 삭제중...---");
        repository.remove(key);
        logger.info("----- 삭제끝 -----");
    }
}
