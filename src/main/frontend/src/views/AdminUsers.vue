<template>
  <!-- AdminNavbar -->
  <admin-navbar></admin-navbar>

  <br />
  <br />

  <div class="container">
    <h5 class="card indigo white-text center users-title">Users</h5>
    <div class="users-table">
      <table class="striped centered responsive-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.id">
            <td>{{ user.id }}</td>
            <td>{{ user.firstName }}</td>
            <td>{{ user.lastName }}</td>
            <td>{{ user.email }}</td>
            <td><button @click="deleteUser(user.id)" class="btn red">Delete</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import AdminNavbar from "@/components/AdminNavbar.vue";
import axios from "@/axios/index.js"

export default {
  data() {
    return {
      users: [],
    }
  },
  components: {
    "admin-navbar": AdminNavbar,
  },
  methods: {
    loadUsers() {
      axios("/api/v1/users/all").then(
        res => {
          this.users = res.data;
        }
      ).catch(
        err => {
          if(err.response.status === 401) {
            this.$router.push({name: "Login"})
          }
        }
      )
    },
    deleteUser(id) {
      axios.delete("/api/v1/users/" + id).then(
        res => {
          this.users = this.users.filter(user => user.id !== id);
        }
      ).catch(
        err => {
          if(err.response.status === 401) {
            this.$router.push({name: "Login"})
          }
        }
      )
    }
  },
  mounted() {
    this.loadUsers();
  }
};
</script>

<style scoped>

.users-table {
  border: 2px solid rgb(136, 12, 224);
}

.users-title {
  padding: 7px;
  margin: 0;
}
</style>