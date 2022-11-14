package com.example.smartcaption;

import java.util.Objects;

public class Captions {
    int id;
    private String text, owner, categoris, active;
    private int seen, favorite;

    public Captions(String text, String owner, String categoris, String active, int seen, int favorite) {
        this.text = text;
        this.owner = owner;
        this.categoris = categoris;
        this.active = active;
        this.seen = seen;
        this.favorite = favorite;
    }
    public Captions(int id, String text, String owner, String categoris, String active, int seen, int favorite) {
        this.id = id;
        this.text = text;
        this.owner = owner;
        this.categoris = categoris;
        this.active = active;
        this.seen = seen;
        this.favorite = favorite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCategoris(String categoris) {
        this.categoris = categoris;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getOwner() {
        return owner;
    }

    public String getCategoris() {
        return categoris;
    }

    public String getActive() {
        return active;
    }

    public int getSeen() {
        return seen;
    }

    @Override
    public String toString() {
        return "Captions{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", owner='" + owner + '\'' +
                ", categoris='" + categoris + '\'' +
                ", active='" + active + '\'' +
                ", seen=" + seen +
                ", favorite=" + favorite +
                '}';
    }

    public int getFavorite() {
        return favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Captions)) return false;
        Captions captions = (Captions) o;
        return getId() == captions.getId() && getSeen() == captions.getSeen() && getFavorite() == captions.getFavorite() && Objects.equals(getText(), captions.getText()) && Objects.equals(getOwner(), captions.getOwner()) && Objects.equals(getCategoris(), captions.getCategoris()) && Objects.equals(getActive(), captions.getActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getOwner(), getCategoris(), getActive(), getSeen(), getFavorite());
    }
}
