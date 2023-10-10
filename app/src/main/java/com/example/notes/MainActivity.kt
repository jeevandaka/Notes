package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel: NotesViewModel
    lateinit var editText:EditText
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerView: RecyclerView = findViewById(R.id.notes_RV)
        editText = findViewById(R.id.text)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })



    }

    override fun onItemClicked(note: Notes) {
        viewModel.deleteNode(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }

    fun Add_note(view: View) {
        val noteText = editText.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNode(Notes(noteText,"12-01-2023"))
            Toast.makeText(this,"${noteText} Inserted",Toast.LENGTH_SHORT).show()
        }
    }

}