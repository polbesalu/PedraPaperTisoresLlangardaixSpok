package cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cat.institutmontilivi.pedrapapertisoresllangardaix.R

class PedraPaperTisoresViewModel : ViewModel() {
    var triaTeva = ""
    var triaIA = ""
    var victories:Int = 0
    var victoriesTotals:Int = 0
    var derrotes:Int = 0
    var imatgeT= R.drawable.logo_vermell
    var imatgeI= R.drawable.logo_blau

    var estat by mutableStateOf(JocEstat())
        private set

    data class JocEstat(
        val triaTevaEstat:String = "",
        val triaIAEstat:String = "",
        val rondesGuanyadesEstat:Int = 0,
        val rondesPerdudesEstat:Int = 0,
        val victoriesEstat:Int = 0,
        val imatgeIa:Int= R.drawable.logo_blau,
        val imatgeTeva:Int= R.drawable.logo_vermell
    )

    fun JugaRandom(modo:Int)
    {

        if (modo == 0)
        {
            val opcions = listOf("Pedra", "Paper", "Tisores")
            triaIA = opcions.random()

        }
        else
        {
            val opcions = listOf("Pedra", "Paper", "Tisores","Llargandaix","Spock")
            triaIA = opcions.random()
        }
        if(triaIA == "Pedra")
        {
            imatgeI = R.drawable.rock
        }
        else if(triaIA == "Paper")
        {
            imatgeI = R.drawable.paper
        }
        else if(triaIA == "Tisores")
        {
            imatgeI = R.drawable.scissors
        }
        else if(triaIA == "Llargandaix")
        {
            imatgeI = R.drawable.lizard
        }
        else if(triaIA == "Spock")
        {
            imatgeI = R.drawable.spok
        }

        if(triaTeva == "Pedra")
        {
            imatgeT = R.drawable.rock
        }
        else if(triaTeva == "Paper")
        {
            imatgeT = R.drawable.paper
        }
        else if(triaTeva == "Tisores")
        {
            imatgeT = R.drawable.scissors
        }
        else if(triaTeva == "Llargandaix")
        {
            imatgeT = R.drawable.lizard
        }
        else if(triaTeva == "Spock")
        {
            imatgeT = R.drawable.spok
        }

        estat = estat.copy( triaIAEstat = triaIA, imatgeIa = imatgeI, imatgeTeva = imatgeT)
    }

    fun victoria(): Boolean {
        var victoria = false
        estat = estat.copy(triaTevaEstat = triaTeva)

        when {
            triaTeva == "Pedra" && triaIA == "Tisores" -> victoria = true
            triaTeva == "Paper" && triaIA == "Pedra" -> victoria = true
            triaTeva == "Tisores" && triaIA == "Paper" -> victoria = true
            triaTeva == "Llargandaix" && triaIA == "Spock" -> victoria = true
            triaTeva == "Spock" && triaIA == "Tisores" -> victoria = true
            triaTeva == "Tisores" && triaIA == "Llargandaix" -> victoria = true
            triaTeva == "Llargandaix" && triaIA == "Paper" -> victoria = true
            triaTeva == "Paper" && triaIA == "Spock" -> victoria = true
            triaTeva == "Spock" && triaIA == "Pedra" -> victoria = true
            triaTeva == triaIA -> JugaRandom(0)
            else -> victoria = false
        }

        return victoria
    }

    fun Juga(rondes: Int) {
        if (victoria()) {
            victories++
        } else {
            derrotes++
        }

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