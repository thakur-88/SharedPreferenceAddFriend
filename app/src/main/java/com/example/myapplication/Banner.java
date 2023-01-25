package com.example.myapplication;

public class Banner {
    int image;String imageurl;

    public Banner(int image, String imageurl) {
        this.image = image;
        this.imageurl = imageurl;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
