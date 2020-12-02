package ru.elgreed.testtask.model

interface NumbersModel {
    fun getNumbersData(pageSize: Int): List<Long>
}