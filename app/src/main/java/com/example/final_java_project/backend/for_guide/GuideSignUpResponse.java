package com.example.final_java_project.backend.for_guide;

public class GuideSignUpResponse {

//    {
//        "id": "01012345678",
//        "password": "can use number"
//        "name": "can use number"
//        "region": "can use number"
//        "star": "can use number"
//        "ment": "can use number"
//        "totalCount": "can use number"

/*    /guide/signIn
    String name
    String region
    double star
    int totalCount
    String ment*/
//    }
    private String name;
    private String region;
    private double star;
    private String ment;
    private int totalCount;

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

    @Override
    public String toString() {
        return "RepoCheckAlready{" +
                ", name='" + name + '\'' +
                ", region'" + region + '\'' +
                ", ment" + ment + '\'' +
                ", star" + star + '\'' +
                ", totalCount" + totalCount + '\'' +
                '}';
    }
}
