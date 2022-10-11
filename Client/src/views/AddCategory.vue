<template>
  <div id="home">
    <Banner title="Add new category" />
    <b-form @submit.prevent="addCategory" id="add-category-form">
      <b-form-group
        id="input-group-1"
        label="Category name:"
        label-for="input-1"
        description="Enter name of the category"
      >
        <b-form-input
          id="input-1"
          v-model="category.categoryName"
          required
          placeholder="Enter name"
        >
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-group-2"
        label="Description:"
        label-for="input-2"
        description="Enter category description"
      >
        <b-form-textarea
          id="input-2"
          v-model="category.categoryDescription"
          required
          placeholder="Enter description"
          rows="3"
        >
        </b-form-textarea>
      </b-form-group>
      <b-button type="submit" variant="primary">Add category</b-button>
    </b-form>
  </div>
</template>

<script>
import Banner from "../components/Banner";
import { useCategoriesStore } from "@/store";

export default {
  name: "AddCategory",
  components: {
    Banner,
  },
  data() {
    return {
      category: {
        categoryName: "",
        categoryDescription: "",
      },
    };
  },
  methods: {
    addCategory() {
      useCategoriesStore().addCategory(this.category);
      this.$router.push({ name: "Categories" });
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

#add-category-form {
  width: 50%;
  margin: 0 auto;
  margin-bottom: 50px;
  margin-top: 50px;
}

@media only screen and (max-width: 600px) {
  #add-category-form {
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
