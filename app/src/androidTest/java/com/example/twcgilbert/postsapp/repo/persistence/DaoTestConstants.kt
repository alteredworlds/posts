package com.example.twcgilbert.postsapp.repo.persistence

import com.example.twcgilbert.postsapp.repo.persistence.model.CommentEntity
import com.example.twcgilbert.postsapp.repo.persistence.model.PostEntity
import com.example.twcgilbert.postsapp.repo.persistence.model.UserEntity

class DaoTestConstants {
    companion object {
        val USER = UserEntity(1, "name", "username", "someone@somewhere.com")

        val POST = PostEntity(1, USER.id, "title", "body")

        val POST1 = PostEntity(POST.id, USER.id, "new title", "new body")

        val POST2 = PostEntity(POST.id, 123, "new title", "new body")

        val POST3 = PostEntity(2, USER.id, "title2", "body2")

        val COMMENT = CommentEntity(
                1,
                POST.id,
                "name",
                "someone@somewhere.com",
                "body")

        val COMMENT1 = CommentEntity(
                COMMENT.id,
                POST.id,
                "new name",
                "someone@somewhereelse.com",
                "body1")

        val COMMENT2 = CommentEntity(
                COMMENT.id,
                123,
                "new name",
                "someone@somewhereelse.com",
                "body1")

        val COMMENT3 = CommentEntity(
                2,
                POST.id,
                "name3",
                "someone@somewhereelse.com",
                "body3")
    }
}