<template>
    <div id="home" >
        <b-container fluid >
            <b-row style="margin-top: -3.5rem;  align-items: center; margin-bottom: 0.3rem; ">
                <b-col cols="10" style="display: flex; align-items: left;">
                    Popular:
                    <b-badge 
                        v-for="tag in popularTags.slice(0, 5)" 
                        :key="tag.id"
                        @click="getNewsWithTag(tag)"
                        class="bg-success">
                        {{tag.name}}
                    </b-badge>
                </b-col>
                <b-col>
                    <button @click="addNews()" class="btn btn-secondary addNews">
                        <span v-if="userID === 0">Columnist? Sign up to write articles</span>
                        <span v-else>Write an article</span>
                    </button>
                </b-col>
            </b-row>
        </b-container>
        <div v-for="article in news" :key="article.id"> 
            <b-card no-body class="overflow-hidden cardSize" style="max-width: 100%; margin-bottom: 1rem;">    
                <div @click="singleNews(article.id)" style="cursor: pointer; width: 100%"> 
                    <b-row no-gutters>
                        <b-col md="6">
                            <b-card-img :src=getUrl(article.id) :alt=article.title class="rounded-0"></b-card-img>
                        </b-col>
                        <b-col md="6">
                            <b-card-body :title=article.title >
                                <b-card-text>
                                    <small>{{ moment(article.creationTime) }}</small>
                                </b-card-text>
                                <b-card-text>
                                    {{ article.content.substring(0,50) }}...
                                </b-card-text>
                            </b-card-body>
                        </b-col>
                    </b-row>
                </div>
                <div class="editdelete">
                    <a v-if="userID === article.author || userID === 1" 
                        @click="editNews(article)">
                        <small>Edit | </small></a>
                    <a v-if="userID === article.author || userID === 1" 
                        @click="deleteNews(article)">
                        <small>Delete </small></a>
                </div>
            </b-card>
        </div>
    </div>
</template>
  
<script>
    import moment from 'moment'
    import { useTagsStore } from '../store/modules/tags';
    import { storeToRefs } from 'pinia';

    export default {
        name: "NewsTable",
        props: {
            news: {
                type: Array,
                required: true
            },
            userID: {
                type: Number,
                required: true
            }
        },
        setup() {
            const tagsStore = useTagsStore();
            const { popularTags } = storeToRefs(tagsStore);

            return {
                popularTags
            }
        },
        methods: {
            addNews() {
                this.$router.push({ name: 'AddNews' })
            },
            singleNews(id) {
                let routeData = this.$router.resolve({name: 'SingleNews', params: {id: id}});
                window.open(routeData.href, '_blank');
            },
            editNews(article) {
                this.$router.push({ name: 'EditNews', params: { id: article.id, catID: article.categoryID } })
            },
            deleteNews(article) {
                this.$emit('deleteNews', article.id);
            },
            getUrl(id) {
                return 'https://picsum.photos/600/300/?image=' + id;
            },
            getNewsWithTag(tag) {
                this.$router.push({name: 'NewsWithTag', params: {id: tag.id, tag: tag.name}, query: {page: 1}})
            },
            reload() {
                this.$router.go(0);
            },
            moment(date) {
                return moment(date).fromNow()
            }
        },
        created() {
            useTagsStore().getPopularTags();
        },
        watch: {
            '$route': 'reload'
        }
    }
</script>

<style>
    
    .card {
        max-height: 25rem;
    }

    .card-title {
        font-weight: bold;
        font-size: 1.8rem;
        margin-top: 1rem;
    }

    .card-text {
        font-size: 1.2rem;
    }

    .card-img:hover {
        opacity: 0.5;
    }

    .addNews {
        float: right; 
        padding: 0.5rem 1rem; 
        margin: 0.5rem 0.5rem 0.5rem 0.5rem;
    }

    .editdelete {
        bottom: 0; 
        right: 0; 
        position: absolute; 
        margin-top: 0.5rem; 
        margin-right: 0.5rem;
        cursor: pointer; 
        color: #000000
    }

</style>