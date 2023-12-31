/*
 * @Author: james.aworo
 * @Date: 11/1/23
 *
 * @Project: agent-crm
 */

package com.james.crm.api.modules.people.domain.model.submodel

import com.james.crm.api.core.common.Base
import com.james.crm.api.core.constant.DatabaseTable.Companion.PROFILE
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = PROFILE)
class Profile(
    id: String? = null,
    var lastname: String = "",
    var firstName: String = "",
    var otherName: String = "",
    var dateOfBirth: String = "",
    var department: String = "",
) : Base(id) {

    constructor() : this(id = null)

    @OneToOne(cascade = [CascadeType.ALL])
    var bankAccount: BankAccount? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var virtualBankAccount: BankAccount? = null
}
