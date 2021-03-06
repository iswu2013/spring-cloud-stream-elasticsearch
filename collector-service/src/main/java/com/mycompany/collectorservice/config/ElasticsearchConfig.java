package com.mycompany.collectorservice.config;

import com.mycompany.collectorservice.properties.CollectorServiceProperties;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableConfigurationProperties(CollectorServiceProperties.class)
public class ElasticsearchConfig {

    private final CollectorServiceProperties collectorServiceProperties;

    public ElasticsearchConfig(CollectorServiceProperties collectorServiceProperties) {
        this.collectorServiceProperties = collectorServiceProperties;
    }

    @Bean
    Client client() throws UnknownHostException {
        InetSocketTransportAddress transportAddress = new InetSocketTransportAddress(
                InetAddress.getByName(collectorServiceProperties.getElasticsearch().getHost()),
                collectorServiceProperties.getElasticsearch().getPort());

        Settings settings = Settings.builder()
                .put("cluster.name", collectorServiceProperties.getElasticsearch().getClustername()).build();

        return new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);
    }

    @Bean
    ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }

}
