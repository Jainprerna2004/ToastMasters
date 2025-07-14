package com.bnt.toastmasters.auth.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bnt.toastmasters.databinding.FragmentSignupBinding
import java.util.*

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etDateOfJoining.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireContext(), { _, y, m, d ->
                binding.etDateOfJoining.setText("$d/${m+1}/$y")
            }, year, month, day)
            dpd.show()
        }
        binding.btnSignup.setOnClickListener {
            viewModel.signup(
                binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etMobile.text.toString(),
                binding.etLevel.text.toString(),
                binding.etDateOfJoining.text.toString(),
                binding.etPassword.text.toString()
            )
        }
        binding.tvLogin.setOnClickListener {
            (activity as? AuthActivity)?.showLogin()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 