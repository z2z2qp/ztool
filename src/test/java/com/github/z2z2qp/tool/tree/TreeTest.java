package com.github.z2z2qp.tool.tree;

public class TreeTest {

    private String tId;
    private String name;

    private String ppId;

    private String ttt;

    public TreeTest(String tId, String name, String ppId) {
        this.tId = tId;
        this.name = name;
        this.ppId = ppId;
        this.ttt = tId + name + ppId;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPpId() {
        return ppId;
    }

    public void setPpId(String ppId) {
        this.ppId = ppId;
    }

    public String getTtt() {
        return ttt;
    }

    public void setTtt(String ttt) {
        this.ttt = ttt;
    }

    @Override
    public String toString() {
        return "TreeTest{" +
                "tId='" + tId + '\'' +
                ", name='" + name + '\'' +
                ", ppId='" + ppId + '\'' +
                ", ttt='" + ttt + '\'' +
                '}';
    }
}
