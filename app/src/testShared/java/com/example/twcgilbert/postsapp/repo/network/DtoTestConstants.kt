package com.example.twcgilbert.postsapp.repo.network

import com.example.twcgilbert.postsapp.repo.network.model.CommentDto
import com.example.twcgilbert.postsapp.repo.network.model.PostDto
import com.example.twcgilbert.postsapp.repo.network.model.UserDto

class DtoTestConstants {

    companion object {

        const val userId1 = 1
        const val userId1Name = "Tom"
        const val userId1Username = "Username1"
        const val userId1Email = "test1@test.com"

        const val userId2 = 2
        const val userId2Name = "Tim"
        const val userId2Username = "Username2"
        const val userId2Email = "test2@test.com"

        const val postId1 = 1
        const val post1Title = "Hi1"
        const val post1Body = "Body1"

        const val postId2 = 2
        const val post2Title = "Hi2"
        const val post2Body = "Body2"

        val testPost1 = PostDto(userId1,
                postId1,
                post1Title,
                post1Body)

        val testPost2 = PostDto(userId2,
                postId2,
                post2Title,
                post2Body)

        val testPost3 = PostDto(userId1, 3, "Hi3", "Body")
        val testPost4 = PostDto(userId1, 4, "Hi4", "Body")
        val testPost5 = PostDto(userId1, 5, "Hi5", "Body")
        val testPost6 = PostDto(userId1, 6, "Hi6", "Body")
        val testPost7 = PostDto(userId1, 7, "Hi7", "Body")
        val testPost8 = PostDto(userId1, 8, "Hi8", "Body")
        val testPost9 = PostDto(userId2, 9, "Hi9", "Body")
        val testPost10 = PostDto(userId2, 10, "Hi10", "Body")
        val testPost11 = PostDto(userId2, 11, "Hi11", "Body")
        val testPost12 = PostDto(userId2, 12, "Hi12", "Body")
        val testPost13 = PostDto(userId2, 13, "Hi13", "Body")
        val testPost14 = PostDto(userId2, 14, "Hi14", "Body")
        val testPost15 = PostDto(userId2, 15, "Hi15", "Body")
        val testPost16 = PostDto(userId2, 16, "Hi16", "Body")

        val users = arrayListOf(
                UserDto(userId1,
                        userId1Name,
                        userId1Username,
                        userId1Email),
                UserDto(userId2,
                        userId2Name,
                        userId2Username,
                        userId2Email)
        )

        val posts = arrayListOf(
                DtoTestConstants.testPost1,
                DtoTestConstants.testPost2,
                DtoTestConstants.testPost3,
                DtoTestConstants.testPost4,
                DtoTestConstants.testPost5,
                DtoTestConstants.testPost6,
                DtoTestConstants.testPost7,
                DtoTestConstants.testPost8,
                DtoTestConstants.testPost9,
                DtoTestConstants.testPost10,
                DtoTestConstants.testPost11,
                DtoTestConstants.testPost12,
                DtoTestConstants.testPost13,
                DtoTestConstants.testPost14,
                DtoTestConstants.testPost15,
                DtoTestConstants.testPost16
        )

        val comments = arrayListOf(
                CommentDto(postId1,
                        1,
                        "Comment1",
                        "fred@fred.com",
                        "Body1")
        )
    }
}