package com.example.movie.ToTalHome.TotalHome.Main;

public class NowMovieItem {

    private String img_url;
    private String title;
    private String director;


    public NowMovieItem(String img_url, String title, String director) {
        this.img_url = img_url;
        this.title = title;
        this.director = director;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

}
