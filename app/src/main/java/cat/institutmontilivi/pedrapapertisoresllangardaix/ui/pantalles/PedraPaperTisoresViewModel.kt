package cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cat.institutmontilivi.pedrapapertisoresllangardaix.R

class PedraPaperTisoresViewModel : ViewModel() {
    var triaJugador = ""
    var triaRandom = ""
    var victories:Int = 0
    var victoriesTotals:Int = 0
    var derrotes:Int = 0
    var imatgeJugador= R.drawable.logo_vermell
    var imatgeRandom= R.drawable.logo_blau

    var estat by mutableStateOf(JocEstat())
        private set

    data class JocEstat(
        val jugadorEstat:String = "",
        val randomEstat:String = "",
        val rondesGuanyadesEstat:Int = 0,
        val rondesPerdudesEstat:Int = 0,
        val victoriesEstat:Int = 0,
        val imatgeRandom:Int= R.drawable.logo_blau,
        val imatgeJugador:Int= R.drawable.logo_vermell
    )

    fun JugaRandom(modo:Int)
    {

        if (modo == 0)
        {
            val opcions = listOf("Pedra", "Paper", "Tisores")
            triaRandom = opcions.random()
        }
        else
        {
            val opcions = listOf("Pedra", "Paper", "Tisores","Llargandaix","Spock")
            triaRandom = opcions.random()
        }
        if(triaRandom == "Pedra")
            imatgeRandom = R.drawable.rock
        else if(triaRandom == "Paper")
            imatgeRandom = R.drawable.paper
        else if(triaRandom == "Tisores")
            imatgeRandom = R.drawable.scissors
        else if(triaRandom == "Llargandaix")
            imatgeRandom = R.drawable.lizard
        else if(triaRandom == "Spock")
            imatgeRandom = R.drawable.spok

        if(triaJugador == "Pedra")
            imatgeJugador = R.drawable.rock
        else if(triaJugador == "Paper")
            imatgeJugador = R.drawable.paper
        else if(triaJugador == "Tisores")
            imatgeJugador = R.drawable.scissors
        else if(triaJugador == "Llargandaix")
            imatgeJugador = R.drawable.lizard
        else if(triaJugador == "Spock")
            imatgeJugador = R.drawable.spok

        estat = estat.copy( randomEstat = triaRandom, imatgeRandom = imatgeRandom, imatgeJugador = imatgeJugador)
    }

    fun victoria(): Boolean {
        var victoria = false
        estat = estat.copy(jugadorEstat = triaJugador)

        when {
            triaJugador == "Pedra" && triaRandom == "Tisores" -> victoria = true
            triaJugador == "Paper" && triaRandom == "Pedra" -> victoria = true
            triaJugador == "Tisores" && triaRandom == "Paper" -> victoria = true
            triaJugador == "Llargandaix" && triaRandom == "Spock" -> victoria = true
            triaJugador == "Spock" && triaRandom == "Tisores" -> victoria = true
            triaJugador == "Tisores" && triaRandom == "Llargandaix" -> victoria = true
            triaJugador == "Llargandaix" && triaRandom == "Paper" -> victoria = true
            triaJugador == "Paper" && triaRandom == "Spock" -> victoria = true
            triaJugador == "Spock" && triaRandom == "Pedra" -> victoria = true
            triaJugador == triaRandom -> JugaRandom(0)
            else -> victoria = false
        }
        return victoria
    }

    fun Juga(rondes: Int) {
        if (victoria())
            victories++
        else
            derrotes++

        estat = estat.copy(rondesGuanyadesEstat = victories)
        estat = estat.copy(rondesPerdudesEstat = derrotes)

        if (victories == rondes) {
            victoriesTotals++
            victories = 0
            derrotes = 0
            estat = estat.copy(victoriesEstat = victoriesTotals)
        };
        if(derrotes == rondes) {
            victories = 0
            derrotes = 0
            victoriesTotals = 0
            estat = estat.copy(victoriesEstat = 0)
        }
    }
}