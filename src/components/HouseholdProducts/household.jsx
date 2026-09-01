import "../../styles/Variable.scss";


import cooker from "../../assets/cooker.jpg";
import fan from "../../assets/cooker.jpg";
import pan from "../../assets/cooker.jpg";
import otg from "../../assets/cooker.jpg";

function Household(){
    const household = [
        {
            image:cooker,
            name: "Prestige Cooker",
            quantity: "1 ",
            price: 1200
        },
        {
            image: fan,
            name: "Fan",
            quantity: "1",
            price: 6000
        },
        {
            image: pan,
            name: "Pan",
            quantity: "1",
            price: 900
        },
        {
            image: otg,
            name: "OTG oven",
            quantity: "1",
            price: 800
        }
    ];

    return (
        <section className="product-section">

            <div className="section-header">
                <h2>HouseHold Products </h2>

                <a href="#">See All</a>
            </div>

            <div className="product-grid">

                {household.map((product, index) => (

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

export default Household;