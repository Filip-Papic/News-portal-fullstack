<template>
	<div>
		<b-navbar toggleable="lg" :class="{ 'onScroll': !topOfPage }" type="dark" fixed="top">
			<div class="container-fluid">
				<router-link :to="{name: 'Home'}" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Home'}" style="display: flex;">
					<img src="@/assets/logo.png" alt="logo" width="40" height="40" >
					<a class="navbar-brand" v-if="jwt !== null && user.usertype !== 'Anonymous'">Welcome, {{ userName }}!</a>
					<a class="navbar-brand" v-else>Welcome!</a>
				</router-link>
				<b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
				<b-collapse id="nav-collapse" is-nav>
					<b-navbar-nav>
						<b-nav-item 
							class="nav-link" 
							to="/" 
							:class="{active: this.$router.currentRoute.name === 'Home'}">
							Home
						</b-nav-item>
						<b-nav-item 
							class="nav-link" 
							:to="{name: 'PopularNews'}" 
							:class="{active: this.$router.currentRoute.name === 'PopularNews'}"> 
							Popular
						</b-nav-item>
						<b-nav-item 
							class="nav-link" 
							:to="{name: 'News'}" 
							:class="{active: this.$router.currentRoute.name === 'News'}">
							News
						</b-nav-item>
						<b-nav-item-dropdown text="Categories" class="nav-link">
							<b-dropdown-item :to="{name: 'Categories'}">
								All Categories
							</b-dropdown-item>
							<b-dropdown-divider></b-dropdown-divider>
							<b-dropdown-item
								@click="reload"
								v-for="cat in categories"
								:key="cat.id"
								:to="`/category/${cat.id}`">
								{{ cat.categoryName }}
							</b-dropdown-item>
						</b-nav-item-dropdown>
						<b-nav-item 
							class="nav-link" 
							v-if="jwt !== null & user.usertype === 'admin'" 
							:to="{name: 'AllUsers'}" 
							:class="{active: this.$router.currentRoute.name === 'AllUsers'}">
							Users
						</b-nav-item>
					</b-navbar-nav>
					<b-navbar-nav class="ms-auto">
						<li class="nav-item">
							<b-nav-form class="col-xs-12" @submit.prevent="searchNews">
								<b-input-group>
									<b-form-input v-model="search" type="search" placeholder="Search articles..."></b-form-input>
									<b-input-group-append>
										<b-button variant="success" type="submit">
											<span class="fa-solid fa-magnifying-glass col-xs-1"></span>
										</b-button>
									</b-input-group-append>
								</b-input-group>
							</b-nav-form>
						</li>
						<li class="nav-item">
							<form v-if="canLogout" class="d-flex" @submit.prevent="logout">
								<button class="btn btn-secondary" type="submit">{{status}}</button>
							</form>
						</li>
					</b-navbar-nav>
				</b-collapse>
			</div>
		</b-navbar>
	</div>
</template>

<script>
	import { useAuthStore } from '@/store';
	import { useCategoriesStore } from '@/store';
	import { storeToRefs } from 'pinia';
	
	export default {
		name: "Navbar",
		data() {
			return {
				jwt: localStorage.getItem("jwt"),
				status: '',
				userName: '',
				search: '',
				topOfPage: true
			};
		},
		setup() {
			const { user } = storeToRefs(useAuthStore());
			const { categories } = storeToRefs(useCategoriesStore());
			
			return {
				user,
				categories
			};
		},
		computed: {
			canLogout() {
				return this.$route.name !== 'Login';
			}
		},
		methods: {
			logout() {
				const authStore = useAuthStore();
				authStore.logout();
			},
			reload() {
				this.$router.go();
			},
			searchNews() {
				this.$router.push({ name: 'Search', query: { q: this.search } });
			},
			handleScroll() {
				if (window.scrollY > 0) {
					if (this.topOfPage) {
						this.topOfPage = false;
					}
				} else {
					if (!this.topOfPage) {
						this.topOfPage = true;
					}
				}
			}
		},
		beforeMount() {
			window.addEventListener('scroll', this.handleScroll);
		},
		mounted() {
			this.status = this.jwt != null ? 'Logout' : 'Login';
			this.userName = this.jwt != null ? this.user.name : '';
			console.log('User type: ' + this.user.usertype);
		},
		created() {
			useCategoriesStore().getCategories();
		}
	}
</script>

<style scoped>

	@media (max-width: 1000px) {
		.navbar {
			position: relative;
			background-color: #373D3F;
		}
	}

	@media (min-width: 1000px) {
		.navbar-expand-lg {
			padding-right: 5rem;
			padding-left: 5rem;
		}
	}

	.navbar img {
		margin-right: 1rem;
	}

	.dropdown {
		margin-right: 1rem;
		color: white !important;
		font-size: 1.1rem;
		font-weight: 700;
	}

	.nav-link a {
		color: white !important;
		font-size: 1.1rem;
		font-weight: 700;
		text-shadow: 0 0 1px #000;
	}
	
	.navbar .navbar-brand {
		color: white !important;
		font-size: 1.5rem;
		font-weight: 700;
		text-shadow: 0 0 1px #000;
	}

	nav.onScroll {
		background-color: #373D3F;
		box-shadow: 0 7px 6px rgba(0, 0, 0, 0.1);
	}

	li {
		margin-right: 0.3rem;
	}
</style>
