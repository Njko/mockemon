package fr.nicolaslinard.mockemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.nicolaslinard.mockemon.ui.theme.MockemonTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MockemonApp()
        }
    }
}

@Composable
fun MockemonApp() {
    MockemonTheme {
        MainUi()
    }
}

@Preview(showBackground = true)
@Composable
fun MockemonAppPreview() {
    MockemonApp()
}