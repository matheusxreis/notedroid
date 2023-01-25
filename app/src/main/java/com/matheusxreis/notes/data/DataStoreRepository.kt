package com.matheusxreis.notes.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.matheusxreis.notes.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context:Context) {

        private object PreferenceKeys {
            val selectedImportantFilter = preferencesKey<String>(Constants.FILTER_IMPORTANT_NAME_KEY)
            val selectedImportantFilterId = preferencesKey<Int>(Constants.FILTER_IMPORTANT_ID_KEY)
        }

        private val dataStore:DataStore<Preferences> = context.createDataStore(
            name = Constants.DATASTORE_NAME
        )

        val readFilterImportant:Flow<ImportantFilter> = dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else {
                    throw exception
                }
            }
            .map { preference ->
                val importantFilter =  preference[PreferenceKeys.selectedImportantFilter].toString() ?: Constants.FILTER_IMPORTANT_VALUE_DEFAULT
                val importantFilterId = preference[PreferenceKeys.selectedImportantFilterId] ?: 0

                ImportantFilter(
                    selectedImportantFilter = importantFilter,
                    selectedImportantFilterId = importantFilterId
                )
            }
        suspend fun saveImportantFilter(
            importantFilter:String,
            importantFilterId:Int
        ) {
            dataStore.edit { preference ->
                 preference[PreferenceKeys.selectedImportantFilter] = importantFilter
                 preference[PreferenceKeys.selectedImportantFilterId] = importantFilterId
            }

        }
        data class ImportantFilter(
            val selectedImportantFilter: String,
            val selectedImportantFilterId: Int
        )

}