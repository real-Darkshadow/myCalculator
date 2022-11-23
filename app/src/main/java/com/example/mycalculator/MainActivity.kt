package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private var final: Double = 0.0             //Final float Value
    private var fint: Int = 0                       //Final int Value
    private var op = ""                         // Current Operator
    private var old = ""                            // Number before user entered 2nd operator
    private var isnewop = false                 //is there any new operator??
    private var nums = ""                           //NUMBER which is entered by user after 2nd operator
    private var num = ""                        // Number Shown in textView
    private var numblck:Boolean=false               //UNBlocks Number buttons // Used so the user can't edit number shown in textview after he clicks equal

    //Checking Which Number Has been Clicked By the user

    fun button(view: View) {                    // Passing view (Which is Button)
        val buselected = view as Button             //Variable Defining View As a button For future usage
        if (isnewop) {                         // if there is new operator than the nums will be 0 to get new Values by user
            nums = ""
            isnewop = false
        }
        if(numblck){                                // Checks if numblck is true and resets  both ui texts and (old values by user, and final answers)
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
            binding.pnm.id -> {                         // If user press =/- button thn changes num according to it
                if (num.contains("-") || nums.contains("-")) {
                    num = num.removePrefix("-")
                    nums = nums.removePrefix("-")
                } else {
                    num = "-$num"
                    nums = "-$nums"
                }
            }
            binding.rev.id -> {                             // Remove last value of num when pressed back button
                num = num.removeSuffix(num.last().toString())
                nums = nums.removeSuffix(nums.last().toString())

            }

        }
        numblck=false                                     // changing numblock back to false as we now want user to enter more numbers not only a single number
        binding.textView2.text = num                          //Setting ui text to num


    }

            // Checking Which Operator has User pressed

    fun action(view: View) {
        val buselected = view as Button
        isnewop = true                                 //enable us to enter new number as it will reset the nums value shown in above function

        // Checks if the user has Clicked new operator just after getting a final answer or after he has clicked equal
        // Then we set num or text of ui to the final answer
        if (fint!=0){
            binding.textView2.text=fint.toString()
            fint=0
        }
        if (final!=0.0){
            binding.textView2.text=final.toString()
            final=0.0
        }
        //

        var num = binding.textView2.text.toString()                 //we set num now by the help of ui text this solve both without clicked equal and after we clicked equal
        if (old.isEmpty()) {
            old = binding.textView2.text.toString()                     // Time to take old value which is present before new operator
        }
        numblck=true
        if(numblck){                             //Resets nums value to 0 as now we will take new values to add into the old
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
                        if(num.last().toString()==op){                         //if the user has clicked different operator after clicking an operator thn we delete the previous
                            num=num.removeSuffix(op)                             //operator from num and update it with new operator viceversa with op
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


     //Equal buttons give us the final answer


    //simply check if there is float present in old or nums thn give us float value else integer values

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
        if (counter > 0) {                            //Checks if float or int to show as answer and resets variables(Mentioned Below), sets numblck true to prevent changes in num
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

    fun ac(view: View) {                //Resets everything to its Starting Value ac Button
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