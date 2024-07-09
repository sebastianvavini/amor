@file:Suppress("UNUSED_EXPRESSION")

package com.sebastiaomoura.innerbody

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastiaomoura.innerbody.ui.theme.InnerBodyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InnerBodyTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {SootheBottomNavigation()}
                ) { innerPadding ->
                   MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ){
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                ) },
            label = {
                Text(
                    stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Fastfood,
                    contentDescription = null
                ) },
            label = {
                Text(
                    stringResource(R.string.bottom_navigation_alimento)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null
                ) },
            label = {
                Text(
                    stringResource(R.string.bottom_navigation_atividade)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Alarm,
                    contentDescription = null
                ) },
            label = {
                Text(
                    stringResource(R.string.bottom_navigation_sono)
                )
            },
            selected = true,
            onClick = {}
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            }
        )
    }


}

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)
private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.jiujitsu to R.string.jiujitsu,
    R.drawable.corida to R.string.corrida,
    // R.drawable.ab5_hiit to R.string.ab5_hiit,
    // R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }
//======================================================

@Composable
fun ColumnImageCropTextElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    // Implement composable here
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            ),
            style = MaterialTheme.typography.bodyMedium
        )

    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(alignYourBodyData) { item ->
            ColumnImageCropTextElement(item.drawable, item.text)
        }
    }
}

private val eatToYourBodyData = listOf(
    R.drawable.cafe_da_manha to R.string.cafe_da_manha,
    R.drawable.almoco to R.string.almoco,
    R.drawable.jantar to R.string.jantar,
    // R.drawable.ab4_tabata to R.string.ab4_tabata,
    // R.drawable.ab5_hiit to R.string.ab5_hiit,
    // R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

@Composable
fun EatYourBodyRow(
    modifier: Modifier = Modifier
    
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(eatToYourBodyData) { item ->
            ColumnImageCropTextElement(item.drawable, item.text)
        }

    }
}
private val sleepServicesStateData = listOf(
    R.drawable.dormir to R.string.dormir,
    R.drawable.dicas_sono to R.string.dicas_sono,
   // R.drawable.jantar to R.string.jantar,
    // R.drawable.ab4_tabata to R.string.ab4_tabata,
    // R.drawable.ab5_hiit to R.string.ab5_hiit,
    // R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }
@Composable
fun SleepServicesRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(sleepServicesStateData) { item ->
            ColumnImageCropTextElement(item.drawable, item.text)
        }
    }
}
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )

}
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier){
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}






@Composable
fun MyApp(modifier: Modifier = Modifier) {
    // Save the state of the onboarding screen
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnboarding) {
            OnboardingScreen(
                onContinueClicked = { shouldShowOnboarding = false }
            )
        } else {
            Citizens()
            //HomeScreen()

        }
    }


}
@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.welcome_to_the_basics_codelab))
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}
public data class Cidadao(
     var cpf: String,
     var nome: String,
     var idade: Int,
     var altura: Double,
     var peso: Double,
     var listOcorrencias: List<String>


)

private val listaDeCidaos = listOf(
    Cidadao(
        "00946456789",
        "Sebastião",
        38,
        1.87,
        90.0,
        listOf("Iniciação da Idéia", "Apresentação de Layout N° 2.")
        ),
    Cidadao(
        "12345678901",
        "João",
        38,
        1.87,
        90.0,
        listOf("Iniciação da Idéia", "Apresentação de Layout N° 2.")
        )
)


@Composable
fun Citizens(
    modifier: Modifier = Modifier,
    //names: List<String> = List(1000) { "${it}" }
    names: List<Cidadao> = listaDeCidaos

) {
    LazyColumn(
        modifier = modifier .padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Citizen(name = name.nome)
        }
    }


}

@Composable
private fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){


        Column(
            modifier = Modifier
                .weight(1f)
                .padding(7.dp)
                .fillMaxWidth()
        )
        {
            Text(text = "Falar Com, ")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less);

                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
            if (expanded) {

                Column(modifier = Modifier.fillMaxWidth(1f)) {
                    Row {
                        Text(
                            text = ("Sebastiao V. A Moura. Idade: 38 anos. " +
                                    "Altura: 1,87. " +" Peso: 90 kg. " +" Doença Cronica: Transtorno Bipolar." +
                                    " Rotina sugerida: Corrida 3x por semana. "),
                        )

                    }
                    ThreePillarItemCardContent (expanded)
                    //MenuBarBoolean (choose, true, choose1, choose2, choose3)

                }

            }
        }



    }

}
@Composable
fun Citizen(name: String, modifier: Modifier = Modifier) {
    Card (colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary
    ), modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
fun ThreePillarItemCardContent(show: Boolean){
    // var expanded by rememberSaveable { mutableStateOf(false) }

    var food by rememberSaveable { mutableStateOf(false) }
    var activity by rememberSaveable { mutableStateOf(false) }
    var sleep by rememberSaveable { mutableStateOf(false) }

    if (show) {
        Column(modifier = Modifier.padding(top = 30.dp, start =0.dp)) {

            Row() {
                    val onEat={food=true; activity=false; sleep=false}
                IconImageVector(Icons.Filled.Fastfood, onEat )
                /** ElevatedButton(modifier = Modifier.padding(2.dp),
                    onClick = { alimento=true; atividade=false; sono=false }) {
                    Text(text = stringResource(R.string.elevated_button_aliment))
                } */
                    val onActivity={food=false; activity=true; sleep=false}
                IconImageVector(Icons.Filled.FavoriteBorder,onActivity)
                /** ElevatedButton(modifier = Modifier.padding(2.dp),
                    onClick = { alimento=false; atividade=true; sono=false }) {
                    Text(text = "Atividade")

                } */
                /**
                ElevatedButton(modifier = Modifier.padding(2.dp),
                    onClick = { alimento=false; atividade=false; sono=true }) {
                    Text(text = "   Sono    ")
                } */
                    val onSleep={food=false; activity=false; sleep=true}
                IconImageVector(Icons.Filled.AccessAlarm,onSleep)


            }

            Column(modifier = Modifier.padding(start = 10.dp)) {
                if(food ) {
                    //Text(text = "Comeu Arroz, feijão e Bife ao Molho, no Almoço." )
                    EatYourBodyRow()

                }
                if(activity) {
                    // Text(text = "Caminhada de 15 minutos e Corrida de 7km")
                    AlignYourBodyRow()
                }
                if(sleep ) {
                    Text(text = "Foi dormir às 21h e Acordou as 8h")
                    SleepServicesRow()
                }

            }
        }

    }

}



@Composable
fun IconImageVector( imageVector: ImageVector,
                     onClick: () -> Unit, modifier: Modifier = Modifier
){
    IconButton(onClick = {onClick()}) {
        Icon(
            imageVector = imageVector,//Icons.Filled.ExpandLess //else Icons.Filled.ExpandMore,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EatYourBodyRowPreview() {
    InnerBodyTheme {
        EatYourBodyRow()
    }
}
@Preview (showBackground = true)
@Composable
fun ThreePillarItemCardContentPreview() {
    InnerBodyTheme {
        ThreePillarItemCardContent(true)
    }
}
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    InnerBodyTheme {
        Citizens()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    InnerBodyTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview
@Composable
fun MyAppPreview() {
    InnerBodyTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
