package nice.cashe.domain.cashe_component;

public class Value {
    private final int count;

    public Value(String count) {
        this.count = converter(count);
    }

    private int converter(String count) {
        try {
            return Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    public int getCount() {
        return count;
    }
}
