package com.ryl.res.es.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author: ryl
 * @description:
 * @date: 2020-05-14 13:55:08
 */
@Service
@Slf4j
public class RestHighLevelClientService {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 创建索引
     * @param indexName
     * @param settings
     * @param mapping
     * @return
     * @throws IOException
     */
    public CreateIndexResponse createIndex(String indexName, String settings, String mapping) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        if (settings != null && !settings.equals("")) {
            request.settings(settings, XContentType.JSON);
        }
        return client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 判断索引是否存在
     * @param indexName
     * @return
     * @throws IOException
     */
    public boolean indexExists(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return client.indices().exists(request,RequestOptions.DEFAULT);
    }

    /**
     * 搜索
     * @param field
     * @param key
     * @param rangeField
     * @param from
     * @param to
     * @param termField
     * @param termVal
     * @param indexNames
     * @return
     * @throws IOException
     */
    public SearchResponse search(String field,String key,String rangeField,
                                 String from, String to,String termField,
                                 String termVal,String... indexNames) throws IOException {
        SearchRequest request = new SearchRequest(indexNames);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(new MatchQueryBuilder(field,key))
                .must(new RangeQueryBuilder(rangeField).from(from).to(to))
                .must(new TermQueryBuilder(termField,termVal));
        builder.query(boolQueryBuilder);
        request.source(builder);
        log.info("[搜索语句为1:{}]",request.source().toString());
        log.info("[搜索语句为2:{}]",builder.toString());
        return client.search(request,RequestOptions.DEFAULT);
    }

    public BulkResponse importAll(String indexName,boolean isAutoId,String source) throws IOException {
        if (source.length() == 0) {
            //todo 抛出异常 导入数据为空
        }
        BulkRequest request = new BulkRequest();
        JsonNode jsonNode = objectMapper.readTree(source);

        if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                if (isAutoId) {
                    request.add(new IndexRequest(indexName).source(node.asText(),XContentType.JSON));
                } else {
                    request.add(new IndexRequest(indexName)
                    .id(node.get("id").asText())
                    .source(node.asText(),XContentType.JSON));
                }
            }
        }
        return client.bulk(request,RequestOptions.DEFAULT);
    }
}
