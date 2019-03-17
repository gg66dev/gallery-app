package com.galleryapp.repository.impl;

import com.galleryapp.model.PageView;
import com.galleryapp.model.QPageView;
import com.galleryapp.repository.PageViewCustomDAO;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PageViewDAOImpl implements PageViewCustomDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long getTotalLikes(String url) {
        final JPAQuery<PageView> query = new JPAQuery<>(em);
        final QPageView pageView = QPageView.pageView;
        return query.from(pageView).where(
                pageView.page.url.eq(url),
                pageView.isLike.eq(true))
                .fetchCount();
    }

    @Override
    public Long getTotalUnlikes(String url) {
        final JPAQuery<PageView> query = new JPAQuery<>(em);
        final QPageView pageView = QPageView.pageView;
        return query.from(pageView).where(
                pageView.page.url.eq(url),
                pageView.isUnlike.eq(true))
                .fetchCount();
    }

    @Override
    public Long getTotalViews(String url) {
        final JPAQuery<PageView> query = new JPAQuery<>(em);
        final QPageView pageView = QPageView.pageView;
        Long result = query.from(pageView)
                .where(pageView.page.url.eq(url))
                .select(pageView.numViews.sum())
                .fetchOne();
        return result != null ? result : 0L;
    }

    @Override
    public Long getTotalLikes() {
        final JPAQuery<PageView> query = new JPAQuery<>(em);
        final QPageView pageView = QPageView.pageView;
        return query.from(pageView).where(
                pageView.isLike.eq(true)
                        ).fetchCount();
    }

    @Override
    public Long getTotalUnlikes() {
        final JPAQuery<PageView> query = new JPAQuery<>(em);
        final QPageView pageView = QPageView.pageView;
        return query.from(pageView).where(
                        pageView.isUnlike.eq(true))
                .fetchCount();
    }

    @Override
    public Long getTotalViews() {
        final JPAQuery<PageView> query = new JPAQuery<>(em);
        final QPageView pageView = QPageView.pageView;
        Long result = query.from(pageView)
                .select(pageView.numViews.sum())
                .fetchOne();
        return result != null ? result : 0L;
    }

}
