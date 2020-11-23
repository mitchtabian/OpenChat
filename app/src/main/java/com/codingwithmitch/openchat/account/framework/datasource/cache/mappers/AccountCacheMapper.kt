package com.codingwithmitch.openchat.account.framework.datasource.cache.mappers

import com.codingwithmitch.openchat.account.business.domain.model.Account
import com.codingwithmitch.openchat.account.framework.datasource.cache.model.AccountCacheEntity
import com.codingwithmitch.openchat.common.business.domain.util.EntityMapper

class AccountCacheMapper
constructor(
): EntityMapper<AccountCacheEntity, Account> {

    override fun mapFromEntity(entity: AccountCacheEntity): Account {
        return Account(
                id = entity.id,
                email = entity.email,
                username = entity.username,
                profileImage = entity.profileImage,
                hideEmail = entity.hideEmail
        )
    }

    override fun mapToEntity(domainModel: Account): AccountCacheEntity {
        return AccountCacheEntity(
                id = domainModel.id,
                email = domainModel.email,
                username = domainModel.username,
                profileImage = domainModel.profileImage,
                hideEmail = domainModel.hideEmail
        )
    }

}





















