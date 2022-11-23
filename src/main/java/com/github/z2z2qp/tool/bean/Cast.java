package com.github.z2z2qp.tool.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Cast {
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    public static <T, R> List<R> cast(List<T> list, Function<T, R> function) {
        return list.stream().collect(ArrayList::new,
                (r, t) -> r.add(function.apply(t)),
                ArrayList::addAll);
    }
}
