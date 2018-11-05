package com.example.xia.demo.bean;


/**
 * @User Xiahangli
 * @Date 2018/10/23  10:37
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class JuheDream extends MyResult {

    private String title;
    private String content;
    private String authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "JuheDream{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authors='" + authors + '\'' +
                '}';
    }
}
