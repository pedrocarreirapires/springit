package com.vega.springit.repository;

import com.vega.springit.domain.Comment;
import com.vega.springit.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
