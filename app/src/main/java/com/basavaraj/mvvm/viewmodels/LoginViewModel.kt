package com.basavaraj.mvvm.viewmodels
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basavaraj.mvvm.R
import com.basavaraj.mvvm.data.models.LoginRequest
import com.basavaraj.mvvm.data.models.NetworkResponse
import com.basavaraj.mvvm.data.models.UserModel
import com.basavaraj.mvvm.data.repository.LoginRepository
import com.basavaraj.mvvm.utils.Utils
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel()
{
    private val loginRepository = LoginRepository()
    val loginResponse: MutableLiveData<NetworkResponse<UserModel>> = MutableLiveData()
    fun login(emailId: String, password: String, context: Context?)
     {
         loginResponse.value = NetworkResponse.Loading()

         /**
          * Validation
          */
         if (!Utils.isValidEmail(emailId))
         {
             loginResponse.value = NetworkResponse.Error(context?.resources!!.getString(R.string.invalid_credentials))
             return
         }
         if (!Utils.isValidPassword(password))
         {
             loginResponse.value = NetworkResponse.Error(context?.resources!!.getString(R.string.invalid_credentials))
             return
         }
         /**
          * launch the coroutine
          */
         viewModelScope.launch{
            try {
                val loginRequest = LoginRequest(
                    password = password,
                    emailId = emailId
                )
                val response = loginRepository.login(loginRequest)
                loginResponse.value=  NetworkResponse.Success(response)

                /**
                 * Below Code will use in actual network API
                if (response?.code() == 200)
                    {
                      loginResponse.value = NetworkResponse.Success(response.body())
                     } else {
                        loginResponse.value = NetworkResponse.Error(response?.message())
                     }
                 */
            } catch (ex: Exception) {
                loginResponse.value = ex.message?.let { NetworkResponse.Error(it) }
            }
        }
    }
}