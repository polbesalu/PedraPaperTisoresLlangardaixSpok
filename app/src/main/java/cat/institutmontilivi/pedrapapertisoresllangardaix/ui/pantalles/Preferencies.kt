package cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cat.institutmontilivi.pedrapapertisoresllangardaix.R
import cat.institutmontilivi.pedrapapertisoresllangardaix.dades.PreferenciesDataStore
import kotlinx.coroutines.launch

@Preview
@Composable
fun Preferencies ()
{
    val context = LocalContext.current
    val preferencies = PreferenciesDataStore(context)
    val modo by preferencies.getModoJoc.collectAsState(initial = 0)
    val rondesXGuanyar by preferencies.getRondesXGuanyar.collectAsState(initial = 1)
    val nom by preferencies.getNomJugador.collectAsState(initial = "")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.inversePrimary)
            .padding(16.dp)
    ) {
        Spacer (Modifier.height(40.dp))
        Image (
            painterResource(id = R.drawable.logo_vermell),
            contentDescription = null,
            modifier = Modifier.width(200.dp)
                .align(Alignment.CenterHorizontally  ),
            contentScale = ContentScale.FillWidth)

        Spacer (Modifier.height(100.dp))

        Text(text = "CHOOSE THE GAME MODE", style = MaterialTheme.typography.headlineMedium)
        buildOption("STANDARD", modo == 0) {
            GlobalScope.launch {
                preferencies.saveModoJoc(0)
            }
        }

        buildOption("SPOCK", modo == 1) {
            GlobalScope.launch {
                preferencies.saveModoJoc(1)
            }
        }
        Spacer (Modifier.height(30.dp))
        Text(text = "CHOOSE HOW MANY ROUNDS:", style = MaterialTheme.typography.bodyLarge)
        Row()
        {

            Slider(
                modifier = Modifier.weight(8F),
                value = rondesXGuanyar.toFloat(),
                onValueChange = {
                    GlobalScope.launch {
                        preferencies.saveRondesXGuanyar(it.toInt())
                    }
                },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                steps = 6,
                valueRange = 1f..5f
            )
            Text(text = rondesXGuanyar.toString(), modifier= Modifier.weight(2F))
        }
        Text(text = "UserName:", style = MaterialTheme.typography.bodyLarge)
        Spacer (Modifier.height(20.dp))
        TextField(
            value = nom,
            onValueChange = {
                GlobalScope.launch {
                    preferencies.saveNomJugador(it)
                }
            },
            label = { Text("Enter your name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun buildOption(label: String, isSelected: Boolean, onClick: () -> Unit) {
    val textColor = if (isSelected) {
        androidx.compose.ui.graphics.Color.Red
    } else {
        androidx.compose.ui.graphics.Color.Black
    }

    Text(
        text = label,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .padding(8.dp),
        color = textColor
    )
}
