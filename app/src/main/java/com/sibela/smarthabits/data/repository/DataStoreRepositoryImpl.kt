package com.sibela.smarthabits.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.sibela.smarthabits.domain.repository.DataStoreRepository
import com.sibela.smarthabits.extension.dataStore
import kotlinx.coroutines.flow.first

class DataStoreRepositoryImpl(private val context: Context) : DataStoreRepository {

    override suspend fun saveBoolean(key: String, value: Boolean) {
        val booleanKey = booleanPreferencesKey(key)
        context.dataStore.edit { settings ->
            settings[booleanKey] = value
        }
    }

    override suspend fun readBoolean(key: String): Boolean {
        val booleanKey = booleanPreferencesKey(key)
        return context.dataStore.data.first()[booleanKey] ?: false
    }
}