package com.pleasecode.reflectionx.data.classes;

import java.io.Serializable;

public class ClassB implements Serializable {

    private static final long serialVersionUID = 4481885788602442128L;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassB classB = (ClassB) o;

        return id.equals(classB.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "ClassB{" +
                "id='" + id + '\'' +
                '}';
    }
}
