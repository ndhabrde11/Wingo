package com.nayandhabarde.wingo.paging

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.nayandhabarde.wingo.retrofit.ApiService
import com.nayandhabarde.wingo.retrofit.response.PageResponseUtil
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class TournamentPagingSourceTest {

    private val tournamentFactory = TournamentFactory()
    private val pageResponseUtil = PageResponseUtil()
    private val mockList = listOf(
        tournamentFactory.create(6253),
        tournamentFactory.create(6252),
        tournamentFactory.create(6266),
    )
    private var service: ApiService = mock(ApiService::class.java)

    @Test
    fun loadReturnsPageWhenOnSuccessLoad() = runBlocking {
        val pagingSource = TournamentPagingSource(
            service,
            2,
            wingoDateTimeFormatter.getCurrentMonthDateServerFormatted(),
            wingoDateTimeFormatter.getCurrentMonthDatePlusNextYearServerFormatted()
        )
        `when`(service.getTournaments(1, 2)).thenReturn(pageResponseUtil.getPageOneResponse())
        val actual = pagingSource.load(PagingSource.LoadParams.Refresh(
            key = null,
            loadSize = 2,
            placeholdersEnabled = false
        ))
        val expected = PagingSource.LoadResult.Page(listOf(mockList[0], mockList[1]), null, 2)
        assertThat(actual)
            .isEqualTo(expected)
    }
    

}