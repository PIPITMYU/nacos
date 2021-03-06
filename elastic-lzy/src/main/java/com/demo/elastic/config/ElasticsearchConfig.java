package com.demo.elastic.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/15
 * Time: 16:58
 * Description: No Description
 */
@Configuration
public class ElasticsearchConfig {
    @Value("${lzy.elasticsearch.host}")
    private String host;
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        //解析hostlist配置信息
        String[] split = host.split(",");
        //创建HttpHost数组，其中存放es主机和端口的配置信息
        HttpHost[] httpHostArray = new HttpHost[split.length];
        for(int i = 0; i<split.length; i++){
            String item = split[i];
            httpHostArray[i] = new HttpHost(
                    item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
        }
        //创建RestHighLevelClient客户端
        return new RestHighLevelClient(RestClient.builder(httpHostArray));
    }
    //项目主要使用RestHighLevelClient，对于低级的客户端暂时不用
    @Bean
    public RestClient restClient(){
        //解析hostlist配置信息
        String[] split = host.split(",");
        //创建HttpHost数组，其中存放es主机和端口的配置信息
        HttpHost[] httpHostArray = new HttpHost[split.length];
        for(int i=0;i<split.length;i++){
            String item = split[i];
            httpHostArray[i] = new HttpHost(
                    item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
        }
        return RestClient.builder(httpHostArray).build();
    }
}
