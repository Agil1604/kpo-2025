import React, { useState } from 'react';
import { ShoppingCart } from 'lucide-react';

interface OrdersCardProps {
  userId: string;
  onCreateOrder: (description: string, amount: number) => void;
}

export const OrdersCard: React.FC<OrdersCardProps> = ({ userId, onCreateOrder }: OrdersCardProps) => {
  const [description, setDescription] = useState('');
  const [amount, setAmount] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!userId || !description  || !amount) return;
    onCreateOrder(description, parseFloat(amount));
    setDescription('');
    setAmount('');
  };

  return (
    <div className="bg-white/10 backdrop-blur-md p-6 rounded-xl shadow-lg space-y-4">
      <h2 className="text-2xl font-bold mb-4 flex items-center"><ShoppingCart className="mr-2" /> Создать заказ</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label htmlFor="description" className="block text-sm font-medium">Описание</label>
          <input
            id="description"
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="ex. Радужный попыт"
            className="mt-1 w-full p-2 rounded-md border border-gray-600 focus:ring-2 focus:ring-pink-500 outline-none"
            required
          />
        </div>
        <div>
          <label htmlFor="amount" className="block text-sm font-medium">Количество</label>
          <input
            id="amount"
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            placeholder="ex. 5"
            className="mt-1 w-full p-2 rounded-md border border-gray-600 focus:ring-2 focus:ring-pink-500 outline-none"
            min="0.01"
            step="0.01"
            required
          />
        </div>
        <button type="submit" className="bg-gray-200 text-gray-800 px-8 py-3 rounded-full text-lg font-semibold shadow-lg hover:shadow-xl transform hover:scale-105 transition-all duration-300">
          Создать заказ
        </button>
      </form>
    </div>
  );
};