package com.example.twcgilbert.postsapp.repo.persistence

import android.arch.persistence.room.*
import com.example.twcgilbert.postsapp.repo.model.Post
import com.example.twcgilbert.postsapp.repo.persistence.model.PostEntity
import io.reactivex.Flowable

@Dao
abstract class PostDao {
    @Query("SELECT posts.id, posts.userId, posts.title, posts.body, users.name AS userName, users.email AS userEmail"
            + " FROM posts"
            + " LEFT JOIN users ON posts.userId = users.id")
    abstract fun getAllDenormalised(): Flowable<List<Post>>

    @Query("SELECT * FROM posts")
    abstract fun getAll(): Flowable<List<PostEntity>>

    @Query("SELECT * FROM posts")
    abstract fun getAllSync(): List<PostEntity>

    /**
     * Get a post by id.
     * @return the post from the table with a specific id.
     */
    @Query("SELECT * FROM posts WHERE id = :postId")
    abstract fun getById(postId: Int): Flowable<PostEntity>

    /**
     * Insert a post in the database. If the post already exists, replace it.
     * @param post the post to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(post: PostEntity)

    /**
     * Insert Posts. If any post already exists, replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(posts: List<PostEntity>)

    /**
     * Update Posts. If any post doesn't already exist, rollback
     */
    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun updateAll(posts: List<PostEntity>)

    /**
     * Delete all posts.
     */
    @Query("DELETE FROM posts")
    abstract fun deleteAll()

    /**
     * Delete all specified posts.
     */
    @Query("DELETE FROM posts WHERE id IN (:ids)")
    abstract fun deleteAll(ids: List<Int>)

    @Transaction
    open fun replaceAll(items: List<PostEntity>) {
        val existingItems = getAllSync()
        val existingItemIds = existingItems.map { it.id }
        val newItemIds = items.map { it.id }

        // items to DELETE (found in database but not in newItems)
        val itemsToDelete = existingItemIds - newItemIds
        deleteAll(itemsToDelete)

        // items to INSERT / UPDATE
        // here we remove all totally unchanged items (data item equality match)
        // leaves behind either brand new items, or items requiring update
        val itemsToInsertOrUpdate = items.minus(existingItems)

        val itemsToUpdate = itemsToInsertOrUpdate.filter { existingItemIds.contains(it.id) }
        updateAll(itemsToUpdate)

        val itemsToInsert = itemsToInsertOrUpdate.filter { !existingItemIds.contains(it.id) }
        insertAll(itemsToInsert)
    }
}