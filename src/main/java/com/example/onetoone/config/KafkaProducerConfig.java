package com.example.onetoone.config;

import com.example.onetoone.core.service.events.BaseEvent;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-server}")
    private List<String> kafkaServer;
    @Value("${spring.kafka.producer.one-to-one.id}")
    private String kafkaProducerId;

    @Value("${spring.kafka.topics.user-answer.name}")
    private String userAnswerTopic;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, "3");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, "1000");
        props.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, "100000");
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "100");
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        return props;
    }

    @Bean
    public ProducerFactory<String, BaseEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, BaseEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

        @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        return new KafkaAdmin(configs);
        }

    @Bean
    public KafkaAdmin.NewTopics declare() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder
                        .name(userAnswerTopic)
                        .partitions(2)
                        .replicas(2)
                        .configs(Map.of(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "2",
                                TopicConfig.MESSAGE_TIMESTAMP_TYPE_CONFIG, "LogAppendTime"))
                        .build() );
    }
}