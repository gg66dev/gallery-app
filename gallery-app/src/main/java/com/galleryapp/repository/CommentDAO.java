package com.galleryapp.repository;

import com.galleryapp.model.Comment;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CommentDAO extends QuerydslPredicateExecutor<Comment>, CrudRepository<Comment, Long>,
                CommentCustomDAO {
}
