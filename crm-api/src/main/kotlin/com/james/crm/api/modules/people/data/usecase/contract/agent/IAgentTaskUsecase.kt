/*
 * @Author: james.aworo
 * @Date: 11/8/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.people.data.usecase.contract.agent

import com.james.crm.api.modules.people.data.dto.TaskDto
import org.springframework.http.ResponseEntity

interface IAgentTaskUsecase {
    fun getTasks(agentId: String): ResponseEntity<List<TaskDto>>
    fun assignTask(agentId: String, taskDto: TaskDto): ResponseEntity<TaskDto>
}