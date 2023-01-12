package com.bateman.bignerdranch.CriminalIntent

import android.content.Context
import androidx.room.Room
import com.bateman.bignerdranch.CriminalIntent.database.CrimeDatabase
import kotlinx.coroutines.flow.Flow
import java.util.*

private const val DATABASE_NAME = "crime-database"

//https://learn.microsoft.com/en-us/dotnet/architecture/microservices/microservice-ddd-cqrs-patterns/infrastructure-persistence-layer-design

class CrimeRepository private constructor(context: Context) {

    private val database: CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        .build()

   // suspend fun  getCrimes(): List<Crime> = database.crimeDAO().getCrimes()
    fun  getCrimes(): Flow<List<Crime>> = database.crimeDAO().getCrimes()
    suspend fun getCrime(id: UUID): Crime = database.crimeDAO().getCrime(id)

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE
                ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }


}