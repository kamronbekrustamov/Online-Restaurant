<template>
  <section>
    <nav class="nav-wrapper grey darken-2">
      <div class="container-fluid">
        <a href="#" class="sidenav-trigger" data-target="mobile-menu">
          <i class="material-icons">menu</i>
        </a>

        <ul class="hide-on-med-and-down left">
          <li><router-link :to="{name: 'AdminUsers'}">Users</router-link></li>
          <li><router-link :to="{name: 'AdminReservations'}">Reservations</router-link></li>
          <li><router-link :to="{name: 'AdminReviews'}">Reviews</router-link></li>
          <li><router-link :to="{name: 'AdminFoods'}">Foods</router-link></li>
        </ul>

        <a href="#" class="brand-logo center">Giardino</a>

        <ul class="hide-on-med-and-down right">
          <li><router-link :to="{name: 'AdminFoodForm'}">New Food</router-link></li>
          <li><a @click="logoutUser">Logout</a></li>
        </ul>
      </div>

      <ul class="sidenav" id="mobile-menu">
        <li><router-link :to="{name: 'AdminUsers'}">Users</router-link></li>
        <li><router-link :to="{name: 'AdminReservations'}">Reservations</router-link></li>
        <li><router-link :to="{name: 'AdminReviews'}">Reviews</router-link></li>
        <li><router-link :to="{name: 'AdminFoods'}">Foods</router-link></li>
        <li><router-link :to="{name: 'AdminFoodForm'}">New Food</router-link></li>
        <li><a @click="logoutUser">Logout</a></li>
      </ul>
    </nav>
  </section>
</template>

<script>
import { mapMutations } from "vuex";
export default {

  methods: {
    ...mapMutations(["logout"]),
    logoutUser() {
      this.logout();
      this.$router.push({name: "Login"})
    }
  }
}
</script>

<style scoped>
ul li a {
  font-size: 1.4rem;
}
</style>