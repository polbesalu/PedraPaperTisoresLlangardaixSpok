import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.institutmontilivi.pedrapapertisoresllangardaix.dades.PreferenciesDataStore
import cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles.PedraPaperTisoresViewModel

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
            Text(text = "Nom jugador: " + nom, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.h5.fontSize)
            Spacer(modifier = Modifier.width(30.dp))
        }

        Column {
            Text(text = nom +": \t" + estat.jugadorEstat, fontWeight = FontWeight.Bold)
            Image (
                painterResource(id = estat.imatgeJugador),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Rival: " +"\t"+ estat.randomEstat, fontWeight = FontWeight.Bold)
            Image (
                painterResource(id = estat.imatgeRandom),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                contentScale = ContentScale.FillWidth)

        }
        Divider(Modifier.fillMaxWidth())
        var expanded by remember { mutableStateOf(false) }


            var selectedOption by remember { mutableStateOf("ESCULL OPCIÓ") }
            var options = listOf("Pedra", "Paper", "Tisores", "Llargandaix", "Spock")

            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clickable { expanded = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedOption,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown arrow",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOption = option
                                expanded = false
                                when (option) {
                                    "Pedra" -> jocViewModel.triaJugador = "Pedra"
                                    "Paper" -> jocViewModel.triaJugador = "Paper"
                                    "Tisores" -> jocViewModel.triaJugador = "Tisores"
                                    "Llargandaix" -> {
                                        jocViewModel.triaJugador = "Llargandaix"
                                        botoActiu = true
                                    }
                                    "Spock" -> {
                                        jocViewModel.triaJugador = "Spock"
                                        botoActiu = true
                                    }
                                }
                                jocViewModel.JugaRandom(modo)
                                jocViewModel.Juga(rondes)
                            }
                        ) {
                            Text(text = option)
                        }
                    }
                }
            }

            Divider(Modifier.fillMaxWidth())
            Row {
                Text(text = "Rondes: " + rondes, fontWeight = FontWeight.Bold) //Numero de rondes
            }
            Divider(Modifier.fillMaxWidth())
            Row {
                Text(text = "Rondes guanyades: \t" + estat.rondesGuanyadesEstat, fontWeight = FontWeight.Bold)
                Text(text = "\t \tRondes guanyades rival: \t" + estat.rondesPerdudesEstat, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(30.dp))
            }

        Row {
            Text(text = "Victories: \t" + estat.victoriesEstat, fontWeight = FontWeight.Bold)
        }
        }
    }