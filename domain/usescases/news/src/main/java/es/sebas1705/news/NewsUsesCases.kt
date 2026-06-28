package es.sebas1705.news


import es.sebas1705.news.usescases.GetNews

/**
 * Use cases for the news
 *
 * @property getNews [GetNews]: Use case to get news
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class NewsUsesCases(
    val getNews: GetNews
)