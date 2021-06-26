<template>

  <!-- Navbar -->
  <the-navbar></the-navbar>

  <div class="container">
    <h2 class="center">Contacts</h2>
    <div class="row">
      <div class="col s12 l6">
        <h4 class="blue-text">Address</h4>
        <h5>Ziyolilar Street, Tashkent</h5>
        <br />
        <br />
        <h4 class="blue-text">Phone Numbers</h4>
        <h5>+998-99-123-45-67</h5>
        <h5>+998-99-987-65-43</h5>
      </div>
      <div class="col s12 l6">
        <div v-if="showMessage" class="block black-text orange lighten-4 error-message">
          <h6>{{ message }}</h6>
        </div>
        <h4>Leave Your Reviews Here</h4>
        <div class="input-field">
          <textarea placeholder="Your Review" v-model="review"></textarea>
        </div>
        <button @click="send" class="btn teal lighten-1">
          <i class="material-icons left">send</i>
          Send
        </button>
      </div>
    </div>
  </div>
  <br>
  <br>

  <!-- Footer -->
  <the-footer></the-footer>
</template>

<script>
import TheNavbar from "@/components/TheNavbar.vue";
import TheFooter from "@/components/TheFooter.vue";
import axios from "@/axios/index.js";

export default {
  data() {
    return {
      review: "",
      showMessage: false,
      message: "",
    }
  },
  components: {
    "the-navbar": TheNavbar,
    "the-footer": TheFooter,
  },
  methods: {
    send() {
      if (this.review.length === 0) {
        this.showMessage = true;
        this.message = "Review cannot be empty"
      } else {
        this.showMessage = false;
        this.message = "";
        this.sendReview();
      }
    },
    sendReview() {
      axios.post("/api/v1/reviews", { review: this.review })
        .then( res => {
          this.showMessage = true;
          this.message = "Thank you for your review";
        }).catch( err => {
          if (err.response.status === 401) {
            this.$router.push({name: "Login"})
          } else {
            console.log(err)
          }
        })
    }
  },
}
</script>

<style scoped>
header {
  background: url(../assets/contacts_bg.jpg);
  background-size: cover;
  background-position: center;
  min-height: 350px;
}

.error-message {
  padding: 1px 10px;
  border-radius: 7px;
}

textarea {
  min-height: 130px;
  border-radius: 10px;
  border: 1px solid grey;
  color: black;
  text-align: center;
  padding: 20px 10px;
  font-size: 25px;
}
</style>