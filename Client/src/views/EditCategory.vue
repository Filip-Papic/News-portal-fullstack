<template>
    <div id="home">
        <Banner :title='"Edit: " + category.categoryName'/>
        <b-form @submit.prevent="editCategory" id="edit-category-form">
            <b-form-group
                id="input-group-1"
                label="Category name:"
                label-for="input-1">
                <b-form-input
                    id="input-1"
                    v-model="category.categoryName"
                    required
                    placeholder="Enter category name"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                id="input-group-2"
                label="Category description:"
                label-for="input-2">
                <b-form-input
                    id="input-2"
                    v-model="category.categoryDescription"
                    required
                    placeholder="Enter category description"
                ></b-form-input>
            </b-form-group>
            <b-button type="submit" variant="primary">Edit category</b-button>
        </b-form>
    </div>
</template>

<script>
    import Banner from '../components/Banner';
    import { useCategoriesStore } from '@/store';
    import { storeToRefs } from 'pinia';

    export default {
        name: 'EditCategory',
        components: {
            Banner
        },
        setup() {
            const { category } = storeToRefs(useCategoriesStore());

            return {
                category
            }
        },
        methods: {
            editCategory() {
                useCategoriesStore().updateCategory(this.category);
                this.$router.push({name: 'Categories'});
            }
        },
        mounted() {
            const categoryStore = useCategoriesStore();
            categoryStore.getCategoryById(this.$route.params.id);
            this.category = categoryStore.category;
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

    #edit-category-form {
        width: 50%;
        margin: 0 auto;
        margin-bottom: 50px;
        margin-top: 50px;
    }

    @media only screen and (max-width: 600px) {
        #edit-category-form {
            width: 100%;
        }
    }
    
    form {
        background-color: #F5F5F5; 
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
  