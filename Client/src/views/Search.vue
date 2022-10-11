<template>
  <div id="home">
    <Banner :title="'Results: ' + this.$route.query.q" />
    <NewsTable :news="news" :userID="user.id" @deleteNews="deleteNews" />
    <Pagination
      :perPage="perPage"
      :news="news.length"
      :currentPage="currentPage"
      :pageName="this.$options.name"
      :search="this.$route.query.q"
    />
  </div>
</template>

<script>
import Banner from "../components/Banner.vue";
import NewsTable from "../components/NewsCards.vue";
import Pagination from "../components/Pagination.vue";
import { useAuthStore } from "../store/modules/auth";
import { useNewsStore } from "../store/modules/news";
import { storeToRefs } from "pinia";

export default {
  name: "Search",
  components: {
    Banner,
    NewsTable,
    Pagination,
  },
  data() {
    return {
      currentPage: 1,
      perPage: 5,
    };
  },
  setup() {
    const { user } = storeToRefs(useAuthStore());
    const { news } = storeToRefs(useNewsStore());

    return {
      user,
      news,
    };
  },
  methods: {
    deleteNews(id) {
      if (confirm("Are you sure?")) {
        useNewsStore().deleteNews(id);
      }
    },
  },
  created() {
    this.currentPage =
      this.$route.query.page != null ? this.$route.query.page : 1;
    this.search = this.$route.query.search;
    useNewsStore().searchNews(
      this.$route.query.q,
      this.currentPage,
      this.perPage
    );
  },
  watch: {
    "$route.query.page": function () {
      this.currentPage = this.$route.query.page;
      useNewsStore().searchNews(
        this.$route.query.q,
        this.currentPage,
        this.perPage
      );
    },
    "$route.query.q": function () {
      this.currentPage =
        this.$route.query.page != null ? this.$route.query.page : 1;
      useNewsStore().searchNews(
        this.$route.query.q,
        this.currentPage,
        this.perPage
      );
    },
  },
};
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
