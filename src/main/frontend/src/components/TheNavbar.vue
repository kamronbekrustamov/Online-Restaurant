<template>
  <header>
    <nav class="nav-wrapper transparent">
      <div class="container">
        <a href="#" class="sidenav-trigger" data-target="mobile-menu">
          <i class="material-icons">menu</i>
        </a>

        <ul class="hide-on-med-and-down left">
          <li><router-link :to="{name: 'Home'}">Home</router-link></li>
          <li><router-link :to="{name: 'Contacts'}">Contacts</router-link></li>
          <li><router-link :to="{name: 'Menu'}">Menu</router-link></li>
        </ul>

        <a href="#" class="brand-logo center">Giardino</a>

        <ul class="hide-on-med-and-down right">
          <li><router-link :to="{name: 'Reservation'}">Reservation</router-link></li>
          <li v-if="!isLoggedIn"><router-link :to="{name: 'Login'}">Login</router-link></li>
          <li v-if="!isLoggedIn"><router-link :to="{name: 'Registration'}">Register</router-link></li>
          <li v-if="isLoggedIn"><a @click="logoutUser">Logout</a></li>
        </ul>
      </div>

      <ul class="sidenav" id="mobile-menu">
        <li><router-link :to="{name: 'Home'}">Home</router-link></li>
        <li><router-link :to="{name: 'Contacts'}">Contacts</router-link></li>
        <li><router-link :to="{name: 'Menu'}">Menu</router-link></li>
        <li><a href="#">Book</a></li>
        <li><router-link :to="{name: 'Reservation'}">Reservation</router-link></li>
        <li v-if="!isLoggedIn"><router-link :to="{name: 'Login'}">Login</router-link></li>
        <li v-if="!isLoggedIn"><router-link :to="{name: 'Registration'}">Register</router-link></li>
        <li v-if="isLoggedIn"><a @click="logoutUser">Logout</a></li>
      </ul>
    </nav>
  </header>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
export default {
  computed: {
    ...mapGetters(["isLoggedIn"]),
  },
  methods: {
    ...mapMutations(["logout"]),
    logoutUser() {
      this.logout();
      this.$router.push({name: "Login"})
    }
  }
};
</script>

<style scoped>
ul li a {
  font-size: 1.4rem;
}
</style>