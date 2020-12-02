package ru.elgreed.testtask.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.button.MaterialButtonToggleGroup
import ru.elgreed.testtask.databinding.ActivityMainBinding
import ru.elgreed.testtask.model.FibonacciNumbersModel
import ru.elgreed.testtask.model.PrimeNumbersModel
import ru.elgreed.testtask.viewmodel.NumbersViewModel

class MainActivity : AppCompatActivity() {
    private val adapter: NumbersAdapter by lazy { NumbersAdapter(applicationContext) }
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val viewModel: NumbersViewModel by viewModels()
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
        binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        observeLifeData(viewModel)
    }

    private fun observeLifeData(viewModel: NumbersViewModel) {
        viewModel.numberList.observe(this, Observer(adapter::submitList))
        binding.recyclerView.adapter = adapter
    }
}