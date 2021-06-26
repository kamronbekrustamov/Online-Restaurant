<template>
  <!-- Navbar -->
  <login-navbar></login-navbar>

  <div class="row registration-page">
    <div class="teal white-text z-depth-2 center registration-word">
      <h4>Register</h4>
    </div>

    <div class="col s12 z-depth-2 card-panel">
      <form class="login-form" @submit.prevent="submit">
        <div class="row"></div>
        <div class="row">
          <div class="input-field col s12">
            <i class="material-icons prefix">comment</i>
            <input id="firstName" type="text" required v-model="firstName" />
            <label for="firstName">First Name</label>
          </div>
          <div class="input-field col s12">
            <i class="material-icons prefix">comment</i>
            <input id="lastName" type="text" required v-model="lastName" />
            <label for="lastName">Last Name</label>
          </div>
          <div class="input-field col s12">
            <i class="material-icons prefix">mail_outline</i>
            <input id="email" type="email" required v-model="email" />
            <label for="email">Email</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <i class="material-icons prefix">lock_outline</i>
            <input
              id="password1"
              type="password"
              required
              v-model="password1"
            />
            <label for="password1">Password</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <i class="material-icons prefix">lock_outline</i>
            <input
              id="password2"
              type="password"
              required
              v-model="password2"
            />
            <label for="password2">Re-Enter Password</label>
          </div>
        </div>
        <div class="row center lighten-4 error-message" v-if="!isValid">
          <h6>{{ error }}</h6>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <button class="btn waves-effect waves-light col s12">
              Register
            </button>
          </div>
        </div>
        <div class="row">
          <div class="input-field center">
            <p class="margin medium-small">
              <router-link :to="{ name: 'Login' }">Login!</router-link>
            </p>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import LoginNavbar from "@/components/LoginNavbar.vue";
import { mapMutations } from "vuex"
import axios from "@/axios/index.js";


export default {
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      password1: "",
      password2: "",
      isValid: true,
      error: "",
    };
  },
  components: {
    "login-navbar": LoginNavbar,
  },
  methods: {
    ...mapMutations(["login"]),
    submit() {
      if (this.isValid) {
        this.register();
      }
    },
    register() {
      const data = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password1,
      }
      axios
        .post("/api/v1/users/register", data)
        .then((res) => {
          this.login(res.data);
          this.$router.push({name: "Home"})
        })
        .catch((err) => {
          if (err.response.status === 409) {
            this.isValid = false;
            this.error =
              "The email is already taken. Please choose another one";
          }
        });
    },
  },
  watch: {
    password1() {
      if (this.password1.length < 8) {
        this.isValid = false;
        this.error = "Password must be at least 8 characters";
      } else if (this.password1 !== this.password2) {
        this.isValid = false;
        this.error = "Passwords do not match";
      } else {
        this.isValid = true;
        this.error = "";
      }
    },
    password2() {
      if (this.password1 !== this.password2) {
        this.isValid = false;
        this.error = "Passwords do not match";
      } else {
        this.isValid = true;
        this.error = "";
      }
    },
  },
};
</script>

<style scoped>
.registration-page {
  width: 500px;
}

.registration-word {
  margin: 0;
  padding: 3px 15px;
}

.card-panel {
  margin-top: 0;
}

.error-message {
  padding: 1px 5px;
  margin: 0 1px;
  color: red;
}
</style>