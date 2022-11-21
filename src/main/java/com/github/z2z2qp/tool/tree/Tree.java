package com.github.z2z2qp.tool.tree;

import java.util.ArrayList;
import java.util.List;

public record Tree(
        String id,
        String text,
        String pid,
        List<Tree> children) {
    public Tree(String id,String text,String pid){
        this(id,text,pid,new ArrayList<>());
    }
}
