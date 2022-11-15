package kosmicbor.redditpaginationmagicapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kosmicbor.redditpaginationmagicapp.R
import kosmicbor.redditpaginationmagicapp.databinding.ItemMainscreenListBinding
import kosmicbor.redditpaginationmagicapp.domain.RedditPost

class MainPagingDataAdapter(context: Context) :
    PagingDataAdapter<RedditPost, MainPostViewHolder>(RedditPostDiffUtilCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: MainPostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPostViewHolder {
        return MainPostViewHolder(
            layoutInflater.inflate(
                R.layout.item_mainscreen_list,
                parent,
                false
            )
        )
    }
}

class MainPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding: ItemMainscreenListBinding by viewBinding(ItemMainscreenListBinding::bind)

    fun bind(redditPost: RedditPost?) {
        with(binding) {
            redditPost?.let {
                itemMainscreenMessageTextview.text = redditPost.title
                itemMainscreenRatingTextview.text = redditPost.ups.toString()
                itemMainscreenMessageCounterTextview.text = redditPost.numberOfComments.toString()
            }

        }
    }
}

private object RedditPostDiffUtilCallback : DiffUtil.ItemCallback<RedditPost>() {
    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.fullAuthorName == newItem.fullAuthorName &&
                oldItem.title == newItem.title &&
                oldItem.ups == newItem.ups &&
                oldItem.numberOfComments == newItem.numberOfComments
    }

}
