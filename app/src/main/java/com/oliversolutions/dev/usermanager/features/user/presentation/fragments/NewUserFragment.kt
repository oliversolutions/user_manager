package com.oliversolutions.dev.usermanager.features.user.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oliversolutions.dev.usermanager.R
import com.oliversolutions.dev.usermanager.core.base.BaseFragment
import com.oliversolutions.dev.usermanager.databinding.FragmentNewUserBinding
import com.oliversolutions.dev.usermanager.features.user.domain.entities.DateTime
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.presentation.viewModels.NewUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class NewUserFragment : BaseFragment() {

    override val viewModel: NewUserViewModel by viewModel()
    private lateinit var binding: FragmentNewUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        binding.time.setOnClickListener { showTimePickerDialog() }
        binding.date.setOnClickListener { showDatePickerDialog() }
        binding.timeImageView.setOnClickListener { showTimePickerDialog() }
        binding.dateImageView.setOnClickListener { showDatePickerDialog() }
        binding.createUser.setOnClickListener {
            createUser()
        }
        return binding.root
    }

    private fun createUser(): Boolean {
        val username = binding.userName.text.toString()
        if (username.isEmpty()) {
            viewModel.showToast.value = getString(R.string.user_cannot_be_empty)
            return false
        }
        viewModel.createUser(
            User(
                username,
                DateTime(
                    DateTime.getDateInIso8601(
                        binding.date.text.toString(),
                        binding.time.text.toString()
                    )
                ),
                0
            )
        )
        return true
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(parentFragmentManager, "timePicker")
    }

    private fun onTimeSelected(time: String) {
        binding.time.text = time
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(parentFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val monthPlus1 = month + 1
        val selectedDate = "$year-$monthPlus1-$day"
        binding.date.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
            Date(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(selectedDate).time)
        )
    }
}