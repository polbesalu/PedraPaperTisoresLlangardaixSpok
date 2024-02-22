package cat.institutmontilivi.pedrapapertisoresllangardaix.dades

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenciesDataStore(private val context: Context) {
    companion object {
        private val Context.datastore: DataStore<androidx.datastore.preferences.core.Preferences>
                by preferencesDataStore(("preferencies"))
        private val MODO_JOC = intPreferencesKey("modoJoc")
        private val RONDESXGUANYAR = intPreferencesKey("rondesXguanyar")
        private val NOM_JUGADOR = stringPreferencesKey("nomJugador")

    }

    val getNomJugador: Flow<String> =
        context.datastore.data.map{
                preferencies -> preferencies[NOM_JUGADOR] ?: ""
        }

    suspend fun saveNomJugador(valor:String)
    {
        context.datastore.edit {
                preferencies -> preferencies[NOM_JUGADOR] = valor
        }
    }

    val getRondesXGuanyar: Flow<Int> =
        context.datastore.data.map {
                preferencies -> preferencies[RONDESXGUANYAR] ?:1
        }
    suspend fun saveRondesXGuanyar(valor:Int)
    {
        context.datastore.edit {
                preferencies -> preferencies[RONDESXGUANYAR] = valor
        }
    }

    val getModoJoc: Flow<Int> =
        context.datastore.data.map{
                preferencies -> preferencies[MODO_JOC] ?: 0
        }
    suspend fun saveModoJoc(valor:Int)
    {
        context.datastore.edit {
                preferencies -> preferencies[MODO_JOC] = valor
        }
    }
}