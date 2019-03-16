package com.galleryapp.model;

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
import java.util.List;

@Entity
@Table
public class PageView {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy="pageView", cascade = {CascadeType.ALL})
    private List<Comment> comments;

    /**
     * num of views (all pages)
     */
    private Long numViews;

    public PageView() {
        isLike = false;
        isUnlike = false;
        numViews = 0L;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return comments;
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
}
