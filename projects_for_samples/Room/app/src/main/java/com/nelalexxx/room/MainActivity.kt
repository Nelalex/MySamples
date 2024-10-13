package com.nelalexxx.room

import android.os.Bundle
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nelalexxx.room.adapters.ContactsAdapter
import com.nelalexxx.room.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

// we need 3 things : database ,


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContactViewModel
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ContactDatabase.getDatabase(this)
        val dao = database.dao
        viewModel = ViewModelProvider(this, ContactViewModelFactory(dao))[ContactViewModel::class.java]

        setupRecyclerView()

        binding.chooseSortGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.sortFirstNameBtn.id -> viewModel.onEvent(ContactEvent.SortContacts(SortType.FIRST_NAME))
                binding.sortLastNameBtn.id -> viewModel.onEvent(ContactEvent.SortContacts(SortType.LAST_NAME))
                binding.sortPhoneNumberBtn.id -> viewModel.onEvent(ContactEvent.SortContacts(SortType.PHONE_NUMBER))
            }
        }

        binding.addNewContactBtn.setOnClickListener {
            showAddContactDialog()
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                contactsAdapter.differ.submitList(state.contacts)
            }
        }
    }

    private fun showAddContactDialog() {val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        val firstNameEditText = view.findViewById<EditText>(R.id.firstNameEditText)
        val lastNameEditText = view.findViewById<EditText>(R.id.lastNameEditText)
        val phoneNumberEditText = view.findViewById<EditText>(R.id.phoneNumberEditText)

        builder.setView(view)
            .setPositiveButton("Добавить") { dialog, _ ->
                val firstName = firstNameEditText.text.toString()
                val lastName = lastNameEditText.text.toString()
                val phoneNumber = phoneNumberEditText.text.toString()

                viewModel.onEvent(ContactEvent.SetFirstName(firstName))
                viewModel.onEvent(ContactEvent.SetLastName(lastName))
                viewModel.onEvent(ContactEvent.SetPhoneNumber(phoneNumber))
                viewModel.onEvent(ContactEvent.SaveContact)

                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun setupRecyclerView() {
        contactsAdapter = ContactsAdapter()
        binding.contactRecyclerView.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}