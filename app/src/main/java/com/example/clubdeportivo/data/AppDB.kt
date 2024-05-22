package com.example.clubdeportivo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UsersEntitie::class],
    version = 1,
    exportSchema = true
)

abstract class AppDB: RoomDatabase() {

    abstract fun userDao(): UserDao

}