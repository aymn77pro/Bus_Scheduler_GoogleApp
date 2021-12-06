package com.example.busschedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(private val scheduleDao:ScheduleDao):ViewModel(){

    fun fullSchedule():Flow<List<Schedule>> = scheduleDao.getAll()

    fun scheduleForStopName(name:String):Flow<List<Schedule>> = scheduleDao.getBYStopName(name)
}
// if your view model use parameter you have to use class Factory
class BusScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)){
        @Suppress("UNCHECKED_CAST")
        return BusScheduleViewModel(scheduleDao) as T
    }else{
       return throw IllegalArgumentException("Unknown ViewModel class")
    }
    }

}