package nice.cashe.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import nice.cashe.domain.cashe_component.Targets;
import nice.cashe.domain.cashe_component.repository_component.Target;
import nice.cashe.domain.cashe_component.repository_component.UntilTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CasheTest {

    private Cashe cashe;
    String key = "kettttt";
    @BeforeEach
    void init() {
        cashe = new Cashe();

        cashe.put(key, "3", "2023.11.20 16:41");
        Exdata person1 = new Exdata(24, "임준형");
        Exdata person2 = new Exdata(25, "김경민");
        cashe.put(key, person1, person2);
        cashe.getAll();
    }

    @Test
    void get() {
        Targets targets = cashe.get(key);
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