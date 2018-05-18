package br.com.alertmehouse.alertmehouse

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.alertmehouse.alertmehouse.view.home.HomeFragment
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private var currentTag = ""

    companion object {
        val HOME = "Home"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic("all")

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment(), HOME)
        }

    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        if (tag != currentTag) {
            currentTag = tag
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment, tag)
                    .commit()
        }
    }
}
