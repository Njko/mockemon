package fr.nicolaslinard.mockemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.nicolaslinard.mockemon.model.IconsBar
import fr.nicolaslinard.mockemon.model.Mockemon
import fr.nicolaslinard.mockemon.ui.theme.MockemonTheme

@Composable
fun MainUi(mainViewModel: MainViewModel = viewModel()) {
    val pokemons by mainViewModel.pockemons.observeAsState(initial = emptyList())
    val icons by mainViewModel.listOngletBar.observeAsState(initial = emptyList())
    val error by mainViewModel.error.observeAsState(initial = null)


    MockemonTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Box {
                PokemonList(pokemons,icons)


                NavMockemon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    mainViewModel = mainViewModel,
                    icons = icons
                )

            }
        }
    }
}

@Composable
fun NavMockemon(modifier: Modifier, mainViewModel: MainViewModel, icons: List<IconsBar>) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .height(50.dp)
    )
    Row(
        modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        for (icon in icons) {
            Column(
                verticalArrangement = if(icon.iconSelected) Arrangement.Top else Arrangement.Center,
                modifier = Modifier.fillMaxHeight(),
            ) {
                val back = MaterialTheme.colorScheme.background
                OutlinedButton(
                    onClick = { mainViewModel.handleEvent(MainViewModel.MainEvent.SelectBar(icon)) },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                    ),
                    border = BorderStroke(0.dp, Color.Transparent),

                    modifier = Modifier.size(60.dp)

                        .drawBehind {
                            if (icon.iconSelected)
                                drawArc(
                                    color = back,
                                    startAngle = 0f,
                                    sweepAngle = 180f,
                                    useCenter = false,
                                    style = Stroke(8.dp.toPx())
                                )
                        }
                        ,
                    ) {
                    Icon(
                        painter = painterResource(id = icon.icon),
                        contentDescription =  icon.title,
                        modifier = Modifier.size(50.dp),
                    )
                }
            }
        }
    }
}


@Composable
private fun PokemonListItem(pokemon: Mockemon, icons: List<IconsBar>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.LightGray)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Détails du Pokémon
            Text(
                text = pokemon.name.french,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (icons[0].iconSelected)"${pokemon.name.english}" else "Type: ${pokemon.type.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text =  if (icons[0].iconSelected)"${pokemon.name.japanese}" else "HP: ${pokemon.base.hP}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
private fun PokemonList(mockemonList: List<Mockemon>, icons: List<IconsBar>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(1f),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(mockemonList) { pokemon ->
            PokemonListItem(pokemon = pokemon, icons = icons)
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    MockemonTheme {

    }
}
