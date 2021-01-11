package com.web.exchangerate.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.web.exchangerate.data.model.NBUBankRate
import com.web.exchangerate.data.model.PrivatBankRate
import com.web.exchangerate.repository.NBURepository
import com.web.exchangerate.repository.PBRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
class BankViewModel(
    application: Application,
    private val nbuRepository: NBURepository,
    private val pbRepository: PBRepository,
) :
    AndroidViewModel(application) {
    protected val io: CoroutineDispatcher
        get() = Dispatchers.IO

    protected val main: CoroutineDispatcher
        get() = Dispatchers.Main

    val nbuListLiveData: LiveData<List<NBUBankRate>>
        get() = nbuListEmitter

    val privatBankListLiveData: LiveData<List<PrivatBankRate>>
        get() = privatBankListEmitter

    private val nbuListEmitter = MutableLiveData<List<NBUBankRate>>()
    private val privatBankListEmitter = MutableLiveData<List<PrivatBankRate>>()

    fun initNBU() {
        viewModelScope.launch(io) {
            val nbuExchangeRate = nbuRepository.getExchangeRate()
            withContext(main) {
                nbuListEmitter.value = nbuExchangeRate
            }
        }
    }

    fun initNBUArchive(date: String) {
        viewModelScope.launch(io) {
            val nbuExchangeRate = nbuRepository.getExchangeRateArchive(date)
            withContext(main) {
                nbuListEmitter.value = nbuExchangeRate
            }
        }
    }

    fun initPB() {
        viewModelScope.launch(io) {
            val privatExchangeRate = pbRepository.getExchangeRate()
            withContext(main) {
                privatBankListEmitter.value = privatExchangeRate
            }
        }
    }

    fun initPBArchive(date: String) {
        viewModelScope.launch(io) {
            val privatExchangeRate = pbRepository.getExchangeRateArchive(date)
            withContext(main) {
                privatBankListEmitter.value = privatExchangeRate
            }
        }
    }
}
