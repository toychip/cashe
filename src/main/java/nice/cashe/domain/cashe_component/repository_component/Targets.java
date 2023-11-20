package nice.cashe.domain.cashe_component.repository_component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Targets {
    private final List<Target> targets;

    public Targets(int count) {
        this.targets = toTargets(count);
    }

    private List<Target> toTargets(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> createTarget())
                .collect(Collectors.toList());
    }

    private Target createTarget() {
        return new Target();
    }

}
