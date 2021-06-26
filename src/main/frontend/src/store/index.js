import { createStore } from 'vuex'

export default createStore({
  state: {
    isLoggedIn: false,
    isAdmin: false,
    token: null,
    firstName: null,
    lastName: null,
    email: null,
    role: null,
  },
  getters: {
    isLoggedIn(state) {
      return state.isLoggedIn;
    },
    isAdmin(state) {
      return state.isAdmin;
    },
    token(state) {
      return state.token;
    },
    firstName(state) {
      return state.firstName;
    },
    lastName(state) {
      return state.lastName;
    },
    email(state) {
      return state.email;
    },
    role(state) {
      return state.role;
    }
  },
  mutations: {
    logout(state) {
      state.isLoggedIn = false;
      state.isAdmin = false;
      state.token = null;
      state.firstName = null,
      state.lastName = null;
      state.email = null;
      state.role = null;
      window.localStorage.removeItem("user");
    },
    login(state, user) {
      state.token = user.token;
      state.firstName = user.firstName;
      state.lastName = user.lastName;
      state.email = user.email;
      state.role = user.role;
      state.isLoggedIn = true;
      if (user.role === "ROLE_USER") {
        state.isAdmin = false;
      } else {
        state.isAdmin = true;
      }
      window.localStorage.setItem("user", JSON.stringify(user));
    }
  },
  actions: {
  },
  modules: {
  }
})
