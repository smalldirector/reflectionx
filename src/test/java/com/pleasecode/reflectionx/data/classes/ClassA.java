package com.pleasecode.reflectionx.data.classes;

public class ClassA {
    private Integer x;
    private Integer y;
    private String name;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassA classA = (ClassA) o;

        if (x != null ? !x.equals(classA.x) : classA.x != null) return false;
        if (y != null ? !y.equals(classA.y) : classA.y != null) return false;
        return !(name != null ? !name.equals(classA.name) : classA.name != null);

    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }
}
