package nice.cashe.domain;

import nice.cashe.domain.cashe_component.Repository;
import nice.cashe.domain.cashe_component.UntilTime;

public class Cashe {
    private final Repository repository;
    private final UntilTime untilTime;

    public Cashe(String key, Object target, String userTime) {
        this.repository = createRepository(key, target);
        this.untilTime = createTime(userTime);
    }

    public Cashe(String key, Object target) {
        this.repository = createRepository(key, target);
        this.untilTime = defaultTime();
    }

    public static Cashe of(String key, Object target) {
        return new Cashe(key, target);
    }

    public static Cashe of(String key, Object target, String localDateTime) {
        return new Cashe(key, target, localDateTime);
    }

    private Repository createRepository(String key, Object target) {
        return null;
    }

    private UntilTime createTime(String userTime) {
        return null;
    }

    private UntilTime defaultTime() {
        return null;
    }


}
