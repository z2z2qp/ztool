package com.github.z2z2qp.tool.bean;

import java.util.*;
import java.util.function.Function;

/**
 * 类型转换
 */
public class Cast {
    /**
     * 强制类型转换 消除编译提示
     *
     * @param obj 待转换类型
     * @param <T> 转换后类型
     * @return 转换后对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    public static <T> T cast(Object obj, Class<T> clazz) {
        return clazz.cast(obj);
    }


    /**
     * 类型转换
     *
     * @param source   源对象
     * @param function 转换规则
     * @param <T>      目标范型
     * @param <S>      源范型
     * @return 转换后的对象
     */
    public static <S, T> T cast(S source, Function<S, T> function) {
        if (source == null) {
            return cast(null);
        }
        Objects.requireNonNull(function, "转换规则不能为空");
        return function.apply(source);
    }

    /**
     * 将 List&lt;S&gt; 集合转换 为 List&lt;T&gt;
     *
     * @param list     待转换集合
     * @param function 转换规则
     * @param <T>      目标范型
     * @param <S>      源范型
     * @return 转换后待结果
     */
    public static <S, T> List<T> cast(List<S> list, Function<S, T> function) {
        Objects.requireNonNull(list, "待转集合不能为空");
        Objects.requireNonNull(function, "转换规则不能为空");
        return list.parallelStream().collect(ArrayList::new,
                (r, t) -> r.add(function.apply(t)),
                List::addAll);
    }


    /**
     * 将集合转换为 map key 安规则生成，value为集合本身元素
     *
     * @param list     待转换集合
     * @param function key生成规则
     * @param <K>      key
     * @param <V>      value
     * @return 转换后待集合
     */
    public static <K, V> Map<K, V> listToMap(Collection<V> list, Function<V, K> function) {
        Objects.requireNonNull(list, "待转集合不能为空");
        Objects.requireNonNull(function, "转换规则不能为空");
        return list.parallelStream().collect(HashMap::new,
                (r, t) -> r.put(function.apply(t), t),
                Map::putAll);
    }

    /**
     * 将集合转换为 map key 安规则生成，value安规则生成
     *
     * @param list          待转换集合
     * @param keyFunction   key生成规则
     * @param valueFunction key生成规则
     * @param <K>           key
     * @param <T>           源数据范型
     * @param <V>           value
     * @return 转换后待集合
     */
    public static <K, V, T> Map<K, V> listToMap(Collection<T> list, Function<T, K> keyFunction, Function<T, V> valueFunction) {
        Objects.requireNonNull(list, "待转集合不能为空");
        Objects.requireNonNull(keyFunction, "key转换规则不能为空");
        Objects.requireNonNull(valueFunction, "value转换规则不能为空");
        return list.parallelStream().collect(HashMap::new,
                (r, t) -> r.put(keyFunction.apply(t), valueFunction.apply(t)),
                Map::putAll);
    }
}
