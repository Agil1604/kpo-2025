import React from 'react';
import type { Order, OrderStatus } from '../types';
import { CheckCircle, Clock, XCircle, ListOrdered } from 'lucide-react';

interface OrdersListProps {
  orders: Order[];
  isLoading: boolean;
}

const statusStyles: { [key in OrderStatus]: { icon: React.ReactNode; color: string; } } = {
  NEW: { icon: <Clock className="h-5 w-5" />, color: 'text-blue-400' },
  COMPLETE: { icon: <CheckCircle className="h-5 w-5" />, color: 'text-green-400' },
  CANCELLED: { icon: <XCircle className="h-5 w-5" />, color: 'text-red-400' },
};

export const OrdersList: React.FC<OrdersListProps> = ({ orders, isLoading }) => {
  return (
    <div className="bg-white/10 backdrop-blur-md p-6 rounded-xl shadow-lg mt-8 col-span-1 md:col-span-2">
      <h2 className="text-2xl font-bold mb-4 flex items-center"><ListOrdered className="mr-2" /> Заказы</h2>
      {isLoading ? (
        <p>Загрузка...</p>
      ) : orders.length === 0 ? (
        <p className="text-gray-400">Пока что нет заказов для данного пользователя.</p>
      ) : (
        <div className="space-y-3 max-h-96 overflow-y-auto pr-2">
          {orders.reverse().map((order) => {
            const { icon, color } = statusStyles[order.status];
            return (
              <div key={order.id} className="bg-gray-40 p-4 rounded-lg flex justify-between items-center">
                <div>
                  <p className="font-semibold">{order.description}</p>
                  <p className="text-sm text-gray-400 text-left">ID: {order.id}</p>
                </div>
                <div className="text-right">
                  <p className="text-lg font-mono">${order.amount}</p>
                  <div className={`flex items-center justify-end space-x-2 font-semibold ${color}`}>
                    {icon}
                    <span>{order.status}</span>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};