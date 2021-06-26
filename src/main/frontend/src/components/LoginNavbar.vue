<template>
  <section>
    <nav class="nav-wrapper white">
      <div class="container">
        <a href="#" class="sidenav-trigger" data-target="mobile-menu">
          <i class="material-icons">menu</i>
        </a>

        <a href="#" class="brand-logo">Giardino</a>

        <ul class="hide-on-med-and-down right">
          <li><router-link :to="{name: 'Login'}">Login</router-link></li>
          <li><router-link :to="{name: 'Registration'}">Register</router-link></li>
        </ul>
      </div>

      <ul class="sidenav" id="mobile-menu">
        <li><router-link :to="{name: 'Login'}">Login</router-link></li>
        <li><router-link :to="{name: 'Registration'}">Register</router-link></li>
      </ul>
    </nav>
  </section>
</template>

<style scoped>
a {
  color: black;
}

</style>