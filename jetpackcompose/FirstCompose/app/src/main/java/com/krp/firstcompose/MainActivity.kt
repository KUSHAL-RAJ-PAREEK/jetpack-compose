package com.krp.firstcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krp.firstcompose.ui.theme.FirstComposeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationScreen()
        }
    }
}

@Composable
fun Recomposable(){
    val state = remember { mutableStateOf(0.0) }
    Log.d("TAGGED","Logged during Initial Composition")
    Button(onClick = {
        state.value = Math.random()
    }) {
        Log.d("TAGGED","Logged during both Composition & Recomposition")
        Text(text = state.value.toString())
    }
}


@Composable
@Preview(showBackground = true, name = "Hello Message", widthDp = 200, heightDp = 200)
private fun previewFunction() {
//    Text(
//        text = "Hello Kushal",
//        fontStyle = FontStyle.Italic,
//        fontWeight = FontWeight.ExtraBold,
//        color = Color.Red,
//        fontSize = 36.sp,
//        textAlign = TextAlign.Right
//    )

//    Image(painter = painterResource(id = R.drawable.ic_launcher_background),
//        contentDescription = "Dummy Image",
//        colorFilter = ColorFilter.tint(Color.Blue),
//        contentScale = ContentScale.Crop
//    )

//    Button(onClick = {  },
//        colors = ButtonDefaults.buttonColors(
//            contentColor = Color.White,
//            containerColor = Color.Black
//        ),
//    ) {
//        Text(text = "Hello")
//        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Dummy")
//    }

//    TextField(value = "Hellokushal", onValueChange = {},
//        label = {Text(text = "Enter Message")},
//        placeholder = {})

//    Column (
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        Text(text = "A", fontSize = 24.sp)
//        Text(text = "B", fontSize = 24.sp)
//    }

//    Row(
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        verticalAlignment = Alignment.CenterVertically
//    ){
//        Text(text = "A", fontSize = 24.sp)
//        Text(text = "B", fontSize = 24.sp)
//    }

//    Box(contentAlignment = Alignment.Center){
//        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Dummy")
//        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Dummy")

//    Column{
//        ListViewItem(R.drawable.ic_launcher_foreground,"Kushal raj pareek","Application Developer")
//        ListViewItem(R.drawable.ic_launcher_foreground,"Kushal raj pareek","Application Developer")
//        ListViewItem(R.drawable.ic_launcher_foreground,"Kushal raj pareek","Application Developer")
//        ListViewItem(R.drawable.ic_launcher_foreground,"Kushal raj pareek","Application Developer")
//        ListViewItem(R.drawable.ic_launcher_foreground,"Kushal raj pareek","Application Developer")
//    }

//    Text(text = "Hello",
//        color = Color.White,
//        modifier= Modifier
//            .clickable { }
//            .background(Color.Blue)
//            .size(200.dp)
//            .padding(36.dp)
//            .border(4.dp, Color.Red)
//            .clip(CircleShape)
//            .background(Color.Yellow)
//    )
}

@Composable
fun ListViewItem(ImgId : Int, name: String, occupatiuon: String, modifier: Modifier){
    Row(modifier.padding(8.dp)){
        Image(painter = painterResource(id = ImgId), contentDescription = "",
            Modifier.size(40.dp))
        Column() {
            Text(text = name, fontWeight = FontWeight.Bold)
            Text(text = occupatiuon, fontWeight = FontWeight.Thin, fontSize = 12.sp)

        }
    }
}

@Composable
fun CircularImage(){
Image(painter = painterResource(id = R.drawable.ic_launcher_background),
    contentScale = ContentScale.Crop,
    modifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .border(2.dp, Color.LightGray, CircleShape),
    contentDescription = "")
}

@Composable
fun TextInput(){
    val state = remember{ mutableStateOf("") }
    TextField(value = state.value,
        onValueChange = {
                     state.value = it
        },
        label = {Text(text = "Enter Message")},
       )
}


