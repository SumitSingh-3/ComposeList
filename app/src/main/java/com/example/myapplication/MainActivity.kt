package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.myapplication.network.api.ApiConfig.Companion.BASE_URL
import com.example.myapplication.network.models.Doc
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme

                val res = vm.docResponse.observeAsState()

                println("res : ${res.value?.response?.docs?.get(0)?.abstract}")

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LazyColumn {

                        res.value?.response?.docs?.let {
                            items(it){
                                Card(it, Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                )
                            }
                        }

                    }


                }
            }
        }
    }
}

@Composable
fun Card(item: Doc, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp,
        border = BorderStroke(2.dp, Color.Gray)
    ) {
        Column(
            modifier =
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = item.headline.main, fontStyle = FontStyle.Normal, color = Color.Black)
            Text(text = item.abstract, color = Color.LightGray)
            AsyncImage(
                model =  BASE_URL + item.multimedia[0].url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 6.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(text = item.pub_date)
        }
    }

}
