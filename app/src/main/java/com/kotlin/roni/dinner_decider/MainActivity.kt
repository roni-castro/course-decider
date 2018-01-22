package com.kotlin.roni.dinner_decider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val foodPlaces = arrayListOf("Chinese", "Mc Donald's", "Bobs", "Subway")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.app_name)

        addFoodBtn.setOnClickListener {
            val newFoodPlace = newFoodEditText.text.toString()
            if(isValidFoodPlace(newFoodPlace)){
                addNewFoodPlace(newFoodPlace)
                showToast(newFoodPlace + " was successfully added to the list")
                newFoodEditText.text.clear()
            }
        }

        decideBtn.setOnClickListener{
            if(!foodPlaces.isEmpty()){
                decide()
            } else {
                showToast("Add a new food before deciding")
            }
        }
    }

    private fun decide(){
        val decidedFoodPlace = getRandomFood()
        foodPlaceDecided.text = decidedFoodPlace
    }

    private fun getRandomFood() :String{
        return when {
            foodPlaces.size > 0 -> {
                val random = Random()
                val index = random.nextInt(foodPlaces.size)
                foodPlaces[index]
            }
            else -> ""
        }
    }

    private fun isValidFoodPlace(newFoodPlace: String):Boolean{
        return when {
            newFoodPlace.isEmpty() -> {
                showToast("Food place cannot be empty")
                false
            }
            foodPlaces.contains(newFoodPlace) -> {
                showToast(newFoodPlace + " was already added to the array")
                false
            }
            else -> true
        }
    }

    private fun addNewFoodPlace(place: String){
        foodPlaces.add(place)
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
