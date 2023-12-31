/*
 * @Author: james.aworo
 * @Date: 11/18/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.people.data.usecase.implementation.client

import com.james.crm.api.core.annotation.Usecase
import com.james.crm.api.core.common.ApiResponse
import com.james.crm.api.core.common.CatchableError
import com.james.crm.api.core.util.Util.Companion.errorResponse
import com.james.crm.api.core.util.Util.Companion.normalizeErrorMessages
import com.james.crm.api.core.util.Util.Companion.notFoundMessageAsList
import com.james.crm.api.core.util.Util.Companion.successResponse
import com.james.crm.api.modules.people.data.dto.client.ClientDetailDto
import com.james.crm.api.modules.people.data.usecase.contract.client.IUpdateClientInformationUsecase
import com.james.crm.api.modules.people.domain.repository.ClientDataRepository
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity

@Usecase
class UpdateClientInformationUseCaseImpl(
    private val clientRepository: ClientDataRepository
) : IUpdateClientInformationUsecase {

    override fun execute(input: ClientDetailDto): ResponseEntity<ApiResponse<Boolean>> {
        return try {
            if (input.id != null) {
                clientRepository.findById(input.id!!).map { client ->
                    clientRepository.save(ClientDetailDto.updateDetails(input, client))
                    successResponse(OK, true)
                }.orElse(errorResponse(NOT_FOUND, notFoundMessageAsList("client")))
            }
            return errorResponse(BAD_REQUEST, normalizeErrorMessages("client id is missing"))
        } catch (ex: Exception) {
            errorResponse(INTERNAL_SERVER_ERROR, CatchableError(INTERNAL_SERVER_ERROR, ex))
        }
    }
}