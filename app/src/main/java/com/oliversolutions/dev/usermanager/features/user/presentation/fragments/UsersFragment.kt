package com.oliversolutions.dev.usermanager.features.user.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oliversolutions.dev.usermanager.core.base.BaseFragment
import com.oliversolutions.dev.usermanager.databinding.FragmentUsersBinding
import com.oliversolutions.dev.usermanager.features.user.presentation.viewModels.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersBinding
    override val _viewModel: UserViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)

        _viewModel.users.observe(viewLifecycleOwner){
            it.forEach {
                Log.i("user", it.name)
            }
        }
        return binding.root
    }
}

