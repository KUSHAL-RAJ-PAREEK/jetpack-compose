package com.krp.blogapp

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.krp.blogapp.ui.theme.BlogAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Derived()
        }
    }
}



@SuppressLint("UnrememberedMutableState")
@Composable
fun Derived(){
    val tableOf = remember {
        mutableStateOf(5)
    }
    val index = produceState(initialValue = 1) {
        repeat(9){
            delay(1000)
            value += 1
        }
    }

    val message = derivedStateOf {
        "${tableOf.value} * ${index.value} = ${tableOf.value * index.value}"
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(1f)
    ){
        Text(text = message.value,
            style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun Loader() {
    val degree = produceState(initialValue = 0) {
        while (true) {
            delay(16)
            value = (value + 30) % 360
        }
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(1f),
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    imageVector = Icons.Default.Refresh, contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .rotate(degree.value.toFloat())
                )
                Text(text = "Loading")
            }
        })

}


@Composable
fun App5() {
    val state = produceState(initialValue = 0) {
        for (i in 1..10) {
            delay(1000)
            value += 1
        }
    }
    Text(
        text = state.value.toString(),
        style = MaterialTheme.typography.bodyLarge
    )
}


@Composable
fun App4() {
    KeyboardComposable()
    TextField(value = "", onValueChange = {})
}

@Composable
fun KeyboardComposable() {
    val view = LocalView.current
    DisposableEffect(key1 = Unit) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val insets = ViewCompat.getRootWindowInsets(view)
            val isKeyboardVisible = insets?.isVisible(WindowInsetsCompat.Type.ime())
            Log.d("Okay", isKeyboardVisible.toString())
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}


@Composable
fun App3() {
    var state = remember { mutableStateOf(false) }

    DisposableEffect(key1 = state.value) {
        Log.d("Okay", "dispossable Effect started")
        onDispose {
            Log.d("Okay", "Cleaning up side effects")
        }
    }

    Button(onClick = { state.value = !state.value }) {
        Text(text = "Update State")
    }
}


fun a() {
    Log.d("OKay", "I am A from App")
}

fun b() {
    Log.d("OKay", "I am B from App")
}


@Composable
fun App2() {
    var state = remember {
        mutableStateOf(::a)
    }

    Button(onClick = { state.value = ::b }) {
        Text(text = "Click to update state")
    }
    LandingScreen(state.value)
}

@Composable
fun LandingScreen(onTimeout: () -> Unit) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    LaunchedEffect(key1 = true) {
        delay(5000)
        currentOnTimeout()
    }
}


@Composable
fun App1() {
    var counter = remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        counter.value = 10
    }
    Counter(counter.value)
}


@Composable
fun Counter(value: Int) {
    val state = rememberUpdatedState(newValue = value)
    LaunchedEffect(key1 = Unit) {
        delay(5000)
        Log.d("OKay", state.value.toString())
    }
    Text(text = value.toString())
}


@Composable
fun LaunchEffectComposable() {
    val counter = remember { mutableStateOf(0) }

    var scope = rememberCoroutineScope()

    var text = "Counter is running ${counter.value}"
    if (counter.value == 10) {
        text = "Counter stopped"
    }
    Column {
        Text(text = text)
        Button(onClick = {

            scope.launch {
                Log.d("LaunchEffectComposable", "Started...")
                try {
                    for (i in 1..10) {
                        counter.value++
                        delay(1000)
                    }
                } catch (e: Exception) {
                    Log.d("LaunchEffectComposable", "Exception- ${e.message.toString()}")
                }
            }

        }) {
            Text(text = "Start")
        }
    }


//    LaunchedEffect(key1 = Unit) {
//        Log.d("LaunchEffectComposable", "Started...")
//        try {
//            for (i in 1..10) {
//                counter.value++
//                delay(1000)
//            }
//        } catch (e: Exception) {
//            Log.d("LaunchEffectComposable", "Exception- ${e.message.toString()}")
//        }
//    }
//    var text = "Counter is running ${counter.value}"
//    if (counter.value == 10) {
//        text = "Counter stopped"
//    }
//    Text(text = text)
}


var counter = 1

@Composable
fun HasSideEffect() {
    counter++
    Text(text = "kushal")
}


@Composable
fun App() {
    var theme = remember { mutableStateOf(false) }
    BlogAppTheme(theme.value) {
        Column(Modifier.background(MaterialTheme.colorScheme.background)) {
            Text(
                text = "Kushal",
                style = MaterialTheme.typography.bodyLarge
            )
            Button(onClick = {
                theme.value = !theme.value

            }) {
                Text(text = "Change Theme")
            }
        }
    }

}
