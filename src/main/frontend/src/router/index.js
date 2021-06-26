import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Menu from "../views/Menu.vue";
import Reservation from "../views/Reservation.vue";
import Contacts from "../views/Contacts.vue";
import Login from "../views/Login.vue";
import Registration from "../views/Registration.vue";
import AdminUsers from "../views/AdminUsers.vue";
import AdminReservations from "../views/AdminReservations.vue";
import AdminReviews from "../views/AdminReviews.vue";
import AdminFoods from "../views/AdminFoods.vue";
import AdminFoodForm from "../views/AdminFoodForm.vue";
import NotFound from "../views/NotFound.vue"
import store from "../store/index.js";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    meta: { authRequired: false, forAdmin: false },
  },
  {
    path: "/menu",
    name: "Menu",
    component: Menu,
    meta: { authRequired: false, forAdmin: false },
  },
  {
    path: "/reservation",
    name: "Reservation",
    component: Reservation,
    meta: { authRequired: true, forAdmin: false },
  },
  {
    path: "/contacts",
    name: "Contacts",
    component: Contacts,
    meta: { authRequired: true, forAdmin: false },
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/register",
    name: "Registration",
    component: Registration,
  },
  {
    path: "/admin/users",
    name: "AdminUsers",
    component: AdminUsers,
    meta: { authRequired: true, forAdmin: true },
  },
  {
    path: "/admin/reservations",
    name: "AdminReservations",
    component: AdminReservations,
    meta: { authRequired: true, forAdmin: true },
  },
  {
    path: "/admin/reviews",
    name: "AdminReviews",
    component: AdminReviews,
    meta: { authRequired: true, forAdmin: true },
  },
  {
    path: "/admin/foods",
    name: "AdminFoods",
    component: AdminFoods,
    meta: { authRequired: true, forAdmin: true },
  },
  {
    path: "/admin/food-form",
    name: "AdminFoodForm",
    component: AdminFoodForm,
    meta: { authRequired: true, forAdmin: true },
  },
  {
    path: "/:notFound(.*)",
    name: "NotFound",
    component: NotFound,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.name === "NotFound") {
    next()
  }
  
  // Users can access Login Page when they are not logged in
  else if (to.name === "Login" || to.name === "Register") {
    if (store.state.isLoggedIn) {
      next({name: from.name})
    } else {
      next()
    }
  }

  // Pages With Authentication
  else if (to.meta.authRequired) {
    if (store.state.isLoggedIn) {
      if (to.meta.forAdmin) {
        
        if (store.state.isAdmin) {
          next()
        }
        
        else {
          next("/not-found")
        }

      }
      
      else {

        if (!store.state.isAdmin) {
          next()
        }
        
        else {
          next("/not-found")
        }

      }
    }
    
    else {
      next({name: "Login"})
    }
  }

  // Pages Without Authentication
  else {
    next();
  }
});

export default router;
