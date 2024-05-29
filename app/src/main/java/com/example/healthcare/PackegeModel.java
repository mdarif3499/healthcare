package com.example.healthcare;


import java.util.List;

public class PackegeModel {
    List<String> line1;
    List<String> line2;
    List<String> line3;
    List<String> line4;
    List<String> line5;
    List<String> line6;
    List<String> line7;
    List<String> package_details;

    public PackegeModel() {
    }

    public PackegeModel(List<String> line1, List<String> line2, List<String> line3, List<String> line4, List<String> line5, List<String> line6, List<String> line7, List<String> package_details) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.line5 = line5;
        this.line6 = line6;
        this.line7 = line7;
        this.package_details = package_details;
    }

    public List<String> getLine1() {
        return line1;
    }

    public void setLine1(List<String> line1) {
        this.line1 = line1;
    }

    public List<String> getLine2() {
        return line2;
    }

    public void setLine2(List<String> line2) {
        this.line2 = line2;
    }

    public List<String> getLine3() {
        return line3;
    }

    public void setLine3(List<String> line3) {
        this.line3 = line3;
    }

    public List<String> getLine4() {
        return line4;
    }

    public void setLine4(List<String> line4) {
        this.line4 = line4;
    }

    public List<String> getLine5() {
        return line5;
    }

    public void setLine5(List<String> line5) {
        this.line5 = line5;
    }

    public List<String> getLine6() {
        return line6;
    }

    public void setLine6(List<String> line6) {
        this.line6 = line6;
    }

    public List<String> getLine7() {
        return line7;
    }

    public void setLine7(List<String> line7) {
        this.line7 = line7;
    }

    public List<String> getPackage_details() {
        return package_details;
    }

    public void setPackage_details(List<String> package_details) {
        this.package_details = package_details;
    }
}
