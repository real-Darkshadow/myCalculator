package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.mycalculator.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    var final: Double = 0.0
    var fint: Int = 0
    var op = ""
    var old = ""
    var isnewop = false
    var nums = ""
    var num = ""
    var numblck:Boolean=false
    fun button(view: View) {
        val buselected = view as Button
        if (isnewop == true) {
            nums = ""
            isnewop = false
        }
        if(numblck){
            binding.textView.text=""
            binding.textView2.text=""
            old=""
            fint=0
            final=0.0
        }
        num = binding.textView2.text.toString()
        when (buselected.id) {
            binding.b1.id -> {
                num += "1"
                nums += "1"
            }
            binding.b0.id -> {
                num += "0"
                nums += "0"
            }
            binding.b2.id -> {
                num += "2"
                nums += "2"
            }
            binding.b3.id -> {
                num += "3"
                nums += "3"
            }
            binding.b4.id -> {
                num += "4"
                nums += "4"
            }
            binding.b5.id -> {
                num += "5"
                nums += "5"
            }
            binding.b6.id -> {
                num += "6"
                nums += "6"
            }
            binding.b7.id -> {
                num += "7"
                nums += "7"
            }
            binding.b8.id -> {
                num += "8"
                nums += "8"
            }
            binding.b9.id -> {
                num += "9"
                nums += "9"
            }
            binding.dot.id -> {
                num += "."
                nums += "."
            }
            binding.pnm.id -> {
                if (num.contains("-") || nums.contains("-")) {
                    num = num.removePrefix("-")
                    nums = nums.removePrefix("-")
                } else {
                    num = "-" + num
                    nums = "-" + nums
                }
            }
            binding.rev.id -> {
                num = num.removeSuffix(num.last().toString())
                nums = nums.removeSuffix(nums.last().toString())

            }

        }
        numblck=false
        binding.textView2.text = num


    }


    fun action(view: View) {
        val buselected = view as Button
        isnewop = true
        if (fint!=0){
            binding.textView2.text=fint.toString()
            fint=0
        }
        if (final!=0.0){
            binding.textView2.text=final.toString()
            final=0.0
        }
        var num = binding.textView2.text.toString()
        if (old.isEmpty()) {
            old = binding.textView2.text.toString()
        }
        numblck=true
        if(numblck){
            nums=""
            numblck=false
        }
        binding.textView.text =""
        if (num.isNotEmpty()) {
            when (buselected.id) {
                binding.add.id -> {
                    if (op.isEmpty()) {
                        op = "+"
                        num += "+"
                        nums=""
                    } else {
                        if(num.last().toString()==op){
                            num=num.removeSuffix(op)
                            op="+"
                            num+="+"
                        }
                    }
                }
                binding.minus.id -> {
                    if (op.isEmpty()) {
                        op = "-"
                        num += "-"
                        nums=""
                    } else {
                        if(num.last().toString()==op){
                            num=num.removeSuffix(op)
                            op="-"
                            num+="-"
                        }

                    }
                }
                binding.divide.id -> {
                    if (op.isEmpty()) {
                        op = "/"
                        num += "/"
                        nums=""
                    } else {
                        if(num.last().toString()==op){
                            num=num.removeSuffix(op)
                            op="/"
                            num+="/"
                        }
                    }
                }
                binding.mod.id -> {
                    if (op.isEmpty()) {
                        op = "%"
                        num += "%"
                        nums=""
                    } else {
                        if(num.last().toString()==op){
                            num=num.removeSuffix(op)
                            op="%"
                            num+="%"
                        }
                    }
                }
                binding.mul.id -> {
                    if (op.isEmpty()) {
                        op = "*"
                        num += "x"
                        nums=""
                    } else {
                        if(num.last().toString()==op){
                            num=num.removeSuffix(op)
                            op="*"
                            num+="x"
                        }
                    }
                }
            }
            binding.textView2.text = num

        }
    }


    fun equal(view: View) {
        var counter = 0
        fint = 0
        final=0.0
        val but = view as Button
        if(old.isEmpty()||nums.isEmpty()){
            return
        }
        if (old.contains(".") || nums.contains(".")) {
            when (op) {
                "+" -> {
                    final = old.toDouble() + nums.toDouble()
                }
                "-" -> {
                    final = old.toDouble() - nums.toDouble()
                }
                "*" -> {
                    final = old.toDouble() * nums.toDouble()
                }
                "/" -> {
                    final = old.toDouble() / nums.toDouble()
                }
                "%" -> {
                    final = old.toDouble() % nums.toDouble()
                }
            }
            counter++
        } else {
            when (op) {
                "+" -> {
                    fint = old.toInt() + nums.toInt()
                }
                "-" -> {
                    fint = old.toInt() - nums.toInt()
                }
                "*" -> {
                    fint = old.toInt() * nums.toInt()
                }
                "/" -> {
                    fint = old.toInt() / nums.toInt()
                }
                "%" -> {
                    fint = old.toInt() % nums.toInt()
                }
            }

        }
        if (counter > 0) {
            binding.textView.text = final.toString()
            old=""
            op=""
            nums=""
            numblck=true
        } else {
            binding.textView.text = fint.toString()
            old=""
            op=""
            nums=""
            numblck=true
        }

    }

    fun ac(view: View) {
        nums = ""
        old = ""
        num = ""
        fint = 0
        isnewop = false
        numblck=false
        op = ""
        final = 0.0
        binding.textView.text = nums
        binding.textView2.text = num
    }
}