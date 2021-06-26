<template>
  <!-- Navbar -->
  <login-navbar></login-navbar>

  <div class="row login-page">
    <div class="teal white-text z-depth-2 center login-word">
      <h4>Login</h4>
    </div>

    <div class="col s12 z-depth-2 card-panel">
      <form class="login-form" @submit.prevent="submit">
        <div class="row"></div>
        <div class="row">
          <div class="input-field col s12">
            <i class="material-icons prefix">mail_outline</i>
            <input id="email" type="email" required v-model="email" />
            <label for="email">Email</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <i class="material-icons prefix">lock_outline</i>
            <input id="password" type="password" required v-model="password" />
            <label for="password">Password</label>
          </div>
        </div>
        <div v-if="!isValid" class="row center lighten-4 error-message">
          <h6>{{ error }}</h6>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <button class="btn waves-effect waves-light col s12">Login</button>
          </div>
        </div>
        <div class="row">
          <div class="input-field center">
            <p class="margin medium-small">
              <router-link :to="{ name: 'Registration' }"
                >Register Now!</router-link
              >
            </p>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import LoginNavbar from "@/components/LoginNavbar.vue";
import { mapMutations } from "vuex";
import axios from "@/axios/index.js";

export default {
  data() {
    return {
      email: "",
      password: "",
      isValid: true,
      error: "",
    };
  },
  components: {
    "login-navbar": LoginNavbar,
  },
  methods: {
    ...mapMutations(["logout", "login"]),
    submit() {
      if (this.isValid) {
        this.authenticate();
      }
    },
    authenticate() {
      const config = {
        headers: {
          Authorization: "Basic " + btoa(this.email + ":" + this.password),
        },
      };

      axios
        .post("/api/v1/users/authenticate",
        {},
        config
        )
        .then((res) => {
          this.login(res.data);
          if (res.data.role === "ROLE_USER") {
            this.$router.push({name: "Home"})
          } else {
            this.$router.push({name: "AdminFoods"})
          }
        })
        .catch((err) => {
          if (err.response.status === 401) {
            this.isValid = false;
            this.error = "Username or password is incorrect";
          }
        });
    },
  },
  watch: {
    password() {
      if (this.password.length < 8) {
        this.isValid = false;
        this.error = "Password must be at least 8 characters";
      } else {
        this.isValid = true;
        this.error = "";
      }
    },
  },
  mounted() {
    this.logout()
  }
};
</script>

<style scoped>
.login-page {
  width: 500px;
}

.login-word {
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