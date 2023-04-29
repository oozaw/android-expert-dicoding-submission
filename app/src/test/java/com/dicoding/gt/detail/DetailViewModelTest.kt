package com.dicoding.gt.detail

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.core.data.NewsRepository
import com.dicoding.core.data.remote.response.ApiStatus
import com.dicoding.core.data.remote.response.NewsDetailResponse
import com.dicoding.core.domain.model.News
import com.dicoding.core.domain.usecase.NewsUseCase
import com.dicoding.gt.utils.DataDummy
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
   @get:Rule
   val instantExecutorRule = InstantTaskExecutorRule()

   @Mock
   private lateinit var newsUseCase: NewsUseCase
   private lateinit var detailViewModel: DetailViewModel
   private val dummyNewsDetailResponse = DataDummy.generateDummyDetailNewsResponse()
   private val dummyNewsResponse = DataDummy.generateDummyNewsResponse()
   private val dummyNews = DataDummy.generateDummyNews()

   @Before
   fun setUp() {
      detailViewModel = DetailViewModel(newsUseCase)
   }

   @Test
   fun `when user click a news the response should not Null and return Success`() {
      val expectedResponse = MutableLiveData<ApiStatus<News>>()
      expectedResponse.value = ApiStatus.Success(dummyNews)

      Mockito.`when`(newsUseCase.getDetailNews(dummyNews, "key")).thenReturn(expectedResponse)

      val actualResponse = addStoryViewModel.postStory(description, photoFile, null, null).getOrAwaitValue()
      Mockito.verify(userRepository).postStory(description, photoFile, null, null)
      Assert.assertNotNull(actualResponse)
      Assert.assertTrue(actualResponse is Result.Success)
      Assert.assertFalse((actualResponse as Result.Success).data.error)
   }
}