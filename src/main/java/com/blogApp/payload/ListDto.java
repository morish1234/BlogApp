package com.blogApp.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDto {
   private List<DtoPost> list =new ArrayList<>();
   private int pageNumber;
   private  int totalPage;
    private int totalElements;
    private boolean lastPage;
    private boolean firstPage;
   }


