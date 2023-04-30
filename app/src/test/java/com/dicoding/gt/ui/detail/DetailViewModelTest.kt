package com.dicoding.gt.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.dicoding.core.data.Status
import com.dicoding.core.domain.model.News
import com.dicoding.core.domain.usecase.NewsUseCase
import com.dicoding.gt.detail.DetailViewModel
import com.dicoding.gt.utils.DataDummy
import com.dicoding.gt.utils.MainDispatcherRule
import com.dicoding.gt.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
   @get:Rule
   val instantExecutorRule = InstantTaskExecutorRule()

   @get:Rule
   val mainDispatcherRule = MainDispatcherRule()

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
      val expectedResponse = MutableLiveData<Status<News>>()
      expectedResponse.value = Status.Success(dummyNews)

      `when`(newsUseCase.getDetailNews(dummyNews, "key")).thenReturn(expectedResponse.asFlow())

      val actualResponse = detailViewModel.detailNews(dummyNews, "key").getOrAwaitValue()

      verify(newsUseCase).getDetailNews(dummyNews, "key")
      Assert.assertNotNull(actualResponse)
      Assert.assertTrue(actualResponse is Status.Success)
   }

   @Test
   fun `when user click fav button trigger setFavorite function`() = runTest {
      newsUseCase.setFavoriteNews(dummyNews, false)
      verify(newsUseCase).setFavoriteNews(dummyNews, false)
   }
}