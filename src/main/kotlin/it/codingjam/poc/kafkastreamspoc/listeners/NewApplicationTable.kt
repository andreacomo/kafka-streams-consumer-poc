package it.codingjam.poc.kafkastreamspoc.listeners

import it.codingjam.poc.kafkastreamspoc.configs.StoreName
import it.codingjam.poc.kafkastreamspoc.configs.Topic
import it.codingjam.poc.kafkastreamspoc.listeners.dtos.ApplicationWithCredentialDTO
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.state.KeyValueStore
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Profile
import org.springframework.kafka.support.serializer.JsonSerde
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * This builder crates a table on "new.application.windowed" topic for testing how to query on the table
 *
 * */
@Component
class NewApplicationTable(val streamsBuilder: StreamsBuilder) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    fun processStream() {
        val longSerde = Serdes.Long()
        val applicationWithCredentialsSerde = JsonSerde(ApplicationWithCredentialDTO::class.java)
        val materialized =
            Materialized.`as`<Long?, ApplicationWithCredentialDTO?, KeyValueStore<Bytes, ByteArray>?>(StoreName.NEW_APP_WINDOWED)
                .withKeySerde(longSerde)
                .withValueSerde(applicationWithCredentialsSerde)

        streamsBuilder.table(Topic.NEW_APPLICATION_TOPIC_WINDOWED, materialized)
    }
}