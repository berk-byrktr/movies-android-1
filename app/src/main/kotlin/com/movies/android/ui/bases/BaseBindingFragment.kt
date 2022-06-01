package com.movies.android.ui.bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.movies.android.common.ui.extension.runIfNull
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType

abstract class BaseBindingFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseFragment() {

    open lateinit var binding: DB
    open lateinit var vm: VM

    private fun onBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, onLayout(), container, false)
        return binding.root
    }

    private fun getViewModelClass() =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getData()
        root = onBinding(inflater, container)
        AndroidSupportInjection.inject(this)
        savedInstanceState.runIfNull {
            vm = ViewModelProviders.of(this, vmProvider).get(getViewModelClass())
            onCreateView()
        }
        return root
    }
}