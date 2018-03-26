package br.com.alertmehouse.alertmehouse

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.alertmehouse.alertmehouse.view.home.HomeFragment
import com.remoteok.io.app.view.home.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentTag = ""
    companion object {
        val HOME = "Home"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment(), MainActivity.HOME)
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
