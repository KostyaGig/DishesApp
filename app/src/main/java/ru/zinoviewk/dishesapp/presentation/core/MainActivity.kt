package ru.zinoviewk.dishesapp.presentation.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.zinoviewk.dishesapp.R
import ru.zinoviewk.dishesapp.presentation.dishes.DishesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, DishesFragment())
                .commit()
        }
    }

}