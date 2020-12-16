package com.example.elasticsearch.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.*;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ElasticsearchUtils {

    public static RestHighLevelClient higeClient = null;

    public static void initHighClient() {
        higeClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("10.130.7.207", 9200, "http")));
    }

    public static String source(String index, String type, String id) throws IOException {
        initHighClient();
        Map<String, String> map = new HashMap<>();
        map.put("aaa", "bbb");
        IndexRequest request = new IndexRequest(index, type, id).source(map);
        final IndexResponse response = higeClient.index(request, RequestOptions.DEFAULT);
        return response.getId();
    }

    public static void search(String index, int start, int row) throws IOException {
        initHighClient();
        SearchRequest request = new SearchRequest(index);
        request.searchType(SearchType.DEFAULT);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0);
        builder.size(row);
        builder.highlighter(new HighlightBuilder().field("aaa"));
        request.source(builder);

        SearchResponse response = higeClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        long totalHits = hits.getTotalHits();
        System.out.println("total hit is " + totalHits);

        for (SearchHit hit : hits.getHits()) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields.size() != 0) {
                System.out.println("bbb");
            }
        }

        System.out.println("aaa");
    }

    /**
     * 根据某个字段进行查询   返回mapping中的某些字段
     * @param index
     * @param field
     * @param result
     * @throws IOException
     */
    public static void searchByfield(String index, String field, String result) throws IOException {
        initHighClient();
        SearchRequest request = new SearchRequest(index);
        request.searchType(SearchType.DEFAULT);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder query = QueryBuilders.matchQuery(field, result);
        builder.query(query);
        request.source(builder);
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
        }
    }

    public static void main(String[] args) throws IOException {

//        source("customer","_doc","5");
//        higeClient.close();
//        search("customer", 0, 10);
        searchByfield("springboot","class","ERROR");
    }


}
