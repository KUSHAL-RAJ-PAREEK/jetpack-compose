package com.krp.ktorclientandroid.data.remote

import com.krp.ktorclientandroid.data.remote.dto.PostRequest
import com.krp.ktorclientandroid.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostsServiceImpl(
    private val client: HttpClient
): PostsService {
    override suspend fun getPosts(): List<PostResponse> {
       return try {
           client.get{ url(HttpRoutes.POSTS) }
       }catch (e: RedirectResponseException){
           // 3xx - responses
           println("error : ${e.response.status.description}")
           emptyList()
       }catch (e: ClientRequestException){
           // 4xx - responses
           println("error : ${e.response.status.description}")
           emptyList()
       }catch (e: ServerResponseException){
           // 5xx - responses
           println("error : ${e.response.status.description}")
           emptyList()
       }catch(e: Exception){
           println("error : ${e.message}")
           emptyList()
       }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse>(){
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        }catch (e: RedirectResponseException){
            // 3xx - responses
            println("error : ${e.response.status.description}")
            null
        }catch (e: ClientRequestException){
            // 4xx - responses
            println("error : ${e.response.status.description}")
            null
        }catch (e: ServerResponseException){
            // 5xx - responses
            println("error : ${e.response.status.description}")
            null
        }catch(e: Exception){
            println("error : ${e.message}")
            null
        }
    }
}