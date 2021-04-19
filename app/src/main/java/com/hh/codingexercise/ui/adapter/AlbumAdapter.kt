package com.hh.codingexercise.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hh.codingexercise.R
import com.hh.codingexercise.core.utils.inflate
import com.hh.codingexercise.databinding.RowAlbumBinding
import com.hh.codingexercise.data.storage.entity.AlbumDataDb

/**
 * The Album adapter to show the albums in a list.
 */
class AlbumAdapter(
) : ListAdapter<AlbumDataDb, AlbumAdapter.AlbumHolder>(DIFF_CALLBACK) {

    /**
     * Inflate the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumHolder(parent.inflate(R.layout.row_album))

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(albumHolder: AlbumHolder, position: Int) = albumHolder.bind(getItem(position))

    /**
     * View Holder Pattern
     */
    class AlbumHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RowAlbumBinding.bind(itemView)

        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(albumDataDb: AlbumDataDb) = with(itemView) {
            binding.albumTitle.text = "".plus(albumDataDb.title)
            binding.userId.text = albumDataDb.id.toString()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AlbumDataDb>() {
            override fun areItemsTheSame(oldItem: AlbumDataDb, newItem: AlbumDataDb): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: AlbumDataDb, newItem: AlbumDataDb): Boolean = oldItem == newItem
        }
    }
}