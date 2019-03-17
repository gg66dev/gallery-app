package com.galleryapp.repository;

import com.galleryapp.model.Comment;
import com.galleryapp.model.Page;

import java.util.List;

public interface CommentCustomDAO {

    /**
     * get all comments from a url
     */
    List<Comment> findAllByPageOrderByCreatedDateDesc(String url);

    /**
     *  get total of comments of the url
     *
     * @param url
     * @return
     */
    Long getTotalComments(String url);
}
