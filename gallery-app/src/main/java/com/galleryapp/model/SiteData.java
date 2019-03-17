package com.galleryapp.model;

/**
 * Site data to display in the home
 */
public class SiteData {

    private Long totalImages;

    private Long totalViews;

    private Long totalComments;

    private Long totalLikes;

    private Long totalUnlikes;

    public Long getTotalImages() {
        return totalImages;
    }

    public void setTotalImages(Long totalImages) {
        this.totalImages = totalImages;
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
}
