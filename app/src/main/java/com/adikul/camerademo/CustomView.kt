package com.adikul.camerademo

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomView: View {

    constructor(context: Context): super(context){
        this.setWillNotDraw(false)
    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet){
        this.setWillNotDraw(false)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int): super(context, attributeSet, defStyle){
        this.setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val metrics = Resources.getSystem().displayMetrics
        val width = 691.5f*metrics.density
        val height = 285*metrics.density
        val paint = Paint()

        paint.strokeWidth = 5F
        paint.style = Paint.Style.STROKE
        paint.color = Color.argb(255, 255, 255, 255)

        if (canvas != null) {
            canvas.drawLine((width/12),0F,(width/12),height,paint)
            canvas.drawLine((width/12)*2,0F,(width/12)*2,height,paint)
            canvas.drawLine((width/12)*3,0F,(width/12)*3,height,paint)
            canvas.drawLine((width/12)*4,0F,(width/12)*4,height,paint)
            canvas.drawLine((width/12)*5,0F,(width/12)*5,height,paint)
            canvas.drawLine((width/12)*6,0F,(width/12)*6,height,paint)
            canvas.drawLine((width/12)*7,0F,(width/12)*7,height,paint)
            canvas.drawLine((width/12)*8,0F,(width/12)*8,height,paint)
            canvas.drawLine((width/12)*9,0F,(width/12)*9,height,paint)
            canvas.drawLine((width/12)*10,0F,(width/12)*10,height,paint)
            canvas.drawLine((width/12)*11,0F,(width/12)*11,height,paint)
            canvas.drawLine(0F,(height/5),width,(height/5),paint)
            canvas.drawLine(0F,(height/5)*2,width,(height/5)*2,paint)
            canvas.drawLine(0F,(height/5)*3,width,(height/5)*3,paint)
            canvas.drawLine(0F,(height/5)*4,width,(height/5)*4,paint)

            canvas.drawRect(0F, 0F, width, height, paint)

        }

    }
}