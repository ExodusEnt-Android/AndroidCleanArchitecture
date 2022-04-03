package org.techtown.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.presentation.adapter.UserListAdapter
import org.techtown.presentation.databinding.FragmentMyFavoritesBinding
import org.techtown.presentation.model.PresentationUserModel
import org.techtown.presentation.viewmodel.UserViewModel


class MyFavoritesFragment : Fragment(),
    UserListAdapter.onFavClickListener{

    private var _binding: FragmentMyFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListAdapter: UserListAdapter

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSet()
        getDataFromViewModel()
    }

    private fun initSet() {

        userListAdapter = UserListAdapter(
            null
        ) { userModel: PresentationUserModel, view: View, i: Int ->
            onFavClick(userModel, view, i)
        }

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = userListAdapter
        }

        userListAdapter.submitList(userViewModel.favoriteUserList)
    }

    private fun getDataFromViewModel() {
        userViewModel.favoritesFragmentUpdateUserList.subscribe({
            userListAdapter.submitList(it.toMutableList())
        }, {
            Toast.makeText(activity, it.message.toString(), Toast.LENGTH_SHORT)
                .show()
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyFavoritesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onFavClick(model: PresentationUserModel, v: View, position: Int) {
        userViewModel.deleteFavUser(model)
    }
}