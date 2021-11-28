package com.gameonanil.tailorapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.gameonanil.tailorapp.data.entity.Clothing
import com.gameonanil.tailorapp.data.entity.Customer
import com.gameonanil.tailorapp.data.entity.Measurement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TailorViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: TailorRepository = TailorRepository(application)

    private var mCustomerId: MutableLiveData<Int> = MutableLiveData()
    private var mClothingId: MutableLiveData<Int> = MutableLiveData()
    val mSortBy: MutableLiveData<String> = MutableLiveData()

//    var customerWithClothing: LiveData<List<Clothing>> =
//        Transformations.switchMap(mSortBy) {
//            mCustomerId.value?.let { it1 -> repository.getClothingListByCusId(it1,it) }
//        }
//


    val measurement: LiveData<Measurement> = Transformations.switchMap(mCustomerId) {
        repository.getMeasurementByCustomerId(customerId = it)
    }

    val customerList: LiveData<List<Customer>> = repository.getAllCustomers()

    val clothing: LiveData<Clothing> = Transformations.switchMap(mClothingId) {
        repository.getClothing(it)
    }

    fun setClothingId(clothingId: Int) {
        mClothingId.value = clothingId
    }

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCustomer(customer)
        }
    }

    fun insertClothing(clothing: Clothing) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertClothing(clothing)
        }
    }

    fun insertMeasurement(measurement: Measurement) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMeasurement(measurement)
        }
    }

    fun setCustomerId(customerId: Int) {
        mCustomerId.value = customerId
    }

    fun setSort(sort: String) {
        mSortBy.value = sort
    }

    fun getMeasurement(customerId: Int) {
        mCustomerId.value = customerId
    }

    fun getMeasurementById(customerId: Int): Measurement? {
        return repository.getMeasurementByCusId(customerId)
    }


    fun getClothingByCusId(customerId: Int): Clothing? {
        return repository.getClothingByCusId(customerId)
    }


    fun updateMeasurement(measurement: Measurement) {
        repository.updateMeasurement(measurement)
    }

    fun updateClothing(clothing: Clothing) {
        repository.updateClothing(clothing)
    }


}