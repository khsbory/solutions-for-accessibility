package com.nvisions.solutionsforaccessibility.RatingBar

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.rating_bar_good_activity.*


class RatingBarGoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rating_bar_good_activity)
        init()
    }

    fun init(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(getString(R.string.ratingBar_good))
        val accessibilityManager = getSystemService(ACCESSIBILITY_SERVICE) as AccessibilityManager
       ratingBar2.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info?.className = SeekBar::class.java.name
            }

       })

        ratingBar2.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (accessibilityManager.isEnabled) { //접근성이 활성화 되어 있는 경우
                accessibilityManager.interrupt()
            }
            ratingBar2.contentDescription = rating.toInt().toString() + getString(R.string.score)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}


