package com.example.handybook_construction_android_app.homecontainer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.handybook_construction_android_app.R
import com.example.handybook_construction_android_app.category_fragment.Category_Fragment
import com.example.handybook_construction_android_app.eventsfragment.EventFragment
import com.example.handybook_construction_android_app.home_fragment.Home_Fragment
import com.example.handybook_construction_android_app.orders_fragment.OrderActivity
import com.example.handybook_construction_android_app.professional_fragment.ProfessionalFragment
import com.example.handybook_construction_android_app.proffesionaldetailsfragment.ProffessionalsDetailsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeContainer : AppCompatActivity() {

    private lateinit var frameBottomBar: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_container)


        drawerLayout=findViewById(R.id.drawerlayout)

        navigationView=findViewById(R.id.navigationView)
        frameBottomBar = findViewById(R.id.frameBottombar)

        navigationView.itemIconTintList=null

        replaceFragment(Home_Fragment())

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFagment) as NavHostFragment?

        val navController = navHostFragment!!.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("NavController", "Navigated to ${destination.label}")
        }


        NavigationUI.setupWithNavController(navigationView,navController)


        NavigationUI.setupWithNavController(frameBottomBar, navController)


        frameBottomBar.setOnNavigationItemSelectedListener {it ->
            when(it.itemId){

                R.id.home -> {
                    replaceFragment(Home_Fragment())
                    true
                }

                R.id.categotries -> {
                    replaceFragment(Category_Fragment())
                    true

                }
//
                R.id.proessionals -> {
                    replaceFragment(ProfessionalFragment())
                    true
                }
//
                R.id.events -> {
                    replaceFragment(EventFragment())
                    true
                }

                R.id.books -> {
                    replaceFragment(ProffessionalsDetailsFragment())
                    true
                }

                else -> {
                    TODO("Hello")
                }
            }

        }


        navigationView.setNavigationItemSelectedListener{item->
            when(item.itemId){
                R.id.nav_my_order->{
                    startActivity(Intent(this,OrderActivity::class.java))
                }
            }
            drawerLayout.closeDrawers()
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Check if the fragment is already in the back stack
        val existingFragment = fragmentManager.findFragmentByTag(fragment.javaClass.simpleName)

        if (existingFragment == null) {
            fragmentTransaction.replace(R.id.navHostFagment, fragment, fragment.javaClass.simpleName)
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
            fragmentTransaction.commit()
        } else {
            // If the fragment already exists, simply pop the back stack up to it
            fragmentManager.popBackStackImmediate(existingFragment.javaClass.simpleName, 0)
        }
    }


    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager

        if (fragmentManager.backStackEntryCount == 1) {
            showExitDialog()
        } else {
            if (fragmentManager.backStackEntryCount > 1) {
                fragmentManager.popBackStackImmediate(
                    fragmentManager.getBackStackEntryAt(1).id,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )

                var selectedFragment: Fragment? = null
                val fragments = supportFragmentManager.fragments
                for (fragment in fragments) {
                    if (fragment != null && fragment.isVisible) {
                        selectedFragment = fragment
                        break
                    }
                }

                selectedFragment?.let {
                    when (it) {
                        is Home_Fragment -> frameBottomBar.selectedItemId = R.id.home
                        is Category_Fragment -> frameBottomBar.selectedItemId = R.id.categotries
                        is ProfessionalFragment -> frameBottomBar.selectedItemId = R.id.proessionals
                        is EventFragment -> frameBottomBar.selectedItemId = R.id.events
                        is ProffessionalsDetailsFragment -> frameBottomBar.selectedItemId = R.id.books
                    }
                } ?: super.onBackPressed()
            } else {
                super.onBackPressed()
            }
        }
    }



    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ -> finish() })
            .setNegativeButton("No", null)
            .show()
    }

}