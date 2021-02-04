package geekbrains.mariaL.kotlinapp.ui

import org.junit.Test

import org.junit.Assert.*

class ExtensionsKtTest {

    @Test
    fun `should return empty list for null`() {
        val result = emptyList<Int>()
        val testData = null

        assertEquals(result, sortDeskAndDistinctIntAndRemoteNulls(testData))
    }
}