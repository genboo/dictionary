package ru.spcm.apps.dictionary.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.annotation.Retention
import kotlin.annotation.Target
import kotlin.annotation.MustBeDocumented
import kotlin.reflect.KClass

/**
 * Аннотация для обознаяения класса ViewModel
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
