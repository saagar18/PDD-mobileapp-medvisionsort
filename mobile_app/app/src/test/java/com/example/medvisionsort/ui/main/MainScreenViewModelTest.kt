package com.example.medvisionsort.ui.main

import com.example.medvisionsort.data.DataRepository
import com.example.medvisionsort.data.model.MedicalImage
import com.example.medvisionsort.data.model.MedicalStats
import com.example.medvisionsort.data.model.ModalityCounts
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MainScreenViewModelTest {
  @Test
  fun authState_initiallyLogin() = runTest {
    val viewModel = MainScreenViewModel(FakeMyModelRepository())
    assertEquals(viewModel.authState.value, AuthState.LOGIN)
  }

  @Test
  fun currentTab_initiallyDashboard() = runTest {
    val viewModel = MainScreenViewModel(FakeMyModelRepository())
    assertEquals(viewModel.currentTab.value, NavigationTab.DASHBOARD)
  }
}

private class FakeMyModelRepository : DataRepository {
  override val data: Flow<List<String>> = flow { emit(listOf("Sample")) }

  override fun getStatsFlow(): Flow<MedicalStats> = flow {
    emit(MedicalStats(0, 0.0, 0.0, 0, ModalityCounts(0, 0, 0, 0)))
  }

  override fun getRecentImagesFlow(): Flow<List<MedicalImage>> = flow {
    emit(emptyList())
  }

  override suspend fun classifyImage(fileBytes: ByteArray, filename: String): MedicalImage {
    return MedicalImage("", "", "", 0.0, "", "", "", "", "", "")
  }
}
