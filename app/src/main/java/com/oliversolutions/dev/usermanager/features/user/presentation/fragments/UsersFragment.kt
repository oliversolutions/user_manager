package com.oliversolutions.dev.usermanager.features.user.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oliversolutions.dev.usermanager.core.base.BaseFragment
import com.oliversolutions.dev.usermanager.databinding.FragmentUsersBinding
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.presentation.viewModels.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersBinding
    override val _viewModel: UserViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)

        /*_viewModel.users.observe(viewLifecycleOwner) {
            it.forEach {
                Log.i("user", it.name)
            }
        }
        _viewModel.getUser("6306")
        _viewModel.user.observe(viewLifecycleOwner) {
            Log.i("output", it.name)
        }*/
        //_viewModel.deleteUser("6309")
       // _viewModel.updateUser(User("tonto del culo", "2022-08-13T09:38:44.351Z", 6307))
        _viewModel.createUser(User("xa0xa0", "2022-08-13T09:38:44.351Z", 0))

        return binding.root
    }
}

