package com.mycompany.collectorservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "news", type = "news")
public class News {

    @Id
    private String id;
    private String title;
    private String text;
    private String datetime;
    private String category;

}
