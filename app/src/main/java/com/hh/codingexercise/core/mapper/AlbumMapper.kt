package com.hh.codingexercise.core.mapper

import com.hh.codingexercise.data.api.AlbumData
import com.hh.codingexercise.data.storage.entity.AlbumDataDb

interface AlbumMapper : Mapper<AlbumDataDb, AlbumData> {
    override fun AlbumDataDb.toRemote(): AlbumData {
        return AlbumData(
            id = id,
            userId = userId,
            title = title,
        )
    }

    override fun AlbumData.toStorage(): AlbumDataDb {
        return AlbumDataDb(
            id = id,
            userId = userId,
            title = title,
        )
    }
}