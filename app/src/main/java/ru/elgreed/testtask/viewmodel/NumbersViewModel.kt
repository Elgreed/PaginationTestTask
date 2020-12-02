package ru.elgreed.testtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ru.elgreed.testtask.model.NumberRepository
import ru.elgreed.testtask.model.NumbersModel


class NumbersViewModel : ViewModel() {
    val numberList: LiveData<PagedList<Long>> by lazy { createLiveData() }
    private val numbersRepository = NumberRepository()

    private fun createLiveData(): LiveData<PagedList<Long>> {
        val numberDataSourceFactory = NumberDataSourceFactory(numbersRepository)
        val config = createPagedListConfig()
        return LivePagedListBuilder(numberDataSourceFactory, config)
                .build()
    }

    fun refreshData(numbersModel: NumbersModel) {
        numbersRepository.numbersModel = numbersModel
        numberList.value?.dataSource?.invalidate()
    }

    private fun createPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
                .setPageSize(10)
                .setEnablePlaceholders(false)
                .build()
    }
}