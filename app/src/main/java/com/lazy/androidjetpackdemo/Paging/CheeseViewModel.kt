package com.lazy.androidjetpackdemo.Paging

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData

/**
 * A simple ViewModel that provides a paged list of delicious Cheeses.
 */
class CheeseViewModel(app: Application) : AndroidViewModel(app) {
    val dao = CheeseDb.get(app).cheeseDao()
    /**
     * We use -ktx Kotlin extension functions here, otherwise you would use LivePagedListBuilder(),
     * and PagedList.Config.Builder()
     * 我们在这里使用-ktx Kotlin扩展函数，否则您将使用LivePagedListBuilder（），和PagedList.Config.Builder（）
     */
    val allCheeses = dao.allCheesesByName().toLiveData(Config(
        /**
         * A good page size is a value that fills at least a screen worth of content on a large
         * device so the User is unlikely to see a null item.
         * You can play with this constant to observe the paging behavior.
         * <p>
         * It's possible to vary this with list device size, but often unnecessary, unless a
         * user scrolling on a large device is expected to scroll through items more quickly
         * than a small device, such as when the large device uses a grid layout of items.
         * 一个好的页面大小是一个值，它在一个大的设备，因此用户不太可能看到空项。
         * 您可以使用这个常量来观察分页行为。
         * 可以根据列表设备的大小更改此值，但通常是不必要的，除非用户在大型设备上滚动可以更快地滚动项目
         * 而不是小设备，比如当大设备使用项目的网格布局时。
         */
        pageSize = 10,
        /**
         * If placeholders are enabled, PagedList will report the full size but some items might
         * 如果启用了占位符，则PagedList将报告完整大小，但某些项目可能会
         * be null in onBind method (PagedListAdapter triggers a rebind when data is loaded).
         * 在onBind方法中为null（加载数据时，PagedListAdapter触发重新绑定）。
         * <p>
         * If placeholders are disabled, onBind will never receive null but as more pages are
         * 如果禁用了占位符，则onBind将永远不会收到null，但随着更多页面的出现，
         * loaded, the scrollbars will jitter as new pages are loaded. You should probably
         * 加载后，滚动条将在加载新页面时抖动。你可能应该
         * disable scrollbars if you disable placeholders.
         * 如果禁用占位符，则禁用滚动条。
         */
        enablePlaceholders = true,
        prefetchDistance = 20,
        /**
         * Maximum number of items a PagedList should hold in memory at once.
         * PagedList一次可容纳在内存中的最大项目数。
         * <p>
         * This number triggers the PagedList to start dropping distant pages as more are loaded.
         * 当加载更多页面时，此数字将触发PagedList开始删除远处的页面。
         */
        maxSize = 200))

    fun insert(text: CharSequence) = ioThread {
        dao.insert(Cheese(id = 0, name = text.toString()))
    }

    fun remove(cheese: Cheese) = ioThread {
        dao.delete(cheese)
    }
}