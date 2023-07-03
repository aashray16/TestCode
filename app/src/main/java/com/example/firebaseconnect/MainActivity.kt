package com.example.firebaseconnect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebaseconnect.ui.theme.FirebaseConnectTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {


    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//            val Firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

            FirebaseConnectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }




//        START CODE


        val tempBeat = 45
        dbRef = FirebaseDatabase.getInstance().getReference("Heartbeat")
        val beatID = dbRef.push().key!!
        val healthParameter = HeartModel(beatID, tempBeat)
        dbRef.child(beatID).setValue(healthParameter)
            .addOnCompleteListener{
                Toast.makeText(this, "DATA INSERTED",  Toast.LENGTH_LONG).show()
            }.addOnFailureListener{ err ->
                Toast.makeText(this, "Error ${err.message}",  Toast.LENGTH_LONG).show()
            }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseConnectTheme {
        Greeting("Android")
    }
}

data class HeartModel(val beatID: String, val tempBeat: Int)