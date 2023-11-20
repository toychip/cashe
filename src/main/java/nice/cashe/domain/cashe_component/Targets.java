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
    private static final Logger logger = Logger.getLogger(Cashe.class.getName());
    private final List<Target> targets;
    private final UntilTime untilTime;

    public Targets(Value value, String userInputTime) {
        targets = toTargets(value);
        untilTime = toUntilTile(userInputTime);
        logger.info(targets + "객체들" + untilTime + "까지 유효");
    }

    private List<Target> toTargets(Value value) {
        return IntStream.range(0, value.getCount())
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

    @Override
    public String toString() {
        return targets.stream().map(target -> target.toString() + ", ").toString();
    }
}
