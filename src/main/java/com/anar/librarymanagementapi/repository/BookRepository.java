package com.anar.librarymanagementapi.repository;

import com.anar.librarymanagementapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
        }
