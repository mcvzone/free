package com.free.module.common.model.vo;

import java.util.List;

public class NoticeVo {
    private String seq;
    private String title;
    private String start_date;
    private String end_date;
    private String writer;
    private String reg_date;
    private String description;
    private String red_count;
    private String use_yn;
    private List<String> attachs;
    
    public String getSeq() {
        return seq;
    }
    public void setSeq(String seq) {
        this.seq = seq;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStart_date() {
        return start_date;
    }
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
    public String getEnd_date() {
        return end_date;
    }
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getReg_date() {
        return reg_date;
    }
    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRed_count() {
        return red_count;
    }
    public void setRed_count(String red_count) {
        this.red_count = red_count;
    }
    public String getUse_yn() {
        return use_yn;
    }
    public void setUse_yn(String use_yn) {
        this.use_yn = use_yn;
    }
    public List<String> getAttachs() {
        return attachs;
    }
    public void setAttachs(List<String> attachs) {
        this.attachs = attachs;
    }
}
