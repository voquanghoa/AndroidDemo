package com.quanghoa.appdemo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.quanghoa.appdemo.asset.AssetFragment
import com.quanghoa.appdemo.composite.view.CompositeViewFragment
import com.quanghoa.appdemo.customui.CustomUiFragment
import com.quanghoa.appdemo.glideapp.GlideAppFragment
import com.quanghoa.appdemo.internalstorage.InternalStorageFragment
import com.quanghoa.appdemo.retrofit.RetrofitFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.menu_app_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val customUiFragment = CustomUiFragment()
    private val compositeViewFragment = CompositeViewFragment()
    private val glideAppFragment = GlideAppFragment()
    private val retrofitFragment = RetrofitFragment()
    private val assetFragment = AssetFragment()
    private val internalStorageFragment = InternalStorageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_app_main)

        setSupportActionBar(toolbar as Toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout as DrawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        (nav_view as NavigationView).setNavigationItemSelectedListener(this)

        showFragment(customUiFragment)
    }

    private fun showFragment(fragment: Fragment) {
        val fts = supportFragmentManager.beginTransaction()
        fts.replace(R.id.frameLayout, fragment)
        fts.commit()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when(p0.itemId){
            R.id.circle_chart -> showFragment(customUiFragment)
            R.id.composite_view -> showFragment(compositeViewFragment)
            R.id.glide_app -> showFragment(glideAppFragment)
            R.id.retrofit -> showFragment(retrofitFragment)
            R.id.asset -> showFragment(assetFragment)
            R.id.internal -> showFragment(internalStorageFragment)
        }

        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }
}