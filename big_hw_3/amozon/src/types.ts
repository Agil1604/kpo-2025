export type OrderStatus = "NEW" | "COMPLETE" | "CANCELLED";

export interface Order {
    id: number;
    userId: number;
    amount: number;
    description: string,
    status: OrderStatus;
}

export interface NewOrderPayload {
    userId: number;
    amount: number;
    description: string;
}

