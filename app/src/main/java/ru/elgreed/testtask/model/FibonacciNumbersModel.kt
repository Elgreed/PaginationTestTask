package ru.elgreed.testtask.model

class FibonacciNumbersModel : NumbersModel {
    private var lastNumber1 = 0L
    private var lastNumber2 = 1L

    override fun getNumbersData(pageSize: Int): List<Long> {
        val list : ArrayList<Long> = ArrayList(pageSize)
        if(lastNumber1 == 0L)
        {
            list.add(lastNumber1)
            list.add(lastNumber2)
        }
        val x = list.size
        for(i in x until pageSize)
        {
            val sum : Long = lastNumber1 + lastNumber2
            list.add(sum)
            lastNumber1 = lastNumber2
            lastNumber2 = sum
        }
        return list
    }
}