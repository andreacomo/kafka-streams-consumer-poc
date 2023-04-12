package it.codingjam.poc.kafkastreamspoc.resources

import it.codingjam.poc.kafkastreamspoc.listeners.dtos.ApplicationWithCredentialDTO
import it.codingjam.poc.kafkastreamspoc.services.ApplicationService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/applications",
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE])
class ApplicationResource(val applicationService: ApplicationService) {

    @GetMapping("/{appId}")
    fun getById(@PathVariable("appId") id: Long): ApplicationWithCredentialDTO {
        return applicationService.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find app with id $id")
    }
}