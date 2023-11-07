package fr.nicolaslinard.mockemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.nicolaslinard.mockemon.model.Mockemon
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _pockemons = MutableLiveData<List<Mockemon>>()
    val pockemons: LiveData<List<Mockemon>> = _pockemons

    private val mainPresenter = MainPresenter()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            mainPresenter.getListMockemon(this) { result ->
                _pockemons.value = result
            }
        }
    }
}