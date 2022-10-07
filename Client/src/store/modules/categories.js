import { defineStore } from 'pinia'
import * as api from '@/api'

export const useCategoriesStore = defineStore({
    id: 'categories',
    state: () => ({
        categories: [],
        category: {}
    }),
    actions: {
        getCategories() {
            api.getCategories()
            .then(response => {
                this.categories = response.data;
            })
            .catch(error => {
                console.log(error);
            })
        },
        getCategoryById(id) {
            api.getCategory(id)
            .then(response => {
                this.category = response.data;
            })
            .catch(error => {
                console.log(error);
            })
        },
        addCategory(category) {
            api.createCategory(category)
            .then(response => {
                console.log('Category added: ' + response.data.categoryName);
                this.categories.push(response.data);
            })
            .catch(error => {
                console.log(error);
            })
        },
        updateCategory(category) {
            api.updateCategory(category.id, category)
            .then(response => {
                console.log('Category updated: ' + response.data.categoryName);
            })
            .catch(error => {
                console.log(error);
            })
        },
        deleteCategory(id) {
            api.deleteCategory(id)
            .then(response => {
                console.log('Category deleted: ' + response.data.categoryName);
            })
            .catch(error => {
                console.log(error);
            })
        }
    }
})