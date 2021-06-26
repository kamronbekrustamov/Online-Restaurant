<template>

  <!-- Navbar -->
  <the-navbar></the-navbar>

  <!-- About Us -->
  <about-us></about-us>

  <!-- Popular Foods -->
  <popular-foods :foods="twoRandomFoods"></popular-foods>

  <!-- Daily Specials -->
  <daily-specials></daily-specials>

  <!-- Reviews -->
  <reviews :reviews="twoRandomReviews"></reviews>

  <!-- Footer -->
  <the-footer></the-footer>

</template>

<script>
import TheNavbar from "@/components/TheNavbar.vue";
import TheFooter from "@/components/TheFooter.vue";
import DailySpecials from "@/components/DailySpecials.vue";
import AboutUs from "@/components/AboutUs.vue";
import Reviews from "@/components/Reviews.vue";
import PopularFoods from "@/components/PopularFoods.vue";
import axios from "@/axios/index.js"

export default {
  components: {
    "the-navbar": TheNavbar,
    "the-footer": TheFooter,
    "daily-specials": DailySpecials,
    "about-us": AboutUs,
    "reviews": Reviews,
    "popular-foods": PopularFoods,
  },
  data() {
    return {
      foods: [],
      reviews: [],
    }    
  },
  computed: {
    twoRandomFoods() {
      if (this.foods.length < 2) {
        return []
      } else {
        let first = Math.floor(Math.random() * this.foods.length);
        let second = Math.floor(Math.random() * this.foods.length);
        return [this.foods[first], this.foods[second]]
      }
    },
    twoRandomReviews() {
      if (this.reviews.length < 2) {
        return []
      } else {
        let first = Math.floor(Math.random() * this.reviews.length);
        let second = Math.floor(Math.random() * this.reviews.length);
        return [this.reviews[first], this.reviews[second]]
      }
    }
  },
  mounted() {
    axios("/api/v1/foods").then(
      res => {
        this.foods = res.data;
      }
    ).catch(
      err => {
        alert(err);
      }
    )

    axios("/api/v1/reviews").then(
      res => {
        this.reviews = res.data;
      }
    ).catch(
      err => {
        alert(err);
      }
    )
  }
};
</script>

<style>

header {
    background: url(../assets/header_bg1.jpg);
    background-size: cover;
    background-position: center;
    min-height: 500px;
}
</style>
