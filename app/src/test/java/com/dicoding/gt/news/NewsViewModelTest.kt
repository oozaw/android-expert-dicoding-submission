package com.dicoding.gt.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.gt.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {
   @get:Rule
   val instantExecutorRule = InstantTaskExecutorRule()

   @get:Rule
   val mainDispatcherRule = MainDispatcherRule()

}