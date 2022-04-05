package com.submission.dicoding.gamecatalogue.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.core.ui.GameAdapter
import com.submission.dicoding.core.utils.ItemClickCallback
import com.submission.dicoding.core.utils.gone
import com.submission.dicoding.core.utils.visible
import com.submission.dicoding.gamecatalogue.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), ItemClickCallback {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            homeViewModel.listGames.observe(viewLifecycleOwner) { game ->
                if (game != null) {
                    when (game) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            game.data?.let { setRecycleview(it) }
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            showError(game.message)
                        }
                    }
                }
            }
        }
    }

    private fun setRecycleview(listUser: List<Games>) {
        if (listUser.isNullOrEmpty()) {
            showLoading(false)
            binding?.rvListGame?.gone()
            binding?.tvEmpty?.visible()
        } else {
            showLoading(false)
            binding?.tvEmpty?.gone()
            binding?.rvListGame?.visible()
            val gameAdapter = GameAdapter(this)
            gameAdapter.submitList(listUser)
            binding?.rvListGame?.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.rvListGame?.gone()
            binding?.pbUser?.visible()
            binding?.tvEmpty?.gone()
        } else {
            binding?.pbUser?.gone()
        }
    }

    private fun showError(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemclicked(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailGameFragment(id)
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}