package com.galleryapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.LazyInitializationException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageView {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="viewer_id", nullable=false)
    private Viewer viewer;

    @ManyToOne
    @JoinColumn(name="page_id", nullable=false)
    private Page page;

    /**
     * if pageView have like
     */
    private boolean isLike;

    /**
     * if pageView have unlike
     */
    private boolean isUnlike;

    /**
     * list of comments
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="pageView", cascade = {CascadeType.ALL})
    private List<Comment> comments;

    /**
     * num of views (all pages)
     */
    private Long numViews;

    @Transient
    private Long totalLikes;

    @Transient
    private Long totalUnlikes;


    public PageView() {
        isLike = false;
        isUnlike = false;
        numViews = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        this.isLike = like;
    }

    public boolean isUnlike() {
        return isUnlike;
    }

    public void setUnlike(boolean unlike) {
        this.isUnlike = unlike;
    }

    public List<Comment> getComments() {
        try {
            return comments;
        } catch (LazyInitializationException e) {
            return new ArrayList<>();
        }
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getNumViews() {
        return numViews;
    }

    public void setNumViews(Long numViews) {
        this.numViews = numViews;
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
