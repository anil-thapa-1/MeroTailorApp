package com.gameonanil.tailorapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.gameonanil.tailorapp.data.entity.Clothing
import com.gameonanil.tailorapp.data.entity.Customer
import com.gameonanil.tailorapp.data.entity.Measurement
import com.gameonanil.tailorapp.data.entity.NotificationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TailorViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: TailorRepository = TailorRepository(application)
    private var mClothingId: MutableLiveData<Int> = MutableLiveData()


    val clothing: LiveData<Clothing> = Transformations.switchMap(mClothingId) {
        repository.getClothing(it)
    }

    /**INSERT**/
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

    fun insertNotification(notificationEntity: NotificationEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNotification(notificationEntity)
        }
    }

    /**UPDATE**/
    fun updateMeasurement(measurement: Measurement) {
        repository.updateMeasurement(measurement)
    }

    fun updateClothing(clothing: Clothing) {
        repository.updateClothing(clothing)
    }

    fun updateCustomer(customer: Customer) {
        repository.updateCustomer(customer)
    }

    /**DELETE**/
    fun deleteClothing(clothing: Clothing) {
        repository.deleteClothing(clothing)
    }

    fun deleteCustomer(customer: Customer) {
        repository.deleteCustomer(customer)
    }

    fun deleteMeasurement(measurement: Measurement) {
        repository.deleteMeasurement(measurement)
    }


    fun getClothingById(clothingId: Int): Clothing? {
        return repository.getClothingById(clothingId)
    }


}