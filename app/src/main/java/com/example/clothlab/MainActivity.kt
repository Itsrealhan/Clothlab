package com.example.clothlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.clothlab.home.HomeFragment
import com.example.clothlab.notification.NotificationFragment
import com.example.clothlab.profile.ProfileFragment
import com.example.clothlab.recommended.RecommendedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

//  initiate every variable in this activity
    private lateinit var bottomNavigationView: BottomNavigationView

//  create life cycle for this activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      declare every variable
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

//      execute replaceFragment function with HomeFragment class as argument, HomeFragment layout will appear first at main activity
        replaceFragment(HomeFragment())

//      to start event and make it still running when click nav menu with id bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener {
//          execute when there is nav menu item selected
            when(it.itemId) {
//              execute replaceFragment function with kotlin class as argument according which nav menu item id is selected
                R.id.bottom_nav_home -> replaceFragment(HomeFragment())
                R.id.bottom_nav_recommended -> replaceFragment(RecommendedFragment())
                R.id.bottom_nav_notification -> replaceFragment(NotificationFragment())
                R.id.bottom_nav_profile -> replaceFragment(ProfileFragment())

//              execute when there is not menu item selected
                else -> {}
            }

//          make a true statement as there is nav menu item selected so that the program can be executed
            true
        }
    }

//  function for replace fragment
    private fun replaceFragment(fragment: Fragment) {
//      fragment manager for interacting with all fragment associated with this activity
        val fragmentManager = supportFragmentManager

//      make fragment transaction that allow fragment manager start a series of edit operation to the fragments
        val fragmentTransaction = fragmentManager.beginTransaction()

//      commit the transaction so that the fragment in layout can be replaced with fragment kotlin class selected
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).commit()
    }
}