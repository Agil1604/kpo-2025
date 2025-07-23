

// import { useState } from "react";
// import reactLogo from "./assets/react.svg";
// import viteLogo from "/vite.svg";

import { useState, useCallback, useEffect } from "react";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import { PaymentsCard } from "./components/PaymentsCard";
import { OrdersCard } from "./components/OrdersCard";
import { OrdersList } from "./components/OrdersList";
import { createOrder, getOrders } from "./api";
import type { Order } from "./types";
import { UserPlus } from 'lucide-react';
// import { useOrderSocket } from './hooks/useOrderSocket';

// import TodoItem from "./components/todo";
// import { dummyData } from "./data/todos";

function App() {
  // const [count, setCount] = useState(0);
  // const [todos, setTodos] = useState(dummyData);

  // function setTodoCompleted(id: number, completed: boolean) {
  //   setTodos((prevTodos) =>
  //     prevTodos.map((todo) => (todo.id === id ? { ...todo, completed } : todo))
  //   );
  // }

  const [userId, setUserId] = useState<number>(123); // Default user for testing
  const [balance, setBalance] = useState<number | null>(null);
  const [orders, setOrders] = useState<Order[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleActionStart = () => {
    setIsLoading(true);
    setError(null);
  };

  const handleActionEnd = (errorMessage?: string) => {
    setIsLoading(false);
    if (errorMessage) {
      setError(errorMessage);
      toast.error(errorMessage);
    }
  };

  const fetchUserOrders = useCallback(async () => {
    if (!userId) return;
    handleActionStart();
    try {
      const { data } = await getOrders(userId);
      setOrders(data.orders); // Show newest first
      console.log(data);
      handleActionEnd();
    } catch (err: any) {
      handleActionEnd(err.response?.data?.error || "Не получилось загрузить заказы.");
    }
  }, [userId]);

  const handleCreateOrder = async (description: string, amount: number) => {
    if (!userId) {
      handleActionEnd("Введите User ID.");
      return;
    }
    handleActionStart();
    try {
      await createOrder({ userId, description, amount });
      toast.success(
        "Заказ принят!"
      );
      // The new order will appear after a short delay once it's created in DB
      setTimeout(fetchUserOrders, 1000); // Refetch orders to see the new one
      handleActionEnd();
    } catch (err: any) {
      handleActionEnd(err.response?.data?.error || "Не получилось создать заказ.");
    }
  };

  const handleOrderUpdate = useCallback((updatedOrder: Order) => {
    setOrders((prevOrders) =>
      prevOrders.map((order) =>
        order.id === updatedOrder.id ? updatedOrder : order
      )
    );
  }, []);

  // Hook into the WebSocket
  // useOrderSocket(userId, handleOrderUpdate);

  // Fetch orders when user ID changes
  useEffect(() => {
    fetchUserOrders();
  }, [fetchUserOrders]);

  return (
    <>
      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="dark"
      />
      <div className="absolute inset-0 -z-10 h-full w-full items-center px-5 py-24 [background:radial-gradient(125%_125%_at_50%_10%,#000_40%,#63e_100%)]" />
      <div className="relative min-h-screen w-full flex items-center justify-center p-4">
        <div
          className="relative bg-white backdrop-blur-sm
                   p-8 md:p-12 rounded-xl shadow-2xl
                   max-w-4xl w-full text-center text-gray-800
                   flex flex-col items-center justify-center"
        >
          <h1 className="text-4xl md:text-6xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-purple-600 to-pink-500 mb-6">
            AmOzon
          </h1>
          <p className="text-xl leading-relaxed mb-8">
            Лучшая компания по деланью заказов (да)
          </p>
          <div className="bg-white backdrop-blur-md p-6 rounded-xl shadow-lg mb-8">
            {/* <h2 className="text-2xl font-bold flex items-center mb-5"><UserPlus className="mr-2" /> Аккаунт</h2> */}
            <label htmlFor="userid" className="text-xl font-bold p-2">
              User ID:
            </label>
            <input
              id="userid"
              type="text"
              value={userId}
              onChange={(e) => setUserId(e.target.value)}
              className="mt-1 w-full md:w-1/3 p-2 rounded-md border border-gray-700 focus:ring-2 focus:ring-pink-500 outline-none"
              placeholder="Enter a unique User ID"
            />
            {/* <div className="p-6"> */}
            {/* </div> */}
          </div>

          <div className="">
            <div className="space-y-8 grid grid-cols-1 md:grid-cols-2 gap-8">
              <PaymentsCard
                userId={userId}
                balance={balance}
                setBalance={setBalance}
                onActionStart={handleActionStart}
                onActionEnd={handleActionEnd}
              />
              <OrdersCard userId={userId} onCreateOrder={handleCreateOrder} />
            </div>

            <OrdersList
              orders={orders}
              isLoading={isLoading && orders.length === 0}
            />
          </div>

          <p className="mt-12 text-sm text-gray-600">
            &copy; {new Date().getFullYear()} Три бобра Corporation
          </p>
        </div>
      </div>
    </>

    /* <div className="space-y-4 md:space-y-0 md:space-x-4 flex flex-col md:flex-row items-center">
            <button className="bg-gradient-to-r from-blue-500 to-purple-600 text-white px-8 py-3 rounded-full text-lg font-semibold shadow-lg hover:shadow-xl transform hover:scale-105 transition-all duration-300">
              Сделать заказ
            </button>
            <button className="bg-gray-200 text-gray-800 px-8 py-3 rounded-full text-lg font-semibold shadow-lg hover:shadow-xl transform hover:scale-105 transition-all duration-300">
              Отправить ядерную ракету в Венесуэлу
            </button>
          </div> */

    // <main className="py-8 h-screen">
    //   <div className="absolute inset-0 -z-10 h-full w-full items-center px-5 py-24 [background:radial-gradient(125%_125%_at_50%_10%,#000_40%,#63e_100%)]"></div>
    //   <div className="max-w-lg mx-auto bg-slate-100 rounded-md p-5 space-y-5">
    //     <h1 className="font-bold text-3xl text-center">My TODO</h1>
    //     <div className="space-y-2">
    //       {todos.map((todo) => (
    //         <TodoItem
    //           // key={todo.id}
    //           todo={todo}
    //           onCompletedChange={setTodoCompleted}
    //         />
    //       ))}
    //     </div>
    //   </div>
    // </main>
  );
}

export default App;
