package com.hh.codingexercise.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hh.codingexercise.core.ui.ViewState
import com.hh.codingexercise.core.ui.base.BaseActivity
import com.hh.codingexercise.core.utils.observeNotNull
import com.hh.codingexercise.core.utils.toast
import com.hh.codingexercise.databinding.ActivityMainBinding
import com.hh.codingexercise.ui.adapter.AlbumAdapter
import com.hh.codingexercise.ui.viewmodel.AlbumViewModel


class AlbumActivity : BaseActivity() {

    private val albumViewModel: AlbumViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    /**
     * Starting point of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up RecyclerView and adapter
        binding.albumList.setEmptyView(binding.emptyLayout.emptyView)
        binding.albumList.setProgressView(binding.progressLayout.progressView)

        val adapter = AlbumAdapter()
        binding.albumList.adapter = adapter
        binding.albumList.layoutManager = LinearLayoutManager(this)

        // Update the UI on state change
        albumViewModel.getAlbum().observeNotNull(this) { state ->
            if (state is ViewState.Success){
                print(state.data)
            }
            when (state) {
                is ViewState.Success -> adapter.submitList(state.data)
                is ViewState.Loading -> binding.albumList.showLoading()
                is ViewState.Error -> toast("Something went wrong ¯\\_(ツ)_/¯ => ${state.message}")
            }
        }

    }
}
