package com.github.z2z2qp.tool.bean;

import junit.framework.TestCase;

import java.util.List;

public class CastTest extends TestCase {

    public void testCast() {
        int i = Cast.cast("abc");
        System.out.println(i);
    }

    public void testTestCast() {
        var list = List.of("1","2","3");
        var list2 = Cast.cast(list, Integer::valueOf);
        System.out.println(list.getClass());
        System.out.println(list2.getClass());
    }
}