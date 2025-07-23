import axios from "axios";
import type { Order, NewOrderPayload } from "./types";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
});

// payment

export const createAccount = (userId: number, balance: number) => {
  return apiClient.post(`/payment/create/${userId}`, { balance });
};

export const getBalance = (
  userId: number
): Promise<{ data: { sum: number } }> => {
  return apiClient.get(`/payment/${userId}`);
};

export const payAccount = (userId: number, sum: number) => {
  return apiClient.patch(`/payment/replenish/${userId}`, { sum });
};

// order

export const getOrders = (userId: number): Promise<{ data: { orders: Order[] } }> => {
  return apiClient.get(`/order/${userId}`);
};

export const getOrderStatus = (
  id: number
): Promise<{ data: { orders: Order[] } }> => {
  return apiClient.get(`/order/status/${id}`);
};

export const createOrder = (
  payload: NewOrderPayload
): Promise<{ data: Order }> => {
  return apiClient.post("/order/create", payload);
};
