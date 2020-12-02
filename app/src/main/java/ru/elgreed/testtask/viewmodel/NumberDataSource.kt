package ru.elgreed.testtask.viewmodel

import androidx.paging.PositionalDataSource
import ru.elgreed.testtask.model.NumbersModel

class NumberDataSource(private val numbersModel: NumbersModel)
    : PositionalDataSource<Long>() {


    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Long>) {
       val result : List<Long> = numbersModel.getNumbersData(params.requestedLoadSize)
        callback.onResult(result, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Long>) {
        val result : List<Long> = numbersModel.getNumbersData(params.loadSize)
        callback.onResult(result)
    }
}