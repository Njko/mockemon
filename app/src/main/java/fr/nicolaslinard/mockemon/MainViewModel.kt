package fr.nicolaslinard.mockemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.nicolaslinard.mockemon.model.IconsBar
import fr.nicolaslinard.mockemon.model.IconsBarProvider
import fr.nicolaslinard.mockemon.model.Mockemon
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _pockemons = MutableLiveData<List<Mockemon>>()
    val pockemons: LiveData<List<Mockemon>> = _pockemons

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> = _error

    private val _listOngletBar = MutableLiveData<List<IconsBar>>()
    val listOngletBar: LiveData<List<IconsBar>> = _listOngletBar

    private val mainPresenter = MainPresenter()

    init {
        initTestBar()
        fetchData()
    }

    private fun initTestBar() {
    _listOngletBar.value = IconsBarProvider.getIconsBar()
    }

    fun initView() {
        viewModelScope.launch {
        }
    }

    private fun fetchData() {
        _error.value = null
        _pockemons.value = emptyList()
        viewModelScope.launch {
            mainPresenter.getListMockemon(this) { result ->
                if(result.success != null) {
                    _pockemons.value = result.success?: emptyList()
                } else {
                    _pockemons.value = emptyList()
                    _error.value = result.error?: "Unknown error"
                }

            }
        }
    }

    fun handleEvent(uiEvent: MainEvent) {
        when (uiEvent) {
            MainEvent.Refresh -> {
                fetchData()
            }
            is MainEvent.SelectBar -> {
                selectionBarMenu(uiEvent.icon)
            }
        }
    }

    private fun selectionBarMenu(icon: IconsBar) {
        var list = _listOngletBar.value?.toMutableList()
        list  = list?.map { it.copy(iconSelected = false) }?.toMutableList()
        _listOngletBar.value = list?.map {
            if(it.icon == icon.icon) it.copy(iconSelected = true)
            else
                it }?.toMutableList()
    }

    sealed class MainEvent {
        object Refresh : MainEvent()
        data class SelectBar(val icon: IconsBar): MainEvent()
    }

}