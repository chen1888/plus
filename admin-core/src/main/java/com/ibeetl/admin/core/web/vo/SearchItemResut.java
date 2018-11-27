package com.ibeetl.admin.core.web.vo;

/**
 * @Author: chenxi
 * @Date: 2018-11-21 14:26
 * @description:
 **/
public class SearchItemResut {

    private String bookPicture;
    private String bookName;
    private String author;
    private String intro;
    private String update;
    private String wordNum;

    public String getBookPicture() {
        return bookPicture;
    }

    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getWordNum() {
        return wordNum;
    }

    public void setWordNum(String wordNum) {
        this.wordNum = wordNum;
    }
}
