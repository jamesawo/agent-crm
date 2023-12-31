/*
 * @Author: james.aworo
 * @Date: 11/8/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.people.data.usecase.contract.agent

import com.james.crm.api.core.common.ApiResponse
import com.james.crm.api.modules.people.data.dto.manager.ManagerDto
import org.springframework.http.ResponseEntity

interface IAgentManagerUsecase {
    fun getManager(agentId: String): ResponseEntity<ApiResponse<ManagerDto?>>
    fun updateManager(agentId: String, managerId: String): ResponseEntity<ApiResponse<Boolean>>

}