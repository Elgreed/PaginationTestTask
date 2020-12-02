package ru.elgreed.testtask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.button.MaterialButtonToggleGroup
import ru.elgreed.testtask.databinding.ActivityMainBinding
import ru.elgreed.testtask.model.FibonacciNumbersModel
import ru.elgreed.testtask.model.PrimeNumbersModel
import ru.elgreed.testtask.viewmodel.NumbersViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var adapter : NumbersAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel : NumbersViewModel by viewModels()
        creteRecyclerView(viewModel)
        setListeners(viewModel)
    }

    private fun setListeners(viewModel: NumbersViewModel) {
        binding.toggleGroup.addOnButtonCheckedListener { materialButtonToggleGroup: MaterialButtonToggleGroup, id: Int, b: Boolean ->
            if (binding.fibonacciNumbersButton.id == id && b) {
                viewModel.refreshData(FibonacciNumbersModel())
            } else if (binding.primeNumbersButton.id == id && b) {
                viewModel.refreshData(PrimeNumbersModel())
            }
        }
    }

    private fun creteRecyclerView(viewModel: NumbersViewModel) {
        adapter = NumbersAdapter(applicationContext)
        binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        observeLifeData(viewModel)
    }

    private fun observeLifeData(viewModel: NumbersViewModel)
    {
        viewModel.numberList.observe(this, Observer(adapter::submitList))
        binding.recyclerView.adapter = adapter
    }
}