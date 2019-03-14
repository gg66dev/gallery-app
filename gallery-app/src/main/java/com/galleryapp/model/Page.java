package com.galleryapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        indexes = {
                @Index(name = "page_url_idx", columnList = "url"),
       },
        uniqueConstraints = {
                @UniqueConstraint(name = "page_url_unique", columnNames = {"url"}),
        }
)
public class Page {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * image realted to page (for home this attribute is null)
     */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "image_id")
    private Image image;

    /**
     * url of page
     */
    @NotNull
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
