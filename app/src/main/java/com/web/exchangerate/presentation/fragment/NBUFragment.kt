package com.web.exchangerate.presentation.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.web.exchangerate.R
import com.web.exchangerate.data.model.NBUBankRate
import com.web.exchangerate.presentation.adapter.NBUAdapter
import com.web.exchangerate.presentation.viewmodel.BankViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
class NBUFragment : Fragment(), NBUAdapter.Listener {
    private val viewModel: BankViewModel by sharedViewModel()
    private val nbuObserver = Observer<List<NBUBankRate>>(::handleRate)
    private val adapter = NBUAdapter(this)
    private var recyclerView: RecyclerView? = null
    private var textViewDate: TextView? = null
    private var cal = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nbu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        viewModel.nbuListLiveData.observe(viewLifecycleOwner, nbuObserver)
        var mYear = cal.get(Calendar.YEAR)
        var mMonth = cal.get(Calendar.MONTH)
        var mDay = cal.get(Calendar.DAY_OF_MONTH)
        textViewDate = view.findViewById(R.id.text_view_date_nbu)
        textViewDate!!.text = "$mYear-$mMonth-$mDay"
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
                val df: DateFormat = SimpleDateFormat("yyyyMMdd")
                val date1: String = df.format(cal.time)
                viewModel.initNBUArchive(date1)
            }
        }
        textViewDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    view.context,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
        viewModel.initNBU()
    }

    override fun onClick(nbu: NBUBankRate) {
    }

    private fun handleRate(nbu: List<NBUBankRate>?) {
        if (nbu == null) return
        adapter.submitList(nbu)
    }

    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textViewDate!!.text = sdf.format(cal.getTime())
    }


}












