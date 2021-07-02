package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Map;

public class Test {

    public static RestHighLevelClient higeClient = null;

    public static void initHighClient() {
        higeClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("10.130.7.207", 9200, "http")));
    }

    public static void main(String[] args) throws IOException {

        searchByTimeDesc("springboot");
    }

    public static void searchByTimeDesc(String index) throws IOException {

        initHighClient();
        SearchRequest request = new SearchRequest(index);
        request.searchType(SearchType.DEFAULT);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        //排序
//        builder.sort("timestampiaaa", SortOrder.DESC);
        MatchQueryBuilder query = QueryBuilders.matchQuery("level", "ERROR");
        builder.query(query);
        request.source(builder);

        SearchResponse response = higeClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        long totalHits = hits.getTotalHits();
        System.out.println("total hit is " + totalHits);

        for (SearchHit hit : hits.getHits()) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();

            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap.get("info"));
            System.out.println(sourceAsMap.get("class"));
/*
            if (highlightFields.size() != 0) {
                HighlightField info = highlightFields.get("info");
                System.out.println("bbb");
            }*/
        }

    }


}
