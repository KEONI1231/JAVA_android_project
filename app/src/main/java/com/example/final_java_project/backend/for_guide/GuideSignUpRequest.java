package com.example.final_java_project.backend.for_guide;

public class GuideSignUpRequest {

//    {
//        "id": "01012345678",
//        "password": "can use number"
//        "name": "can use number"
//        "region": "can use number"
//        "star": "can use number"
//        "ment": "can use number"
//        "totalCount": "can use number"
//    }

    private boolean isRight = false;
    private String id;
    private String password;
    private String name;
    private String region;
    private double star;
    private String ment;
    private int totalCount;

    public GuideSignUpRequest(String phone) {
        this.id = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {this.password = password;}

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public String getMent() {
        return ment;
    }

    public void setMent(String ment) {
        this.ment = ment;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    @Override
    public String toString() {
        return "RepoCheckAlready{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", region'" + region + '\'' +
                ", ment" + ment + '\'' +
                ", star" + star +'\'' +
                ", totalCount" + totalCount +'\''+
                '}';
    }
}
