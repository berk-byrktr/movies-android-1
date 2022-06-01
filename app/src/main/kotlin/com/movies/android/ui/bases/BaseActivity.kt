package com.movies.android.ui.bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.movies.android.R

abstract class BaseActivity : AppCompatActivity() {

    open fun onCreate() {}
    open fun onLayout() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (onLayout() != 0) setContentView(onLayout())
        onCreate()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    open fun openFragment(fragment: BaseFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    open fun addFragment(
        fragment: BaseFragment,
        manager: FragmentManager = supportFragmentManager,
        container: Int = R.id.container
    ) {
        manager.beginTransaction()
            .addToBackStack(null)
            .add(container, fragment)
            .commit()
    }
}