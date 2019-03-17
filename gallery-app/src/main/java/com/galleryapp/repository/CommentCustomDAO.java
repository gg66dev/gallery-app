package com.galleryapp.repository;

import com.galleryapp.model.Comment;
import com.galleryapp.model.Page;

import java.util.List;

public interface CommentCustomDAO {

    /**
     * get all comments from a url
     */
    List<Comment> findAllByPageOrderByCreatedDateAsc(String url);

    /**
     *  get total of comments of the url
     *
     * @param url
     * @return
     */
    Long getTotalComments(String url);


    /**
     *  get total of comments of site
     *
     * @return
     */
    Long getTotalComments();
}
