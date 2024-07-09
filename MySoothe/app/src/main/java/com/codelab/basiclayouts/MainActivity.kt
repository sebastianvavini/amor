/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelab.basiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.MySootheTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            MySootheApp(windowSizeClass)
        }
    }
}

// Step: Search bar - Modifiers
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    //TextField
    OutlinedTextField(
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

// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement(
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

// Step: Favorite collection card - Material Surface


// Step: Align your body row - Arrangements
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
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}

// Step: Favorite collections grid - LazyGrid


// Step: Home section - Slot APIs
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



// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column (
        modifier
            .verticalScroll(rememberScrollState())
    ){
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.meditations) {
           // AlignYourBodyRow()
        }
        /**HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }*/
       /** HomeSection(title = R.string.favorite_collections) {

            //
        }
        */

    }


}

public data class Citizen(
    var cpf: String,
    var nome: String,
    var idade: Int,
    var altura: Double,
    var peso: Double,
    var listOcorrencias: List<String>


)
private val listaDeCidaos = listOf(
    Citizen(
        "00946456789",
        "Sebastião",
        38,
        1.87,
        90.0,
        listOf("Iniciação da Idéia", "Apresentação de Layout N° 2.")
    ),
    Citizen(
        "12345678901",
        "João",
        38,
        1.87,
        90.0,
        listOf("Iniciação da Idéia", "Apresentação de Layout N° 2.")
    ),
    Citizen(
        "12345678901",
        "Maria",
        38,
        1.87,
        90.0,
        listOf("Iniciação da Idéia", "Apresentação de Layout N° 2.")
    ),
    Citizen(
        "12345678901",
        "Ana",
        38,
        1.87,
        90.0,
        listOf("Iniciação da Idéia", "Apresentação de Layout N° 2.")
    )
)


@Composable
fun Citizens(
    landscape: Boolean = false,
    modifier: Modifier = Modifier,
    //names: List<String> = List(1000) { "${it}" }
    names: List<Citizen> = listaDeCidaos

) { var bottom= 80.dp
    if(landscape){
            bottom= 0.dp
    }
    LazyColumn(
        modifier = modifier .padding(vertical = 4.dp).padding(bottom = bottom)

    ) {
        items(items = names) { name ->
            Citizen(name = name.nome)
        }
    }


}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun CitizensPreview() {
    MySootheTheme {
        Citizens()
    }
}
@Composable
fun IconImageVector(imageVector: ImageVector,
                    onClick: () -> Unit, modifier: Modifier = Modifier
){
    IconButton(onClick = {onClick()}) {
        Icon(
            imageVector = imageVector,//Icons.Filled.ExpandLess //else Icons.Filled.ExpandMore,
            contentDescription = null
        )
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

// Step: Bottom navigation - Material
/* @Composable

private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ){
        NavigationBarItem(
            icon = {
                   Icon(
                       imageVector = Icons.Default.Spa,
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
*/
// Step: MySoothe App - Scaffold
@Composable
fun MySootheAppPortrait() {
    MySootheTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            Column() {
                HomeScreen(Modifier.padding(padding))
                Text(text = "Cidadãos", modifier = Modifier.padding(start = 16.dp))
                Citizens()
            }

        }
    }
}

// Step: Bottom navigation - Material
@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail (
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                }
            )
            Spacer(Modifier.height(8.dp))
            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Fastfood,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_alimento))
                }
            )
            Spacer(Modifier.height(8.dp))
            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_atividade))
                }
            )
            Spacer(Modifier.height(8.dp))
            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Alarm,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_sono))
                }
            )
            Spacer(Modifier.height(8.dp))
            NavigationRailItem(
                selected = false ,
                onClick = { /*TODO*/ },
                icon = {
                       Icon(
                           imageVector = Icons.Default.AccountCircle,
                           contentDescription = null
                       )
                       },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                }
            )

        }
    }
}

// Step: Landscape Mode
@Composable
fun MySootheAppLandscape(){
    MySootheTheme {
        Surface(color = MaterialTheme.colorScheme.background){
            Row {
                SootheNavigationRail()

                Column() {
                    HomeScreen()
                    Citizens(true)
                }


            }

        }
    }
}

// Step: MySoothe App
@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MySootheAppPortrait()
        }

        WindowWidthSizeClass.Expanded -> {
            MySootheAppLandscape()
        }
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchBarPreview() {
    MySootheTheme { SearchBar(Modifier.padding(8.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    MySootheTheme {
        AlignYourBodyElement(
            text = R.string.ab1_inversions,
            drawable = R.drawable.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}





@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    MySootheTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    MySootheTheme {
        HomeSection(R.string.align_your_body) {
          AlignYourBodyRow()

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    MySootheTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    MySootheTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationRailPreview() {
    MySootheTheme { SootheNavigationRail() }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePortraitPreview() {
    MySootheTheme{MySootheAppPortrait()}
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun MySootheLandscapePreview() {
    MySootheTheme{MySootheAppLandscape()}
}
