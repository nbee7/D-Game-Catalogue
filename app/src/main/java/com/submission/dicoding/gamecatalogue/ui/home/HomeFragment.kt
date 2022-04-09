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
import com.submission.dicoding.gamecatalogue.databinding.FragmentHomeBinding
import com.submission.dicoding.gamecatalogue.ui.adapter.GameAdapter
import com.submission.dicoding.gamecatalogue.utils.ItemClickCallback
import com.submission.dicoding.gamecatalogue.utils.gone
import com.submission.dicoding.gamecatalogue.utils.visible
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), ItemClickCallback {

    private val homeViewModel: HomeViewModel by viewModel()

    private var toast: Toast? = null

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
                            game.data?.let { setRecyclerview(it) }
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

    private fun setRecyclerview(listGame: List<Games>) {
        if (listGame.isNullOrEmpty()) {
            showLoading(false)
            binding?.rvListGame?.gone()
            binding?.tvEmpty?.visible()
        } else {
            showLoading(false)
            binding?.tvEmpty?.gone()
            binding?.rvListGame?.visible()
            val gameAdapter = GameAdapter(this)
            gameAdapter.submitList(listGame)
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
        if (toast != null) {
            toast?.cancel()
        } else {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast?.show()
        }
    }

    override fun onItemClicked(id: Int) {
        val action = HomeFragmentDirections.actionToDetail(id)
        findNavController().navigate(action)
    }

    override fun onStop() {
        super.onStop()
        if (toast != null) {
            toast?.cancel()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (toast != null) {
            toast?.cancel()
        }
    }
}