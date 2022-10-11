import { defineStore } from "pinia";
import * as api from "@/api";
import { useTagsStore } from "./tags";

export const useNewsStore = defineStore({
  id: "news",
  state: () => ({
    news: [],
    newsArticle: {},
    similarNews: [],
    viewedNews: [],
    likedNews: [],
    dislikedNews: [],
  }),
  persist: {
    storage: localStorage,
    paths: ["viewedNews", "likedNews", "dislikedNews"],
  },
  actions: {
    getNews(currentPage, perPage) {
      api
        .getNews(currentPage, perPage)
        .then((response) => {
          this.news = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getNewsById(id) {
      api
        .getNewsById(id)
        .then((response) => {
          this.newsArticle = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getNewsByCategory(categoryID, currentPage, perPage) {
      api
        .getNewsByCategory(categoryID, currentPage, perPage)
        .then((response) => {
          this.news = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getNewsWithTag(tagID, currentPage, perPage) {
      api
        .getNewsWithTag(tagID, currentPage, perPage)
        .then((response) => {
          this.news = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getNewNews() {
      api
        .getNewNews()
        .then((response) => {
          this.news = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getPopularNews() {
      api
        .getPopularNews()
        .then((response) => {
          this.news = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getSimilarNews(id, currentPage, perPage) {
      api
        .getSimilarNews(id, currentPage, perPage)
        .then((response) => {
          this.similarNews = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    searchNews(search, currentPage, perPage) {
      api
        .searchNews(search, currentPage, perPage)
        .then((response) => {
          this.news = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addNews(news, tags) {
      api
        .createNews(news)
        .then((response) => {
          console.log("Article added: " + response.data.title);
          this.news.push(response.data);
          tags.forEach((tag) => {
            console.log("Tag: " + JSON.stringify(tag));
            useTagsStore().addTag(tag, response.data.id);
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateNews(article) {
      api
        .updateNews(article.id, article)
        .then((response) => {
          console.log("Article updated: " + response.data.title);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateNewsViews(id) {
      if (this.viewedNews.includes(id)) {
        return;
      }
      api
        .updateNewsViews(id)
        .then((response) => {
          this.viewedNews.push(id);
          this.newsArticle = response.data;
          console.log("Article views updated: " + response.data.title);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateNewsLikes(id) {
      if (!this.likedNews.includes(id) && !this.dislikedNews.includes(id)) {
        api
          .updateNewsLikes(id)
          .then((response) => {
            this.likedNews.push(id);
            this.newsArticle = response.data;
            console.log("Article liked: " + response.data.title);
          })
          .catch((error) => {
            console.log(error);
          });
      } else if (
        !this.likedNews.includes(id) &&
        this.dislikedNews.includes(id)
      ) {
        api
          .likeAfterDislike(id)
          .then((response) => {
            this.likedNews.push(id);
            this.dislikedNews = this.dislikedNews.filter((item) => item !== id);
            this.newsArticle = response.data;
            console.log("Article liked: " + response.data.title);
          })
          .catch((error) => {
            console.log(error);
          });
      } else if (
        this.likedNews.includes(id) &&
        !this.dislikedNews.includes(id)
      ) {
        api
          .unlike(id)
          .then((response) => {
            this.likedNews.splice(this.likedNews.indexOf(id), 1);
            this.newsArticle = response.data;
            console.log("Article unliked: " + response.data.title);
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        console.log("Error");
      }
    },
    updateNewsDislikes(id) {
      if (!this.likedNews.includes(id) && !this.dislikedNews.includes(id)) {
        api
          .updateNewsDislikes(id)
          .then((response) => {
            this.dislikedNews.push(id);
            this.newsArticle = response.data;
            console.log("Article disliked: " + response.data.title);
          })
          .catch((error) => {
            console.log(error);
          });
      } else if (
        !this.likedNews.includes(id) &&
        this.dislikedNews.includes(id)
      ) {
        api
          .undislike(id)
          .then((response) => {
            this.dislikedNews.splice(this.dislikedNews.indexOf(id), 1);
            this.newsArticle = response.data;
            console.log("Article unliked: " + response.data.title);
          })
          .catch((error) => {
            console.log(error);
          });
      } else if (
        this.likedNews.includes(id) &&
        !this.dislikedNews.includes(id)
      ) {
        api
          .dislikeAfterLike(id)
          .then((response) => {
            this.dislikedNews.push(id);
            this.likedNews = this.likedNews.filter((item) => item !== id);
            this.newsArticle = response.data;
            console.log("Article disliked: " + response.data.title);
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        console.log("Error");
      }
    },
    deleteNews(id) {
      api
        .deleteNews(id)
        .then((this.news = this.news.filter((article) => article.id !== id)))
        .catch((error) => {
          console.log(error);
        });
    },
  },
});
