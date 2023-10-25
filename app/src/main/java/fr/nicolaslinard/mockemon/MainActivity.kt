package fr.nicolaslinard.mockemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.nicolaslinard.mockemon.ui.theme.MockemonTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val controllerMain = ControllerMain()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var responseData by remember { mutableStateOf(Result("Loading...")) }

            val coroutineScope = rememberCoroutineScope()

            MockemonTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LaunchedEffect(Unit) {
                        coroutineScope.launch {
                            controllerMain.fetchData(coroutineScope) { result ->
                                responseData = result
                            }
                        }
                    }

                    Greeting(responseData.rawData)
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Response from API: $name",
            modifier = modifier
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MockemonTheme {
        Greeting("Android")
    }
}
