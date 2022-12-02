package com.github.z2z2qp.tool.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树结构
 *
 * @param id       主键
 * @param text     内容
 * @param pid      父健
 * @param children 子元素
 */
public record Tree(
        String id,
        String text,
        String pid,
        List<Tree> children) {
    public Tree(String id, String text, String pid) {
        this(id, text, pid, new ArrayList<>());
    }
}
