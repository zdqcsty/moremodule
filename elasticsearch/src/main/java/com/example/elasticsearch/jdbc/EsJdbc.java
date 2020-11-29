package com.example.elasticsearch.jdbc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Map;

public class EsJdbc {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("10.130.7.207", 9200, "http")));


        GetRequest getRequest = new GetRequest(
                "customer",//index name
                "_doc",  //type
                "2");   //id

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        String index = getResponse.getIndex();
        String type = getResponse.getType();
        String id = getResponse.getId();
        System.out.println(index + "-------" + type + "---------" + id);

        if (getResponse.isExists()) {
            long version = getResponse.getVersion();
            String sourceAsString = getResponse.getSourceAsString();
            System.out.println("++++++++++++++"+sourceAsString);
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            System.out.println(")))))"+sourceAsMap);
            byte[] sourceAsBytes = getResponse.getSourceAsBytes();
        } else {
            //TODO
        }
        client.close();
    }
}
