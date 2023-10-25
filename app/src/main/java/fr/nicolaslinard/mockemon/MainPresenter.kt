package fr.nicolaslinard.mockemon

import kotlinx.coroutines.CoroutineScope

class MainPresenter {
    private val mainRepository = MainService()

    fun getListMockemon(coroutineScope: CoroutineScope, displayListPokemon: (String) -> Unit) {
        mainRepository.fetchData(coroutineScope) { result ->
            result.success?.let {
               displayListPokemon(it.pokemonlist)
            }
        }
    }

}