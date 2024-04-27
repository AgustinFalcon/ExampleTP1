package com.example.examplesharedpreferences.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.examplesharedpreferences.R
import com.example.examplesharedpreferences.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding

    var number = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)


        val thirdViewModel = ViewModelProvider(this).get(ThirdViewModel::class.java)


        binding.btnNext.setOnClickListener {
            thirdViewModel.setNumberValue()
            //binding.tvNumberToShow.text = thirdViewModel.getNumberValue().toString()
        }


        thirdViewModel.getNumberLiveData().observe(viewLifecycleOwner) { newNumber ->
            binding.tvNumberToShow.text = newNumber.toString()
        }



        binding.editTextEmail.addTextChangedListener { email ->
            thirdViewModel.validateEmail(email.toString())
        }

        binding.editTextPassword.addTextChangedListener {  password ->
            thirdViewModel.validatePassword(password.toString())
        }



        thirdViewModel.viewState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is ThirdStates.SuccessEmail -> {
                    binding.layoutEmail.error = null
                }
                is ThirdStates.ErrorEmail -> {
                    binding.layoutEmail.error = "Formato email invalido"
                }
                is ThirdStates.SuccessPassword -> {
                    binding.layoutPassword.error = null
                }
                is ThirdStates.ErrorPassword -> {
                    binding.layoutPassword.error = "${state.password.length}/3"
                }

                is ThirdStates.ErrorButton -> {
                    binding.btnNext.isEnabled = false
                }

                is ThirdStates.SuccessButton -> {
                    binding.btnNext.isEnabled = true
                }
            }
        }



        return binding.root
    }


}