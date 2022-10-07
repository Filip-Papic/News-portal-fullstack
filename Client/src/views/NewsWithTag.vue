<template>
    <div id="home">
        <Banner :title="'Tag: ' + this.$route.params.tag" />
        <NewsTable :news="news" :userID="user.id" @deleteNews="deleteNews"/>
        <Pagination :perPage="perPage" :news="news.length" :currentPage="currentPage" :pageName="this.$options.name" :search="''"/>
    </div>
</template>
  
<script>
    import Banner from '../components/Banner.vue'
    import NewsTable from '../components/NewsCards.vue'
    import Pagination from '../components/Pagination.vue'
    import { useAuthStore } from '@/store'
    import { useNewsStore } from '@/store'
    import { storeToRefs } from 'pinia'

    export default {
        name: "NewsWithTag",
        components: {
            Banner,
            Pagination,
            NewsTable
        },
        data() {
            return {
                currentPage: 1,
                perPage: 5
            }
        },
        setup() {
            const { news } = storeToRefs(useNewsStore())
            const { user } = storeToRefs(useAuthStore())

            return {
                news,
                user
            }
        },
        methods: {
            deleteNews(id) {
				if (confirm('Are you sure?')) {
                    useNewsStore().deleteNews(id);
                }
            }
        },
        created() {
            this.currentPage = this.$route.query.page != null ? this.$route.query.page : 1; 
            useNewsStore().getNewsWithTag(this.$route.params.id, this.currentPage, this.perPage)
        },
        watch: {
            '$route.query.page': function() {
                this.currentPage = this.$route.query.page != null ? this.$route.query.page : 1;
                useNewsStore().getNewsWithTag(this.$route.params.id, this.currentPage, this.perPage)
            }
        }
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
    }
</style>
  