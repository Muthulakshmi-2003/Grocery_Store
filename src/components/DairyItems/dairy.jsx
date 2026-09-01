import "../../styles/Variable.scss";


import milk from "../../assets/milk.jpg";
import tofue from "../../assets/milk.jpg";
import panner from "../../assets/milk.jpg";
import curd from "../../assets/milk.jpg";

function Dairy(){
    const dairy = [
        {
            image:milk ,
            name: "Fresh milk",
            quantity: "1 lit",
            price: 120
        },
        {
            image: tofue,
            name: "Fresh tofue",
            quantity: "100 gram",
            price: 60
        },
        {
            image: panner,
            name: "Pannere",
            quantity: "100 gram",
            price: 90
        },
        {
            image: curd,
            name: "Fresh Curd",
            quantity: "100 g",
            price: 80
        }
    ];

    return (
        <section className="product-section">

            <div className="section-header">
                <h2>Fresh Dairy Products</h2>

                <a href="#">See All</a>
            </div>

            <div className="product-grid">

                {dairy.map((product, index) => (

                    <div className="product-card" key={index}>

                        <div className="product-image">
                            <img
                                src={product.image}
                                alt={product.name}
                            />
                        </div>

                        <h3>{product.name}</h3>

                        <p>{product.quantity}</p>

                        <strong>₹{product.price}</strong>

                        <button>Add</button>

                    </div>

                ))}

            </div>

        </section>
    );
}

export default Dairy;