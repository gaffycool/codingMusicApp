package com.hh.codingexercise.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hh.codingexercise.core.ui.ViewState
import com.hh.codingexercise.domain.AlbumRepository
import com.hh.codingexercise.data.storage.entity.AlbumDataDb

/**
 * A container for [AlbumDataDb] related data to show on the UI.
 */
class AlbumViewModel @ViewModelInject constructor(
        albumRepository: AlbumRepository
) : ViewModel() {

    private val albumDb: LiveData<ViewState<List<AlbumDataDb>>> = albumRepository.getAlbums().asLiveData()

    /**
     * Return album to observeNotNull on the UI.
     */
    fun getAlbum(): LiveData<ViewState<List<AlbumDataDb>>> = albumDb
}