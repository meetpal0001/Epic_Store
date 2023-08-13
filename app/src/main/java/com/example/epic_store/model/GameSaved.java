package com.example.epic_store.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GameSaved {
    @PrimaryKey @NonNull
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String discPrice;
    private String orgPrice;
    private String publisher;
    private String relDate;

    private String url;

    public GameSaved(String id, String title, String description, String imageUrl, String discPrice, String orgPrice, String publisher, String relDate, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.discPrice = discPrice;
        this.orgPrice = orgPrice;
        this.publisher = publisher;
        this.relDate = relDate;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDiscPrice() {
        return discPrice;
    }

    public void setDiscPrice(String discPrice) {
        this.discPrice = discPrice;
    }

    public String getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(String orgPrice) {
        this.orgPrice = orgPrice;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRelDate() {
        return relDate;
    }

    public void setRelDate(String relDate) {
        this.relDate = relDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
