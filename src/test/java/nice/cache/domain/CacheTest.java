package nice.cache.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import nice.cache.domain.cache_component.Targets;
import nice.cache.domain.cache_component.repository_component.Target;
import nice.cache.domain.cache_component.repository_component.UntilTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CacheTest {

    private Cache cache;
    String key = "kettttt";
    @BeforeEach
    void init() {
        cache = new Cache();

        cache.put(key, "3", "2023.11.20 16:41");
        Exdata person1 = new Exdata(24, "임준형");
        Exdata person2 = new Exdata(25, "김경민");
        cache.put(key, person1, person2);
        cache.getAll();
    }

    @Test
    void get() {
        Targets targets = cache.get(key);
        UntilTime untilTime = targets.getUntilTime();
        LocalDateTime timeValue = untilTime.getTimeValue();

        // 마감 시간 검증
        assertEquals(LocalDateTime.parse("2023-11-20T16:41"), timeValue);

        List<Target> targetsList = targets.getTargets();
        // Exdata 타입인지 확인
        boolean allExdata = targetsList.stream()
                .map(Target::getData) // Target 클래스에 getData 메서드가 있어야 합니다.
                .allMatch(data -> data instanceof Exdata);

        // 모든 객체가 Exdata 타입인지 검증
        assertEquals(true, allExdata);
    }
}