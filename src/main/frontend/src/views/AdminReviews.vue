<template>
  <!-- AdminNavbar -->
  <admin-navbar></admin-navbar>

  <br />
  <br />
  
  <div class="container">
    <h5 class="card indigo white-text center reviews-title">Reviews</h5>
    <div class="reviews-table">
      <table class="striped centered responsive-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Review</th>
            <th>Date</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="review in reviews" :key="review.id">
            <td>{{ review.username }}</td>
            <td>{{ review.review }}</td>
            <td>{{ review.time }}</td>
            <td><button @click="deleteReview(review.id)" class="btn red">Delete</button></td>
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
      reviews: []
    }
  },
  components: {
    "admin-navbar": AdminNavbar,
  },
  methods: {
    loadReviews() {
      axios("/api/v1/reviews").then(
        res => {
          this.reviews = res.data;
        }
      ).catch(
        err => {
          if(err.response.status === 401) {
            this.$router.push({name: "Login"})
          }
        }
      )
    },
    deleteReview(id) {
      axios.delete("/api/v1/reviews/" + id).then(
        res => {
          this.reviews = this.reviews.filter(reviews => reviews.id !== id);
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
    this.loadReviews();
  }

};
</script>

<style scoped>
.reviews-table {
  border: 2px solid rgb(136, 12, 224);
}

.reviews-title {
  padding: 7px;
  margin: 0;
}

</style>