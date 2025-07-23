import React, { useState } from "react";
import { createAccount, getBalance, payAccount } from "../api";
import { PlusCircle, UserPlus, Wallet } from "lucide-react";

interface PaymentsCardProps {
  userId: number;
  balance: number | null;
  setBalance: (balance: number | null) => void;
  onActionStart: () => void;
  onActionEnd: (error?: string) => void;
}

export const PaymentsCard: React.FC<PaymentsCardProps> = ({
  userId,
  balance,
  setBalance,
  onActionStart,
  onActionEnd,
}) => {
  const [payAmount, setPayAmount] = useState("");

  const handleCreateAccount = async () => {
    if (!userId) {
      onActionEnd("Please enter a User ID first.");
      return;
    }
    onActionStart();
    try {
      await createAccount(userId, 5);
      await handleCheckBalance(); // Check balance right after creation
      onActionEnd();
    } catch (err: any) {
      onActionEnd(err.response?.data?.error || "Failed to create account.");
    }
  };

  const handleCheckBalance = async () => {
    if (!userId) {
      onActionEnd("Please enter a User ID first.");
      return;
    }
    onActionStart();
    try {
      const { data } = await getBalance(userId);
      setBalance(data.sum);
      onActionEnd();
    } catch (err: any) {
      setBalance(null);
      onActionEnd(
        err.response?.data?.error ||
          "Failed to fetch balance. Does the account exist?"
      );
    }
  };

  const handlePay = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!userId || !payAmount) return;
    onActionStart();
    try {
      await payAccount(userId, parseFloat(payAmount));
      await handleCheckBalance();
      setPayAmount("");
      onActionEnd();
    } catch (err: any) {
      onActionEnd(err.response?.data?.error || "Failed to top up.");
    }
  };

  return (
    <div className="bg-white/10 backdrop-blur-md p-6 rounded-xl shadow-lg space-y-4">
      <h2 className="text-2xl font-bold flex items-center">
        <Wallet className="mr-2" /> Аккаунт
      </h2>

      <button
        onClick={handleCreateAccount}
        className="bg-gradient-to-r from-blue-500 to-purple-600 text-white px-8 py-3 rounded-full text-lg font-semibold shadow-lg hover:shadow-xl transform hover:scale-105 transition-all duration-300"
      >
        Регистрация/Логин
      </button>

      {/* <button onClick={handleCreateAccount} className="w-full flex items-center justify-center p-2 bg-blue-600 hover:bg-blue-700 rounded-md transition-colors">
        <UserPlus className="mr-2 h-5 w-5" /> Создать аккаунт
      </button> */}
      <div className="flex items-center justify-between">
        <span className="font-semibold">Баланс:</span>
        <span className="text-xl font-mono m-3 p-2 bg-black/20 rounded">
          {balance !== null ? `${balance}` : "N/A"}
        </span>
        <button
          onClick={handleCheckBalance}
          className="bg-gray-200 text-gray-800 px-8 py-3 rounded-full text-lg font-semibold shadow-lg hover:shadow-xl transform hover:scale-105 transition-all duration-300"
        >
          Обновить
        </button>
      </div>

      <form onSubmit={handlePay} className="space-y-2">
        <label htmlFor="pay" className="block text-sm font-medium">
          Пополнить счёт
        </label>
        <div className="flex space-x-2">
          <input
            id="pay"
            type="number"
            value={payAmount}
            onChange={(e) => setPayAmount(e.target.value)}
            placeholder="ex. 100"
            className="w-full p-2 rounded-md border border-gray-600 focus:ring-2 focus:ring-pink-500 outline-none"
            min="1"
            step="1"
            required
          />
          <button
            type="submit"
            className="flex items-center p-2 bg-green-600 hover:bg-green-700 rounded-md transition-colors whitespace-nowrap"
          >
            <PlusCircle className="h-5 w-5" />
          </button>
        </div>
      </form>
    </div>
  );
};
