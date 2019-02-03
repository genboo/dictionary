package ru.spcm.apps.dictionary.view.fragments

import android.os.Bundle
import android.view.*
import ru.spcm.apps.dictionary.R

/**
 * Главная
 */
class MainFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initFragment()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateToolbar(getString(R.string.screen_main))



    }



}
