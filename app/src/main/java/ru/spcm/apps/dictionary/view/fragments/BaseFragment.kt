package ru.spcm.apps.dictionary.view.fragments

import android.app.Activity
import android.arch.lifecycle.*
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.layout_app_bar_main.*
import javax.inject.Inject
import android.widget.LinearLayout
import ru.spcm.apps.dictionary.App
import ru.spcm.apps.dictionary.di.components.AppComponent
import ru.spcm.apps.dictionary.view.Navigator
import ru.spcm.apps.dictionary.view.activities.MainActivity
import ru.spcm.apps.dictionary.R


/**
 * Базовый фрагмент
 */
abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var component: AppComponent? = null

    val args: Bundle by lazy { arguments ?: Bundle() }

    val navigator: Navigator by lazy { (activity as MainActivity).navigator }

    /**
     * Получение ViewModel
     */
    protected fun <T : ViewModel> getViewModel(owner: Fragment, t: Class<T>): T {
        return ViewModelProviders.of(owner, viewModelFactory).get(t)
    }

    protected fun updateToolbar(title: String) {
        if (toolbar != null) {
            setToolbarSize()
            val activity = (activity as MainActivity)
            activity.setSupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener {
                if (activity.supportFragmentManager.backStackEntryCount > 0) {
                    activity.onBackPressed()
                }
            }
            if (activity.supportFragmentManager.backStackEntryCount == 0) {
                activity.supportActionBar?.setHomeAsUpIndicator(null)
                activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
                activity.supportActionBar?.setHomeButtonEnabled(false)
            } else {
                activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
                activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
                activity.supportActionBar?.setHomeButtonEnabled(true)
            }
            updateTitle(title)
        }
    }

    /**
     * Обновление тулбара
     */
    private fun setToolbarSize() {
        val statusBarHeight = getStatusBarHeight()
        toolbar.setPadding(0, statusBarHeight, 0, 0)
        val params = toolbar.layoutParams
        params.height += statusBarHeight
        toolbar.layoutParams = params

        if (toolbar.parent !is AppBarLayout && toolbar.parent is LinearLayout) {
            val parentParams = (toolbar.parent as LinearLayout).layoutParams
            parentParams.height += statusBarHeight
            (toolbar.parent as LinearLayout).layoutParams = parentParams
        }
    }

    /**
     * Высота статус бара
     */
    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * Инициализация фрагмента
     */
    protected fun initFragment() {
        if (component == null) {
            component = (activity?.application as App).appComponent
            component?.inject(this)
        }
    }

    /**
     * Обновление заголовка окна
     */
    private fun updateTitle(title: String) {
        toolbar?.title = title
    }

    /**
     * Скрытие виртуальной клавиатуры
     */
    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.applicationWindowToken, 0)
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    /**
     * Отображение SnackBar с текстом из ресурсов
     */
    @Suppress("unused")
    protected fun showSnack(text: Int, action: View.OnClickListener?) {
        val snackBar = Snackbar.make((activity as MainActivity).getView(), text, Snackbar.LENGTH_LONG)
        if (action != null) {
            snackBar.setAction(R.string.action_cancel, action)
        }
        snackBar.show()
    }

    /**
     * Отображение SnackBar с произвольным текстом
     */
    @Suppress("unused")
    protected fun showSnack(text: String, action: View.OnClickListener?) {
        val snackBar = Snackbar.make((activity as MainActivity).getView(), text, Snackbar.LENGTH_LONG)
        if (action != null) {
            snackBar.setAction(R.string.action_cancel, action)
        }
        snackBar.show()
    }

}