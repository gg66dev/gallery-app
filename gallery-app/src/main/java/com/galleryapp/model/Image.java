package com.galleryapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(
        indexes = {
                @Index(name = "image_name_idx", columnList = "name"),
       },
        uniqueConstraints = {
                @UniqueConstraint(name = "image_name_unique", columnNames = {"name"}),
        }
)
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * name of image
     */
    @NotNull
    private String name;


    @Transient
    private Long totalLikes;

    @Transient
    private Long totalUnlikes;

    @Transient
    private Long totalViews;

    @Transient
    private Long totalComments;

    /**
     * updated date
     */
    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Long totalLikes) {
        this.totalLikes = totalLikes;
    }

    public Long getTotalUnlikes() {
        return totalUnlikes;
    }

    public void setTotalUnlikes(Long totalUnlikes) {
        this.totalUnlikes = totalUnlikes;
    }

    public Long getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Long totalViews) {
        this.totalViews = totalViews;
    }

    public Long getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Long totalComments) {
        this.totalComments = totalComments;
    }
}
