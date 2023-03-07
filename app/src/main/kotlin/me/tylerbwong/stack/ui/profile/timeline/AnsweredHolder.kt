package me.tylerbwong.stack.ui.profile.timeline

import android.net.Uri
import android.view.ViewGroup
import dagger.hilt.android.EntryPointAccessors
import me.tylerbwong.adapter.viewbinding.DynamicViewBindingHolder
import me.tylerbwong.stack.data.DeepLinkResult
import me.tylerbwong.stack.data.DeepLinkerEntryPoint
import me.tylerbwong.stack.databinding.TimelineHolderBinding
import me.tylerbwong.stack.ui.utils.setThrottledOnClickListener
import me.tylerbwong.stack.ui.utils.toHtml

class AnsweredHolder(
    container: ViewGroup
) : DynamicViewBindingHolder<AnsweredItem, TimelineHolderBinding>(container, TimelineHolderBinding::inflate) {
    override fun TimelineHolderBinding.bind(item: AnsweredItem) {
        content.setLatex(item.event.title?.toHtml().toString())
        itemView.setThrottledOnClickListener {
            val deepLinker = EntryPointAccessors.fromApplication<DeepLinkerEntryPoint>(
                it.context.applicationContext
            ).deepLinker
            val result = deepLinker.resolvePath(it.context, Uri.parse(item.event.link))
            if (result is DeepLinkResult.Success) {
                it.context.startActivity(result.intent)
            }
        }
    }
}
