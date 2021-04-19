package com.hh.codingexercise.data.storage

import androidx.room.*
import com.hh.codingexercise.data.storage.entity.AlbumDataDb
import kotlinx.coroutines.flow.Flow

/**
 * Defines access layer to album table
 */
@Dao
interface AlbumDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(albums: List<AlbumDataDb>): List<Long>

    @Query("DELETE FROM album_table")
    fun clearAllAlbums()

    @Transaction
    fun clearAndCacheAlum(albums: List<AlbumDataDb>) {
        clearAllAlbums()
        insertAlbum(albums)
    }

    /**
     * Get all the albums from table
     */
    @Query("SELECT * FROM album_table ORDER BY title ASC")
    fun getAlbums(): Flow<List<AlbumDataDb>>
}