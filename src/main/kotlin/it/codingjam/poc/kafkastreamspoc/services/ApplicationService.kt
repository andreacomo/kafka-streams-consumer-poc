package it.codingjam.poc.kafkastreamspoc.services

import it.codingjam.poc.kafkastreamspoc.configs.StoreName
import it.codingjam.poc.kafkastreamspoc.listeners.dtos.ApplicationWithCredentialDTO
import org.apache.kafka.streams.StoreQueryParameters
import org.apache.kafka.streams.state.QueryableStoreTypes
import org.springframework.kafka.config.StreamsBuilderFactoryBean
import org.springframework.stereotype.Service

@Service
class ApplicationService(val factoryBean: StreamsBuilderFactoryBean) {

    fun getById(id: Long): ApplicationWithCredentialDTO? {
        val store = factoryBean.kafkaStreams.store(
            StoreQueryParameters.fromNameAndType(
                StoreName.NEW_APP_WINDOWED,
                QueryableStoreTypes.keyValueStore<Long, ApplicationWithCredentialDTO>()
            )
        )
        return store.get(id)
    }
}