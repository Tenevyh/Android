package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextSwitcher
import androidx.fragment.app.Fragment
import android.text.format.DateFormat
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "CrimeFragment"
private const val ARG_CRIME_ID = "crime_id"

class CrimeFragment: Fragment() {

    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox
    private lateinit var format1: SimpleDateFormat

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        crime = Crime()
        val crimeId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        Log.d(TAG, "args bundle crime ID: $crimeId")
        //Загрузка преступленя из базы данных

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                             savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)
        titleField = view.findViewById(R.id.crime_title) as EditText
        dateButton = view.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        dateButton.apply{
            text = crime.date.toString()
            isEnabled = false
        }
        return view
    }

    override fun onStart(){
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int,
                                           after: Int) {

            }

            override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int,
                                       count: Int){
                crime.title = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?){

            }
        }
        titleField.addTextChangedListener(titleWatcher)

        solvedCheckBox.apply{
            setOnCheckedChangeListener{_, isCheked -> crime.isSolved = isCheked}
        }
    }

    companion object {

        fun newInstance(crimeID: UUID): CrimeFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, crimeID)
            }
            return CrimeFragment().apply {
                arguments = args
            }
        }
    }
}