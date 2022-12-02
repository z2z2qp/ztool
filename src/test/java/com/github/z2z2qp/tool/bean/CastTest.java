package com.github.z2z2qp.tool.bean;

import junit.framework.TestCase;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class CastTest extends TestCase {

    public void testCast() {
        int i = Cast.cast("abc");
        System.out.println(i);
    }

    public void testTestCast() {
        var list = List.of("1", "2", "3");
        var list2 = Cast.cast(list, (Function<String, Integer>) Integer::valueOf);
        System.out.println(list.getClass());
        System.out.println(list2.getClass());
    }

    public void testTestCast2() {
        var a = "1334";
        var i = Cast.cast(a,Integer::valueOf);
        System.out.println(i);
    }

    public void testListToMap() {
        var list = List.of("1", "2", "3");
        var map = Cast.listToMap(list, Objects::hashCode);
        System.out.println(map);
    }
}