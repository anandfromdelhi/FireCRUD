package com.example.firecrud.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firecrud.util.SharedViewModel
import com.example.firecrud.util.UserData

@Composable
fun AddDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
){

    var userId: String by remember {
        mutableStateOf("")
    }
    var name: String by remember {
        mutableStateOf("")
    }
    var profession: String by remember {
        mutableStateOf("")
    }
    var age: String by remember {
        mutableStateOf("")
    }
    var ageInt: Int by remember {
        mutableStateOf(0)
    }
    val context = LocalContext.current

    //main layout
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }

        }
    }

    //add data layout
    Column(
        modifier = Modifier
            .padding(
                start = 60.dp,
                end = 60.dp, bottom = 60.dp
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //user id
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userId, onValueChange = { userId = it },
            label = { Text(text = "User Id") }
        )

        //name
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name, onValueChange = { name = it },
            label = { Text(text = "Name") }
        )

        //profession
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = profession, onValueChange = { profession = it },
            label = { Text(text = "Profession") }
        )

        //age
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = age, onValueChange = {
                age = it
                if (age.isNotEmpty()){
                    ageInt = age.toInt()
                }
            },
            label = { Text(text = "age") },
            keyboardOptions = KeyboardOptions(  keyboardType = KeyboardType.Number)
        )

        //save button
        Button(
            modifier = Modifier.padding(top = 50.dp)
            ,onClick = {
                val userData = UserData(
                    userId, name, profession,age = ageInt
                )
                sharedViewModel.SaveData(userData ,context)}) {
            Text(text = "Save")
        }
    }
}