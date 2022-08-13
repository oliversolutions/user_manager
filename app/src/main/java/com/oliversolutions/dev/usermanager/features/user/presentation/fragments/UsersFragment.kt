package com.oliversolutions.dev.usermanager.features.user.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oliversolutions.dev.usermanager.core.base.BaseFragment
import com.oliversolutions.dev.usermanager.databinding.FragmentUsersBinding
import com.oliversolutions.dev.usermanager.features.user.presentation.UserGridAdapter
import com.oliversolutions.dev.usermanager.features.user.presentation.viewModels.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersBinding
    override val _viewModel: UserViewModel by viewModel()
    private lateinit var userGridAdapter: UserGridAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = _viewModel

        binding.usersRecycler.adapter =
            UserGridAdapter(UserGridAdapter.OnClickListener {}).apply {
                userGridAdapter = this
            }
        _viewModel.users.observe(viewLifecycleOwner) {
            userGridAdapter.submitList(it)
        }

        return binding.root
    }
}

