package com.bhaveshp750.mynotification.allstyles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhaveshp750.mynotification.allstyles.ui.theme.MyNotificationTheme2

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotificationTheme2 {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyAppAllStyle()
                }
            }
        }
    }
}

@Composable
fun MyAppAllStyle() {
    val context = LocalContext.current
    val myService = MyNotificationService(context)
    var inputValue1 by rememberSaveable { mutableStateOf("") }
    var inputValue2 by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditText(inputValue1, onInputChanged = { inputValue1 = it})
        Spacer(modifier = Modifier.height(16.dp))
        EditText(inputValue2, onInputChanged = { inputValue2 = it})
        Spacer(modifier = Modifier.height(16.dp))
        AddButton("Send On Channel 1") {
            myService.sendOnChannel1(inputValue1, inputValue2)
        }
        Spacer(modifier = Modifier.height(16.dp))
        AddButton("Send On Channel 2") {
            myService.sendOnChannel2(inputValue1, inputValue2)
        }
    }
}

@Composable
fun EditText(textState :String, onInputChanged: (String) -> Unit) {
    TextField(value = textState,
        onValueChange = onInputChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text(text = "Enter title")},

    )
}

@Composable
fun AddButton(name: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "$name!")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyNotificationTheme2 {
        MyAppAllStyle()
    }
}