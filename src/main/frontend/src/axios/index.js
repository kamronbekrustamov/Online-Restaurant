import axios from "axios";
import store from "../store/index.js"




const axiosInstance = axios.create({
  baseURL: "http://localhost:8080"
})

axiosInstance.interceptors.request.use(
  config => {
    if (store.state.token != null) {
      config.headers.Authorization = "Bearer " + store.state.token;
    }
    return config
  }
)

export default axiosInstance;