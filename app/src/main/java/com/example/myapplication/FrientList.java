package com.example.myapplication;
public class FrientList {
    String FirstName,Lastname;
    int Image;
    public FrientList(String firstName, String lastname, int image) {
        FirstName = firstName;
        Lastname = lastname;
        Image = image;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }
    public int getImage() {
        return Image;
    }
    public void setImage(int image) {
        Image = image;
    }
}
