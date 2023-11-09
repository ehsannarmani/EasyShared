package ir.ehsan.easyshared

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ehsannarmani.easyshared.savable
import com.ehsannarmani.easyshared.savableInt
import com.ehsannarmani.easyshared.savableLong
import com.ehsannarmani.easyshared.savableString
import ir.ehsan.easyshared.ui.theme.EasySharedTheme
import java.util.UUID

class MainActivity : ComponentActivity() {

    var token:String by savable("auth_token")
    private var age:Int by savable() /* savable("age") */
    private var loggedIn:Boolean by savable() /* savable("loggedIn") */

    private var phone by savableInt()
    private var name by savableString()
    private var timestamp by savableLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
           sharedPreferences.edit().putString("auth_token","marg bar linux karan").apply()
           sharedPreferences.edit().putInt("age",18).apply()
           sharedPreferences.edit().putBoolean("loggedIn",true).apply()
        */
//        token = "marg bar linux karan"
//        age = 18
//        loggedIn = true

        /*
            println(sharedPreferences.getString("auth_token",""))
            println(sharedPreferences.getInt("age",0))
            println(sharedPreferences.getBoolean("loggedIn",false))
         */
//        println(token)
//        println(age)
//        println(loggedIn)

        setContent {
            EasySharedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp), contentAlignment = Alignment.Center){
                        Column {
                            Button(onClick = {
                                token = UUID.randomUUID().toString()
                            }) {
                                Text(text = "Set")
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                Toast.makeText(context, token, Toast.LENGTH_SHORT).show()
                            }) {
                                Text(text = "Get")
                            }
                        }
                    }
                }
            }
        }
    }
}
