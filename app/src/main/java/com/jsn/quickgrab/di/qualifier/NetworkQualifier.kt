package com.jsn.quickgrab.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithoutLogging

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithLogging
