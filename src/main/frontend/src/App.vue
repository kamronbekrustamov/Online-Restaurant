<template>
  <router-view/>
</template>

<script>
import { mapMutations } from "vuex"

export default {
  methods: {
    ...mapMutations(["login"])
  },
  created() {
    const userString = window.localStorage.getItem("user");
    if (userString != null) {
      const user = JSON.parse(userString);
      this.login(user);
    }
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap');
body {
  font-family: 'Roboto', sans-serif;
}
</style>
