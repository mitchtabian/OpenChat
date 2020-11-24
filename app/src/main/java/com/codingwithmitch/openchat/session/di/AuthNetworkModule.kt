package com.codingwithmitch.openchat.session.di

import com.codingwithmitch.openchat.session.business.data.network.AuthNetworkDataSource
import com.codingwithmitch.openchat.session.business.data.network.AuthNetworkDataSourceImpl
import com.codingwithmitch.openchat.session.framework.datasource.network.SessionService
import com.codingwithmitch.openchat.session.framework.datasource.network.SessionServiceImpl
import com.codingwithmitch.openchat.session.framework.datasource.network.mappers.SessionNetworkMapper
import com.codingwithmitch.openchat.session.framework.datasource.network.retrofit.AuthRetrofitService
import com.codingwithmitch.openchat.common.business.domain.util.DateUtil
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AuthNetworkModule {


    @Singleton
    @Provides
    fun provideNetworkMapper(dateUtil: DateUtil): SessionNetworkMapper {
        return SessionNetworkMapper(dateUtil)
    }

    @Singleton
    @Provides
    @Named("auth_retrofit")
    fun provideAuthRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://open-chat.xyz/api/account/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    }

    @Singleton
    @Provides
    fun provideAuthRetrofitService(
            @Named("auth_retrofit") retrofit: Retrofit.Builder
    ): AuthRetrofitService {
        return retrofit
            .build()
            .create(AuthRetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideSessionService(
        authRetrofit: AuthRetrofitService,
        mapper: SessionNetworkMapper,
    ): SessionService {
        return SessionServiceImpl(
            authRetrofitService = authRetrofit,
            mapper = mapper,
        )
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        sessionService: SessionService
    ): AuthNetworkDataSource{
        return AuthNetworkDataSourceImpl(sessionService)
    }

}










































