package nice.cashe.domain.cashe_component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import nice.cashe.domain.cashe_component.repository_component.Target;
import nice.cashe.domain.cashe_component.repository_component.UntilTime;

public class Targets {
    private final List<Target> targets;
    private final UntilTime untilTime;

    public Targets(int count, String userInputTime) {
        this.targets = toTargets(count);
        this.untilTime = toUntilTile(userInputTime);
    }

    private List<Target> toTargets(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> createTarget())
                .collect(Collectors.toList());
    }

    private UntilTime toUntilTile(String userInputTime) {
        return new UntilTime(userInputTime);
    }

    private Target createTarget() {
        return new Target();
    }

    public List<Target> getTargets() {
        return targets;
    }

    public LocalDateTime getUntilTime() {
        return untilTime.getTimeValue();
    }
}
