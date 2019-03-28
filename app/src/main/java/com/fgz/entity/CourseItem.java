package com.fgz.entity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * 作者： FGZ
 * 功能： com.fgz.entity
 * 创建日期： 2019-03-07
 */
public class CourseItem {
    public String getCourseBg() {
        return courseBg;
    }

    public void setCourseBg(String courseBg) {
        this.courseBg = courseBg;
    }

    private String courseBg;//课程背景框

    private Bitmap courseCover;//课程背景图

    public Bitmap getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(Bitmap courseCover) {
        this.courseCover = courseCover;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private String courseName;//课程名称

}
