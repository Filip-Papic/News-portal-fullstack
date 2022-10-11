<template>
  <div id="home">
    <UserTable
      :users="users"
      @deleteUser="deleteUser"
      @changeStatus="changeStatus"
    />
    <Pagination
      :perPage="perPage"
      :news="users.length"
      :currentPage="currentPage"
      :pageName="'AllUsers'"
      :search="''"
    />
  </div>
</template>

<script>
import UserTable from "@/components/UserTable.vue";
import Pagination from "../components/Pagination.vue";
import { useUsersStore } from "@/store";
import { storeToRefs } from "pinia";

export default {
  name: "Users",
  components: {
    UserTable,
    Pagination,
  },
  data() {
    return {
      currentPage: 1,
      perPage: 10,
    };
  },
  setup() {
    const usersStore = useUsersStore();
    const { users } = storeToRefs(usersStore);

    return {
      users,
    };
  },
  methods: {
    changeStatus(id) {
      useUsersStore().changeStatus(id);
    },
    deleteUser(id) {
      useUsersStore().deleteUser(id);
    },
  },
  created() {
    this.currentPage =
      this.$route.query.page != null ? this.$route.query.page : 1;
    useUsersStore().getUsers(this.currentPage, this.perPage);
  },
  watch: {
    "$route.query.page"() {
      this.currentPage =
        this.$route.query.page != null ? this.$route.query.page : 1;
      useUsersStore().getUsers(this.currentPage, this.perPage);
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
</style>
