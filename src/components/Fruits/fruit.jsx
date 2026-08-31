import "../../styles/Variable.scss";


import apple from "../../assets/pomegranate.jpg";
import banana from "../../assets/pomegranate.jpg";
import orange from "../../assets/pomegranate.jpg";
import pomegrants from "../../assets/pomegranate.jpg";

function Fruits(){
    const fruits = [
        {
            image: apple,
            name: "Fresh Apple",
            quantity: "1 kg",
            price: 120
        },
        {
            image: banana,
            name: "Banana",
            quantity: "1 dozen",
            price: 60
        },
        {
            image: orange,
            name: "Orange",
            quantity: "1 kg",
            price: 90
        },
        {
            image: pomegrants,
            name: "Fresh pomegrants",
            quantity: "500 g",
            price: 80
        }
    ];

    return (
        <section className="product-section">

            <div className="section-header">
                <h2>Fresh Fruits</h2>

                <a href="#">See All</a>
            </div>

            <div className="product-grid">

                {fruits.map((product, index) => (

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

export default Fruits;