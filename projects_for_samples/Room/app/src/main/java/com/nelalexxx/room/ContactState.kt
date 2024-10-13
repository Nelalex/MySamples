package com.nelalexxx.room

data class ContactState (
    val contacts: List<Contact> = emptyList(),
    val firstName: String = "",
    val latName: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
){
}