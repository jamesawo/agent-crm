/*
 * @Author: james.aworo
 * @Date: 11/13/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.team.data.usecase.contract

import com.james.crm.api.core.common.Paginate
import com.james.crm.api.core.common.Usecase
import com.james.crm.api.modules.team.data.dto.TeamDetailDto
import org.springframework.data.domain.Pageable


interface IGetTeamUsecase : Usecase<Pageable, Paginate<TeamDetailDto>>