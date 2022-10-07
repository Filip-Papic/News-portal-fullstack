<template>
    <div id="home"> 
        <Banner :title="newsArticle.title" :image="newsArticle.id"/>
        <br>
        <div style="margin-left: auto; margin-right: 0;">
            <small>Author: {{newsArticle.authorName}}</small>
            <p><small>{{new Date(newsArticle.creationTime).toLocaleString().split(',')[0] }}, Views: {{newsArticle.views}}</small></p>
            <p>Tags: <small style="color:blue" v-for="tag in tagsByNewsId" :key="tag.id">
                <a @click="getNewsWithTag(tag)"><strong>{{tag.name}}&nbsp;</strong></a>
            </small>
            </p>
        </div>
        <hr>
        <b-button v-if="user.id === newsArticle.author || user.id === 1" class="btn btn-primary" @click="editNews(newsArticle)">Edit</b-button> |
        <b-button v-if="user.id === newsArticle.author || user.id === 1" class="btn btn-secondary" @click="deleteNews(newsArticle)">Delete</b-button> 
        <br><br>
        <p class="content">{{ newsArticle.content }}</p>
        <br>
        <a class="btn btn-success fa-solid fa-thumbs-up" @click="likeNews()"> {{ newsArticle.likes }} </a>
        <a class="btn btn-danger fa-solid fa-thumbs-down" @click="dislikeNews()"> {{ newsArticle.dislikes }} </a>
        <br><br>
        <div>
            <b-button v-b-toggle.sidebar-right variant="info">Articles with similar tags</b-button>
            <b-sidebar id="sidebar-right" title="Similar articles" right shadow>
                <div class="px-3 py-2" v-for="article in similarNews" :key="article.id">
                    <b-card
                        :title=article.title
                        @click="singleNews(article.id)"
                        :img-src=getUrl(article.id)
                        img-top
                        style="max-width: 20rem; cursor: pointer;"
                        class="mb-2">
                        <b-card-text>
                            {{ article.content.substring(0,20) }}...
                        </b-card-text>
                    </b-card>
                </div>
            </b-sidebar>
        </div>
        <hr>
        <section class="content-item" id="comments">
            <div class="container">   
                <div class="row">
                    <div class="col-sm-8">
                        <form>
                            <h3 class="pull-left">New Comment </h3>
                            <div class="coment-bottom bg-white">
                                <form class="d-flex flex-row add-comment-section mt-4 mb-4" @submit.prevent="addComment">
                                    <img class="img-fluid img-responsive rounded-circle mr-2" src="@/assets/avatar.png" width="38">
                                    <input v-model="content" type="text" class="form-control mr-3" placeholder="Add comment">
                                    <button class="btn btn-primary" type="submit">Comment</button>
                                </form>
                            </div>
                            <br>
                        </form>
                        <hr>
                        <h3 v-if="comments.length === 1">{{comments.length}} Comment: </h3>
                        <h3 v-else>{{comments.length}} Comments: </h3>
                        <br><br>
                        <div v-for="comment in comments" :key="comment.id" id="comments">
                            <div class="media comment">
                                <a class="pull-left">
                                    <img class="media-object avatar" src="@/assets/avatar.png" alt="">
                                </a>
                                <h4 class="media-heading" style="padding-right: 5px;">{{comment.author}}</h4>
                                <ul class="list-unstyled list-inline media-detail pull-left">
                                    <li><i class="fa fa-calendar"></i>{{new Date(comment.createdDate).toLocaleString().split(',')[0]}}</li>
                                </ul>
                                <p>{{comment.content}}</p>
                                <ul class="list-unstyled list-inline media-detail pull-right">
                                    
                                </ul>
                                <a class="btn btn-success fa-solid fa-thumbs-up" @click="likeComment(comment)"> {{comment.likes}}</a>
                                <a class="btn btn-danger fa-solid fa-thumbs-down" @click="dislikeComment(comment)"> {{comment.dislikes}}</a>
                                <a class="btn btn-warning" 
                                    v-if="comment.author !== 'Anonymous' & comment.author === user.name || user.id === 1" 
                                    @click="deleteComment(comment)"> Delete
                                </a>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>
  
