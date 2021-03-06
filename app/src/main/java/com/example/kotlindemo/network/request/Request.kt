package network.request

import com.example.kotlindemo.mvp.model.entity.*
import io.reactivex.Observable
import network.response.Response
import org.json.JSONObject
import retrofit2.http.*

/**
 * Created by AxeChen on 2018/3/19.
 *
 * 请求接口封装
 */
interface Request {
    companion object {
        var HOST: String = "https://www.wanandroid.com/"
    }

    /**
     * 获取主页文章列表
     */
    @GET("/article/list/{page}/json")
    fun getArticles(
        @Path("page") page: Int
    ): Observable<Response<ArticleResponse>>

    /**
     * 获取首页banner数据
     */
    @GET("banner/json")
    fun getBanner(): Observable<Response<List<Banner>>>

    /**
     * 获取首页置顶文章列表
     */
    @GET("article/top/json")
    fun getTopArticles(): Observable<Response<MutableList<Article>>>


    /**
     * 用户登陆
     */
    @POST("user/login")
    fun userLogin(
        @Query("username") userName: String,
        @Query("password") password: String
    ): Observable<Response<UserInfo>>

    /**
     * 用户注册
     */
    @POST("user/register")
    fun userRegister(
        @Query("username") userName: String,
        @Query("password") password: String,
        @Query("repassword") rePassword: String
    ): Observable<Response<UserInfo>>

    /**
     * 退出登录
     */
    @GET("user/logout/json")
    fun logout(): Observable<Response<Any>>

    /**
     * 获取个人积分，需要登录后访问
     * https://www.wanandroid.com/lg/coin/userinfo/json
     */
    @GET("/lg/coin/userinfo/json")
    fun getUserScoreInfo(): Observable<Response<UserScoreInfo>>

    /**
     * 获取个人积分列表，需要登录后访问
     * https://www.wanandroid.com//lg/coin/list/1/json
     * @param page 页码 从1开始
     */
    @GET("/lg/coin/list/{page}/json")
    fun getUserScoreList(@Path("page") page: Int): Observable<Response<BaseListResponse<ScoreBean>>>

    /**
     * 广场列表数据
     * https://wanandroid.com/user_article/list/0/json
     * @param page 页码拼接在url上从0开始
     */
    @GET("user_article/list/{page}/json")
    fun getSquareList(@Path("page") page: Int): Observable<Response<ArticleResponse>>

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("/wxarticle/chapters/json")
    fun getWXChapters(): Observable<Response<MutableList<WXChapterBean>>>

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page
     * @param cid
     */
    @GET("article/list/{page}/json")
    fun getKnowledgeList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): Observable<Response<ArticleResponse>>


    /**
     * 获取知识树
     */
    @GET("tree/json")
    fun getKnowledgeTreeList(): Observable<Response<List<KnowledgeTree>>>


    /**
     * 获取项目树
     */
    @GET("project/tree/json")
    fun getProjectTree(): Observable<Response<List<ProjectBean>>>


    /**
     * 根据项目分类id获取项目列表
     */
    @GET("project/list/{page}/json")
    fun getProjectListByCid(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): Observable<Response<ArticleResponse>>

    /**
     * 获取热词
     */
    @GET("hotkey/json")
    fun getHotSearchTag(): Observable<Response<MutableList<HotSearchBean>>>

    /**
     * 搜索
     */
    @POST("article/query/{page}/json")
    fun searchByKey(
        @Path("page") page: Int,
        @Query("k") text: String
    ): Observable<Response<ArticleResponse>>

    /**
     * 分享文章
     */
    @POST("lg/user_article/add/json")
    fun shareArticle(
        @Query("title") title: String,
        @Query("link") link: String
    ): Observable<Response<Any>>

    /**
     * 自己的分享的文章列表
     * https://wanandroid.com/user/lg/private_articles/1/json
     * @param page 页码 从1开始
     */
    @GET("user/lg/private_articles/{page}/json")
    fun getShareList(@Path("page") page: Int): Observable<Response<MyShareBean>>

    /**
     * 删除自己分享的文章
     * https://wanandroid.com/lg/user_article/delete/9475/json
     * @param id 文章id，拼接在链接上
     */
    @POST("lg/user_article/delete/{id}/json")
    fun deleteShareArticle(@Path("id") id: Int): Observable<Response<Any>>

    /**
     * 网址导航
     */
    @GET("navi/json")
    fun getNaviJson(): Observable<Response<MutableList<NavBean>>>


    /**
     * 获取收藏的文章列表
     */
    @GET("lg/collect/list/{page}/json")
    fun getCollectArticleList(@Path("page") page: Int): Observable<Response<BaseListResponse<CollectArticle>>>

//    /**
//     * 获取收藏的网站列表
//     */
//    @GET("lg/collect/usertools/json")
//    fun getCollectWebList(): Observable<Response<MutableList<SearchTag>>>


    /**
     * 收藏站内文章
     */
    @POST("lg/collect/{id}/json")
    fun collectInArticle(@Path("id") id: Int): Observable<Response<JSONObject>>


    /**
     * 收藏站外文章
     */
    @POST("lg/collect/add/json")
    fun collectOutArticle(
        @Query("title") title: String,
        @Query("author") author: String,
        @Query("link") link: String
    ): Observable<Response<JSONObject>>

    /**
     * 取消收藏
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun unCollectArticle(@Path("id") id: Int): Observable<Response<JSONObject>>

    /**
     * 收藏列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect/2805/json
     * @param id
     * @param originId
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    fun removeCollectArticle(
        @Path("id") id: Int,
        @Field("originId") originId: Int = -1
    ): Observable<Response<Any>>

    /**
     * 收藏网站
     */
    @POST("lg/collect/addtool/json")
    fun collectWebsite(
        @Query("name") name: String,
        @Query("link") link: String
    ): Observable<Response<JSONObject>>

    /**
     * 取消网站收藏
     */
    @POST("lg/collect/deletetool/json")
    fun unCollectWebsite(@Query("id") id: Int): Observable<Response<JSONObject>>

    /**
     * 编辑收藏的网站
     */
    @POST("lg/collect/updatetool/json")
    fun updateWebsite(
        @Query("id") id: String,
        @Query("name") name: String,
        @Query("link") link: String
    ): Observable<Response<JSONObject>>
}