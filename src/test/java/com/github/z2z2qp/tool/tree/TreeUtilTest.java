package com.github.z2z2qp.tool.tree;

import com.github.z2z2qp.tool.bean.Cast;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TreeUtilTest extends TestCase {

    public void testCreateTree() {
        var list = List.of(
                new Tree("1", "a", "0"),
                new Tree("2", "b", "1"),
                new Tree("3", "c", "1"),
                new Tree("4", "b", "2"),
                new Tree("5", "c", "3")

        );
        var t = TreeUtil.createTree(list);
        System.out.println(t);
    }

    public void testTestCreateTree() {
        var list = List.of(
                new TreeTest("1", "a", "0"),
                new TreeTest("2", "b", "1"),
                new TreeTest("3", "c", "1"),
                new TreeTest("4", "b", "2"),
                new TreeTest("5", "c", "3")

        );
        var t = TreeUtil.createTree(list, it -> new Tree(it.gettId(), it.getName(), it.getPpId()));
        System.out.println(t);
    }

    public void testTestCreateTree1() {
        var list = List.of(
                new TreeTest("1", "a", "0"),
                new TreeTest("2", "b", "1"),
                new TreeTest("3", "c", "1"),
                new TreeTest("4", "b", "2"),
                new TreeTest("5", "c", "3")

        );
        var treeNodes = Cast.cast(list, TreeNode::new);
        var t = TreeUtil.createTree(treeNodes, TreeNode::gettId, TreeNode::getPpId, TreeNode::getList);
        System.out.println(t);
    }
}

class TreeNode extends TreeTest {

    private List<TreeNode> list;

    public TreeNode(TreeTest tree) {
        super(tree.gettId(), tree.getName(), tree.getPpId());
        list = new ArrayList<>();
    }

    public List<TreeNode> getList() {
        return list;
    }

    public void setList(List<TreeNode> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "[" +
                "id=" + gettId() + ", " +
                "tid=" + gettId() + ", " +
                "name=" + getName() + ", " +
                "ppid=" + getPpId() + ", " +
                "ttt=" + getTtt() + ", " +
                "list=" + getList() + ", " +
                "]";
    }
}