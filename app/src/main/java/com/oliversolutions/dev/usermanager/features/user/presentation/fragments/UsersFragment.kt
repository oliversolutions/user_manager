package com.oliversolutions.dev.usermanager.features.user.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oliversolutions.dev.usermanager.core.base.BaseFragment
import com.oliversolutions.dev.usermanager.core.base.NavigationCommand
import com.oliversolutions.dev.usermanager.databinding.FragmentUsersBinding
import com.oliversolutions.dev.usermanager.features.user.presentation.utils.UserGridAdapter
import com.oliversolutions.dev.usermanager.features.user.presentation.viewModels.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersBinding
    override val viewModel: UsersViewModel by viewModel()
    private lateinit var userGridAdapter: UserGridAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.usersRecycler.adapter =
            UserGridAdapter(UserGridAdapter.OnClickListener {
                viewModel.navigationCommand.value = NavigationCommand.To(UsersFragmentDirections.actionUsersFragmentToUserDetailFragment(it))
            }).apply {
                userGridAdapter = this
            }
        viewModel.users.observe(viewLifecycleOwner) {
            userGridAdapter.submitList(it)
        }

        binding.createUserButton.setOnClickListener {
            viewModel.navigationCommand.value = NavigationCommand.To(UsersFragmentDirections.actionUsersFragmentToNewUserFragment())
        }

        return binding.root
    }
}

