package com.fgz.entity;

/**
 * 作者： FGZ
 * 功能： 才艺数据
 * 创建日期： 2019-03-20
 */
public class TalentType {
    private Integer imageUrl;
    private String text;


//    public TalentType(Integer imageUrl,String text){
//        this.imageUrl = imageUrl;
//        this.text = text;
//    }

    public Integer getImageUrl() { return imageUrl; }
    public String getText() { return text; }

    public void setImageUrl(Integer imageUrl) { this.imageUrl = imageUrl;}
    public void setText(String text) {  this.text = text;}
}
