package com.galleryapp.repository;

import com.galleryapp.model.Page;
import com.galleryapp.model.PageView;
import com.galleryapp.model.Viewer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/***
 * interface to access to the PageView perisistence entity
 */
public interface PageViewDAO extends QuerydslPredicateExecutor<PageView>, CrudRepository<PageView, Long> {

    //Integer getTotalLikes(String url);

   // Integer getTotalUnlikes(String url);

    PageView findByViewerAndPage(Viewer viewer, Page page);
}
