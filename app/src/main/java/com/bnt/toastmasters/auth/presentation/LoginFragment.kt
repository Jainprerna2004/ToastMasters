package com.bnt.toastmasters.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bnt.toastmasters.databinding.FragmentLoginBinding
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
        binding.tvSignup.setOnClickListener {
            (activity as? AuthActivity)?.showSignup()
        }
        binding.tvForgotPassword.setOnClickListener {
            viewModel.forgotPassword(binding.etEmail.text.toString())
        }
        // Observe authState for role-based redirection
        viewModel.authState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is com.bnt.toastmasters.auth.domain.AuthState.SuccessWithRole -> {
                    val ctx = requireContext()
                    val intent = Intent(ctx, com.bnt.toastmasters.MainActivity::class.java)
                    intent.putExtra("userType", state.userType)
                    startActivity(intent)
                    activity?.finish()
                }
                is com.bnt.toastmasters.auth.domain.AuthState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
                // Optionally handle other states (loading, etc.)
                else -> {}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 