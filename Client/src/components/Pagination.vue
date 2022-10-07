<template>
    <div class="text-center container">
        <ul class="pagination">
            <li class="page-item"><a @click="prevPage" class="page-link" aria-label="Previous"><span aria-hidden="true">Previous</span></a></li>
            <h2>-</h2>
            <h2>{{ currentPage }}</h2>
            <h2>-</h2>
            <li class="page-item"><a @click="nextPage" class="page-link" aria-label="Next"><span aria-hidden="true">Next</span></a></li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: "Pagination",
        props: {
            perPage: {
                type: Number,
                required: true
            },
            news: {
                type: Number,
                required: true
            },
            pageName: {
                type: String,
                required: true
            },
            search: {
                type: String,
                required: true
            }
        },
        data() {
            return {
                currentPage: 1
            }
        },
        methods: {
            prevPage() {
                if (this.currentPage > 1) {
                    this.currentPage--;
                    if (this.pageName == "Search") {
                        this.$router.push({ name: 'Search', query: { page: this.currentPage, q: this.search } });
                    } else {
                        this.$router.push({name: this.pageName, query: {page: this.currentPage}})
                    }
                    this.$emit('page-changed', this.currentPage);
                }
            },
            nextPage() {
                if (this.news === this.perPage) {//??
                    this.currentPage++;
                    if (this.pageName == "Search") {
                        this.$router.push({ name: 'Search', query: { page: this.currentPage, q: this.search } });
                    } else {
                        this.$router.push({name: this.pageName, query: {page: this.currentPage}})
                    }
                    this.$emit('page-changed', this.currentPage);
                }
            }
        },
        created() {
            if (this.$route.query.page) {
                this.currentPage = this.$route.query.page;
            }
        },
        watch: {
            '$route.query.page': function() {
                this.currentPage = this.$route.query.page != null ? this.$route.query.page : 1;
            }
        }
    }
</script>