<template>
  <!-- AdminNavbar -->
  <admin-navbar></admin-navbar>
  <div class="container grey lighten-4">
    <div
      class="row red white-text center error"
      v-if="!isValidImage || !isValidPrice"
    >
      {{ error }}
    </div>
    <form class="col s12" @submit.prevent="submit">
      <div class="row">
        <div class="input-field col s12">
          <input id="name" type="text" required v-model="name" />
          <label for="name">Name</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="price" type="text" required v-model="price" />
          <label for="price">Price</label>
        </div>
      </div>
      <div class="row">
        <div class="col s12 l6">
          <p for="category">Category</p>
          <select
            id="category"
            class="browser-default"
            required
            v-model="category"
          >
            <option value="Breakfast">Breakfast</option>
            <option value="Lunch">Lunch</option>
            <option value="Dinner">Dinner</option>
            <option value="Dessert">Dessert</option>
            <option value="Drink">Drink</option>
          </select>
        </div>
        <div class="col s12 l6">
          <p for="rank">Rank</p>
          <select id="rank" class="browser-default" required v-model="rank">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="file-field input-field">
          <div class="btn">
            <span>Image</span>
            <input type="file" required @change="onFileSelected" />
          </div>
          <div class="file-path-wrapper">
            <input class="file-path validate" type="text" />
          </div>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <textarea
            id="textarea1"
            class="materialize-textarea"
            data-length="160"
            required
            v-model="description"
          ></textarea>
          <label for="textarea1">Description About Food</label>
        </div>
      </div>
      <div class="row center">
        <div class="input-field col s12">
          <input class="btn" type="submit" value="Submit" />
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import AdminNavbar from "@/components/AdminNavbar.vue";
import axios from "@/axios/index.js";

export default {
  data() {
    return {
      name: "",
      description: "",
      price: null,
      category: "Breakfast",
      rank: 1,
      image: null,
      isValidImage: true,
      isValidPrice: true,
      error: "",
    };
  },
  components: {
    "admin-navbar": AdminNavbar,
  },
  methods: {
    submit() {
      if (this.isValidImage && this.isValidPrice) {
        this.uploadFood();
      }
    },
    uploadFood() {
      const data = new FormData();
      data.append("name", this.name);
      data.append("description", this.description);
      data.append("price", this.price);
      data.append("category", this.category);
      data.append("rank", this.rank);
      data.append("image", this.image, this.image.name);

      axios("/api/v1/foods", {
        method: "POST",
        data: data,
      }).then(res => {
        this.name = "";
        this.description = "";
        this.price = null;
        this.category = "Breakfast";
        this.rank = 1;
        this.image = null;
        this.isValidImage = true;
        this.isValidPrice = true;
        this.error = "";
      }).catch(err => {
        if (err.response.status === 401) {
          this.$router.push({name: "Login"});
        } else {
          console.log(err);
        }
      })

    },
    onFileSelected(event) {
      this.image = event.target.files[0];
    },
  },
  watch: {
    price() {
      if (isNaN(this.price)) {
        this.isValidPrice = false;
        this.error = "Price must be number";
      } else {
        this.isValidPrice = true;
      }
    },
    image() {
      if (this.image != null) {
        if (
          this.image.name.endsWith(".jpg") ||
          this.image.name.endsWith(".jpeg")
        ) {
          this.isValidImage = true;
          this.error = "";
        } else {
          this.isValidImage = false;
          this.error = "Only .jpeg or .jpg files can be uploaded";
        }
      }
    },
  },
};
</script>

<style scoped>
.container {
  margin: 20px auto;
  border-radius: 15px;
  padding: 20px 20px;
  border: 1px dashed black;
  max-width: 500px;
}

.error {
  padding: 3px;
}
</style>
