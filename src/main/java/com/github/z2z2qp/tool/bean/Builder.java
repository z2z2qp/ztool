package com.github.z2z2qp.tool.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 实体类Builder工具
 *
 * @param <E> 需要构建的实体类
 */
public class Builder<E> {
    /**
     * 获取实体类
     */
    private final Supplier<E> supplier;

    /**
     * 设置实体类属性集合
     */
    private final List<Consumer<E>> setList;

    /**
     * 私有构造方法
     *
     * @param supplier 实体获取
     */
    private Builder(Supplier<E> supplier) {
        this.supplier = supplier;
        this.setList = new ArrayList<>();
    }

    /**
     * 静态方法创建 builder
     *
     * @param supplier 对象生成器
     * @param <E>      对象
     * @return builder
     */
    public static <E> Builder<E> builder(Supplier<E> supplier) {
        return new Builder<>(supplier);
    }

    /**
     * 设置属性
     *
     * @param consumer 属性设置器
     * @param p        设置的属性值
     * @param <P>      属性类型
     * @return builder
     */
    public <P> Builder<E> with(BiConsumer<E, P> consumer, P p) {
        setList.add(e -> consumer.accept(e, p));
        return this;
    }

    /**
     * 创建实体
     *
     * @return 创建好的实体
     */
    public E build() {
        var e = supplier.get();
        setList.forEach(it -> it.accept(e));
        return e;
    }

}
