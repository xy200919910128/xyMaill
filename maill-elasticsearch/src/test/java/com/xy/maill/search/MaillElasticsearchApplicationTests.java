package com.xy.maill.search;

import com.alibaba.fastjson.JSONObject;
import com.xy.maill.search.config.ElasticsearchConfig;
import com.xy.maill.search.entity.BankEntity;
import com.xy.maill.search.entity.Users;
import org.apache.lucene.index.Term;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.elasticsearch.action.search.SearchRequest;
import java.io.IOException;
import java.util.List;

//@RunWith(SpringRunner.class) springboot 2.2版本之前需要写这个
@SpringBootTest
public class MaillElasticsearchApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void contextLoads() throws IOException {
        //新建请求 并添加索引名称
        IndexRequest request = new IndexRequest("user");
        request.id("1");
//        String jsonString = "{" +
//                "\"user\":\"kimchy\"," +
//                "\"postDate\":\"2013-01-30\"," +
//                "\"message\":\"trying out Elasticsearch\"" +
//                "}";
        Users users = new Users();
        users.setAge(10);
        users.setName("xy");
        users.setPassword("123123123");

        String jsonObjectStr = JSONObject.toJSONString(users);
        //request设置json数据
        request.source(jsonObjectStr, XContentType.JSON);
        //通过client发送请求
        IndexResponse indexResponse = restHighLevelClient.index(request, ElasticsearchConfig.COMMON_OPTIONS);
        System.out.println(indexResponse);
    }


    @Test
    public void searchData() throws  Exception {
        //建立索引请求
        SearchRequest searchResult = new SearchRequest();
        //查询那个索引
        searchResult.indices("newbank");
        //指定dsl 检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //构造检索条件
        //QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("address","mill");
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("agg_age").field("age").subAggregation(AggregationBuilders.avg("avg_age_balance").field("balance"));
        searchSourceBuilder.aggregation(aggregationBuilder);
        aggregationBuilder = AggregationBuilders.avg("avg_balance").field("balance");
        searchSourceBuilder.aggregation(aggregationBuilder);
        //QueryBuilders 是一个工具类 QueryBuilder是查询时需要传入的查询条件参数
        searchSourceBuilder.query(queryBuilder);
        searchResult.source(searchSourceBuilder);

      SearchResponse searchResponse =  restHighLevelClient.search(searchResult,ElasticsearchConfig.COMMON_OPTIONS);
      //获取所有命中的记录
        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit  :hits ) {
            //hit.getIndex();  获取索引
            //hit.getId();  获取id

           //将源转为字符串
          String sourceStr = hit.getSourceAsString();
          BankEntity bankEntity = JSONObject.parseObject(sourceStr,BankEntity.class);
          System.out.println(bankEntity.toString());
        }

        //获取分析信息
        Aggregations aggregations = searchResponse.getAggregations();
        Terms aggregation =  aggregations.get("agg_age");
        List<? extends Terms.Bucket> buckets = aggregation.getBuckets();
        for(int i = 0; i <buckets.size() ; i++) {
            System.out.println("年龄"+buckets.get(i).getKeyAsString()+"的有"+buckets.get(i).getDocCount()+"人");
            Aggregations ag4 = buckets.get(i).getAggregations();
            Avg aggregation1 =  ag4.get("avg_age_balance");
            System.out.println(buckets.get(i).getKey()+"不同年龄的平均薪资"+aggregation1.getValueAsString());
        }

        Avg aggregation1 =  aggregations.get("avg_balance");
        System.out.println("平均薪资"+aggregation1.getValueAsString());

    }
}
