<template>
    <div id="home">
        <Banner :title='"Edit user: " + user.name' />
        <b-form @submit.prevent="editUser" id="edit-user-form">
            <b-form-group
                id="input-group-1"
                label="Name:"
                label-for="input-1">
                <b-form-input
                    id="input-1"
                    v-model="user.name"
                    required
                    placeholder="Enter name"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                id="input-group-2"
                label="Surname:"
                label-for="input-2">
                <b-form-input
                    id="input-2"
                    v-model="user.surname"
                    required
                    placeholder="Enter surname"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                id="input-group-3"
                label="Email:"
                label-for="input-3">
                <b-form-input
                    id="input-3"
                    v-model="user.email"
                    required
                    placeholder="Enter email"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                id="input-group-4"
                label="Password:"
                label-for="input-4">
                <b-form-input
                    id="input-4"
                    v-model="user.password"
                    required
                    placeholder="Enter password"
                ></b-form-input>
            </b-form-group>
            <b-form-group
                id="input-group-5"
                label="Role:"
                label-for="input-5">
                <b-form-select
                    id="input-5"
                    v-model="user.userType"
                    :options="roles"
                    required
                ></b-form-select>
            </b-form-group>
            <b-button type="submit" variant="primary">Edit user</b-button>
        </b-form>
    </div>
</template>
  
<script>
    import Banner from '../components/Banner';
    import { useUsersStore } from '@/store';
    import { storeToRefs } from 'pinia';

    export default {
        name: 'EditUser',
        components: {
            Banner
        },
        data() {
            return {
                roles: [
                    'admin',
                    'user'
                ]
            }
        },
        setup() {
            const { user } = storeToRefs(useUsersStore());

            return {
                user
            }
        },
        methods: {
            editUser() {
                useUsersStore().updateUser(this.user);
                this.$router.push({name: 'AllUsers'});
            }
        },
        created() {
            const userStore = useUsersStore();
            userStore.getUserById(this.$route.params.id);
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

    #edit-user-form {
        width: 50%;
        margin: 0 auto;
        margin-bottom: 50px;
        margin-top: 50px;
    }

    @media only screen and (max-width: 600px) {
        #edit-user-form {
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
    