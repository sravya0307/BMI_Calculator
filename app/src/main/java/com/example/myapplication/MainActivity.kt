package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity2)
        val getWeight=findViewById<EditText>(R.id.edt1)
        val getHeight=findViewById<EditText>(R.id.edt3)
        val calcButton=findViewById<Button>(R.id.displaybtn)
        calcButton.setOnClickListener {
            val weight=getWeight.text.toString()
            val height=getHeight.text.toString()
            if(checkbmi(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayfun(bmi2digits)
            }
        }

    }
    private fun checkbmi(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is not entered",Toast.LENGTH_LONG).show()
                false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is not entered",Toast.LENGTH_LONG).show()
                false
            }
            else->{
                true
            }
        }

    }
    private fun displayfun(bmi:Float){
        val disresult=findViewById<TextView>(R.id.restv)
        disresult.text=bmi.toString()
        val infomsg=findViewById<TextView>(R.id.tvdisplay)
        val displayrange=findViewById<TextView>(R.id.tvrange)
        displayrange.text="(Normal range is 18.5-24.9)"
        var resulttxt=""
        var color=0
        when{
            bmi<18.50->{
                resulttxt="Under Weight"
                color=R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resulttxt="Healthy"
                color=R.color.green
            }
            bmi in 25.00..29.99->{
                resulttxt="OverWeight"
                color=R.color.over_weight
            }
            bmi>29.99->{
                resulttxt="Obese"
                color=R.color.obese
            }
        }
        infomsg.setTextColor(ContextCompat.getColor(this,color))
        infomsg.text=resulttxt
    }

}