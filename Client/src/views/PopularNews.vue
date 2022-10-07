<template>
    <div id="home">
        <Banner title="Popular" />
        <NewsTable :news="news" :title="'Popular articles:'" :userID="user.id" @deleteNews="deleteNews" :search="''" />
    </div>
  </template>
  
<script>
    import Banner from "@/components/Banner";
    import NewsTable from '../components/NewsCards.vue';
    import { useAuthStore } from '@/store';
    import { useNewsStore } from '@/store';
    import { storeToRefs } from 'pinia';

    export default {
        name: 'PopularNews',
        components: {
            Banner,
            NewsTable 
        },
        setup() {
            const { news } = storeToRefs(useNewsStore());
            const { user } = storeToRefs(useAuthStore());

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
            useNewsStore().getPopularNews();
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
  