package ru.elgreed.testtask.model

import java.util.*

class PrimeNumbersModel : NumbersModel {
    private var lastNumber = 1L;
    override fun getNumbersData(pageSize: Int): List<Long> {
        val list : ArrayList<Long> = ArrayList(pageSize)

            for(i in 0 until pageSize) {
                while (true)
                {
                    if(isPrime(++lastNumber))
                    {
                        list.add(lastNumber)
                        break
                    }
                }
            }
        return list
    }

    private fun isPrime(number: Long): Boolean
    {
        if (number < 2) return false
        for (m in 2 until number) {
            if (number % m == 0L) return false
        }
        return true
    }
}