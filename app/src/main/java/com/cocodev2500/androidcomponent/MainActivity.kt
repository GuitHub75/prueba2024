package com.cocodev2500.androidcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.cocodev2500.androidcomponent.databinding.ActivityMainBinding
import com.cocodev2500.androidcomponent.ui.fragments.home.IndexFragment
import com.cocodev2500.androidcomponent.ui.fragments.map.mapFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

     // Creamos una variable para el DrawerLayout que hemos creado en el layout. esto nos permie abrir y cerrar el menu lateral
    private lateinit var draweLayout : DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos el Navigation Drawer
        initNavigationDrawer()
        loadIndexFragment(savedInstanceState)

    }


    private fun initNavigationDrawer() {
        draweLayout = binding.drawerLayout
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        // Creamos una variable para el NavigationView que hemos creado en el layout. esto nos permite escuchar los eventos de seleccion de items
        val navigationview = binding.navView
        navigationview.setNavigationItemSelectedListener(this)
        navigationview.bringToFront()

        // Creamos un objeto de la clase ActionBarDrawerToggle que nos permite abrir y cerrar el menu lateral
        val toggle = ActionBarDrawerToggle(this, draweLayout, toolbar, R.string.open_nav, R.string.close_nav)
        // Le decimos al toggle que muestre el icono de hamburguesa
        toggle.isDrawerIndicatorEnabled = true
        // Le decimos al draweLayout que escuche los eventos del toggle
        draweLayout.addDrawerListener(toggle)
        // Le decimos al toggle que sincronice el estado del draweLayout
        toggle.syncState()
        // Aca le decimos al NavigationView que escuche los eventos de seleccion de items
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.Home -> {
               loadIndexFragment()
           }
           R.id.mapOption -> {
                loadMapFragment()
           }
       }
        draweLayout.closeDrawer(binding.navView)
        return true
    }


    //Components of the Navigation Drawer ----------------------------------------------------------------------------------
    private fun loadIndexFragment(savedInstanceState : Bundle? = null) {
        if (savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, IndexFragment())
            }
        }
    }
    private fun loadMapFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, mapFragment())
        }
    }
    //End Components of the Navigation Drawer ----------------------------------------------------------------------------------

}

