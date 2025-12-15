package com.example.apipaginationpractice.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apipaginationpractice.data.model.DataModel
import com.example.apipaginationpractice.data.model.TopAnimeModel
import com.example.apipaginationpractice.repository.TopAnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAnimeViewModel @Inject constructor(
    private val repository: TopAnimeRepository
): ViewModel(){
    private val pageSize = 10 // items per page

    // keep track of current page
    private val _currentPage = MutableStateFlow(1)

    private val _pageLoadState = MutableStateFlow<PageLoadState>(PageLoadState.Loading)
    val pageLoadState: StateFlow<PageLoadState> = _pageLoadState


    init {
        observePages()
    }
    // ViewModel methods should OWN pagination
    fun nextPage(){
      _currentPage.value += 1 // increment 1 for next page
      //loadPage() // update page content based on increment
    }


    fun previousPage(){
        // add conditional to ensure previous page doesn't go below 1
        if(_currentPage.value >1){
            _currentPage.value -=1
           // loadPage()
        }
    }

    // initially observe pages that is colleced by current page value
    fun observePages(){
        viewModelScope.launch {
            _currentPage.collect { page->
                loadPage(page)
            }
        }
    }

    fun loadPage(page:Int){
        viewModelScope.launch {
            _pageLoadState.value = PageLoadState.Loading

            try {
                val data = repository.getAllTopAnime(
                    page = page,
                    limit = pageSize
                )
                _pageLoadState.value = PageLoadState.Success(data)

            }catch(e:Exception){
                _pageLoadState.value = PageLoadState.Error("${e.message}")
            }

        }



    }
}


sealed class PageLoadState{
    object Loading: PageLoadState()
    data class Success(val fullModel: TopAnimeModel) : PageLoadState() // <-- EXPECTS TopAnimeModel
    data class Error(val message:String):PageLoadState()
}