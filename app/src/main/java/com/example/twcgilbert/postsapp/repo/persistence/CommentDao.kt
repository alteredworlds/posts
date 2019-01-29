package com.example.twcgilbert.postsapp.repo.persistence

import androidx.room.*
import com.example.twcgilbert.postsapp.repo.persistence.model.CommentEntity
import io.reactivex.Flowable

@Dao
abstract class CommentDao {

    /**
     * Get a Comment by id.
     * @return the Comment from the table with a specific id.
     */
    @Query("SELECT * FROM comments WHERE id = :id")
    abstract fun getById(id: Int): Flowable<CommentEntity>

    /**
     * Get a comments for postId.
     * @return the set of comments from the table with a specific postId.
     */
    @Query("SELECT * FROM comments WHERE id = :postId")
    abstract fun getByPostId(postId: Int): Flowable<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE id IN (:postIds)")
    abstract fun getByPostIdsSync(postIds: List<Int>): List<CommentEntity>

    @Query("SELECT COUNT(*) FROM comments WHERE postId = :postId")
    abstract fun getNumCommentsForPostId(postId: Int): Flowable<Int>

    /**
     * Insert a post in the database. If the post already exists, replace it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(comment: CommentEntity)

    /**
     * Insert Comments
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(comments: List<CommentEntity>)

    /**
     * Update Comments
     */
    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    abstract fun updateAll(comments: List<CommentEntity>)

    /**
     * Delete all Comments.
     */
    @Query("DELETE FROM comments")
    abstract fun deleteAll()

    /**
     * Delete all specified Comments.
     */
    @Query("DELETE FROM comments WHERE id IN (:ids)")
    abstract fun deleteAll(ids: List<Int>)

    @Transaction
    open fun replaceAll(items: List<CommentEntity>) {
        val postIds = items.distinctBy { it.postId }.map { it.postId }
        val existingItems = getByPostIdsSync(postIds)

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