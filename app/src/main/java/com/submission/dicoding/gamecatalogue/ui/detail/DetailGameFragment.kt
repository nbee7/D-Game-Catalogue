package com.submission.dicoding.gamecatalogue.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.core.utils.gone
import com.submission.dicoding.core.utils.visible
import com.submission.dicoding.gamecatalogue.R
import com.submission.dicoding.gamecatalogue.databinding.FragmentDetailGameBinding
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

        binding?.btnBack?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun showData(game: Games) {
        binding?.apply {
            tvTittle.text = game.name
            tvGenres.text = game.genres
            tvRating.text = resources.getString(R.string.rating, game.rating.toString())
            tvReleased.text = game.released
            tvDescription.text = game.description
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.rvScreenshots?.gone()
            binding?.pbUser?.visible()
        } else {
            binding?.pbUser?.gone()
        }
    }

    private fun showError(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}