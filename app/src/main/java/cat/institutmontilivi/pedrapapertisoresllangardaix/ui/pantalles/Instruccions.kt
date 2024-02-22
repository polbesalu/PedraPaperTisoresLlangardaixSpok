package cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Instruccions ()
{
    LazyColumn(){
        item {
            Box(Modifier.fillMaxSize(). background(color = MaterialTheme.colorScheme.surfaceVariant))
            {
                Text(text = "Pantalla d'instruccions",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center)
            }
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surfaceVariant))
            {
                Text(text = "Cada jugador escull una forma de la mà, llavors tots els jugadors mostren les seves opcions a la vegada. El guanyador és el qui venç els altres. Les regles de pedra, paper, tisora, llangardaix, Spock són:\n" +
                        "\n" +
                        "Tisora talla paper\n" +
                        "Paper tapa pedra\n" +
                        "Roca aixafa llangardaix\n" +
                        "Llangardaix enverina Spock\n" +
                        "Spock trenca tisora\n" +
                        "Tisora decapita llangardaix\n" +
                        "Llangardaix menja paper\n" +
                        "Paper desautoritza Spock\n" +
                        "Spock vaporitza Roca\n" +
                        "Roca aixafa tisora\n" +
                        "Hi ha quinze possibles aparellaments dels cinc gestos. Cada gest guanya dos dels altres gestos i és vençut pels dos restants. En un empat, el procés es repeteix fins que es trobi un guanyador. Les regles originals (pedra trenca tisores, tisores tallen paper, paper embolica pedra) segueixen sent els mateixos.",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center)
            }
        }

    }

}