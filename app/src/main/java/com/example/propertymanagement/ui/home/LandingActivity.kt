package com.example.propertymanagement.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.helpers.UserSessionManager
import com.example.propertymanagement.helpers.d
import com.example.propertymanagement.ui.*
import com.example.propertymanagement.ui.property.activities.PropertyActivity
import com.example.propertymanagement.ui.start.MainActivity
import com.example.propertymanagement.ui.tenants.TenantsOptionActivity
import com.example.propertymanagement.ui.todo.activities.TodoActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_landing.*
import kotlinx.android.synthetic.main.nav_header.view.*

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
        button_notifications.setOnClickListener(this)
        button_transactions.setOnClickListener(this)
        button_rent.setOnClickListener(this)
        button_trips.setOnClickListener(this)
        button_documents.setOnClickListener(this)
        button_vendors.setOnClickListener(this)

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

        val user = sessionManager.getUser()

        var headerView = navView.getHeaderView(0)
        headerView.tv_header_name.text = user?.name.toString()
        headerView.tv_header_email.text = user?.email.toString() // when you get the user info

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
            icon_property -> startActivity(Intent(this, PropertyActivity::class.java))
            button_tenants -> startActivity(Intent(this, TenantsOptionActivity::class.java))
            button_todo -> startActivity(Intent(this, TodoActivity::class.java))
            button_notifications -> startActivity(Intent(this, NotificationsActivity::class.java))
            button_transactions -> startActivity(Intent(this, TransactionsActivity::class.java))
            button_rent -> startActivity(Intent(this, CollectRentActivity::class.java))
            button_trips -> startActivity(Intent(this, TripsActivity::class.java))
            button_documents -> startActivity(Intent(this, DocumentsActivity::class.java))
            button_vendors -> startActivity(Intent(this, VendorsActivity::class.java))
        }
    }
}