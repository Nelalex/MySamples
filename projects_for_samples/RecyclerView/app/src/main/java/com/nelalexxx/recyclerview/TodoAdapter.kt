package com.nelalexxx.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nelalexxx.recyclerview.databinding.ItemTodoBinding

// now we need do understand which data it should set to which data
//we created a data class and now can access him from constructor
class TodoAdapter (
    var todos: List<ToDo>
): RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() { // adapter=recyclerview.adapter<viewholder>()
    // inner class is viewholder = must hold views for recycler view
    // parameter is a layout(view) that we need as an item in recycler view
    // we must return standart object for recycler view thats why we inherit
    // from .viewholder  : RecyclerView.ViewHolder(itemView)
    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // этот код пишем если хотим исопльзовать viewBinding для обновления ui
        val binding = ItemTodoBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        //inflate as an activity main. we have parent parameter to context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        //so we inflate in recyclerview layout inflate(int resource, android.view.ViewGroup root, boolean attachToRoot)
        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size // number of existing elements in recyclerview
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = todos[position]
        holder.binding.apply {
            tvTitle.text = todo.title
            cbDone.isChecked = todo.isCheckedState
        }
    }
}