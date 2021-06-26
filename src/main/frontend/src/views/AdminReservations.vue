<template>
  <!-- AdminNavbar -->
  <admin-navbar></admin-navbar>

  <div class="main-container">
    <h5 class="card indigo white-text center reservations-title">Reservations</h5>
    <div class="reservations-table">
      <table class="striped centered responsive-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Date</th>
            <th>Time</th>
            <th># of People</th>
            <th>Status</th>
            <th>Message</th>
            <th>Update</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="booking in bookings" :key="booking.id">
            <td>{{ booking.username }}</td>
            <td>{{ booking.date }}</td>
            <td>{{ booking.time }}</td>
            <td>{{ booking.numberOfPeople }}</td>
            <td>
              <select class="browser-default" v-model="booking.status">
                <option value="Processing">Processing</option>
                <option value="Cancelled">Cancelled</option>
                <option value="Completed">Completed</option>

              </select>
            </td>
            <td>{{ booking.message }}</td>
            <td><button @click="updateBooking(booking.id)" class="btn">Update</button></td>
            <td><button @click="deleteBooking(booking.id)" class="btn red">Delete</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import AdminNavbar from "@/components/AdminNavbar.vue";
import axios from "@/axios/index.js";

export default {
  components: {
    "admin-navbar": AdminNavbar,
  },
  data() {
    return {
      bookings: [],
    }
  },
  methods: {
    updateBooking(id) {
      let booking = this.bookings.find(b => b.id === id);
      axios.put("/api/v1/bookings/" + id,
        {
          numberOfPeople: booking.numberOfPeople,
          message: booking.message,
          date: booking.date,
          time: booking.time,
          status: booking.status
        }
      ).then(
        res => {
          alert("Updated");
        }
      ).catch(
        err => {
          if (err.response.status === 401) {
            this.$router.push({name: "Login"});
          } else {
            alert(err)
          }
        }
      )
    },
    deleteBooking(id) {
      axios.delete("/api/v1/bookings/" + id).then(
        res => {
          this.bookings = this.bookings.filter(booking => booking.id !== id);
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
    }

  },
  mounted() {
    axios("/api/v1/bookings").then(
      res => {
        this.bookings = res.data;
      }
    ).catch(
      err => {
        if (err.response.status === 401) {
          this.$router.push({name: "Login"});
        } else {
          alert(err);
        }
      }
    ) 
  }
};
</script>

<style scoped>
.reservations-table {
  border: 2px solid rgb(136, 12, 224);
}

.reservations-title {
  padding: 7px;
  margin: 0;
}

.main-container {
  margin: 20px 50px;
}
</style>
