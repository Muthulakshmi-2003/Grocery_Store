import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar/Navbar";
import Home from "./pages/Home/Home";
import Category from "./components/BackendComponenets/Category/category";
import Product from "./components/BackendComponenets/Product/product";
import Login from "./pages/Login/Login";
import Register from "./pages/Register/Register";
import Search from "./components/Navbar/Search";
import Profile from "./pages/Profile/profile";
import EditProfile from "./pages/EditProfile/EditProfile";
import Cart from "./components/Cart/cart";
import CategoryProducts from "./components/CategoryProducts/categoryProduct";

import Dashboard from "./pages/Admin/Dashboard/Dashboard";
import Products from "./pages/Admin/Products/products";
import AddProduct from "./pages/Admin/Products/AddProducts/AddProducts";
import AboutUs from "./components/AboutUs/AboutUs";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/Register" element={<Register />} />
        <Route path="/categories" element={<Category />} />
        <Route path="/products" element={<Product />} />
        <Route path="/search" element={<Search />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/profile/edit" element={<EditProfile />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/category/:categoryId" element={<CategoryProducts />} />
        <Route path="/about" element={<AboutUs />} />
        <Route path="/admin/dashboard" element={<Dashboard />} />
        <Route path="/admin/products" element={<Products />} />
        <Route path="/admin/products/add" element={<AddProduct />} />
      </Routes>
    </BrowserRouter>
  );
}
export default App;
