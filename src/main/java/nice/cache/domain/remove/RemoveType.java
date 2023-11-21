package nice.cache.domain.remove;

public enum RemoveType {

    PAST_DATE("가장 오래된 키를 지웁니다 - 1", 1),
    RECENT_DATE("가장 최근 저장된 키를 지웁니다. - 2", 2),
    MAX_ITEM("객체 수가 가장 많은 키를 지웁니다. - 3", 3),
    MIN_ITEM("객체 수가 가장 적은 키를 지웁니다. -4", 4),
    ALL("모든 키를 지웁니다 - 5", 5),
    ;

    private final String description;
    private final int choose;

    RemoveType(String description, int choose) {
        this.description = description;
        this.choose = choose;
    }

    public static RemoveType defaultType() {
        return PAST_DATE;
    }

    public static RemoveType create(int choose) {
        for (RemoveType removeType : RemoveType.values()) {
            if (removeType.getChoose() == choose) {
                return removeType;
            }
        }
        return PAST_DATE;
    }

    public String getDescription() {
        return description;
    }

    public int getChoose() {
        return choose;
    }



}
