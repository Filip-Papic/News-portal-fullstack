<template>
  <div id="home">
    <div class="div-overlay"></div>
    <br /><br />
    <b-form @submit.prevent="login" id="login-form">
      <h2><strong>Login</strong></h2>
      <b-form-group id="input-group-1" label="Email:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="email"
          required
          placeholder="Enter email"
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-2" label="Password:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="password"
          required
          placeholder="Enter password"
          type="password"
        ></b-form-input>
      </b-form-group>
      <b-button type="submit" variant="primary">Login</b-button>
    </b-form>
  </div>
</template>

<script>
import { useAuthStore } from "@/store";

export default {
  name: "Login",
  components: {},
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    login() {
      useAuthStore().login(this.email, this.password);
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
.div-overlay {
  min-width: 100%;
  min-height: 25%;
  position: absolute;
  top: 10%;
  left: 50%;
  z-index: 3;
  transform: translate(-50%, -80%);
  background-color: #373d3f;
}

#login-form {
  width: 50%;
  margin: 0 auto;

  margin-bottom: 50px;
  margin-top: 50px;
}

@media only screen and (max-width: 600px) {
  #login-form {
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
