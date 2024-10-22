package com.example.checkbox_snackbar

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textInfoET: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    private lateinit var textInfo: TextView

    private lateinit var checkBoxPDD: CheckBox
    private lateinit var textPDD: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textInfoET = findViewById(R.id.textInfoET)
        btnSave = findViewById(R.id.btnSaveBTN)
        btnDelete = findViewById(R.id.btnDeleteBTN)
        textInfo = findViewById(R.id.resultTV)

        btnSave.setOnClickListener(this)
        btnDelete.setOnClickListener(this)

        checkBoxPDD = findViewById(R.id.checkboxPddCH)
        textPDD = findViewById(R.id.framePDD)
        textPDD.setVisibility(View.GONE)

        checkBoxPDD.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v) {
            btnSave -> {
                val text = textInfoET.text
                if (!text.isEmpty()) {
                    textInfo.text = text
                }
            }

            btnDelete -> {
                val lastText = textInfo.text.toString()
                textInfo.text = ""
                val snackbar = Snackbar.make(v,"Вы удалили данные!",Snackbar.LENGTH_LONG)
                    .setAction("Откатить действие?") {
                        textInfo.text = lastText
                        Snackbar.make(v,"Откатили",Snackbar.LENGTH_LONG).show()
                    }
                snackbar.show()
            }

            checkBoxPDD -> {
                if (checkBoxPDD.isChecked) {
                    textPDD.setVisibility(View.VISIBLE)
                    checkBoxPDD.text = getString(R.string.name_Cb)
                } else {
                    checkBoxPDD.text = getString(R.string.info_Cb)
                    textPDD.setVisibility(View.GONE)
                }

            }
        }
    }
}