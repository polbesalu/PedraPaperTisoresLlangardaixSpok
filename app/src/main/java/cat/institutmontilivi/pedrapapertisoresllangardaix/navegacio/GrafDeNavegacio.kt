package cat.institutmontilivi.pedrapapertisoresllangardaix.navegacio

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles.Instruccions
import cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles.PedraPaperTisores
import cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles.Portada
import cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles.Preferencies
import cat.institutmontilivi.pedrapapertisoresllangardaix.ui.pantalles.QuantA


@Composable
fun GrafDeNavegacio (controladorDeNavegacio: NavHostController = rememberNavController())
{
    NavHost(navController = controladorDeNavegacio, startDestination =CategoriaDeNavegacio.Portada.rutaPrevia)
    {
        navigation(startDestination = Destinacio.Portada.rutaBase,route =CategoriaDeNavegacio.Portada.rutaPrevia )
        {
            composable(route = Destinacio.Portada.rutaGenerica){
                Portada()
            }
        }

        navigation(startDestination = Destinacio.Instruccions.rutaBase,route =CategoriaDeNavegacio.Instruccions.rutaPrevia )
        {
            composable(route = Destinacio.Instruccions.rutaGenerica){
                Instruccions()
            }
        }
        navigation(startDestination = Destinacio.Preferencies.rutaBase,route =CategoriaDeNavegacio.Preferencies.rutaPrevia)
        {
            composable(route = Destinacio.Preferencies.rutaGenerica){
                Preferencies()
            }
        }
        navigation(startDestination = Destinacio.QuantA.rutaBase,route =CategoriaDeNavegacio.QuantA.rutaPrevia)
        {
            composable(route = Destinacio.QuantA.rutaGenerica){
                QuantA()
            }
        }
        navigation(startDestination = Destinacio.PedraPaperTisores.rutaBase,route =CategoriaDeNavegacio.PedraPaperTisores.rutaPrevia)
        {
            composable(route = Destinacio.PedraPaperTisores.rutaGenerica){
                PedraPaperTisores()
            }
        }
    }
}