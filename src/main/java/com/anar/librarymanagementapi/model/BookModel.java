package com.anar.librarymanagementapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {
    private Long id;
    private String title;
    private Double price;
    private Integer publishedYear;
    private Long authorId;
    private AuthorSummary author;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorSummary {
        private Long id;
        private String name;
    }
}
