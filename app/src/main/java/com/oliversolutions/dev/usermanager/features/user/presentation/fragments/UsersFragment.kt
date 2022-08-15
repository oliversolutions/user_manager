package com.oliversolutions.dev.usermanager.features.user.presentation.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.oliversolutions.dev.usermanager.R
import com.oliversolutions.dev.usermanager.core.base.BaseFragment
import com.oliversolutions.dev.usermanager.core.base.NavigationCommand
import com.oliversolutions.dev.usermanager.databinding.FragmentUsersBinding
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.presentation.utils.UserGridAdapter
import com.oliversolutions.dev.usermanager.features.user.presentation.viewModels.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersBinding
    override val viewModel: UsersViewModel by viewModel()
    private lateinit var userGridAdapter: UserGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        inflateMenu()
        binding.usersRecycler.adapter =
            UserGridAdapter(UserGridAdapter.OnClickListener {
                viewModel.navigationCommand.value = NavigationCommand.To(
                    UsersFragmentDirections.actionUsersFragmentToUserDetailFragment(it)
                )
            }).apply {
                userGridAdapter = this
            }
        viewModel.users.observe(viewLifecycleOwner) {
            userGridAdapter.submitList(it)
        }

        binding.createUserButton.setOnClickListener {
            viewModel.navigationCommand.value =
                NavigationCommand.To(UsersFragmentDirections.actionUsersFragmentToNewUserFragment())
        }

        return binding.root
    }

    private fun filterResults(query: String) {
        val filteredUsers = mutableListOf<User>()
        viewModel.users.value?.forEach {
            if (it.name.contains(query)) {
                filteredUsers.add(it)
            }
        }
        userGridAdapter.submitList(filteredUsers)
        userGridAdapter.notifyDataSetChanged()

    }

    private fun inflateMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_users, menu)
                val searchView = menu.findItem(R.id.searchAction).actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        filterResults(query.toString())
                        return true
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        filterResults(newText.toString())
                        return true
                    }
                })
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}

