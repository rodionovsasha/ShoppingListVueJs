import axios from 'axios'

export const AXIOS = axios.create({
  baseURL: 'http://localhost:8000/v1/api'
})