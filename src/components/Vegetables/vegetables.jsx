import "../../styles/ProductSection.scss";

import carrot from "../../assets/carrot.jpg";
import tomato from "../../assets/carrot.jpg";
import potato from "../../assets/carrot.jpg";
import onion from "../../assets/carrot.jpg";

function Vegetables(){

 const vegetables = [
        {
            image: carrot,
            name: "Fresh Carrot",
            quantity: "500 g",
            price: 35
        },
        {
            image: tomato,
            name: "Fresh Tomato",
            quantity: "1 kg",
            price: 40
        },
        {
            image: potato,
            name: "Potato",
            quantity: "1 kg",
            price: 30
        },
        {
            image: onion,
            name: "Fresh Onion",
            quantity: "1 kg",
            price: 45
        }
    ];

    return (
        <section className="product-section">

            <div className="section-header">
                <h2>Fresh Vegetables</h2>

                <a href="#">See All</a>
            </div>

            <div className="product-grid">

                {vegetables.map((product, index) => (

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

export default Vegetables;