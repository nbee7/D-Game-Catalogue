package com.submission.dicoding.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.dicoding.favorite.databinding.FragmentFavoriteGamesBinding
import com.submission.dicoding.gamecatalogue.ui.adapter.GameAdapter
import com.submission.dicoding.gamecatalogue.utils.ItemClickCallback
import com.submission.dicoding.gamecatalogue.utils.gone
import com.submission.dicoding.gamecatalogue.utils.visible
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteGamesFragment : Fragment(), ItemClickCallback {

    private val favoriteViewModel: FavoriteGamesViewModel by viewModel()

    private var _binding: FragmentFavoriteGamesBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteGamesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)

        val gameAdapter = GameAdapter(this)
        binding?.rvListGame?.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        if (activity != null) {
            favoriteViewModel.favoriteGames.observe(viewLifecycleOwner) { game ->
                if (game.isNullOrEmpty()) {
                    binding?.rvListGame?.gone()
                    binding?.tvEmpty?.visible()
                } else {
                    binding?.tvEmpty?.gone()
                    binding?.rvListGame?.visible()
                    gameAdapter.submitList(game)
                }
            }
        }
    }

    override fun onItemClicked(id: Int) {
        val action = FavoriteGamesFragmentDirections.actionToDetail(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}