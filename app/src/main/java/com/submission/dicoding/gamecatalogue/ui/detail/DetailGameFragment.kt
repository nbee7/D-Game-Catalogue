package com.submission.dicoding.gamecatalogue.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.gamecatalogue.R
import com.submission.dicoding.gamecatalogue.databinding.FragmentDetailGameBinding
import com.submission.dicoding.gamecatalogue.utils.gone
import com.submission.dicoding.gamecatalogue.utils.setImageUrl
import com.submission.dicoding.gamecatalogue.utils.visible
import org.koin.android.viewmodel.ext.android.viewModel

class DetailGameFragment : Fragment() {

    private val detailGameViewModel: DetailGameViewModel by viewModel()
    private val detailGameFragmentArgs: DetailGameFragmentArgs by navArgs()
    private var _binding: FragmentDetailGameBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailGameBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idGame = detailGameFragmentArgs.id
        val type = detailGameFragmentArgs.type

        if (type == "search") {
            detailGameViewModel.getDetailGameFromSearch(idGame)
                .observe(viewLifecycleOwner) { game ->
                    if (game != null) {
                        when (game) {
                            is Resource.Loading -> showLoading(true)
                            is Resource.Success -> {
                                showLoading(false)
                                game.data?.let { showDataFromSearch(it) }
                            }
                            is Resource.Error -> {
                                showLoading(false)
                                showError(game.message)
                            }
                        }
                    }
                }
        } else {
            detailGameViewModel.getDetailGame(idGame).observe(viewLifecycleOwner) { game ->
                if (game != null) {
                    when (game) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            showLoading(false)
                            game.data?.let { showData(it) }
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            showError(game.message)
                        }
                    }
                }
            }
        }


        binding?.btnBack?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun showData(game: Games) {
        binding?.apply {
            activity?.let {
                ivImage.setImageUrl(
                    it,
                    game.image,
                    pbImage,
                    R.drawable.ic_baseline_broken_image_24,
                )
            }
            tvTittle.text = game.name
            tvGenres.text = game.genres
            tvRating.text = resources.getString(R.string.rating, game.rating.toString())
            tvReleased.text = game.released
            tvDescription.text = game.description

            var isFavorite = game.isFavorite
            setFavorite(isFavorite)
            fabFavorite.setOnClickListener {
                if (isFavorite) {
                    isFavorite = !isFavorite
                    detailGameViewModel.setFavoriteGame(game, isFavorite)
                } else {
                    isFavorite = !isFavorite
                    detailGameViewModel.setFavoriteGame(game, isFavorite)
                }
            }
        }
    }

    private fun showDataFromSearch(game: Games) {
        binding?.apply {
            activity?.let {
                ivImage.setImageUrl(
                    it,
                    game.image,
                    pbImage,
                    R.drawable.ic_baseline_broken_image_24
                )
            }
            tvTittle.text = game.name
            tvGenres.text = game.genres
            tvRating.text = resources.getString(R.string.rating, game.rating.toString())
            tvReleased.text = game.released
            tvDescription.text = game.description

            var isFavorite = game.isFavorite
            setFavorite(isFavorite)
            fabFavorite.setOnClickListener {
                if (isFavorite) {
                    isFavorite = !isFavorite
                    setFavorite(isFavorite)
                    detailGameViewModel.insertGameFromSearch(game, isFavorite)
                } else {
                    isFavorite = !isFavorite
                    setFavorite(isFavorite)
                    detailGameViewModel.insertGameFromSearch(game, isFavorite)
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.pbUser?.visible()
        } else {
            binding?.pbUser?.gone()
        }
    }

    private fun showError(message: String?) {
        binding?.tvError?.text = message
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding?.fabFavorite?.setImageDrawable(
                activity?.let {
                    ContextCompat.getDrawable(
                        it, R.drawable.ic_favorited
                    )
                }
            )
        } else {
            binding?.fabFavorite?.setImageDrawable(
                activity?.let {
                    ContextCompat.getDrawable(
                        it, R.drawable.ic_favorite
                    )
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}