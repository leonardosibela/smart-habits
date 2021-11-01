package com.sibela.smarthabits.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val STORE_NAME = "settings"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)