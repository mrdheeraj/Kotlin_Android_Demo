package com.mrdheerajpurohit.kotlinproject
/**
 * Dheeraj Purohit Kotlin Demo for Android.
 */

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    internal var editTextFirstName: EditText? = null
    internal var editTextLastName: EditText? = null
    internal var editTextAddress: EditText? = null
    internal var editTextMobileNo: EditText? = null
    internal var editTextCity: EditText? = null
    internal var radioSexGroup: RadioGroup? = null
    internal var radioSexButton: RadioButton? = null
    internal var radioButtonMale: RadioButton? = null
    internal var radioButtonFemale: RadioButton? = null
    internal var buttonSave: Button? = null
    internal var textViewFirstName: TextView? = null
    internal var textViewLastName: TextView? = null
    internal var textViewMobileNo: TextView? = null
    internal var textViewAddress: TextView? = null
    internal var textViewGender: TextView? = null
    internal var textViewCity: TextView? = null

    internal var titleTypeFace: Typeface? = null

    internal var mobileNo: String? = null
    internal var firstName: String? = null
    internal var lastName: String? = null
    internal var address: String? = null
    internal var gender: String? = null
    internal var city: String? = null

    val PREFS_FILENAME = "com.omegasolutions.kotlinproject.prefs"
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        initializeView();
        setTypeface();
        this.setlistenerOnButton()
    }

    private fun setlistenerOnButton() {
        (buttonSave as Button).setOnClickListener(this)
    }

    override fun onClick(p: View) {
        when (p.id) {

            R.id.button_save -> {

                if(isValid) {

                    var count = prefs!!.getInt("count",0);

                    count++

                    val editor = prefs!!.edit()
                    editor.putInt("count", count)
                    editor.apply()

                    val mIntent = Intent(this, DisplayActivity::class.java)
                    mIntent.putExtra("firstName", firstName)
                    mIntent.putExtra("lastName",lastName)
                    startActivity(mIntent)
                }
            }
        }
    }

    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun initializeView() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        textViewFirstName = findViewById(R.id.textFN) as TextView
        textViewLastName = findViewById(R.id.textLN) as TextView
        textViewAddress = findViewById(R.id.textAddress) as TextView
        textViewGender = findViewById(R.id.text_view_gender) as TextView
        textViewMobileNo = findViewById(R.id.textMoblieNo) as TextView
        textViewCity = findViewById(R.id.textCity) as TextView
        editTextFirstName = findViewById(R.id.editFN) as EditText
        editTextLastName = findViewById(R.id.editLN) as EditText
        editTextAddress = findViewById(R.id.editAddress) as EditText
        editTextMobileNo = findViewById(R.id.editMobileNo) as EditText
        editTextCity = findViewById(R.id.editCity) as EditText
        radioSexGroup = findViewById(R.id.radio_group_sex) as RadioGroup
        radioButtonMale = findViewById(R.id.radio_button_male) as RadioButton
        radioButtonFemale = findViewById(R.id.radio_button_female) as RadioButton
        buttonSave = findViewById(R.id.button_save) as Button
    }

    fun setTypeface() {
        titleTypeFace = Typeface.createFromAsset(assets, "SourceSansPro-Regular.ttf")
        textViewFirstName?.typeface = titleTypeFace
        textViewLastName?.typeface = titleTypeFace
        textViewAddress?.typeface = titleTypeFace
        textViewGender?.typeface = titleTypeFace
        textViewMobileNo?.typeface = titleTypeFace
        textViewCity?.typeface = titleTypeFace
        editTextFirstName?.typeface = titleTypeFace
        editTextLastName?.typeface = titleTypeFace
        editTextAddress?.typeface = titleTypeFace
        editTextMobileNo?.typeface = titleTypeFace
        editTextCity?.typeface = titleTypeFace
        textViewGender?.typeface = titleTypeFace
        radioButtonMale?.typeface = titleTypeFace
        radioButtonFemale?.typeface = titleTypeFace
        buttonSave?.typeface = titleTypeFace
    }

    val isValid: Boolean
        get() {

            firstName = editTextFirstName?.text.toString().trim { it <= ' ' }
            lastName = editTextLastName?.text.toString().trim { it <= ' ' }
            address = editTextAddress?.text.toString().trim { it <= ' ' }
            city = editTextCity?.text.toString().trim { it <= ' ' }
            mobileNo = editTextMobileNo?.text.toString().trim { it <= ' ' }

            if (firstName?.length!! <= 0) {
                toast("Please Enter First Name")
                return false
            }

            if (lastName?.length!! <= 0) {
                toast("Please Enter Last Name")
                return false
            }

            if (radioSexGroup?.checkedRadioButtonId == -1) {
                toast("Please Select Gender")
                return false
            } else {
                radioSexButton = findViewById(radioSexGroup!!.checkedRadioButtonId) as RadioButton
                gender = radioSexButton?.text.toString().trim { it <= ' ' }
            }

            if (mobileNo?.length!! != 10) {
                toast("Please Enter(10 digit) Mobile No.")
                return false
            }

            if (address?.length!! <= 0) {
                toast("Please Enter Address")
                return false
            }
            if (city?.length!! <= 0) {
                toast("Please Enter City")
                return false
            }

            return true
        }






}
