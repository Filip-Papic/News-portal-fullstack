import { defineStore } from "pinia";
import * as api from "@/api";
import router from "../../router";

export const useTagsStore = defineStore({
  id: "tags",
  state: () => ({
    tags: [],
    tagsByNewsId: [],
    popularTags: [],
  }),
  actions: {
    getTags() {
      api
        .getTags()
        .then((response) => {
          this.tags = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getTagsByNewsId(id) {
      api
        .getTagsByNewsId(id)
        .then((response) => {
          this.tagsByNewsId = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getPopularTags() {
      api
        .getPopularTags()
        .then((response) => {
          this.popularTags = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addTag(name, newsID) {
      api
        .addTag({ name })
        .then((response) => {
          console.log("tags " + response);
          this.tags.push(response.data);
          api.addTagToNews(newsID, response.data.id);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    removeTag(name, newsID) {
      api.getTagByName(name).then((response) => {
        api
          .removeTagFromNews(newsID, response.data.id)
          .then((response) => {
            console.log("Tag removed: " + response.data.name);
          })
          .catch((error) => {
            router.go();
            console.log(error);
          });
      });
    },
  },
});
