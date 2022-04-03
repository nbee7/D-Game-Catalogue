package com.submission.dicoding.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.submission.dicoding.core.R
import com.submission.dicoding.core.databinding.ItemGameScreenshotBinding
import com.submission.dicoding.core.domain.model.GameScreenshots
import com.submission.dicoding.core.utils.setImageUrl

class GameScreenshotAdapter :
    ListAdapter<GameScreenshots, GameScreenshotAdapter.HorizontalViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val view =
            ItemGameScreenshotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        val image = getItem(position)
        if (image != null) {
            holder.bind(image)
        }
    }

    inner class HorizontalViewHolder(private val binding: ItemGameScreenshotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GameScreenshots) {
            with(binding) {
                ivItemImage.setImageUrl(
                    itemView.context,
                    data.image,
                    progressBar,
                    R.drawable.ic_baseline_broken_image_24
                )
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GameScreenshots> =
            object : DiffUtil.ItemCallback<GameScreenshots>() {
                override fun areItemsTheSame(
                    oldUser: GameScreenshots,
                    newUser: GameScreenshots
                ): Boolean {
                    return oldUser.id == newUser.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: GameScreenshots,
                    newUser: GameScreenshots
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }
}