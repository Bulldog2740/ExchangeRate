package com.web.exchangerate.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.web.exchangerate.R
import com.web.exchangerate.presentation.adapter.PrivatBankAdapter
import com.web.exchangerate.presentation.viewmodel.BankViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
class MainActivity : AppCompatActivity() {
    private val viewModel: BankViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

    }
}