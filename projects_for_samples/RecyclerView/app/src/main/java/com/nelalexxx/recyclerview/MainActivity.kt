package com.nelalexxx.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nelalexxx.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var list1 = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // можно сделать пустой лист , можно потом подгружать с бд
        // место где хранятся значения для ui
        var todoList = mutableListOf(
            ToDo("KEKW", false),
            ToDo("KEKW2", true)
        )
        // Обязательная штука подключения адаптера
        val adapter = TodoAdapter(todoList)
        binding.rvToDo.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.AddBtn.setOnClickListener {
            // нам нужен обьект класса to do который мы просто добавим в лист
            val title = binding.ToDoEdit.text.toString()
            val todo = ToDo(title, false)
            todoList.add(todo)
            adapter.notifyItemInserted(todoList.size - 1)
            // есть аналог adapter.notifyDataSetChanged но он обновляет все эжлементы RecyclerView
            // а этот обновляет один
        }





        }
}
