package com.Electro.Dto;

import lombok.*;

import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse <T>{

    public List<T> content;

    public int pageNumber;

    public Integer pageSize;

    public Long totalElements;

    public Integer totalPages;

    public Boolean lastPage;




}
