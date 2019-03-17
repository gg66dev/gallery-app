package com.galleryapp.repository;

import com.galleryapp.model.Image;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/***
 * interface to access to the Image perisistence entity
 */
public interface ImageDAO extends QuerydslPredicateExecutor<Image>, CrudRepository<Image, Long> {

    Image findByName(String name);

    List<Image> findAllByOrderByUpdatedDateDesc();
}
