package com.example.todoapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        rvToDOItems.adapter = todoAdapter
        rvToDOItems.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Add To Do Items")
            alert.setMessage("Enter items to add")

            val editTasks = EditText(this)
            alert.setView(editTasks)

            alert.setPositiveButton("Add") { dialog, _ ->
                val edittext = editTasks.text.toString()
                if (edittext.isNotEmpty()) {
                    val todo = Todo(edittext)
                    todoAdapter.addTodo(todo)
                    Toast.makeText(this, "Task added and saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Invalid input. Please try again", Toast.LENGTH_SHORT)
                        .show()
                }
                editTasks.text.clear()
                dialog.dismiss()

            }
            alert.setNegativeButton("Cancel"){dialog,_->
                Toast.makeText(this, "Clicked cancel", Toast.LENGTH_SHORT)
                    .show()
                editTasks.text.clear()
                dialog.dismiss()
            }
            alert.show()
        }


        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#B89CC2")))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deleteAll->{
                todoAdapter.deleteAll()
                true
            }
            R.id.deleteDone->{
                todoAdapter.deleteDoneTodos()
                true
            }else ->super.onOptionsItemSelected(item)
        }
    }

}