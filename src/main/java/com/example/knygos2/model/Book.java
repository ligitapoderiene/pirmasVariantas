package com.example.knygos2.model;

public class Book {
    private  int id;
    private String title;
    private int page;
    private String category;
    private String autor;
    private String summary;

    public Book(String id,String title,int page,String category,String autor,String summary){
    }
    public Book(String title,int page,String category,String autor, String summary){
        this.title  =title;
        this.page =page;
        this.category = category;
        this.autor = autor;
        this.summary = summary;
    }
    public Book(int id, String title,int page, String category,String autor, String summary){
        this.id = id;
        this.title  =title;
        this.page =page;
        this.category = category;
        this.autor = autor;
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", page=" + page +
                ", category='" + category + '\'' +
                ", autor='" + autor + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
