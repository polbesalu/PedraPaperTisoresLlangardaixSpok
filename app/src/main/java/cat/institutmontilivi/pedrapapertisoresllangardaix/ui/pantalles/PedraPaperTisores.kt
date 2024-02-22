package cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.institutmontilivi.pedrapapertisoresllangardaix.dades.PreferenciesDataStore


@Composable
fun PedraPaperTisores(jocViewModel: PedraPaperTisoresViewModel = viewModel()) {

    val estat = jocViewModel.estat
    val context = LocalContext.current
    val preferencies = PreferenciesDataStore(context)
    val modo by preferencies.getModoJoc.collectAsState(initial = 0)
    val rondes by preferencies.getRondesXGuanyar.collectAsState(initial = 0)
    val nom by preferencies.getNomJugador.collectAsState(initial = "Monti")

    var botoActiu = if (modo == 1) true else false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Row {
            Text(text = "Name: " + nom, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
        }

        Column {
            Text(text = nom +"\t" + estat.triaTevaEstat, fontWeight = FontWeight.Bold)
            Image (
                painterResource(id = estat.imatgeTeva),
                contentDescription = null,
                modifier = Modifier.width(150.dp)
                    .height(150.dp),
                contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Rival " +"\t"+ estat.triaIAEstat, fontWeight = FontWeight.Bold)
            Image (
                painterResource(id = estat.imatgeIa),
                contentDescription = null,
                modifier = Modifier.width(150.dp)
                    .height(150.dp),
                contentScale = ContentScale.FillWidth)

        }
        Divider(Modifier.fillMaxWidth())
        Row {
            Button(onClick = {
                jocViewModel.triaTeva = "Pedra"
                jocViewModel.JugaRandom(modo)
                jocViewModel.Juga(rondes)
            }) {
                Text(text = "Pedra")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                jocViewModel.triaTeva = "Paper"
                jocViewModel.JugaRandom(modo)
                jocViewModel.Juga(rondes)
            }) {
                Text(text = "Paper")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                jocViewModel.triaTeva = "Tisores"
                jocViewModel.JugaRandom(modo)
                jocViewModel.Juga(rondes)
            }) {
                Text(text = "Tisores")
            }
        }
        Row {
            Button(
                onClick = {
                    jocViewModel.triaTeva = "Llargandaix"
                    jocViewModel.JugaRandom(modo)
                    jocViewModel.Juga(rondes)
                },
                enabled = botoActiu
            ) {
                Text(text = "Llargandaix")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    jocViewModel.triaTeva = "Spock"
                    jocViewModel.JugaRandom(modo)
                    jocViewModel.Juga(rondes)
                },
                enabled = botoActiu
            ) {
                Text(text = "Spock")
            }
        }
        Divider(Modifier.fillMaxWidth())
        Row {
            Text(text = "Rondes: " + rondes, fontWeight = FontWeight.Bold) //Numero de rondes
        }
        Divider(Modifier.fillMaxWidth())
        Row {
            Text(text = "Victories: " + estat.victoriesEstat, fontWeight = FontWeight.Bold)
            Text(text = "\n Victories Rival: \t" + estat.victoriesEstat, fontWeight = FontWeight.Bold)
        }
    }
}



