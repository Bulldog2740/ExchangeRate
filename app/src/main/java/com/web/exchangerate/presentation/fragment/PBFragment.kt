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
import com.web.exchangerate.data.model.PrivatBankRate
import com.web.exchangerate.presentation.adapter.PrivatBankAdapter
import com.web.exchangerate.presentation.viewmodel.BankViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.nio.channels.Selector
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
class PBFragment : Fragment(), PrivatBankAdapter.Listener {
    private val viewModel: BankViewModel by sharedViewModel()
    private val privatObserver = Observer<List<PrivatBankRate>>(::handleRate)
    private val adapter = PrivatBankAdapter(this)
    private var recyclerView: RecyclerView? = null
    private var textViewDate: TextView? = null
    private var cal = Calendar.getInstance()
    private lateinit var itemSelector: Selector

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_privat_bank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mYear = cal.get(Calendar.YEAR)
        var mMonth = cal.get(Calendar.MONTH)
        var mDay = cal.get(Calendar.DAY_OF_MONTH)
        recyclerView = view.findViewById(R.id.recyclerViewPrivat)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        viewModel.privatBankListLiveData.observe(viewLifecycleOwner, privatObserver)
        viewModel
        textViewDate = view.findViewById(R.id.textViewDatePrivat)
        textViewDate!!.text = "$mDay-$mMonth-$mYear"
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
                val df: DateFormat = SimpleDateFormat("dd.MM.yyyy")
                val date1: String = df.format(cal.time)
                viewModel.initPBArchive(date1)
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
        viewModel.initPB()
    }

    override fun onClick(pb: PrivatBankRate) {
        //null
    }

    private fun handleRate(pb: List<PrivatBankRate>?) {
        if (pb == null) return
        adapter.submitList(pb)
    }

    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textViewDate!!.text = sdf.format(cal.getTime())
    }

}











