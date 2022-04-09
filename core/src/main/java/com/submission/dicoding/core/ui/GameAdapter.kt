package com.submission.dicoding.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.submission.dicoding.core.R
import com.submission.dicoding.core.databinding.ItemGameBinding
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.core.utils.ItemClickCallback
import com.submission.dicoding.core.utils.setImageUrl
import com.submission.dicoding.core.utils.setVisibility

class GameAdapter(val callback: ItemClickCallback? = null) :
    ListAdapter<Games, GameAdapter.VerticalViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val view = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VerticalViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        val game = getItem(position)
        if (game != null) {
            holder.bind(game)
        }
    }

    inner class VerticalViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Games) {
            binding.apply {
                ivGame.setImageUrl(
                    itemView.context,
                    data.image,
                    progressBar,
                    R.drawable.ic_baseline_broken_image_24
                )
                tvName.text = data.name
                tvRating.text = data.rating.toString()


                val isPcGame = data.platform.any { it == "pc" }
                val isPsGame = data.platform.any { it == "playstation" }
                val isXboxGame = data.platform.any { it == "xbox" }
                val isNintendoGame = data.platform.any { it == "nintendo" }

                pcPlatformIcon.setVisibility(isPcGame)
                psPlatformIcon.setVisibility(isPsGame)
                xboxPlatformIcon.setVisibility(isXboxGame)
                nintendoPlatformIcon.setVisibility(isNintendoGame)

                itemView.setOnClickListener {
                    callback?.onItemClicked(data.id)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Games> =
            object : DiffUtil.ItemCallback<Games>() {
                override fun areItemsTheSame(
                    oldUser: Games,
                    newUser: Games
                ): Boolean {
                    return oldUser.id == newUser.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: Games,
                    newUser: Games
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }
}