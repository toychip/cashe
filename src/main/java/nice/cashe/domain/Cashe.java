package nice.cashe.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import nice.cashe.domain.cashe_component.Targets;
import nice.cashe.domain.cashe_component.Value;
import nice.cashe.domain.cashe_component.repository_component.Key;
import nice.cashe.domain.cashe_component.repository_component.Target;
import nice.cashe.domain.cashe_component.repository_component.UntilTime;
import nice.cashe.domain.exception.InputNotExistsKeyException;

public class Cashe {

    private static final ConcurrentHashMap<Key, Targets> repository = new ConcurrentHashMap<>();
    private static final Logger logger = Logger.getLogger(Cashe.class.getName());
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
        logger.info("올바른 결과를 보려면 귀하의 객체에 @toString이 알맞게 재정의되어있어야합니다.");
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
        Targets targets = Optional.ofNullable(repository.get(key))
                .orElseThrow(InputNotExistsKeyException::new);
        UntilTime untilTime = getUntilTime(targets);

        printLog(targets, untilTime);
        return targets;
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

    private void printLog(Targets targets, UntilTime untilTime) {
        logger.info(targets.toString() + " 객체의 유효기간은 " + untilTime + "까지입니다.");
    }

    public void put(String inputKey, Object... userTargets) {
        saveData(inputKey, userTargets);
    }

    private void saveData(String inputKey, Object... userTargets) {
        // 키 생성
        Key key = initKey(inputKey);
        // 초기화 되어있는 시간 추출
        UntilTime untilTime = getUntilTime(repository.get(key));
        // 유저가 입력한 객체를 컬렉션으로 변환
        List<Target> targets = createTargets(userTargets);
        // List<객체>와 유효 시간을 Targets으로 생성

        Targets savedTarget = Targets.saveOf(targets, untilTime);
        logger.info("----- 값을 삽입중...---");
        repository.put(key, savedTarget);
        logger.info("----- 값 삽입 끝. -----");
        printLog(savedTarget, untilTime);
    }

    private List<Target> createTargets(Object... userTargets) {
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
        Targets targets = initTargets(value, userInputTime);
        UntilTime untilTime = getUntilTime(targets);
        printLog(targets, untilTime);
        logger.info("----- 초기화 중...---");
        repository.put(key, targets);
        logger.info("----- 초기화 끝 -----");
    }

    private UntilTime getUntilTime(Targets targets) {
        return targets.getUntilTime();
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
        Targets targets = repository.get(key);
        UntilTime untilTime = getUntilTime(targets);
        printLog(targets, untilTime);
        logger.info("----- 삭제합니다 -----");
        repository.remove(key);
    }
}
