package com.example.twcgilbert.postsapp.repo.persistence

import androidx.room.*
import com.example.twcgilbert.postsapp.repo.persistence.model.UserEntity
import io.reactivex.Flowable

@Dao
abstract class UserDao {

    @Query("SELECT * FROM users")
    abstract fun getAll(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM users")
    abstract fun getAllSync(): List<UserEntity>

    /**
     * Get a user by id.
     * @return the user from the table with a specific id.
     */
    @Query("SELECT * FROM users WHERE id = :userId")
    abstract fun getById(userId: Int): Flowable<UserEntity>

    /**
     * Insert a user in the database. If the user already exists, replace it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(user: UserEntity)

    /**
     * Insert Users
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(users: List<UserEntity>)

    /**
     * Update Users (rollback if attempt made to update non-existant user)
     */
    @Update(onConflict = OnConflictStrategy.ABORT)
    abstract fun updateAll(users: List<UserEntity>)

    /**
     * Delete all users.
     */
    @Query("DELETE FROM users")
    abstract fun deleteAll()

    /**
     * Delete all specified users.
     */
    @Query("DELETE FROM users WHERE id IN (:ids)")
    abstract fun deleteAll(ids: List<Int>)

    @Transaction
    open fun replaceAll(items: List<UserEntity>) {
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