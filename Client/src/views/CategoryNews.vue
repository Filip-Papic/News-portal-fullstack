<template>
    <div id="home">
        <Banner title="No news found" v-if="news.length === 0" />
        <Banner :title="catName" v-else />
        <NewsTable :news="news" :userID="user.id" @deleteNews="deleteNews" />
        <Pagination :perPage="perPage" :news="news.length" :currentPage="currentPage" :pageName="this.$options.name" :search="''" />
    </div>
</template>

<script>
    import Banner from "@/components/Banner";
    import NewsTable from '../components/NewsCards.vue'
    import Pagination from '../components/Pagination.vue'
    import { useAuthStore } from '@/store';
    import { useNewsStore } from '@/store';
    import { useCategoriesStore } from '@/store';
	import { storeToRefs } from 'pinia';

    export default {
        name: "CategoryNews",
        components: {
            Banner,
            Pagination,
            NewsTable
        },
        data() {
            return {
                catName: '',
                currentPage: 1,
                perPage: 5
            }
        },
        setup() {
            const { user } = storeToRefs(useAuthStore());
            const { news } = storeToRefs(useNewsStore());
            const { category } = storeToRefs(useCategoriesStore());

            return {
                user,
                news,
                category
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
            const catID = this.$route.params.id;
            useNewsStore().getNewsByCategory(catID, this.currentPage, this.perPage);
            useCategoriesStore().getCategoryById(catID);
        },
        watch: {
            '$route.query.page': function() {
                this.currentPage = this.$route.query.page;
                const catID = this.$route.params.id;
                useNewsStore().getNewsByCategory(catID, this.currentPage, this.perPage);
            },
            category: function() {
                this.catName = this.category.categoryName;
            }
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
    }
</style>
