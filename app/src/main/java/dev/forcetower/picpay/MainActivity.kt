package dev.forcetower.picpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.forcetower.picpay.view.contacts.ContactsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,
                ContactsFragment(), "contacts")
            .commit()
    }
}