package ru.elgreed.testtask.viewmodel

import androidx.paging.DataSource
import ru.elgreed.testtask.model.NumberRepository


class NumberDataSourceFactory(_numbersRepository: NumberRepository)
    : DataSource.Factory<Int, Long>() {
    var numbersRepository = _numbersRepository

    override fun create(): DataSource<Int, Long> {
        return NumberDataSource(numbersRepository.numbersModel)
    }
}