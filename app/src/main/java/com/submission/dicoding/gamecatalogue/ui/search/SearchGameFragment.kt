package com.submission.dicoding.gamecatalogue.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.core.ui.GameAdapter
import com.submission.dicoding.core.utils.ItemClickCallback
import com.submission.dicoding.core.utils.gone
import com.submission.dicoding.core.utils.visible
import com.submission.dicoding.gamecatalogue.databinding.FragmentSearchGameBinding
import org.koin.android.viewmodel.ext.android.viewModel


class SearchGameFragment : Fragment(), ItemClickCallback {

    private val searchGameViewModel: SearchGameViewModel by viewModel()

    private var _binding: FragmentSearchGameBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchGameBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding?.svGame?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        searchGameViewModel.getSearchGame(query)
                            .observe(viewLifecycleOwner) { game ->
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
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
    }

    private fun setRecycleview(listGame: List<Games>) {
        if (listGame.isNullOrEmpty()) {
            showLoading(false)
            binding?.rvSearchGame?.gone()
            binding?.tvEmpty?.visible()
        } else {
            showLoading(false)
            binding?.tvEmpty?.gone()
            binding?.rvSearchGame?.visible()
            val gameAdapter = GameAdapter(this)
            gameAdapter.submitList(listGame)
            binding?.rvSearchGame?.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.rvSearchGame?.gone()
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
        val action = SearchGameFragmentDirections.actionToDetail(id, "search")
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}