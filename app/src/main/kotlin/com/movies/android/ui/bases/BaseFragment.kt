package com.movies.android.ui.bases

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.movies.android.common.ui.extension.runIfNull
import com.movies.android.ui.customs.CustomPage
import javax.inject.Inject

abstract class BaseFragment : Fragment(), CustomPage.OnCustomPageErrorListener {

    @Inject
    internal lateinit var vmProvider: ViewModelProvider.Factory

    private lateinit var cp: CustomPage
    open lateinit var root: View
    open lateinit var lastRequest: () -> Unit

    open fun getData() {}
    open fun onReq() = lastRequest.invoke()

    abstract fun onLayout(): Int
    abstract fun onCreateView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getData()
        root = inflater.inflate(onLayout(), container, false)
        savedInstanceState.runIfNull {
            onCreateView()
        }
        return root
    }

    open fun setCustomPage(cp: CustomPage) {
        this.cp = cp
        cp.setPageErrorListener(this)
    }

    open fun onBackPressed() {
        (context as BaseActivity).onBackPressed()
    }

    override fun onCustomPageErrorClick() {
        onReq()
    }
}