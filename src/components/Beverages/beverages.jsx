import "../../styles/Variable.scss";


import stingJuice from "../../assets/string-juice.jpg";
import mazza from "../../assets/string-juice.jpg";
import sevenUp from "../../assets/string-juice.jpg";
import soda from "../../assets/string-juice.jpg";

function Beverages(){
    const beverages = [
        {
            image:stingJuice ,
            name: "StringGasHola",
            quantity: "1 ",
            price: 120
        },
        {
            image: mazza,
            name: "Mazza mango original",
            quantity: "1",
            price: 60
        },
        {
            image: sevenUp,
            name: "SevenUp",
            quantity: "1",
            price: 90
        },
        {
            image: soda,
            name: "Goli soda",
            quantity: "1",
            price: 80
        }
    ];

    return (
        <section className="product-section">

            <div className="section-header">
                <h2>Soft Drinks </h2>

                <a href="#">See All</a>
            </div>

            <div className="product-grid">

                {beverages.map((product, index) => (

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

export default Beverages;