package com.example.keisuke.myapplication;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by keisuke on 2015/03/22.
 */
@Table(name = "Shots")
public class Shots extends Model {

    public static final String TAG = Shots.class.getSimpleName();

    @Column(name = "Shots_Id")
    public int shotsId;

    @Column(name = "Category")
    public String category;

    @Column(name = "Title")
    public String title;

    @Column(name = "Image_Url")
    public String imageUrl;

    @Column(name = "Image_Teaser_Url")
    public String imageTeaserUrl;

    @Column(name = "Player_Name")
    public String playerName;

    @Column(name = "Likes_Count")
    public int likesCount;

    public Shots() {
        super();
    }

// クエリを使うことができます。

    // idを指定してデータを取得します。
    public static Shots getShots(String shotsId) {
        return new Select()
                .from(Shots.class)
                .where("Shots_Id = ?", shotsId)
                .executeSingle();
    }

    // categoryを指定してデータのリストを取得します。
    public static List getCategoryList(String category) {
        return new Select()
                .from(Shots.class)
                .where("Category = ?", category)
                .orderBy("Shots_Id ASC")
                .execute();
    }

    public static List<Shots> getCategoryList(String mCategory, int mCount) {
        return null;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setShotsId(int shotsId) {
        this.shotsId = shotsId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String image_url) {
        this.imageUrl = image_url;
    }

    public void setImageTeaserUrl(String image_teaser_url) {
        this.imageTeaserUrl = image_teaser_url;
    }

    public void setLikesCount(int likes_count) {
        this.likesCount = likes_count;
    }

    public static Shots getShots(int id) {
        return null;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }
}