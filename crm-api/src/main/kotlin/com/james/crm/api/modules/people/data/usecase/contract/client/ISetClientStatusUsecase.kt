/*
 * @Author: james.aworo
 * @Date: 11/18/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.people.data.usecase.contract.client

import com.james.crm.api.core.common.Usecase
import com.james.crm.api.modules.people.domain.enums.ClientStatus

interface ISetClientStatusUsecase : Usecase<Pair<String, ClientStatus>, Boolean>
