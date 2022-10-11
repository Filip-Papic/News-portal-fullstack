import { defineStore } from "pinia";
import {
  getUsers,
  getUser,
  createUser,
  updateUser,
  changeStatus,
  deleteUser,
} from "@/api";

export const useUsersStore = defineStore({
  id: "users",
  state: () => ({
    users: [],
    user: {},
  }),
  actions: {
    getUsers(currentPage, perPage) {
      getUsers(currentPage, perPage)
        .then((response) => {
          this.users = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getUserById(id) {
      getUser(id)
        .then((response) => {
          this.user = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addUser(user) {
      createUser(user)
        .then((response) => {
          console.log("User added: " + response.data.name);
          this.users.push(response.data);
        })
        .catch((error) => {
          alert("Something went wrong");
          console.log(error);
        });
    },
    updateUser(user) {
      updateUser(user.id, user)
        .then((response) => {
          console.log("User updated: " + response.data.name);
          this.users = this.users.map((u) => {
            if (u.id === user.id) {
              return response.data;
            }
            return u;
          });
        })
        .catch((error) => {
          alert("Something went wrong");
          console.log(error);
        });
    },
    changeStatus(id) {
      changeStatus(id).catch((error) => {
        console.log(error);
      });
    },
    deleteUser(id) {
      deleteUser(id)
        .then((this.users = this.users.filter((user) => user.id !== id)))
        .catch((error) => {
          console.log(error);
        });
    },
  },
});
