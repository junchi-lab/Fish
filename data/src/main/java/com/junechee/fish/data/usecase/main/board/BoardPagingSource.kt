package com.junechee.fish.data.usecase.main.board

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.junechee.fish.data.model.toDomainModel
import com.junechee.fish.data.retrofit.BoardService
import com.junechee.fish.domain.model.Board
import javax.inject.Inject

class BoardPagingSource @Inject constructor(
    private val boardService: BoardService
) : PagingSource<Int, Board>() {

    override fun getRefreshKey(state: PagingState<Int, Board>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition = anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Board> {
        try {
            val page: Int = params.key ?: 1
            val loadSize = params.loadSize

            val response = boardService.getBoards(
                page = page,
                size = loadSize
            )

            val size = response.data.size
            return LoadResult.Page(
                data = response.data.map { it.toDomainModel() },
                prevKey = null,
                nextKey = if (size == loadSize) page + 1 else null
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}