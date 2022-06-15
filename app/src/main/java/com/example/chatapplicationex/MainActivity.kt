package com.example.chatapplicationex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var type1: EditText = findViewById(R.id.edittext1)
        var type2: EditText = findViewById(R.id.edittext2)
        var view1: TextView = findViewById(R.id.textview1)
        var view2: TextView = findViewById(R.id.textview2)
        var button1: Button = findViewById(R.id.button1)
        var button2: Button = findViewById(R.id.button2)
        val database = Firebase.database
        val myRef1 = database.getReference("key1")
        val myRef2 = database.getReference("key2")
        button1.setOnClickListener {
            var message1 = type1.text.toString()
            myRef1.setValue("$message1")
            myRef1.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = snapshot.getValue<String>()
                    view1.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        }
        button2.setOnClickListener{
            var message2 = type2.text.toString()
            myRef2.setValue("$message2")
            myRef2.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = snapshot.getValue<String>()
                    view2.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        }

    }
}
