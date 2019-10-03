package com.quanghoa.appdemo

import android.content.Context
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
import com.quanghoa.appdemo.retrofit.RetrofitFragment
import com.quanghoa.appdemo.storage.external.ExternalStorageFragment
import com.quanghoa.appdemo.storage.internal.InternalStorageFragment
import com.quanghoa.appdemo.storage.sharedpreferences.SharedPreferencesFragment
import com.quanghoa.appdemo.support.multilanguages.LocaleHelper
import com.quanghoa.appdemo.support.multilanguages.MultiLanguagesFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.menu_app_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val customUiFragment = CustomUiFragment()
    private val compositeViewFragment = CompositeViewFragment()
    private val glideAppFragment = GlideAppFragment()
    private val retrofitFragment = RetrofitFragment()
    private val assetFragment = AssetFragment()
    private val internalStorageFragment = InternalStorageFragment()
    private val sharedPreferencesFragment = SharedPreferencesFragment()
    private val externalStorageFragment = ExternalStorageFragment()
    private val multiLanguagesFragment = MultiLanguagesFragment()

    private var lastFragmentId = R.id.circle_chart

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

        displayFragment(savedInstanceState?.getInt("fragment_id", R.id.circle_chart) ?: lastFragmentId )
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    private fun showFragment(fragment: Fragment) {
        val fts = supportFragmentManager.beginTransaction()
        fts.replace(R.id.frameLayout, fragment)
        fts.commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        displayFragment(menuItem.itemId)

        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("fragment_id", lastFragmentId)
    }

    private fun displayFragment(itemId: Int){
        when(itemId){
            R.id.circle_chart -> showFragment(customUiFragment)
            R.id.composite_view -> showFragment(compositeViewFragment)
            R.id.glide_app -> showFragment(glideAppFragment)
            R.id.retrofit -> showFragment(retrofitFragment)
            R.id.asset -> showFragment(assetFragment)
            R.id.internal -> showFragment(internalStorageFragment)
            R.id.shared_preferences -> showFragment(sharedPreferencesFragment)
            R.id.external_storage -> showFragment(externalStorageFragment)
            R.id.multi_languages -> showFragment(multiLanguagesFragment)
        }
        lastFragmentId = itemId
    }
}