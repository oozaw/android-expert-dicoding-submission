package com.dicoding.gt.utils

import com.dicoding.core.data.remote.response.NewsDetailResponse
import com.dicoding.core.data.remote.response.NewsResponse
import com.dicoding.core.domain.model.News

object DataDummy {
   fun generateDummyDetailNewsResponse(): NewsDetailResponse {
      return NewsDetailResponse(
         "GET",
         true,
         generateDummyNewsResponse(),
         "Berhasil"
      )
   }

   fun generateDummyNewsResponse(): NewsResponse {
      val listString = generateListOfString()

      return NewsResponse(
         "https://thelazy.media/wp-content/uploads/2021/01/vlcsnap-2021-01-28-11h57m53s604-1068x601.png",
         "Aldy Wayong",
         "game",
         "January 28, 2021",
         "Balan Wonderworld Preview – Dunia Yang Misterius",
         "ini key",
         "Balan Wonderworld, sebuah game terbaru Square Enix yang berada dibawah nama Balan Company ini akhirnya bisa juga kami coba. Sejak pertama kali diumumkan tentu kami masih cukup mengira-ngira seperti apa game terbaru yang hadir dari kreator dibalik game Sonic ini.",
         listString,
         listString,
         listString
      )
   }

   fun generateDummyNews(): News {
      val data = generateDummyNewsResponse()
      return News(
         data.thumb,
         data.author,
         data.tag,
         data.time,
         data.title,
         data.key,
         data.desc,
         data.figure,
         data.categories,
         data.content,
         false
      )
   }

   private fun generateListOfString(): List<String> {
      return listOf("Games", "Console", "Reviews")
   }
}