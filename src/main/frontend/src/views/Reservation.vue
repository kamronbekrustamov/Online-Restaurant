<template>
  <!-- Navbar -->
  <the-navbar></the-navbar>

  <div class="container">
    <div class="col">
      <br />
      <br />
      <div
        class="block black-text orange lighten-4 error-message"
        v-if="!isValidNumber || !isValidDate"
      >
        <h6>{{ error }}</h6>
      </div>
      <br />
      <br />
      <div class="row">
        <div class="col s12 l5 offset-l1">
          <form @submit.prevent="submit">
            <div class="input-field">
              <i class="material-icons prefix">perm_identity</i>
              <input
                id="numberOfPeople"
                type="text"
                required
                v-model="numberOfPeople"
              />
              <label for="numberOfPeople">Number Of People</label>
            </div>
            <div class="input-field">
              <i class="material-icons prefix">message</i>
              <textarea
                name="message"
                id="message"
                class="materialize-textarea"
                required
                v-model="message"
              ></textarea>
              <label for="message">Your message</label>
            </div>
            <div class="input-field">
              <i class="material-icons prefix">date_range</i>
              <input type="date" id="date" required v-model="date" />
              <label for="date">Date</label>
            </div>
            <div class="input-field">
              <i class="material-icons prefix">watch_later</i>
              <input type="time" id="time" required v-model="time" />
              <label for="date">Time</label>
            </div>
            <div class="input-field center">
              <input type="submit" value="Book" class="btn" />
            </div>
          </form>
        </div>
        <div class="col s12 l5">
          <img
            src="@/assets/reserve_bg.png"
            alt="Reservation"
            style="width: 350px"
          />
        </div>
      </div>
      <div>
        <h5 class="card indigo white-text center reservations-title">
          Saved Reservations
        </h5>
        <div class="reservations-table">
          <table class="striped centered responsive-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Number of People</th>
                <th>Date</th>
                <th>Time</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="booking in bookings" :key="booking.id">
                <td>{{ booking.id }}</td>
                <td>{{ booking.numberOfPeople }}</td>
                <td>{{ booking.date }}</td>
                <td>{{ booking.time }}</td>
                <td>{{ booking.status }}</td>
                <td><button @click="updateBooking(booking.id)" class="btn">Update</button></td>
                <td><button @click="deleteBooking(booking.id)" class="btn red">Delete</button></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <br />
      <br />
    </div>
  </div>

  <!-- Footer -->
</template>

<script>
import TheNavbar from "@/components/TheNavbar.vue";
import TheFooter from "@/components/TheFooter.vue";
import axios from "@/axios/index.js";

export default {
  components: {
    "the-navbar": TheNavbar,
    "the-footer": TheFooter,
  },
  data() {
    return {
      id: null,
      bookings: [],
      numberOfPeople: null,
      message: null,
      time: null,
      date: null,
      isValidNumber: true,
      isValidDate: true,
      error: "",
    };
  },
  methods: {
    submit() {
      if (this.isValidNumber && this.isValidDate) {
        this.submitBooking();
      }
    },
    submitBooking() {
      let url = "/api/v1/bookings";
      let method = "POST";
      if (this.id != null) {
        url = url + "/" + this.id;
        method = "PUT";
      }

      axios({
        url: url,
        method: method,
        data: {
          numberOfPeople: this.numberOfPeople,
          message: this.message,
          date: this.date,
          time: this.time,
        }
      }).then( res => {
        if (this.id != null) {
          this.bookings = this.bookings.filter(booking => booking.id !== this.id );
          this.id = null;
        }
        this.bookings.push(res.data);
        this.numberOfPeople = null;
        this.message = null;
        this.date = null;
        this.time = null;
      }).catch( err => {
        if (err.response.status === 401) {
          this.$router.push({name: "Login"});
        } else {
          alert(err)
        }
      })
    },
    updateBooking(id) {
      let booking = this.bookings.find(b => b.id === id);
      this.id = booking.id;
      this.numberOfPeople = booking.numberOfPeople;
      this.message = booking.message;
      this.date = booking.date;
      this.time = booking.time;
    },
    deleteBooking(id) {
      axios.delete("/api/v1/bookings/" + id).then(
        res => {
          this.bookings = this.bookings.filter(b => b.id !== id);
          if (this.id === id) {
            this.id = null;
            this.numberOfPeople = null;
            this.message = null;
            this.date = null;
            this.time = null;
          }
        }
      ).catch(
        err => {
          if (err.response.status === 401) {
            this.$router.push({name: "Login"})
          } else {
            alert(err)
          }
        }
      )
    },
  },
  watch: {
    numberOfPeople() {
      if (isNaN(this.numberOfPeople)) {
        this.isValidNumber = false;
        this.error = "Number of people must be number";
      } else {
        this.isValidNumber = true;
        this.error = "";
      }
    },
    date() {
      if (this.date != null) {
        if ((new Date()) > (new Date(this.date))) {
          this.isValidDate = false;
          this.error = "Date cannot be in the past";
        } else {
          this.isValidDate = true;
          this.error = "";
        }
      }
    },
  },
  mounted() {
    axios("/api/v1/bookings")
      .then((res) => {
        this.bookings = res.data;
      })
      .catch((err) => {
        if (err.response.status === 401) {
          this.$router.push({ name: "Login" });
        } else {
          console.log(err);
        }
      });
  },
};
</script>


<style scoped>
header {
  background: url(../assets/header_bg2.jpg);
  background-size: cover;
  background-position: center;
  min-height: 400px;
}
.error-message {
  padding: 1px 10px;
  border-radius: 7px;
}

.reservations-table {
  border: 2px solid rgb(136, 12, 224);
}

.reservations-title {
  padding: 7px;
  margin: 0;
}
</style>