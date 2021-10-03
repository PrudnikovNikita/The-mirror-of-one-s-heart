package com.example.prudnikovapp.models;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String name = "Виталий Эрнстович Жмур";
    private String email = "Shpak1488@burov";
    private String age = "27";

    private int lvl = 0;
    private String description = "Пажилой транквилизатор";

    private Image avatar;
    private Image lvlPick;

    public User() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public void setLvlPick(Image lvlPick) {
        this.lvlPick = lvlPick;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public int getLvl() {
        return lvl;
    }

    public String getDescription() {
        return description;
    }

    public Image getAvatar() {
        return avatar;
    }

    public Image getLvlPick() {
        return lvlPick;
    }

    public User(Parcel in) {
        name = in.readString();
        email = in.readString();
        age = in.readString();
        lvl = in.readInt();
        description = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(age);
        parcel.writeInt(lvl);
        parcel.writeString(description);
    }
}
