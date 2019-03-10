package com.galleryapp.repository;

import com.galleryapp.model.Viewer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/***
 * interface to access to the Viewer perisistence entity
 */
public interface ViewerDAO extends QuerydslPredicateExecutor<Viewer>, CrudRepository<Viewer, Long> {

    Viewer findByIp(String ip);
}
