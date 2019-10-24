package com.lazy.androidjetpackdemo.Paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.lazy.androidjetpackdemo.R
import kotlinx.android.synthetic.main.activity_paging.*
import org.jetbrains.anko.toast

class PagingActivity : AppCompatActivity() {
    private val viewModel by viewModels<CheeseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        // 为RecyclerView创建适配器
        val adapter = CheeseAdapter()
        // 将适配器订阅到ViewModel，以便刷新适配器中的项目
        // 列表更改时
        viewModel.allCheeses.observe(this, Observer (adapter::submitList))
//        viewModel.allCheeses.observe(this, Observer { cheese->
//            adapter.submitList(cheese)
//        })
        cheeseList.adapter = adapter
        swiperefreshlayout.setOnRefreshListener {
//            Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout")
            toast("onRefresh called from SwipeRefreshLayout")
            swiperefreshlayout.isRefreshing=false
        }

        initAddButtonListener()
        initSwipeToDelete()
    }

    //滑动删除
    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            // enable the items to swipe to the left or right
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            // When an item is swiped, remove the item via the view model. The list item will be
            // automatically removed in response, because the adapter is observing the live list.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as CheeseViewHolder).cheese?.let {
                    viewModel.remove(it)
                }
            }
        }).attachToRecyclerView(cheeseList)
    }

    //增加操作
    private fun addCheese() {
        val newCheese = inputText.text.trim()
        if (newCheese.isNotEmpty()) {
            viewModel.insert(newCheese)
            inputText.setText("")
        }
    }

    //初始化
    private fun initAddButtonListener() {
        addButton.setOnClickListener {
            addCheese()
        }

        // when the user taps the "Done" button in the on screen keyboard, save the item.
        //当用户点击屏幕键盘上的“完成”按钮时，保存该项目
        inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addCheese()
                return@setOnEditorActionListener true
            }
            false // action that isn't DONE occurred - ignore
        }
        // When the user clicks on the button, or presses enter, save the item.
        inputText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addCheese()
                return@setOnKeyListener true
            }
            false // event that isn't DOWN or ENTER occurred - ignore
        }
    }
}