<script>
    import Banner from '@/components/Banner'
    import { useAuthStore } from '../store/modules/auth'
    import { useNewsStore } from '../store/modules/news'
    import { useTagsStore } from '../store/modules/tags'
    import { useCommentsStore } from '../store/modules/comments'
    import { storeToRefs } from 'pinia'

    export default {
        name: "SingleNews",
        components: {
            Banner
        },
        setup() {
            const { user } = storeToRefs(useAuthStore())
            const { newsArticle } = storeToRefs(useNewsStore())
            const { tagsByNewsId } = storeToRefs(useTagsStore())
            const { similarNews } = storeToRefs(useNewsStore());
            const { comments } = storeToRefs(useCommentsStore())

            return {
                user,
                newsArticle,
                tagsByNewsId,
                similarNews,
                comments
            }
        },
        data() {
            return {
                content: '',
            }
        },
        methods: {
            getNewsWithTag(tag) {
                this.$router.push({name: 'NewsWithTag', params: {id: tag.id, tag: tag.name}, query: {page: 1}})
            },
            editNews(article) {
                this.$router.push({name: 'EditNews', params: {id: article.id, catID: article.categoryID}})
            },
            deleteNews(article) {
                if (confirm('Are you sure you want to delete this article?')) {
                    useNewsStore().deleteNews(article.id)
                    this.$router.push({name: 'News'})
                }
            },
            singleNews(id) {
                let routeData = this.$router.resolve({name: 'SingleNews', params: {id: id}});
                window.open(routeData.href, '_blank');
            },
            addComment() {
                const comment = {
                    newsID: this.$route.params.id,
                    content: this.content,
                    author: this.user.name === null ? 'Anonymous' : this.user.name
                }
                useCommentsStore().addComment(comment)
            },
            deleteComment(comment) {
                useCommentsStore().deleteComment(comment.id)
            },
            likeNews() {
                useNewsStore().updateNewsLikes(this.$route.params.id)
            },
            dislikeNews() {
                useNewsStore().updateNewsDislikes(this.$route.params.id)
            },
            likeComment(comment) {
                useCommentsStore().updateCommentLikes(comment.id)
            },
            dislikeComment(comment) {
                useCommentsStore().updateCommentDislikes(comment.id)
            },
            getUrl(id) {
                return 'https://picsum.photos/600/300/?image=' + id;
            }
        },
        created() {
            useNewsStore().updateNewsViews(this.$route.params.id)
            useNewsStore().getNewsById(this.$route.params.id) 
            useTagsStore().getTagsByNewsId(this.$route.params.id)
            useNewsStore().getSimilarNews(this.$route.params.id, 1, 10);
            useCommentsStore().getCommentsByNewsId(this.$route.params.id)
        },
    }
</script>
  
<style scoped>

    #home {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
        background-color: #F5F5F5;
    }
    .avatar {
        padding-right: 5px; 
        padding-top: 5px; 
        float:left; height:40px; 
        width: 40px;
    }

    .content {
        text-align: justify; 
        text-justify: inter-word; 
        white-space: pre-line; 
        font-size: 1.2rem; 
        line-height: 1.7rem;
        font-weight: 400; 
        color: #212529; 
        margin-bottom: 1rem; 
        margin-top: 1rem; 
        margin-left: auto; 
        margin-right: auto;
        width: 70%;
    }

    #comments {
        text-align: justify; 
        text-justify: inter-word; 
        white-space: pre-line; 
        font-size: 1.2rem; 
        line-height: 1.5rem;
        font-weight: 400; 
        color: #212529; 
        margin-bottom: 1rem; 
        margin-left: auto; 
        margin-right: auto;
        width: 90%;
    }

    .comment {
        background-color: #ede7e7;
        padding: 10px;
        border-radius: 10px;
    }

</style>
  