<template>
  <!-- AdminNavbar -->
  <admin-navbar></admin-navbar>

  <div class="container-fluid">
    <h2 class="center">All Foods</h2>
    <div style="display: flex; justify-content: space-around; flex-wrap: wrap">
      <div class="food-card z-depth-1" v-for="food in foods" :key="food.id">
        <div class="row">
          <div class="col s6">
            <div class="food-image">
              <img :src="url + food.imageUrl" :alt="food.name" />
            </div>
          </div>
          <div class="col s6 food-data">
            <h6 class="">{{ food.name }}</h6>
            <div class="price">${{ food.price }}</div>
            <hr />
            <p>{{ food.description }}</p>
            <hr />
            <p>Category: {{ food.category }} || Rank: {{ food.rank }}</p>
          </div>
          <button @click="updateFood(food.id)" class="btn">Update</button>
          <button @click="deleteFood(food.id)" class="btn red right">Delete</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdminNavbar from "@/components/AdminNavbar.vue";
import FoodCard from "@/components/FoodCard.vue";
import axios from "@/axios/index.js";

export default {
  components: {
    "admin-navbar": AdminNavbar,
    "food-card": FoodCard,
  },
  data() {
    return {
      url: "http://localhost:8080/images/",
      foods: [],
    }
  },
  methods: {
    updateFood(id) {
    },
    deleteFood(id) {
      axios.delete("/api/v1/foods/" + id).then(
        res => {
          this.foods = this.foods.filter(food => food.id !== id);
        }
      ).catch(
        err => {
          if(err.response.status === 401) {
            this.$router.push({name: "Login"});
          } else {
            alert(err)
          }
        }
      )
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
.food-card {
  min-height: 300px;
  max-height: 300px;
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
