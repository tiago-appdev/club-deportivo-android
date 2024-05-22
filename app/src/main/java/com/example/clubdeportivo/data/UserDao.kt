package com.example.clubdeportivo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    //Insertar un usuario
    @Insert()
    fun newUser(user: UsersEntitie)

    //Leer todos los usuarios
    @Query("SELECT * FROM ${Contract.Users.TABLE_NAME}")
    fun getAllUsers(): List<UsersEntitie>
}