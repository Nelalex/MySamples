package com.nelalexxx.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel(
    private val dao: ContactDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _state = MutableStateFlow(ContactState())


    //Тоже метод из асинхронного программирования который позволяет
    //При получении повторного запроса пока 1 не завершен, отменять предыдущий запрос
    //и выполнять последний, чтобы работать только с актуальными данными
    //Каждое действие будет выполняться в отдельном потоке, но в главный поток прийдет последнее
    private val _contacts = _sortType.flatMapLatest { sortType ->
        when(sortType) {
            SortType.FIRST_NAME -> dao.getContactsOrderedByFirstName()
            SortType.LAST_NAME -> dao.getContactsOrderedByLastName()
            SortType.PHONE_NUMBER -> dao.getContactsOrderedByPhoneNumber()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    // здесь нет задержки потому что это работа именно с данными которая может крашуть программу
    // при неожиданной смерти Activity

    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())


    fun onEvent(event: ContactEvent) {
        when (event) {
            is ContactEvent.DeleteContact ->
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            ContactEvent.HideDialog ->
                _state.update { it.copy(isAddingContact = false) }

            is ContactEvent.SetFirstName ->
                _state.update { it.copy(firstName = event.firstName) }

            is ContactEvent.SetLastName ->
                _state.update { it.copy(latName = event.lastName) }

            is ContactEvent.SetPhoneNumber ->
                _state.update { it.copy(phoneNumber = event.phoneNumber) }

            ContactEvent.ShowDialog ->
                _state.update { it.copy(isAddingContact = true) }

            is ContactEvent.SortContacts -> {
                _sortType.value = event.sortType
            }

            ContactEvent.SaveContact -> {
                val firstName = _state.value.firstName
                val lastName = _state.value.latName
                val phoneNumber = _state.value.phoneNumber

                if (firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank() )
                    return

                val contact = Contact (
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber
                )

                viewModelScope.launch {
                    dao.upsertContact(contact)
                }
                _state.update { it.copy(
                    isAddingContact = false,
                    firstName = "",
                    latName = "",
                    phoneNumber = ""
                ) }

            }

        }
    }

}