package com.dilipkumarg.netent.bookstore.helpers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemoMediaPost {
    //    private Integer userId;
//    private Integer id;
    private String title;
    private String body;
}

