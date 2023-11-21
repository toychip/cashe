package nice.cache.domain.cache_component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import nice.cache.domain.cache_component.repository_component.Target;
import nice.cache.domain.cache_component.repository_component.UntilTime;

public class Targets {
    private final List<Target> targets;
    private final UntilTime untilTime;

    private Targets(List<Target> targets, UntilTime untilTime) {
        this.targets = targets;
        this.untilTime = untilTime;
    }

    public static Targets of(List<Target> targets, UntilTime untilTime) {
        return new Targets(targets, untilTime);
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
