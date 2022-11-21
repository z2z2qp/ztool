package com.github.z2z2qp.tool.bean;

import junit.framework.TestCase;

public class BuilderTest extends TestCase {

    public void testBuild() {
        var s = Builder.builder(Student::new)
                .with(Student::setAge, 18)
                .with(Student::setName, "a")
                .build();
        assertEquals(18, s.getAge());
        assertEquals("a", s.getName());
    }
}

class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}