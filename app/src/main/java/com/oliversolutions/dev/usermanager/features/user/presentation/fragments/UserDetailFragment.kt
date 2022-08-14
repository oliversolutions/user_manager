package com.oliversolutions.dev.usermanager.features.user.presentation.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.oliversolutions.dev.usermanager.R
import com.oliversolutions.dev.usermanager.core.base.BaseFragment
import com.oliversolutions.dev.usermanager.databinding.FragmentUserDetailBinding
import com.oliversolutions.dev.usermanager.features.user.domain.entities.DateTime
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.presentation.viewModels.UserDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class UserDetailFragment : BaseFragment() {

    override val viewModel: UserDetailViewModel by viewModel()
    private lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        binding.user = UserDetailFragmentArgs.fromBundle(requireArguments()).user
        binding.time.setOnClickListener { showTimePickerDialog() }
        binding.date.setOnClickListener { showDatePickerDialog() }
        binding.timeImageView.setOnClickListener { showTimePickerDialog() }
        binding.dateImageView.setOnClickListener { showDatePickerDialog() }
        binding.updateUser.setOnClickListener {
            updateUser()
        }
        inflateMenu()

        return binding.root
    }

    private fun updateUser() : Boolean {
        val username = binding.userName.text.toString()
        if (username.isEmpty()) {
            viewModel.showToast.value = getString(R.string.user_cannot_be_empty)
            return false
        }
        viewModel.updateUser(
            User(
                username,
                DateTime(
                    DateTime.getDateInIso8601(
                        binding.date.text.toString(),
                        binding.time.text.toString()
                    )
                ),
                UserDetailFragmentArgs.fromBundle(requireArguments()).user.id
            )
        )
        return true
    }

    private fun inflateMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_user_detail, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.deleteUser) {
                    viewModel.deleteUser(UserDetailFragmentArgs.fromBundle(requireArguments()).user.id)
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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