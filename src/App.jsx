import { BrowserRouter,Routes , Route } from "react-router-dom";

import Navbar from "./components/Navbar/Navbar";
import Home from "./pages/Home/Home";
import Category from "./components/BackendComponenets/Category/category";
import Product from "./components/BackendComponenets/Product/product";
import Login from "./pages/Login/Login";
import Register from "./pages/Register/Register";


function App(){
  return (
    
    <BrowserRouter>
     <Navbar />
       <Routes>
         <Route path="/" element={ <Home />}/>
         <Route path="/login" element={<Login />} />
         <Route path="/Register" element={<Register/>} />
        <Route path="/categories" element={<Category/>}/>
         <Route path="/products" element={<Product />}/>
       </Routes>
    </BrowserRouter>

   
  );
}
export default App;