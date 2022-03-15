package dto;

import java.util.List;
import java.util.Map;

public class OpenRecipe {
    private String data1;
    private String data2;
    private String data3;
    private List<Map<String, String>> data4;  // 조리방법 세부, 이미지

    private int seq;  // 일련번호
    private String name;  // 이름
    private String way;  // 조리방법
    private String pat;  // 요리종류
    private String eng;  // 열량
    private String pro;  // 단백질
    private String fat;  // 지방
    private String na; // 나트륨
    private String mainImg;  // 메인이미지
    private String ingrs;  // 재료정보

    @Override
    public String toString() {
        return "OpenRecipe{" +
                "data1='" + data1 + '\'' +
                ", data2='" + data2 + '\'' +
                ", data3='" + data3 + '\'' +
                ", data4='" + data4 + '\'' +
                '}';
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getNa() {
        return na;
    }

    public void setNa(String na) {
        this.na = na;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getIngrs() {
        return ingrs;
    }

    public void setIngrs(String ingrs) {
        this.ingrs = ingrs;
    }

    public List<Map<String, String>> getData4() {
        return data4;
    }

    public void setData4(List<Map<String, String>> data4) {
        this.data4 = data4;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

}
