package br.com.brunobrito.covidhack.platform.device

import br.com.brunobrito.covidhack.KogitApplication

class DeviceProperties {
    companion object {
        fun getStatusBarHeight(): Int {
            var result = 0
            val resourceId =
                KogitApplication.getContext().resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = KogitApplication.getContext().resources.getDimensionPixelSize(resourceId)
            }
            return result
        }

        fun getNavbarHeight(): Int {
            val resources = KogitApplication.getContext().resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            return if (resourceId > 0) {
                KogitApplication.getContext().resources.getDimensionPixelSize(resourceId)
            } else 0
        }
    }
}