package dev.leonlatsch.avivchallange.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.leonlatsch.avivchallange.core.data.local.DATABASE_NAME
import dev.leonlatsch.avivchallange.core.data.local.ListingDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://gsl-apps-technical-test.dignp.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): RoomDatabase = Room.databaseBuilder(
        appContext,
        ListingDatabase::class.java, DATABASE_NAME
    ).build()
}