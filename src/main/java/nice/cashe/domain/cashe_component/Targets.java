package nice.cashe.domain.cashe_component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import nice.cashe.domain.Cashe;
import nice.cashe.domain.cashe_component.repository_component.Target;
import nice.cashe.domain.cashe_component.repository_component.UntilTime;

public class Targets {
    private final List<Target> targets;
    private final UntilTime untilTime;

    // 초기화용
    private Targets(Value value, String userInputTime) {
        targets = initTargets(value);
        untilTime = toUntilTime(userInputTime);
    }

    // 데이터 값 삽입용
    private Targets(List<Target> targets, UntilTime untilTime) {
        this.targets = targets;
        this.untilTime = untilTime;
    }

    // 초기화용
    public static Targets initOf(Value value, String userInputTime) {
        return new Targets(value, userInputTime);
    }

    // 데이터 값 삽입용
    public static Targets saveOf(List<Target> targets, UntilTime untilTime) {
        return new Targets(targets, untilTime);
    }

    private List<Target> initTargets(Value value) {
        return IntStream.range(0, value.getCount())
                .mapToObj(i -> createTarget())
                .collect(Collectors.toList());
    }

    private UntilTime toUntilTime(String userInputTime) {
        return new UntilTime(userInputTime);
    }

    private Target createTarget() {
        return new Target(new Object());
    }

    public List<Target> getTargets() {
        return targets;
    }

    public LocalDateTime getUntilValue() {
        return untilTime.getTimeValue();
    }

    public UntilTime getUntilTime() {
        return untilTime;
    }

    @Override
    public String toString() {
        return targets.stream()
                .map(Target::toString)
                .collect(Collectors.joining(", "));
    }
}
