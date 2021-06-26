<template>
  <section>
    <div class="container-fluid">
      <h2 class="center white-text">Popular Foods</h2>
      <div style="display: flex; justify-content: space-around; flex-wrap: wrap;">
        <food-card v-for="food in foods" :key="food.id"
        :title="food.name"
        :price="food.price"
        :description="food.description"
        :imageUrl="url + food.imageUrl"
        >
        </food-card>
      </div>
    </div>
  </section>
</template>

<script>
import FoodCard from "./FoodCard.vue"

export default {
  components: {
    "food-card": FoodCard,
  },
  props: {
    foods: Array,
  },
  data() {
    return {
      url: "http://localhost:8080/images/"
    }
  }
}
</script>

<style scoped>
section {
  background-image: url(../assets/header_bg3.jpg);
  background-size: cover;
  background-position: center;
  min-height: 500px;
}
h2 {
  padding: 50px;
}

</style>