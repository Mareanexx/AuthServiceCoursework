package ru.mareanexx.authservice.api.exception

class WrongEmailOrPasswordException(mes: String) : Exception(mes)

class UserAlreadyExists(mes: String) : Exception(mes)