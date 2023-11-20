/*
 * @Author: james.aworo
 * @Date: 11/19/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.people.data.usecase.implementation.client

import com.james.crm.api.core.annotation.Usecase
import com.james.crm.api.core.common.ApiResponse
import com.james.crm.api.core.common.CatchableError
import com.james.crm.api.core.util.Util.Companion.errorResponse
import com.james.crm.api.core.util.Util.Companion.notFoundMessageAsList
import com.james.crm.api.core.util.Util.Companion.successResponse
import com.james.crm.api.modules.people.data.dto.client.CategorizationDto
import com.james.crm.api.modules.people.data.usecase.contract.client.IGetClientCategoriesUsecase
import com.james.crm.api.modules.people.domain.repository.ClientDataRepository
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity

@Usecase
class GetClientCategoriesUsecaseImpl(
    private val clientRepository: ClientDataRepository
) : IGetClientCategoriesUsecase {
    override fun execute(input: String): ResponseEntity<ApiResponse<CategorizationDto>> {
        return try {
            clientRepository.findById(input)
                .map {
                    successResponse(
                        OK, CategorizationDto(
                            clientId = it.id ?: input,
                            tags = it.tags.toSet()
                        )
                    )
                }
                .orElse(errorResponse(NOT_FOUND, notFoundMessageAsList("client")))
        } catch (ex: Exception) {
            errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, CatchableError(HttpStatus.INTERNAL_SERVER_ERROR, ex))
        }
    }
}