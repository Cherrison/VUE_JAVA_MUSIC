package ouc.cs.course.java.musicserver.model;

public class Range {
    private String name;
    private int times;

    public Range(String name, int times){
        this.name = name;
        this.times = times;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
