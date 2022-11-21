package com.github.z2z2qp.tool.tree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        var map = new HashMap<String, Tree>();
        list.forEach(it -> {
            map.put(it.id(), it);
        });
        return createTree0(map);
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
        var map = new HashMap<String, Tree>();
        list.forEach(it -> {
            var tree = treeCreator.apply(it);
            map.put(tree.id(), tree);
        });
        return createTree0(map);
    }

    private static List<Tree> createTree0(Map<String, Tree> nodeMap) {
        var root = new ArrayList<Tree>();
        nodeMap.values().forEach(it -> {
            if (it.pid() != null) {
                var parentNode = nodeMap.get(it.pid());
                if (parentNode != null) {
                    parentNode.children().add(it);
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
