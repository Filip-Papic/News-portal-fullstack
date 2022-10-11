<template>
  <div>
    <Banner title="Users" />
    <br />
    <b-row>
      <b-col>
        <h2>Users:</h2>
      </b-col>
      <b-col></b-col>
      <b-col>
        <button class="btn btn-primary green" @click="addUser">Add User</button>
      </b-col>
    </b-row>
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Surname</th>
          <th scope="col">Email</th>
          <th scope="col">User Type</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.name }}</td>
          <td>{{ user.surname }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.userType }}</td>
          <td>
            <button
              v-if="user.userType !== 'admin'"
              id="statusBtn"
              class="btn btn-primary"
              @click="changeStatus(user)"
            >
              {{ user.status }}
            </button>
            <button
              v-if="user.userType !== 'admin'"
              class="btn btn-primary"
              @click="editUser(user.id)"
            >
              Edit
            </button>
            <button
              v-if="user.userType !== 'admin'"
              class="btn btn-danger"
              @click="deleteUser(user.id)"
            >
              Delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import Banner from "../components/Banner.vue";

export default {
  name: "UserTable",
  components: {
    Banner,
  },
  props: {
    users: {
      type: Array,
      required: true,
    },
  },
  methods: {
    addUser() {
      this.$router.push({ name: "AddUser" });
    },
    editUser(id) {
      this.$router.push({ name: "EditUser", params: { id: id } });
    },
    changeStatus(user) {
      if (user.status === "active") {
        user.status = "inactive";
      } else {
        user.status = "active";
      }
      this.$emit("changeStatus", user.id);
    },
    deleteUser(id) {
      this.$emit("deleteUser", id);
    },
  },
};
</script>

<style scoped>
.green {
  background-color: #4caf50;
}
</style>
