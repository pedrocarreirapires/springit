package com.vega.springit.repository;

import com.vega.springit.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedHashSet;

public interface LinkRepository extends JpaRepository<Link,Long> {



}
