package ru.zinoviewk.dishesapp.presentation.dishes.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import ru.zinoviewk.dishesapp.databinding.DishItemBinding
import ru.zinoviewk.dishesapp.databinding.RemoveAllDishesItemBinding
import ru.zinoviewk.dishesapp.databinding.RemoveSelectedDishesItemBinding

private const val BASE_ITEM = 0
private const val REMOVE_SELECTED_ITEM = 1
private const val REMOVE_ALL_ITEM = 2

class DishesAdapter(
    private val onDishClick: (String) -> Unit,
    private val onDishSelect: (String) -> Unit,
    private val onRemoveSelected: () -> Unit,
    private val onRemoveAll: () -> Unit
) : ListAdapter<DishRecyclerItem, ViewHolder>(Callback()) {

    class DishViewHolder(
        private val binding: DishItemBinding,
        private val onDishClick: (String) -> Unit,
        private val onDishSelect: (String) -> Unit,
    ) : ViewHolder(binding.root) {

        fun bind(dish: DishRecyclerItem) {
            if (dish is DishRecyclerItem.Base) {
                with(binding) {
                    dishTitleTv.text = dish.title
                    dishPriceTv.text = dish.price
                    dishShortDescriptionTv.text = dish.shortDescription
                    removeDishChb.isChecked = dish.isCheckedToDelete

                    loadImage(binding, dish.url)
                    setUpListeners(binding, dish)
                }
            }
        }

        private fun loadImage(binding: DishItemBinding, url: String) {
            Glide
                .with(binding.dishImageTv)
                .load(url)
                .into(binding.dishImageTv)
        }

        private fun setUpListeners(binding: DishItemBinding, dish: DishRecyclerItem.Base) {
            with(binding) {
                root.setOnClickListener { this@DishViewHolder.onDishClick(dish.id) }
                removeDishChb.setOnClickListener { this@DishViewHolder.onDishSelect(dish.id) }
            }
        }
    }

    class RemoveAllViewHolder(
        private val binding: RemoveAllDishesItemBinding,
        private val onRemoveAll: () -> Unit
    ) : ViewHolder(binding.root) {

        fun bind() {
            binding.removeAllBtn.setOnClickListener { onRemoveAll() }
        }
    }

    class RemoveSelectedViewHolder(
        private val binding: RemoveSelectedDishesItemBinding,
        private val onRemoveSelected: () -> Unit
    ) : ViewHolder(binding.root) {

        fun bind(item: DishRecyclerItem) {
            if (item is DishRecyclerItem.RemoveSelected) {
                binding.removeSelectedBtn.isEnabled = item.isEnabled
                binding.removeSelectedBtn.setOnClickListener { onRemoveSelected() }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DishRecyclerItem.Base -> BASE_ITEM
            is DishRecyclerItem.RemoveSelected -> REMOVE_SELECTED_ITEM
            is DishRecyclerItem.RemoveAll -> REMOVE_ALL_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            BASE_ITEM -> DishViewHolder(
                DishItemBinding.inflate(inflater, parent, false),
                onDishClick,
                onDishSelect
            )
            REMOVE_SELECTED_ITEM -> RemoveSelectedViewHolder(
                RemoveSelectedDishesItemBinding.inflate(inflater, parent, false),
                onRemoveSelected
            )
            REMOVE_ALL_ITEM -> RemoveAllViewHolder(
                RemoveAllDishesItemBinding.inflate(inflater, parent, false),
                onRemoveAll
            )
            else -> throw java.lang.IllegalArgumentException("There is no ViewHolder by $viewType type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is DishViewHolder -> holder.bind(item)
            is RemoveSelectedViewHolder -> holder.bind(item)
            is RemoveAllViewHolder -> holder.bind()
        }
    }


    // also checks if the elements' position was changed
    private class Callback : DiffUtil.ItemCallback<DishRecyclerItem>() {
        override fun areItemsTheSame(oldItem: DishRecyclerItem, newItem: DishRecyclerItem) : Boolean {
            return when {
                oldItem is DishRecyclerItem.Base && newItem is DishRecyclerItem.Base -> {
                    oldItem.id == newItem.id
                }
                oldItem is DishRecyclerItem.RemoveSelected && newItem is DishRecyclerItem.RemoveSelected -> {
                    oldItem.isEnabled == newItem.isEnabled
                }
                else -> true
            }
        }

        override fun areContentsTheSame(oldItem: DishRecyclerItem, newItem: DishRecyclerItem): Boolean {
            return if (oldItem is DishRecyclerItem.Base && newItem is DishRecyclerItem.Base) {
                oldItem.isCheckedToDelete == newItem.isCheckedToDelete
            } else true
        }
    }
}
