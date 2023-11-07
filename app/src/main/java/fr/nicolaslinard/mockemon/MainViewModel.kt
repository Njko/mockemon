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

    private fun fetchData() {
        println("TEST : fetchData")
        viewModelScope.launch {
            mainPresenter.getListMockemon(this) { result ->
                println("TEST : result fin ${result.size}")
                _pockemons.value = result
            }
        }
    }

    fun handleEvent(uiEvent: MainEvent) {
        when (uiEvent) {
            MainEvent.Refresh -> fetchData()
        }
    }

    sealed class MainEvent {
        object Refresh : MainEvent()
    }

}