package nice.cache.domain;

public class Exdata {
    private int age;
    private String name;

    public Exdata(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exdata 타입의 객체: 나이=" + age + ", 이름=" + name;
    }
}
