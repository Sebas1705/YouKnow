package es.sebas1705.youknow.data.local.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.sebas1705.youknow.data.local.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * User DAO
 *
 * @see Dao
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Dao
interface UserDao {

    //Selects:
    /**
     * Get the user with an id
     *
     * @param firebaseId [String]: firebase id of the user
     *
     * @return [UserEntity]
     */
    @Query("SELECT * FROM users_table WHERE firebaseId = :firebaseId")
    fun getByID(firebaseId: String): UserEntity?

    /**
     * Say if the user is in the database
     *
     * @param firebaseId [String]: firebase id of the user
     *
     * @return [Int]: number of users with the id
     */
    @Query("SELECT COUNT(*) FROM users_table WHERE firebaseId = :firebaseId")
    suspend fun contains(firebaseId: String): Int


    //Inserts:
    /**
     * Insert a user or replace if already exists
     *
     * @param userEntity [UserEntity]: user to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(userEntity: UserEntity)

    //Updates:
    /**
     * Update the credits of a specific user
     *
     * @param firebaseId [String]: firebase id of the user
     * @param credits [Int]: new credits value to update
     *
     * @return [Int]: number of rows updated
     */
    @Query("UPDATE users_table SET credits = :credits WHERE firebaseId = :firebaseId")
    suspend fun updateCreditsById(firebaseId: String, credits: Int): Int

    /**
     * Update the group of a specific user
     *
     * @param firebaseId [String]: firebase id of the user
     * @param groupId [String]: new group id to update
     *
     * @return [Int]: number of rows updated
     */
    @Query("UPDATE users_table SET groupId = :groupId WHERE firebaseId = :firebaseId")
    fun updateGroupById(firebaseId: String, groupId: String): Int

    //Deletes:
    /**
     * Delete a user using the id
     *
     * @param firebaseId [String]: firebase id of the user
     *
     * @return [Int]: number of users deleted
     */
    @Query("DELETE FROM users_table WHERE firebaseId = :firebaseId")
    suspend fun deleteById(firebaseId: String): Int

}