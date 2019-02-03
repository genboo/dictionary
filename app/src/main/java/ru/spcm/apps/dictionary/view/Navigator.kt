package ru.spcm.apps.dictionary.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.*
import ru.spcm.apps.dictionary.R
import ru.spcm.apps.dictionary.view.activities.MainActivity
import ru.spcm.apps.dictionary.view.fragments.*

/**
 * Навигатор по приложению
 * Created by evgeniy on 03.09.2018.
 */
class Navigator(private val activity: MainActivity,
                fragmentManager: FragmentManager,
                containerId: Int) : SupportFragmentNavigator(fragmentManager, containerId) {

    /**
     * Создание фразментов
     */
    override fun createFragment(screenKey: String, data: Any?): Fragment {


        return MainFragment()
    }

    override fun showSystemMessage(message: String) {
        //not used
    }

    override fun exit() {
        activity.finish()
    }

    override fun setupFragmentTransactionAnimation(command: Command,
                                                   currentFragment: Fragment?,
                                                   nextFragment: Fragment?,
                                                   fragmentTransaction: FragmentTransaction) {

        if (command is Replace) {
            fragmentTransaction.setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out,
                    android.R.anim.fade_in,
                    android.R.anim.fade_out)
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right_2)
        }
    }

    /**
     * Переход назад
     */
    fun comeBack() {
        applyCommand(Back())
    }

    /**
     * Набор методов для перехода к различным экранам
     */
    fun toNews() {
        applyCommands(arrayOf(BackTo(null), Replace(SCREEN_NEWS, null)))
    }

    fun toCategories() {
        applyCommands(arrayOf(BackTo(null), Replace(SCREEN_CATEGORIES, null)))
    }

    fun toNewsItem(id: Int) {
        applyCommands(arrayOf(Forward(SCREEN_NEWS_ITEM, id)))
    }

    fun toNewsByCategory(category: String, title: String) {
        applyCommands(arrayOf(Forward(SCREEN_NEWS_BY_CATEGORY, arrayOf(category, title))))
    }

    companion object {
        const val SCREEN_NEWS = "screen_news"
        const val SCREEN_CATEGORIES = "screen_categories"
        const val SCREEN_NEWS_ITEM = "screen_news_item"
        const val SCREEN_NEWS_BY_CATEGORY = "screen_news_by_category"
    }

}