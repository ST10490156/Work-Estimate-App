package vcmsa.ci.workestimateapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var taskRadioGroup: RadioGroup
    private lateinit var woodCheck: CheckBox
    private lateinit var nailsCheck: CheckBox
    private lateinit var hingesCheck: CheckBox
    private lateinit var hoursInput: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        taskRadioGroup = findViewById(R.id.taskRadioGroup)
        woodCheck = findViewById(R.id.woodCheck)
        nailsCheck = findViewById(R.id.nailsCheck)
        hingesCheck = findViewById(R.id.hingesCheck)
        hoursInput = findViewById(R.id.hoursInput)
        calculateButton = findViewById(R.id.calculateButton)
        resultText = findViewById(R.id.resultText)

        calculateButton.setOnClickListener {
            calculateTotal()

        }
        fun calculateTotal(){
            var totalCost = 0

            if (woodCheck.isChecked) totalCost += 500
            if (nailsCheck.isChecked) totalCost += 20
            if (hingesCheck.isChecked) totalCost += 30

            val hoursText = hoursInput.text.toString()
            val hours = hoursText.toIntOrNull()
            if (hours == null || hours <= 0) {
                Toast.makeText(this, "Please enter a valid number of hours", Toast.LENGTH_SHORT)
                    .show()
                return
            }

            // Add hourly rate based on task
            val taskId = taskRadioGroup.checkedRadioButtonId
            if (taskId == -1) {
                Toast.makeText(this, "Please select a task type", Toast.LENGTH_SHORT).show()
                return
            }

            val hourlyRate = when (taskId) {
                R.id.installDoor -> 200
                R.id.buildShelf -> 150
                R.id.assembleFurniture -> 100
                else -> 0
            }

            totalCost += (hours * hourlyRate)

            resultText.text = "Total Cost: R$totalCost"
        }
    }

    private fun calculateTotal() {
        TODO("Not yet implemented")
    }
}

