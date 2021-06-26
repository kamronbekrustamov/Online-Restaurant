<template>
  <section>
    <div class="container">
    <h2 class="white-text center">Reviews</h2>
    <div class="row">
      <div class="col m12 l6 center" v-for="review in reviews" :key="review.id">
        <div class="cards z-depth-3">
          <img src="@/assets/quotes.png" alt="Quotes" class="left quotes">
          <br>
          <h5>{{ review.review }}</h5>
        </div>
        <div class="mini-card">
          <h6>{{ review.username }}</h6>
          <h6>Customer</h6>
        </div>
      </div>
    </div>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    reviews: Array
  }
}
</script>

<style scoped>
section {
  background-image: url(../assets/recipe_bg.png);
  background-size: cover;
  background-position: center;
  min-height: 450px;
}
h2 {
  padding-top: 50px;
}

.cards {
  min-height: 250px;
  max-height: 250px;
  max-width: 400px;
  min-width: 400px;
  background-color: aliceblue;
  border-radius: 20px;
}

.quotes {
  position: relative;
  top: -15px;
  left: 15px;
}
.mini-card {
  position: relative;
  top: -25px;
  left: 25px;
  min-height: 70px;
  max-height: 70px;
  max-width: 300px;
  min-width: 300px;
  background-color: aliceblue;
  border-radius: 10px;
  border: 1px solid black;
}

</style>