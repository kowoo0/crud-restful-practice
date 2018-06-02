import axios from 'axios'

export default axios.create({
  baseURL: 'http://localhost:8090/api',
  timeout: 3000,
  withCredentials: true, // 쿠기를 보내거나 받고 싶을 때
  headers: {
    'Content-Type': 'application/json'
  }
})
