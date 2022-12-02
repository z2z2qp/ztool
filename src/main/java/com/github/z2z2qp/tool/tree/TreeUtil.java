package com.github.z2z2qp.tool.tree;


import com.github.z2z2qp.tool.bean.Cast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * 构建树
 */
public class TreeUtil {

    /**
     * 树集合构建树
     *
     * @param list 树集合
     * @return 构建好的树
     */
    public static List<Tree> createTree(List<? extends Tree> list) {
        Objects.requireNonNull(list, "源集合不能为空");
        return createTree(list, it -> it);
    }

    /**
     * 含树结构任意集合构建数据
     *
     * @param list     含树结构集合
     * @param id       id获取方法
     * @param pid      父id获取方法
     * @param children 子节点集合获取方法
     * @param <T>      含数据结构实体
     * @return 构建好的树
     */
    public static <T> List<T> createTree(List<T> list,
                                         Function<T, String> id,
                                         Function<T, String> pid,
                                         Function<T, List<T>> children) {
        Objects.requireNonNull(list, "源集合不能为空");
        Objects.requireNonNull(id, "id规则不能为空");
        Objects.requireNonNull(pid, "pid规则不能为空");
        Objects.requireNonNull(children, "children规则不能为空");
        var map = Cast.listToMap(list, id);
        return createTree0(map, pid, children);
    }

    /**
     * 含树结构任意集合构建数据
     *
     * @param list        含树结构集合
     * @param treeCreator 含树结构转树
     * @param <T>         含数据结构实体
     * @return 构建好的树
     */
    public static <T> List<Tree> createTree(List<T> list, Function<T, Tree> treeCreator) {
        Objects.requireNonNull(list, "源集合不能为空");
        Objects.requireNonNull(treeCreator, "树生成器不能为空");
        var map = Cast.listToMap(list, treeCreator.andThen(Tree::id), treeCreator);
        return createTree0(map, Tree::pid, Tree::children);
    }

    private static <T> List<T> createTree0(Map<String, T> nodeMap,
                                           Function<T, String> pidFunc,
                                           Function<T, List<T>> childrenFunc) {
        var root = new ArrayList<T>();
        nodeMap.values().forEach(it -> {
            var pid = pidFunc.apply(it);
            if (pid != null) {
                var parentNode = nodeMap.get(pid);
                if (parentNode != null) {
                    childrenFunc.apply(parentNode).add(it);
                } else {
                    root.add(it);
                }
            } else {
                root.add(it);
            }
        });
        return root;
    }
}
