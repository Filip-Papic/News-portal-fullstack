<template>
    <div id="home"> 
        <Banner title='Categories' />
        <b-button v-if="user.usertype === 'admin'" variant="primary" @click="addCategory">Add category</b-button>
        <br>
        <div class="mt-3" 
            id="categories" 
            v-for="cat in categories" :key="cat.id">
            <b-card-group deck>
                <b-card 
                    bg-variant="dark" 
                    text-variant="white">
                    <div @click="categoryNews(cat.id)" style="cursor: pointer;">
                        <b-card-text>
                            <h3>{{ cat.categoryName }}</h3>
                            {{ cat.categoryDescription }}
                        </b-card-text>
                    </div>
                    <div class="admin">
                        <a v-if="user.usertype === 'admin'" 
                            variant="primary" 
                            @click="editCategory(cat.id)">
                            <small>Edit | </small></a>
                        <a v-if="user.usertype === 'admin'" 
                            variant="danger" 
                            @click="deleteCategory(cat.id)">
                            <small>Delete</small></a>
                    </div>
                </b-card>
            </b-card-group>
        </div>
    </div>
</template>

<script>
    import Banner from '../components/Banner.vue'
    import { useCategoriesStore } from '@/store';
    import { useAuthStore } from '@/store';
    import { storeToRefs } from 'pinia';

    export default {
        name: "Categories",
        components: {
            Banner
        },
        setup() {
            const categoryStore = useCategoriesStore();
            const authStore = useAuthStore();
            const { categories } = storeToRefs(categoryStore);
            const { user } = storeToRefs(authStore);

            return {
                categories,
                user
            }
        },
        methods: {
            getImage(cat) {
                return `https://picsum.photos/200/200/?image=${cat.id}`
            },
            categoryNews(id) {
                this.$router.push({name: 'CategoryNews', params: {id: id}});
            },
            addCategory() {
                this.$router.push({name: 'AddCategory'})
            },
            editCategory(id) {
                this.$router.push({name: 'EditCategory', params: {id: id}})
            },
            deleteCategory(id) {
                useCategoriesStore().deleteCategory(id);
                this.$router.go();
            },
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
        background-color: #F5F5F5;
    }

    #categories {
        width: 70%;
        margin: 0 auto;
    }

    @media only screen and (max-width: 600px) {
        #categories {
            width: 100%;
        }
    }

    .card {
        margin-bottom: 1%;
        cursor: pointer;
        height: 10rem
    }
    
    .admin {
        top: 0; 
        right: 0; 
        position: absolute;
        margin-top: 0.5rem; 
        margin-right: 0.5rem;
    }

    .btn {
        margin-top: 20px;
    }

    .btn-primary {
        background-color: #2c3e50;
        border-color: #2c3e50;
    }
</style>
