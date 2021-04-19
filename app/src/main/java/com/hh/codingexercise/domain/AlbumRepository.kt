package com.hh.codingexercise.domain

import com.hh.codingexercise.core.ui.ViewState
import com.hh.codingexercise.core.utils.httpError
import com.hh.codingexercise.core.mapper.AlbumMapper
import com.hh.codingexercise.data.api.AlbumData
import com.hh.codingexercise.data.api.AlbumService
import com.hh.codingexercise.data.storage.AlbumDao
import com.hh.codingexercise.data.storage.entity.AlbumDataDb
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 */
interface AlbumRepository {

    /**
     * Gets tne cached album from database and tries to get
     * fresh album from web and save into database
     * if that fails then continues showing cached data.
     */
    fun getAlbums(): Flow<ViewState<List<AlbumDataDb>>>

    /**
     * Gets fresh album from web.
     */
    suspend fun getAlbumFromWebservice(): Response<List<AlbumData>>
}

@Singleton
class DefaultAlbumRepository @Inject constructor(
    private val albumDao: AlbumDao,
    private val albumService: AlbumService
) : AlbumRepository, AlbumMapper {

    override fun getAlbums(): Flow<ViewState<List<AlbumDataDb>>> = flow {
        // 1. Start with loading
        emit(ViewState.loading())

        // 2. Try to fetch fresh album from web + cache if any
        val freshAlbum = getAlbumFromWebservice()
        freshAlbum.body()?.toStorage()?.let(albumDao::clearAndCacheAlum)

        // 3. Get album from cache [cache is always source of truth]
        val cachedNAlbums = albumDao.getAlbums()
        emitAll(cachedNAlbums.map { ViewState.success(it) })
    }
        .flowOn(Dispatchers.IO)

    override suspend fun getAlbumFromWebservice(): Response<List<AlbumData>> {
        return try {
            albumService.getAlbums()
        } catch (e: Exception) {
            httpError(404)
        }
    }
}

@Module
@InstallIn(ApplicationComponent::class)
interface AlbumRepositoryModule {
    /* Exposes the concrete implementation for the interface */
    @Binds
    fun it(it: DefaultAlbumRepository): AlbumRepository
}