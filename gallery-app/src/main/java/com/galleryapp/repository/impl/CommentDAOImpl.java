package com.galleryapp.repository.impl;

import com.galleryapp.model.Comment;
import com.galleryapp.model.QComment;
import com.galleryapp.repository.CommentCustomDAO;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CommentDAOImpl implements CommentCustomDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comment> findAllByPageOrderByCreatedDateAsc(String url) {
        final JPAQuery<Comment> query = new JPAQuery<>(em);
        final QComment comment = QComment.comment;
        return query.from(comment)
                .where(comment.pageView.page.url.eq(url))
                .orderBy(comment.createdDate.asc())
                .fetch();
    }

    @Override
    public Long getTotalComments(String url) {
        final JPAQuery<Comment> query = new JPAQuery<>(em);
        final QComment comment = QComment.comment;
        return query.from(comment)
                .where(comment.pageView.page.url.eq(url))
                .fetchCount();
    }

    @Override
    public Long getTotalComments() {
        final JPAQuery<Comment> query = new JPAQuery<>(em);
        final QComment comment = QComment.comment;
        return query.from(comment)
                .fetchCount();
    }
}
