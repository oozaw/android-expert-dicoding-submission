package com.dicoding.gt.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.dicoding.core.data.NewsRepository
import com.dicoding.core.data.Status
import com.dicoding.core.domain.model.News
import com.dicoding.core.domain.usecase.NewsInteractor
import com.dicoding.core.domain.usecase.NewsUseCase
import com.dicoding.gt.utils.DataDummy
import com.dicoding.gt.utils.MainDispatcherRule
import com.dicoding.gt.utils.observeForTesting
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
class NewsUseCaseTest {
   @get:Rule
   val instantExecutorRule = InstantTaskExecutorRule()

   @get:Rule
   val mainDispatcherRule = MainDispatcherRule()

   private lateinit var newsUseCase: NewsUseCase
   private val dummyNews = listOf(DataDummy.generateDummyNews())
   private lateinit var expectedResult: MutableLiveData<Status<List<News>>>

   @Mock
   private lateinit var newsRepository: NewsRepository

   @Before
   fun setUp() {
      newsUseCase = NewsInteractor(newsRepository)
      expectedResult = MutableLiveData<Status<List<News>>>()
      expectedResult.value = Status.Success(dummyNews)
      `when`(newsUseCase.getGameNews()).thenReturn(expectedResult.asFlow())
   }

   @Test
   fun `should get list of news from repository`() = runTest {
      val actualResult = newsUseCase.getGameNews().asLiveData()

      verify(newsRepository).getGameNews()

      actualResult.observeForTesting {
         Assert.assertNotNull(actualResult)
         Assert.assertTrue(actualResult.value is Status.Success)
         Assert.assertEquals(expectedResult.value?.data, actualResult.value?.data)
      }
   }
}