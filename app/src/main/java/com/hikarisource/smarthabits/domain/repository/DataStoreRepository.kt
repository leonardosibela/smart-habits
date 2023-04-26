package com.hikarisource.smarthabits.domain.repository

interface DataStoreRepository {
    suspend fun saveBoolean(key: String, value: Boolean)
    suspend fun readBoolean(key: String): Boolean
}
