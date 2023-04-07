package com.example.firecrud.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel :ViewModel(){

    fun SaveData(
        userData: UserData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{
        val fireStoreRef = Firebase.firestore.collection("user")
            .document(userData.userId)

        try {
            fireStoreRef.set(userData).addOnSuccessListener {
                Toast.makeText(context,"Saved successfully",Toast.LENGTH_SHORT).show()
            }
        }catch (e:Exception){
            Toast.makeText(context,"${e.message}",Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(
        userId:String,
        context: Context,
        data: (UserData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{
        val fireStoreRef = Firebase.firestore.collection("user")
            .document(userId)

        try {
            fireStoreRef.get().addOnSuccessListener {
                if (it.exists()){
                    val userData = it.toObject<UserData>()!!
                    data(userData)
                }else{
                    Toast.makeText(context,"User not found",Toast.LENGTH_SHORT).show()
                }
            }
        }catch (e:Exception){
            Toast.makeText(context,"${e.message}",Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteData(
        userId:String,
        context: Context,
        navController: NavController
    ) = CoroutineScope(Dispatchers.IO).launch{
        val fireStoreRef = Firebase.firestore.collection("user")
            .document(userId)

        try {
            fireStoreRef.delete().addOnSuccessListener {
                Toast.makeText(context,"Deleted successfully",Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }catch (e:Exception){
            Toast.makeText(context,"${e.message}",Toast.LENGTH_SHORT).show()
        }
    }
}