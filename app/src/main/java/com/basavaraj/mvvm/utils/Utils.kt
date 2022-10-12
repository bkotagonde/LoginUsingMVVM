package com.basavaraj.mvvm.utils

import android.content.Context
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.util.regex.Pattern

class Utils {
    companion object {
    fun View.show() : View {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
        return this
    }
    fun CircularProgressIndicator.hide() : View {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
        return this
    }

      fun CircularProgressIndicator.show():View
      {
          if (visibility != View.VISIBLE) {
              visibility = View.VISIBLE
          }
          return this
      }


    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        val EMAIL_ADDRESS: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        fun isValidEmail(email: String): Boolean {
          return if (email.isBlank())
            {
              false
            } else {
                val pattern: Pattern = Patterns.EMAIL_ADDRESS
                return pattern.matcher(email).matches()
            }
        }
        fun isValidPassword(password:String):Boolean
        {
            /**
             * We can as per our custom logic here to check password
             */
           if(password.isBlank()) false
           return password.length >= 8
        }
    }
}