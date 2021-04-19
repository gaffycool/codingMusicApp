package com.hh.codingexercise.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hh.codingexercise.data.storage.entity.AlbumDataDb.AlbumDatas.tableName

/**
 * Describes how the album are stored.
 */
@Entity(tableName = tableName)
data class AlbumDataDb(


    @PrimaryKey
    @ColumnInfo(name = AlbumDatas.Column.id)
    val id: Int = 0,


    @ColumnInfo(name = AlbumDatas.Column.userId)
    val userId: Int? = null,


    @ColumnInfo(name = AlbumDatas.Column.title)
    val title: String? = null,

    ) {


    object AlbumDatas {
        const val tableName = "album_table"

        object Column {
            const val id = "id"
            const val userId = "userId"
            const val title = "title"
        }
    }
}