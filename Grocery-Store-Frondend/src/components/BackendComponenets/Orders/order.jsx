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
                `http://localhost:8085/orders/customer/${customerId}`
            );

            console.log("Orders response:", response.data);

            setOrders(response.data);

        } catch (error) {
   

            console.error(
                "Error fetching orders:",
                error
            );

        }
    };

    return (
        <div className="orders">

            <h1 className="orders__title">
                My Orders
            </h1>

            {orders.length === 0 ? (

                <p>No orders found.</p>

            ) : (

                <div className="orders__list">

                    {orders.map((order) => (

                        <div
                            className="orders__card"
                            key={order.orderId}
                        >

                            <div className="orders__header">

                                <h2>
                                    Order #{order.orderId}
                                </h2>

                                <span>
                                    {order.status}
                                </span>

                            </div>

                            {order.items.map((item) => (

                                <div
                                    className="orders__item"
                                    key={item.productId}
                                >

                                    <img
                                        className="orders__image"
                                        src={`/images/product-${item.productId}.jpg`}
                                        alt={`Product ${item.productId}`}
                                    />

                                    <div className="orders__details">

                                        <p>
                                            Product ID:
                                            {" "}
                                            {item.productId}
                                        </p>

                                        <p>
                                            Quantity:
                                            {" "}
                                            {item.quantity}
                                        </p>

                                        <p>
                                            Price:
                                            {" "}
                                            ₹{item.price}
                                        </p>

                                    </div>

                                </div>

                            ))}

                            <div className="orders__total">

                                Total:
                                {" "}
                                ₹{order.total}

                            </div>

                        </div>

                    ))}

                </div>

            )}

        </div>
    );
}

export default Orders;