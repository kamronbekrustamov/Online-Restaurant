<template>
  <div class="food-card z-depth-1">
    <div class="row">
      <div class="col s6">
        <div class="food-image">
          <img :src="imageUrl" alt="">
        </div>

      </div>
      <div class="col s6 food-data">
        <h6 class="">{{ title }}</h6>
        <div class="price">${{ price }}</div>
        <hr>
        <p>{{ description }}</p>
      </div>

    </div>
  </div>

</template>

<script>
export default {
  props: ["title", "price", "description", "imageUrl"]
}
</script>


<style scoped>

.food-card {
  min-height: 250px;
  max-height: 250px;
  min-width: 500px;
  max-width: 500px;
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  margin: 10px;
}

.price {
  display: inline-block;
  padding: 5px 20px;
  border-radius: 10px;
  font-size: 1.2rem;
  background-color: yellow;
}

.food-image {
  background-color: white;
  border-radius: 20px;
  min-height: 210px;
  padding: 0;
  margin: 0;
}
.food-image img {
  max-height: 210px;
  max-width: 203px;
  border-radius: 20px;
}

h6 {
  font-size: 1.4rem;
}
</style>