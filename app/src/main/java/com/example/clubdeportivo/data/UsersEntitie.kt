package com.example.clubdeportivo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UsersEntitie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = Contract.Users.Name) val name: String,
    @ColumnInfo(name = Contract.Users.Password) val password: String
)
