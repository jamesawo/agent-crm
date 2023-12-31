/*
 * @Author: james.aworo
 * @Date: 11/7/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.people.data.dto.agent

import com.fasterxml.jackson.annotation.JsonInclude
import com.james.crm.api.core.common.Mapper
import com.james.crm.api.modules.people.data.dto.*
import com.james.crm.api.modules.people.data.dto.client.ClientDto
import com.james.crm.api.modules.people.data.dto.manager.ManagerDto
import com.james.crm.api.modules.people.data.dto.other.*
import com.james.crm.api.modules.people.domain.enums.UserTypeEnum
import com.james.crm.api.modules.people.domain.model.Agent
import com.james.crm.api.modules.task.data.dto.TaskDto
import com.james.crm.api.modules.team.data.dto.TeamDto


@JsonInclude(JsonInclude.Include.NON_NULL)
data class AgentDto(var id: String?) {
    var isActive: Boolean = true
    var profile: ProfileDto = ProfileDto()
    var contact: ContactDto = ContactDto()
    var emergencyContact: EmergencyContactDto = EmergencyContactDto()
    var user: UserDto = UserDto()
    var clients: MutableList<ClientDto>? = mutableListOf()
    var resources: MutableList<ResourceDto>? = mutableListOf()
    var task: TaskDto? = TaskDto()
    var team: TeamDto? = TeamDto()
    var location: LocationDto? = LocationDto()
    var manager: ManagerDto? = ManagerDto(null)

    constructor() : this(id = null)

    constructor(
        id: String?,
        isActive: Boolean,
        profile: ProfileDto,
        contact: ContactDto,
        emergencyContact: EmergencyContactDto,
        user: UserDto,
        clients: MutableList<ClientDto>?,
        resources: MutableList<ResourceDto>?,
        task: TaskDto?,
        team: TeamDto?,
        location: LocationDto?,
        manager: ManagerDto?
    ) : this(id) {
        this.isActive = isActive
        this.profile = profile
        this.contact = contact
        this.emergencyContact = emergencyContact
        this.user = user
        this.clients = clients
        this.resources = resources
        this.task = task
        this.team = team
        this.location = location
        this.manager = manager
    }


    companion object : Mapper<AgentDto, Agent> {

        override fun toRequest(entity: Agent): AgentDto {
            return AgentDto(
                id = entity.id,
                isActive = entity.isActive,
                profile = ProfileDto.toRequest(entity.profile),
                contact = ContactDto.toRequest(entity.contact),
                emergencyContact = EmergencyContactDto.toRequest(entity.emergencyContact),
                user = UserDto.toRequest(entity.user),
                clients = null,
                resources = null,
                task = null,
                team = null,
                location = entity.location?.let { LocationDto.toRequest(it) },
                manager = null,
            )
        }

        override fun toEntity(request: AgentDto): Agent {
            val agent = Agent()
            agent.profile = ProfileDto.toEntity(request.profile)
            agent.contact = ContactDto.toEntity(request.contact)
            agent.emergencyContact = EmergencyContactDto.toEntity(request.emergencyContact)
            agent.user = UserDto.toEntity(request.user).apply { userType = UserTypeEnum.AGENT }
            agent.location = request.location?.let { LocationDto.toEntity(it) }
            return agent
        }

        override fun toTrimRequest(entity: Agent): AgentDto {
            return toRequest(entity).apply {
                manager = null
                location = null
                task = null
                team = null
                clients = null
                resources = null
                profile.bankAccount = null
                profile.virtualBankAccount = null
            }
        }
    }


}

