package com.example.testworkironwaterstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.testworkironwaterstudio.contract.Navigator
import com.example.testworkironwaterstudio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Navigator {

    private val actions = mutableListOf<() -> Unit>()
    private lateinit var binding: ActivityMainBinding
    private val idContainer: Int = R.id.fragment_container
    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    private fun updateUi() {
        TODO("Not yet implemented")
    }

    override fun toMoveInfoCompanyPage(result: Any?, addToBackStack: Boolean) {
        TODO("Not yet implemented")
    }

    override fun toMoveDetailsItemPage(result: Any?, addToBackStack: Boolean) {
        TODO("Not yet implemented")
    }

    override fun toBack() {
        TODO("Not yet implemented")
    }
}