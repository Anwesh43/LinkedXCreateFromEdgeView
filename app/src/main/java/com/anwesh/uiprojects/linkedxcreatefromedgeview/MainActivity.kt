package com.anwesh.uiprojects.linkedxcreatefromedgeview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.xcreateedgeview.XCreateEdgeView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XCreateEdgeView.create(this)
    }
}
