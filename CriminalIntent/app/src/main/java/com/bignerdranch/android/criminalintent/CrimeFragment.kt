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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "CrimeFragment"
private const val ARG_CRIME_ID = "crime_id"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0
private const val DIALOG_TIME = "DialogTime"
private const val REQUEST_TIME = 1

class CrimeFragment: Fragment(), DatePickerFragment.Callbacks, TimePickerFragment.Callbacks {

    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button
    private lateinit var solvedCheckBox: CheckBox
    private lateinit var format1: SimpleDateFormat
    private lateinit var format2: SimpleDateFormat
    private val crimeDetailViewModel : CrimeDetailViewModel by lazy {
        ViewModelProvider(this).get(CrimeDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        crime = Crime()
        val crimeId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        crimeDetailViewModel.loadCrime(crimeId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                             savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)
        titleField = view.findViewById(R.id.crime_title) as EditText
        timeButton = view.findViewById(R.id.crime_time)
        dateButton = view.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeDetailViewModel.crimeLiveData.observe(
            viewLifecycleOwner,
            Observer{
                crime ->
                crime?.let {
                    this.crime = crime
                    updateUI()
                }
            })
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

        dateButton.setOnClickListener {
                DatePickerFragment.newInstance(crime.date).apply {
                    setTargetFragment(this@CrimeFragment, REQUEST_DATE)
                    show(this@CrimeFragment.parentFragmentManager, DIALOG_DATE)
                }
        }

        timeButton.setOnClickListener {
            TimePickerFragment.newInstance(crime.date).apply {
                setTargetFragment(this@CrimeFragment, REQUEST_TIME)
                show(this@CrimeFragment.parentFragmentManager, DIALOG_TIME)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        crimeDetailViewModel.saveCrime(crime)
    }

    private fun updateUI(){
        titleField.setText(crime.title)
        format1 = SimpleDateFormat("dd.MM.y")
        dateButton.text = format1.format(crime.date)
        format2 = SimpleDateFormat("HH:mm")
        timeButton.text = format2.format(crime.date)
        solvedCheckBox.apply{
            isChecked = crime.isSolved
            jumpDrawablesToCurrentState()
        }
    }

    override fun onDateSelected(date: Date){
        crime.date = date
        updateUI()
    }

    override fun onTimeSelected(date: Date){
        crime.date = date
        updateUI()
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