package com.sp4a.wanandroid

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.header.StoreHouseHeader
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sp4a.R
import com.sp4a.manager.BaseActivity
import com.sp4a.manager.MyLoadMoreView
import com.sp4a.service.subscriber.DefaultHttpResultSubscriber
import com.sp4a.util.HtmlUtils
import com.sp4a.util.startBrowserActivity
import com.sp4a.wanandroid.model.bean.CollectItem
import com.sp4a.wanandroid.model.bean.CollectList
import com.sp4a.wanandroid.model.fetchCollectionList
import kotlinx.android.synthetic.main.activity_my_collect.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MyCollectActivity : BaseActivity() {
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    var mPageIndex: Int = 0
    private lateinit var mCollectAdapter: BaseQuickAdapter<CollectItem, BaseViewHolder>
    private var mPageCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_collect)
        setLeftTitleAndDisplayHomeAsUp("ζηζΆθ")
        initPtrFrame()
        initRecyclerView()
        fetchCollectListFromServer(mPageIndex)
    }

    private fun initPtrFrame() {
        initHeadView()
        ptrFrameCollect.disableWhenHorizontalMove(true)
        ptrFrameCollect.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                pullDownToRefresh()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return recyclerViewFirstItemCanVisible()
            }
        })
    }

    private fun recyclerViewFirstItemCanVisible() =
            mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0

    private fun initHeadView() {
        val header = StoreHouseHeader(this)
        header.setTextColor(resources.getColor(R.color.colorTxtSelected))
        header.setPadding(0, dp2px(15f), 0, 0)
        header.initWithString("Collect", 15)
        ptrFrameCollect.headerView = header
        ptrFrameCollect.addPtrUIHandler(header)
    }

    private fun pullDownToRefresh() {
        mPageIndex = 0
        fetchCollectListFromServer(mPageIndex)
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mCollectAdapter = object : BaseQuickAdapter<CollectItem, BaseViewHolder>(R.layout.item_collect, null) {
            override fun convert(helper: BaseViewHolder?, item: CollectItem?) {
                item?.also {
                    helper?.setText(R.id.tv_title, HtmlUtils.translation(it.title))
                            ?.setText(R.id.tv_author, "δ½θ : " + it.author)
                            ?.setText(R.id.tv_category, "εη±» : " + it.chapterName)
                            ?.setText(R.id.tv_niceDate, it.niceDate)
                            ?.addOnClickListener(R.id.rl_collect_item)
                }
            }
        }
        mCollectAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.rl_collect_item -> {
                    val recommendItem = adapter.getItem(position) as CollectItem
                    startBrowserActivity(this@MyCollectActivity, recommendItem.link, recommendItem.title)
                }
            }
        }
        mCollectAdapter.setOnLoadMoreListener({
            if (mPageIndex + 1 == mPageCount) {
                mCollectAdapter.loadMoreEnd()
            } else {
                fetchCollectListFromServer(++mPageIndex)
            }
        }, recyclerViewCollect)
        mCollectAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mCollectAdapter.setLoadMoreView(MyLoadMoreView())

        recyclerViewCollect.layoutManager = mLinearLayoutManager
        recyclerViewCollect.adapter = mCollectAdapter
    }

    private fun fetchCollectListFromServer(pageIndex: Int) {
        fetchCollectionList(mPageIndex).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<CollectList>() {
                    override fun onSuccess(data: CollectList?) {
                        data?.also {
                            renderRecommendList(pageIndex, it)
                            if (mPageIndex == 0 && it.datas.size < it.size) {
                                mCollectAdapter.loadMoreEnd()
                            } else if (mPageIndex > 0) {
                                mCollectAdapter.loadMoreComplete()
                            }
                        }
                    }

                    override fun onCompleted() {
                        ptrFrameCollect?.refreshComplete()
                    }
                })
    }

    private fun renderRecommendList(witchPage: Int, data: CollectList) {
        if (witchPage == 0) {
            mCollectAdapter.setNewData(data.datas)
        } else {
            mPageCount = data.pageCount
            mCollectAdapter.addData(data.datas)
        }
    }
}
