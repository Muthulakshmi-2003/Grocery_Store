import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import "./Products.scss";

function Products() {
  const [products, setProducts] = useState([]);
  const [editingId, setEditingId] = useState(null);
  const [editQuantity, setEditQuantity] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      // Fetch products
      const productResponse = await axios.get("http://localhost:8085/products");

      const products = productResponse.data;

      // Fetch stock movements
      const movementResponse = await axios.get(
        "http://localhost:8085/inventory-service/stock-movements",
      );

      const stockMovements = movementResponse.data;

      // Combine product + stock movement
      const productsWithInventory = products.map((product) => {
        const movement = stockMovements.find(
          (movement) => movement.productId === product.id,
        );

        return {
          ...product,
          quantity: movement?.quantity ?? 0,
          movementType: movement?.movementType ?? "-",
          createdAt: movement?.createdAt ?? "-",
        };
      });

      console.log("Products with inventory:", productsWithInventory);

      setProducts(productsWithInventory);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  const handleDelete = async (productId) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this product?",
    );

    if (!confirmDelete) {
      return;
    }

    try {
      await axios.delete(`http://localhost:8085/products/${productId}`);

      setProducts(products.filter((product) => product.id !== productId));
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  };

  return (
    <div className="admin-products">
      <div className="admin-products__header">
        <h1>Product Management</h1>

        <button
          className="add-product-btn"
          onClick={() => navigate("/admin/products/add")}
        >
          + Add Product
        </button>
      </div>

      <div className="admin-products__table">
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Category</th>
              <th>Price</th>
              <th>Description</th>
              <th>Quantity</th>
              <th>Created At</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {products.map((product) => (
              <tr key={product.id}>
                <td>{product.name}</td>

                <td>{product.categoryId}</td>

                <td>₹{product.price}</td>

                <td>{product.description}</td>

                <td>
                  {editingId === product.id ? (
                    <>
                      <input
                        type="number"
                        value={editQuantity}
                        onChange={(e) => setEditQuantity(e.target.value)}
                      />

                      <button
                        onClick={() => {
                          setProducts(
                            products.map((item) =>
                              item.id === product.id
                                ? {
                                    ...item,
                                    quantity: editQuantity,
                                  }
                                : item,
                            ),
                          );

                          setEditingId(null);
                          setEditQuantity("");
                        }}
                      >
                        Save
                      </button>
                    </>
                  ) : (
                    product.quantity
                  )}
                </td>

                <td>
                  {product.createdAt !== "-"
                    ? new Date(product.createdAt).toLocaleString()
                    : "-"}
                </td>

                <td>
                  <button
                    onClick={() => {
                      setEditingId(product.id);
                      setEditQuantity(product.quantity);
                    }}
                  >
                    Edit
                  </button>

                  <button onClick={() => handleDelete(product.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Products;
