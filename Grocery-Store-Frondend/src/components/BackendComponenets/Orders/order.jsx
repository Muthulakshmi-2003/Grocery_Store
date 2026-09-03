import { useEffect, useState } from "react";
import axios from "axios";

import "./Orders.scss";

function Orders() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const customerId = localStorage.getItem("customerId");
      console.log("Logged-in customerId:", customerId);

      const response = await axios.get(
        `http://localhost:8085/orders/customer/${customerId}`,
      );

      console.log("Orders response:", response.data);

      setOrders(response.data);
    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  };

}

export default Orders;
