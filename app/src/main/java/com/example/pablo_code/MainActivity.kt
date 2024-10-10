package com.example.pablo_code

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pablo_code.ui.theme.PablocodeTheme
import retrofit2.await

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaPosts()
        }
    }
}

@Composable
fun ListaPosts(){

    var posts by remember {
        mutableStateOf<List<APIpost>>(emptyList())
    }

    var erro by remember {
        mutableStateOf<String?>(null)
    }
    
    LaunchedEffect(Unit){
        try {

            val reponse = RetrofitInstance.api.getPosts().await()

            posts = reponse

        } catch (e: Exception){

            erro = "Erro ao carregar dados"
        }
    }

    if (erro != null){
        
        Text(text = erro!!)

    } else{
        
        LazyColumn(modifier = Modifier.fillMaxSize()){

            items(posts){

                post ->

                PostItem(post)

            }
        }
    }
}

@Composable
fun PostItem(post: APIpost) {

    Column(modifier = Modifier

        .fillMaxWidth()

        .padding(16.dp)) {

        Text(text = "User Id: ${post.userId}")
        
        Text(text = "Title: ${post.title}", fontWeight = FontWeight.Bold)

        Text(text = post.body)

    }
}

@Preview
@Composable
fun Preview(){
    ListaPosts()
}
