package com.worldonetop.ourry.model.extension

import com.worldonetop.ourry.model.local.AccountEntity
import com.worldonetop.ourry.model.remote.MemberTable


/*********************
 *   any to domain   *
 *********************/
fun AccountEntity.toDomain() { }
fun MemberTable.toDomain() { }


/*********************
 *  local to remote  *
 *********************/
fun AccountEntity.toTable() = MemberTable(
    id = id,
    email = email,
    password = pw,
)


/*********************
 *  remote to local  *
 *********************/
