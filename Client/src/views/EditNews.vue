<template>
  <div id="home">
    <Banner :title="'Edit: ' + newsArticle.title" />
    <b-form @submit.prevent="editNewsAndTags" id="edit-news-form">
      <b-form-group
        id="input-group-1"
        label="Title:"
        label-for="input-1"
        description="Enter title of the news article"
      >
        <b-form-input
          id="input-1"
          v-model="newsArticle.title"
          required
          placeholder="Enter title"
        >
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-group-2"
        label="Content:"
        label-for="input-2"
        description="Enter content of the news article"
      >
        <b-form-textarea
          id="input-2"
          v-model="newsArticle.content"
          required
          placeholder="Enter content"
          rows="5"
        >
        </b-form-textarea>
      </b-form-group>
      <label for="tags-basic">Type a new tag and add it</label>
      <b-form-tags
        input-id="tags-basic"
        remove-on-delete
        @remove="removeTag()"
        v-model="newTags"
        no-add-on-enter
        :limit="tagLimit"
        tag-class="bg-success"
      >
      </b-form-tags>
      <label for="input-3">Or select some of the existing tags</label>
      <b-form-group
        style="margin-top: 0rem"
        id="input-group-3"
        label-for="input-3"
      >
        <b-form-select
          id="input-3"
          v-model="selectedTag"
          @change="addTag()"
          :options="tags"
          value-field="name"
          text-field="name"
          class="mb-3"
        >
        </b-form-select>
      </b-form-group>
      <b-form-group
        id="input-group-4"
        label="Select category:"
        label-for="input-4"
        description="Please select category of the news article."
      >
        <b-form-select
          id="input-4"
          v-model="newsArticle.categoryID"
          :options="categories"
          value-field="id"
          text-field="categoryName"
          required
        >
        </b-form-select>
      </b-form-group>
      <b-button type="submit" variant="primary">Edit</b-button>
    </b-form>
    <br /><br />
  </div>
</template>

<script>
import Banner from "../components/Banner";
import { useAuthStore } from "@/store";
import { useNewsStore } from "@/store";
import { useCategoriesStore } from "@/store";
import { useTagsStore } from "@/store";
import { storeToRefs } from "pinia";

export default {
  name: "EditNews",
  components: {
    Banner,
  },
  data() {
    return {
      selectedTag: null,
      newTags: this.tagsByNewsId.map((tag) => tag.name),
      tagLimit: 5,
    };
  },
  setup() {
    const { user } = storeToRefs(useAuthStore());
    const { newsArticle } = storeToRefs(useNewsStore());
    const { categories, category } = storeToRefs(useCategoriesStore());
    const { tags, tagsByNewsId } = storeToRefs(useTagsStore());

    return {
      user,
      newsArticle,
      categories,
      category,
      tags,
      tagsByNewsId,
    };
  },
  methods: {
    addTag() {
      if (this.selectedTag != null) {
        this.newTags.push(this.selectedTag);
        this.selectedTag = null;
      }
    },
    removeTag(tag) {
      this.newTags.splice(this.newTags.indexOf(tag), 1);
      useTagsStore().removeTag(tag, this.newsArticle.id);
    },
    editNewsAndTags() {
      const article = {
        id: this.$route.params.id,
        title: this.newsArticle.title,
        content: this.newsArticle.content,
        author: this.newsArticle.author,
        authorName: this.newsArticle.authorName,
        categoryID: this.newsArticle.categoryID,
        views: this.newsArticle.views,
        likes: this.newsArticle.likes,
        dislikes: this.newsArticle.dislikes,
        creationTime: this.newsArticle.createdDate,
      };
      useNewsStore().updateNews(article);

      this.newTags.forEach((tag) => {
        if (!this.tagsByNewsId.some((t) => t.name === tag)) {
          useTagsStore().addTag(tag, this.newsArticle.id);
        }
      });
      this.tagsByNewsId.forEach((tag) => {
        if (!this.newTags.includes(tag.name)) {
          useTagsStore().removeTag(tag.name, this.newsArticle.id);
        }
      });

      this.$router.push({
        name: "SingleNews",
        params: { id: this.$route.params.id },
      });
    },
  },
  created() {
    useNewsStore().getNewsById(this.$route.params.id);
    useTagsStore().getTags();
    useTagsStore().getTagsByNewsId(this.$route.params.id);
    useCategoriesStore().getCategories();
    useCategoriesStore().getCategoryById(this.$route.params.catID);
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

#edit-news-form {
  width: 50%;
  margin: 0 auto;
  margin-bottom: 50px;
  margin-top: 50px;
}

@media only screen and (max-width: 600px) {
  #edit-news-form {
    width: 100%;
  }
}

form {
  background-color: #f5f5f5;
  padding: 1rem;
  border-radius: 0.5rem;
}

.form-group {
  margin-bottom: 1rem;
  margin-top: 2rem;
  text-align: justify;
}

select {
  width: 100%;
  height: 2rem;
}

.btn {
  margin-top: 20px;
}

.btn-primary {
  background-color: #2c3e50;
  border-color: #2c3e50;
}
</style>
