package thornton.mj.com.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import thornton.mj.com.calculator.Operator.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var state: Operator = add(0)
    lateinit var tv_display : TextView
    lateinit var btn_0: Button
    lateinit var btn_1: Button
    lateinit var btn_2: Button
    lateinit var btn_3: Button
    lateinit var btn_4: Button
    lateinit var btn_5: Button
    lateinit var btn_6: Button
    lateinit var btn_7: Button
    lateinit var btn_8: Button
    lateinit var btn_9: Button
    lateinit var btn_add: Button
    lateinit var btn_sub: Button
    lateinit var btn_mult: Button
    lateinit var btn_div: Button
    lateinit var btn_clear: Button
    lateinit var btn_negative: Button
    lateinit var btn_percent: Button
    lateinit var btn_equals: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         tv_display = findViewById<TextView>(R.id.tv_screen) as TextView
         btn_0 = findViewById<Button>(R.id.btn_0) as Button
         btn_1 = findViewById<Button>(R.id.btn_1) as Button
         btn_2 = findViewById<Button>(R.id.btn_2) as Button
         btn_3 = findViewById<Button>(R.id.btn_3) as Button
         btn_4 = findViewById<Button>(R.id.btn_4) as Button
         btn_5 = findViewById<Button>(R.id.btn_5) as Button
         btn_6 = findViewById<Button>(R.id.btn_6) as Button
         btn_7 = findViewById<Button>(R.id.btn_7) as Button
         btn_8 = findViewById<Button>(R.id.btn_8) as Button
         btn_9 = findViewById<Button>(R.id.btn_9) as Button
         btn_add = findViewById<Button>(R.id.btn_plus) as Button
         btn_sub = findViewById<Button>(R.id.btn_minus) as Button
         btn_mult = findViewById<Button>(R.id.btn_mult) as Button
         btn_div = findViewById<Button>(R.id.btn_div) as Button
         btn_clear = findViewById<Button>(R.id.btn_clear) as Button
         btn_negative = findViewById<Button>(R.id.btn_negative) as Button
         btn_percent = findViewById<Button>(R.id.btn_percent) as Button
         btn_equals = findViewById<Button>(R.id.btn_equals) as Button

        btn_0.setOnClickListener(this)
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener(this)
        btn_4.setOnClickListener(this)
        btn_5.setOnClickListener(this)
        btn_6.setOnClickListener(this)
        btn_7.setOnClickListener(this)
        btn_8.setOnClickListener(this)
        btn_9.setOnClickListener(this)
        btn_add.setOnClickListener(this)
        btn_sub.setOnClickListener(this)
        btn_mult.setOnClickListener(this)
        btn_div.setOnClickListener(this)
        btn_clear.setOnClickListener(this)
        btn_negative.setOnClickListener(this)
        btn_percent.setOnClickListener(this)
        btn_equals.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        println("On Click: ")

        when(v?.id) {
            R.id.btn_0 -> operator("0")
            R.id.btn_1 -> operator("1")
            R.id.btn_2 -> operator("2")
            R.id.btn_3 -> operator("3")
            R.id.btn_4 -> operator("4")
            R.id.btn_5 -> operator("5")
            R.id.btn_6 -> operator("6")
            R.id.btn_7 -> operator("7")
            R.id.btn_8 -> operator("8")
            R.id.btn_9 -> operator("9")
            R.id.btn_clear -> operator("C")
            R.id.btn_plus -> operator("+")
            R.id.btn_minus -> operator("-")
            R.id.btn_mult -> operator("X")
            R.id.btn_div -> operator("/")
            R.id.btn_negative -> operator("+/-")
            R.id.btn_percent -> operator("%")
            R.id.btn_equals -> operator("=")
        }
    }

    fun onAction(fn : Operator){
        println("On Action: " + fn.x)
        state = fn
        tv_display.setText("")
    }

    val displayValue: Long
    get() = when(tv_display.text){
        "" -> 0
        else -> tv_display.text.toString().toLong()
    }

    private fun operator(x : String){
        println("Operator: " + x)

        if(Regex("[0-9]").matches(x)){
            tv_display.setText(tv_display.text.toString() + x)
        } else {
            when(x) {
                "+" -> onAction(add(displayValue))
                "-" -> onAction(minus(displayValue))
                "X" -> onAction(mult(displayValue))
                "/" -> onAction(div(displayValue))
                "%" -> {onAction(add(displayValue / 100)); operator("=")}
                "C" -> onAction(add(0))
                "+/-" -> {onAction(add(-1 * displayValue)); operator("=")}
                "=" -> tv_display.text = state.calculate(displayValue).toString()
            }
        }
    }


}
