package nice.cashe.domain;

import java.time.LocalDateTime;

public class Cashe {

    private final Key key;
    private final Target target;
    private final UntilTime untilTime;

    public Cashe(Key key, Target target, UntilTime untilTime) {
        this.key = key;
        this.target = target;
        this.untilTime = untilTime;
    }
}
