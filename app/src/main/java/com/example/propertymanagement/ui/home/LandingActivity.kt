package com.example.propertymanagement.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.helpers.UserSessionManager
import com.example.propertymanagement.helpers.d
import com.example.propertymanagement.ui.todo.activities.TodoActivity
import com.example.propertymanagement.ui.tenants.TenantsActivity
import com.example.propertymanagement.ui.property.activities.AddPropertyActivity
import com.example.propertymanagement.ui.property.activities.PropertyActivity
import com.example.propertymanagement.ui.start.MainActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_landing.*

class LandingActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener {


    lateinit var sessionManager: UserSessionManager
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        sessionManager = UserSessionManager(this)

        init()
    }

    private fun init() {
        setupToolbar()
        setupDrawer()
        setupData()

        icon_property.setOnClickListener(this)
        button_tenants.setOnClickListener(this)
        button_todo.setOnClickListener(this)
        button_property_list.setOnClickListener(this)
    }

    private fun setupData() {
        User.TOKEN = sessionManager.getToken()
        User.KEY_USER_ID = sessionManager.getId()
        this.d("token " + User.TOKEN.toString())
        this.d("id " + User.KEY_USER_ID.toString())
    }

    private fun setupDrawer() {
        drawerLayout = drawer_layout
        navView = nav_view

        var headerView = navView.getHeaderView(0)
//        headerView.tv_header_name.text = user.firstName
//        headerView.tv_header_email.text = user.email // when you get the user info

        var toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar_general, 0, 0
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    private fun setupToolbar() {
        var toolbar = toolbar_general
        toolbar.title = "Property App"
        setSupportActionBar(toolbar)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_Logout -> dialogueLogout()
        }
        return true
    }

    private fun dialogueLogout() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("Yes") { p0, p1 ->
            logout()
        }
        builder.setNegativeButton("No") { dialogue, p1 ->
            dialogue?.dismiss()
        }
        val alertDialogue = builder.create()
        alertDialogue.show()
    }

    private fun logout() {
        val sessionManager = UserSessionManager(this)
        sessionManager.logout()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onClick(view: View) {
        when (view) {
            icon_property -> startActivity(Intent(this, AddPropertyActivity::class.java))
            button_tenants -> startActivity(Intent(this, TenantsActivity::class.java))
            button_todo -> startActivity(Intent(this, TodoActivity::class.java))
            button_property_list -> startActivity(Intent(this, PropertyActivity::class.java))
        }
    }
}