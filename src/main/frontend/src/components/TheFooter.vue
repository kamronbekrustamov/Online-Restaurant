<template>
  <!-- footer -->
  <footer class="page-footer grey darken-3">
    <div class="container">
      <div class="row">
        <div class="navbar">
          <ul>
            <div class="col s12 m2 center">
              <li>
                <router-link :to="{name: 'Home'}"><h6>Home</h6></router-link>
              </li>
            </div>
            <div class="col s12 m2 center">
              <li>
                <router-link :to="{name: 'Contacts'}"><h6>Contacts</h6></router-link>
              </li>
            </div>
            <div class="col s12 m2 center">
              <li>
                <router-link :to="{name: 'Menu'}"><h6>Menu</h6></router-link>
              </li>
            </div>
            <div class="col s12 m2 center">
              <li>
                <router-link :to="{name: 'Reservation'}"><h6>Reservation</h6></router-link>
              </li>
            </div>
            <div class="col s12 m2 center" v-if="!isLoggedIn">
              <li>
                <router-link :to="{name: 'Login'}"><h6>Login now</h6></router-link>
              </li>
            </div>
            <div class="col s12 m2 center" v-if="!isLoggedIn">
              <li>
                <router-link :to="{name: 'Registration'}"><h6>Register now</h6></router-link>
              </li>
            </div>
          </ul>
        </div>
        <div class="address">
          <div class="row">
            <div class="col m12 l4">
              <div class="row center">
                <img src="@/assets/map.png" alt="Map" />
                <span>Ziyolilar Street, Tashkent</span>
              </div>
            </div>
            <div class="col m12 l4">
              <div class="row center">
                <img src="@/assets/phone.png" alt="Phone" />
                <span>+998-99-999-99-99</span>
              </div>
            </div>
            <div class="col m12 l4">
              <div class="row center">
                <img src="@/assets/person.png" alt="Person" />
                <span>Developer: Kamronbek</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="footer-copyright grey darken-4">
      <div class="container center-align">&copy; 2021 Giardiano</div>
    </div>
  </footer>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["isLoggedIn"]),
  },
};
</script>
<style scoped>
ul {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

span {
  padding: 10px;
}

h6 {
  color: aliceblue;
}
</style>