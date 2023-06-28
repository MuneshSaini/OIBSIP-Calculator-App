package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvinput:TextView?=null
    var lastnumeric : Boolean = false
    var lastdot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvinput = findViewById(R.id.tvinput)
    }

    fun onDigit(view:View){
        tvinput ?.append((view as Button).text)
        lastnumeric = true
        lastdot= false
    }
    fun onClear(view: View){
        tvinput?.text=""
    }
    fun decimalpoint(view: View){
        if(lastnumeric && !lastdot){
            tvinput?.append(".")
            lastnumeric = false
            lastdot = true
        }
    }

    fun onequle(view: View){
        if(lastnumeric) {
            var tvValue = tvinput?.text.toString()
            var prefix =""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue=tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    var splitvalue = tvValue.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterresult((one.toDouble() - two.toDouble()).toString())
                }else if (tvValue.contains("+")){
                    var splitvalue = tvValue.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterresult((one.toDouble() + two.toDouble()).toString())
                }else if (tvValue.contains("*")){
                    var splitvalue = tvValue.split("*")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterresult((one.toDouble() * two.toDouble()).toString())
                }else if (tvValue.contains("/")){
                    var splitvalue = tvValue.split("/")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezeroafterresult((one.toDouble() / two.toDouble()).toString())
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removezeroafterresult(result:String):String{
        var value=result
        if(result.contains(".0"))
            value = result.substring(0,result.length -2)
        return value

    }

    fun onoperator(view: View){
        tvinput?.text?.let{
        if(lastnumeric && !isoperatoradded(it.toString())){
            tvinput?.append((view as Button).text)
            lastnumeric = false
            lastdot = false
        }
    }}
    private fun isoperatoradded(value: String) : Boolean{
      return if (value.startsWith("-")){
          false
      }else{
          value.contains("/")
          value.contains("*")
          value.contains("+")
          value.contains("-")
      }
    }


}