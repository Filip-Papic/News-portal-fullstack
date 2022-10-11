<template>
  <div id="home">
    <Banner title="Add new user" />
    <b-form @submit.prevent="addUser" id="add-user-form">
      <b-form-group
        id="input-group-1"
        label="Name:"
        label-for="input-1"
        description="Enter name of the user"
      >
        <b-form-input
          id="input-1"
          v-model="user.name"
          required
          placeholder="Enter name"
        >
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-group-2"
        label="Surname:"
        label-for="input-2"
        description="Enter surname of the user"
      >
        <b-form-input
          id="input-2"
          v-model="user.surname"
          required
          placeholder="Enter surname"
        >
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-group-3"
        label="Email:"
        label-for="input-3"
        description="Enter email of the user"
      >
        <b-form-input
          id="input-3"
          v-model="user.email"
          required
          type="email"
          placeholder="Enter email"
        >
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-group-4"
        label="Password:"
        label-for="input-4"
        description="Enter password of the user"
      >
        <b-form-input
          id="input-4"
          v-model="user.password"
          required
          placeholder="Enter password"
        >
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-group-5"
        label="Confirm password:"
        label-for="input-5"
        description="Confirm password of the user"
      >
        <b-form-input
          id="input-5"
          v-model="confirmPassword"
          required
          placeholder="Confirm password"
        >
        </b-form-input>
      </b-form-group>
      <b-form-group
        id="input-group-6"
        label="Role:"
        label-for="input-6"
        description="Select role of the user"
      >
        <b-form-select
          id="input-6"
          v-model="user.userType"
          :options="roles"
          value-field="name"
          text-field="name"
          class="mb-3"
        >
        </b-form-select>
      </b-form-group>
      <b-button type="submit" variant="primary">Add user</b-button>
    </b-form>
  </div>
</template>

<script>
import Banner from "../components/Banner";
import { useUsersStore } from "@/store";

export default {
  name: "AddUser",
  components: { Banner },
  data() {
    return {
      user: {
        name: "",
        surname: "",
        email: "",
        password: "",
        userType: "",
        status: "active",
      },
      confirmPassword: "",
      roles: [{ name: "admin" }, { name: "user" }],
    };
  },
  methods: {
    addUser() {
      if (this.user.password !== this.confirmPassword) {
        alert("Wrong password confirmation!");
        return;
      }
      useUsersStore().addUser(this.user);
      this.$router.push({ name: "AllUsers" });
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

#add-user-form {
  width: 50%;
  margin: 0 auto;
  margin-bottom: 50px;
  margin-top: 50px;
}

@media only screen and (max-width: 600px) {
  #add-user-form {
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
