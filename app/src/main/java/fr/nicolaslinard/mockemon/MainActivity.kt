package fr.nicolaslinard.mockemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import fr.nicolaslinard.mockemon.ui.theme.MockemonTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var apiResult by remember { mutableStateOf("") }
            MockemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting(apiResult)
                }
            }
            fetchData { result -> apiResult = result }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchData(onSuccess: (String) -> Unit) {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                val url = URL("https://65366c57bb226bb85dd21671.mockapi.io/api/v1/list")
                val urlConnection = url.openConnection() as HttpURLConnection
                try {
                    val input = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (input.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    response.toString()
                } finally {
                    urlConnection.disconnect()
                }
            }
            onSuccess(result)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MockemonTheme {
        Greeting("Android")
    }
}
