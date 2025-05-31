package com.example.scheduleit

import android.app.Application
import com.example.scheduleit.data.graph.Graph

class ScheduleItApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}
