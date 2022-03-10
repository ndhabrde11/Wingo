package com.nayandhabarde.wingo.util

import com.google.common.truth.Truth.assertThat
import com.nayandhabarde.wingo.constants.WingoDateFormats
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.text.SimpleDateFormat

class WingoDateTimeFormatterTest {

    private val serverDateFormat = SimpleDateFormat(WingoDateFormats.SERVER_FORMAT.value)
    private val wingoDateTimeFormatter = WingoDateTimeFormatter(serverDateFormat)


    @Test
    fun `test format to month and date of month returns correct`() {
        val output = wingoDateTimeFormatter.formatToMonthDateOfMonth("2021-07-10T06:31:20Z")
        assertThat(output).isEqualTo("Jul 10")
    }

    @Test
    fun `test transform start and end date to month date of month range returns correct`() {
        val output = wingoDateTimeFormatter.transformStartAndEndDateToMonthDateOfMonthRange("2021-07-10T06:31:30Z", "2021-10-10T06:31:30Z")
        assertThat(output).isEqualTo("Jul 10 - Oct 10")
    }
}