package com.codingwithmitch.openchat.auth.di

import com.codingwithmitch.openchat.auth.business.data.network.AuthNetworkDataSource
import com.codingwithmitch.openchat.auth.business.data.network.AuthNetworkDataSourceImpl
import com.codingwithmitch.openchat.auth.framework.datasource.network.AuthService
import com.codingwithmitch.openchat.auth.framework.datasource.network.AuthServiceImpl
import com.codingwithmitch.openchat.auth.framework.datasource.network.mappers.AuthNetworkMapper
import com.codingwithmitch.openchat.auth.framework.datasource.network.retrofit.AuthRetrofitService
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
    fun provideNetworkMapper(dateUtil: DateUtil): AuthNetworkMapper {
        return AuthNetworkMapper(dateUtil)
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
    fun provideAuthService(
        authRetrofit: AuthRetrofitService,
        mapper: AuthNetworkMapper,
    ): AuthService {
        return AuthServiceImpl(
            authRetrofitService = authRetrofit,
            mapper = mapper,
        )
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        authService: AuthService
    ): AuthNetworkDataSource{
        return AuthNetworkDataSourceImpl(authService)
    }

}










































