package com.ll.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Quotation {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String content;
    @Getter
    @Setter
    private String authorName;


}
