package com.galleryapp.repository;

import com.galleryapp.model.Page;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/***
 * interface to access to the Page perisistence entity
 */
public interface PageDAO extends QuerydslPredicateExecutor<Page>, CrudRepository<Page, Long> {

    Page findByUrl(String url);
}
