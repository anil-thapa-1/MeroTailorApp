package com.gameonanil.tailorapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gameonanil.tailorapp.data.entity.Clothing
import com.gameonanil.tailorapp.data.entity.Customer
import com.gameonanil.tailorapp.data.entity.CustomerWithClothing
import com.gameonanil.tailorapp.data.entity.Measurement

@Dao
interface TailorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(customer: Customer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClothing(clothing: Clothing)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeasurement(measurement: Measurement)

    @Transaction
    @Query("SELECT * FROM customer_table WHERE customerId=:customerId ")
    fun getCustomerWithClothing(customerId: Int): LiveData<CustomerWithClothing>

    @Query("SELECT * FROM MEASUREMENT_TABLE WHERE customerId=:customerId")
    fun getMeasurementByCustomerId(customerId: Int): LiveData<Measurement>


    @Query("SELECT * FROM CUSTOMER_TABLE")
    fun getAllCustomer(): LiveData<List<Customer>>
}