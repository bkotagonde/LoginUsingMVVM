package com.basavaraj.mvvm.data.repository
import com.basavaraj.mvvm.data.models.LoginRequest
import com.basavaraj.mvvm.data.models.UserModel
/**
 * TODO
 * class LoginRepository(private val networkHelper: NetworkHelper)
 * this above constructor is used to when actual api is available
 */
class LoginRepository
{
     fun login(loginRequest: LoginRequest): UserModel
     {
        /**
         * NetworkHelper.login(loginRequest)
         * Here NetworkHelper.login method return response will catch success and failure
         * Currently we don't have actual network class so i am passing only success adding to model and returning
         */
        return UserModel("Basavaraj Kotagond","Login Success","6f7fd7142cb21bd448703be19b050ef9fb0425da71c5439a11ec567ce092566a")
    }
}