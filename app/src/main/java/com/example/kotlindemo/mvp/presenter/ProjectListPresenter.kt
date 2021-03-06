package com.example.kotlindemo.mvp.presenter

import com.example.kotlindemo.listener.ModelListener
import com.example.kotlindemo.mvp.contract.ProjectListContract
import com.example.kotlindemo.mvp.model.ProjectListModel
import com.example.kotlindemo.mvp.model.entity.ArticleResponse

class ProjectListPresenter : CommonPresenter<ProjectListContract.IProjectListView>() {

    private var projectListModel: ProjectListModel? = null

    init {
        projectListModel = ProjectListModel()
    }

    fun getProjectListByCid(page: Int, cid: Int) {
        val articles = projectListModel?.getProjectListByCid(page, cid, object : ModelListener<ArticleResponse> {
            override fun onResponse(
                success: Boolean,
                data: ArticleResponse?,
                errorMsg: String?,
                ext: Throwable?
            ) {
                if (success)
                    mView?.showProjectList(data)
                else
                    mView?.showError(errorMsg)
            }
        })
        addSubscription(articles)
    }
}