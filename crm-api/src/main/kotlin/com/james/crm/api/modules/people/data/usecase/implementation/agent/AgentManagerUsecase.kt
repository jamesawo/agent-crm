/*
 * @Author: james.aworo
 * @Date: 11/8/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.people.data.usecase.implementation.agent

import com.james.crm.api.core.annotation.Usecase
import com.james.crm.api.core.common.ApiResponse
import com.james.crm.api.core.common.ErrorResponse
import com.james.crm.api.core.common.SuccessResponse
import com.james.crm.api.modules.people.data.dto.ManagerDto
import com.james.crm.api.modules.people.data.usecase.contract.agent.IAgentManagerUsecase
import com.james.crm.api.modules.people.data.usecase.contract.manager.IManagerUsecase
import com.james.crm.api.modules.people.domain.repository.AgentDataRepository
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity

@Usecase
class AgentManagerUsecase(
    private val agentRepo: AgentDataRepository,
    private val managerUsecase: IManagerUsecase
) : IAgentManagerUsecase {

    override fun getManager(agentId: String): ResponseEntity<ApiResponse<ManagerDto?>> {
        return agentRepo.findById(agentId).map {
            val response: ApiResponse<ManagerDto?> = SuccessResponse(
                OK, it.manager?.let { manager -> ManagerDto.toTrimmedRequest(manager) }
            )
            ResponseEntity.ok().body(response)
        }.orElse(
            ResponseEntity.status(NOT_FOUND)
                .body(ErrorResponse(NOT_FOUND, listOf("Couldn't find that agent")))
        )
    }

    override fun updateManager(agentId: String, managerId: String): ResponseEntity<ApiResponse<Boolean>> {
        return managerUsecase.findById(managerId).flatMap { manager ->
            agentRepo.findById(agentId).map { agent ->
                agent.manager = manager
                agentRepo.save(agent)
                ResponseEntity.ok().body(SuccessResponse(OK, true) as ApiResponse<Boolean>)
            }
        }.orElse(
            ResponseEntity.status(NOT_FOUND).body(ErrorResponse(NOT_FOUND, listOf("The manager doesn't exist")))
        )
    }

}