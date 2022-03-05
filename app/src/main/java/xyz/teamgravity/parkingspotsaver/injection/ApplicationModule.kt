package xyz.teamgravity.parkingspotsaver.injection

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.teamgravity.parkingspotsaver.data.local.ParkingSpotConst
import xyz.teamgravity.parkingspotsaver.data.local.ParkingSpotDao
import xyz.teamgravity.parkingspotsaver.data.local.ParkingSpotDatabase
import xyz.teamgravity.parkingspotsaver.data.repository.ParkingSpotRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideParkingSpotDatabase(app: Application): ParkingSpotDatabase =
        Room.databaseBuilder(app, ParkingSpotDatabase::class.java, ParkingSpotConst.NAME)
            .addMigrations()
            .build()

    @Provides
    @Singleton
    fun provideParkingSpotDao(db: ParkingSpotDatabase): ParkingSpotDao = db.parkingSpotDao()

    @Provides
    @Singleton
    fun provideParkingSpotRepository(dao: ParkingSpotDao): ParkingSpotRepository = ParkingSpotRepository(dao)
}