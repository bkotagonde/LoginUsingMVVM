package com.basavaraj.mvvm.uiview
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.basavaraj.mvvm.R
import com.basavaraj.mvvm.data.models.NetworkResponse
import com.basavaraj.mvvm.data.models.UserModel
import com.basavaraj.mvvm.databinding.FragmentLoginBinding
import com.basavaraj.mvvm.utils.Utils
import com.basavaraj.mvvm.utils.Utils.Companion.show
import com.basavaraj.mvvm.utils.Utils.Companion.toast
import com.basavaraj.mvvm.viewmodels.LoginViewModel


class LoginFragment : Fragment()
{
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Observe the state and data from the view model
         */
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResponse.Loading -> {
                    showLoading()
                }
                is NetworkResponse.Success -> {
                    goToNextScreen(it.data)
                    stopLoading()
                }
                is NetworkResponse.Error -> {
                    stopLoading()
                    showToast(it.error)
                }
                else -> {
                    stopLoading()
                }
            }
        }
        binding.buttonLogin.setOnClickListener {
            val emailId= binding.editTextEmail.editText?.text.toString()
            val password= binding.editTextEmail.editText?.text.toString()
            viewModel.login(emailId,password, activity)
        }
    }

    private fun goToNextScreen(data: UserModel?)
    {
        /**
         * Login success navigate to the new screen
         */
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
   private fun showToast(message: String) {
       /**
        * for showing toast used extension function
        */
       context?.toast(message)
    }
    private fun showLoading() {
      binding.circularProgressIndicator.show()
    }
    private fun stopLoading() {
        binding.circularProgressIndicator.hide()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}