<template>
    <div id="home">
        <Banner title='Write news article' />
        <b-form @submit.prevent="addNewsAndTags" id="add-news-form">
            <b-form-group
                id="input-group-1"
                label="Title:"
                label-for="input-1"
                description="Enter title of the news article">
                <b-form-input
                    id="input-1"
                    v-model="title"
                    required
                    placeholder="Enter title">
                </b-form-input>
            </b-form-group>
            <b-form-group
                id="input-group-2"
                label="Content:"
                label-for="input-2"
                description="Enter content of the news article">
                <b-form-textarea
                    id="input-2"
                    v-model="content"
                    required
                    placeholder="Enter content"
                    rows="5">
                </b-form-textarea>
            </b-form-group>
            <label for="tags-basic">Type a new tag and add it</label>
            <b-form-tags
                input-id="tags-basic" 
                v-model="newTags"
                remove-on-delete
                no-add-on-enter
                :limit="tagLimit"
                tag-class="bg-success">
            </b-form-tags>
            <label for="input-3">Or select some of the existing tags</label>
            <b-form-group
                style="margin-top: 0rem;"
                id="input-group-3"
                label-for="input-3">
                <b-form-select
                    id="input-3"
                    v-model="selectedTag"
                    @change="addTag"
                    :options="tags"
                    value-field="name"
                    text-field="name"
                    class="mb-3">
                </b-form-select>
            </b-form-group>
            <p class="mt-2">Tags: <strong> {{ newTags }} </strong></p>
            <b-form-group
                id="input-group-4"
                label="Select category:"
                label-for="input-4"
                description="Please select category of the news article.">
                <b-form-select
                    id="input-4"
                    v-model="category"
                    :options="categories"
                    value-field="id"
                    text-field="categoryName"
                    required>
                </b-form-select>
            </b-form-group>
            <b-button type="submit" variant="primary">Submit</b-button>
        </b-form>
        <br><br>
    </div>
</template>
  
<script>
    import Banner from '../components/Banner';
    import { useAuthStore } from '@/store';
    import { useCategoriesStore } from '@/store';
    import { useNewsStore } from '@/store';
    import { useTagsStore } from '@/store';
	import { storeToRefs } from 'pinia';

    export default {
        name: 'AddNews',
        components: {
            Banner
        },
        data() {
            return {
                news: {
                    title: '',
                    content: '',
                    author: '',
                    authorName: '',
                    categoryID: '',
                },
                title: '',
                newTags: [],
                content: '',
                category: '',
                tagLimit: 5,
                selectedTag: ' '
            }
        },
        setup() {
			const { user } = storeToRefs(useAuthStore());
			const { categories } = storeToRefs(useCategoriesStore());
			const { tags } = storeToRefs(useTagsStore());

			return {
				user,
				categories,
                tags
			};
		},
        methods: {
            addNewsAndTags() {   
                this.news.title = this.title;
                this.news.content = this.content;
                this.news.author = this.user.id;
                this.news.authorName = this.user.name + ' ' + this.user.surname;
                this.news.categoryID = this.category;
                useNewsStore().addNews(this.news, this.newTags);
                this.$router.push({name: 'News'})
            },
            addTag() {
                if (this.selectedTag != null) {
                    this.newTags.push(this.selectedTag);
                    this.selectedTag = null;
                }
            }
        },
        mounted() {
            useCategoriesStore().getCategories();
            useTagsStore().getTags();
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

    #add-news-form {
        width: 50%;
        margin: 0 auto;
        margin-bottom: 50px;
        margin-top: 50px;
    }

    @media only screen and (max-width: 600px) {
        #add-news-form {
            width: 100%;
        }
    }

    #tags-basic {
        color: black;
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
  