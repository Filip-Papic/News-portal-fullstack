<template>
  <div id="home">
    <Banner title="Home" />
    <NewsCard
      :news="news"
      :userID="user.id"
      @deleteNews="deleteNews"
      @editNews="editNews"
    />
  </div>
</template>

<script>
import Banner from "@/components/Banner";
import NewsCard from "../components/NewsCards.vue";
import { useAuthStore } from "@/store";
import { useNewsStore } from "@/store";
import { storeToRefs } from "pinia";

export default {
  name: "Home",
  components: {
    Banner,
    NewsCard,
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
    editNews(article) {
      this.$router.push({
        name: "EditNews",
        params: { id: article.id, catID: article.categoryID },
      });
    },
    deleteNews(id) {
      if (confirm("Are you sure?")) {
        useNewsStore().deleteNews(id);
      }
    },
  },
  created() {
    useNewsStore().getNewNews();
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
