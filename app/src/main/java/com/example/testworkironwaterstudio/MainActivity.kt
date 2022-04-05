package com.example.testworkironwaterstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.example.testworkironwaterstudio.contract.ARG_STARTUP
import com.example.testworkironwaterstudio.contract.ActionUI
import com.example.testworkironwaterstudio.contract.HasCustomTitle
import com.example.testworkironwaterstudio.contract.Navigator
import com.example.testworkironwaterstudio.databinding.ActivityMainBinding
import com.example.testworkironwaterstudio.fragments.FilmsFragment
import java.io.Serializable

class MainActivity : AppCompatActivity(), Navigator, ActionUI {

    private val actions = mutableListOf<() -> Unit>()
    private lateinit var binding: ActivityMainBinding
    private val idContainer: Int = R.id.fragment_container

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
        if (savedInstanceState == null) toMoveListItem()
    }

    override fun onResume() {
        super.onResume()
        actions.forEach { it() }
        actions.clear()
    }

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        super.onDestroy()
    }

    private fun runWhenActive(action: () -> Unit) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            // activity is active -> just execute the action
            action()
        } else {
            // activity is not active -> add action to queue
            actions += action
        }
    }

    override fun updateUi() {
        val currentFragment = supportFragmentManager.findFragmentById(idContainer)!!
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        else{
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }
        if (currentFragment is HasCustomTitle) supportActionBar?.title = currentFragment.getTitle()
    }

    private fun launchFragment(fragment: Fragment, addToBackStack: Boolean = false, result: Any? = null, tag: String? = null){
            if (result != null){
                val bundle = Bundle()
                bundle.putSerializable(ARG_STARTUP, result as Serializable) // WARNING, THIS CODE MUST BE SERIALIZABLE
                fragment.arguments = bundle
            }
            val transaction = supportFragmentManager.beginTransaction()
            if (addToBackStack) transaction.addToBackStack(null)
            transaction
                .replace(idContainer, fragment, tag)
                .commit()
    }

    private fun toMoveListItem() = launchFragment(FilmsFragment())

    override fun toMoveInfoCompanyPage(result: Any?, addToBackStack: Boolean) {
        TODO("Not yet implemented")
    }

    override fun toMoveDetailsItemPage(result: Any?, addToBackStack: Boolean) {
        TODO("Not yet implemented")
    }

    override fun toBack(){
        supportFragmentManager.popBackStack()
    }
}