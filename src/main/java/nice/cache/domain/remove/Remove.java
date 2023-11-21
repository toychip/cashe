package nice.cache.domain.remove;

import static nice.cache.domain.remove.RemoveType.PAST_DATE;
import static nice.cache.domain.remove.RemoveType.RECENT_DATE;
import static nice.cache.domain.remove.RemoveType.defaultType;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import nice.cache.domain.cache_component.Targets;
import nice.cache.domain.cache_component.repository_component.Key;

public class Remove {
    private final ConcurrentHashMap<Key, Targets> repository;
    private final RemoveType removeType;

    public Remove(ConcurrentHashMap<Key, Targets> repository) {
        this.repository = repository;
        removeType = defaultType();
    }

    // default -> 가장 오래된 날짜 삭제
    public void removeStrategy() {
        removePastDate();
    }

    public void removeStrategy(int choose) {
        chooseStrategy(choose);
    }

    private void chooseStrategy(int choose) {
        // 가장 오래된
        if (choose == PAST_DATE.getChoose()) {
            removePastDate();
        }

        // 가장 최근
        if (choose == RECENT_DATE.getChoose()) {
            removeRecentDate();
        }

        // Item의 개수가 적은 Key: Value 쌍을 지움
        if (choose == RemoveType.MIN_ITEM.getChoose()) {
            removeMinObjectSize();
        }

        // Item의 개수가 적은 Key: Value 쌍을 지움
        if (choose == RemoveType.MAX_ITEM.getChoose()) {
            removeMaxObjectSize();
        }
    }

    private void removePastDate() {
        Optional<Entry<Key, Targets>> pastTargets = repository.entrySet().stream()
                .min(Comparator.comparing(entry -> entry.getValue().getUntilValue()));

        removeEntryByKey(pastTargets);
    }

    private void removeRecentDate() {
        Optional<Entry<Key, Targets>> recentTargets = repository.entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().getUntilValue()));

        removeEntryByKey(recentTargets);
    }

    private void removeMinObjectSize() {
        Optional<Entry<Key, Targets>> minSize = repository.entrySet().stream()
                .min(Comparator.comparing(entry -> entry.getValue().getTargets().size()));
        removeEntryByKey(minSize);
    }

    private void removeMaxObjectSize() {
        Optional<Entry<Key, Targets>> minSize = repository.entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().getTargets().size()));
        removeEntryByKey(minSize);
    }

    private void removeEntryByKey(Optional<Entry<Key, Targets>> minTargets) {
        minTargets.ifPresent(entry -> {
            Key key = entry.getKey();
            repository.remove(key);
        });
    }
}
