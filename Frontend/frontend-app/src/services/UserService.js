import axios from "axios";
import { jwtDecode } from "jwt-decode";

const API_URL = "http://localhost:8081/api"

export const register = async (body) => {
  const res = await axios.post(API_URL + "/register", body);

  if (res.status === 201) return res;
  throw new Error("Request failed");
};

export const login = async (body) => {

  console.log(API_URL) 
  const res = await axios.post(API_URL + "/authentication", body);

  if (res.status === 200) {
    localStorage.setItem("token", JSON.stringify(res.data.jwt));
    return res;
  }
  throw new Error("Request failed");
};

export const logout = () => {
  localStorage.removeItem("token");
};

export const getUserName = () => {
  return jwtDecode(localStorage.getItem("token")).sub;
};
