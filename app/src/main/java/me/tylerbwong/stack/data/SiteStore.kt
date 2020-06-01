package me.tylerbwong.stack.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.tylerbwong.stack.data.network.service.DEFAULT_SITE
import me.tylerbwong.stack.ui.ApplicationWrapper

object SiteStore {

    private const val SITE_PREFERENCES = "site_preferences"
    private const val SITE_KEY = "site"

    private val preferences = ApplicationWrapper.context.getSharedPreferences(
        SITE_PREFERENCES,
        Context.MODE_PRIVATE
    )

    var site: String
        get() = deepLinkSite ?: preferences.getString(SITE_KEY, DEFAULT_SITE) ?: DEFAULT_SITE
        set(value) {
            preferences.edit().putString(SITE_KEY, value).apply()
            mutableSiteLiveData.value = value
        }

    /**
     * Used to determine the current site for the deep link session only.
     */
    internal var deepLinkSite: String? = null
        set(value) {
            if (value != site) field = value
        }

    private val mutableSiteLiveData = MutableLiveData(site)

    val siteLiveData: LiveData<String> = mutableSiteLiveData
}
