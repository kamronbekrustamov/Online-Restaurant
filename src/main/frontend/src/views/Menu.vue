<template>
  <!-- Navbar -->
  <the-navbar></the-navbar>

  <div class="container-fluid">
    <div class="col s12">

      <h2 class="center">Breakfast</h2>
      <div
        style="display: flex; justify-content: space-around; flex-wrap: wrap"
      >
        <food-card v-for="food in breakfastFoods" :key="food.id"
          :title="food.name"
          :price="food.price"
          :description="food.description"
          :imageUrl="url + food.imageUrl"
        >
        </food-card>
      </div>

      <h2 class="center">Lunch</h2>
      <div
        style="display: flex; justify-content: space-around; flex-wrap: wrap"
      >
        <food-card v-for="food in lunchFoods" :key="food.id"
          :title="food.name"
          :price="food.price"
          :description="food.description"
          :imageUrl="url + food.imageUrl"
        >
        </food-card>
      </div>

      <h2 class="center">Dinner</h2>
      <div
        style="display: flex; justify-content: space-around; flex-wrap: wrap"
      >
        <food-card v-for="food in dinnerFoods" :key="food.id"
          :title="food.name"
          :price="food.price"
          :description="food.description"
          :imageUrl="url + food.imageUrl"
        >
        </food-card>
      </div>

      <h2 class="center">Dessert</h2>
      <div
        style="display: flex; justify-content: space-around; flex-wrap: wrap"
      >
        <food-card v-for="food in desserts" :key="food.id"
          :title="food.name"
          :price="food.price"
          :description="food.description"
          :imageUrl="url + food.imageUrl"
        >
        </food-card>
      </div>

      <h2 class="center">Drinks</h2>
      <div
        style="display: flex; justify-content: space-around; flex-wrap: wrap"
      >
        <food-card v-for="food in drinks" :key="food.id"
          :title="food.name"
          :price="food.price"
          :description="food.description"
          :imageUrl="url + food.imageUrl"
        >
        </food-card>
      </div>
    </div>
  </div>

  <!-- Footer -->
  <the-footer></the-footer>

</template>

<script>
import TheNavbar from "@/components/TheNavbar.vue";
import TheFooter from "@/components/TheFooter.vue";
import FoodCard from "@/components/FoodCard.vue";
import axios from "@/axios/index.js"

export default {
  components: {
    "the-navbar": TheNavbar,
    "the-footer": TheFooter,
    "food-card": FoodCard,
  },
  data() {
    return {
      url: "http://localhost:8080/images/",
      foods: [],
    }
  },
  computed: {
    breakfastFoods() {
      return this.foods.filter(food => food.category === "Breakfast");
    },
    lunchFoods() {
      return this.foods.filter(food => food.category === "Lunch");
    },
    dinnerFoods() {
      return this.foods.filter(food => food.category === "Dinner");
    },
    desserts() {
      return this.foods.filter(food => food.category === "Dessert");
    },
    drinks() {
      return this.foods.filter(food => food.category === "Drink");
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
  }
};
</script>


<style scoped>
header {
  background: url(../assets/menu_bg.png);
  background-size: cover;
  background-position: center;
  min-height: 400px;
}
@media screen and (max-width: 670px) {
  .main-img {
    min-height: 200px;
  }
}
</style>